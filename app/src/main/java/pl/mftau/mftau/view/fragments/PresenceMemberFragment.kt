package pl.mftau.mftau.view.fragments


import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import com.github.mikephil.charting.animation.Easing
import com.github.mikephil.charting.components.Legend
import com.github.mikephil.charting.data.PieData
import com.github.mikephil.charting.data.PieDataSet
import com.github.mikephil.charting.data.PieEntry
import com.github.mikephil.charting.formatter.PercentFormatter
import kotlinx.android.synthetic.main.fragment_presence_member.view.*
import pl.mftau.mftau.R
import pl.mftau.mftau.model.Member
import pl.mftau.mftau.view.adapters.PresenceMeetingRecyclerAdapter
import pl.mftau.mftau.viewmodel.MainViewModel

class PresenceMemberFragment : Fragment() {

    companion object {
        fun newInstance(member: Member, meetingType: Int): PresenceMemberFragment {
            return PresenceMemberFragment().apply {
                arguments = Bundle().apply {
                    putParcelable("member", member)
                    putInt("meetingType", meetingType)
                }
            }
        }
    }

    private lateinit var mViewModel: MainViewModel
    private lateinit var mAdapter: PresenceMeetingRecyclerAdapter
    private lateinit var mMember: Member
    private var mPresenceList = arrayListOf<String>()
    private var mAbsenceList = HashMap<String, String>()
    private var mNumberOfMeetings = 0
    private var mMeetingType = 0

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? =
        inflater.inflate(R.layout.fragment_presence_member, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        activity?.let { mViewModel = ViewModelProvider(it).get(MainViewModel::class.java) }
        arguments?.let {
            mMember = it.getParcelable("member")!!
            mMeetingType = it.getInt("meetingType")
        }
        mAdapter = PresenceMeetingRecyclerAdapter()

        view.meetingsRecyclerView.layoutManager = LinearLayoutManager(view.context)
        view.meetingsRecyclerView.itemAnimator = DefaultItemAnimator()
        view.meetingsRecyclerView.adapter = mAdapter

        view.presencePieChart.setUsePercentValues(true)
        view.presencePieChart.description.isEnabled = false
        view.presencePieChart.setExtraOffsets(20f, 0f, 20f, 0f)
        view.presencePieChart.isDrawHoleEnabled = false
        view.presencePieChart.isRotationEnabled = true
        view.presencePieChart.isHighlightPerTapEnabled = true
        view.presencePieChart.rotationAngle = 0f
        view.presencePieChart.setEntryLabelColor(if (mViewModel.isNightMode) Color.WHITE else Color.BLACK)
        view.presencePieChart.setEntryLabelTextSize(12f)

        val legend = view.presencePieChart.legend
        legend.verticalAlignment = Legend.LegendVerticalAlignment.TOP
        legend.horizontalAlignment = Legend.LegendHorizontalAlignment.CENTER
        legend.orientation = Legend.LegendOrientation.HORIZONTAL
        legend.textColor = if (mViewModel.isNightMode) Color.WHITE else Color.BLACK
        legend.setDrawInside(false)
        legend.xEntrySpace = 7f
        legend.yEntrySpace = 0f
        legend.yOffset = 0f

        mViewModel.getAllMeetings(mMeetingType).observe(viewLifecycleOwner, Observer { meetings ->
            view.loadingIndicator.hide()

            mNumberOfMeetings = meetings.size
            meetings.forEach { meeting ->
                if (meeting.attendanceList.contains(mMember.id))
                    mPresenceList.add(meeting.id)
                else if (meeting.absenceList.containsKey(mMember.id))
                    mAbsenceList[meeting.id] = meeting.absenceList[mMember.id]!!
            }

            setDataToChart()
            mAdapter.setLists(meetings, mPresenceList, mAbsenceList)
            if (meetings.isEmpty()) {
                view.emptyView.visibility = View.VISIBLE
                view.presencePieChart.visibility = View.INVISIBLE
            } else {
                view.emptyView.visibility = View.INVISIBLE
                view.presencePieChart.visibility = View.VISIBLE
            }
        })

        view.presencePieChart.animateY(1000, Easing.EaseInOutQuad)
    }

    private fun setDataToChart() {
        val values = ArrayList<PieEntry>()
        if (mPresenceList.size > 0)
            values.add(PieEntry(mPresenceList.size.toFloat() / mNumberOfMeetings, getString(R.string.present)))
        if (mAbsenceList.size > 0)
            values.add(
                PieEntry(mAbsenceList.size.toFloat() / mNumberOfMeetings, getString(R.string.justified))
            )
        if (mNumberOfMeetings > mPresenceList.size + mAbsenceList.size)
            values.add(
                PieEntry(
                    (mNumberOfMeetings - mPresenceList.size - mAbsenceList.size).toFloat() / mNumberOfMeetings,
                    getString(R.string.absent)
                )
            )

        val dataSet = PieDataSet(values, "")
        dataSet.sliceSpace = 3f
        dataSet.selectionShift = 5f

        when (mMeetingType) {
            0 -> dataSet.setColors(
                ContextCompat.getColor(context!!, R.color.meetingType1_color1),
                ContextCompat.getColor(context!!, R.color.meetingType1_color2),
                ContextCompat.getColor(context!!, R.color.meetingType1_color3)
            )
            1 -> dataSet.setColors(
                ContextCompat.getColor(context!!, R.color.meetingType2_color1),
                ContextCompat.getColor(context!!, R.color.meetingType2_color2),
                ContextCompat.getColor(context!!, R.color.meetingType2_color3)
            )
            2 -> dataSet.setColors(
                ContextCompat.getColor(context!!, R.color.meetingType3_color1),
                ContextCompat.getColor(context!!, R.color.meetingType3_color2),
                ContextCompat.getColor(context!!, R.color.meetingType3_color3)
            )
        }

        dataSet.valueLinePart1OffsetPercentage = 80f
        dataSet.valueLinePart1Length = 0.4f
        dataSet.valueLinePart2Length = 1f
        dataSet.valueLineColor = if (mViewModel.isNightMode) Color.WHITE else Color.BLACK
        //dataSet.setUsingSliceColorAsValueLineColor(true)
        dataSet.yValuePosition = PieDataSet.ValuePosition.OUTSIDE_SLICE

        val pieData = PieData(dataSet)
        pieData.setValueFormatter(PercentFormatter())
        pieData.setValueTextSize(11f)
        pieData.setValueTextColor(if (mViewModel.isNightMode) Color.WHITE else Color.BLACK)

        view?.presencePieChart?.data = pieData
        view?.presencePieChart?.invalidate()
    }
}
