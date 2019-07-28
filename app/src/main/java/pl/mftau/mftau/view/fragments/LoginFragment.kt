package pl.mftau.mftau.view.fragments


import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException
import com.google.firebase.auth.FirebaseAuthInvalidUserException
import com.google.firebase.auth.FirebaseAuthUserCollisionException
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.dialog_reset_password.view.*
import kotlinx.android.synthetic.main.fragment_login.view.*
import pl.mftau.mftau.R
import pl.mftau.mftau.utils.FirestoreUtils.firestoreCollectionUsers
import pl.mftau.mftau.utils.createUnderlinedString
import pl.mftau.mftau.viewmodel.MainViewModel

class LoginFragment : Fragment() {

    private lateinit var mViewModel: MainViewModel
    private lateinit var mAuth: FirebaseAuth
    private var isSigningUp: Boolean = false

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? =
        inflater.inflate(R.layout.fragment_login, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        activity?.let { mViewModel = ViewModelProviders.of(it).get(MainViewModel::class.java) }
        mAuth = FirebaseAuth.getInstance()
        setOnClickListeners()

        view.forgotPasswordTV.text = view.forgotPasswordTV.text.toString().createUnderlinedString()
        view.createAccountTV.text = view.createAccountTV.text.toString().createUnderlinedString()
    }

    private fun setOnClickListeners() {
        view?.forgotPasswordTV?.setOnClickListener { showResetPasswordDialog() }

        view?.backToSignInTV?.setOnClickListener { setSignUpViewVisible(false) }

        view?.createAccountTV?.setOnClickListener { setSignUpViewVisible(true) }

        view?.loginBtn?.setOnClickListener {
            view!!.loginBtn.isEnabled = false
            val email = view!!.emailET.text.toString().trim()
            val password = view!!.passwordET.text.toString().trim()

            if (isSigningUp) {
                if (!isEmailAndPasswordValid(email, password)) {
                    view!!.loginBtn.isEnabled = true
                    return@setOnClickListener
                }

                mAuth.createUserWithEmailAndPassword(email, password)
                    .addOnCompleteListener(activity!!) { task ->
                        if (task.isSuccessful) {
                            mAuth.currentUser?.sendEmailVerification()

                            FirebaseFirestore.getInstance().collection(firestoreCollectionUsers)
                                .document(mAuth.currentUser!!.uid)
                                .set(mViewModel.createUserValues(email))

                            mAuth.signOut()
                            setSignUpViewVisible(false)
                            showSignupSuccessfulDialog()
                        } else {
                            Log.d("SignUpFailed", task.exception!!.toString())
                            if (task.exception!! is FirebaseAuthUserCollisionException) {
                                view!!.emailET.error = getString(R.string.sign_up_existing_user_error)
                                view!!.emailET.requestFocus()
                            } else
                                Snackbar.make(view!!.loginLayout, R.string.sign_up_error, Snackbar.LENGTH_LONG).show()
                        }
                        view!!.loginBtn.isEnabled = true
                    }
            } else {
                if (isEmailOrPasswordNull(email, password)) {
                    view!!.loginBtn.isEnabled = true
                    return@setOnClickListener
                }

                mAuth.signInWithEmailAndPassword(email, password)
                    .addOnCompleteListener(activity!!) { task ->
                        if (task.isSuccessful) {
                            if (!mAuth.currentUser!!.isEmailVerified) {
                                showVerifyEmailDialog()
                                view!!.loginBtn.isEnabled = true
                            } else {
                                Toast.makeText(context, R.string.signed_in, Toast.LENGTH_SHORT).show()
                                findNavController().navigateUp()
                            }
                        } else {
                            Log.d("SignInFailed", task.exception.toString())
                            when {
                                task.exception is FirebaseAuthInvalidUserException -> {
                                    view!!.emailET.error = getString(R.string.sign_in_no_user_error)
                                    view!!.emailET.requestFocus()
                                }
                                task.exception is FirebaseAuthInvalidCredentialsException -> {
                                    view!!.passwordET.error = getString(R.string.sign_in_wrong_password_error)
                                    view!!.passwordET.requestFocus()
                                }
                                else -> Snackbar.make(
                                    view!!.loginLayout,
                                    R.string.sign_in_error,
                                    Snackbar.LENGTH_LONG
                                ).show()
                            }
                            view!!.loginBtn.isEnabled = true
                        }
                    }
            }
        }

        view?.loginLayout?.setOnClickListener {
            (it.context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager)
                .hideSoftInputFromWindow(activity?.currentFocus?.windowToken, 0)
        }
        view?.logo?.setOnClickListener {
            (it.context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager)
                .hideSoftInputFromWindow(activity?.currentFocus?.windowToken, 0)
        }
    }

