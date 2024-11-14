package pl.mftau.mftau.songbook.di

import org.koin.compose.viewmodel.dsl.viewModel
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
import pl.mftau.mftau.songbook.presentation.songs.UserSongsListScreenModel
import pl.mftau.mftau.songbook.presentation.playlists.PlaylistDetailsViewModel
import pl.mftau.mftau.songbook.presentation.playlists.PlaylistsViewModel
import pl.mftau.mftau.songbook.presentation.songs.SongBookListScreenModel

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
    factory { SongBookListScreenModel(get(), get(), get(), get(), get(), get()) }

    factory { SavePlaylistUseCase(get()) }
    factory { SaveSongsInPlaylistUseCase(get()) }
    factory { GetPlaylistsUseCase(get()) }
    factory { GetPlaylistFromDbUseCase(get(), get()) }
    factory { UpdateSongsInPlaylistUseCase(get()) }
    factory { DeletePlaylistUseCase(get()) }
    factory { ImportPlaylistUseCase(get(), get()) }
    factory { SharePlaylistUseCase(get()) }
    factory { DeleteSongFromPlaylist(get()) }
    viewModel { PlaylistsViewModel(get(), get()) }
    viewModel { PlaylistDetailsViewModel(get(), get(), get(), get(), get(), get(), get()) }


    single { GetUserSongsUseCase(get()) }
    factory { UserSongsListScreenModel(get(), get(), get()) }
}