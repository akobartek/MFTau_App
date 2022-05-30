package pl.mftau.mftau.view.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import pl.mftau.mftau.databinding.ItemSongPlaylistBinding
import pl.mftau.mftau.model.local_db.Song
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
                mPlaylist[position].chords = getNewChords(mPlaylist[position].chords, -1)
                songChords.text = mPlaylist[position].chords
            }

            transpositionUp.setOnClickListener {
                mPlaylist[position].chords = getNewChords(mPlaylist[position].chords, 1)
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

    /*
    mollChords = c, cis, d, dis, e, f, fis, g, gis, a, b, h
    durChords  = C, Cis, D, Dis, E, F, Fis, G, Gis, A, B, H
     */
    private fun getNewChords(chords: String, valueChange: Int): String =
        chords.split("\n").joinToString("\n") { chordsLine ->
            chordsLine.split(" ").joinToString(" ") { chord ->
                if (chord.contains("cis"))
                    chord.replace("cis", if (valueChange == 1) "d" else "c")
                else if (chord.contains("dis"))
                    chord.replace("dis", if (valueChange == 1) "e" else "d")
                else if (chord.contains("fis"))
                    chord.replace("fis", if (valueChange == 1) "g" else "f")
                else if (chord.contains("gis"))
                    chord.replace("gis", if (valueChange == 1) "a" else "g")
                else if (chord.contains("c"))
                    chord.replace("c", if (valueChange == 1) "cis" else "h")
                else if (chord.contains("d"))
                    chord.replace("d", if (valueChange == 1) "dis" else "cis")
                else if (chord.contains("e"))
                    chord.replace("e", if (valueChange == 1) "f" else "dis")
                else if (chord.contains("f"))
                    chord.replace("f", if (valueChange == 1) "fis" else "e")
                else if (chord.contains("g"))
                    chord.replace("g", if (valueChange == 1) "gis" else "fis")
                else if (chord.contains("a"))
                    chord.replace("a", if (valueChange == 1) "b" else "gis")
                else if (chord.contains("b"))
                    chord.replace("b", if (valueChange == 1) "h" else "a")
                else if (chord.contains("h"))
                    chord.replace("h", if (valueChange == 1) "c" else "b")
                else if (chord.contains("Cis"))
                    chord.replace("Cis", if (valueChange == 1) "D" else "C")
                else if (chord.contains("Dis"))
                    chord.replace("Dis", if (valueChange == 1) "E" else "D")
                else if (chord.contains("Fis"))
                    chord.replace("Fis", if (valueChange == 1) "G" else "F")
                else if (chord.contains("Gis"))
                    chord.replace("Gis", if (valueChange == 1) "A" else "G")
                else if (chord.contains("C"))
                    chord.replace("C", if (valueChange == 1) "Cis" else "H")
                else if (chord.contains("D"))
                    chord.replace("D", if (valueChange == 1) "Dis" else "Cis")
                else if (chord.contains("E"))
                    chord.replace("E", if (valueChange == 1) "F" else "Dis")
                else if (chord.contains("F"))
                    chord.replace("F", if (valueChange == 1) "Fis" else "E")
                else if (chord.contains("G"))
                    chord.replace("G", if (valueChange == 1) "Gis" else "Fis")
                else if (chord.contains("A"))
                    chord.replace("A", if (valueChange == 1) "B" else "Gis")
                else if (chord.contains("B"))
                    chord.replace("B", if (valueChange == 1) "H" else "A")
                else if (chord.contains("H"))
                    chord.replace("H", if (valueChange == 1) "C" else "B")
                else ""
            }
        }
}