package pl.mftau.mftau.view.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.transition.MaterialContainerTransform
import pl.mftau.mftau.R
import pl.mftau.mftau.databinding.FragmentSongBookImportedPlaylistBinding
import pl.mftau.mftau.model.local_db.Song
import pl.mftau.mftau.view.adapters.SongBookPlaylistRecyclerAdapter
import pl.mftau.mftau.viewmodel.PlaylistViewModel

class SongBookImportedPlaylistFragment :
    BindingFragment<FragmentSongBookImportedPlaylistBinding>() {

    private val mViewModel: PlaylistViewModel by viewModels()
    private lateinit var mAdapter: SongBookPlaylistRecyclerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        sharedElementEnterTransition = MaterialContainerTransform()
    }

    override fun attachBinding(inflater: LayoutInflater, container: ViewGroup?) =
        FragmentSongBookImportedPlaylistBinding.inflate(inflater, container, false)

    override fun setup(savedInstanceState: Bundle?) {
        mAdapter =
            SongBookPlaylistRecyclerAdapter(mViewModel, true)
        binding.contentPlaylist.playlistRecyclerView.apply {
            layoutManager = LinearLayoutManager(requireContext())
            itemAnimator = DefaultItemAnimator()
            adapter = mAdapter
            layoutAnimation =
                AnimationUtils.loadLayoutAnimation(this.context, R.anim.layout_animation_fall_down)
        }

        binding.songBookPlaylistToolbar.setNavigationOnClickListener { findNavController().navigateUp() }

        val playlistCode = SongBookImportedPlaylistFragmentArgs.fromBundle(requireArguments()).code
        if (playlistCode.split(" ")[0] == "sb")
            showPlaylist(mViewModel.getImportedPlaylistFromSongbook(playlistCode.split(" ")[1]))
        else
            mViewModel.getImportedFirestorePlaylist(playlistCode.split(" ")[1])
                .observe(viewLifecycleOwner) {
                    showPlaylist(mViewModel.convertPlaylistToSongList(it))
                }
    }

    private fun showPlaylist(playlist: List<Song>) {
        mAdapter.setPlayList(playlist)
        binding.contentPlaylist.playlistRecyclerView.scheduleLayoutAnimation()

        binding.contentPlaylist.loadingIndicator.hide()
        binding.contentPlaylist.emptyView.visibility =
            if (playlist.isEmpty()) View.VISIBLE else View.INVISIBLE
    }
}