package pl.mftau.mftau.gospel.presentation

import android.content.Context
import android.speech.tts.TextToSpeech
import android.speech.tts.UtteranceProgressListener
import android.widget.Toast
import pl.mftau.mftau.R
import java.util.Locale

class MyTextToSpeech(val context: Context, listener: OnInitListener) :
    TextToSpeech(context, listener) {

    fun setup(onDone: () -> Unit) {
        val errorToast = Toast.makeText(
            context, context.getString(R.string.language_not_supported), Toast.LENGTH_LONG
        )

        val result = setLanguage(Locale("pl_PL"))
        setSpeechRate(0.9f)

        if (result == LANG_MISSING_DATA || result == LANG_NOT_SUPPORTED)
            errorToast.show()

        setOnUtteranceProgressListener(object : UtteranceProgressListener() {
            override fun onDone(utteranceId: String?) {
                onDone()
            }

            @Deprecated("This method is deprecated, but is required to implement")
            override fun onError(utteranceId: String?) {
            }

            override fun onStart(utteranceId: String?) {}
        })
    }
}