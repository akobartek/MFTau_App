package pl.mftau.mftau.view.fragments

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.text.SpannableString
import android.text.Spanned
import android.text.TextPaint
import android.text.method.LinkMovementMethod
import android.text.style.ClickableSpan
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.navigation.fragment.findNavController
import pl.mftau.mftau.R
import pl.mftau.mftau.databinding.FragmentEmailBinding
import pl.mftau.mftau.utils.*

class EmailFragment : BindingFragment<FragmentEmailBinding>() {

    private lateinit var mMailType: String

    private val emailAddresses = arrayOf("modlitwa@mftau.pl", "sokolowskijbartek@gmail.com")

    override fun attachBinding(inflater: LayoutInflater, container: ViewGroup?) =
        FragmentEmailBinding.inflate(inflater, container, false)

    override fun setup(savedInstanceState: Bundle?) {
        binding.emailToolbar.setNavigationOnClickListener { findNavController().navigateUp() }

        arguments?.let {
            mMailType = EmailFragmentArgs.fromBundle(it).emailType
            binding.emailToolbarTitle.text = when (mMailType) {
                "pray" -> getString(R.string.ask_for_pray)
                "error" -> {
                    binding.contentEmail.emailIntentionInputLayout.hint =
                        getString(R.string.error_description)
                    getString(R.string.report_error)
                }
                else -> ""
            }
        }
        activity?.let {
            it.title =
                if (mMailType == "error") getString(R.string.report_error) else getString(R.string.ask_for_pray)
        }

        binding.sendMailBtn.setOnClickListener {
            if (binding.contentEmail.rodoCheckBox.isChecked) sendMail()
            else showRodoDialog()
        }
        binding.contentEmail.emailLayout.setOnClickListener { requireActivity().hideKeyboard() }


        val rodoSrodo = getString(R.string.rodo_srodo)
        val privacyPolicy = getString(R.string.privacy_policy)
        val ss = SpannableString(rodoSrodo)
        val clickableSpan = object : ClickableSpan() {
            override fun onClick(textView: View) {
                if (requireContext().isChromeCustomTabsSupported()) {
                    requireContext().openWebsiteInChromeCustomTabs("http://mftau.pl/polityka-prywatnosci/")
                } else {
                    findNavController().navigate(MainFragmentDirections.showWebsiteFragment("http://mftau.pl/polityka-prywatnosci/"))
                }
            }

            override fun updateDrawState(ds: TextPaint) {
                super.updateDrawState(ds)
                ds.isUnderlineText = true
            }
        }
        val indexOfPolicy = rodoSrodo.indexOf(privacyPolicy)
        ss.setSpan(
            clickableSpan,
            indexOfPolicy,
            indexOfPolicy + privacyPolicy.length,
            Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
        )

        binding.contentEmail.privacyPolicyTV.text = ss
        binding.contentEmail.privacyPolicyTV.movementMethod = LinkMovementMethod.getInstance()
    }

    private fun showRodoDialog() =
        AlertDialog.Builder(requireContext())
            .setMessage(R.string.rodo_dialog_message)
            .setCancelable(false)
            .setPositiveButton(R.string.ok) { dialog, _ ->
                dialog.dismiss()
            }
            .create()
            .show()

    private fun sendMail() {
        var isValid = true

        val name = binding.contentEmail.emailNameET.text
        if (name.isNullOrEmpty()) {
            binding.contentEmail.emailNameET.error = getString(R.string.empty_email_name_error)
            isValid = false
        }
        val emailAddress = binding.contentEmail.emailAdressET.text.toString().trim()
        if (!emailAddress.isValidEmail()) {
            binding.contentEmail.emailAdressET.error = getString(R.string.email_error)
            isValid = false
        }
        val intention = binding.contentEmail.emailIntentionET.text
        if (intention.isNullOrEmpty()) {
            binding.contentEmail.emailIntentionET.error =
                getString(R.string.empty_email_intention_error)
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
        emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Wiadomość od ${name.toString().trim()}")
        emailIntent.putExtra(Intent.EXTRA_TEXT, emailTextBuilder.toString())

        try {
            startActivity(Intent.createChooser(emailIntent, getString(R.string.send_mail)))
        } catch (ex: android.content.ActivityNotFoundException) {
            Toast.makeText(context, getString(R.string.send_mail_error), Toast.LENGTH_SHORT).show()
        }
    }
}
