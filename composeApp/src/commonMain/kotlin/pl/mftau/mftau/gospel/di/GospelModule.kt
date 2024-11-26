package pl.mftau.mftau.gospel.di

import org.koin.dsl.module
import pl.mftau.mftau.gospel.data.GospelRepositoryImpl
import pl.mftau.mftau.gospel.domain.GospelRepository
import pl.mftau.mftau.gospel.presentation.GospelViewModel

val gospelModule = module {
    // TODO
//    single {
//        var textToSpeech: TextToSpeech? = null
//        textToSpeech = MyTextToSpeech(androidApplication()) { status ->
//            if (status != TextToSpeech.ERROR) {
//                textToSpeech?.setLanguage(Locale("pl_PL"))
//                textToSpeech?.setSpeechRate(0.9f)
//            }
//        }
//        textToSpeech
//    }

    single<GospelRepository> { GospelRepositoryImpl() }

    factory { GospelViewModel(get(), get()) }
}