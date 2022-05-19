package pl.mftau.mftau.view.adapters

import android.annotation.SuppressLint
import android.graphics.drawable.AnimatedVectorDrawable
import android.util.TypedValue
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import pl.mftau.mftau.R
import pl.mftau.mftau.databinding.ItemSongBinding
import pl.mftau.mftau.utils.SongBookUtils
import pl.mftau.mftau.utils.collapse
import pl.mftau.mftau.utils.expand

class SongBookRecyclerAdapter : RecyclerView.Adapter<SongBookRecyclerAdapter.SongViewHolder>() {

    inner class SongViewHolder(val binding: ItemSongBinding) : RecyclerView.ViewHolder(binding.root)

    // TODO() -> LIST OF MY SONGS AND FAVOURITES SONGS
    private var mShowCords = false
    private var mTextSize = 18f

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = SongViewHolder(
        ItemSongBinding.inflate(LayoutInflater.from(parent.context), parent, false)
    )

    override fun onBindViewHolder(holder: SongViewHolder, position: Int) {
        with(holder.binding) {
            songCardToExpand.visibility = View.GONE
            // TODO() -> CHECK IF IT IS ON PLAYLIST AND SET CORRECT ICON
            addToPlaylistBtn.setImageDrawable(
                ContextCompat.getDrawable(
                    root.context, R.drawable.anim_playlist_add_to_remove
                )
            )

            divider.visibility = if (mShowCords) View.VISIBLE else View.INVISIBLE
            songChords.visibility = if (mShowCords) View.VISIBLE else View.GONE

            songTitle.text = SongBookUtils.songTitles[position]
            songText.text = SongBookUtils.songs[position].dropLast(2)
            songText.setTextSize(TypedValue.COMPLEX_UNIT_SP, mTextSize)
            // TODO() -> ADD CHORDS FROM SONG BOOK
            songChords.text = "X D X D Xd"
            songChords.setTextSize(TypedValue.COMPLEX_UNIT_SP, mTextSize)

            songHeader.setOnClickListener {
                if (songCardToExpand.visibility == View.GONE) songCardToExpand.expand()
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
        }
    }

    override fun getItemCount(): Int = SongBookUtils.songTitles.size

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
}