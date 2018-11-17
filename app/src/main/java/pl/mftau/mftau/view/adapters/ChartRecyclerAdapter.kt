package pl.mftau.mftau.view.adapters

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.github.mikephil.charting.components.Legend
import com.github.mikephil.charting.components.LegendEntry
import com.github.mikephil.charting.components.XAxis
import com.google.firebase.storage.FirebaseStorage
import kotlinx.android.synthetic.main.item_presence_show.view.*
import pl.mftau.mftau.R
import pl.mftau.mftau.model.Member
import pl.mftau.mftau.utils.GlideApp
import com.github.mikephil.charting.data.BarData
import com.github.mikephil.charting.data.BarDataSet
import com.github.mikephil.charting.data.BarEntry
import com.github.mikephil.charting.formatter.PercentFormatter


class ChartRecyclerAdapter : RecyclerView.Adapter<ChartRecyclerAdapter.ChartViewHolder>() {

    private var mMemberList = listOf<Member>()
    private var mPresence = HashMap<String, Array<Int>>()
    private var mNumberOfMeetings = arrayOf(0, 0, 0)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChartViewHolder {
        val viewHolder = ChartViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_presence_show, parent, false))
        viewHolder.reloadChart()
        return viewHolder
    }

    override fun onBindViewHolder(holder: ChartViewHolder, position: Int) =
            holder.bindView(mMemberList[position])

    override fun getItemCount(): Int = mMemberList.size

    fun setLists(memberList: List<Member>, presence: HashMap<String, Array<Int>>, numberOfMeetings: Array<Int>) {
        mMemberList = memberList
        mPresence = presence
        mNumberOfMeetings = numberOfMeetings
        notifyDataSetChanged()
    }


    inner class ChartViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val meetingTypeStrings = arrayOf(
                itemView.context.getString(R.string.formation),
                itemView.context.getString(R.string.prayerful),
                itemView.context.getString(R.string.other)
        )

        private val colors = arrayOf(Color.rgb(104, 241, 175),
                Color.rgb(164, 228, 251),
                Color.rgb(242, 247, 158)
        )

        fun bindView(member: Member) {
            loadChart(member.id)
            itemView.tag = member.id
            itemView.presenceMemberName.text = member.name

            val storageReference = FirebaseStorage.getInstance()
                    .reference.child("members/${member.id}.jpg")

            GlideApp.with(itemView.context)
                    .load(storageReference)
                    .circleCrop()
                    .placeholder(R.drawable.ic_user)
                    .into(itemView.presenceMemberPhoto)
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

            val yAxisLeft = itemView.presenceMemberChart.axisLeft
            yAxisLeft.axisMinimum = 0f
            yAxisLeft.axisMaximum = 100f
            yAxisLeft.spaceTop = 15f

            val yAxisRight = itemView.presenceMemberChart.axisRight
            yAxisRight.axisMinimum = 0f
            yAxisRight.axisMaximum = 100f
            yAxisRight.spaceTop = 15f

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
            legend.setDrawInside(false)
            legend.formSize = 8f
            legend.setCustom(legendEntries)

            val values = arrayListOf<BarEntry>()
            val dataSet: BarDataSet

            if (mNumberOfMeetings[0] > 0)
                values.add(BarEntry(0f, (mPresence[id]!![0].toFloat()) / mNumberOfMeetings[0] * 100))
            else
                values.add(BarEntry(0f, 0f))

            if (mNumberOfMeetings[1] > 0)
                values.add(BarEntry(1f, (mPresence[id]!![1].toFloat()) / mNumberOfMeetings[1] * 100))
            else
                values.add(BarEntry(1f, 0f))

            if (mNumberOfMeetings[2] > 0)
                values.add(BarEntry(2f, (mPresence[id]!![2].toFloat()) / mNumberOfMeetings[2] * 100))
            else
                values.add(BarEntry(2f, 0f))

            if (itemView.presenceMemberChart.data != null && itemView.presenceMemberChart.data.dataSetCount > 0) {
                dataSet = itemView.presenceMemberChart.data.getDataSetByIndex(0) as BarDataSet
                dataSet.values = values
                itemView.presenceMemberChart.data.notifyDataChanged()
                itemView.presenceMemberChart.notifyDataSetChanged()
                itemView.presenceMemberChart.invalidate()
            } else {
                dataSet = BarDataSet(values, itemView.context.getString(R.string.meetings))
                dataSet.setDrawIcons(false)

                val barData = BarData(dataSet)
                barData.setValueFormatter(PercentFormatter())
                barData.setValueTextSize(10f)
                barData.barWidth = 0.6f
                itemView.presenceMemberChart.data = barData
                itemView.presenceMemberChart.setFitBars(true)

                dataSet.setColors(Color.rgb(104, 241, 175),
                        Color.rgb(164, 228, 251),
                        Color.rgb(242, 247, 158))
                itemView.presenceMemberChart.notifyDataSetChanged()
                itemView.presenceMemberChart.invalidate()
            }
        }
    }
}