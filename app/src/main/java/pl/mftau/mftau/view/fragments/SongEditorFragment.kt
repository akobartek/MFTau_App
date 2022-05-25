package pl.mftau.mftau.view.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.google.android.material.chip.Chip
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.android.material.transition.MaterialContainerTransform
import pl.mftau.mftau.R
import pl.mftau.mftau.databinding.FragmentSongEditorBinding
import pl.mftau.mftau.db.entities.SongEntity
import pl.mftau.mftau.model.local_db.Song
import pl.mftau.mftau.viewmodel.SongBookViewModel

class SongEditorFragment : BindingFragment<FragmentSongEditorBinding>() {

    private val mViewModel: SongBookViewModel by activityViewModels()
    private var mIsPreviewVisible = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        sharedElementEnterTransition = MaterialContainerTransform()
    }

    override fun attachBinding(inflater: LayoutInflater, container: ViewGroup?) =
        FragmentSongEditorBinding.inflate(inflater, container, false)

    override fun setup(savedInstanceState: Bundle?) {
        val song = SongEditorFragmentArgs.fromBundle(requireArguments()).song
        binding.songEditorToolbarTitle.text =
            getString(if (song == null) R.string.add_song else R.string.edit_song)

        with(binding.contentSongEditor) {
            if (song != null) {
                titleET.setText(song.title)
                textET.setText(song.text)
                chordsET.setText(song.chords)
                song.databaseTopics.split("+").forEach {
                    topicsChipGroup.findViewWithTag<Chip>(it)?.isChecked = true
                }
            }
        }

        binding.songEditorToolbar.apply {
            setNavigationOnClickListener { findNavController().navigateUp() }
            menu.findItem(R.id.action_delete_song).isVisible = song != null

            setOnMenuItemClickListener { item ->
                when (item.itemId) {
                    R.id.action_show_preview -> showPreview()
                    R.id.action_save_song -> saveSong(song)
                    R.id.action_delete_song -> song?.let { showDeleteConfirmationDialog(it) }
                }
                true
            }
        }
    }

    private fun showPreview() {
        with(binding.contentSongEditor) {
            val editorVisibility = if (mIsPreviewVisible) View.VISIBLE else View.GONE
            val previewVisibility = if (mIsPreviewVisible) View.GONE else View.VISIBLE

            if (!mIsPreviewVisible) {
                titlePreview.text = titleET.text.toString()
                textPreview.text = textET.text.toString()
                chordsPreview.text = chordsET.text.toString()
            }

            titleInputLayout.visibility = editorVisibility
            textInputLayout.visibility = editorVisibility
            chordsInputLayout.visibility = editorVisibility
            topicsTitle.visibility = editorVisibility
            topicsScrollView.visibility = editorVisibility

            titlePreview.visibility = previewVisibility
            textPreviewScrollView.visibility = previewVisibility
            divider.visibility = previewVisibility
            chordsPreview.visibility = previewVisibility
        }
        mIsPreviewVisible = !mIsPreviewVisible
    }

    private fun saveSong(song: Song?) {
        if (!areInputFieldsValid()) return

        val songEntity = with(binding.contentSongEditor) {
            val topics = "ALL" + if (topicsChipGroup.checkedChipIds.isNotEmpty()) {
                topicsChipGroup.checkedChipIds
                    .map { topicsChipGroup.findViewById<Chip>(it).tag }
                    .joinToString("+", "+")
            } else ""
            SongEntity(
                title = titleET.text.toString(),
                text = textET.text.toString(),
                chords = chordsET.text.toString(),
                topics = topics
            )
        }

        if (song != null) {
            songEntity.id = song.databaseId
            mViewModel.updateSong(songEntity)
        } else
            mViewModel.insertNewSong(songEntity)

        findNavController().navigateUp()
    }

    private fun areInputFieldsValid(): Boolean {
        var isValid = true

        with(binding.contentSongEditor) {
            if (titleET.text?.isBlank() == true) {
                titleInputLayout.error = getString(R.string.empty_field_error)
                isValid = false
            }
            if (textET.text?.isBlank() == true) {
                textInputLayout.error = getString(R.string.empty_field_error)
                isValid = false
            }
        }

        return isValid
    }

    private fun showDeleteConfirmationDialog(song: Song) =
        MaterialAlertDialogBuilder(requireContext())
            .setMessage(R.string.member_delete_dialog_msg)
            .setCancelable(false)
            .setPositiveButton(R.string.delete) { dialog, _ ->
                mViewModel.deleteSong(song)
                dialog.dismiss()
                findNavController().navigateUp()
            }
            .setNegativeButton(R.string.cancel) { dialog, _ -> dialog.dismiss() }
            .create()
            .show()
}