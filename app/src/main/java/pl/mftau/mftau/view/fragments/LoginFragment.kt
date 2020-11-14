package pl.mftau.mftau.view.fragments


import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException
import com.google.firebase.auth.FirebaseAuthInvalidUserException
import com.google.firebase.auth.FirebaseAuthUserCollisionException
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.content_login.view.*
import kotlinx.android.synthetic.main.dialog_reset_password.view.*
import kotlinx.android.synthetic.main.fragment_login.view.*
import pl.mftau.mftau.R
import pl.mftau.mftau.utils.FirestoreUtils.firestoreCollectionUsers
import pl.mftau.mftau.utils.createUnderlinedString
import pl.mftau.mftau.utils.hideKeyboard
import pl.mftau.mftau.utils.isValidEmail
import pl.mftau.mftau.utils.isValidPassword
import pl.mftau.mftau.viewmodel.MainViewModel

class LoginFragment : Fragment() {

    private lateinit var mViewModel: MainViewModel
    private lateinit var mAuth: FirebaseAuth
    private var isSigningUp: Boolean = false

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.fragment_login, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        view.loginToolbar.setNavigationOnClickListener { findNavController().navigateUp() }

        activity?.let { mViewModel = ViewModelProvider(it).get(MainViewModel::class.java) }
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
            requireView().loginBtn.isEnabled = false
            val email = requireView().emailET.text.toString().trim()
            val password = requireView().passwordET.text.toString().trim()

            if (isSigningUp) {
                if (!isEmailAndPasswordValid(email, password)) {
                    requireView().loginBtn.isEnabled = true
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
                                requireView().emailET.apply {
                                    error = getString(R.string.sign_up_existing_user_error)
                                    requestFocus()
                                }
                            } else
                                Snackbar.make(
                                    requireView().loginLayout,
                                    R.string.sign_up_error,
                                    Snackbar.LENGTH_LONG
                                ).show()
                        }
                        requireView().loginBtn.isEnabled = true
                    }
            } else {
                if (isEmailOrPasswordNull(email, password)) {
                    requireView().loginBtn.isEnabled = true
                    return@setOnClickListener
                }

                mAuth.signInWithEmailAndPassword(email, password)
                    .addOnCompleteListener(requireActivity()) { task ->
                        if (task.isSuccessful) {
                            if (!mAuth.currentUser!!.isEmailVerified) {
                                showVerifyEmailDialog()
                                requireView().loginBtn.isEnabled = true
                            } else {
                                Toast.makeText(context, R.string.signed_in, Toast.LENGTH_SHORT)
                                    .show()
                                findNavController().navigateUp()
                            }
                        } else {
                            Log.d("SignInFailed", task.exception.toString())
                            when (task.exception) {
                                is FirebaseAuthInvalidUserException -> {
                                    requireView().emailET.apply {
                                        error = getString(R.string.sign_in_no_user_error)
                                        requestFocus()
                                    }
                                }
                                is FirebaseAuthInvalidCredentialsException -> {
                                    requireView().passwordET.apply {
                                        error = getString(R.string.sign_in_wrong_password_error)
                                        requestFocus()
                                    }
                                }
                                else -> Snackbar.make(
                                    requireView().loginLayout,
                                    R.string.sign_in_error,
                                    Snackbar.LENGTH_LONG
                                ).show()
                            }
                            requireView().loginBtn.isEnabled = true
                        }
                    }
            }
        }

        view?.loginLayout?.setOnClickListener { requireActivity().hideKeyboard() }
        view?.logo?.setOnClickListener { requireActivity().hideKeyboard() }
    }

    private fun setSignUpViewVisible(boolean: Boolean) {
        isSigningUp = boolean
        if (boolean) {
            with(requireView()) {
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
            }
        } else {
            with(requireView()) {
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
        }
        with(requireView()) {
            emailET.error = null
            passwordET.error = null
        }
    }

    private fun isEmailOrPasswordNull(email: String, password: String): Boolean {
        var isNull = false

        if (!email.isValidEmail()) {
            requireView().emailET.error = getString(R.string.email_error)
            isNull = true
        }
        if (password.isEmpty()) {
            requireView().passwordET.error = getString(R.string.password_error_empty)
            isNull = true
        }
        return isNull
    }

    private fun isEmailAndPasswordValid(email: String, password: String): Boolean {
        var isValid = true

        if (!email.isValidEmail()) {
            requireView().emailET.error = getString(R.string.email_error)
            isValid = false
        }
        if (password.length < 6) {
            requireView().passwordET.error = getString(R.string.password_error_too_short)
            isValid = false
        } else if (!password.isValidPassword()) {
            requireView().passwordET.error = getString(R.string.password_error_wrong)
            isValid = false
        }
        return isValid
    }

    private fun showSignupSuccessfulDialog() {
        AlertDialog.Builder(requireContext())
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
        AlertDialog.Builder(requireContext())
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
                )
                    .show()
            }
            .create()
            .show()
    }

    @SuppressLint("InflateParams")
    private fun showResetPasswordDialog() {
        val dialogView = LayoutInflater.from(context).inflate(R.layout.dialog_reset_password, null)

        val dialog = AlertDialog.Builder(requireContext())
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
                if (!email.isValidEmail()) {
                    dialogView.resetPasswordET.error = getString(R.string.email_error)
                    return@setOnClickListener
                } else {
                    mAuth.sendPasswordResetEmail(email)
                        .addOnCompleteListener { task ->
                            if (task.isSuccessful) {
                                dialog.dismiss()
                                Snackbar.make(
                                    requireView().loginLayout,
                                    R.string.message_sent,
                                    Snackbar.LENGTH_LONG
                                ).show()
                            } else {
                                dialogView.resetPasswordET.error =
                                    getString(R.string.reset_password_error)
                            }
                        }
                }
            }
        }
        dialog.show()
    }
}
