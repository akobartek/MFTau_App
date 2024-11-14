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
import mftau.composeapp.generated.resources.sign_in_error
import mftau.composeapp.generated.resources.sign_up_error
import mftau.composeapp.generated.resources.signed_in
import org.jetbrains.compose.resources.StringResource

sealed class SnackbarEvent(val message: StringResource, val action: SnackbarAction?) {

    data object SignedIn :
        SnackbarEvent(message = Res.string.signed_in, action = null)

    data object SignInError :
        SnackbarEvent(message = Res.string.sign_in_error, action = null)

    data object SignUpError :
        SnackbarEvent(message = Res.string.sign_up_error, action = null)

    data object ResetPasswordMessageSent :
        SnackbarEvent(message = Res.string.message_sent, action = null)

    data object AccountDeleted :
        SnackbarEvent(message = Res.string.delete_account_success, action = null)

    data object CopiedToClipboard :
        SnackbarEvent(message = Res.string.copied_to_clipboard, action = null)

    data object PersonSaved :
        SnackbarEvent(message = Res.string.person_saved, action = null)

    data object PersonDeleted :
        SnackbarEvent(message = Res.string.person_delete_success, action = null)

    data object EmausDeleteSuccess :
        SnackbarEvent(message = Res.string.draw_delete_success, action = null)

    data object EmausDeleteError :
        SnackbarEvent(message = Res.string.draw_delete_error, action = null)

    data object MeetingSaved :
        SnackbarEvent(message = Res.string.meeting_saved, action = null)

    data object MeetingDeleted :
        SnackbarEvent(message = Res.string.meeting_delete_success, action = null)
}