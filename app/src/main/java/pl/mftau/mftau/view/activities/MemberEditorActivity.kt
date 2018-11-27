package pl.mftau.mftau.view.activities

import android.graphics.Color
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AlertDialog
import com.bumptech.glide.load.resource.bitmap.CircleCrop
import kotlinx.android.synthetic.main.activity_member_editor.*
import pl.mftau.mftau.R
import pl.mftau.mftau.utils.GlideApp
import pl.mftau.mftau.utils.FirestoreUtils.firestoreKeyName
import pl.mftau.mftau.utils.FirestoreUtils.firestoreKeyCity
import pl.mftau.mftau.utils.FirestoreUtils.firestoreKeyIsResponsible
import android.content.Intent
import android.app.Activity
import android.content.Context
import android.view.Menu
import android.view.inputmethod.InputMethodManager
import androidx.core.app.NavUtils
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProviders
import androidx.preference.PreferenceManager
import com.google.firebase.auth.FirebaseAuth
import pl.mftau.mftau.databinding.ActivityMemberEditorBinding
import pl.mftau.mftau.viewmodel.MemberEditorViewModel


class MemberEditorActivity : AppCompatActivity() {

    private lateinit var mMemberEditorViewModel: MemberEditorViewModel
    private var mPersonHasChanged = false

    override fun onCreate(savedInstanceState: Bundle?) {
        if (PreferenceManager.getDefaultSharedPreferences(this@MemberEditorActivity)
                        .getBoolean(getString(R.string.night_mode_key), false)) {
            setTheme(R.style.AppTheme_Dark)
        } else {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
                window.statusBarColor = Color.WHITE
            }
        }

        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_member_editor)
        val binding = DataBindingUtil.setContentView<ActivityMemberEditorBinding>(
                this@MemberEditorActivity, R.layout.activity_member_editor)
        setSupportActionBar(addMemberToolbar)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        supportActionBar!!.setHomeAsUpIndicator(R.drawable.ic_arrow_back)

        mMemberEditorViewModel = ViewModelProviders.of(this@MemberEditorActivity).get(MemberEditorViewModel::class.java)
        binding.viewModel = mMemberEditorViewModel

        mMemberEditorViewModel.member = intent.getParcelableExtra("member")
        title = if (mMemberEditorViewModel.member == null) {
            invalidateOptionsMenu()
            getString(R.string.add_member)
        } else {
            getString(R.string.edit_member)
        }

        setOnClickListeners()
    }

    private fun setOnClickListeners() {
        saveMemberBtn.setOnClickListener {
            var errorOccurred = false
            if (memberNameET.text.isNullOrBlank()) {
                memberNameET.error = getString(R.string.empty_name_error)
                errorOccurred = true
            }
            if (cityET.text.isNullOrBlank()) {
                cityET.error = getString(R.string.empty_city_error)
                errorOccurred = true
            }
            if (errorOccurred)
                return@setOnClickListener

            val memberValues = HashMap<String, Any>()
            memberValues[firestoreKeyName] = memberNameET.text.toString().trim()
            memberValues[firestoreKeyCity] = cityET.text.toString().trim()
            memberValues[firestoreKeyIsResponsible] = responsibleSwitch.isChecked

            if (mMemberEditorViewModel.member == null) {
                mMemberEditorViewModel.addMember(this@MemberEditorActivity, memberValues)
            } else {
                mPersonHasChanged = (memberNameET.text.toString().trim() != mMemberEditorViewModel.member!!.name
                        || cityET.text.toString().trim() != mMemberEditorViewModel.member!!.city
                        || responsibleSwitch.isChecked != mMemberEditorViewModel.member!!.isResponsible)
                when {
                    mPersonHasChanged -> {
                        mMemberEditorViewModel.updateMember(this@MemberEditorActivity, memberValues)
                    }
                    mMemberEditorViewModel.filePath != null -> {
                        mMemberEditorViewModel.updatePhoto(this@MemberEditorActivity)
                    }
                    else -> finish()
                }
            }
        }

        addMemberPhoto.setOnClickListener {
            if (FirebaseAuth.getInstance().currentUser!!.email!! == "example@mftau.pl")
                return@setOnClickListener

            val intent = Intent()
            intent.type = "image/*"
            intent.action = Intent.ACTION_GET_CONTENT
            startActivityForResult(Intent.createChooser(intent, getString(R.string.select_photo)), 777)
        }

        addMemberLayout.setOnClickListener(mHideKeyboardClickListener)
        responsibleSwitch.setOnClickListener(mHideKeyboardClickListener)
        responsibleTextView.setOnClickListener(mHideKeyboardClickListener)

        memberNameET.setOnTouchListener(mTouchListener)
        cityET.setOnTouchListener(mTouchListener)
        responsibleSwitch.setOnTouchListener(mTouchListener)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == 777 && resultCode == Activity.RESULT_OK && data != null && data.data != null) {
            mMemberEditorViewModel.filePath = contentResolver.openInputStream(data.data!!)
            try {
                GlideApp.with(this@MemberEditorActivity)
                        .load(data.data)
                        .transform(CircleCrop())
                        .into(addMemberPhoto)
            } catch (exc: Exception) {
                exc.printStackTrace()
            }
        }
    }

    override fun onBackPressed() {
        if (mMemberEditorViewModel.filePath != null || mPersonHasChanged)
            showUnsavedChangesDialog()
        else {
            startActivity(Intent(this@MemberEditorActivity, MembersActivity::class.java))
            finish()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_edit_member, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onPrepareOptionsMenu(menu: Menu): Boolean {
        if (mMemberEditorViewModel.member == null)
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
                    .setPositiveButton(R.string.delete) { dialog, _ ->
                        dialog.dismiss()
                        mMemberEditorViewModel.deleteMember(this@MemberEditorActivity)
                        finish()
                    }
                    .setNegativeButton(R.string.cancel) { dialog, _ -> dialog.dismiss() }
                    .create()
                    .show()


    private val mTouchListener = View.OnTouchListener { _, _ ->
        mPersonHasChanged = true
        false
    }
    private val mHideKeyboardClickListener = View.OnClickListener {
        (getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager)
                .hideSoftInputFromWindow(currentFocus!!.windowToken, 0)
    }
}