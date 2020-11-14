package pl.mftau.mftau.view.fragments


import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.load.resource.bitmap.CircleCrop
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.content_member_editor.view.*
import kotlinx.android.synthetic.main.fragment_member_editor.view.*
import pl.mftau.mftau.R
import pl.mftau.mftau.model.Member
import pl.mftau.mftau.utils.FirestoreUtils.firestoreKeyCity
import pl.mftau.mftau.utils.FirestoreUtils.firestoreKeyIsResponsible
import pl.mftau.mftau.utils.FirestoreUtils.firestoreKeyName
import pl.mftau.mftau.utils.GlideApp
import pl.mftau.mftau.utils.hideKeyboard
import pl.mftau.mftau.viewmodel.MainViewModel
import java.io.InputStream

class MemberEditorFragment : Fragment() {

    companion object {
        var personHasChanged = false
    }

    private lateinit var mViewModel: MainViewModel
    private var mMember: Member? = null
    private var mFilePath: InputStream? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        personHasChanged = false
        return inflater.inflate(R.layout.fragment_member_editor, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        inflateToolbarMenu(view.memberEditorToolbar)

        activity?.let {
            mViewModel = ViewModelProvider(it).get(MainViewModel::class.java)
        }
        arguments?.let { bundle ->
            mMember = MemberEditorFragmentArgs.fromBundle(bundle).member
            view.memberEditorToolbarTitle.text =
                if (mMember == null) getString(R.string.add_member) else getString(R.string.edit_member)
            mMember?.let {
                Member.loadImage(view.addMemberPhoto, it)
                view.memberNameET.setText(it.name)
                view.cityET.setText(it.city)
                view.responsibleSwitch.isChecked = it.isResponsible
            }
            setupToolbarMenuIcons(view.memberEditorToolbar.menu)
        }

        setOnClickListeners()
    }

    private fun inflateToolbarMenu(toolbar: Toolbar) {
        toolbar.apply {
            setNavigationOnClickListener { findNavController().navigateUp() }
            inflateMenu(R.menu.menu_member_edit)
            setOnMenuItemClickListener {
                when (it.itemId) {
                    R.id.action_show_presence -> {
                        findNavController().navigate(
                            MemberEditorFragmentDirections.showPresenceDetailsFragment(mMember!!)
                        )
                        true
                    }
                    R.id.action_delete_member -> {
                        showDeleteConfirmationDialog()
                        true
                    }
                    else -> false
                }
            }
        }
    }

    private fun setupToolbarMenuIcons(menu: Menu) {
        if (mMember == null) {
            menu.findItem(R.id.action_show_presence)?.isVisible = false
            menu.findItem(R.id.action_delete_member)?.isVisible = false
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        // TODO() -> deprecation fix
        if (requestCode == 777 && resultCode == Activity.RESULT_OK && data != null && data.data != null) {
            mFilePath = activity?.contentResolver?.openInputStream(data.data!!)
            try {
                GlideApp.with(this)
                    .load(data.data)
                    .transform(CircleCrop())
                    .into(requireView().addMemberPhoto)
                personHasChanged = true

            } catch (exc: Exception) {
                exc.printStackTrace()
            }
        }
    }

    private fun setOnClickListeners() {
        view?.saveMemberBtn?.setOnClickListener {
            var errorOccurred = false
            if (requireView().memberNameET.text.isNullOrBlank()) {
                requireView().memberNameET.error = getString(R.string.empty_name_error)
                errorOccurred = true
            }
            if (requireView().cityET.text.isNullOrBlank()) {
                requireView().cityET.error = getString(R.string.empty_city_error)
                errorOccurred = true
            }
            if (errorOccurred)
                return@setOnClickListener

            val memberValues = HashMap<String, Any>()
            memberValues[firestoreKeyName] = requireView().memberNameET.text.toString().trim()
            memberValues[firestoreKeyCity] = requireView().cityET.text.toString().trim()
            memberValues[firestoreKeyIsResponsible] = requireView().responsibleSwitch.isChecked

            if (mMember == null) {
                mViewModel.addMember(requireActivity(), memberValues, mFilePath)
            } else {
                personHasChanged =
                    (requireView().memberNameET.text.toString().trim() != mMember!!.name
                            || requireView().cityET.text.toString().trim() != mMember!!.city
                            || requireView().responsibleSwitch.isChecked != mMember!!.isResponsible)
                when {
                    personHasChanged -> {
                        mViewModel.updateMember(
                            requireActivity(), mMember!!.id, memberValues, mFilePath
                        )
                    }
                    mFilePath != null -> {
                        mViewModel.updatePhoto(requireActivity(), mMember!!.id, mFilePath)
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
            startActivityForResult(
                Intent.createChooser(intent, getString(R.string.select_photo)), 777
            )
        }

        view?.addMemberLayout?.setOnClickListener(mHideKeyboardClickListener)
        view?.responsibleSwitch?.setOnClickListener(mHideKeyboardClickListener)
        view?.responsibleTextView?.setOnClickListener(mHideKeyboardClickListener)

        view?.memberNameET?.setOnTouchListener(mTouchListener)
        view?.cityET?.setOnTouchListener(mTouchListener)
        view?.responsibleSwitch?.setOnTouchListener(mTouchListener)
    }

    private fun showDeleteConfirmationDialog() =
        AlertDialog.Builder(requireContext())
            .setMessage(R.string.member_delete_dialog_msg)
            .setCancelable(false)
            .setPositiveButton(R.string.delete) { dialog, _ ->
                dialog.dismiss()
                mViewModel.deleteMember(requireActivity(), mMember!!.id)
                findNavController().navigateUp()
            }
            .setNegativeButton(R.string.cancel) { dialog, _ -> dialog.dismiss() }
            .create()
            .show()


    @SuppressLint("ClickableViewAccessibility")
    private val mTouchListener = View.OnTouchListener { _, _ ->
        personHasChanged = true
        false
    }
    private val mHideKeyboardClickListener =
        View.OnClickListener { requireActivity().hideKeyboard() }
}
