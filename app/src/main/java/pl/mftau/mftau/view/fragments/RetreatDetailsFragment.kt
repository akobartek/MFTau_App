package pl.mftau.mftau.view.fragments


import android.annotation.SuppressLint
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.CalendarContract
import android.view.*
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import kotlinx.android.synthetic.main.dialog_retreat_register.view.*
import kotlinx.android.synthetic.main.fragment_retreat_details.view.*
import pl.mftau.mftau.R
import pl.mftau.mftau.model.Retreat
import pl.mftau.mftau.utils.PermissionUtils
import pl.mftau.mftau.viewmodel.MainViewModel
import java.util.*

class RetreatDetailsFragment : Fragment() {

    private lateinit var mViewModel: MainViewModel
    private lateinit var mRetreat: Retreat

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        setHasOptionsMenu(true)
        return inflater.inflate(R.layout.fragment_retreat_details, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        activity?.let {
            mViewModel = ViewModelProviders.of(it).get(MainViewModel::class.java)
        }
        arguments?.let {
            mRetreat = RetreatDetailsFragmentArgs.fromBundle(it).retreat
            view.retreatNameET.setText(mRetreat.name)
            view.retreatCityET.setText(mRetreat.city)
            view.retreatAddressET.setText(mRetreat.address)
            view.retreatPriceET.setText(mRetreat.price.toString())
            view.beginDateText.setText(mViewModel.getDateFormatted(mRetreat.beginDate.toDate()))
            view.endDateText.setText(mViewModel.getDateFormatted(mRetreat.endDate.toDate()))
            view.retreatTypeET.setText(resources.getStringArray(R.array.retreat_types)[mRetreat.retreatType])
        }

        if (mRetreat.registerLimitDate.toDate() < Date())
            view.registerBtn.hide()

        view.registerBtn.setOnClickListener {
            showRegisterDialog()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) =
        inflater.inflate(R.menu.menu_retreat_details, menu)

    override fun onOptionsItemSelected(item: MenuItem): Boolean = when (item.itemId) {
        R.id.action_show_map -> {
            val gmmIntentUri = Uri.parse("geo:0,0?q=" + Uri.encode("${mRetreat.address} ${mRetreat.city}"))
            val mapIntent = Intent(Intent.ACTION_VIEW, gmmIntentUri)
            mapIntent.setPackage("com.google.android.apps.maps")
            startActivity(mapIntent)
            true
        }
        R.id.action_save_to_calendar -> {
            if (PermissionUtils.haveCalendarReadWritePermissions(activity!!))
                showSaveToCalendarDialog()
            else
                PermissionUtils.requestCalendarReadWritePermission(activity!!)
            true
        }
        else -> super.onOptionsItemSelected(item)
    }

    @SuppressLint("InflateParams")
    private fun showRegisterDialog() {
        val dialogView = LayoutInflater.from(context).inflate(R.layout.dialog_retreat_register, null)

        val dialog = AlertDialog.Builder(context!!)
            .setTitle(R.string.retreat_register_title)
            .setMessage(R.string.retreat_register_msg)
            .setView(dialogView)
            .setCancelable(false)
            .setPositiveButton(getString(R.string.send), null)
            .setNegativeButton(getString(R.string.cancel)) { dialog, _ -> dialog.dismiss() }
            .create()

        dialog.setOnShowListener {
            dialog.getButton(AlertDialog.BUTTON_POSITIVE).setOnClickListener {
                if (dialogView.registerNameET.text.isNullOrBlank()) {
                    dialogView.registerNameET.error = getString(R.string.empty_name_error)
                    return@setOnClickListener
                } else {
                    if (mRetreat.advancePayment) {
                        showAdvancePaymentDialog(dialogView.registerNameET.text.toString().trim())
                    } else {
                        sendRegisterEmail(dialogView.registerNameET.text.toString().trim())
                    }
                }
            }
        }
        dialog.show()
    }

    private fun showAdvancePaymentDialog(name: String) =
        AlertDialog.Builder(context!!)
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
        emailIntent.putExtra(Intent.EXTRA_SUBJECT, "<MF Tau App> Zapisy na rekolekcje ${mRetreat.name}")
        emailIntent.putExtra(
            Intent.EXTRA_TEXT, "Pokój i dobro!\n" +
                    "Chciałbym zgłosić chęć uczestnictwa w rekolekcjach ${mRetreat.name}.\n\n" +
                    "Pozdrawiam,\n$name"
        )

        try {
            startActivity(Intent.createChooser(emailIntent, getString(R.string.send_mail)))
        } catch (ex: android.content.ActivityNotFoundException) {
            Toast.makeText(context, getString(R.string.send_mail_error), Toast.LENGTH_SHORT).show()
        }
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
            putExtra(CalendarContract.Events.EVENT_LOCATION, "${mRetreat.address}, ${mRetreat.city}")
            putExtra(CalendarContract.Events.EVENT_TIMEZONE, TimeZone.getDefault().id)
        }
        startActivity(intent)
    }
}
