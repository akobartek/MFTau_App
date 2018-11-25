package pl.mftau.mftau.view.adapters

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_retreat.view.*
import pl.mftau.mftau.R
import pl.mftau.mftau.view.activities.RetreatDetailsActivity
import pl.mftau.mftau.model.Retreat
import pl.mftau.mftau.view.activities.RetreatEditActivity
import pl.mftau.mftau.viewmodel.MainViewModel
import java.text.SimpleDateFormat
import java.util.*

class RetreatRecyclerAdapter(var userType: Int, val activity: Activity)
    : RecyclerView.Adapter<RetreatRecyclerAdapter.RetreatViewHolder>() {

    private var mRetreats = listOf<Retreat>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RetreatViewHolder =
            RetreatViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_retreat, parent, false))

    override fun onBindViewHolder(holder: RetreatViewHolder, position: Int) =
            holder.bindView(mRetreats[position])

    override fun getItemCount(): Int = mRetreats.size

    fun setRetreatList(retreats: List<Retreat>) {
        mRetreats = retreats
        notifyDataSetChanged()
    }

    fun getDateFormatted(date: Date): String =
            SimpleDateFormat("dd.MM.yyyy", Locale.getDefault()).format(date)


    inner class RetreatViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        @SuppressLint("SetTextI18n")
        fun bindView(retreat: Retreat) {
            itemView.retreatName.text = retreat.name
            itemView.retreatCity.text = retreat.city
            itemView.retreatDate.text = getDateFormatted(retreat.beginDate.toDate()) + " - " +
                    getDateFormatted(retreat.endDate.toDate())

            itemView.setOnClickListener {

                val intent =
                        if (userType != MainViewModel.USER_TYPE_ADMIN)
                            Intent(itemView.context, RetreatDetailsActivity::class.java)
                        else
                            Intent(itemView.context, RetreatEditActivity::class.java)
                intent.putExtra("retreat", retreat)
                intent.putExtra("userType", userType)
                activity.startActivityForResult(intent, 2137)
            }
        }
    }
}