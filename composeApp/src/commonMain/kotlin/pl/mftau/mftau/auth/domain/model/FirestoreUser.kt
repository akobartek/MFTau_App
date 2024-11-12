package pl.mftau.mftau.auth.domain.model

data class FirestoreUser(
    val email: String? = null,
    val userType: UserType = UserType.MEMBER
) {
    companion object {
        private val adminAddresses = arrayOf(
            "rada@mftau.pl", "referat@mftau.pl", "lider@mftau.pl", "sokolowskijbartek@gmail.com"
        )

        fun createUser(email: String): FirestoreUser {
            val type = when {
                adminAddresses.contains(email) -> UserType.ADMIN
                email.contains("@mftau.pl") && email != "modlitwa@mftau.pl" -> UserType.LEADER
                else -> UserType.MEMBER
            }
            return FirestoreUser(email, type)
        }
    }
}