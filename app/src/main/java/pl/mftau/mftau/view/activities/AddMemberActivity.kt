package pl.mftau.mftau.view.activities

import android.graphics.Color
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AlertDialog
import com.bumptech.glide.load.resource.bitmap.CircleCrop
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.activity_add_member.*
import pl.mftau.mftau.R
import pl.mftau.mftau.utils.GlideApp
import pl.mftau.mftau.utils.FirestoreUtils.firestoreCollectionCities
import pl.mftau.mftau.utils.FirestoreUtils.firestoreCollectionMembers
import pl.mftau.mftau.utils.FirestoreUtils.firestoreKeyName
import pl.mftau.mftau.utils.FirestoreUtils.firestoreKeyCity
import pl.mftau.mftau.utils.FirestoreUtils.firestoreKeyIsResponsible
import pl.mftau.mftau.utils.FirestoreUtils.firestoreKeyIsPhotoAdded
import android.content.Intent
import android.net.Uri
import android.app.Activity
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import java.io.IOException


class AddMemberActivity : AppCompatActivity() {

    private lateinit var mAuth: FirebaseAuth
    private lateinit var mFirestore: FirebaseFirestore
    private lateinit var mStorageRef: StorageReference

    private var filePath: Uri? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_member)
        setSupportActionBar(addMemberToolbar)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
            window.statusBarColor = Color.WHITE
        }

        mAuth = FirebaseAuth.getInstance()
        mFirestore = FirebaseFirestore.getInstance()
        mStorageRef = FirebaseStorage.getInstance().reference

        GlideApp.with(this@AddMemberActivity)
                .load(R.drawable.ic_photo)
                .into(addMemberPhoto)

        setOnClickListeners()
    }

    private fun setOnClickListeners() {
        saveMemberBtn.setOnClickListener {
            var errorOccurred = false
            if (memberNameET.text.isNullOrEmpty()) {
                memberNameET.error = getString(R.string.empty_name_error)
                errorOccurred = true
            }
            if (cityET.text.isNullOrEmpty()) {
                cityET.error = getString(R.string.empty_city_error)
                errorOccurred = true
            }
            if (errorOccurred)
                return@setOnClickListener

            val newMember = HashMap<String, Any>()
            newMember[firestoreKeyName] = memberNameET.text.toString()
            newMember[firestoreKeyCity] = cityET.text.toString()
            newMember[firestoreKeyIsResponsible] = responsibleSwitch.isChecked
            newMember[firestoreKeyIsPhotoAdded] = false

            val cityWithoutPolishSigns = getCityWithoutPolishSigns()
            mFirestore.collection(firestoreCollectionCities)
                    .document(cityWithoutPolishSigns)
                    .collection(firestoreCollectionMembers)
                    .add(newMember)
                    .addOnSuccessListener { documentReference ->
                        if (filePath != null) {
                            val dialog = AlertDialog.Builder(this@AddMemberActivity)
                                    .setView(R.layout.dialog_loading)
                                    .create()
                            dialog.show()

                            mStorageRef.child("$firestoreCollectionMembers/${documentReference.id}")
                                    .putFile(filePath!!)
                                    .addOnSuccessListener {
                                        mFirestore.collection(firestoreCollectionCities)
                                                .document(cityWithoutPolishSigns)
                                                .collection(firestoreCollectionMembers)
                                                .document(documentReference.id)
                                                .update(firestoreKeyIsPhotoAdded, true)
                                        dialog.dismiss()
                                        Toast.makeText(this@AddMemberActivity,
                                                getString(R.string.member_with_photo_added), Toast.LENGTH_SHORT).show()
                                        finish()
                                    }
                                    .addOnFailureListener {
                                        dialog.dismiss()
                                        Toast.makeText(this@AddMemberActivity,
                                                getString(R.string.member_photo_error), Toast.LENGTH_SHORT).show()
                                        finish()
                                    }
                        } else {
                            Toast.makeText(this@AddMemberActivity,
                                    getString(R.string.member_added), Toast.LENGTH_SHORT).show()
                            finish()
                        }
                    }
                    .addOnFailureListener {
                        Toast.makeText(this@AddMemberActivity,
                                getString(R.string.member_add_error), Toast.LENGTH_SHORT).show()
                    }
        }

        addMemberPhoto.setOnClickListener {
            val intent = Intent()
            intent.type = "image/*"
            intent.action = Intent.ACTION_GET_CONTENT
            startActivityForResult(Intent.createChooser(intent, getString(R.string.select_photo)), 777)
        }
    }

    private fun getCityWithoutPolishSigns(): String = cityET.text.toString().toLowerCase()
            .replace(" ", "").replace("ą", "a")
            .replace("ć", "c").replace("ę", "e")
            .replace("ł", "l").replace("ń", "n")
            .replace("ó", "o").replace("ś", "s")
            .replace("ż", "z").replace("ź", "z")

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == 777 && resultCode == Activity.RESULT_OK && data != null && data.data != null) {
            filePath = data.data
            try {
                GlideApp.with(this@AddMemberActivity)
                        .load(filePath)
                        .transform(CircleCrop())
                        .into(addMemberPhoto)
            } catch (e: IOException) {
                e.printStackTrace()
            }
        }
    }

    override fun onBackPressed() {
        if (filePath != null || memberNameET.text.isNullOrEmpty() || cityET.text.isNullOrEmpty())
            showUnsavedChangesDialog()
        else
            super.onBackPressed()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                onBackPressed()
                true
            }
            else -> return super.onOptionsItemSelected(item)
        }
    }

    private fun showUnsavedChangesDialog() =
            AlertDialog.Builder(this@AddMemberActivity)
                    .setMessage(R.string.unsaved_changes_dialog_msg)
                    .setCancelable(false)
                    .setPositiveButton(R.string.discard) { dialog, _ ->
                        dialog.dismiss()
                        finish()
                    }
                    .setNegativeButton(R.string.keep_editing) { dialog, _ -> dialog.dismiss() }
                    .create()
                    .show()


    private fun showDeleteConfirmationDialog() =
            AlertDialog.Builder(this@AddMemberActivity)
                    .setMessage(R.string.delete_dialog_msg)
//                .setPositiveButton(R.string.delete, { _, _ -> deletePerson() })
                    .setNegativeButton(R.string.cancel) { dialog, _ -> dialog.dismiss() }
                    .create()
                    .show()

}
