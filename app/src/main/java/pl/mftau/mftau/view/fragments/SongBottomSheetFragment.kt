package pl.mftau.mftau.view.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import kotlinx.android.synthetic.main.fragment_song_bottom_sheet.view.*
import pl.mftau.mftau.R
import pl.mftau.mftau.utils.SongbookUtils
import pl.mftau.mftau.viewmodel.SongbookSearchViewModel

class SongBottomSheetFragment : Fragment() {

    private val mViewModel: SongbookSearchViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.fragment_song_bottom_sheet, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        mViewModel.song.observe(viewLifecycleOwner, {
            view.songTitle.text = SongbookUtils.songTitles[it]
            view.songText.text = SongbookUtils.songs[it]
        })

        view.collapseSheetBtn.setOnClickListener { requireActivity().onBackPressed() }
    }
}