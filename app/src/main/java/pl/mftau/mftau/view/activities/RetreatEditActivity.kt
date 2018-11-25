package pl.mftau.mftau.view.activities

import android.app.Activity
import android.app.DatePickerDialog
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.ViewModelProviders
import androidx.preference.PreferenceManager
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_retreat_edit.*
import pl.mftau.mftau.R
import pl.mftau.mftau.viewmodel.MainViewModel
import pl.mftau.mftau.viewmodel.RetreatEditViewModel
import java.text.SimpleDateFormat
import java.util.*

class RetreatEditActivity : AppCompatActivity() {

    companion object {
        const val DATE_PATTERN = "dd.MM.yyyy"
    }

    private lateinit var mRetreatEditViewModel: RetreatEditViewModel
    private var mRetreatHasChanged = false

    override fun onCreate(savedInstanceState: Bundle?) {
        if (PreferenceManager.getDefaultSharedPreferences(this@RetreatEditActivity)
                        .getBoolean(getString(R.string.night_mode_key), false)) {
            setTheme(R.style.AppTheme_Dark)
        } else {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
                window.statusBarColor = Color.WHITE
            }
        }

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_retreat_edit)

        setSupportActionBar(retreatDetailsToolbar)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        supportActionBar!!.setHomeAsUpIndicator(R.drawable.ic_arrow_back)

        mRetreatEditViewModel = ViewModelProviders.of(this@RetreatEditActivity).get(RetreatEditViewModel::class.java)

        mRetreatEditViewModel.retreat = intent.getParcelableExtra("retreat")
        if (mRetreatEditViewModel.retreat == null) {
            invalidateOptionsMenu()
            title = getString(R.string.add_retreat)

            beginDateText.setText(mRetreatEditViewModel.getDateFormatted(mRetreatEditViewModel.beginDate))
            endDateText.setText(mRetreatEditViewModel.getDateFormatted(mRetreatEditViewModel.endDate))
            registerDateText.setText(mRetreatEditViewModel.getDateFormatted(mRetreatEditViewModel.registerDate))
        } else {
            mRetreatEditViewModel.beginDate = mRetreatEditViewModel.retreat!!.beginDate.toDate()
            mRetreatEditViewModel.endDate = mRetreatEditViewModel.retreat!!.endDate.toDate()
            mRetreatEditViewModel.registerDate = mRetreatEditViewModel.retreat!!.registerLimitDate.toDate()
            title = getString(R.string.edit_retreat)

            retreatNameET.setText(mRetreatEditViewModel.retreat!!.name)
            retreatCityET.setText(mRetreatEditViewModel.retreat!!.city)
            retreatAddressET.setText(mRetreatEditViewModel.retreat!!.address)
            retreatPriceET.setText(mRetreatEditViewModel.getPriceFormatted())
            beginDateText.setText(mRetreatEditViewModel.getDateFormatted(mRetreatEditViewModel.beginDate))
            endDateText.setText(mRetreatEditViewModel.getDateFormatted(mRetreatEditViewModel.endDate))
            registerDateText.setText(mRetreatEditViewModel.getDateFormatted(mRetreatEditViewModel.registerDate))
            advPaymentSwitch.isChecked = mRetreatEditViewModel.retreat!!.advancePayment
        }

        setOnClickListeners()
    }

    private fun setOnClickListeners() {
        saveRetreatBtn.setOnClickListener {
            var errorOccurred = false
            if (retreatNameET.text.isNullOrBlank()) {
                retreatNameET.error = getString(R.string.empty_name_error)
                errorOccurred = true
            }
            if (retreatCityET.text.isNullOrBlank()) {
                retreatCityET.error = getString(R.string.empty_city_error)
                errorOccurred = true
            }
            if (retreatAddressET.text.isNullOrBlank()) {
                retreatAddressET.error = getString(R.string.empty_address_error)
                errorOccurred = true
            }
            if (retreatPriceET.text.isNullOrBlank()) {
                retreatPriceET.error = getString(R.string.empty_price_error)
                errorOccurred = true
            }
            when {
                mRetreatEditViewModel.endDate < Date(Date().time - 86400000) -> {
                    Snackbar.make(retreatsEditLayout, getString(R.string.error_retreat_has_taken_place),
                            Snackbar.LENGTH_LONG).show()
                    errorOccurred = true
                }
                mRetreatEditViewModel.beginDate > mRetreatEditViewModel.endDate -> {
                    Snackbar.make(retreatsEditLayout, getString(R.string.error_begin_date_later_than_end),
                            Snackbar.LENGTH_LONG).show()
                    errorOccurred = true
                }
                mRetreatEditViewModel.registerDate > mRetreatEditViewModel.beginDate -> {
                    Snackbar.make(retreatsEditLayout, getString(R.string.error_date_limit_after_begin),
                            Snackbar.LENGTH_LONG).show()
                    errorOccurred = true
                }
            }
            if (errorOccurred) return@setOnClickListener

            val retreatValues = mRetreatEditViewModel.createRetreatValues(
                    retreatNameET.text.toString().trim(), retreatCityET.text.toString().trim(),
                    retreatAddressET.text.toString().trim(), retreatPriceET.text.toString().toInt(),
                    advPaymentSwitch.isChecked
            )

            if (mRetreatEditViewModel.retreat == null) {
                mRetreatEditViewModel.addRetreat(this@RetreatEditActivity, retreatValues)
            } else {
                if (retreatNameET.text.toString().trim() == mRetreatEditViewModel.retreat!!.name &&
                        retreatCityET.text.toString().trim() == mRetreatEditViewModel.retreat!!.city &&
                        retreatAddressET.text.toString().trim() == mRetreatEditViewModel.retreat!!.address &&
                        retreatPriceET.text.toString().toInt() == mRetreatEditViewModel.retreat!!.price &&
                        mRetreatEditViewModel.beginDate == mRetreatEditViewModel.retreat!!.beginDate.toDate() &&
                        mRetreatEditViewModel.endDate == mRetreatEditViewModel.retreat!!.endDate.toDate() &&
                        mRetreatEditViewModel.registerDate == mRetreatEditViewModel.retreat!!.registerLimitDate.toDate() &&
                        advPaymentSwitch.isChecked == mRetreatEditViewModel.retreat!!.advancePayment
                ) finish()
                else mRetreatEditViewModel.updateRetreat(this@RetreatEditActivity, retreatValues)
            }
        }

        beginDateText.setOnClickListener {
            (getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager)
                    .hideSoftInputFromWindow(currentFocus!!.windowToken, 0)

            val calendar = Calendar.getInstance()
            calendar.time = mRetreatEditViewModel.beginDate

            DatePickerDialog(this@RetreatEditActivity, myBeginDateListener,
                    calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH)).show()
        }
        endDateText.setOnClickListener {
            (getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager)
                    .hideSoftInputFromWindow(currentFocus!!.windowToken, 0)

            val calendar = Calendar.getInstance()
            calendar.time = mRetreatEditViewModel.endDate

            DatePickerDialog(this@RetreatEditActivity, myEndDateListener,
                    calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH)).show()
        }
        registerDateText.setOnClickListener {
            (getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager)
                    .hideSoftInputFromWindow(currentFocus!!.windowToken, 0)

            val calendar = Calendar.getInstance()
            calendar.time = mRetreatEditViewModel.registerDate

            DatePickerDialog(this@RetreatEditActivity, myRegisterDateListener,
                    calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH)).show()
        }

        retreatsEditLayout.setOnClickListener(mHideKeyboardClickListener)

        retreatNameET.setOnTouchListener(mTouchListener)
        retreatCityET.setOnTouchListener(mTouchListener)
        retreatAddressET.setOnTouchListener(mTouchListener)
        retreatPriceET.setOnTouchListener(mTouchListener)
        beginDateText.setOnTouchListener(mTouchListener)
        endDateText.setOnTouchListener(mTouchListener)
        registerDateText.setOnTouchListener(mTouchListener)
        advPaymentSwitch.setOnTouchListener(mTouchListener)
    }

    override fun onBackPressed() {
        if (mRetreatHasChanged) {
            showUnsavedChangesDialog()
        } else {
            setResult(Activity.RESULT_OK, Intent()
                    .putExtra("userType", intent.getIntExtra("userType", MainViewModel.USER_TYPE_NONE)))
            finish()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_edit_retreat, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onPrepareOptionsMenu(menu: Menu): Boolean {
        if (mRetreatEditViewModel.retreat == null) {
            menu.findItem(R.id.action_delete_retreat).isVisible = false
        }
        return super.onPrepareOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_delete_retreat -> {
                showDeleteConfirmationDialog()
                true
            }
            android.R.id.home -> {
                onBackPressed()
                true
            }
            else -> return super.onOptionsItemSelected(item)
        }
    }

    private fun showUnsavedChangesDialog() =
            AlertDialog.Builder(this@RetreatEditActivity)
                    .setMessage(R.string.unsaved_changes_dialog_msg)
                    .setCancelable(false)
                    .setPositiveButton(R.string.discard) { dialog, _ ->
                        dialog.dismiss()

                        finish()
                    }
                    .setNegativeButton(R.string.keep_editing) { dialog, _ -> dialog.dismiss() }
                    .create()
                    .show()

    private fun showDeleteConfirmationDialog() =
            AlertDialog.Builder(this@RetreatEditActivity)
                    .setMessage(R.string.retreat_delete_dialog_msg)
                    .setPositiveButton(R.string.delete) { dialog, _ ->
                        dialog.dismiss()
                        mRetreatEditViewModel.deleteRetreat(this@RetreatEditActivity)
                        setResult(Activity.RESULT_OK, Intent()
                                .putExtra("userType", intent.getIntExtra("userType", MainViewModel.USER_TYPE_NONE)))
                        finish()
                    }
                    .setNegativeButton(R.string.cancel) { dialog, _ -> dialog.dismiss() }
                    .create()
                    .show()


    private val mTouchListener = View.OnTouchListener { _, _ ->
        mRetreatHasChanged = true
        false
    }
    private val mHideKeyboardClickListener = View.OnClickListener {
        (getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager)
                .hideSoftInputFromWindow(currentFocus!!.windowToken, 0)
    }
    private val myBeginDateListener = DatePickerDialog.OnDateSetListener { _, year, month, day ->
        val dateString = StringBuilder().append(day).append(".").append(month + 1).append(".").append(year).toString()
        mRetreatEditViewModel.beginDate = SimpleDateFormat(DATE_PATTERN, Locale.getDefault()).parse(dateString)
        beginDateText.setText(mRetreatEditViewModel.getDateFormatted(mRetreatEditViewModel.beginDate))
    }
    private val myEndDateListener = DatePickerDialog.OnDateSetListener { _, year, month, day ->
        val dateString = StringBuilder().append(day).append(".").append(month + 1).append(".").append(year).toString()
        mRetreatEditViewModel.endDate = SimpleDateFormat(DATE_PATTERN, Locale.getDefault()).parse(dateString)
        endDateText.setText(mRetreatEditViewModel.getDateFormatted(mRetreatEditViewModel.endDate))
    }
    private val myRegisterDateListener = DatePickerDialog.OnDateSetListener { _, year, month, day ->
        val dateString = StringBuilder().append(day).append(".").append(month + 1).append(".").append(year).toString()
        mRetreatEditViewModel.registerDate = SimpleDateFormat(DATE_PATTERN, Locale.getDefault()).parse(dateString)
        registerDateText.setText(mRetreatEditViewModel.getDateFormatted(mRetreatEditViewModel.registerDate))
    }
}
