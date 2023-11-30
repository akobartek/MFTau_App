package pl.mftau.mftau.common.data

import androidx.room.Database
import androidx.room.RoomDatabase
import pl.mftau.mftau.breviary.domain.db.BreviaryDao
import pl.mftau.mftau.breviary.domain.db.entities.BreviaryEntity
import pl.mftau.mftau.leaders.domain.db.EmausDao
import pl.mftau.mftau.leaders.domain.model.Emaus
import pl.mftau.mftau.songbook.domain.db.SongBookDao
import pl.mftau.mftau.songbook.domain.db.entities.PlaylistEntity
import pl.mftau.mftau.songbook.domain.db.entities.PlaylistSongEntity
import pl.mftau.mftau.songbook.domain.db.entities.SongEntity

@Database(
    entities = [BreviaryEntity::class, SongEntity::class, PlaylistEntity::class, PlaylistSongEntity::class, Emaus::class],
    version = 1,
    exportSchema = false
)
abstract class MFTauDatabase : RoomDatabase() {
    abstract fun breviaryDao(): BreviaryDao
    abstract fun songBookDao(): SongBookDao
    abstract fun emausDao(): EmausDao
}