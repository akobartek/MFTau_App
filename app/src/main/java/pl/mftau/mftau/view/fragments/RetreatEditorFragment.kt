package pl.mftau.mftau.view.fragments

import android.annotation.SuppressLint
import android.app.DatePickerDialog
import android.content.Intent
import android.os.Bundle
import android.provider.CalendarContract
import android.view.LayoutInflater
import android.view.Menu
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.Toolbar
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.Timestamp
import pl.mftau.mftau.R
import pl.mftau.mftau.databinding.FragmentRetreatEditorBinding
import pl.mftau.mftau.model.local_db.Retreat
import pl.mftau.mftau.utils.FirestoreUtils
import pl.mftau.mftau.utils.PermissionUtils
import pl.mftau.mftau.utils.getDateFormatted
import pl.mftau.mftau.utils.hideKeyboard
import pl.mftau.mftau.view.ui.ClearErrorTextWatcher
import pl.mftau.mftau.viewmodel.MainViewModel
import java.text.SimpleDateFormat
import java.util.*

class RetreatEditorFragment : BindingFragment<FragmentRetreatEditorBinding>() {

    companion object {
        var retreatHasChanged = false
    }

    private lateinit var mViewModel: MainViewModel
    private var mRetreat: Retreat? = null
    private var beginDate: Date = Date()
    private var endDate: Date = Date()
    private var registerDate: Date = Date()
    private var lastEditDate: Date = Date()

    override fun attachBinding(inflater: LayoutInflater, container: ViewGroup?) =
        FragmentRetreatEditorBinding.inflate(inflater, container, false)

    override fun setup(savedInstanceState: Bundle?) {
        retreatHasChanged = false
        inflateToolbarMenu(binding.retreatEditorToolbar)

        activity?.let {
            mViewModel = ViewModelProvider(it)[MainViewModel::class.java]
        }

        // TODO() -> SET CORRECT SIMPLE ITEMS AFTER CONNECTING TO REMOTE DATABASE
        arguments?.let { bundle ->
            mRetreat = RetreatEditorFragmentArgs.fromBundle(bundle).retreat
            binding.retreatEditorToolbarTitle.text =
                if (mRetreat == null) getString(R.string.add_retreat) else getString(R.string.edit_retreat)
            mRetreat?.let {
                beginDate = it.beginDate.toDate()
                endDate = it.endDate.toDate()
                registerDate = it.registerLimitDate.toDate()

                with(binding.contentRetreatEditor) {
                    retreatNameET.setText(it.name)
                    retreatCityET.setText(it.city)
                    retreatAddressET.setText(it.address)
                    retreatPriceET.setText(it.price.toString())
                    retreatTypeTV.setText(resources.getStringArray(R.array.retreat_types)[it.retreatType])
                    advPaymentSwitch.isChecked = it.advancePayment
                }
            }
            with(binding.contentRetreatEditor) {
                beginDateText.setText(beginDate.getDateFormatted())
                endDateText.setText(endDate.getDateFormatted())
                registerDateText.setText(registerDate.getDateFormatted())
            }

            setupToolbarMenuIcons(binding.retreatEditorToolbar.menu)
        }

        setViewsListeners()
    }

    private fun inflateToolbarMenu(toolbar: Toolbar) {
        toolbar.apply {
            setNavigationOnClickListener { findNavController().navigateUp() }
            inflateMenu(R.menu.menu_retreat_edit)
            setOnMenuItemClickListener {
                when (it.itemId) {
                    R.id.action_save_to_calendar -> {
                        if (PermissionUtils.haveCalendarReadWritePermissions(requireActivity()))
                            showSaveToCalendarDialog()
                        else
                            PermissionUtils.requestCalendarReadWritePermission(requireActivity())
                        true
                    }
                    R.id.action_delete_retreat -> {
                        showDeleteConfirmationDialog()
                        true
                    }
                    else -> false
                }
            }
        }
    }

    private fun setupToolbarMenuIcons(menu: Menu) {
        if (mRetreat == null) {
            menu.findItem(R.id.action_save_to_calendar)?.isVisible = false
            menu.findItem(R.id.action_delete_retreat)?.isVisible = false
        }
    }

