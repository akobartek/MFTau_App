package pl.mftau.mftau.view.fragments


import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.*
import android.view.inputmethod.InputMethodManager
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.load.resource.bitmap.CircleCrop
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.fragment_member_editor.view.*
import pl.mftau.mftau.R
import pl.mftau.mftau.model.Member
import pl.mftau.mftau.utils.FirestoreUtils.firestoreKeyCity
import pl.mftau.mftau.utils.FirestoreUtils.firestoreKeyIsResponsible
import pl.mftau.mftau.utils.FirestoreUtils.firestoreKeyName
import pl.mftau.mftau.utils.GlideApp
import pl.mftau.mftau.viewmodel.MainViewModel
import java.io.InputStream

class MemberEditorFragment : Fragment() {

    companion object {
        var personHasChanged = false
    }

    private lateinit var mViewModel: MainViewModel
    private var mMember: Member? = null
    private var mFilePath: InputStream? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        setHasOptionsMenu(true)
        personHasChanged = false
        return inflater.inflate(R.layout.fragment_member_editor, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        activity?.let {
            mViewModel = ViewModelProvider(it).get(MainViewModel::class.java)
        }
        arguments?.let { bundle ->
            mMember = MemberEditorFragmentArgs.fromBundle(bundle).member
            mMember?.let {
                Member.loadImage(view.addMemberPhoto, it)
                view.memberNameET.setText(it.name)
                view.cityET.setText(it.city)
                view.responsibleSwitch.isChecked = it.isResponsible
            }
            activity?.invalidateOptionsMenu()
        }

        setOnClickListeners()
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) =
        inflater.inflate(R.menu.menu_member_edit, menu)

    override fun onPrepareOptionsMenu(menu: Menu) {
        if (mMember == null) {
            menu.findItem(R.id.action_show_presence)?.isVisible = false
            menu.findItem(R.id.action_delete_member)?.isVisible = false
        }
        super.onPrepareOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean = when (item.itemId) {
        R.id.action_show_presence -> {
            findNavController().navigate(MemberEditorFragmentDirections.showPresenceDetailsFragment(mMember!!))
            true
        }
        R.id.action_delete_member -> {
            showDeleteConfirmationDialog()
            true
        }
        else -> super.onOptionsItemSelected(item)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == 777 && resultCode == Activity.RESULT_OK && data != null && data.data != null) {
            mFilePath = activity?.contentResolver?.openInputStream(data.data!!)
            try {
                GlideApp.with(this)
                    .load(data.data)
                    .transform(CircleCrop())
                    .into(view!!.addMemberPhoto)
                personHasChanged = true

            } catch (exc: Exception) {
                exc.printStackTrace()
            }
        }
    }

    private fun setOnClickListeners() {
        view?.saveMemberBtn?.setOnClickListener {
            var errorOccurred = false
            if (view!!.memberNameET.text.isNullOrBlank()) {
                view!!.memberNameET.error = getString(R.string.empty_name_error)
                errorOccurred = true
            }
            if (view!!.cityET.text.isNullOrBlank()) {
                view!!.cityET.error = getString(R.string.empty_city_error)
                errorOccurred = true
            }
            if (errorOccurred)
                return@setOnClickListener

            val memberValues = HashMap<String, Any>()
            memberValues[firestoreKeyName] = view!!.memberNameET.text.toString().trim()
            memberValues[firestoreKeyCity] = view!!.cityET.text.toString().trim()
            memberValues[firestoreKeyIsResponsible] = view!!.responsibleSwitch.isChecked

            if (mMember == null) {
                mViewModel.addMember(activity!!, memberValues, mFilePath)
            } else {
                personHasChanged = (view!!.memberNameET.text.toString().trim() != mMember!!.name
                        || view!!.cityET.text.toString().trim() != mMember!!.city
                        || view!!.responsibleSwitch.isChecked != mMember!!.isResponsible)
                when {
                    personHasChanged -> {
                        mViewModel.updateMember(activity!!, mMember!!.id, memberValues, mFilePath)
                    }
                    mFilePath != null -> {
                        mViewModel.updatePhoto(activity!!, mMember!!.id, mFilePath)
                    }
                }
            }
            findNavController().navigateUp()
        }

        view?.addMemberPhoto?.setOnClickListener {
            if (FirebaseAuth.getInstance().currentUser!!.email!! == "example@mftau.pl")
                return@setOnClickListener

            val intent = Intent()
            intent.type = "image/*"
            intent.action = Intent.ACTION_GET_CONTENT
            startActivityForResult(Intent.createChooser(intent, getString(R.string.select_photo)), 777)
        }

        view?.addMemberLayout?.setOnClickListener(mHideKeyboardClickListener)
        view?.responsibleSwitch?.setOnClickListener(mHideKeyboardClickListener)
        view?.responsibleTextView?.setOnClickListener(mHideKeyboardClickListener)

        view?.memberNameET?.setOnTouchListener(mTouchListener)
        view?.cityET?.setOnTouchListener(mTouchListener)
        view?.responsibleSwitch?.setOnTouchListener(mTouchListener)
    }

    private fun showDeleteConfirmationDialog() =
        AlertDialog.Builder(context!!)
            .setMessage(R.string.member_delete_dialog_msg)
            .setCancelable(false)
            .setPositiveButton(R.string.delete) { dialog, _ ->
                dialog.dismiss()
                mViewModel.deleteMember(activity!!, mMember!!.id)
                findNavController().navigateUp()
            }
            .setNegativeButton(R.string.cancel) { dialog, _ -> dialog.dismiss() }
            .create()
            .show()


    private val mTouchListener = View.OnTouchListener { _, _ ->
        personHasChanged = true
        false
    }
    private val mHideKeyboardClickListener = View.OnClickListener {
        (it.context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager)
            .hideSoftInputFromWindow(activity?.currentFocus?.windowToken, 0)
    }
}
