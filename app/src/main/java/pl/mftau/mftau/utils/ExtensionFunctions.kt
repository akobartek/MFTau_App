package pl.mftau.mftau.utils

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.os.Build
import android.text.SpannableString
import android.text.style.UnderlineSpan
import androidx.appcompat.app.AlertDialog
import androidx.navigation.findNavController
import pl.mftau.mftau.R

fun Context.isChromeCustomTabsSupported(): Boolean {
    val serviceIntent = Intent("android.support.customtabs.action.CustomTabsService")
    serviceIntent.setPackage("com.android.chrome")
    val resolveInfos = packageManager.queryIntentServices(serviceIntent, 0)
    return resolveInfos.isNotEmpty()
}

fun String.createUnderlinedString(): SpannableString {
    val spannable = SpannableString(this)
    spannable.setSpan(UnderlineSpan(), 0, this.length, 0)
    return spannable
}

fun Activity.showNoInternetDialogWithTryAgain(function: () -> Unit): Unit =
    AlertDialog.Builder(this)
        .setTitle(R.string.no_internet_title)
        .setMessage(R.string.no_internet_reconnect_message)
        .setCancelable(false)
        .setPositiveButton(R.string.try_again) { dialog, _ ->
            dialog.dismiss()
            if (checkNetworkConnection()) function()
            else if (!isFinishing && !isDestroyed) showNoInternetDialogWithTryAgain(function)
        }
        .setNegativeButton(R.string.cancel) { dialog, _ ->
            dialog.dismiss()
            findNavController(R.id.navHostFragment).navigateUp()
        }
        .create()
        .show()

fun Activity.showNoInternetDialogDataOutOfDate(): Unit =
    AlertDialog.Builder(this)
        .setTitle(R.string.no_internet_title)
        .setMessage(R.string.no_internet_data_message)
        .setPositiveButton(R.string.ok) { dialog, _ -> dialog.dismiss() }
        .create()
        .show()

@Suppress("DEPRECATION")
fun Activity.checkNetworkConnection(): Boolean {
    val connectivityManager = getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager?
    return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
        val capabilities = connectivityManager?.getNetworkCapabilities(connectivityManager.activeNetwork)
        capabilities != null
    } else {
        val activeNetworkInfo = connectivityManager?.activeNetworkInfo
        activeNetworkInfo != null && activeNetworkInfo.isConnected
    }
}

fun Activity.tryToRunFunctionOnInternet(function: () -> Unit) {
    if (checkNetworkConnection()) {
        try {
            function()
        } catch (exc: Throwable) {
            showNoInternetDialogWithTryAgain { function() }
        }
    } else {
        showNoInternetDialogWithTryAgain { function() }
    }
}
