package pl.mftau.mftau.view.activities

import android.app.DatePickerDialog
import android.graphics.Color
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AlertDialog
import kotlinx.android.synthetic.main.activity_add_meeting.*
import pl.mftau.mftau.R
import java.util.*


class AddMeetingActivity : AppCompatActivity() {

    private var date = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_meeting)
        setSupportActionBar(addMeetingToolbar)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
            window.statusBarColor = Color.WHITE
        }

        setOnClickListeners()
    }

    private fun setOnClickListeners() {
        setDateBtn.setOnClickListener {
            val calendar = Calendar.getInstance()
            val dialog = DatePickerDialog(this@AddMeetingActivity, myDateListener,
                    calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH))
            dialog.show()
        }


    }

    private val myDateListener = DatePickerDialog.OnDateSetListener { _, year, month, day ->
        date = StringBuilder().append(day).append("/").append(month + 1).append("/").append(year).toString()
        val text = getString(R.string.set_date) + " ($date)"
        setDateBtn.text = text
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                onBackPressed()
                true
            }
            else -> return super.onOptionsItemSelected(item)
        }
    }

    override fun onBackPressed() {
        if (!date.isEmpty() || meetingNameET.text.isNullOrEmpty())
            showUnsavedChangesDialog()
        else
            super.onBackPressed()
    }

    private fun showUnsavedChangesDialog() {
        AlertDialog.Builder(this@AddMeetingActivity)
                .setMessage(R.string.unsaved_changes_dialog_msg)
                .setCancelable(false)
                .setPositiveButton(R.string.discard) { dialog, _ ->
                    dialog.dismiss()
                    finish()
                }
                .setNegativeButton(R.string.keep_editing) { dialog, _ -> dialog.dismiss() }
                .create()
                .show()
    }
}
