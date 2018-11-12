package pl.mftau.mftau

import android.graphics.Color
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.activity_login.*
import java.util.regex.Pattern
import android.text.style.UnderlineSpan
import android.text.SpannableString
import android.view.LayoutInflater
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.dialog_reset_password.view.*


class LoginActivity : AppCompatActivity() {

    private var isSigningUp = false
    private lateinit var mAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        setSupportActionBar(loginToolbar)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
            window.statusBarColor = Color.WHITE
        }

        mAuth = FirebaseAuth.getInstance()
        setOnClickListeners()

        var string = SpannableString(forgotPasswordTV.text)
        string.setSpan(UnderlineSpan(), 0, string.length, 0)
        forgotPasswordTV.text = string
        string = SpannableString(createAccountTV.text)
        string.setSpan(UnderlineSpan(), 0, string.length, 0)
        createAccountTV.text = string
    }

    private fun setOnClickListeners() {
        forgotPasswordTV.setOnClickListener {
            showResetPasswordDialog()
        }

        createAccountTV.setOnClickListener {
            isSigningUp = true
            loginBtn.text = getString(R.string.sign_up)
            forgotPasswordTV.animate().alpha(0f).duration = 300
            createAccountTV.animate().alpha(0f).duration = 300
//            forgotPasswordTV.visibility = View.INVISIBLE
//            createAccountTV.visibility = View.INVISIBLE
        }

        loginBtn.setOnClickListener {
            val email = emailET.text.toString().trim()
            val password = passwordET.text.toString().trim()

            if (!isEmailAndPasswordValid(email, password)) return@setOnClickListener

            if (isSigningUp) {
                mAuth.createUserWithEmailAndPassword(email, password)
                        .addOnCompleteListener(this@LoginActivity) { task ->
                            if (task.isSuccessful) {
                                mAuth.currentUser!!.sendEmailVerification()
                                showSignupSuccessfulDialog()
                            } else {
                                Snackbar.make(loginLayout, R.string.sign_up_error, Snackbar.LENGTH_SHORT).show()
                            }
                        }
            } else {
                mAuth.signInWithEmailAndPassword(email, password)
                        .addOnCompleteListener(this@LoginActivity) { task ->
                            if (task.isSuccessful) {
                                if (!mAuth.currentUser!!.isEmailVerified) {
                                    showVerifyEmailDialog()
                                } else {
                                    Toast.makeText(this@LoginActivity, R.string.signed_in, Toast.LENGTH_SHORT).show()
                                    finish()
                                }
                            } else {
                                Snackbar.make(loginLayout, R.string.sign_in_error, Snackbar.LENGTH_SHORT).show()
                            }
                        }
            }
        }
    }

    private fun isEmailAndPasswordValid(email: String, password: String): Boolean {
        var isValid = true

        if (!isEmailValid(email)) {
            emailET.error = getString(R.string.email_error)
            isValid = false
        }

        if (password.length < 6) {
            passwordET.error = getString(R.string.password_error_too_short)
            isValid = false
        } else if (!isValidPassword(password)) {
            passwordET.error = getString(R.string.password_error_wrong)
            isValid = false
        }

        return isValid
    }

    private fun isEmailValid(email: String): Boolean {
        return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }

    private fun isValidPassword(password: String): Boolean {
        val passwordRegex = "((?=.*[a-z])(?=.*\\d)(?=.*[A-Z]).{6,20})"
        val pattern = Pattern.compile(passwordRegex)
        val matcher = pattern.matcher(password)
        return matcher.matches()
    }

    private fun showSignupSuccessfulDialog() = AlertDialog.Builder(this)
            .setTitle(R.string.sign_up_successful_dialog_title)
            .setMessage(R.string.sign_up_successful_dialog_message)
            .setCancelable(false)
            .setPositiveButton(getString(R.string.ok)) { dialog, _ ->
                dialog?.dismiss()
                finish()
            }
            .create()
            .show()

    private fun showVerifyEmailDialog() = AlertDialog.Builder(this)
            .setTitle(R.string.verify_email_dialog_title)
            .setMessage(R.string.verify_email_dialog_message)
            .setCancelable(false)
            .setPositiveButton(getString(R.string.ok)) { dialog, _ ->
                dialog?.dismiss()
                finish()
            }
            .setNeutralButton(getString(R.string.verify_email_send_again)) { dialog, _ ->
                dialog.dismiss()
                mAuth.currentUser!!.sendEmailVerification()
                Toast.makeText(this@LoginActivity, getString(R.string.message_sent), Toast.LENGTH_SHORT).show()
                finish()
            }
            .create()
            .show()

    private fun showResetPasswordDialog() {
        val view = LayoutInflater.from(this@LoginActivity).inflate(R.layout.dialog_reset_password, null)

        val dialog = AlertDialog.Builder(this)
                .setTitle(R.string.reset_password_dialog_title)
                .setMessage(R.string.reset_password_dialog_message)
                .setView(view)
                .setCancelable(false)
                .setPositiveButton(getString(R.string.send), null)
                .setNegativeButton(getString(R.string.cancel)) { dialog, _ -> dialog?.dismiss() }
                .create()

        dialog.setOnShowListener {
            (dialog as AlertDialog).getButton(AlertDialog.BUTTON_POSITIVE).setOnClickListener {
                val email = view.resetPasswordET.text.toString().trim()
                if (!isEmailValid(email)) {
                    view.resetPasswordET.error = getString(R.string.email_error)
                    return@setOnClickListener
                } else {
                    mAuth.sendPasswordResetEmail(email)
                            .addOnCompleteListener { task ->
                                if (task.isSuccessful) {
                                    dialog.dismiss()
                                    Snackbar.make(loginLayout, R.string.message_sent, Snackbar.LENGTH_SHORT).show()
                                } else {
                                    view.resetPasswordET.error = getString(R.string.reset_password_error)
                                }
                            }
                }
            }
        }

        dialog.show()
    }


}
