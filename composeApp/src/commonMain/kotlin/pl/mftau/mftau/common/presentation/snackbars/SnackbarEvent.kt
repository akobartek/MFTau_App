package pl.mftau.mftau.common.presentation.snackbars

import mftau.composeapp.generated.resources.Res
import mftau.composeapp.generated.resources.delete_account_success
import org.jetbrains.compose.resources.StringResource

sealed class SnackbarEvent(val message: StringResource, val action: SnackbarAction?) {

    data object AccountDeleted :
        SnackbarEvent(message = Res.string.delete_account_success, action = null)
}