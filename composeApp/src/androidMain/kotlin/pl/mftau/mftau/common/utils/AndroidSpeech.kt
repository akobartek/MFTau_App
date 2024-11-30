package pl.mftau.mftau.common.utils

import android.content.Context
import android.os.Bundle
import android.speech.tts.TextToSpeech
import android.speech.tts.TextToSpeech.OnInitListener
import android.speech.tts.UtteranceProgressListener
import java.util.Locale

class AndroidSpeech(context: Context) : MFTauSpeech, OnInitListener {

    private val textToSpeech = MyTextToSpeech(context, this)

    override fun onInit(status: Int) {
        if (status != TextToSpeech.ERROR) {
            textToSpeech.apply {
                language = Locale("pl_PL")
                setSpeechRate(0.9f)
            }
        }
    }

    override fun setListener(onDone: () -> Unit) {
        textToSpeech.setProgressListener(onDone)
    }

    override fun isSpeaking(): Boolean = textToSpeech.isSpeaking

    override fun speak(textToRead: String) {
        textToSpeech.speak(
            textToRead,
            TextToSpeech.QUEUE_FLUSH,
            Bundle().apply {
                putString(TextToSpeech.Engine.KEY_PARAM_UTTERANCE_ID, "MFTauUtteranceId")
            },
            "MFTauUtteranceId",
        )
    }

    override fun stop() {
        textToSpeech.stop()
    }

    override fun shutdown() {
        textToSpeech.stop()
        textToSpeech.shutdown()
    }
}

private class MyTextToSpeech(context: Context, listener: OnInitListener) :
    TextToSpeech(context, listener, "com.google.android.tts") {

    fun setProgressListener(onDone: () -> Unit) {
        setOnUtteranceProgressListener(object : UtteranceProgressListener() {
            override fun onDone(utteranceId: String?) {
                onDone()
            }

            @Deprecated("Deprecated in Java")
            override fun onError(utteranceId: String?) {
            }

            override fun onStart(utteranceId: String?) {}
        })
    }
}