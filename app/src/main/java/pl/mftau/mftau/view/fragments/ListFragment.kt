package pl.mftau.mftau.view.fragments


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.navigation.fragment.findNavController
import kotlinx.android.synthetic.main.fragment_list.view.*
import pl.mftau.mftau.R
import pl.mftau.mftau.utils.PrayerUtils

class ListFragment : Fragment() {

    private lateinit var listType: String

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? =
        inflater.inflate(R.layout.fragment_list, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        context?.let {
            if (ListFragmentArgs.fromBundle(arguments!!).listType == "prayer") {
                listType = "prayer"
                view.list.adapter = ArrayAdapter<String>(it, R.layout.item_listview, PrayerUtils.prayerNames)
            } else {
                listType = "breviary"
                view.list.adapter =
                    ArrayAdapter<String>(it, R.layout.item_listview, resources.getStringArray(R.array.breviary_list))
            }
        }

        view.list.setOnItemClickListener { _, _, position, _ ->
            if (listType == "prayer") {
                findNavController().navigate(ListFragmentDirections.showPrayerFragment(position))
            } else {
                findNavController().navigate(ListFragmentDirections.showBreviaryFragment(position))
            }
        }
    }
}
