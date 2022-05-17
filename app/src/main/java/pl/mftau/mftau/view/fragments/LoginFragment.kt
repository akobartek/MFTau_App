package pl.mftau.mftau.view.fragments

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException
import com.google.firebase.auth.FirebaseAuthInvalidUserException
import com.google.firebase.auth.FirebaseAuthUserCollisionException
import com.google.firebase.firestore.FirebaseFirestore
import pl.mftau.mftau.R
import pl.mftau.mftau.databinding.DialogResetPasswordBinding
import pl.mftau.mftau.databinding.FragmentLoginBinding
import pl.mftau.mftau.utils.FirestoreUtils.firestoreCollectionUsers
import pl.mftau.mftau.utils.createUnderlinedString
import pl.mftau.mftau.utils.hideKeyboard
import pl.mftau.mftau.utils.isValidEmail
import pl.mftau.mftau.utils.isValidPassword
import pl.mftau.mftau.view.ui.ClearErrorTextWatcher
import pl.mftau.mftau.viewmodel.MainViewModel

class LoginFragment : BindingFragment<FragmentLoginBinding>() {

    private lateinit var mViewModel: MainViewModel
    private lateinit var mAuth: FirebaseAuth
    private var isSigningUp: Boolean = false

    override fun attachBinding(inflater: LayoutInflater, container: ViewGroup?) =
        FragmentLoginBinding.inflate(inflater, container, false)

    override fun setup(savedInstanceState: Bundle?) {
        binding.loginToolbar.setNavigationOnClickListener { findNavController().navigateUp() }

        activity?.let { mViewModel = ViewModelProvider(it)[MainViewModel::class.java] }
        mAuth = FirebaseAuth.getInstance()
        setViewsListeners()

        with(binding.contentLogin) {
            forgotPasswordTV.text = forgotPasswordTV.text.toString().createUnderlinedString()
            createAccountTV.text = createAccountTV.text.toString().createUnderlinedString()
        }
    }

    private fun setViewsListeners() {
        with(binding.contentLogin) {
            forgotPasswordTV.setOnClickListener { showResetPasswordDialog() }
            backToSignInTV.setOnClickListener { setSignUpViewVisible(false) }
            createAccountTV.setOnClickListener { setSignUpViewVisible(true) }

            loginBtn.setOnClickListener {
                loginBtn.isEnabled = false
                val email = emailET.text.toString().trim()
                val password = passwordET.text.toString().trim()

                if (isSigningUp) {
                    if (!isEmailAndPasswordValid(email, password)) {
                        loginBtn.isEnabled = true
                        return@setOnClickListener
                    }

                    mAuth.createUserWithEmailAndPassword(email, password)
                        .addOnCompleteListener(requireActivity()) { task ->
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
                                    emailInputLayout.apply {
                                        error = getString(R.string.sign_up_existing_user_error)
                                        requestFocus()
                                    }
                                } else
                                    Snackbar.make(
                                        loginLayout,
                                        R.string.sign_up_error,
                                        Snackbar.LENGTH_LONG
                                    ).show()
                            }
                            loginBtn.isEnabled = true
                        }
                } else {
                    if (isEmailOrPasswordNull(email, password)) {
                        loginBtn.isEnabled = true
                        return@setOnClickListener
                    }

                    mAuth.signInWithEmailAndPassword(email, password)
                        .addOnCompleteListener(requireActivity()) { task ->
                            if (task.isSuccessful) {
                                if (!mAuth.currentUser!!.isEmailVerified && mAuth.currentUser!!.email != "example@mftau.pl") {
                                    showVerifyEmailDialog()
                                    loginBtn.isEnabled = true
                                } else {
                                    Toast.makeText(context, R.string.signed_in, Toast.LENGTH_SHORT)
                                        .show()
                                    findNavController().navigateUp()
                                }
                            } else {
                                Log.d("SignInFailed", task.exception.toString())
                                when (task.exception) {
                                    is FirebaseAuthInvalidUserException -> {
                                        emailInputLayout.apply {
                                            error = getString(R.string.sign_in_no_user_error)
                                            requestFocus()
                                        }
                                    }
                                    is FirebaseAuthInvalidCredentialsException -> {
                                        passwordInputLayout.apply {
                                            error = getString(R.string.sign_in_wrong_password_error)
                                            requestFocus()
                                        }
                                    }
                                    else -> Snackbar.make(
                                        loginLayout,
                                        R.string.sign_in_error,
                                        Snackbar.LENGTH_LONG
                                    ).show()
                                }
                                loginBtn.isEnabled = true
                            }
                        }
                }
            }

            loginLayout.setOnClickListener { requireActivity().hideKeyboard() }
            logo.setOnClickListener { requireActivity().hideKeyboard() }

