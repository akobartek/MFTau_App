package pl.mftau.mftau.view.fragments


import android.graphics.Color
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.PopupMenu
import androidx.browser.customtabs.CustomTabsIntent
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.fragment_main.view.*
import pl.mftau.mftau.R
import pl.mftau.mftau.utils.FirestoreUtils.firestoreCollectionUsers
import pl.mftau.mftau.utils.FirestoreUtils.firestoreKeyIsAdmin
import pl.mftau.mftau.utils.FirestoreUtils.firestoreKeyIsLeader
import pl.mftau.mftau.utils.FirestoreUtils.firestoreKeyIsMember
import pl.mftau.mftau.utils.PreferencesManager
import pl.mftau.mftau.utils.isChromeCustomTabsSupported
import pl.mftau.mftau.viewmodel.MainViewModel

class MainFragment : Fragment() {

    private lateinit var mViewModel: MainViewModel
    private lateinit var mAuth: FirebaseAuth

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.fragment_main, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        activity?.let { mViewModel = ViewModelProvider(it).get(MainViewModel::class.java) }
        mAuth = FirebaseAuth.getInstance()
        setOnClickListeners()
    }

    override fun onResume() {
        super.onResume()
        if (mAuth.currentUser != null && mAuth.currentUser!!.isEmailVerified) {
            FirebaseFirestore.getInstance().collection(firestoreCollectionUsers)
                .document(FirebaseAuth.getInstance().currentUser!!.uid)
                .get()
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
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
                }
        } else {
            showUIChanges(MainViewModel.USER_TYPE_NONE)
        }
    }

    private fun setOnClickListeners() {
        view?.menuBtn?.setOnClickListener {
            val popupMenu = PopupMenu(context, requireView().menuBtn)

            if (FirebaseAuth.getInstance().currentUser != null)
                popupMenu.menuInflater.inflate(R.menu.menu_main_out, popupMenu.menu)
            else
                popupMenu.menuInflater.inflate(R.menu.menu_main_in, popupMenu.menu)

            popupMenu.setOnMenuItemClickListener { menuItem ->
                when (menuItem.itemId) {
                    R.id.action_sign_in -> {
                        findNavController().navigate(MainFragmentDirections.showLoginFragment())
                        true
                    }
                    R.id.action_sign_out -> {
                        mAuth.signOut()
                        showUIChanges(MainViewModel.USER_TYPE_NONE)
                        true
                    }
                    R.id.action_settings -> {
                        findNavController().navigate(MainFragmentDirections.showPreferenceFragment())
                        true
                    }
                    R.id.action_ask_for_pray -> {
                        findNavController().navigate(MainFragmentDirections.showEmailFragment("pray"))
                        true
                    }
                    R.id.action_report_error -> {
                        findNavController().navigate(MainFragmentDirections.showEmailFragment("error"))
                        true
                    }
                    else -> true
                }
            }
            popupMenu.show()
        }

        view?.songBook?.setOnClickListener {
            findNavController().navigate(MainFragmentDirections.showPdfFragment("songbook"))
        }

        view?.breviary?.setOnClickListener {
            findNavController().navigate(MainFragmentDirections.showBreviaryFragment("breviary"))
        }

        view?.gospel?.setOnClickListener {
            findNavController().navigate(MainFragmentDirections.showGospelFragment())
        }

        view?.statute?.setOnClickListener {
            findNavController().navigate(MainFragmentDirections.showPdfFragment("statute"))
        }

        view?.members?.setOnClickListener {
            findNavController().navigate(MainFragmentDirections.showMembersFragment())
        }

        view?.meetings?.setOnClickListener {
            findNavController().navigate(MainFragmentDirections.showMeetingsFragment())
        }

        view?.retreat?.setOnClickListener {
            findNavController().navigate(MainFragmentDirections.showRetreatsFragment())
        }

        view?.prayerBook?.setOnClickListener {
            findNavController().navigate(MainFragmentDirections.showPrayerFragment("prayer"))
        }

        view?.website?.setOnClickListener {
            if (requireContext().isChromeCustomTabsSupported()) {
                CustomTabsIntent.Builder().apply {
                    val color =
                        if (PreferencesManager.getNightMode()) Color.parseColor("#28292e")
                        else Color.WHITE
                    setToolbarColor(color)
                    setSecondaryToolbarColor(color)
                }.build().launchUrl(requireContext(), Uri.parse("http://mftau.pl/"))
            } else {
                findNavController().navigate(MainFragmentDirections.showWebsiteFragment("http://mftau.pl/"))
            }
        }
    }

    private fun showUIChanges(userType: Int) {
        when (userType) {
            MainViewModel.USER_TYPE_ADMIN, MainViewModel.USER_TYPE_MEMBER -> {
                hideViews(view?.members, view?.meetings)
                showViews(view?.retreat)
            }
            MainViewModel.USER_TYPE_LEADER -> {
                showViews(view?.members, view?.meetings, view?.retreat)
            }
            MainViewModel.USER_TYPE_NONE -> {
                hideViews(view?.members, view?.meetings, view?.retreat)
            }
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