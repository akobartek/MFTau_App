package pl.mftau.mftau.songbook.di

import org.koin.dsl.module
import pl.mftau.mftau.common.data.MFTauDatabase
import pl.mftau.mftau.songbook.data.DbSongBookRepository
import pl.mftau.mftau.songbook.data.FirebasePlaylistRepository
import pl.mftau.mftau.songbook.data.MFTauSongBookRepository
import pl.mftau.mftau.songbook.domain.db.SongBookDao
import pl.mftau.mftau.songbook.domain.repository.RemotePlaylistRepository
import pl.mftau.mftau.songbook.domain.repository.TextsSongBookRepository
import pl.mftau.mftau.songbook.domain.usecase.DeletePlaylistUseCase
import pl.mftau.mftau.songbook.domain.usecase.DeleteSongFromPlaylist
import pl.mftau.mftau.songbook.domain.usecase.DeleteSongUseCase
import pl.mftau.mftau.songbook.domain.usecase.GetPlaylistsUseCase
import pl.mftau.mftau.songbook.domain.usecase.GetPlaylistFromDbUseCase
import pl.mftau.mftau.songbook.domain.usecase.GetSongBookUseCase
import pl.mftau.mftau.songbook.domain.usecase.GetUserSongsUseCase
import pl.mftau.mftau.songbook.domain.usecase.ImportPlaylistUseCase
import pl.mftau.mftau.songbook.domain.usecase.MarkSongAsFavouriteUseCase
import pl.mftau.mftau.songbook.domain.usecase.SavePlaylistUseCase
import pl.mftau.mftau.songbook.domain.usecase.SaveSongUseCase
import pl.mftau.mftau.songbook.domain.usecase.SaveSongsInPlaylistUseCase
import pl.mftau.mftau.songbook.domain.usecase.SharePlaylistUseCase
import pl.mftau.mftau.songbook.domain.usecase.UpdateSongsInPlaylistUseCase
import pl.mftau.mftau.songbook.presentation.screenmodels.UserSongsListScreenModel
import pl.mftau.mftau.songbook.presentation.screenmodels.PlaylistDetailsScreenModel
import pl.mftau.mftau.songbook.presentation.screenmodels.PlaylistsListScreenModel
import pl.mftau.mftau.songbook.presentation.screenmodels.SongBookListScreenModel

val songBookModule = module {
    single<SongBookDao> {
        val db = get<MFTauDatabase>()
        db.songBookDao()
    }

    single<TextsSongBookRepository> { MFTauSongBookRepository() }

    single { DbSongBookRepository(get()) }

    single<RemotePlaylistRepository> { FirebasePlaylistRepository(get()) }


    single { GetSongBookUseCase(get(), get()) }

    single { SaveSongUseCase(get()) }

    single { DeleteSongUseCase(get()) }

    single { MarkSongAsFavouriteUseCase(get()) }

    single { SavePlaylistUseCase(get()) }

    single { SaveSongsInPlaylistUseCase(get()) }

    single { GetPlaylistsUseCase(get()) }

    single { GetPlaylistFromDbUseCase(get(), get()) }

    single { UpdateSongsInPlaylistUseCase(get()) }

    single { DeletePlaylistUseCase(get()) }

    single { ImportPlaylistUseCase(get(), get()) }

    single { SharePlaylistUseCase(get()) }

    single { DeleteSongFromPlaylist(get()) }

    single { GetUserSongsUseCase(get()) }


    factory { SongBookListScreenModel(get(), get(), get(), get(), get(), get()) }

    factory { PlaylistsListScreenModel(get(), get()) }

    factory { PlaylistDetailsScreenModel(get(), get(), get(), get(), get(), get(), get()) }

    factory { UserSongsListScreenModel(get(), get(), get()) }
}