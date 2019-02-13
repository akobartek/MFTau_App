package pl.mftau.mftau.utils

import android.content.Context
import android.content.Intent
import android.text.SpannableString
import android.text.style.UnderlineSpan

fun Context.isChromeCustomTabsSupported(): Boolean {
    val serviceIntent = Intent("android.support.customtabs.action.CustomTabsService")
    serviceIntent.setPackage("com.android.chrome")
    val resolveInfos = packageManager.queryIntentServices(serviceIntent, 0)
    return !(resolveInfos == null || resolveInfos.isEmpty())
}

fun String.createUnderlinedString(): SpannableString {
    val spannable = SpannableString(this)
    spannable.setSpan(UnderlineSpan(), 0, this.length, 0)
    return spannable
}

