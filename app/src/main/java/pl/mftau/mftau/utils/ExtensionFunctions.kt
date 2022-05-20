package pl.mftau.mftau.utils

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.net.ConnectivityManager
import android.net.Uri
import android.os.Build
import android.text.SpannableString
import android.text.style.UnderlineSpan
import android.util.Log
import android.util.TypedValue
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.Transformation
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.browser.customtabs.CustomTabColorSchemeParams
import androidx.browser.customtabs.CustomTabsIntent
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import pl.mftau.mftau.R
import pl.mftau.mftau.databinding.DialogSongChangeTextSizeBinding
import java.text.SimpleDateFormat
import java.util.*
import java.util.regex.Pattern

fun Context.showShortToast(stringRes: Int, oldToast: Toast? = null): Toast {
    oldToast?.cancel()
    val toast = Toast.makeText(this, stringRes, Toast.LENGTH_SHORT)
    toast.show()
    return toast
}

fun Context.openWebsiteInChromeCustomTabs(website: String) {
    CustomTabsIntent.Builder().apply {
        val params = CustomTabColorSchemeParams.Builder().apply {
            val color =
                if (PreferencesManager.getNightMode()) Color.parseColor("#28292e")
                else Color.WHITE
            setNavigationBarColor(color)
            setToolbarColor(color)
            setSecondaryToolbarColor(color)
        }.build()
        setDefaultColorSchemeParams(params)
    }.build().launchUrl(this, Uri.parse(website))
}

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
    SimpleDateFormat("dd.MM.yyyy", Locale.US).format(this)

fun Activity.showNoInternetDialogWithTryAgain(function: () -> Unit): Unit =
    MaterialAlertDialogBuilder(this)
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
    MaterialAlertDialogBuilder(this)
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

fun View.expand(onAnimationEnd: () -> Unit = {}) {
    val matchParentMeasureSpec =
        View.MeasureSpec.makeMeasureSpec((parent as View).width, View.MeasureSpec.EXACTLY)
    val wrapContentMeasureSpec = View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED)
    measure(matchParentMeasureSpec, wrapContentMeasureSpec)
    val targetHeight = measuredHeight

    visibility = View.VISIBLE
    val animation = object : Animation() {
        override fun applyTransformation(interpolatedTime: Float, t: Transformation) {
            layoutParams.height = if (interpolatedTime == 1f)
                ViewGroup.LayoutParams.WRAP_CONTENT
            else
                (targetHeight * interpolatedTime).toInt()
            requestLayout()
        }

        override fun willChangeBounds(): Boolean = true
    }
    animation.duration = 444
    animation.setAnimationListener(object : Animation.AnimationListener {
        override fun onAnimationStart(p0: Animation?) {}

        override fun onAnimationEnd(p0: Animation?) {
            onAnimationEnd()
        }

        override fun onAnimationRepeat(p0: Animation?) {}
    })
    startAnimation(animation)
}

fun View.collapse() {
    val initialHeight = measuredHeight

    val animation = object : Animation() {
        override fun applyTransformation(interpolatedTime: Float, t: Transformation) {
            if (interpolatedTime == 1f) {
                visibility = View.GONE
            } else {
                layoutParams.height = initialHeight - (initialHeight * interpolatedTime).toInt()
                requestLayout()
            }
        }

        override fun willChangeBounds(): Boolean = true
    }
    animation.duration = 444
    startAnimation(animation)
}

fun Fragment.showChangeTextSizeDialog(currentSize: Float, updateTextSize: (Float) -> Unit) {
    var toast: Toast? = null
    val minTextSize = 12f
    val maxTextSize = 32f
    var newSize = currentSize

    val dialogBinding = DialogSongChangeTextSizeBinding.inflate(layoutInflater)
    dialogBinding.sizeDownBtn.setOnClickListener {
        Log.d("xDDDD", "showChangeTextSizeDialog: $newSize, $currentSize")
        if (newSize != minTextSize) {
            newSize -= 2
            dialogBinding.exampleText.setTextSize(TypedValue.COMPLEX_UNIT_SP, newSize)
        } else toast = requireContext().showShortToast(R.string.min_size_msg, toast)
    }
    dialogBinding.sizeUpBtn.setOnClickListener {
        if (newSize != maxTextSize) {
            newSize += 2
            dialogBinding.exampleText.setTextSize(TypedValue.COMPLEX_UNIT_SP, newSize)
        } else toast = requireContext().showShortToast(R.string.max_size_msg, toast)
    }

    MaterialAlertDialogBuilder(requireContext())
        .setTitle(R.string.change_font_size)
        .setView(dialogBinding.root)
        .setPositiveButton(R.string.save) { dialog, _ ->
            dialog.dismiss()
            if (newSize != currentSize) updateTextSize(newSize)
        }
        .setNegativeButton(R.string.cancel) { dialog, _ -> dialog.dismiss() }
        .create()
        .show()
}