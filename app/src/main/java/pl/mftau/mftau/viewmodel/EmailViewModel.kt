package pl.mftau.mftau.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel

class EmailViewModel(app: Application) : AndroidViewModel(app) {

    val emailAddresses = arrayOf("modlitwa@mftau.pl", "sokolowskijbartek@gmail.com")

    var mailType = ""

    fun isEmailValid(email: String): Boolean {
        return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }
}