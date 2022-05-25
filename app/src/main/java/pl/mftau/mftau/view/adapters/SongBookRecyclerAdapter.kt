package pl.mftau.mftau.view.adapters

import android.annotation.SuppressLint
import android.graphics.drawable.AnimatedVectorDrawable
import android.util.TypedValue
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import android.widget.ImageButton
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import pl.mftau.mftau.R
import pl.mftau.mftau.databinding.ItemSongBinding
import pl.mftau.mftau.model.local_db.Song
import pl.mftau.mftau.utils.PreferencesManager
import pl.mftau.mftau.utils.SongBookUtils
import pl.mftau.mftau.utils.collapse
import pl.mftau.mftau.utils.expand
import pl.mftau.mftau.viewmodel.SongBookViewModel

class SongBookRecyclerAdapter(val viewModel: SongBookViewModel, val scrollFun: (Int) -> Unit) :
    RecyclerView.Adapter<SongBookRecyclerAdapter.SongViewHolder>(), Filterable {

    inner class SongViewHolder(val binding: ItemSongBinding) : RecyclerView.ViewHolder(binding.root)

    private var mResults = arrayListOf<Song>()
    private var mCurrentFilter = SongBookUtils.Topic.ALL
    private var mShowCords = PreferencesManager.getSongBookShowCords()
    private var mTextSize = 18f

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = SongViewHolder(
        ItemSongBinding.inflate(LayoutInflater.from(parent.context), parent, false)
    )

    override fun onBindViewHolder(holder: SongViewHolder, position: Int) {
        val song = mResults[position]
        with(holder.binding) {
            songCardToExpand.visibility = View.GONE
            songCardToExpand.collapse()

            divider.visibility = if (mShowCords) View.VISIBLE else View.INVISIBLE
            songChords.visibility = if (mShowCords) View.VISIBLE else View.GONE

            songTitle.text = song.title
            songText.text = if (song.isOriginallyInSongBook) song.text.dropLast(2) else song.text
            songText.setTextSize(TypedValue.COMPLEX_UNIT_SP, mTextSize)
            songChords.text = song.chords
            songChords.setTextSize(TypedValue.COMPLEX_UNIT_SP, mTextSize)

            setCorrectPlaylistDrawable(addToPlaylistBtn, song.isOnPlaylist)
            setCorrectFavouriteDrawable(addToFavouritesBtn, song.isFavourite)

            songHeader.setOnClickListener {
                if (songCardToExpand.visibility == View.GONE)
                    songCardToExpand.expand {
                        scrollFun(songHeader.height * if (position >= itemCount - 3) 3 else 1)
                    }
                else songCardToExpand.collapse()
            }

            addToPlaylistBtn.setOnClickListener {
                setCorrectPlaylistDrawable(addToPlaylistBtn, song.isOnPlaylist)
                (addToPlaylistBtn.drawable as AnimatedVectorDrawable).start()
                if (song.isOnPlaylist) viewModel.removeFromPlaylist(song)
                else viewModel.addToPlaylist(song)

                song.isOnPlaylist = !song.isOnPlaylist
                mResults[position] = song
            }

            addToFavouritesBtn.setOnClickListener {
                setCorrectFavouriteDrawable(addToFavouritesBtn, song.isFavourite)
                (addToFavouritesBtn.drawable as AnimatedVectorDrawable).start()
                if (song.isFavourite) viewModel.removeFromFavourites(song)
                else viewModel.addToFavourites(song)

                song.isFavourite = !song.isFavourite
                mResults[position] = song
            }
        }
    }

    override fun getItemCount(): Int = mResults.size

    override fun getFilter(): Filter = object : Filter() {
        override fun performFiltering(p0: CharSequence?): FilterResults {
            mResults = viewModel.getSongsToShow(mCurrentFilter)
            return FilterResults().apply { values = mResults }
        }

        @SuppressLint("NotifyDataSetChanged")
        override fun publishResults(p0: CharSequence?, p1: FilterResults?) {
            notifyDataSetChanged()
        }
    }

    private fun setCorrectPlaylistDrawable(button: ImageButton, isOnPlaylist: Boolean) {
        button.setImageDrawable(
            ContextCompat.getDrawable(
                button.context,
                if (isOnPlaylist) R.drawable.anim_playlist_remove else R.drawable.anim_playlist_add
            )
        )
    }

    private fun setCorrectFavouriteDrawable(button: ImageButton, isFavourite: Boolean) {
        button.setImageDrawable(
            ContextCompat.getDrawable(
                button.context,
                if (isFavourite) R.drawable.anim_favourites_remove else R.drawable.anim_favorites_add
            )
        )
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

    fun updateFilter(filterPosition: Int = -1) {
        if (filterPosition != -1)
            mCurrentFilter = SongBookUtils.Topic.values().first { it.value == filterPosition }
        filter.filter("")
    }

    fun getCurrentFilterPosition() = mCurrentFilter
}