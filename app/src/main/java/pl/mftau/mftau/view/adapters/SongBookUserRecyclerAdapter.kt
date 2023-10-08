package pl.mftau.mftau.view.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.navigation.fragment.FragmentNavigatorExtras
import androidx.recyclerview.widget.RecyclerView
import pl.mftau.mftau.databinding.ItemSongUserBinding
import pl.mftau.mftau.model.local_db.Song
import pl.mftau.mftau.view.fragments.SongBookFragmentDirections

class SongBookUserRecyclerAdapter :
    RecyclerView.Adapter<SongBookUserRecyclerAdapter.SongViewHolder>() {

    inner class SongViewHolder(val binding: ItemSongUserBinding) :
        RecyclerView.ViewHolder(binding.root)

    private var mUserSongs = arrayListOf<Song>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = SongViewHolder(
        ItemSongUserBinding.inflate(LayoutInflater.from(parent.context), parent, false)
    )

    override fun onBindViewHolder(holder: SongViewHolder, position: Int) {
        with(holder.binding) {
            val song = mUserSongs[position]
            songHeader.transitionName = ""
            songTitle.text = song.title

            songHeader.setOnClickListener {
                songHeader.transitionName = "shared_element_editor"
                it.findNavController().navigate(
                    SongBookFragmentDirections.showSongEditorFragment(song),
                    FragmentNavigatorExtras(songHeader to "shared_element_editor")
                )
            }
        }
    }

    override fun getItemCount(): Int = mUserSongs.size

    fun setSongsList(songs: List<Song>) {
        val currentSize = mUserSongs.size
        mUserSongs.clear()
        mUserSongs.addAll(songs)
        notifyItemRangeRemoved(0, currentSize)
        notifyItemRangeInserted(0, songs.size)
    }
}