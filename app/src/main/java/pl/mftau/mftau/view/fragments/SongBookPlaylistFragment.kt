package pl.mftau.mftau.view.fragments

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.view.animation.AnimationUtils
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.ItemTouchHelper.*
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.transition.MaterialContainerTransform
import com.google.android.material.transition.MaterialElevationScale
import kotlinx.coroutines.launch
import pl.mftau.mftau.R
import pl.mftau.mftau.databinding.DialogSongBookImportPlaylistBinding
import pl.mftau.mftau.databinding.FragmentSongBookPlaylistBinding
import pl.mftau.mftau.utils.hideKeyboard
import pl.mftau.mftau.utils.tryToRunFunctionOnInternet
import pl.mftau.mftau.view.adapters.SongBookPlaylistRecyclerAdapter
import pl.mftau.mftau.viewmodel.PlaylistViewModel

class SongBookPlaylistFragment : BindingFragment<FragmentSongBookPlaylistBinding>() {

    private val mViewModel: PlaylistViewModel by viewModels()
    private lateinit var mAdapter: SongBookPlaylistRecyclerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        sharedElementEnterTransition = MaterialContainerTransform()
        exitTransition = MaterialElevationScale(false)
        reenterTransition = MaterialElevationScale(true)
    }

    override fun attachBinding(inflater: LayoutInflater, container: ViewGroup?) =
        FragmentSongBookPlaylistBinding.inflate(inflater, container, false)

    override fun setup(savedInstanceState: Bundle?) {
        mItemTouchHelper.attachToRecyclerView(binding.contentPlaylist.playlistRecyclerView)
        mAdapter =
            SongBookPlaylistRecyclerAdapter(mViewModel, false) { mItemTouchHelper.startDrag(it) }
        binding.contentPlaylist.playlistRecyclerView.apply {
            layoutManager = LinearLayoutManager(requireContext())
            itemAnimator = DefaultItemAnimator()
            adapter = mAdapter
            layoutAnimation =
                AnimationUtils.loadLayoutAnimation(this.context, R.anim.layout_animation_fall_down)
        }

        lifecycleScope.launch {
            mViewModel.playlist.collect { playlist ->
                mViewModel.fetchPlaylist(playlist)
            }
        }

        mViewModel.visibleSongs.observe(viewLifecycleOwner) { songs ->
            mAdapter.setPlayList(songs)
            binding.contentPlaylist.playlistRecyclerView.scheduleLayoutAnimation()

            binding.contentPlaylist.loadingIndicator.hide()
            binding.contentPlaylist.emptyView.visibility =
                if (songs.isEmpty()) View.VISIBLE else View.INVISIBLE
        }

        binding.songBookPlaylistToolbar.setNavigationOnClickListener { findNavController().navigateUp() }
        binding.songBookPlaylistToolbar.setOnMenuItemClickListener {
            when (it.itemId) {
                R.id.action_clear_playlist ->
                    showClearPlaylistConfirmationDialog()
                R.id.action_share_playlist ->
                    requireActivity().tryToRunFunctionOnInternet { showSharePlaylistDialog() }
            }
            true
        }

        binding.importPlaylistBtn.setOnClickListener {
            requireActivity().tryToRunFunctionOnInternet { showImportPlaylistDialog() }
        }
    }

    private fun showSharePlaylistDialog() {
        val loadingDialog = MaterialAlertDialogBuilder(requireContext())
            .setView(R.layout.dialog_loading)
            .setOnCancelListener { findNavController().navigateUp() }
            .create()
        loadingDialog.show()
        mViewModel.getPlaylistShareCode(mAdapter.getCurrentPlaylist()).observe(viewLifecycleOwner) {
            loadingDialog.hide()
            MaterialAlertDialogBuilder(requireContext())
                .setTitle(R.string.share_playlist)
                .setMessage(getString(R.string.share_playlist_dialog_msg, it))
                .setPositiveButton(R.string.copy_to_clipboard) { dialog, _ ->
                    (requireActivity().getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager)
                        .setPrimaryClip(ClipData.newPlainText("playlist", it))
                    dialog.dismiss()
                    Snackbar.make(binding.root, R.string.copied_to_clipboard, Snackbar.LENGTH_SHORT)
                        .show()
                }
                .setNegativeButton(R.string.cancel) { dialog, _ -> dialog.dismiss() }
                .create()
                .show()
        }
    }

    private fun showClearPlaylistConfirmationDialog() =
        MaterialAlertDialogBuilder(requireContext())
            .setTitle(R.string.playlist_clear_dialog_msg)
            .setPositiveButton(R.string.clear) { dialog, _ ->
                mViewModel.clearPlaylist()
                dialog.dismiss()
            }
            .setNegativeButton(R.string.cancel) { dialog, _ -> dialog.dismiss() }
            .create()
            .show()

    private fun showImportPlaylistDialog() {
        val dialogBinding = DialogSongBookImportPlaylistBinding.inflate(layoutInflater)
        val dialog = MaterialAlertDialogBuilder(requireContext())
            .setView(dialogBinding.root)
            .setPositiveButton(getString(R.string.import_btn), null)
            .setNegativeButton(getString(R.string.cancel)) { dialog, _ -> dialog?.dismiss() }
            .create()
        dialog.window?.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN)
        dialog.setOnShowListener {
            dialog.getButton(AlertDialog.BUTTON_POSITIVE).setOnClickListener {
                val playlistCode = dialogBinding.playlistCodeET.text.toString().trim()
                if (playlistCode.isBlank()) {
                    dialogBinding.playlistCodeInputLayout.error =
                        getString(R.string.empty_field_error)
                    return@setOnClickListener
                } else if (!playlistCode.startsWith("sb ") && !playlistCode.startsWith("fs ")) {
                    dialogBinding.playlistCodeInputLayout.error =
                        getString(R.string.import_incorrect_code_error)
                    return@setOnClickListener
                } else {
                    dialog.dismiss()
                    requireActivity().hideKeyboard()
                    findNavController().navigate(
                        SongBookPlaylistFragmentDirections.showImportedPlaylistFragment(playlistCode)
                    )
                }
            }
        }
        dialog.show()
    }

    private val mItemTouchHelper by lazy {
        val simpleItemTouchCallback =
            object : ItemTouchHelper.Callback() {

                override fun isLongPressDragEnabled(): Boolean = false

                override fun getMovementFlags(
                    recyclerView: RecyclerView,
                    viewHolder: RecyclerView.ViewHolder
                ): Int {
                    return makeMovementFlags(UP or DOWN, 0)
                }

                override fun onMove(
                    recyclerView: RecyclerView,
                    viewHolder: RecyclerView.ViewHolder,
                    target: RecyclerView.ViewHolder
                ): Boolean {
                    (recyclerView.adapter as SongBookPlaylistRecyclerAdapter)
                        .moveItem(viewHolder.adapterPosition, target.adapterPosition)
                    return true
                }

                override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                }

                override fun onSelectedChanged(
                    viewHolder: RecyclerView.ViewHolder?,
                    actionState: Int
                ) {
                    if (actionState == ACTION_STATE_DRAG) viewHolder?.itemView?.alpha = 0.5f
                    super.onSelectedChanged(viewHolder, actionState)
                }

                override fun clearView(
                    recyclerView: RecyclerView,
                    viewHolder: RecyclerView.ViewHolder
                ) {
                    viewHolder.itemView.alpha = 1.0f
                    super.clearView(recyclerView, viewHolder)
                }
            }

        ItemTouchHelper(simpleItemTouchCallback)
    }
}