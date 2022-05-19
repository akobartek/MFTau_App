package pl.mftau.mftau.view.fragments

import android.annotation.SuppressLint
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.CalendarContract
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.widget.Toolbar
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import pl.mftau.mftau.R
import pl.mftau.mftau.databinding.DialogRetreatRegisterBinding
import pl.mftau.mftau.databinding.FragmentRetreatDetailsBinding
import pl.mftau.mftau.model.local_db.Retreat
import pl.mftau.mftau.utils.PermissionUtils
import pl.mftau.mftau.utils.getDateFormatted
import pl.mftau.mftau.utils.showShortToast
import pl.mftau.mftau.view.ui.ClearErrorTextWatcher
import pl.mftau.mftau.viewmodel.RetreatsViewModel
import java.util.*

class RetreatDetailsFragment : BindingFragment<FragmentRetreatDetailsBinding>() {

    private lateinit var mViewModel: RetreatsViewModel
    private lateinit var mRetreat: Retreat

    override fun attachBinding(inflater: LayoutInflater, container: ViewGroup?) =
        FragmentRetreatDetailsBinding.inflate(inflater, container, false)

    override fun setup(savedInstanceState: Bundle?) {
        inflateToolbarMenu(binding.retreatDetailsToolbar)

        activity?.let {
            mViewModel = ViewModelProvider(it)[RetreatsViewModel::class.java]
        }
        arguments?.let {
            mRetreat = RetreatDetailsFragmentArgs.fromBundle(it).retreat
            binding.retreatDetailsToolbarTitle.text = mRetreat.name
            with(binding.contentRetreatDetails) {
                retreatNameET.setText(mRetreat.name)
                retreatCityET.setText(mRetreat.city)
                retreatAddressET.setText(mRetreat.address)
                retreatPriceET.setText(mRetreat.price.toString())
                beginDateText.setText(mRetreat.beginDate.toDate().getDateFormatted())
                endDateText.setText(mRetreat.endDate.toDate().getDateFormatted())
                retreatTypeET.setText(resources.getStringArray(R.array.retreat_types)[mRetreat.retreatType])
            }
        }

        if (mRetreat.registerLimitDate.toDate() < Date())
            binding.registerBtn.hide()

        binding.registerBtn.setOnClickListener {
            showRegisterDialog()
        }
    }

    private fun inflateToolbarMenu(toolbar: Toolbar) {
        toolbar.apply {
            setNavigationOnClickListener { findNavController().navigateUp() }
            inflateMenu(R.menu.menu_retreat_details)
            setOnMenuItemClickListener {
                when (it.itemId) {
                    R.id.action_show_map -> {
                        val gmmIntentUri =
                            Uri.parse("geo:0,0?q=" + Uri.encode("${mRetreat.address} ${mRetreat.city}"))
                        val mapIntent = Intent(Intent.ACTION_VIEW, gmmIntentUri)
                        mapIntent.setPackage("com.google.android.apps.maps")
                        startActivity(mapIntent)
                        true
                    }
                    R.id.action_save_to_calendar -> {
                        if (PermissionUtils.haveCalendarReadWritePermissions(requireActivity()))
                            showSaveToCalendarDialog()
                        else
                            PermissionUtils.requestCalendarReadWritePermission(requireActivity())
                        true
                    }
                    else -> false
                }
            }
        }
    }

    @SuppressLint("InflateParams")
    private fun showRegisterDialog() {
        val dialogBinding = DialogRetreatRegisterBinding.inflate(layoutInflater)

        val dialog = MaterialAlertDialogBuilder(requireContext())
            .setTitle(R.string.retreat_register_title)
            .setMessage(R.string.retreat_register_msg)
            .setView(dialogBinding.root)
            .setCancelable(false)
            .setPositiveButton(getString(R.string.send), null)
            .setNegativeButton(getString(R.string.cancel)) { dialog, _ -> dialog.dismiss() }
            .create()


        dialog.setOnShowListener {
            dialog.getButton(AlertDialog.BUTTON_POSITIVE).setOnClickListener {
                if (dialogBinding.registerNameET.text.isNullOrBlank()) {
                    dialogBinding.registerNameInputLayout.error =
                        getString(R.string.empty_name_error)
                    return@setOnClickListener
                } else {
                    dialog.dismiss()
                    if (mRetreat.advancePayment) {
                        showAdvancePaymentDialog(
                            dialogBinding.registerNameET.text.toString().trim()
                        )
                    } else {
                        sendRegisterEmail(dialogBinding.registerNameET.text.toString().trim())
                    }
                }
            }
            dialogBinding.registerNameET.addTextChangedListener(ClearErrorTextWatcher(dialogBinding.registerNameInputLayout))
        }
        dialog.show()
    }

    private fun showAdvancePaymentDialog(name: String) =
        MaterialAlertDialogBuilder(requireContext())
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
        emailIntent.setDataAndType(Uri.parse("mailto:"), "message/rfc822")
        emailIntent.putExtra(Intent.EXTRA_EMAIL, arrayOf("rada@mftau.pl"))
        emailIntent.putExtra(
            Intent.EXTRA_SUBJECT, "Zapisy na rekolekcje ${mRetreat.name}"
        )
        emailIntent.putExtra(
            Intent.EXTRA_TEXT, "Pokój i dobro!\n" +
                    "Chciałbym zgłosić chęć uczestnictwa w rekolekcjach ${mRetreat.name}.\n\n" +
                    "Pozdrawiam,\n$name"
        )

        try {
            startActivity(Intent.createChooser(emailIntent, getString(R.string.send_mail)))
        } catch (ex: android.content.ActivityNotFoundException) {
            requireContext().showShortToast(R.string.send_mail_error)
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
            .setNegativeButton(getString(R.string.no)) { dialog, _ ->
                dialog.dismiss()
            }
            .create()
            .show()

    private fun saveToCalendar() {
        val startMillis: Long = Calendar.getInstance().run {
            time = mRetreat.beginDate.toDate()
            set(Calendar.HOUR_OF_DAY, 18)
            timeInMillis
        }
        val endMillis: Long = Calendar.getInstance().run {
            time = mRetreat.endDate.toDate()
            set(Calendar.HOUR_OF_DAY, 14)
            timeInMillis
        }

        val intent = Intent(Intent.ACTION_INSERT).apply {
            type = "vnd.android.cursor.item/event"
            putExtra(CalendarContract.Events.CALENDAR_ID, 1)
            putExtra(CalendarContract.Events.TITLE, mRetreat.name)
            putExtra(CalendarContract.EXTRA_EVENT_BEGIN_TIME, startMillis)
            putExtra(CalendarContract.EXTRA_EVENT_END_TIME, endMillis)
            putExtra(CalendarContract.Events.STATUS, CalendarContract.Events.STATUS_CONFIRMED)
            putExtra(
                CalendarContract.Events.EVENT_LOCATION, "${mRetreat.address}, ${mRetreat.city}"
            )
            putExtra(CalendarContract.Events.EVENT_TIMEZONE, TimeZone.getDefault().id)
        }
        startActivity(intent)
    }
}
