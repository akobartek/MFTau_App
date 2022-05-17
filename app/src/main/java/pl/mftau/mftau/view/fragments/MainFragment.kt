package pl.mftau.mftau.view.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import pl.mftau.mftau.R
import pl.mftau.mftau.databinding.FragmentMainBinding
import pl.mftau.mftau.utils.FirestoreUtils.firestoreCollectionUsers
import pl.mftau.mftau.utils.FirestoreUtils.firestoreKeyIsAdmin
import pl.mftau.mftau.utils.FirestoreUtils.firestoreKeyIsLeader
import pl.mftau.mftau.utils.FirestoreUtils.firestoreKeyIsMember
import pl.mftau.mftau.utils.isChromeCustomTabsSupported
import pl.mftau.mftau.utils.openWebsiteInChromeCustomTabs
import pl.mftau.mftau.viewmodel.MainViewModel
import java.lang.NullPointerException

class MainFragment : BindingFragment<FragmentMainBinding>() {

    private lateinit var mViewModel: MainViewModel
    private val mAuth: FirebaseAuth by lazy { FirebaseAuth.getInstance() }

    override fun attachBinding(inflater: LayoutInflater, container: ViewGroup?) =
        FragmentMainBinding.inflate(inflater, container, false)

    override fun setup(savedInstanceState: Bundle?) {
        inflateToolbarMenu()
        setOnClickListeners()
        activity?.let { mViewModel = ViewModelProvider(it)[MainViewModel::class.java] }
    }

    override fun onResume() {
        super.onResume()
        if (mAuth.currentUser != null) {
            FirebaseFirestore.getInstance().collection(firestoreCollectionUsers)
                .document(FirebaseAuth.getInstance().currentUser!!.uid)
                .get()
                .addOnCompleteListener { task ->
                    if (task.isSuccessful)
                        when {
                            (task.result?.get(firestoreKeyIsAdmin) as Boolean) -> {
                                showUIChanges(MainViewModel.USER_TYPE_ADMIN)
                                mViewModel.currentUserType = MainViewModel.USER_TYPE_ADMIN
                            }
                            (task.result?.get(firestoreKeyIsLeader) as Boolean) -> {
                                showUIChanges(MainViewModel.USER_TYPE_LEADER)
                                mViewModel.currentUserType = MainViewModel.USER_TYPE_LEADER
                            }
                            (task.result?.get(firestoreKeyIsMember) as Boolean) -> {
                                showUIChanges(MainViewModel.USER_TYPE_MEMBER)
                                mViewModel.currentUserType = MainViewModel.USER_TYPE_MEMBER
                            }
                            else -> {
                                showUIChanges(MainViewModel.USER_TYPE_NONE)
                                mViewModel.currentUserType = MainViewModel.USER_TYPE_NONE
                            }
                        }
                }
        } else {
            showUIChanges(MainViewModel.USER_TYPE_NONE)
        }
    }

    private fun inflateToolbarMenu() {
        binding.mainToolbar.apply {
            setMenuItemsVisibility(menu)
            setOnMenuItemClickListener {
                when (it.itemId) {
                    R.id.action_sign_in -> {
                        findNavController().navigate(MainFragmentDirections.showLoginFragment())
                    }
                    R.id.action_sign_out -> {
                        mAuth.signOut()
                        showUIChanges(MainViewModel.USER_TYPE_NONE)
                    }
                    R.id.action_settings -> {
                        findNavController().navigate(MainFragmentDirections.showPreferenceFragment())
                    }
                    R.id.action_ask_for_pray -> {
                        findNavController().navigate(MainFragmentDirections.showEmailFragment("pray"))
                    }
                    R.id.action_report_error -> {
                        findNavController().navigate(MainFragmentDirections.showEmailFragment("error"))
                    }
                }
                setMenuItemsVisibility(menu)
                true
            }
        }
    }

    private fun setMenuItemsVisibility(menu: Menu) {
        val isUserLogged = FirebaseAuth.getInstance().currentUser != null
        menu.findItem(R.id.action_sign_in).isVisible = !isUserLogged
        menu.findItem(R.id.action_sign_out).isVisible = isUserLogged
    }

    private fun setOnClickListeners() {
        with(binding.contentMain) {
            songBook.setOnClickListener {
                findNavController().navigate(MainFragmentDirections.showPdfFragment("songbook"))
            }
            breviary.setOnClickListener {
                findNavController().navigate(MainFragmentDirections.showBreviarySelectFragment())
            }
            gospel.setOnClickListener {
                findNavController().navigate(MainFragmentDirections.showGospelFragment())
            }
            statute.setOnClickListener {
                findNavController().navigate(MainFragmentDirections.showPdfFragment("statute"))
            }
            members.setOnClickListener {
                findNavController().navigate(MainFragmentDirections.showMembersFragment())
            }
            meetings.setOnClickListener {
                findNavController().navigate(MainFragmentDirections.showMeetingsFragment())
            }
            retreat.setOnClickListener {
                findNavController().navigate(MainFragmentDirections.showRetreatsFragment())
            }
            prayerBook.setOnClickListener {
                findNavController().navigate(MainFragmentDirections.showPrayersSelectFragment())
            }
            website.setOnClickListener {
                if (requireContext().isChromeCustomTabsSupported()) {
                    requireContext().openWebsiteInChromeCustomTabs("http://mftau.pl/")
                } else {
                    findNavController().navigate(MainFragmentDirections.showWebsiteFragment("http://mftau.pl/"))
                }
            }
        }
    }

    private fun showUIChanges(userType: Int) {
        try {
            with(binding.contentMain) {
                when (userType) {
                    MainViewModel.USER_TYPE_ADMIN, MainViewModel.USER_TYPE_MEMBER -> {
                        hideViews(members, meetings)
                        showViews(retreat)
                    }
                    MainViewModel.USER_TYPE_LEADER -> {
                        showViews(members, meetings, retreat)
                    }
                    MainViewModel.USER_TYPE_NONE -> {
                        hideViews(members, meetings, retreat)
                    }
                }
            }
        } catch (exc: NullPointerException) {
            exc.printStackTrace()
        }
    }

    private fun showViews(vararg viewsToShow: View?) {
        viewsToShow.forEach {
            if (it?.alpha == 0f) it.animate().alpha(1f).duration = 333
            it?.isClickable = true
        }
    }

    private fun hideViews(vararg viewsToHide: View?) {
        viewsToHide.forEach {
            it?.isClickable = false
            if (it?.alpha == 1f) it.animate().alpha(0f).duration = 333
        }
    }
}