    @SuppressLint("ClickableViewAccessibility")
    private fun setViewsListeners() {
        with(binding.contentRetreatEditor) {
            binding.saveRetreatBtn.setOnClickListener {
                var errorOccurred = false
                if (retreatNameET.text.isNullOrBlank()) {
                    nameInputLayout.error = getString(R.string.empty_name_error)
                    errorOccurred = true
                }
                if (retreatCityET.text.isNullOrBlank()) {
                    cityInputLayout.error = getString(R.string.empty_city_error)
                    errorOccurred = true
                }
                if (retreatAddressET.text.isNullOrBlank()) {
                    addressInputLayout.error = getString(R.string.empty_address_error)
                    errorOccurred = true
                }
                if (retreatPriceET.text.isNullOrBlank()) {
                    priceInputLayout.error = getString(R.string.empty_price_error)
                    errorOccurred = true
                }
                when {
                    endDate < Date(Date().time - 86400000) -> {
                        Snackbar.make(
                            retreatsEditLayout,
                            getString(R.string.error_retreat_has_taken_place),
                            Snackbar.LENGTH_LONG
                        ).show()
                        errorOccurred = true
                    }
                    beginDate > endDate -> {
                        Snackbar.make(
                            retreatsEditLayout,
                            getString(R.string.error_begin_date_later_than_end),
                            Snackbar.LENGTH_LONG
                        ).show()
                        errorOccurred = true
                    }
                    registerDate > beginDate -> {
                        Snackbar.make(
                            retreatsEditLayout,
                            getString(R.string.error_date_limit_after_begin),
                            Snackbar.LENGTH_LONG
                        ).show()
                        errorOccurred = true
                    }
                }
                if (errorOccurred) return@setOnClickListener

                val retreatValues = HashMap<String, Any>()
                retreatValues[FirestoreUtils.firestoreKeyName] =
                    retreatNameET.text.toString().trim()
                retreatValues[FirestoreUtils.firestoreKeyCity] =
                    retreatCityET.text.toString().trim()
                retreatValues[FirestoreUtils.firestoreKeyAddress] =
                    retreatAddressET.text.toString().trim()
                retreatValues[FirestoreUtils.firestoreKeyPrice] =
                    retreatPriceET.text.toString().toInt()
                retreatValues[FirestoreUtils.firestoreKeyBeginDate] = Timestamp(beginDate)
                retreatValues[FirestoreUtils.firestoreKeyEndDate] = Timestamp(endDate)
                retreatValues[FirestoreUtils.firestoreKeyRegisterLimitDate] =
                    Timestamp(registerDate)
//                retreatValues[FirestoreUtils.firestoreKeyRetreatType] =
//                    retreatTypeSpinner.selectedItemPosition
                retreatValues[FirestoreUtils.firestoreKeyAdvancePayment] =
                    advPaymentSwitch.isChecked

                if (mRetreat == null) {
                    mViewModel.addRetreat(requireActivity(), retreatValues)
                } else {
                    if (!(retreatNameET.text.toString().trim() == mRetreat!!.name &&
                                retreatCityET.text.toString().trim() == mRetreat!!.city &&
                                retreatAddressET.text.toString().trim() == mRetreat!!.address &&
                                retreatPriceET.text.toString().toInt() == mRetreat!!.price &&
                                beginDate == mRetreat!!.beginDate.toDate() &&
                                endDate == mRetreat!!.endDate.toDate() &&
                                registerDate == mRetreat!!.registerLimitDate.toDate() &&
//                                retreatTypeSpinner.selectedItemPosition == mRetreat!!.retreatType &&
                                advPaymentSwitch.isChecked == mRetreat!!.advancePayment)
                    ) mViewModel.updateRetreat(requireActivity(), mRetreat!!.id, retreatValues)
                }
                findNavController().navigateUp()
            }

            beginDateText.setOnClickListener {
                requireActivity().hideKeyboard()

                val calendar = Calendar.getInstance()
                calendar.time = lastEditDate

                DatePickerDialog(
                    it.context,
                    myBeginDateListener,
                    calendar.get(Calendar.YEAR),
                    calendar.get(Calendar.MONTH),
                    calendar.get(Calendar.DAY_OF_MONTH)
                ).show()
            }
            endDateText.setOnClickListener {
                requireActivity().hideKeyboard()

                val calendar = Calendar.getInstance()
                calendar.time = lastEditDate

                DatePickerDialog(
                    it.context,
                    myEndDateListener,
                    calendar.get(Calendar.YEAR),
                    calendar.get(Calendar.MONTH),
                    calendar.get(Calendar.DAY_OF_MONTH)
                ).show()
            }
            registerDateText.setOnClickListener {
                requireActivity().hideKeyboard()

                val calendar = Calendar.getInstance()
                calendar.time = lastEditDate

                DatePickerDialog(
                    it.context,
                    myRegisterDateListener,
                    calendar.get(Calendar.YEAR),
                    calendar.get(Calendar.MONTH),
                    calendar.get(Calendar.DAY_OF_MONTH)
                ).show()
            }

            retreatsEditLayout.setOnClickListener { requireActivity().hideKeyboard() }

            retreatNameET.setOnTouchListener(mTouchListener)
            retreatCityET.setOnTouchListener(mTouchListener)
            retreatAddressET.setOnTouchListener(mTouchListener)
            retreatPriceET.setOnTouchListener(mTouchListener)
            beginDateText.setOnTouchListener(mTouchListener)
            endDateText.setOnTouchListener(mTouchListener)
            registerDateText.setOnTouchListener(mTouchListener)
            retreatTypeInputLayout.setOnTouchListener(mTouchListener)
            advPaymentSwitch.setOnTouchListener(mTouchListener)

            retreatNameET.addTextChangedListener(ClearErrorTextWatcher(nameInputLayout))
            retreatCityET.addTextChangedListener(ClearErrorTextWatcher(cityInputLayout))
            retreatAddressET.addTextChangedListener(ClearErrorTextWatcher(addressInputLayout))
            retreatPriceET.addTextChangedListener(ClearErrorTextWatcher(priceInputLayout))
        }
    }

