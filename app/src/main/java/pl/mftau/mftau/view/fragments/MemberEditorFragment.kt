package pl.mftau.mftau.view.fragments

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.provider.MediaStore
import android.view.LayoutInflater
import android.view.Menu
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.widget.Toolbar
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.load.resource.bitmap.CircleCrop
import com.google.firebase.auth.FirebaseAuth
import pl.mftau.mftau.R
import pl.mftau.mftau.databinding.FragmentMemberEditorBinding
import pl.mftau.mftau.model.local_db.Member
import pl.mftau.mftau.utils.FirestoreUtils.firestoreKeyCity
import pl.mftau.mftau.utils.FirestoreUtils.firestoreKeyIsResponsible
import pl.mftau.mftau.utils.FirestoreUtils.firestoreKeyName
import pl.mftau.mftau.utils.GlideApp
import pl.mftau.mftau.utils.hideKeyboard
import pl.mftau.mftau.viewmodel.MainViewModel
import java.io.InputStream

class MemberEditorFragment : BindingFragment<FragmentMemberEditorBinding>() {

    companion object {
        var personHasChanged = false
    }

    private lateinit var mViewModel: MainViewModel
    private var mMember: Member? = null
    private var mFilePath: InputStream? = null

    override fun attachBinding(inflater: LayoutInflater, container: ViewGroup?) =
        FragmentMemberEditorBinding.inflate(inflater, container, false)

    override fun setup(savedInstanceState: Bundle?) {
        personHasChanged = false
        inflateToolbarMenu(binding.memberEditorToolbar)

        activity?.let {
            mViewModel = ViewModelProvider(it)[MainViewModel::class.java]
        }
        arguments?.let { bundle ->
            mMember = MemberEditorFragmentArgs.fromBundle(bundle).member
            binding.memberEditorToolbarTitle.text =
                if (mMember == null) getString(R.string.add_member) else getString(R.string.edit_member)
            mMember?.let {
                with(binding.contentMemberEditor) {
                    Member.loadImage(addMemberPhoto, it)
                    memberNameET.setText(it.name)
                    cityET.setText(it.city)
                    responsibleSwitch.isChecked = it.isResponsible
                }
            }
            setupToolbarMenuIcons(binding.memberEditorToolbar.menu)
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

    private val pickImageFromGalleryForResult =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == Activity.RESULT_OK && result.data != null && result.data?.data != null) {
                mFilePath = activity?.contentResolver?.openInputStream(result.data!!.data!!)
                try {
                    GlideApp.with(this)
                        .load(result.data!!.data)
                        .transform(CircleCrop())
                        .into(binding.contentMemberEditor.addMemberPhoto)
                    personHasChanged = true
                } catch (exc: Exception) {
                    exc.printStackTrace()
                }
            }
        }

    @SuppressLint("ClickableViewAccessibility")
    private fun setOnClickListeners() {
        with(binding.contentMemberEditor) {
            binding.saveMemberBtn.setOnClickListener {
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

                if (mMember == null) {
                    mViewModel.addMember(requireActivity(), memberValues, mFilePath)
                } else {
                    personHasChanged =
                        (memberNameET.text.toString().trim() != mMember!!.name
                                || cityET.text.toString().trim() != mMember!!.city
                                || responsibleSwitch.isChecked != mMember!!.isResponsible)
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

            addMemberPhoto.setOnClickListener {
                if (FirebaseAuth.getInstance().currentUser!!.email!! == "example@mftau.pl")
                    return@setOnClickListener

                val intent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
                pickImageFromGalleryForResult.launch(intent)
            }

            addMemberLayout.setOnClickListener(mHideKeyboardClickListener)
            responsibleSwitch.setOnClickListener(mHideKeyboardClickListener)
            responsibleTextView.setOnClickListener(mHideKeyboardClickListener)

            memberNameET.setOnTouchListener(mTouchListener)
            cityET.setOnTouchListener(mTouchListener)
            responsibleSwitch.setOnTouchListener(mTouchListener)
        }
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
