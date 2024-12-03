package pl.mftau.mftau.common.presentation

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.launch
import mftau.composeapp.generated.resources.Res
import org.jetbrains.compose.resources.ExperimentalResourceApi

interface PdfOpener {
    suspend fun openPdf(fileName: String, uri: String, bytes: ByteArray): Boolean
}

@OptIn(ExperimentalResourceApi::class)
fun CoroutineScope.launchPdf(
    pdfOpener: PdfOpener,
    fileName: String,
    onFailure: () -> Unit,
) = launch(Dispatchers.IO) {
    val result = try {
        pdfOpener.openPdf(
            fileName = fileName,
            uri = Res.getUri("files/$fileName"),
            bytes = Res.readBytes("files/$fileName"),
        )
    } catch (exception: Exception) {
        false
    }
    if (result.not())
        onFailure()
}