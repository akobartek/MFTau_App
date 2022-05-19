package pl.mftau.mftau.view.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import pl.mftau.mftau.databinding.FragmentSongBottomSheetBinding
import pl.mftau.mftau.utils.SongBookUtils
import pl.mftau.mftau.viewmodel.SongBookSearchViewModel

class SongBottomSheetFragment : BindingFragment<FragmentSongBottomSheetBinding>() {

    private val mViewModel: SongBookSearchViewModel by activityViewModels()

    override fun attachBinding(inflater: LayoutInflater, container: ViewGroup?) =
        FragmentSongBottomSheetBinding.inflate(inflater, container, false)

    override fun setup(savedInstanceState: Bundle?) {
        mViewModel.song.observe(viewLifecycleOwner) {
            binding.songTitle.text = SongBookUtils.songTitles[it]
            binding.songText.text = SongBookUtils.songs[it]
        }

        binding.collapseSheetBtn.setOnClickListener { requireActivity().onBackPressed() }
    }
}