    private fun showSaveToCalendarDialog() =
        MaterialAlertDialogBuilder(requireContext())
            .setTitle(R.string.save_to_calendar)
            .setMessage(R.string.save_to_calendar_msg)
            .setCancelable(false)
            .setPositiveButton(getString(R.string.yes)) { dialog, _ ->
                dialog.dismiss()
                if (PermissionUtils.haveCalendarReadWritePermissions(requireActivity()))
                    saveToCalendar()
                else
                    PermissionUtils.requestCalendarReadWritePermission(requireActivity())
            }
            .setNegativeButton(getString(R.string.no)) { dialog, _ -> dialog.dismiss() }
            .create()
            .show()

    private fun saveToCalendar() {
        if (mRetreat == null) return
        val startMillis: Long = Calendar.getInstance().run {
            time = mRetreat!!.beginDate.toDate()
            set(Calendar.HOUR_OF_DAY, 18)
            timeInMillis
        }
        val endMillis: Long = Calendar.getInstance().run {
            time = mRetreat!!.endDate.toDate()
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
            putExtra(
                CalendarContract.Events.EVENT_LOCATION,
                "${mRetreat?.address}, ${mRetreat?.city}"
            )
            putExtra(CalendarContract.Events.EVENT_TIMEZONE, TimeZone.getDefault().id)
        }
        startActivity(intent)
    }

    private fun showDeleteConfirmationDialog() =
        MaterialAlertDialogBuilder(requireContext())
            .setMessage(R.string.retreat_delete_dialog_msg)
            .setCancelable(false)
            .setPositiveButton(R.string.delete) { dialog, _ ->
                dialog.dismiss()
                mViewModel.deleteRetreat(requireActivity(), mRetreat!!.id, true)
                findNavController().navigateUp()
            }
            .setNegativeButton(R.string.cancel) { dialog, _ -> dialog.dismiss() }
            .create()
            .show()

    @SuppressLint("ClickableViewAccessibility")
    private val mTouchListener = View.OnTouchListener { _, _ ->
        retreatHasChanged = true
        false
    }
    private val myBeginDateListener = DatePickerDialog.OnDateSetListener { _, year, month, day ->
        val dateString =
            StringBuilder().append(day).append(".").append(month + 1).append(".").append(year)
                .toString()
        beginDate = SimpleDateFormat("dd.MM.yyyy", Locale.getDefault()).parse(dateString)!!
        lastEditDate = SimpleDateFormat("dd.MM.yyyy", Locale.getDefault()).parse(dateString)!!
        binding.contentRetreatEditor.beginDateText.setText(beginDate.getDateFormatted())
    }
    private val myEndDateListener = DatePickerDialog.OnDateSetListener { _, year, month, day ->
        val dateString =
            StringBuilder().append(day).append(".").append(month + 1).append(".").append(year)
                .toString()
        endDate = SimpleDateFormat("dd.MM.yyyy", Locale.getDefault()).parse(dateString)!!
        lastEditDate = SimpleDateFormat("dd.MM.yyyy", Locale.getDefault()).parse(dateString)!!
        binding.contentRetreatEditor.endDateText.setText(endDate.getDateFormatted())
    }
    private val myRegisterDateListener = DatePickerDialog.OnDateSetListener { _, year, month, day ->
        val dateString =
            StringBuilder().append(day).append(".").append(month + 1).append(".").append(year)
                .toString()
        registerDate = SimpleDateFormat("dd.MM.yyyy", Locale.getDefault()).parse(dateString)!!
        lastEditDate = SimpleDateFormat("dd.MM.yyyy", Locale.getDefault()).parse(dateString)!!
        binding.contentRetreatEditor.registerDateText.setText(registerDate.getDateFormatted())
    }
}
