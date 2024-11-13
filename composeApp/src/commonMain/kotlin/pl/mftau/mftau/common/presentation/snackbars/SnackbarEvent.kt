package pl.mftau.mftau.common.presentation.snackbars

import mftau.composeapp.generated.resources.Res
import mftau.composeapp.generated.resources.delete_account_success
import mftau.composeapp.generated.resources.message_sent
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
}