    private fun setSignUpViewVisible(boolean: Boolean) {
        isSigningUp = boolean
        if (boolean) {
            view!!.loginBtn.text = getString(R.string.sign_up)
            view!!.forgotPasswordTV.animate()
                .alpha(0f)
                .withEndAction { view!!.forgotPasswordTV.visibility = View.INVISIBLE }
                .duration = 300
            view!!.createAccountTV.animate()
                .alpha(0f)
                .withEndAction { view!!.createAccountTV.visibility = View.INVISIBLE }
                .duration = 300
            view!!.backToSignInTV.animate()
                .alpha(1f)
                .withStartAction {
                    view!!.backToSignInTV.visibility = View.VISIBLE
                    view!!.backToSignInTV.alpha = 0f
                }
                .duration = 300
        } else {
            view!!.loginBtn.text = getString(R.string.sign_in)
            view!!.forgotPasswordTV.animate()
                .alpha(1f)
                .withStartAction {
                    view!!.forgotPasswordTV.visibility = View.VISIBLE
                    view!!.forgotPasswordTV.alpha = 0f
                }
                .duration = 300
            view!!.createAccountTV.animate()
                .alpha(1f)
                .withStartAction {
                    view!!.createAccountTV.visibility = View.VISIBLE
                    view!!.createAccountTV.alpha = 0f
                }
                .duration = 300
            view!!.backToSignInTV.animate()
                .alpha(0f)
                .withEndAction { view!!.backToSignInTV.visibility = View.INVISIBLE }
                .duration = 300
        }
        view!!.emailET.error = null
        view!!.passwordET.error = null
    }

    private fun isEmailOrPasswordNull(email: String, password: String): Boolean {
        var isNull = false

        if (!mViewModel.isEmailValid(email)) {
            view!!.emailET.error = getString(R.string.email_error)
            isNull = true
        }
        if (password.isEmpty()) {
            view!!.passwordET.error = getString(R.string.password_error_empty)
            isNull = true
        }
        return isNull
    }

    private fun isEmailAndPasswordValid(email: String, password: String): Boolean {
        var isValid = true

        if (!mViewModel.isEmailValid(email)) {
            view!!.emailET.error = getString(R.string.email_error)
            isValid = false
        }
        if (password.length < 6) {
            view!!.passwordET.error = getString(R.string.password_error_too_short)
            isValid = false
        } else if (!mViewModel.isValidPassword(password)) {
            view!!.passwordET.error = getString(R.string.password_error_wrong)
            isValid = false
        }
        return isValid
    }

    private fun showSignupSuccessfulDialog() {
        AlertDialog.Builder(context!!)
            .setTitle(R.string.sign_up_successful_dialog_title)
            .setMessage(R.string.sign_up_successful_dialog_message)
            .setCancelable(false)
            .setPositiveButton(getString(R.string.ok)) { dialog, _ ->
                dialog?.dismiss()
                setSignUpViewVisible(false)
            }
            .create()
            .show()
    }

    private fun showVerifyEmailDialog() {
        AlertDialog.Builder(context!!)
            .setTitle(R.string.verify_email_dialog_title)
            .setMessage(R.string.verify_email_dialog_message)
            .setCancelable(false)
            .setPositiveButton(getString(R.string.ok)) { dialog, _ ->
                dialog?.dismiss()
                mAuth.signOut()
            }
            .setNeutralButton(getString(R.string.verify_email_send_again)) { dialog, _ ->
                dialog.dismiss()
                mAuth.currentUser?.sendEmailVerification()
                mAuth.signOut()
                Toast.makeText(context!!, getString(R.string.message_sent), Toast.LENGTH_SHORT).show()
            }
            .create()
            .show()
    }

    @SuppressLint("InflateParams")
    private fun showResetPasswordDialog() {
        val dialogView = LayoutInflater.from(context).inflate(R.layout.dialog_reset_password, null)

        val dialog = AlertDialog.Builder(context!!)
            .setTitle(R.string.reset_password_dialog_title)
            .setMessage(R.string.reset_password_dialog_message)
            .setView(dialogView)
            .setCancelable(false)
            .setPositiveButton(getString(R.string.send), null)
            .setNegativeButton(getString(R.string.cancel)) { dialog, _ -> dialog?.dismiss() }
            .create()

        dialog.setOnShowListener {
            dialog.getButton(AlertDialog.BUTTON_POSITIVE).setOnClickListener {
                val email = dialogView.resetPasswordET.text.toString().trim()
                if (!mViewModel.isEmailValid(email)) {
                    dialogView.resetPasswordET.error = getString(R.string.email_error)
                    return@setOnClickListener
                } else {
                    mAuth.sendPasswordResetEmail(email)
                        .addOnCompleteListener { task ->
                            if (task.isSuccessful) {
                                dialog.dismiss()
                                Snackbar.make(view!!.loginLayout, R.string.message_sent, Snackbar.LENGTH_LONG).show()
                            } else {
                                dialogView.resetPasswordET.error = getString(R.string.reset_password_error)
                            }
                        }
                }
            }
        }
        dialog.show()
    }
}
