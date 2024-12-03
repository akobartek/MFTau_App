package pl.mftau.mftau.common.presentation

class ApplePdfOpener: PdfOpener {

    override suspend fun openPdf(
        fileName: String,
        uri: String,
        bytes: ByteArray,
    ): Boolean = true
}