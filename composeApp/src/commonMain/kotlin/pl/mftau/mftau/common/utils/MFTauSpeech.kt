package pl.mftau.mftau.common.utils

interface MFTauSpeech {
    fun setListener(onDone: () -> Unit)
    fun isSpeaking(): Boolean
    fun speak(textToRead: String)
    fun stop()
    fun shutdown()
}