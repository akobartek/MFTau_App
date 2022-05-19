package pl.mftau.mftau.view.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.google.firebase.auth.FirebaseAuth
import pl.mftau.mftau.R
import pl.mftau.mftau.databinding.FragmentMainBinding
import pl.mftau.mftau.utils.PreferencesManager
import pl.mftau.mftau.utils.isChromeCustomTabsSupported
import pl.mftau.mftau.utils.openWebsiteInChromeCustomTabs
import pl.mftau.mftau.view.activities.MainActivity
import java.lang.NullPointerException

class MainFragment : BindingFragment<FragmentMainBinding>() {

    private val mAuth: FirebaseAuth by lazy { FirebaseAuth.getInstance() }

    override fun attachBinding(inflater: LayoutInflater, container: ViewGroup?) =
        FragmentMainBinding.inflate(inflater, container, false)

    override fun setup(savedInstanceState: Bundle?) {
        inflateToolbarMenu()
        setOnClickListeners()
    }

    override fun onResume() {
        super.onResume()
        MainActivity.currentUserType.observe(viewLifecycleOwner) { showUIChanges(it) }
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
                        MainActivity.currentUserType.postValue(MainActivity.USER_TYPE_NONE)
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
                findNavController().navigate(
                    if (PreferencesManager.getOpenSongBookAsPdf())
                        MainFragmentDirections.showPdfFragment("song_book")
                    else
                        MainFragmentDirections.showSongBookFragment()
                )
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
                    MainActivity.USER_TYPE_ADMIN, MainActivity.USER_TYPE_MEMBER -> {
                        hideViews(members, meetings)
                        showViews(retreat)
                    }
                    MainActivity.USER_TYPE_LEADER -> {
                        showViews(members, meetings, retreat)
                    }
                    MainActivity.USER_TYPE_NONE -> {
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