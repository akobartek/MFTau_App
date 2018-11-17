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
import android.content.Intent
import android.net.Uri
import android.app.Activity
import android.view.Menu
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import pl.mftau.mftau.model.Member


class MemberEditorActivity : AppCompatActivity() {

    private lateinit var mAuth: FirebaseAuth
    private lateinit var mFirestore: FirebaseFirestore
    private lateinit var mStorageRef: StorageReference

    private var mMember: Member? = null
    private var mFilePath: Uri? = null
    private var mPersonHasChanged = false

    private val mTouchListener = View.OnTouchListener { _, _ ->
        mPersonHasChanged = true
        false
    }

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

        mMember = intent.getParcelableExtra("member")
        if (mMember == null) {
            invalidateOptionsMenu()
            title = getString(R.string.add_member)
            GlideApp.with(this@MemberEditorActivity)
                    .load(R.drawable.ic_photo)
                    .into(addMemberPhoto)
        } else {
            title = getString(R.string.edit_member)
            GlideApp.with(this@MemberEditorActivity)
                    .load(mStorageRef.child("$firestoreCollectionMembers/${mMember!!.id}.jpg"))
                    .circleCrop()
                    .placeholder(R.drawable.ic_user)
                    .into(addMemberPhoto)
            memberNameET.setText(mMember!!.name)
            cityET.setText(mMember!!.city)
            responsibleSwitch.isSelected = mMember!!.isResponsible
        }

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

            val memberValues = HashMap<String, Any>()
            memberValues[firestoreKeyName] = memberNameET.text.toString().trim()
            memberValues[firestoreKeyCity] = cityET.text.toString().trim()
            memberValues[firestoreKeyIsResponsible] = responsibleSwitch.isChecked

