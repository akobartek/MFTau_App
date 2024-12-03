package pl.mftau.mftau.common.presentation

import android.content.ActivityNotFoundException
import android.content.Context
import android.content.Intent
import android.util.Log
import androidx.core.content.FileProvider
import mftau.composeapp.generated.resources.Res
import mftau.composeapp.generated.resources.choose_pdf_viewer
import org.jetbrains.compose.resources.getString
import java.io.File
import java.io.FileOutputStream

class AndroidPdfOpener(private val context: Context) : PdfOpener {

    override suspend fun openPdf(
        fileName: String,
        uri: String,
        bytes: ByteArray,
    ): Boolean =
        context.openPdf(bytes, fileName)?.let { intent ->
            val chooserIntent =
                Intent.createChooser(intent, getString(Res.string.choose_pdf_viewer))
            chooserIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            context.startActivity(chooserIntent)
            true
        } ?: false

    private fun Context.openPdf(bytes: ByteArray, fileName: String): Intent? {
        val file = File(cacheDir, fileName)
        FileOutputStream(file).use { output ->
            output.write(bytes)
            output.flush()
        }

        val uri = FileProvider.getUriForFile(this, "$packageName.provider", file)
        return try {
            uri?.let {
                val intent = Intent(Intent.ACTION_VIEW)
                intent.data = it
                intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)
                intent
            }
        } catch (exc: ActivityNotFoundException) {
            Log.e("openPdf", exc.toString())
            return null
        }
    }
}