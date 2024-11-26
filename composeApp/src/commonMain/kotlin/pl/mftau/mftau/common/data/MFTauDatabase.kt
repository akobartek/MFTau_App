package pl.mftau.mftau.common.data

import androidx.room.ConstructedBy
import androidx.room.Database
import androidx.room.RoomDatabase
import pl.mftau.mftau.breviary.data.database.BreviaryDao
import pl.mftau.mftau.breviary.data.database.BreviaryEntity
import pl.mftau.mftau.leaders.data.db.EmausDao
import pl.mftau.mftau.leaders.domain.model.Emaus
import pl.mftau.mftau.songbook.data.db.SongBookDao
import pl.mftau.mftau.songbook.data.db.entities.PlaylistEntity
import pl.mftau.mftau.songbook.data.db.entities.PlaylistSongEntity
import pl.mftau.mftau.songbook.data.db.entities.SongEntity

@Database(
    entities = [BreviaryEntity::class, SongEntity::class, PlaylistEntity::class, PlaylistSongEntity::class, Emaus::class],
    version = 1,
    exportSchema = false
)
@ConstructedBy(MFTauDatabaseCtor::class)
abstract class MFTauDatabase : RoomDatabase() {
    abstract fun breviaryDao(): BreviaryDao
    abstract fun songBookDao(): SongBookDao
    abstract fun emausDao(): EmausDao

    companion object {
        const val DATABASE_NAME = "mftau_database.db"
    }
}