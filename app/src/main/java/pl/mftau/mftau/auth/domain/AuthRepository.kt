package pl.mftau.mftau.auth.domain

import kotlinx.coroutines.flow.Flow
import pl.mftau.mftau.auth.domain.model.User

interface AuthRepository {
    val currentUserId: String
    val hasUser: Boolean

    val currentUser: Flow<User?>

    suspend fun signIn(email: String, password: String): Result<Boolean>
    suspend fun signUp(email: String, password: String): Result<Boolean>
    suspend fun sendRecoveryEmail(email: String): Result<Boolean>
    suspend fun signOut()
    suspend fun deleteAccount()
}