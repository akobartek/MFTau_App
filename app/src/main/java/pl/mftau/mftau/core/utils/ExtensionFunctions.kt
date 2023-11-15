package pl.mftau.mftau.core.utils

import android.content.ActivityNotFoundException
import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.Color
import android.graphics.ImageDecoder
import android.net.Uri
import android.os.Build
import android.provider.MediaStore
import android.util.Log
import android.widget.Toast
import androidx.browser.customtabs.CustomTabColorSchemeParams
import androidx.browser.customtabs.CustomTabsIntent
import androidx.core.content.FileProvider
import pl.mftau.mftau.R
import java.io.File
import java.io.FileOutputStream

fun CharSequence.isValidEmail(): Boolean =
    android.util.Patterns.EMAIL_ADDRESS.matcher(this).matches()

fun Context.showShortToast(msgId: Int) {
    Toast.makeText(this, getString(msgId), Toast.LENGTH_SHORT).show()
}

fun Context.getBitmapFromUri(uri: Uri): Bitmap {
    return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
        ImageDecoder.decodeBitmap(ImageDecoder.createSource(contentResolver, uri))
    } else {
        @Suppress("DEPRECATION")
        MediaStore.Images.Media.getBitmap(contentResolver, uri)
    }
}

fun Context.openWebsiteInChromeCustomTabsIfSupported(website: String) {
    if (isChromeCustomTabsSupported())
        openWebsiteInChromeCustomTabs(website)
    else
        startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(website)))
}

private fun Context.openWebsiteInChromeCustomTabs(website: String) {
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

private fun Context.isChromeCustomTabsSupported(): Boolean {
    val serviceIntent = Intent("android.support.customtabs.action.CustomTabsService")
    serviceIntent.setPackage("com.android.chrome")
    val resolveInfo = packageManager.resolveService(serviceIntent, 0)
    return resolveInfo != null
}

fun Context.openPdf(fileName: String): Boolean {
    val inputStream = assets.open(fileName)
    inputStream.use { stream ->
        val file = File(cacheDir, fileName)
        FileOutputStream(file).use { output ->
            val buffer = ByteArray(4 * 1024) // or other buffer size
            var read: Int
            while (stream.read(buffer).also { read = it } != -1) {
                output.write(buffer, 0, read)
            }
            output.flush()
        }
    }
    val cacheFile = File(cacheDir, fileName)

    val uri = FileProvider.getUriForFile(this, "$packageName.provider", cacheFile)
    try {
        if (uri != null) {
            val pdfViewIntent = Intent(Intent.ACTION_VIEW)
            pdfViewIntent.data = uri
            pdfViewIntent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)
            startActivity(
                Intent.createChooser(
                    pdfViewIntent,
                    getString(R.string.choose_pdf_viewer)
                )
            )
            return true
        }
        return false
    } catch (exc: ActivityNotFoundException) {
        Log.e("openPdf", exc.toString())
        return false
    }
}