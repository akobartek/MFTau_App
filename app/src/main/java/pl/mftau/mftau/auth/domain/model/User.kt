package pl.mftau.mftau.auth.domain.model

import android.net.Uri

data class User(
    val id: String = "",
    val email: String? = null,
    val userType: UserType = UserType.MEMBER,
    val photoUri: Uri? = null
)