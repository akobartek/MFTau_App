package pl.mftau.mftau.view.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import pl.mftau.mftau.databinding.ItemRetreatBinding
import pl.mftau.mftau.model.Retreat
import pl.mftau.mftau.utils.getDateFormatted
import pl.mftau.mftau.view.fragments.RetreatsFragmentDirections
import pl.mftau.mftau.viewmodel.MainViewModel

class RetreatRecyclerAdapter(private var userType: Int, val fragment: Fragment) :
    RecyclerView.Adapter<RetreatRecyclerAdapter.RetreatViewHolder>() {

    private var mRetreats = listOf<Retreat>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = RetreatViewHolder(
        ItemRetreatBinding.inflate(LayoutInflater.from(parent.context), parent, false)
    )

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: RetreatViewHolder, position: Int) {
        with(holder.binding) {
            val retreat = mRetreats[position]
            retreatName.text = retreat.name
            retreatCity.text = retreat.city
            retreatDate.text = retreat.beginDate.toDate().getDateFormatted() + " - " +
                    retreat.endDate.toDate().getDateFormatted()

            root.setOnClickListener {
                if (userType != MainViewModel.USER_TYPE_ADMIN)
                    fragment.findNavController()
                        .navigate(RetreatsFragmentDirections.showDetailsFragment(retreat))
                else
                    fragment.findNavController()
                        .navigate(RetreatsFragmentDirections.showEditorFragment(retreat))
            }
        }
    }

    override fun getItemCount(): Int = mRetreats.size

    inner class RetreatViewHolder(val binding: ItemRetreatBinding) :
        RecyclerView.ViewHolder(binding.root)

    @SuppressLint("NotifyDataSetChanged")
    fun setRetreatList(retreats: List<Retreat>) {
        mRetreats = retreats
        notifyDataSetChanged()
    }
}