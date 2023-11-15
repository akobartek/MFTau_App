package pl.mftau.mftau.gospel.di

import android.speech.tts.TextToSpeech
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module
import pl.mftau.mftau.gospel.data.GospelRepositoryImpl
import pl.mftau.mftau.gospel.domain.GospelRepository
import pl.mftau.mftau.gospel.presentation.GospelScreenModel
import pl.mftau.mftau.gospel.presentation.MyTextToSpeech
import java.util.Locale

val gospelModule = module {
    single {
        var textToSpeech: TextToSpeech? = null
        textToSpeech = MyTextToSpeech(androidApplication()) { status ->
            if (status != TextToSpeech.ERROR) {
                textToSpeech?.setLanguage(Locale("pl_PL"))
                textToSpeech?.setSpeechRate(0.9f)
            }
        }
        textToSpeech
    }

    single<GospelRepository> { GospelRepositoryImpl() }

    factory { GospelScreenModel(get()) }
}