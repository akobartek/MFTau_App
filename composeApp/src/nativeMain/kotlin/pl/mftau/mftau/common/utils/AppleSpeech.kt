package pl.mftau.mftau.common.utils

import platform.AVFAudio.AVSpeechBoundary
import platform.AVFAudio.AVSpeechSynthesisVoice
import platform.AVFAudio.AVSpeechSynthesisVoiceQualityEnhanced
import platform.AVFAudio.AVSpeechSynthesizer
import platform.AVFAudio.AVSpeechSynthesizerDelegateProtocol
import platform.AVFAudio.AVSpeechUtterance
import platform.darwin.NSObject

class AppleSpeech : MFTauSpeech {

    private val textToSpeech = MyTextToSpeech()

    override fun setListener(onDone: () -> Unit) =
        textToSpeech.setListener(onDone)

    override fun isSpeaking(): Boolean = textToSpeech.isSpeaking()

    override fun speak(textToRead: String) = textToSpeech.speak(textToRead)

    override fun stop() = textToSpeech.stopSpeaking()

    override fun shutdown() {}
}

private class MyTextToSpeech : NSObject(), AVSpeechSynthesizerDelegateProtocol {

    private val synthesizer: AVSpeechSynthesizer = AVSpeechSynthesizer()
    private var onDone: () -> Unit = { }

    init {
        synthesizer.delegate = this
        synthesizer.usesApplicationAudioSession = false
    }

    fun setListener(onDone: () -> Unit) {
        this.onDone = onDone
    }

    fun speak(text: String) {
        val utterance = AVSpeechUtterance.speechUtteranceWithString(text)
        utterance.rate = 0.5f

        val polishVoices = AVSpeechSynthesisVoice.speechVoices().filter {
            (it as AVSpeechSynthesisVoice).language.startsWith("pl", ignoreCase = true)
        }
        polishVoices
            .firstOrNull {
                (it as AVSpeechSynthesisVoice).quality == AVSpeechSynthesisVoiceQualityEnhanced
            } ?: polishVoices.firstOrNull()
            ?.let {
                utterance.voice = it as AVSpeechSynthesisVoice
            }

        synthesizer.speakUtterance(utterance)
    }

    override fun speechSynthesizer(
        synthesizer: AVSpeechSynthesizer,
        @Suppress("PARAMETER_NAME_CHANGED_ON_OVERRIDE")
        didFinishSpeechUtterance: AVSpeechUtterance
    ) {
        onDone()
    }

    fun stopSpeaking() {
        synthesizer.stopSpeakingAtBoundary(AVSpeechBoundary.AVSpeechBoundaryImmediate)
    }

    fun isSpeaking(): Boolean = synthesizer.isSpeaking()
}