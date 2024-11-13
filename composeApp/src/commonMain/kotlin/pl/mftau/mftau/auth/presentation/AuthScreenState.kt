package pl.mftau.mftau.auth.presentation

data class AuthScreenState(
    val email: String = "",
    val emailError: EmailErrorType? = null,
    val password: String = "",
    val passwordHidden: Boolean = true,
    val passwordError: PasswordErrorType? = null,
    val isSignedIn: Boolean = false,
    val isSignedUpDialogVisible: Boolean = false,
    val noInternetAction: NoInternetAction? = null,
    val forgottenPasswordDialogVisible: Boolean = false,
    val forgottenPasswordDialogError: Boolean = false,
    val emailUnverifiedDialogVisible: Boolean = false
)

enum class EmailErrorType {
    INVALID, NO_USER, USER_EXISTS
}

enum class PasswordErrorType {
    EMPTY, TOO_SHORT, WRONG, INVALID
}

enum class NoInternetAction {
    SIGN_IN, SIGN_UP, RESET_PASSWORD
}