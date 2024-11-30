package pl.mftau.mftau.auth.data

import dev.gitlive.firebase.auth.FirebaseAuth
import dev.gitlive.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.NonCancellable
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.withContext
import pl.mftau.mftau.auth.domain.AuthRepository
import pl.mftau.mftau.auth.domain.model.EmailNotVerifiedException
import pl.mftau.mftau.auth.domain.model.FirestoreUser
import pl.mftau.mftau.auth.domain.model.User
import pl.mftau.mftau.common.utils.deleteObject
import pl.mftau.mftau.common.utils.getFirestoreDocument
import pl.mftau.mftau.common.utils.saveObject

class FirebaseAuthRepository(
    private val auth: FirebaseAuth,
    private val firestore: FirebaseFirestore
) : AuthRepository {

    override fun getCurrentUser(): Flow<User?> =
        auth.currentUser?.let { authUser ->
            firestore.getFirestoreDocument<User?>(
                collectionName = COLLECTION_USERS,
                documentId = authUser.uid,
            )
        } ?: flowOf(null)

    override suspend fun signIn(email: String, password: String): Result<Boolean> {
        return try {
            val authResult = auth.signInWithEmailAndPassword(email, password)
            authResult.user?.let { user ->
                if (user.isEmailVerified || user.email == TEST_EMAIL)
                    Result.success(true)
                else
                    Result.failure(EmailNotVerifiedException())
            } ?: Result.failure(Exception())
        } catch (exc: Exception) {
            Result.failure(exc)
        }
    }

    override suspend fun signUp(email: String, password: String): Result<Boolean> {
        return try {
            val authResult = auth.createUserWithEmailAndPassword(email, password)
            authResult.user?.let {
                withContext(NonCancellable) {
                    auth.currentUser?.let { user ->
                        user.sendEmailVerification()
                        firestore.saveObject(
                            collectionName = COLLECTION_USERS,
                            id = user.uid,
                            data = FirestoreUser.createUser(email),
                        )
                        signOut()
                    }
                    Result.success(true)
                }
            } ?: Result.failure(Exception())
        } catch (exc: Exception) {
            Result.failure(exc)
        }
    }

    override suspend fun sendRecoveryEmail(email: String): Result<Boolean> {
        return try {
            auth.sendPasswordResetEmail(email)
            Result.success(true)
        } catch (exc: Exception) {
            Result.failure(exc)
        }
    }

    override suspend fun sendVerificationEmail() {
        try {
            auth.currentUser?.sendEmailVerification()
        } catch (exc: Exception) {
            exc.printStackTrace()
        }
    }

    override suspend fun signOut() {
        auth.signOut()
    }

    override suspend fun deleteAccount() {
        try {
            withContext(NonCancellable) {
                auth.currentUser?.let { user ->
                    firestore.deleteObject(
                        collectionName = COLLECTION_USERS,
                        id = user.uid
                    )
                    user.delete()
                }
            }
        } catch (exc: Exception) {
            exc.printStackTrace()
        }
    }

    private companion object {
        const val COLLECTION_USERS = "users"
        const val TEST_EMAIL = "example@mftau.pl"
    }
}