package pl.mftau.mftau.core.presentation.home

import pl.mftau.mftau.auth.domain.model.User

data class HomeScreenState(
    val user: User? = null,
    val resetPasswordFailed: Boolean = false,
)