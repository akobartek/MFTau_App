package pl.mftau.mftau.common.presentation

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import platform.Foundation.NSURL
import platform.UIKit.UIApplication
import platform.UIKit.UIDocumentInteractionController
import platform.UIKit.UIDocumentInteractionControllerDelegateProtocol
import platform.UIKit.UIViewController
import platform.darwin.NSObject

class ApplePdfOpener : PdfOpener {

    private val pdfOpener = MyPdfOpener()

    override suspend fun openPdf(
        fileName: String,
        uri: String,
        bytes: ByteArray,
    ): Boolean = pdfOpener.openPdf(uri)
}

private class MyPdfOpener : NSObject(), UIDocumentInteractionControllerDelegateProtocol {

    suspend fun openPdf(uri: String): Boolean = withContext(Dispatchers.Main) {
        UIApplication.sharedApplication.keyWindow?.rootViewController?.let {
            val documentInteractionController =
                UIDocumentInteractionController.interactionControllerWithURL(NSURL(string = uri))
            documentInteractionController.delegate = this@MyPdfOpener
            documentInteractionController.presentPreviewAnimated(true)
            true
        } ?: false
    }

    override fun documentInteractionControllerViewControllerForPreview(controller: UIDocumentInteractionController): UIViewController {
        return UIApplication.sharedApplication.keyWindow!!.rootViewController!!
    }
}