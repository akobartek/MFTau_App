package pl.mftau.mftau.view.adapters

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
import kotlinx.android.synthetic.main.item_song_search.view.*
import pl.mftau.mftau.R
import pl.mftau.mftau.utils.SongbookUtils

class SongbookSearchAdapter(
    val emptyView: TextView, val showBottomSheet: (Int) -> Unit
) : RecyclerView.Adapter<SongbookSearchAdapter.SongViewHolder>(), Filterable {

    private var mResults = listOf<Pair<String, String>>()
    private var mQuery = ""

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = SongViewHolder(
        LayoutInflater.from(parent.context).inflate(R.layout.item_song_search, parent, false)
    )

    override fun onBindViewHolder(holder: SongViewHolder, position: Int) =
        holder.bindView(mResults.getOrNull(position))

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
                        for (index in SongbookUtils.songTitles.indices) {
                            val title = SongbookUtils.songTitles[index]
                            val text = SongbookUtils.songs[index]
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

    inner class SongViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bindView(song: Pair<String, String>?) {
            if (song == null) return

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

            itemView.songItemTitle.text = songTitle
            itemView.songItemText.text = textLines
            itemView.songItemText.visibility = if (textLines.isEmpty()) View.GONE else View.VISIBLE

            itemView.setOnClickListener { showBottomSheet(SongbookUtils.songTitles.indexOf(song.first)) }
        }
    }
}