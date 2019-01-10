package pl.mftau.mftau.view.adapters

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.github.mikephil.charting.components.Legend
import com.github.mikephil.charting.components.LegendEntry
import com.github.mikephil.charting.components.XAxis
import kotlinx.android.synthetic.main.item_presence_show.view.*
import pl.mftau.mftau.R
import pl.mftau.mftau.model.Member
import com.github.mikephil.charting.data.BarData
import com.github.mikephil.charting.data.BarDataSet
import com.github.mikephil.charting.data.BarEntry
import com.github.mikephil.charting.formatter.PercentFormatter
import com.github.mikephil.charting.interfaces.datasets.IBarDataSet
import pl.mftau.mftau.view.fragments.PresenceListFragmentDirections


class PresenceListRecyclerAdapter : RecyclerView.Adapter<PresenceListRecyclerAdapter.ChartViewHolder>() {

    private var memberList = listOf<Member>()
    private var presence = HashMap<String, Array<Int>>()
    private var numberOfMeetings = arrayOfNulls<Int>(3)
    private var isNightMode = false

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChartViewHolder {
        val viewHolder = ChartViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_presence_show, parent, false))
        viewHolder.reloadChart()
        return viewHolder
    }

    override fun onBindViewHolder(holder: ChartViewHolder, position: Int) =
            holder.bindView(memberList[position])

    override fun getItemCount(): Int = memberList.size

    fun setLists(memberList: List<Member>, presence: HashMap<String, Array<Int>>, numberOfMeetings: Array<Int?>, isNightMode: Boolean) {
        this.memberList = memberList
        this.presence = presence
        this.numberOfMeetings = numberOfMeetings
        this.isNightMode = isNightMode
        notifyDataSetChanged()
    }


    inner class ChartViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val meetingTypeStrings = arrayOf(
                itemView.context.getString(R.string.formation),
                itemView.context.getString(R.string.prayerful),
                itemView.context.getString(R.string.other)
        )

        private val colors = arrayOf(ContextCompat.getColor(itemView.context, R.color.meetingType1_color1),
                ContextCompat.getColor(itemView.context, R.color.meetingType2_color1),
                ContextCompat.getColor(itemView.context, R.color.meetingType3_color1)
        )

        fun bindView(member: Member) {
            itemView.tag = member.id
            itemView.presenceMemberName.text = member.name

            Member.loadImage(itemView.presenceMemberPhoto, member)
            loadChart(member.id)

            itemView.setOnClickListener {
                it.findNavController().navigate(PresenceListFragmentDirections.showPresenceDetailsFragment(member))
            }
        }

        fun reloadChart() {
            itemView.presenceMemberChart.notifyDataSetChanged()
            itemView.presenceMemberChart.invalidate()
        }

        private fun loadChart(id: String) {
            itemView.presenceMemberChart.setDrawValueAboveBar(true)
            itemView.presenceMemberChart.description.isEnabled = false
            itemView.presenceMemberChart.setPinchZoom(false)
            itemView.presenceMemberChart.setDrawGridBackground(false)
            itemView.presenceMemberChart.offsetLeftAndRight(0)

            val xAxis = itemView.presenceMemberChart.xAxis
            xAxis.position = XAxis.XAxisPosition.BOTTOM
            xAxis.setDrawGridLines(false)
            xAxis.setDrawLabels(false)
            xAxis.textColor = if (isNightMode) Color.WHITE else Color.BLACK

            val yAxisLeft = itemView.presenceMemberChart.axisLeft
            yAxisLeft.axisMinimum = 0f
            yAxisLeft.axisMaximum = 100f
            yAxisLeft.spaceTop = 15f
            yAxisLeft.textColor = if (isNightMode) Color.WHITE else Color.BLACK

            val yAxisRight = itemView.presenceMemberChart.axisRight
            yAxisRight.axisMinimum = 0f
            yAxisRight.axisMaximum = 100f
            yAxisRight.spaceTop = 15f
            yAxisRight.textColor = if (isNightMode) Color.WHITE else Color.BLACK

            itemView.presenceMemberChart.animateY(777)

            val legendEntries = ArrayList<LegendEntry>()
            for (i in 0 until colors.size) {
                val legendEntry = LegendEntry()
                legendEntry.label = meetingTypeStrings[i]
                legendEntry.formColor = colors[i]
                legendEntries.add(legendEntry)
            }
            val legend = itemView.presenceMemberChart.legend
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
                values.add(BarEntry(0f, (presence[id]!![0].toFloat()) / numberOfMeetings[0]!! * 100))
            else
                values.add(BarEntry(0f, 0f))

            if (numberOfMeetings[1] ?: 0 > 0)
                values.add(BarEntry(1f, (presence[id]!![1].toFloat()) / numberOfMeetings[1]!! * 100))
            else
                values.add(BarEntry(1f, 0f))

            if (numberOfMeetings[2] ?: 0 > 0)
                values.add(BarEntry(2f, (presence[id]!![2].toFloat()) / numberOfMeetings[2]!! * 100))
            else
                values.add(BarEntry(2f, 0f))

            if (itemView.presenceMemberChart.data != null && itemView.presenceMemberChart.data.dataSetCount > 0) {
                dataSet = itemView.presenceMemberChart.data.getDataSetByIndex(0) as BarDataSet
                dataSet.values = values
                itemView.presenceMemberChart.data.setValueTextColor(if (isNightMode) Color.WHITE else Color.BLACK)
                itemView.presenceMemberChart.data.notifyDataChanged()
                itemView.presenceMemberChart.notifyDataSetChanged()
                itemView.presenceMemberChart.invalidate()
            } else {
                dataSet = BarDataSet(values, itemView.context.getString(R.string.meetings))
                dataSet.setDrawIcons(false)
                dataSet.setColors(colors[0], colors[1], colors[2])
                dataSet.values = values

                val sets = arrayListOf<IBarDataSet>()
                sets.add(dataSet)

                val barData = BarData(sets)
                barData.setValueFormatter(PercentFormatter())
                barData.setValueTextSize(10f)
                barData.barWidth = 0.6f
                itemView.presenceMemberChart.data = barData
                itemView.presenceMemberChart.data.setValueTextColor(if (isNightMode) Color.WHITE else Color.BLACK)
                itemView.presenceMemberChart.setFitBars(true)

                itemView.presenceMemberChart.data.notifyDataChanged()
                itemView.presenceMemberChart.notifyDataSetChanged()
                itemView.presenceMemberChart.invalidate()
            }
        }
    }
}