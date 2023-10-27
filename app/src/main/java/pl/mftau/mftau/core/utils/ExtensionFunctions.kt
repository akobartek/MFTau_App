package pl.mftau.mftau.core.utils

import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.net.Uri
import androidx.browser.customtabs.CustomTabColorSchemeParams
import androidx.browser.customtabs.CustomTabsIntent

fun Context.openWebsiteInChromeCustomTabs(website: String) {
    CustomTabsIntent.Builder().apply {
        val params = CustomTabColorSchemeParams.Builder().apply {
            val color = Color.parseColor("#28292e")
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
    val resolveInfo = packageManager.resolveService(serviceIntent, 0)
    return resolveInfo != null
}