            if (mMember == null) {
                mFirestore.collection(firestoreCollectionCities)
                        .document(mAuth.currentUser!!.email!!.substring(0, mAuth.currentUser!!.email!!.indexOf("@")))
                        .collection(firestoreCollectionMembers)
                        .add(memberValues)
                        .addOnSuccessListener { documentReference ->
                            if (mFilePath != null) {
                                val dialog = AlertDialog.Builder(this@MemberEditorActivity)
                                        .setView(R.layout.dialog_loading)
                                        .create()

                                dialog.show()
                                putPhoto(documentReference.id, dialog)
                            } else {
                                Toast.makeText(this@MemberEditorActivity,
                                        getString(R.string.member_added), Toast.LENGTH_SHORT).show()
                                finish()
                            }
                        }
                        .addOnFailureListener {
                            Toast.makeText(this@MemberEditorActivity,
                                    getString(R.string.member_add_error), Toast.LENGTH_SHORT).show()
                        }
            } else {
                mPersonHasChanged = (memberNameET.text.toString().trim() != mMember!!.name
                        || cityET.text.toString().trim() != mMember!!.city
                        || responsibleSwitch.isChecked != mMember!!.isResponsible)

                when {
                    mPersonHasChanged -> mFirestore.collection(firestoreCollectionCities)
                            .document(mAuth.currentUser!!.email!!.substring(0, mAuth.currentUser!!.email!!.indexOf("@")))
                            .collection(firestoreCollectionMembers)
                            .document(mMember!!.id)
                            .set(memberValues)
                            .addOnSuccessListener {
                                if (mFilePath != null) {
                                    val dialog = AlertDialog.Builder(this@MemberEditorActivity)
                                            .setView(R.layout.dialog_loading)
                                            .create()
                                    dialog.show()

                                    mStorageRef.child("$firestoreCollectionMembers/${mMember!!.id}.jpg")
                                            .delete()
                                            .addOnSuccessListener {
                                                putPhoto(mMember!!.id, dialog)
                                            }
                                            .addOnFailureListener {
                                                putPhoto(mMember!!.id, dialog)
                                            }
                                } else {
                                    Toast.makeText(this@MemberEditorActivity,
                                            getString(R.string.member_added), Toast.LENGTH_SHORT).show()
                                    finish()
                                }
                            }
                            .addOnFailureListener {
                                Toast.makeText(this@MemberEditorActivity,
                                        getString(R.string.member_update_error), Toast.LENGTH_SHORT).show()
                            }
                    mFilePath != null -> {
                        val dialog = AlertDialog.Builder(this@MemberEditorActivity)
                                .setView(R.layout.dialog_loading)
                                .create()
                        dialog.show()

                        mStorageRef.child("$firestoreCollectionMembers/${mMember!!.id}.jpg")
                                .delete()
                                .addOnSuccessListener {
                                    putPhoto(mMember!!.id, dialog)
                                }
                                .addOnFailureListener {
                                    putPhoto(mMember!!.id, dialog)
                                }
                    }
                    else -> finish()
                }
            }
        }

        addMemberPhoto.setOnClickListener {
            val intent = Intent()
            intent.type = "image/*"
            intent.action = Intent.ACTION_GET_CONTENT
            startActivityForResult(Intent.createChooser(intent, getString(R.string.select_photo)), 777)
        }

        memberNameET.setOnTouchListener(mTouchListener)
        cityET.setOnTouchListener(mTouchListener)
        responsibleSwitch.setOnTouchListener(mTouchListener)
    }

    private fun putPhoto(id: String, dialog: AlertDialog) {
        mStorageRef.child("$firestoreCollectionMembers/$id.jpg")
                .putFile(mFilePath!!)
                .addOnSuccessListener {
                    dialog.dismiss()
                    Toast.makeText(this@MemberEditorActivity,
                            getString(R.string.member_with_photo_saved), Toast.LENGTH_SHORT).show()
                    finish()
                }
                .addOnFailureListener {
                    dialog.dismiss()
                    Toast.makeText(this@MemberEditorActivity,
                            getString(R.string.member_photo_error), Toast.LENGTH_SHORT).show()
                    finish()
                }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == 777 && resultCode == Activity.RESULT_OK && data != null && data.data != null) {
            mFilePath = data.data
            try {
                GlideApp.with(this@MemberEditorActivity)
                        .load(mFilePath)
                        .transform(CircleCrop())
                        .into(addMemberPhoto)
            } catch (exc: Exception) {
                exc.printStackTrace()
            }
        }
    }

    override fun onBackPressed() {
        if (mFilePath != null || mPersonHasChanged)
            showUnsavedChangesDialog()
        else
            super.onBackPressed()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_edit_member, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onPrepareOptionsMenu(menu: Menu): Boolean {
        if (mMember == null)
            menu.findItem(R.id.action_delete_member).isVisible = false
        return super.onPrepareOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_delete_member -> {
                showDeleteConfirmationDialog()
                true
            }
            android.R.id.home -> {
                onBackPressed()
                true
            }
            else -> return super.onOptionsItemSelected(item)
        }
    }

    private fun showUnsavedChangesDialog() =
            AlertDialog.Builder(this@MemberEditorActivity)
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
            AlertDialog.Builder(this@MemberEditorActivity)
                    .setMessage(R.string.member_delete_dialog_msg)
                    .setPositiveButton(R.string.delete) { _, _ -> deletePerson() }
                    .setNegativeButton(R.string.cancel) { dialog, _ -> dialog.dismiss() }
                    .create()
                    .show()

    private fun deletePerson() {
        if (mMember != null) {
            mFirestore.collection(firestoreCollectionCities)
                    .document(mAuth.currentUser!!.email!!.substring(0, mAuth.currentUser!!.email!!.indexOf("@")))
                    .collection(firestoreCollectionMembers)
                    .document(mMember!!.id)
                    .delete()
                    .addOnSuccessListener {
                        mStorageRef.child("$firestoreCollectionMembers/${mMember!!.id}.jpg")
                                .delete()
                                .addOnSuccessListener {
                                    Toast.makeText(this@MemberEditorActivity, getString(R.string.member_delete_successfully),
                                            Toast.LENGTH_SHORT).show()
                                }
                    }
                    .addOnFailureListener {
                        Toast.makeText(this@MemberEditorActivity, getString(R.string.delete_error), Toast.LENGTH_SHORT).show()
                    }
        }
        finish()
    }

}
