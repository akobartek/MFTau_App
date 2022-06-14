package pl.mftau.mftau.view.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import pl.mftau.mftau.databinding.ItemSongPlaylistBinding
import pl.mftau.mftau.model.local_db.Song
import pl.mftau.mftau.utils.SongBookUtils.getTransposedChords
import pl.mftau.mftau.utils.collapse
import pl.mftau.mftau.utils.expand
import pl.mftau.mftau.viewmodel.PlaylistViewModel
import java.util.*

class SongBookPlaylistRecyclerAdapter(
    val viewModel: PlaylistViewModel,
    private val isPlaylistImported: Boolean,
    val startDrag: (viewHolder: RecyclerView.ViewHolder) -> Unit = {}
) : RecyclerView.Adapter<SongBookPlaylistRecyclerAdapter.SongViewHolder>() {

    inner class SongViewHolder(val binding: ItemSongPlaylistBinding) :
        RecyclerView.ViewHolder(binding.root)

    private var mPlaylist = listOf<Song>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = SongViewHolder(
        ItemSongPlaylistBinding.inflate(LayoutInflater.from(parent.context), parent, false)
    )

    @SuppressLint("ClickableViewAccessibility")
    override fun onBindViewHolder(holder: SongViewHolder, position: Int) {
        val song = mPlaylist[position]
        with(holder.binding) {
            songCardToExpand.visibility = View.GONE
            songCardToExpand.collapse()
            transpositionLayout.visibility = View.GONE
            transpositionLayout.collapse()

            songTitle.text = song.title
            songText.text = if (song.isOriginallyInSongBook) song.text.dropLast(2) else song.text
            songChords.text = song.chords

            songHeader.setOnClickListener {
                if (songCardToExpand.visibility == View.GONE) songCardToExpand.expand()
                else songCardToExpand.collapse()
            }

            removeFromPlaylistBtn.setOnClickListener {
                viewModel.removeFromPlaylist(song)
                notifyItemRemoved(position)
            }

            dragAndDropHandle.setOnTouchListener { _, motionEvent ->
                if (motionEvent.action == MotionEvent.ACTION_DOWN)
                    startDrag(holder)
                false
            }

            if (isPlaylistImported) {
                dragAndDropHandle.visibility = View.GONE
                removeFromPlaylistBtn.visibility = View.GONE
            }

            songChords.setOnLongClickListener {
                if (transpositionLayout.visibility == View.GONE) transpositionLayout.expand()
                else transpositionLayout.collapse()
                true
            }

            transpositionDown.setOnClickListener {
                mPlaylist[position].chords = mPlaylist[position].chords.getTransposedChords(-1)
                songChords.text = mPlaylist[position].chords
            }

            transpositionUp.setOnClickListener {
                mPlaylist[position].chords = mPlaylist[position].chords.getTransposedChords(1)
                songChords.text = mPlaylist[position].chords
            }
        }
    }

    override fun getItemCount(): Int = mPlaylist.size

    fun getCurrentPlaylist() = mPlaylist

    @SuppressLint("NotifyDataSetChanged")
    fun setPlayList(playlist: List<Song>) {
        mPlaylist = playlist
        notifyDataSetChanged()
    }

    fun moveItem(fromPosition: Int, toPosition: Int) {
        if (fromPosition < toPosition)
            for (i in fromPosition until toPosition)
                Collections.swap(mPlaylist, i, i + 1)
        else
            for (i in fromPosition downTo toPosition + 1)
                Collections.swap(mPlaylist, i, i - 1)

        notifyItemMoved(fromPosition, toPosition)
    }
}