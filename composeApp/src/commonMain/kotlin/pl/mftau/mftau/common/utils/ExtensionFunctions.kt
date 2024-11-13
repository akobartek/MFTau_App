package pl.mftau.mftau.common.utils

fun <T> List<T>.swap(index1: Int, index2: Int): List<T> {
    val list = this.toMutableList()
    val tmp = this[index1]
    list[index1] = this[index2]
    list[index2] = tmp
    return list
}

fun Context.getBitmapFromUri(uri: Uri): Bitmap {
    return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
        ImageDecoder.decodeBitmap(ImageDecoder.createSource(contentResolver, uri))
    } else {
        @Suppress("DEPRECATION")
        MediaStore.Images.Media.getBitmap(contentResolver, uri)
    }
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

fun TextToSpeech.speak(textToRead: String) {
    speak(textToRead, TextToSpeech.QUEUE_FLUSH, Bundle().apply {
        putString(TextToSpeech.Engine.KEY_PARAM_UTTERANCE_ID, "MFTauUtteranceId")
    }, "MFTauUtteranceId")
}