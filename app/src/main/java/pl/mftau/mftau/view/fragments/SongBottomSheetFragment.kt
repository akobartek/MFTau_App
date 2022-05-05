package pl.mftau.mftau.view.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import pl.mftau.mftau.databinding.FragmentSongBottomSheetBinding
import pl.mftau.mftau.utils.SongbookUtils
import pl.mftau.mftau.viewmodel.SongbookSearchViewModel

class SongBottomSheetFragment : BindingFragment<FragmentSongBottomSheetBinding>() {

    private val mViewModel: SongbookSearchViewModel by activityViewModels()

    override fun attachBinding(inflater: LayoutInflater, container: ViewGroup?) =
        FragmentSongBottomSheetBinding.inflate(inflater, container, false)

    override fun setup(savedInstanceState: Bundle?) {
        mViewModel.song.observe(viewLifecycleOwner) {
            binding.songTitle.text = SongbookUtils.songTitles[it]
            binding.songText.text = SongbookUtils.songs[it]
        }

        binding.collapseSheetBtn.setOnClickListener { requireActivity().onBackPressed() }
    }
}