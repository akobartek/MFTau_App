package pl.mftau.mftau.utils

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.os.Build
import android.text.SpannableString
import android.text.style.UnderlineSpan
import android.view.inputmethod.InputMethodManager
import androidx.appcompat.app.AlertDialog
import androidx.core.content.ContextCompat
import androidx.navigation.findNavController
import pl.mftau.mftau.R
import java.text.SimpleDateFormat
import java.util.*
import java.util.regex.Pattern

fun Context.isChromeCustomTabsSupported(): Boolean {
    val serviceIntent = Intent("android.support.customtabs.action.CustomTabsService")
    serviceIntent.setPackage("com.android.chrome")
    val resolveInfos = packageManager.queryIntentServices(serviceIntent, 0)
    return resolveInfos.isNotEmpty()
}

fun Context.getColorsByName(vararg colorNames: String) = colorNames.map { name ->
    ContextCompat.getColor(this, resources.getIdentifier(name, "color", packageName))
}

fun String.createUnderlinedString(): SpannableString {
    val spannable = SpannableString(this)
    spannable.setSpan(UnderlineSpan(), 0, this.length, 0)
    return spannable
}

fun CharSequence.isValidEmail(): Boolean =
    android.util.Patterns.EMAIL_ADDRESS.matcher(this).matches()

fun CharSequence.isValidPassword(): Boolean {
    val passwordRegex = "((?=.*[a-z])(?=.*\\d)(?=.*[A-Z]).{6,20})"
    val pattern = Pattern.compile(passwordRegex)
    val matcher = pattern.matcher(this)
    return matcher.matches()
}

fun Date.getDateFormatted(): String =
    SimpleDateFormat("dd.MM.yyyy", Locale.getDefault()).format(this)

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
        val capabilities =
            connectivityManager?.getNetworkCapabilities(connectivityManager.activeNetwork)
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

fun Activity.hideKeyboard() =
    (getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager)
        .hideSoftInputFromWindow(currentFocus?.windowToken, 0)
