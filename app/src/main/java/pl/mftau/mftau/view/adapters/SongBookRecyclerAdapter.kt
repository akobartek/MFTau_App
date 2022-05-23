package pl.mftau.mftau.view.adapters

import android.annotation.SuppressLint
import android.graphics.drawable.AnimatedVectorDrawable
import android.util.TypedValue
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import pl.mftau.mftau.R
import pl.mftau.mftau.databinding.ItemSongBinding
import pl.mftau.mftau.utils.PreferencesManager
import pl.mftau.mftau.utils.SongBookUtils
import pl.mftau.mftau.utils.collapse
import pl.mftau.mftau.utils.expand

class SongBookRecyclerAdapter(val scrollFun: (Int) -> Unit) :
    RecyclerView.Adapter<SongBookRecyclerAdapter.SongViewHolder>(), Filterable {

    inner class SongViewHolder(val binding: ItemSongBinding) : RecyclerView.ViewHolder(binding.root)

    private var mResults = listOf<Triple<String, String, String>>()
    private var mCurrentFilter = SongBookUtils.Topics.ALL
    private var mShowCords = PreferencesManager.getSongBookShowCords()
    private var mTextSize = 18f

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = SongViewHolder(
        ItemSongBinding.inflate(LayoutInflater.from(parent.context), parent, false)
    )

    override fun onBindViewHolder(holder: SongViewHolder, position: Int) {
        with(holder.binding) {
            songCardToExpand.visibility = View.GONE
            songCardToExpand.collapse()
            // TODO() -> CHECK IF IT IS ON PLAYLIST AND SET CORRECT ICON
            addToPlaylistBtn.setImageDrawable(
                ContextCompat.getDrawable(
                    root.context, R.drawable.anim_playlist_add_to_remove
                )
            )
            addToFavouritesBtn.setImageDrawable(
                ContextCompat.getDrawable(
                    root.context, R.drawable.anim_favorites_add
                )
            )

            divider.visibility = if (mShowCords) View.VISIBLE else View.INVISIBLE
            songChords.visibility = if (mShowCords) View.VISIBLE else View.GONE

            songTitle.text = mResults[position].first
            songText.text = mResults[position].second.dropLast(2)
            songText.setTextSize(TypedValue.COMPLEX_UNIT_SP, mTextSize)
            songChords.text = mResults[position].third
            songChords.setTextSize(TypedValue.COMPLEX_UNIT_SP, mTextSize)

            songHeader.setOnClickListener {
                if (songCardToExpand.visibility == View.GONE)
                    songCardToExpand.expand {
                        scrollFun(songHeader.height * if (position >= itemCount - 3) 3 else 1)
                    }
                else songCardToExpand.collapse()
            }

            addToPlaylistBtn.setOnClickListener {
                // TODO() -> CHECK IF IT IS ON PLAYLIST AND SET CORRECT ICON
//                addToPlaylistBtn.setImageDrawable(
//                    ContextCompat.getDrawable(
//                        root.context, R.drawable.anim_playlist_remove_to_add
//                    )
//                )
                (addToPlaylistBtn.drawable as AnimatedVectorDrawable).start()
            }

            addToFavouritesBtn.setOnClickListener {
                // TODO() -> CHECK IF IT IS FAVOURITE AND SET CORRECT ICON
//                addToPlaylistBtn.setImageDrawable(
//                    ContextCompat.getDrawable(
//                        root.context, R.drawable.anim_playlist_remove_to_add
//                    )
//                )
                (addToFavouritesBtn.drawable as AnimatedVectorDrawable).start()
            }
        }
    }

    override fun getItemCount(): Int = mResults.size

    override fun getFilter(): Filter = object : Filter() {
        override fun performFiltering(p0: CharSequence?): FilterResults {
            // TODO() FILTERING DATABASE SONGS
            // TODO() FAVOURITES
            mResults =
                if (mCurrentFilter == SongBookUtils.Topics.FAVOURITES) {
                    listOf()
                } else SongBookUtils.topics[mCurrentFilter]?.map {
                    Triple(
                        SongBookUtils.songTitles[it - 1],
                        SongBookUtils.songs[it - 1],
                        SongBookUtils.chords[it - 1]
                    )
                } ?: listOf()
            return FilterResults().apply { values = mResults }
        }

        @SuppressLint("NotifyDataSetChanged")
        override fun publishResults(p0: CharSequence?, p1: FilterResults?) {
            notifyDataSetChanged()
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    fun updateShowChords(newValue: Boolean) {
        mShowCords = newValue
        notifyDataSetChanged()
    }

    fun getCurrentTextSize(): Float = mTextSize

    @SuppressLint("NotifyDataSetChanged")
    fun updateTextSize(newValue: Float) {
        mTextSize = newValue
        notifyDataSetChanged()
    }

    fun updateFilter(filterPosition: Int) {
        mCurrentFilter = SongBookUtils.Topics.values().first { it.value == filterPosition }
        filter.filter("")
    }

    fun getCurrentFilterPosition() = mCurrentFilter
}