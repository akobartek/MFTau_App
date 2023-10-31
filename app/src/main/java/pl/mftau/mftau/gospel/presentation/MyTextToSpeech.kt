package pl.mftau.mftau.gospel.presentation

import android.content.Context
import android.speech.tts.TextToSpeech
import android.speech.tts.UtteranceProgressListener

class MyTextToSpeech(val context: Context, listener: OnInitListener) :
    TextToSpeech(context, listener) {

    fun setProgressListener(onDone: () -> Unit) {
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