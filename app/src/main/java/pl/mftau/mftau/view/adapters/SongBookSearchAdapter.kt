package pl.mftau.mftau.view.adapters

import android.annotation.SuppressLint
import android.graphics.Color
import android.text.Spannable
import android.text.SpannableString
import android.text.SpannableStringBuilder
import android.text.style.BackgroundColorSpan
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import pl.mftau.mftau.R
import pl.mftau.mftau.databinding.ItemSongSearchBinding
import pl.mftau.mftau.utils.SongBookUtils

class SongBookSearchAdapter(
    val emptyView: TextView, val showBottomSheet: (Int) -> Unit
) : RecyclerView.Adapter<SongBookSearchAdapter.SongViewHolder>(), Filterable {

    inner class SongViewHolder(val binding: ItemSongSearchBinding) : RecyclerView.ViewHolder(binding.root)

    private var mResults = listOf<Pair<String, String>>()
    private var mQuery = ""

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = SongViewHolder(
        ItemSongSearchBinding.inflate(LayoutInflater.from(parent.context), parent, false)
    )

    override fun onBindViewHolder(holder: SongViewHolder, position: Int) {
        with(holder.binding) {
            val song = mResults.getOrNull(position) ?: return

            val songTitle = SpannableString(song.first)
            val searchTitle = song.first.lowercase()
            if (searchTitle.contains(mQuery))
                songTitle.setSpan(
                    BackgroundColorSpan(Color.RED),
                    searchTitle.indexOf(mQuery),
                    searchTitle.indexOf(mQuery) + mQuery.length,
                    Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
                )

            val textLines = SpannableStringBuilder()
            song.second.split("\n").forEach {
                val line = it.lowercase()
                if (line.contains(mQuery)) {
                    val spannable = SpannableString(it + "\n")
                    spannable.setSpan(
                        BackgroundColorSpan(Color.RED),
                        line.indexOf(mQuery),
                        line.indexOf(mQuery) + mQuery.length,
                        Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
                    )
                    textLines.append(spannable)
                }
            }

            songItemTitle.text = songTitle
            songItemText.text = textLines
            songItemText.visibility = if (textLines.isEmpty()) View.GONE else View.VISIBLE

            root.setOnClickListener { showBottomSheet(SongBookUtils.songTitles.indexOf(song.first)) }
        }
    }

    override fun getItemCount(): Int = mResults.size

    override fun getFilter(): Filter {
        return object : Filter() {
            override fun performFiltering(charSequence: CharSequence?): FilterResults {
                mQuery = charSequence.toString()
                val searchQuery = mQuery.lowercase()
                mResults =
                    if (mQuery.isEmpty()) listOf()
                    else {
                        val filteredList = arrayListOf<Pair<String, String>>()
                        for (index in SongBookUtils.songTitles.indices) {
                            val title = SongBookUtils.songTitles[index]
                            val text = SongBookUtils.songs[index]
                            if (title.lowercase().contains(searchQuery) ||
                                text.lowercase().contains(searchQuery)
                            ) filteredList.add(Pair(title, text))
                        }
                        filteredList
                    }
                val filterResults = FilterResults()
                filterResults.values = mResults
                return filterResults
            }

            @SuppressLint("NotifyDataSetChanged")
            override fun publishResults(charSequence: CharSequence?, results: FilterResults?) {
                @Suppress("UNCHECKED_CAST")
                mResults = results?.values as List<Pair<String, String>>? ?: listOf()
                emptyView.apply {
                    visibility = if (mResults.isEmpty()) View.VISIBLE else View.INVISIBLE
                    text =
                        this.context.getString(
                            if (mQuery.isEmpty()) R.string.empty_search_query
                            else R.string.empty_search_list
                        )
                }
                notifyDataSetChanged()
            }
        }
    }
}