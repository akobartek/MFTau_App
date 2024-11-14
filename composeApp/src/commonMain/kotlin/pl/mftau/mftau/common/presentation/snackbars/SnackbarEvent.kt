package pl.mftau.mftau.common.presentation.snackbars

import mftau.composeapp.generated.resources.Res
import mftau.composeapp.generated.resources.copied_to_clipboard
import mftau.composeapp.generated.resources.delete_account_success
import mftau.composeapp.generated.resources.draw_delete_error
import mftau.composeapp.generated.resources.draw_delete_success
import mftau.composeapp.generated.resources.meeting_delete_success
import mftau.composeapp.generated.resources.meeting_saved
import mftau.composeapp.generated.resources.message_sent
import mftau.composeapp.generated.resources.person_delete_success
import mftau.composeapp.generated.resources.person_saved
import mftau.composeapp.generated.resources.playlists_updated
import mftau.composeapp.generated.resources.sign_in_error
import mftau.composeapp.generated.resources.sign_up_error
import mftau.composeapp.generated.resources.signed_in
import mftau.composeapp.generated.resources.song_removed
import mftau.composeapp.generated.resources.song_saved
import mftau.composeapp.generated.resources.undo
import org.jetbrains.compose.resources.StringResource

sealed class SnackbarEvent(
    val message: StringResource,
    val action: SnackbarAction? = null,
) {
    data object SignedIn : SnackbarEvent(message = Res.string.signed_in)

    data object SignInError : SnackbarEvent(message = Res.string.sign_in_error)

    data object SignUpError : SnackbarEvent(message = Res.string.sign_up_error)

    data object ResetPasswordMessageSent : SnackbarEvent(message = Res.string.message_sent)

    data object AccountDeleted : SnackbarEvent(message = Res.string.delete_account_success)

    data object CopiedToClipboard : SnackbarEvent(message = Res.string.copied_to_clipboard)

    data object PlaylistUpdated : SnackbarEvent(message = Res.string.playlists_updated)

    data object SongSaved : SnackbarEvent(message = Res.string.song_saved)

    data class DeleteSongFromPlaylist(val undo: suspend () -> Unit) :
        SnackbarEvent(
            message = Res.string.song_removed,
            action = SnackbarAction(name = Res.string.undo, action = undo),
        )

    data object PersonSaved : SnackbarEvent(message = Res.string.person_saved)

    data object PersonDeleted : SnackbarEvent(message = Res.string.person_delete_success)

    data object EmausDeleteSuccess : SnackbarEvent(message = Res.string.draw_delete_success)

    data object EmausDeleteError : SnackbarEvent(message = Res.string.draw_delete_error)

    data object MeetingSaved : SnackbarEvent(message = Res.string.meeting_saved)

    data object MeetingDeleted : SnackbarEvent(message = Res.string.meeting_delete_success)
}