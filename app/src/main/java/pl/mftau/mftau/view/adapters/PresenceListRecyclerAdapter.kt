package pl.mftau.mftau.view.adapters

import android.annotation.SuppressLint
import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.github.mikephil.charting.charts.BarChart
import com.github.mikephil.charting.components.Legend
import com.github.mikephil.charting.components.LegendEntry
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.data.BarData
import com.github.mikephil.charting.data.BarDataSet
import com.github.mikephil.charting.data.BarEntry
import com.github.mikephil.charting.formatter.PercentFormatter
import com.github.mikephil.charting.interfaces.datasets.IBarDataSet
import pl.mftau.mftau.R
import pl.mftau.mftau.databinding.ItemPresenceShowBinding
import pl.mftau.mftau.model.Member
import pl.mftau.mftau.utils.PreferencesManager
import pl.mftau.mftau.view.fragments.PresenceListFragmentDirections

class PresenceListRecyclerAdapter :
    RecyclerView.Adapter<PresenceListRecyclerAdapter.ChartViewHolder>() {

    inner class ChartViewHolder(val binding: ItemPresenceShowBinding) :
        RecyclerView.ViewHolder(binding.root) {

        init {
            reloadChart()
        }

        private fun reloadChart() {
            binding.presenceMemberChart.notifyDataSetChanged()
            binding.presenceMemberChart.invalidate()
        }
    }

    private var memberList = listOf<Member>()
    private var presence = HashMap<String, Array<Int>>()
    private var numberOfMeetings = arrayOfNulls<Int>(3)
    private var isNightMode = PreferencesManager.getNightMode()

    private lateinit var meetingTypeStrings: Array<String>
    private lateinit var colors: Array<Int>

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ChartViewHolder(
        ItemPresenceShowBinding.inflate(LayoutInflater.from(parent.context), parent, false)
    )

    override fun onBindViewHolder(holder: ChartViewHolder, position: Int) {
        with(holder.binding) {
            val member = memberList[position]
            root.tag = member.id
            presenceMemberName.text = member.name

            Member.loadImage(presenceMemberPhoto, member)
            loadChart(member.id, presenceMemberChart)

            root.setOnClickListener {
                it.findNavController()
                    .navigate(PresenceListFragmentDirections.showPresenceDetailsFragment(member))
            }
        }
    }

    override fun getItemCount(): Int = memberList.size

    override fun onAttachedToRecyclerView(recyclerView: RecyclerView) {
        super.onAttachedToRecyclerView(recyclerView)

        meetingTypeStrings = arrayOf(
            recyclerView.context.getString(R.string.formation),
            recyclerView.context.getString(R.string.prayerful),
            recyclerView.context.getString(R.string.other)
        )
        colors = arrayOf(
            ContextCompat.getColor(recyclerView.context, R.color.meetingType1_color1),
            ContextCompat.getColor(recyclerView.context, R.color.meetingType2_color1),
            ContextCompat.getColor(recyclerView.context, R.color.meetingType3_color1)
        )
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setLists(
        members: List<Member>, presence: HashMap<String, Array<Int>>, numberOfMeetings: Array<Int?>,
    ) {
        this.memberList = members
        this.presence = presence
        this.numberOfMeetings = numberOfMeetings
        notifyDataSetChanged()
    }

    private fun loadChart(id: String, chart: BarChart) {
        chart.setDrawValueAboveBar(true)
        chart.description.isEnabled = false
        chart.setPinchZoom(false)
        chart.setDrawGridBackground(false)
        chart.offsetLeftAndRight(0)

        val xAxis = chart.xAxis
        xAxis.position = XAxis.XAxisPosition.BOTTOM
        xAxis.setDrawGridLines(false)
        xAxis.setDrawLabels(false)
        xAxis.textColor = if (isNightMode) Color.WHITE else Color.BLACK

        val yAxisLeft = chart.axisLeft
        yAxisLeft.axisMinimum = 0f
        yAxisLeft.axisMaximum = 100f
        yAxisLeft.spaceTop = 15f
        yAxisLeft.textColor = if (isNightMode) Color.WHITE else Color.BLACK

        val yAxisRight = chart.axisRight
        yAxisRight.axisMinimum = 0f
        yAxisRight.axisMaximum = 100f
        yAxisRight.spaceTop = 15f
        yAxisRight.textColor = if (isNightMode) Color.WHITE else Color.BLACK

        chart.animateY(777)

        val legendEntries = ArrayList<LegendEntry>()
        for (i in colors.indices) {
            val legendEntry = LegendEntry()
            legendEntry.label = meetingTypeStrings[i]
            legendEntry.formColor = colors[i]
            legendEntries.add(legendEntry)
        }
        val legend = chart.legend
        legend.verticalAlignment = Legend.LegendVerticalAlignment.BOTTOM
        legend.horizontalAlignment = Legend.LegendHorizontalAlignment.CENTER
        legend.orientation = Legend.LegendOrientation.HORIZONTAL
        legend.textColor = if (isNightMode) Color.WHITE else Color.BLACK
        legend.setDrawInside(false)
        legend.formSize = 8f
        legend.setCustom(legendEntries)

        val values = arrayListOf<BarEntry>()
        val dataSet: BarDataSet

        if (numberOfMeetings[0] ?: 0 > 0)
            values.add(
                BarEntry(0f, (presence[id]!![0].toFloat()) / numberOfMeetings[0]!! * 100)
            )
        else
            values.add(BarEntry(0f, 0f))

        if (numberOfMeetings[1] ?: 0 > 0)
            values.add(
                BarEntry(1f, (presence[id]!![1].toFloat()) / numberOfMeetings[1]!! * 100)
            )
        else
            values.add(BarEntry(1f, 0f))

        if (numberOfMeetings[2] ?: 0 > 0)
            values.add(
                BarEntry(2f, (presence[id]!![2].toFloat()) / numberOfMeetings[2]!! * 100)
            )
        else
            values.add(BarEntry(2f, 0f))

        if (chart.data != null && chart.data.dataSetCount > 0) {
            dataSet = chart.data.getDataSetByIndex(0) as BarDataSet
            dataSet.values = values
            chart.data.setValueTextColor(if (isNightMode) Color.WHITE else Color.BLACK)
            chart.data.notifyDataChanged()
            chart.notifyDataSetChanged()
            chart.invalidate()
        } else {
            dataSet = BarDataSet(values, chart.context.getString(R.string.meetings))
            dataSet.setDrawIcons(false)
            dataSet.setColors(colors[0], colors[1], colors[2])
            dataSet.values = values

            val sets = arrayListOf<IBarDataSet>()
            sets.add(dataSet)

            val barData = BarData(sets)
            barData.setValueFormatter(PercentFormatter())
            barData.setValueTextSize(10f)
            barData.barWidth = 0.6f
            chart.data = barData
            chart.data.setValueTextColor(if (isNightMode) Color.WHITE else Color.BLACK)
            chart.setFitBars(true)

            chart.data.notifyDataChanged()
            chart.notifyDataSetChanged()
            chart.invalidate()
        }
    }
}