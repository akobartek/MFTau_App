package pl.mftau.mftau.auth.domain.model

import kotlinx.serialization.Serializable

@Serializable
data class User(
    val id: String = "",
    val email: String? = null,
    val userType: UserType = UserType.MEMBER,
    val photoUrl: String? = null,
)