package pl.mftau.mftau.model.local_db

import pl.mftau.mftau.db.entities.SongEntity
import pl.mftau.mftau.db.entities.SongFavouriteEntity
import pl.mftau.mftau.db.entities.SongPlaylistEntity

data class SongBook(
    val userSongs: List<SongEntity>,
    val playlist: List<SongPlaylistEntity>,
    val favourites: List<SongFavouriteEntity>
)