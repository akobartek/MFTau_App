package pl.mftau.mftau.view.fragments

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.TypedValue
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.view.menu.MenuBuilder
import androidx.appcompat.view.menu.MenuPopupHelper
import androidx.appcompat.widget.PopupMenu
import androidx.fragment.app.activityViewModels
import pl.mftau.mftau.R
import pl.mftau.mftau.databinding.FragmentSongBottomSheetBinding
import pl.mftau.mftau.utils.PreferencesManager
import pl.mftau.mftau.utils.SongBookUtils
import pl.mftau.mftau.utils.showChangeTextSizeDialog
import pl.mftau.mftau.viewmodel.SongBookSearchViewModel

class SongBottomSheetFragment : BindingFragment<FragmentSongBottomSheetBinding>() {

    private val mViewModel: SongBookSearchViewModel by activityViewModels()
    private var mCurrentTextSize = 18f

    override fun attachBinding(inflater: LayoutInflater, container: ViewGroup?) =
        FragmentSongBottomSheetBinding.inflate(inflater, container, false)

    @SuppressLint("RestrictedApi")
    override fun setup(savedInstanceState: Bundle?) {
        with(binding) {
            mViewModel.song.observe(viewLifecycleOwner) {
                songTitle.text = SongBookUtils.songTitles[it]
                songText.text = SongBookUtils.songs[it]
                songChords.text = SongBookUtils.chords[it]
            }

            setChordsVisibility()

            collapseSheetBtn.setOnClickListener { requireActivity().onBackPressed() }

            openMenuBtn.setOnClickListener {
                val popupMenu = PopupMenu(requireContext(), it)
                popupMenu.inflate(R.menu.menu_song_popup)

                popupMenu.menu.findItem(R.id.action_show_chords).isVisible =
                    !PreferencesManager.getSongBookShowCords()
                popupMenu.menu.findItem(R.id.action_hide_chords).isVisible =
                    PreferencesManager.getSongBookShowCords()
                // TODO() -> SET CORRECT PLAYLIST MENU ICONS VISIBILITY

                popupMenu.setOnMenuItemClickListener { menuItem ->
                    when (menuItem.itemId) {
                        R.id.action_show_chords -> {
                            PreferencesManager.setSongBookShowCords(true)
                            setChordsVisibility()
                        }
                        R.id.action_hide_chords -> {
                            PreferencesManager.setSongBookShowCords(false)
                            setChordsVisibility()
                        }
                        R.id.action_change_font_size -> {
                            showChangeTextSizeDialog(mCurrentTextSize) { textSize ->
                                mCurrentTextSize = textSize
                                songText.setTextSize(TypedValue.COMPLEX_UNIT_SP, mCurrentTextSize)
                                songChords.setTextSize(TypedValue.COMPLEX_UNIT_SP, mCurrentTextSize)
                            }
                        }
                        R.id.action_add_to_playlist -> { /* TODO() */
                        }
                        R.id.action_remove_from_playlist -> { /* TODO() */
                        }
                    }
                    true
                }
                val helper = MenuPopupHelper(requireContext(), popupMenu.menu as MenuBuilder, it)
                helper.setForceShowIcon(true)
                helper.show()
            }
        }
    }

    private fun setChordsVisibility() {
        val shouldChordsBeVisible = PreferencesManager.getSongBookShowCords()
        binding.songChords.visibility = if (shouldChordsBeVisible) View.VISIBLE else View.GONE
        binding.divider.visibility = if (shouldChordsBeVisible) View.VISIBLE else View.INVISIBLE
    }
}