            emailET.addTextChangedListener(ClearErrorTextWatcher(emailInputLayout))
            passwordET.addTextChangedListener(ClearErrorTextWatcher(passwordInputLayout))
        }
    }

    private fun setSignUpViewVisible(isSigningUp: Boolean) {
        this.isSigningUp = isSigningUp
        with(binding.contentLogin) {
            if (isSigningUp) {
                loginBtn.text = getString(R.string.sign_up)
                forgotPasswordTV.animate()
                    .alpha(0f)
                    .withEndAction { forgotPasswordTV.visibility = View.INVISIBLE }
                    .duration = 300
                createAccountTV.animate()
                    .alpha(0f)
                    .withEndAction { createAccountTV.visibility = View.INVISIBLE }
                    .duration = 300
                backToSignInTV.animate()
                    .alpha(1f)
                    .withStartAction {
                        backToSignInTV.visibility = View.VISIBLE
                        backToSignInTV.alpha = 0f
                    }
                    .duration = 300
            } else {
                loginBtn.text = getString(R.string.sign_in)
                forgotPasswordTV.animate()
                    .alpha(1f)
                    .withStartAction {
                        forgotPasswordTV.visibility = View.VISIBLE
                        forgotPasswordTV.alpha = 0f
                    }
                    .duration = 300
                createAccountTV.animate()
                    .alpha(1f)
                    .withStartAction {
                        createAccountTV.visibility = View.VISIBLE
                        createAccountTV.alpha = 0f
                    }
                    .duration = 300
                backToSignInTV.animate()
                    .alpha(0f)
                    .withEndAction { backToSignInTV.visibility = View.INVISIBLE }
                    .duration = 300
            }

            emailInputLayout.error = null
            passwordInputLayout.error = null
        }
    }

    private fun isEmailOrPasswordNull(email: String, password: String): Boolean {
        var isNull = false

        if (!email.isValidEmail()) {
            binding.contentLogin.emailInputLayout.error = getString(R.string.email_error)
            isNull = true
        }
        if (password.isEmpty()) {
            binding.contentLogin.passwordInputLayout.error =
                getString(R.string.password_error_empty)
            isNull = true
        }
        return isNull
    }

    private fun isEmailAndPasswordValid(email: String, password: String): Boolean {
        var isValid = true

        if (!email.isValidEmail()) {
            binding.contentLogin.emailInputLayout.error = getString(R.string.email_error)
            isValid = false
        }
        if (password.length < 6) {
            binding.contentLogin.passwordInputLayout.error =
                getString(R.string.password_error_too_short)
            isValid = false
        } else if (!password.isValidPassword()) {
            binding.contentLogin.passwordInputLayout.error =
                getString(R.string.password_error_wrong)
            isValid = false
        }
        return isValid
    }

    private fun showSignupSuccessfulDialog() {
        MaterialAlertDialogBuilder(requireContext())
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
        MaterialAlertDialogBuilder(requireContext())
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
                Toast.makeText(
                    requireContext(),
                    getString(R.string.message_sent),
                    Toast.LENGTH_SHORT
                ).show()
            }
            .create()
            .show()
    }

    @SuppressLint("InflateParams")
    private fun showResetPasswordDialog() {
        val dialogBinding = DialogResetPasswordBinding.inflate(layoutInflater)
        val dialog = MaterialAlertDialogBuilder(requireContext())
            .setTitle(R.string.reset_password_dialog_title)
            .setMessage(R.string.reset_password_dialog_message)
            .setView(dialogBinding.root)
            .setCancelable(false)
            .setPositiveButton(getString(R.string.send), null)
            .setNegativeButton(getString(R.string.cancel)) { dialog, _ -> dialog?.dismiss() }
            .create()
        dialog.window?.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN)
        dialog.setOnShowListener {
            dialog.getButton(AlertDialog.BUTTON_POSITIVE).setOnClickListener {
                val email = dialogBinding.resetPasswordET.text.toString().trim()
                if (!email.isValidEmail()) {
                    dialogBinding.resetPasswordInputLayout.error = getString(R.string.email_error)
                    return@setOnClickListener
                } else {
                    mAuth.sendPasswordResetEmail(email).addOnCompleteListener { task ->
                        if (task.isSuccessful) {
                            dialog.dismiss()
                            Snackbar.make(
                                binding.contentLogin.loginLayout,
                                R.string.message_sent,
                                Snackbar.LENGTH_LONG
                            ).show()
                        } else {
                            dialogBinding.resetPasswordInputLayout.error =
                                getString(R.string.reset_password_error)
                        }
                    }
                }
            }
        }
        dialog.show()
    }
}
