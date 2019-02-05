package pl.mftau.mftau.view.fragments

import android.app.DatePickerDialog
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.provider.CalendarContract
import android.view.*
import android.view.inputmethod.InputMethodManager
import android.widget.ArrayAdapter
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.Timestamp
import kotlinx.android.synthetic.main.fragment_retreat_editor.view.*
import pl.mftau.mftau.R
import pl.mftau.mftau.model.Retreat
import pl.mftau.mftau.utils.FirestoreUtils
import pl.mftau.mftau.utils.PermissionUtils
import pl.mftau.mftau.viewmodel.MainViewModel
import java.text.SimpleDateFormat
import java.util.*

class RetreatEditorFragment : Fragment() {

    companion object {
        var retreatHasChanged = false
    }

    private lateinit var mViewModel: MainViewModel
    private var mRetreat: Retreat? = null
    private var beginDate: Date = Date()
    private var endDate: Date = Date()
    private var registerDate: Date = Date()
    private var lastEditDate: Date = Date()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        setHasOptionsMenu(true)
        retreatHasChanged = false
        return inflater.inflate(R.layout.fragment_retreat_editor, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        activity?.let {
            mViewModel = ViewModelProviders.of(it).get(MainViewModel::class.java)
        }

        view.retreatTypeSpinner.adapter = object : ArrayAdapter<String>(view.context,
                R.layout.item_spinner, resources.getStringArray(R.array.retreat_types)) {
            override fun getDropDownView(position: Int, convertView: View?, parent: ViewGroup): View {
                val dropDownView = super.getDropDownView(position, convertView, parent)
                (dropDownView as TextView).setTextColor(Color.BLACK)

                return dropDownView
            }
        }

        arguments?.let { bundle ->
            mRetreat = RetreatEditorFragmentArgs.fromBundle(bundle).retreat
            mRetreat?.let {
                beginDate = it.beginDate.toDate()
                endDate = it.endDate.toDate()
                registerDate = it.registerLimitDate.toDate()

                view.retreatNameET.setText(it.name)
                view.retreatCityET.setText(it.city)
                view.retreatAddressET.setText(it.address)
                view.retreatPriceET.setText(it.price.toString())
                view.retreatTypeSpinner.setSelection(it.retreatType)
                view.advPaymentSwitch.isChecked = it.advancePayment
            }
            view.beginDateText.setText(mViewModel.getDateFormatted(beginDate))
            view.endDateText.setText(mViewModel.getDateFormatted(endDate))
            view.registerDateText.setText(mViewModel.getDateFormatted(registerDate))

            activity?.invalidateOptionsMenu()
        }

        setOnClickListeners()
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_retreat_edit, menu)
    }

    override fun onPrepareOptionsMenu(menu: Menu) {
        if (mRetreat == null) {
            menu.findItem(R.id.action_save_to_calendar)?.isVisible = false
            menu.findItem(R.id.action_delete_retreat)?.isVisible = false
        }
        super.onPrepareOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_save_to_calendar -> {
                if (PermissionUtils.haveCalendarReadWritePermissions(activity!!))
                    showSaveToCalendarDialog()
                else
                    PermissionUtils.requestCalendarReadWritePermission(activity!!)
                true
            }
            R.id.action_delete_retreat -> {
                showDeleteConfirmationDialog()
                true
            }
            else -> return super.onOptionsItemSelected(item)
        }
    }

    private fun setOnClickListeners() {
        view?.saveRetreatBtn?.setOnClickListener {
            var errorOccurred = false
            if (view!!.retreatNameET.text.isNullOrBlank()) {
                view!!.retreatNameET.error = getString(R.string.empty_name_error)
                errorOccurred = true
            }
            if (view!!.retreatCityET.text.isNullOrBlank()) {
                view!!.retreatCityET.error = getString(R.string.empty_city_error)
                errorOccurred = true
            }
            if (view!!.retreatAddressET.text.isNullOrBlank()) {
                view!!.retreatAddressET.error = getString(R.string.empty_address_error)
                errorOccurred = true
            }
            if (view!!.retreatPriceET.text.isNullOrBlank()) {
                view!!.retreatPriceET.error = getString(R.string.empty_price_error)
                errorOccurred = true
            }
            when {
                endDate < Date(Date().time - 86400000) -> {
                    Snackbar.make(view!!.retreatsEditLayout, getString(R.string.error_retreat_has_taken_place),
                            Snackbar.LENGTH_LONG).show()
                    errorOccurred = true
                }
                beginDate > endDate -> {
                    Snackbar.make(view!!.retreatsEditLayout, getString(R.string.error_begin_date_later_than_end),
                            Snackbar.LENGTH_LONG).show()
                    errorOccurred = true
                }
                registerDate > beginDate -> {
                    Snackbar.make(view!!.retreatsEditLayout, getString(R.string.error_date_limit_after_begin),
                            Snackbar.LENGTH_LONG).show()
                    errorOccurred = true
                }
            }
            if (errorOccurred) return@setOnClickListener

            val retreatValues = HashMap<String, Any>()
            retreatValues[FirestoreUtils.firestoreKeyName] = view!!.retreatNameET.text.toString().trim()
            retreatValues[FirestoreUtils.firestoreKeyCity] = view!!.retreatCityET.text.toString().trim()
            retreatValues[FirestoreUtils.firestoreKeyAddress] = view!!.retreatAddressET.text.toString().trim()
            retreatValues[FirestoreUtils.firestoreKeyPrice] = view!!.retreatPriceET.text.toString().toInt()
            retreatValues[FirestoreUtils.firestoreKeyBeginDate] = Timestamp(beginDate)
            retreatValues[FirestoreUtils.firestoreKeyEndDate] = Timestamp(endDate)
            retreatValues[FirestoreUtils.firestoreKeyRegisterLimitDate] = Timestamp(registerDate)
            retreatValues[FirestoreUtils.firestoreKeyRetreatType] = view!!.retreatTypeSpinner.selectedItemPosition
            retreatValues[FirestoreUtils.firestoreKeyAdvancePayment] = view!!.advPaymentSwitch.isChecked

            if (mRetreat == null) {
                mViewModel.addRetreat(activity!!, retreatValues)
            } else {
                if (!(view!!.retreatNameET.text.toString().trim() == mRetreat!!.name &&
                                view!!.retreatCityET.text.toString().trim() == mRetreat!!.city &&
                                view!!.retreatAddressET.text.toString().trim() == mRetreat!!.address &&
                                view!!.retreatPriceET.text.toString().toInt() == mRetreat!!.price &&
                                beginDate == mRetreat!!.beginDate.toDate() &&
                                endDate == mRetreat!!.endDate.toDate() &&
                                registerDate == mRetreat!!.registerLimitDate.toDate() &&
                                view!!.retreatTypeSpinner.selectedItemPosition == mRetreat!!.retreatType &&
                                view!!.advPaymentSwitch.isChecked == mRetreat!!.advancePayment))
                    mViewModel.updateRetreat(activity!!, mRetreat!!.id, retreatValues)
            }
            findNavController().navigateUp()
        }

        view?.beginDateText?.setOnClickListener {
            (it.context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager)
                    .hideSoftInputFromWindow(activity?.currentFocus?.windowToken, 0)

            val calendar = Calendar.getInstance()
            calendar.time = lastEditDate

            DatePickerDialog(it.context, myBeginDateListener,
                    calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH)).show()
        }
        view?.endDateText?.setOnClickListener {
            (it.context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager)
                    .hideSoftInputFromWindow(activity?.currentFocus?.windowToken, 0)

            val calendar = Calendar.getInstance()
            calendar.time = lastEditDate

            DatePickerDialog(it.context, myEndDateListener,
                    calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH)).show()
        }
        view?.registerDateText?.setOnClickListener {
            (it.context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager)
                    .hideSoftInputFromWindow(activity?.currentFocus?.windowToken, 0)

            val calendar = Calendar.getInstance()
            calendar.time = lastEditDate

            DatePickerDialog(it.context, myRegisterDateListener,
                    calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH)).show()
        }

        view?.retreatsEditLayout?.setOnClickListener(mHideKeyboardClickListener)

        view?.retreatNameET?.setOnTouchListener(mTouchListener)
        view?.retreatCityET?.setOnTouchListener(mTouchListener)
        view?.retreatAddressET?.setOnTouchListener(mTouchListener)
        view?.retreatPriceET?.setOnTouchListener(mTouchListener)
        view?.beginDateText?.setOnTouchListener(mTouchListener)
        view?.endDateText?.setOnTouchListener(mTouchListener)
        view?.registerDateText?.setOnTouchListener(mTouchListener)
        view?.retreatTypeSpinner?.setOnTouchListener(mTouchListener)
        view?.advPaymentSwitch?.setOnTouchListener(mTouchListener)
    }

    private fun showSaveToCalendarDialog() =
            AlertDialog.Builder(context!!)
                    .setTitle(R.string.save_to_calendar)
                    .setMessage(R.string.save_to_calendar_msg)
                    .setCancelable(false)
                    .setPositiveButton(getString(R.string.yes)) { dialog, _ ->
                        dialog.dismiss()
                        if (PermissionUtils.haveCalendarReadWritePermissions(activity!!))
                            saveToCalendar()
                        else
                            PermissionUtils.requestCalendarReadWritePermission(activity!!)
                    }
                    .setNegativeButton(getString(R.string.no)) { dialog, _ ->
                        dialog.dismiss()
                    }
                    .create()
                    .show()

    private fun saveToCalendar() {
        val startMillis: Long = Calendar.getInstance().run {
            time = mRetreat?.beginDate?.toDate()
            set(Calendar.HOUR_OF_DAY, 18)
            timeInMillis
        }
        val endMillis: Long = Calendar.getInstance().run {
            time = mRetreat?.endDate?.toDate()
            set(Calendar.HOUR_OF_DAY, 14)
            timeInMillis
        }

        val intent = Intent(Intent.ACTION_INSERT).apply {
            type = "vnd.android.cursor.item/event"
            putExtra(CalendarContract.Events.CALENDAR_ID, 1)
            putExtra(CalendarContract.Events.TITLE, mRetreat?.name)
            putExtra(CalendarContract.EXTRA_EVENT_BEGIN_TIME, startMillis)
            putExtra(CalendarContract.EXTRA_EVENT_END_TIME, endMillis)
            putExtra(CalendarContract.Events.STATUS, CalendarContract.Events.STATUS_CONFIRMED)
            putExtra(CalendarContract.Events.EVENT_LOCATION, "${mRetreat?.address}, ${mRetreat?.city}")
            putExtra(CalendarContract.Events.EVENT_TIMEZONE, TimeZone.getDefault().id)
        }
        startActivity(intent)
    }

    private fun showDeleteConfirmationDialog() =
            AlertDialog.Builder(context!!)
                    .setMessage(R.string.retreat_delete_dialog_msg)
                    .setCancelable(false)
                    .setPositiveButton(R.string.delete) { dialog, _ ->
                        dialog.dismiss()
                        mViewModel.deleteRetreat(activity!!, mRetreat!!.id, true)
                        findNavController().navigateUp()
                    }
                    .setNegativeButton(R.string.cancel) { dialog, _ -> dialog.dismiss() }
                    .create()
                    .show()

    private val mTouchListener = View.OnTouchListener { _, _ ->
        retreatHasChanged = true
        false
    }
    private val mHideKeyboardClickListener = View.OnClickListener {
        (it.context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager)
                .hideSoftInputFromWindow(activity?.currentFocus?.windowToken, 0)
    }
    private val myBeginDateListener = DatePickerDialog.OnDateSetListener { _, year, month, day ->
        val dateString = StringBuilder().append(day).append(".").append(month + 1).append(".").append(year).toString()
        beginDate = SimpleDateFormat("dd.MM.yyyy", Locale.getDefault()).parse(dateString)
        lastEditDate = SimpleDateFormat("dd.MM.yyyy", Locale.getDefault()).parse(dateString)
        view?.beginDateText?.setText(mViewModel.getDateFormatted(beginDate))
    }
    private val myEndDateListener = DatePickerDialog.OnDateSetListener { _, year, month, day ->
        val dateString = StringBuilder().append(day).append(".").append(month + 1).append(".").append(year).toString()
        endDate = SimpleDateFormat("dd.MM.yyyy", Locale.getDefault()).parse(dateString)
        lastEditDate = SimpleDateFormat("dd.MM.yyyy", Locale.getDefault()).parse(dateString)
        view?.endDateText?.setText(mViewModel.getDateFormatted(endDate))
    }
    private val myRegisterDateListener = DatePickerDialog.OnDateSetListener { _, year, month, day ->
        val dateString = StringBuilder().append(day).append(".").append(month + 1).append(".").append(year).toString()
        registerDate = SimpleDateFormat("dd.MM.yyyy", Locale.getDefault()).parse(dateString)
        lastEditDate = SimpleDateFormat("dd.MM.yyyy", Locale.getDefault()).parse(dateString)
        view?.registerDateText?.setText(mViewModel.getDateFormatted(registerDate))
    }
}
