package pl.mftau.mftau.view.activities

import android.graphics.Color
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.activity_login.*
import android.util.Log
import android.view.LayoutInflater
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.core.app.NavUtils
import androidx.lifecycle.ViewModelProviders
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException
import com.google.firebase.auth.FirebaseAuthInvalidUserException
import com.google.firebase.auth.FirebaseAuthUserCollisionException
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.dialog_reset_password.view.*
import pl.mftau.mftau.R
import pl.mftau.mftau.model.utils.FirestoreUtils.firestoreCollectionUsers
import pl.mftau.mftau.viewmodel.LoginViewModel

class LoginActivity : AppCompatActivity() {

    private var isSigningUp = false
    private lateinit var mAuth: FirebaseAuth
    private lateinit var mFirestore: FirebaseFirestore

    private lateinit var mLoginViewModel: LoginViewModel

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
        mFirestore = FirebaseFirestore.getInstance()

        mLoginViewModel = ViewModelProviders.of(this@LoginActivity).get(LoginViewModel::class.java)

        setOnClickListeners()

        forgotPasswordTV.text = mLoginViewModel.createSpannableString(forgotPasswordTV.text.toString())
        createAccountTV.text = mLoginViewModel.createSpannableString(createAccountTV.text.toString())
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
            emailET.error = null
            passwordET.error = null
        }

        loginBtn.setOnClickListener {
            val email = emailET.text.toString().trim()
            val password = passwordET.text.toString().trim()

            if (isSigningUp) {
                if (!isEmailAndPasswordValid(email, password)) return@setOnClickListener

                mAuth.createUserWithEmailAndPassword(email, password)
                        .addOnCompleteListener(this@LoginActivity) { task ->
                            if (task.isSuccessful) {
                                mAuth.currentUser!!.sendEmailVerification()

                                mFirestore.collection(firestoreCollectionUsers)
                                        .document(mAuth.currentUser!!.uid)
                                        .set(mLoginViewModel.createUserValues(email))

                                mAuth.signOut()
                                isSigningUp = false
                                loginBtn.text = getString(R.string.sign_in)
                                forgotPasswordTV.animate().alpha(1f).duration = 300
                                createAccountTV.animate().alpha(1f).duration = 300

                                showSignupSuccessfulDialog()
                            } else {
                                Log.d("SignUpFailed", task.exception!!.toString())
                                if (task.exception is FirebaseAuthUserCollisionException) {
                                    emailET.error = getString(R.string.sign_up_existing_user_error)
                                    emailET.requestFocus()
                                } else
                                    Snackbar.make(loginLayout, R.string.sign_up_error, Snackbar.LENGTH_LONG).show()
                            }
                        }
            } else {
                mAuth.signInWithEmailAndPassword(email, password)
                        .addOnCompleteListener(this@LoginActivity) { task ->
                            if (task.isSuccessful) {
                                // TODO (Auth) -> Turn on verification
//                                if (!mAuth.currentUser!!.isEmailVerified) {
//                                    showVerifyEmailDialog()
//                                } else {
//                                    Toast.makeText(this@LoginActivity, R.string.signed_in, Toast.LENGTH_SHORT).show()
//                                    finish()
//                                }
                            } else {
                                Log.d("SignInFailed", task.exception!!.toString())
                                when {
                                    task.exception is FirebaseAuthInvalidUserException -> {
                                        emailET.error = getString(R.string.sign_in_no_user_error)
                                        emailET.requestFocus()
                                    }
                                    task.exception is FirebaseAuthInvalidCredentialsException -> {
                                        passwordET.error = getString(R.string.sign_in_wrong_password_error)
                                        passwordET.requestFocus()
                                    }
                                    else -> Snackbar.make(loginLayout, R.string.sign_in_error, Snackbar.LENGTH_LONG).show()
                                }
                            }
                        }
            }
        }
    }

    private fun isEmailAndPasswordValid(email: String, password: String): Boolean {
        var isValid = true

        if (!mLoginViewModel.isEmailValid(email)) {
            emailET.error = getString(R.string.email_error)
            isValid = false
        }

        if (password.length < 6) {
            passwordET.error = getString(R.string.password_error_too_short)
            isValid = false
        } else if (!mLoginViewModel.isValidPassword(password)) {
            passwordET.error = getString(R.string.password_error_wrong)
            isValid = false
        }

        return isValid
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                NavUtils.navigateUpFromSameTask(this@LoginActivity)
                true
            }
            else -> return super.onOptionsItemSelected(item)
        }
    }

    private fun showSignupSuccessfulDialog() = AlertDialog.Builder(this)
            .setTitle(R.string.sign_up_successful_dialog_title)
            .setMessage(R.string.sign_up_successful_dialog_message)
            .setCancelable(false)
            .setPositiveButton(getString(R.string.ok)) { dialog, _ ->
                dialog?.dismiss()
            }
            .create()
            .show()

    private fun showVerifyEmailDialog() = AlertDialog.Builder(this)
            .setTitle(R.string.verify_email_dialog_title)
            .setMessage(R.string.verify_email_dialog_message)
            .setCancelable(false)
            .setPositiveButton(getString(R.string.ok)) { dialog, _ ->
                dialog?.dismiss()
                mAuth.signOut()
            }
            .setNeutralButton(getString(R.string.verify_email_send_again)) { dialog, _ ->
                dialog.dismiss()
                mAuth.currentUser!!.sendEmailVerification()
                mAuth.signOut()
                Toast.makeText(this@LoginActivity, getString(R.string.message_sent), Toast.LENGTH_SHORT).show()
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
                if (!mLoginViewModel.isEmailValid(email)) {
                    view.resetPasswordET.error = getString(R.string.email_error)
                    return@setOnClickListener
                } else {
                    mAuth.sendPasswordResetEmail(email)
                            .addOnCompleteListener { task ->
                                if (task.isSuccessful) {
                                    dialog.dismiss()
                                    Snackbar.make(loginLayout, R.string.message_sent, Snackbar.LENGTH_LONG).show()
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
