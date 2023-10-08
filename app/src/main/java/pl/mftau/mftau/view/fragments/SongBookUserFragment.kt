package pl.mftau.mftau.view.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.FragmentNavigatorExtras
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.transition.MaterialContainerTransform
import com.google.android.material.transition.MaterialElevationScale
import kotlinx.coroutines.launch
import pl.mftau.mftau.R
import pl.mftau.mftau.databinding.FragmentSongBookUserBinding
import pl.mftau.mftau.view.adapters.SongBookUserRecyclerAdapter
import pl.mftau.mftau.viewmodel.SongBookViewModel

class SongBookUserFragment : BindingFragment<FragmentSongBookUserBinding>() {

    private val mViewModel: SongBookViewModel by activityViewModels()
    private lateinit var mAdapter: SongBookUserRecyclerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        sharedElementEnterTransition = MaterialContainerTransform()
        exitTransition = MaterialElevationScale(false)
        reenterTransition = MaterialElevationScale(true)
    }

    override fun attachBinding(inflater: LayoutInflater, container: ViewGroup?) =
        FragmentSongBookUserBinding.inflate(inflater, container, false)

    override fun setup(savedInstanceState: Bundle?) {
        mAdapter = SongBookUserRecyclerAdapter()

        binding.contentSongBookUser.userSongsRecyclerView.apply {
            layoutManager = LinearLayoutManager(requireContext())
            itemAnimator = DefaultItemAnimator()
            adapter = mAdapter
            addOnScrollListener(object : RecyclerView.OnScrollListener() {
                override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                    super.onScrolled(recyclerView, dx, dy)
                    if (dy < 0 && !binding.addSongBtn.isShown)
                        binding.addSongBtn.show()
                    else if (dy > 0 && binding.addSongBtn.isShown)
                        binding.addSongBtn.hide()
                }
            })
            layoutAnimation =
                AnimationUtils.loadLayoutAnimation(this.context, R.anim.layout_animation_fall_down)
        }

        lifecycleScope.launch {
            mViewModel.combinedFlow.collect { songBook ->
                mAdapter.setSongsList(mViewModel.getSongsFromEntities(songBook.userSongs))
                binding.contentSongBookUser.userSongsRecyclerView.scheduleLayoutAnimation()

                binding.contentSongBookUser.loadingIndicator.hide()
                binding.contentSongBookUser.emptyView.visibility =
                    if (songBook.userSongs.isEmpty()) View.VISIBLE else View.INVISIBLE
            }
        }

        binding.songBookUserToolbar.setNavigationOnClickListener {
            findNavController().navigateUp()
        }

        binding.addSongBtn.setOnClickListener {
            binding.addSongBtn.transitionName = "shared_element_editor"
            findNavController().navigate(
                SongBookFragmentDirections.showSongEditorFragment(null),
                FragmentNavigatorExtras(binding.addSongBtn to "shared_element_editor")
            )
        }
    }
}