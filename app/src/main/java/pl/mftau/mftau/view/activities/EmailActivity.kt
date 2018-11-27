package pl.mftau.mftau.view.activities

import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.net.Uri
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.lifecycle.ViewModelProviders
import androidx.preference.PreferenceManager
import kotlinx.android.synthetic.main.activity_email.*
import pl.mftau.mftau.R
import pl.mftau.mftau.viewmodel.EmailViewModel
import java.lang.StringBuilder
import android.widget.Toast


class EmailActivity : AppCompatActivity() {

    private lateinit var mEmailViewModel: EmailViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        if (PreferenceManager.getDefaultSharedPreferences(this@EmailActivity)
                        .getBoolean(getString(R.string.night_mode_key), false)) {
            setTheme(R.style.AppTheme_Dark)
        } else {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
                window.statusBarColor = Color.WHITE
            }
        }

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_email)
        setSupportActionBar(emailToolbar)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        supportActionBar!!.setHomeAsUpIndicator(R.drawable.ic_arrow_back)

        mEmailViewModel = ViewModelProviders.of(this@EmailActivity).get(EmailViewModel::class.java)
        mEmailViewModel.mailType = intent.getStringExtra("email")

        title = when {
            mEmailViewModel.mailType == "pray" -> getString(R.string.ask_for_pray)
            mEmailViewModel.mailType == "error" -> {
                emailIntentionInputLayout.hint = getString(R.string.error_description)
                getString(R.string.report_error)
            }
            else -> ""
        }

        sendMailBtn.setOnClickListener {
            sendMail()
        }
        emailLayout.setOnClickListener {
            (getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager)
                    .hideSoftInputFromWindow(currentFocus!!.windowToken, 0)
        }
    }

    override fun onBackPressed() {
        startActivity(Intent(this@EmailActivity, MainActivity::class.java))
        finish()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                onBackPressed()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun sendMail() {
        var isValid = true

        val name = emailNameET.text
        if (name.isNullOrEmpty()) {
            emailNameET.error = getString(R.string.empty_email_name_error)
            isValid = false
        }
        val emailAddress = emailAdressET.text.toString().trim()
        if (!mEmailViewModel.isEmailValid(emailAddress)) {
            emailAdressET.error = getString(R.string.email_error)
            isValid = false
        }
        val intention = emailIntentionET.text
        if (intention.isNullOrEmpty()) {
            emailIntentionET.error = getString(R.string.empty_email_intention_error)
            isValid = false
        }

        if (!isValid) return

        val emailTextBuilder = StringBuilder()
        emailTextBuilder.append("Nadawca: $emailAddress\n\n")
        emailTextBuilder.append(intention.toString().trim())

        val emailTo = when {
            mEmailViewModel.mailType == "pray" -> mEmailViewModel.emailAddresses[0]
            mEmailViewModel.mailType == "error" -> mEmailViewModel.emailAddresses[1]
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
            finish()
        } catch (ex: android.content.ActivityNotFoundException) {
            Toast.makeText(this@EmailActivity, getString(R.string.send_mail_error), Toast.LENGTH_SHORT).show()
        }
    }
}
