package pl.mftau.mftau.view.fragments


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import pl.mftau.mftau.R


class MeetingFragment : Fragment() {

    companion object {
//        private val LOG_TAG = TopicFragment::class.java.simpleName

        fun newInstance(meetingType: Int): MeetingFragment {
            return MeetingFragment().apply {
                arguments = Bundle().apply {
                    putInt("meetingType", meetingType)
                }
            }
        }
    }

    private var meetingType: Int = 0

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_meeting, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        meetingType = arguments!!.getInt("meetingType")

        // TODO (UI) -> Load meetings data
    }

}
