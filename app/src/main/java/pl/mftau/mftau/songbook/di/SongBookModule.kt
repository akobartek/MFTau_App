package pl.mftau.mftau.songbook.di

import org.koin.dsl.module
import pl.mftau.mftau.songbook.data.MfTauSongBookRepository
import pl.mftau.mftau.songbook.domain.SongBookRepository
import pl.mftau.mftau.songbook.presentation.SongBookScreenModel

val songBookModule = module {
    single<SongBookRepository> { MfTauSongBookRepository() }
    factory { SongBookScreenModel(get(), get()) }
}