package pl.mftau.mftau.view.fragments

import android.os.Bundle
import android.util.TypedValue
import android.view.LayoutInflater
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import pl.mftau.mftau.R
import pl.mftau.mftau.databinding.DialogSongChangeTextSizeBinding
import pl.mftau.mftau.databinding.FragmentSongBookBinding
import pl.mftau.mftau.utils.PreferencesManager
import pl.mftau.mftau.utils.showShortToast
import pl.mftau.mftau.view.adapters.SongBookRecyclerAdapter
import pl.mftau.mftau.view.ui.SongBookBottomAppBar

class SongBookFragment : BindingFragment<FragmentSongBookBinding>() {

    private lateinit var mRecyclerAdapter: SongBookRecyclerAdapter
    private val mMinTextSize = 12f
    private val mMaxTextSize = 32f

    override fun attachBinding(inflater: LayoutInflater, container: ViewGroup?) =
        FragmentSongBookBinding.inflate(inflater, container, false)

    override fun setup(savedInstanceState: Bundle?) {
        binding.songBookBottomAppBar.setContent {
            SongBookBottomAppBar(
                { findNavController().navigateUp() },
                {
                    if (PreferencesManager.getOpenSongBookAsPdf())
                        findNavController().navigateUp()
                    else
                        findNavController().navigate(SongBookFragmentDirections.showPdfFragment("song_book"))
                },
                {/* TODO() */}, {/* TODO() */},
                {
                    binding.songsRecyclerView.layoutAnimation =
                        AnimationUtils.loadLayoutAnimation(
                            binding.songsRecyclerView.context,
                            R.anim.layout_animation_fall_down
                        )
                    PreferencesManager.setSongBookShowCords(it)
                    mRecyclerAdapter.updateShowChords(it)
                },
                { showChangeTextSizeDialog(mRecyclerAdapter.getCurrentTextSize()) },
                {/* TODO() */}
            )
        }

        mRecyclerAdapter = SongBookRecyclerAdapter()
        binding.songsRecyclerView.apply {
            layoutManager = LinearLayoutManager(requireContext())
            itemAnimator = DefaultItemAnimator()
            adapter = mRecyclerAdapter
        }
    }

    private fun showChangeTextSizeDialog(currentSize: Float) {
        var newSize = currentSize
        val dialogBinding = DialogSongChangeTextSizeBinding.inflate(layoutInflater)
        dialogBinding.sizeDownBtn.setOnClickListener {
            if (newSize != mMinTextSize) {
                newSize--
                dialogBinding.exampleText.setTextSize(TypedValue.COMPLEX_UNIT_SP, newSize)
            } else requireContext().showShortToast(R.string.min_size_msg)
        }
        dialogBinding.sizeUpBtn.setOnClickListener {
            if (newSize != mMaxTextSize) {
                newSize++
                dialogBinding.exampleText.setTextSize(TypedValue.COMPLEX_UNIT_SP, newSize)
            } else requireContext().showShortToast(R.string.max_size_msg)
        }

        MaterialAlertDialogBuilder(requireContext())
            .setTitle(R.string.change_font_size)
            .setView(dialogBinding.root)
            .setPositiveButton(R.string.save) { dialog, _ ->
                dialog.dismiss()
                if (newSize != currentSize) mRecyclerAdapter.updateTextSize(newSize)
            }
            .setNegativeButton(R.string.cancel) { dialog, _ -> dialog.dismiss() }
            .create()
            .show()
    }
}