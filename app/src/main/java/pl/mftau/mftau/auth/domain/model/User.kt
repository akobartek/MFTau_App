package pl.mftau.mftau.auth.domain.model

data class User(
    val id: String = "",
    val email: String? = null,
    val userType: UserType = UserType.NONE
)