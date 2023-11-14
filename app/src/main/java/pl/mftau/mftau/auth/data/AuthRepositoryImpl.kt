package pl.mftau.mftau.auth.data

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.toObject
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await
import pl.mftau.mftau.auth.domain.AuthRepository
import pl.mftau.mftau.auth.domain.model.FirebaseAuthEmailNotVerifiedException
import pl.mftau.mftau.auth.domain.model.FirestoreUser
import pl.mftau.mftau.auth.domain.model.User

class AuthRepositoryImpl(
    private val auth: FirebaseAuth,
    private val firestore: FirebaseFirestore
) : AuthRepository {
    override val currentUserId: String
        get() = auth.currentUser?.uid.orEmpty()

    override val hasUser: Boolean
        get() = auth.currentUser != null

    override val currentUser: Flow<User?>
        get() = callbackFlow {
            val scope = this
            val listener =
                FirebaseAuth.AuthStateListener { auth ->
                    scope.launch {
                        val authUser = auth.currentUser?.let { User(it.uid, it.email) }
                        val firestoreUser = authUser?.let {
                            firestore.collection(USERS_COLLECTION)
                                .document(authUser.id)
                                .get().await().toObject<User>()
                        }
                        scope.trySend(authUser)
                    }
                }
            auth.addAuthStateListener(listener)
            awaitClose { auth.removeAuthStateListener(listener) }
        }

    override suspend fun signIn(email: String, password: String): Result<Boolean> {
        return try {
            val task = auth.signInWithEmailAndPassword(email, password).also { it.await() }
            val result = if (task.isSuccessful) {
                auth.currentUser?.let { user ->
                    if (user.isEmailVerified || user.email != "example@mftau.pl")
                        Result.success(true)
                    else Result.failure(FirebaseAuthEmailNotVerifiedException())
                } ?: Result.failure(Exception())
            } else Result.failure(task.exception ?: Exception())
            result
        } catch (exc: Exception) {
            Result.failure(exc)
        }
    }

    override suspend fun signUp(email: String, password: String): Result<Boolean> {
        return try {
            val task = auth.createUserWithEmailAndPassword(email, password).also { it.await() }
            val result = if (task.isSuccessful) {
                auth.currentUser?.let { user ->
                    user.sendEmailVerification()
                    firestore.collection(USERS_COLLECTION)
                        .document(user.uid)
                        .set(FirestoreUser.createUser(email))
                }
                signOut()
                Result.success(true)
            } else Result.failure(task.exception ?: Exception())
            result
        } catch (exc: Exception) {
            Result.failure(exc)
        }
    }

    override suspend fun sendRecoveryEmail(email: String): Result<Boolean> {
        return try {
            val task = auth.sendPasswordResetEmail(email).also { it.await() }
            if (task.isSuccessful) Result.success(true)
            else Result.failure(task.exception ?: Exception())
        } catch (exc: Exception) {
            Result.failure(exc)
        }
    }

    override suspend fun signOut() {
        auth.signOut()
    }

    override suspend fun deleteAccount() {
        auth.currentUser?.delete()?.await()
    }

    companion object {
        private const val USERS_COLLECTION = "users"
    }
}