package pl.mftau.mftau.viewmodel

import android.app.Application
import android.text.SpannableString
import android.text.style.UnderlineSpan
import androidx.lifecycle.AndroidViewModel
import com.google.firebase.auth.FirebaseAuth
import pl.mftau.mftau.utils.FirestoreUtils
import java.util.regex.Pattern


class LoginViewModel(app: Application) : AndroidViewModel(app) {

    private val adminAddresses = arrayOf(
            "rada@mftau.pl", "referat@mftau.pl", "webmaster@mftau.pl", "lider@mftau.pl"
    )


    fun createSpannableString(string: String): SpannableString {
        val spannable = SpannableString(string)
        spannable.setSpan(UnderlineSpan(), 0, string.length, 0)
        return spannable
    }

    fun isEmailValid(email: String?): Boolean {
        return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }

    fun isValidPassword(password: String?): Boolean {
        val passwordRegex = "((?=.*[a-z])(?=.*\\d)(?=.*[A-Z]).{6,20})"
        val pattern = Pattern.compile(passwordRegex)
        val matcher = pattern.matcher(password)
        return matcher.matches()
    }

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