package pl.mftau.mftau.viewmodel

import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth
import pl.mftau.mftau.utils.FirestoreUtils

class LoginViewModel : ViewModel() {

    private val adminAddresses = arrayOf(
        "rada@mftau.pl", "referat@mftau.pl", "lider@mftau.pl", "sokolowskijbartek@gmail.com"
    )

    fun createUserValues(email: String): HashMap<String, Any> {
        val user = HashMap<String, Any>()
        user[FirestoreUtils.firestoreKeyEmail] = FirebaseAuth.getInstance().currentUser!!.email!!
        when {
            adminAddresses.contains(email) -> {
                user[FirestoreUtils.firestoreKeyIsAdmin] = true
                user[FirestoreUtils.firestoreKeyIsLeader] = false
                user[FirestoreUtils.firestoreKeyIsMember] = false
            }
            email.contains("@mftau.pl") && email != "modlitwa@mftau.pl" -> {
                user[FirestoreUtils.firestoreKeyIsAdmin] = false
                user[FirestoreUtils.firestoreKeyIsLeader] = true
                user[FirestoreUtils.firestoreKeyIsMember] = false
            }
            else -> {
                user[FirestoreUtils.firestoreKeyIsAdmin] = false
                user[FirestoreUtils.firestoreKeyIsLeader] = false
                user[FirestoreUtils.firestoreKeyIsMember] = true
            }
        }
        return user
    }
}