package pl.mftau.mftau.view.activities

import android.Manifest
import android.annotation.SuppressLint
import android.app.Activity
import android.content.ContentValues
import android.graphics.Color
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.lifecycle.ViewModelProviders
import androidx.preference.PreferenceManager
import kotlinx.android.synthetic.main.activity_retreat_details.*
import pl.mftau.mftau.R
import pl.mftau.mftau.viewmodel.RetreatDetailsViewModel
import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import kotlinx.android.synthetic.main.dialog_retreat_register.view.*
import pl.mftau.mftau.viewmodel.MainViewModel
import java.util.*
import android.provider.CalendarContract.Events
import android.content.pm.PackageManager
import android.provider.CalendarContract
import androidx.core.content.ContextCompat
import androidx.core.app.ActivityCompat


class RetreatDetailsActivity : AppCompatActivity() {

    private lateinit var mRetreatDetailsViewModel: RetreatDetailsViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        if (PreferenceManager.getDefaultSharedPreferences(this@RetreatDetailsActivity)
                        .getBoolean(getString(R.string.night_mode_key), false)) {
            setTheme(R.style.AppTheme_Dark)
        } else {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
                window.statusBarColor = Color.WHITE
            }
        }

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_retreat_details)

        setSupportActionBar(retreatDetailsToolbar)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        supportActionBar!!.setHomeAsUpIndicator(R.drawable.ic_arrow_back)

        mRetreatDetailsViewModel = ViewModelProviders.of(this@RetreatDetailsActivity).get(RetreatDetailsViewModel::class.java)

        mRetreatDetailsViewModel.retreat = intent.getParcelableExtra("retreat")
        title = mRetreatDetailsViewModel.retreat!!.name

        retreatNameET.setText(mRetreatDetailsViewModel.retreat!!.name)
        retreatCityET.setText(mRetreatDetailsViewModel.retreat!!.city)
        retreatAddressET.setText(mRetreatDetailsViewModel.retreat!!.address)
        retreatPriceET.setText(mRetreatDetailsViewModel.getPriceFormatted())
        beginDateText.setText(mRetreatDetailsViewModel.getDateFormatted(mRetreatDetailsViewModel.retreat!!.beginDate.toDate()))
        endDateText.setText(mRetreatDetailsViewModel.getDateFormatted(mRetreatDetailsViewModel.retreat!!.endDate.toDate()))

        if (mRetreatDetailsViewModel.retreat!!.registerLimitDate.toDate() < Date())
            registerBtn.hide()

        registerBtn.setOnClickListener {
            showRegisterDialog()
        }
    }

    override fun onBackPressed() {
        setResult(Activity.RESULT_OK, Intent()
                .putExtra("userType", intent.getIntExtra("userType", MainViewModel.USER_TYPE_NONE)))
        finish()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_retreats, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_show_map -> {
                val gmmIntentUri = Uri.parse("geo:0,0?q=" + Uri.encode(
                        "${retreatAddressET.text.toString()} ${retreatCityET.text.toString()}"))
                val mapIntent = Intent(Intent.ACTION_VIEW, gmmIntentUri)
                mapIntent.setPackage("com.google.android.apps.maps")
                startActivity(mapIntent)
                true
            }
            R.id.action_save_to_calendar -> {
                if (haveCalendarReadWritePermissions())
                    showSaveToCalendarDialog()
                else
                    requestCalendarReadWritePermission()
                true
            }
            android.R.id.home -> {
                onBackPressed()
                true
            }
            else -> return super.onOptionsItemSelected(item)
        }
    }

    @SuppressLint("InflateParams")
    private fun showRegisterDialog() {
        val view = LayoutInflater.from(this@RetreatDetailsActivity).inflate(R.layout.dialog_retreat_register, null)

        val dialog = AlertDialog.Builder(this)
                .setTitle(R.string.retreat_register_title)
                .setMessage(R.string.retreat_register_msg)
                .setView(view)
                .setCancelable(false)
                .setPositiveButton(getString(R.string.send), null)
                .setNegativeButton(getString(R.string.cancel)) { dialog, _ -> dialog.dismiss() }
                .create()

        dialog.setOnShowListener {
            (dialog as AlertDialog).getButton(AlertDialog.BUTTON_POSITIVE).setOnClickListener {
                if (view.registerNameET.text.isNullOrBlank()) {
                    view.registerNameET.error = getString(R.string.empty_name_error)
                    return@setOnClickListener
                } else {
                    if (mRetreatDetailsViewModel.retreat!!.advancePayment) {
                        showAdvancePaymentDialog(view.registerNameET.text.toString().trim())
                    } else {
                        sendRegisterEmail(view.registerNameET.text.toString().trim())
                    }
                }
            }
        }

        dialog.show()
    }

    private fun showAdvancePaymentDialog(name: String) =
            AlertDialog.Builder(this)
                    .setTitle(R.string.advance_payment_title)
                    .setMessage(R.string.advance_payment_msg)
                    .setCancelable(false)
                    .setPositiveButton(getString(R.string.ok)) { dialog, _ ->
                        dialog.dismiss()
                        sendRegisterEmail(name)
                    }
                    .create()
                    .show()

    private fun sendRegisterEmail(name: String) {
        val emailIntent = Intent(Intent.ACTION_SEND)
        emailIntent.data = Uri.parse("mailto:")
        emailIntent.type = "message/rfc822"
        emailIntent.putExtra(Intent.EXTRA_EMAIL, arrayOf("rada@mftau.pl"))
        emailIntent.putExtra(Intent.EXTRA_SUBJECT,
                "<MF Tau App> Zapisy na rekolekcje ${mRetreatDetailsViewModel.retreat!!.name}")
        emailIntent.putExtra(Intent.EXTRA_TEXT, "Pokój i dobro!\n" +
                "Chciałbym zgłosić chęć uczestnictwa w rekolekcjach ${mRetreatDetailsViewModel.retreat!!.name}.\n\n" +
                "Pozdrawiam,\n$name")

        try {
            startActivity(Intent.createChooser(emailIntent, getString(R.string.send_mail)))
            finish()
        } catch (ex: android.content.ActivityNotFoundException) {
            Toast.makeText(this@RetreatDetailsActivity, getString(R.string.send_mail_error), Toast.LENGTH_SHORT).show()
        }
    }

    private fun showSaveToCalendarDialog() =
            AlertDialog.Builder(this)
                    .setTitle(R.string.save_to_calendar)
                    .setMessage(R.string.save_to_calendar_msg)
                    .setCancelable(false)
                    .setPositiveButton(getString(R.string.yes)) { dialog, _ ->
                        dialog.dismiss()
                        if (haveCalendarReadWritePermissions())
                            saveToCalendar()
                        else
                            requestCalendarReadWritePermission()
                    }
                    .setNegativeButton(getString(R.string.no)) { dialog, _ ->
                        dialog.dismiss()
                    }
                    .create()
                    .show()

    private fun saveToCalendar() {
        val startMillis: Long = Calendar.getInstance().run {
            time = mRetreatDetailsViewModel.retreat!!.beginDate.toDate()
            set(Calendar.HOUR_OF_DAY, 18)
            timeInMillis
        }
        val endMillis: Long = Calendar.getInstance().run {
            time = mRetreatDetailsViewModel.retreat!!.endDate.toDate()
            set(Calendar.HOUR_OF_DAY, 14)
            timeInMillis
        }

        val intent = Intent(Intent.ACTION_INSERT).apply {
            type = "vnd.android.cursor.item/event"
            putExtra(Events.CALENDAR_ID, 1)
            putExtra(Events.TITLE, mRetreatDetailsViewModel.retreat!!.name)
            putExtra(CalendarContract.EXTRA_EVENT_BEGIN_TIME, startMillis)
            putExtra(CalendarContract.EXTRA_EVENT_END_TIME, endMillis)
            putExtra(Events.STATUS, Events.STATUS_CONFIRMED)
            putExtra(Events.EVENT_LOCATION, "${mRetreatDetailsViewModel.retreat!!.address}, " +
                    mRetreatDetailsViewModel.retreat!!.city)
            putExtra(Events.EVENT_TIMEZONE, TimeZone.getDefault().id)
        }
        startActivity(intent)
    }

    private fun requestCalendarReadWritePermission() {
        val permissionList = ArrayList<String>()

        if (ContextCompat.checkSelfPermission(this@RetreatDetailsActivity,
                        Manifest.permission.WRITE_CALENDAR) != PackageManager.PERMISSION_GRANTED) {
            permissionList.add(Manifest.permission.WRITE_CALENDAR)
        }

        if (ContextCompat.checkSelfPermission(this@RetreatDetailsActivity,
                        Manifest.permission.READ_CALENDAR) != PackageManager.PERMISSION_GRANTED) {
            permissionList.add(Manifest.permission.READ_CALENDAR)
        }

        if (permissionList.size > 0) {
            val permissionArray = arrayOfNulls<String>(permissionList.size)

            for (i in 0 until permissionList.size) {
                permissionArray[i] = permissionList[i]
            }

            ActivityCompat.requestPermissions(this@RetreatDetailsActivity, permissionArray, 911)
        }
    }

    private fun haveCalendarReadWritePermissions(): Boolean {
        var permissionCheck = ContextCompat.checkSelfPermission(this@RetreatDetailsActivity,
                Manifest.permission.READ_CALENDAR)

        if (permissionCheck == PackageManager.PERMISSION_GRANTED) {
            permissionCheck = ContextCompat.checkSelfPermission(this@RetreatDetailsActivity,
                    Manifest.permission.WRITE_CALENDAR)

            if (permissionCheck == PackageManager.PERMISSION_GRANTED) {
                return true
            }
        }
        return false
    }
}
