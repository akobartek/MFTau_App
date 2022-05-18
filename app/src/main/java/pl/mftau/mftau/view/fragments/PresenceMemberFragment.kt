package pl.mftau.mftau.view.fragments

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import com.github.mikephil.charting.animation.Easing
import com.github.mikephil.charting.components.Legend
import com.github.mikephil.charting.data.PieData
import com.github.mikephil.charting.data.PieDataSet
import com.github.mikephil.charting.data.PieEntry
import com.github.mikephil.charting.formatter.PercentFormatter
import pl.mftau.mftau.R
import pl.mftau.mftau.databinding.FragmentPresenceMemberBinding
import pl.mftau.mftau.model.local_db.Member
import pl.mftau.mftau.utils.PreferencesManager
import pl.mftau.mftau.utils.getColorsByName
import pl.mftau.mftau.view.adapters.PresenceMeetingRecyclerAdapter
import pl.mftau.mftau.viewmodel.MeetingsViewModel

class PresenceMemberFragment : BindingFragment<FragmentPresenceMemberBinding>() {

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

    private lateinit var mViewModel: MeetingsViewModel
    private lateinit var mAdapter: PresenceMeetingRecyclerAdapter
    private lateinit var mMember: Member
    private var mPresenceList = arrayListOf<String>()
    private var mAbsenceList = HashMap<String, String>()
    private var mNumberOfMeetings = 0
    private var mMeetingType = 0

    override fun attachBinding(inflater: LayoutInflater, container: ViewGroup?) =
        FragmentPresenceMemberBinding.inflate(inflater, container, false)

    override fun setup(savedInstanceState: Bundle?) {
        activity?.let { mViewModel = ViewModelProvider(it)[MeetingsViewModel::class.java] }
        arguments?.let {
            mMember = it.getParcelable("member")!!
            mMeetingType = it.getInt("meetingType")
        }
        mAdapter = PresenceMeetingRecyclerAdapter()

        val isNightMode = PreferencesManager.getNightMode()

        with(binding) {
            meetingsRecyclerView.layoutManager = LinearLayoutManager(requireContext())
            meetingsRecyclerView.itemAnimator = DefaultItemAnimator()
            meetingsRecyclerView.adapter = mAdapter

            presencePieChart.setUsePercentValues(true)
            presencePieChart.description.isEnabled = false
            presencePieChart.setExtraOffsets(20f, 0f, 20f, 0f)
            presencePieChart.isDrawHoleEnabled = false
            presencePieChart.isRotationEnabled = true
            presencePieChart.isHighlightPerTapEnabled = true
            presencePieChart.rotationAngle = 0f
            presencePieChart.setEntryLabelColor(if (isNightMode) Color.WHITE else Color.BLACK)
            presencePieChart.setEntryLabelTextSize(12f)

            val legend = presencePieChart.legend
            legend.verticalAlignment = Legend.LegendVerticalAlignment.TOP
            legend.horizontalAlignment = Legend.LegendHorizontalAlignment.CENTER
            legend.orientation = Legend.LegendOrientation.HORIZONTAL
            legend.textColor = if (isNightMode) Color.WHITE else Color.BLACK
            legend.setDrawInside(false)
            legend.xEntrySpace = 7f
            legend.yEntrySpace = 0f
            legend.yOffset = 0f

            presencePieChart.animateY(1000, Easing.EaseInOutQuad)
        }

        mViewModel.getAllMeetings(mMeetingType).observe(viewLifecycleOwner) { meetings ->
            binding.loadingIndicator.hide()

            mNumberOfMeetings = meetings.size
            meetings.forEach { meeting ->
                if (meeting.attendanceList.contains(mMember.id))
                    mPresenceList.add(meeting.id)
                else if (meeting.absenceList.containsKey(mMember.id))
                    mAbsenceList[meeting.id] = meeting.absenceList[mMember.id]!!
            }

            setDataToChart(isNightMode)
            mAdapter.setLists(meetings, mPresenceList, mAbsenceList)
            if (meetings.isEmpty()) {
                binding.emptyView.visibility = View.VISIBLE
                binding.presencePieChart.visibility = View.INVISIBLE
            } else {
                binding.emptyView.visibility = View.INVISIBLE
                binding.presencePieChart.visibility = View.VISIBLE
            }
        }
    }

    private fun setDataToChart(isNightMode: Boolean) {
        val values = ArrayList<PieEntry>()
        if (mPresenceList.size > 0)
            values.add(
                PieEntry(
                    mPresenceList.size.toFloat() / mNumberOfMeetings,
                    getString(R.string.present)
                )
            )
        if (mAbsenceList.size > 0)
            values.add(
                PieEntry(
                    mAbsenceList.size.toFloat() / mNumberOfMeetings,
                    getString(R.string.justified)
                )
            )
        if (mNumberOfMeetings > mPresenceList.size + mAbsenceList.size)
            values.add(
                PieEntry(
                    (mNumberOfMeetings - mPresenceList.size - mAbsenceList.size).toFloat() / mNumberOfMeetings,
                    getString(R.string.absent)
                )
            )

        val dataSet = PieDataSet(values, "").apply {
            sliceSpace = 3f
            selectionShift = 5f
            colors = requireContext().getColorsByName(
                *Array(3) { i -> "meetingType${mMeetingType + 1}_color${i + 1}" }
            )
            valueLinePart1OffsetPercentage = 80f
            valueLinePart1Length = 0.4f
            valueLinePart2Length = 1f
            valueLineColor = if (isNightMode) Color.WHITE else Color.BLACK
//            setUsingSliceColorAsValueLineColor(true)
            yValuePosition = PieDataSet.ValuePosition.OUTSIDE_SLICE
        }

        val pieData = PieData(dataSet).apply {
            setValueFormatter(PercentFormatter())
            setValueTextSize(11f)
            setValueTextColor(if (isNightMode) Color.WHITE else Color.BLACK)
        }

        binding.presencePieChart.data = pieData
        binding.presencePieChart.invalidate()
    }
}
