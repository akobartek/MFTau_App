package pl.mftau.mftau.songbook.di

import org.koin.dsl.module
import pl.mftau.mftau.core.data.MFTauDatabase
import pl.mftau.mftau.songbook.data.DbSongBookRepository
import pl.mftau.mftau.songbook.data.MFTauSongBookRepository
import pl.mftau.mftau.songbook.domain.db.SongBookDao
import pl.mftau.mftau.songbook.domain.repository.TextsSongBookRepository
import pl.mftau.mftau.songbook.domain.usecase.GetSongBookUseCase
import pl.mftau.mftau.songbook.domain.usecase.MarkSongAsFavouriteUseCase
import pl.mftau.mftau.songbook.presentation.SongBookScreenModel

val songBookModule = module {
    single<SongBookDao> {
        val db = get<MFTauDatabase>()
        db.songBookDao()
    }

    single<TextsSongBookRepository> { MFTauSongBookRepository() }

    single { DbSongBookRepository(get()) }

    single { GetSongBookUseCase(get(), get()) }

    single { MarkSongAsFavouriteUseCase(get()) }

    factory { SongBookScreenModel(get(), get(), get()) }
}