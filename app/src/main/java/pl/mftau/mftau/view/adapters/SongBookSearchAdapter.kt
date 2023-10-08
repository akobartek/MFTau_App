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
import pl.mftau.mftau.R
import pl.mftau.mftau.databinding.ItemSongSearchBinding
import pl.mftau.mftau.model.local_db.Song

class SongBookSearchAdapter(val emptyView: TextView, val showBottomSheet: (Song) -> Unit) :
    RecyclerView.Adapter<SongBookSearchAdapter.SongViewHolder>(), Filterable {

    inner class SongViewHolder(val binding: ItemSongSearchBinding) :
        RecyclerView.ViewHolder(binding.root)

    private var mAllSongs = arrayListOf<Song>()
    private var mResults = listOf<Triple<String, String, Int>>()
    private var mQuery = ""

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = SongViewHolder(
        ItemSongSearchBinding.inflate(LayoutInflater.from(parent.context), parent, false)
    )

    override fun onBindViewHolder(holder: SongViewHolder, position: Int) {
        with(holder.binding) {
            val triple = mResults.getOrNull(position) ?: return

            val songTitle = SpannableString(triple.first)
            val searchTitle = triple.first.lowercase()
            if (searchTitle.contains(mQuery))
                songTitle.setSpan(
                    BackgroundColorSpan(Color.RED),
                    searchTitle.indexOf(mQuery),
                    searchTitle.indexOf(mQuery) + mQuery.length,
                    Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
                )

            val textLines = SpannableStringBuilder()
            triple.second.split("\n").forEach {
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

            root.setOnClickListener { showBottomSheet(mAllSongs[triple.third]) }
        }
    }

    override fun getItemCount(): Int = mResults.size

    fun updateList(newList: ArrayList<Song>, query: String) {
        mAllSongs.clear()
        mAllSongs.addAll(newList)
        filter.filter(query)
    }

    override fun getFilter(): Filter {
        return object : Filter() {
            override fun performFiltering(charSequence: CharSequence?): FilterResults {
                mQuery = charSequence.toString()
                val searchQuery = mQuery.lowercase()
                mResults =
                    if (mQuery.isEmpty()) listOf()
                    else {
                        val filteredList = arrayListOf<Triple<String, String, Int>>()
                        mAllSongs.forEachIndexed { index, song ->
                            val title = song.title
                            val text = song.text
                            if (title.lowercase().contains(searchQuery) ||
                                text.lowercase().contains(searchQuery)
                            ) filteredList.add(Triple(title, text, index))
                        }
                        filteredList
                    }
                return FilterResults().apply { values = mResults }
            }

            override fun publishResults(charSequence: CharSequence?, results: FilterResults?) {
                emptyView.apply {
                    visibility = if (mResults.isEmpty()) View.VISIBLE else View.INVISIBLE
                    text =
                        this.context.getString(
                            if (mQuery.isEmpty()) R.string.empty_search_query
                            else R.string.empty_search_list
                        )
                }
                notifyItemRangeRemoved(0, itemCount)
                notifyItemRangeInserted(0, results?.count ?: 0)
            }
        }
    }
}