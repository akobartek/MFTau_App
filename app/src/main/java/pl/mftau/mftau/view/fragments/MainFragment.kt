package pl.mftau.mftau.view.fragments


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.PopupMenu
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.fragment_main.view.*
import pl.mftau.mftau.R
import pl.mftau.mftau.utils.FirestoreUtils.firestoreCollectionUsers
import pl.mftau.mftau.utils.FirestoreUtils.firestoreKeyIsAdmin
import pl.mftau.mftau.utils.FirestoreUtils.firestoreKeyIsLeader
import pl.mftau.mftau.utils.FirestoreUtils.firestoreKeyIsMember
import pl.mftau.mftau.viewmodel.MainViewModel

class MainFragment : Fragment() {

    private lateinit var mViewModel: MainViewModel
    private lateinit var mAuth: FirebaseAuth

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_main, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        activity?.let { mViewModel = ViewModelProviders.of(it).get(MainViewModel::class.java) }
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
            val popupMenu = PopupMenu(context, view!!.menuBtn)

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
                        val showPreferenceFragment = MainFragmentDirections.showPreferenceFragment()
                        findNavController().navigate(showPreferenceFragment)
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
            findNavController().navigate(MainFragmentDirections.showPdfFragment("songBook"))
        }

        view?.breviary?.setOnClickListener {
            findNavController().navigate(MainFragmentDirections.showListFragment("breviary"))
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
            findNavController().navigate(MainFragmentDirections.showListFragment("prayer"))
        }

        view?.website?.setOnClickListener {
            findNavController().navigate(MainFragmentDirections.showWebsiteFragment())
        }
    }

    private fun showUIChanges(userType: Int) {
        when (userType) {
            MainViewModel.USER_TYPE_ADMIN -> {
                showAdminUI(true)
            }
            MainViewModel.USER_TYPE_LEADER -> {
                showLeaderUI(true)
            }
            MainViewModel.USER_TYPE_MEMBER -> {
                showNormalUserUI(true)
            }
            MainViewModel.USER_TYPE_NONE -> {
                showAdminUI(false)
                showLeaderUI(false)
                showNormalUserUI(false)
            }
        }
    }

    private fun showAdminUI(isLogged: Boolean) {
        view?.retreat?.isClickable = isLogged

        if (isLogged && view?.retreat?.alpha == 0f) {
            view?.retreat?.animate()
                    ?.alpha(1f)
                    ?.withStartAction {
                        view?.members?.visibility = View.INVISIBLE
                        view?.meetings?.visibility = View.INVISIBLE
                        view?.retreat?.visibility = View.VISIBLE
                    }
                    ?.duration = 333
        } else if (!isLogged && view?.retreat?.alpha == 1f) {
            view?.retreat?.animate()
                    ?.alpha(0f)
                    ?.duration = 333
        }
    }

    private fun showLeaderUI(isLogged: Boolean) {
        view?.members?.isClickable = isLogged
        view?.meetings?.isClickable = isLogged

        if (isLogged && view?.members?.alpha == 0f) {
            view?.members?.animate()
                    ?.alpha(1f)
                    ?.withStartAction {
                        view?.retreat?.visibility = View.INVISIBLE
                        view?.members?.visibility = View.VISIBLE
                        view?.meetings?.visibility = View.VISIBLE
                    }
                    ?.duration = 333
            view?.meetings?.animate()
                    ?.alpha(1f)
                    ?.duration = 333
        } else if (!isLogged && view?.members?.alpha == 1f) {
            view?.members?.animate()
                    ?.alpha(0f)
                    ?.duration = 333
            view?.meetings?.animate()
                    ?.alpha(0f)
                    ?.duration = 333
        }
    }

    private fun showNormalUserUI(isLogged: Boolean) {
        view?.retreat?.isClickable = isLogged

        if (isLogged && view?.retreat?.alpha == 0f) {
            view?.retreat?.animate()
                    ?.alpha(1f)
                    ?.withStartAction {
                        view?.members?.visibility = View.INVISIBLE
                        view?.meetings?.visibility = View.INVISIBLE
                        view?.retreat?.visibility = View.VISIBLE
                    }
                    ?.duration = 333
        } else if (!isLogged && view?.retreat?.alpha == 1f) {
            view?.retreat?.animate()
                    ?.alpha(0f)
                    ?.duration = 333
        }
    }
}
