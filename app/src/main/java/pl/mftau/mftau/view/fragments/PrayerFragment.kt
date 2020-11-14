package pl.mftau.mftau.view.fragments


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import kotlinx.android.synthetic.main.fragment_prayer.view.*
import pl.mftau.mftau.R
import pl.mftau.mftau.utils.PrayerUtils

class PrayerFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.fragment_prayer, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        view.prayerToolbar.setNavigationOnClickListener { findNavController().navigateUp() }

        val position = PrayerFragmentArgs.fromBundle(requireArguments()).position
        view.prayerToolbarTitle.text = PrayerUtils.prayerNames[position]
        view.prayerText.text = PrayerUtils.prayerBook[position]
    }
}
