package pl.mftau.mftau.view.fragments


import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.lifecycle.ViewModelProviders
import kotlinx.android.synthetic.main.fragment_email.view.*
import pl.mftau.mftau.R
import pl.mftau.mftau.viewmodel.MainViewModel

class EmailFragment : Fragment() {

    private lateinit var mViewModel: MainViewModel
    private lateinit var mMailType: String

    private val emailAddresses = arrayOf("modlitwa@mftau.pl", "sokolowskijbartek@gmail.com")

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_email, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        arguments?.let {
            mMailType = EmailFragmentArgs.fromBundle(it).emailType
            if (mMailType == "error") {
                view.emailIntentionInputLayout.hint = getString(R.string.error_description)
            }
        }
        activity?.let {
            ViewModelProviders.of(it).get(MainViewModel::class.java)
            it.title = if (mMailType == "error") getString(R.string.report_error) else getString(R.string.ask_for_pray)
        }

        view.sendMailBtn?.setOnClickListener {
            sendMail()
        }
        view.emailLayout.setOnClickListener {
            (view.context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager)
                    .hideSoftInputFromWindow(activity?.currentFocus?.windowToken, 0)
        }
    }

    private fun sendMail() {
        var isValid = true

        val name = view?.emailNameET?.text
        if (name.isNullOrEmpty()) {
            view?.emailNameET?.error = getString(R.string.empty_email_name_error)
            isValid = false
        }
        val emailAddress = view?.emailAdressET?.text.toString().trim()
        if (!mViewModel.isEmailValid(emailAddress)) {
            view?.emailAdressET?.error = getString(R.string.email_error)
            isValid = false
        }
        val intention = view?.emailIntentionET?.text
        if (intention.isNullOrEmpty()) {
            view?.emailIntentionET?.error = getString(R.string.empty_email_intention_error)
            isValid = false
        }

        if (!isValid) return

        val emailTextBuilder = StringBuilder()
        emailTextBuilder.append("Nadawca: $emailAddress\n\n")
        emailTextBuilder.append(intention.toString().trim())

        val emailTo = when (mMailType) {
            "pray" -> emailAddresses[0]
            "error" -> emailAddresses[1]
            else -> ""
        }

        val emailIntent = Intent(Intent.ACTION_SEND)
        emailIntent.data = Uri.parse("mailto:")
        emailIntent.type = "message/rfc822"
        emailIntent.putExtra(Intent.EXTRA_EMAIL, arrayOf(emailTo))
        emailIntent.putExtra(Intent.EXTRA_SUBJECT, "<MF Tau App> Wiadomość od ${name.toString().trim()}")
        emailIntent.putExtra(Intent.EXTRA_TEXT, emailTextBuilder.toString())

        try {
            startActivity(Intent.createChooser(emailIntent, getString(R.string.send_mail)))
        } catch (ex: android.content.ActivityNotFoundException) {
            Toast.makeText(context, getString(R.string.send_mail_error), Toast.LENGTH_SHORT).show()
        }
    }
}
