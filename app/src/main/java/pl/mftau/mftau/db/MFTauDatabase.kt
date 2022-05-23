package pl.mftau.mftau.db

import android.content.Context
import androidx.annotation.WorkerThread
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import pl.mftau.mftau.db.converters.Converters
import pl.mftau.mftau.db.converters.ListConverters
import pl.mftau.mftau.db.daos.DrawsDao
import pl.mftau.mftau.db.daos.MembersDao
import pl.mftau.mftau.db.daos.SongBookDao
import pl.mftau.mftau.db.entities.*

@Database(
    entities = [MemberEntity::class, DrawEntity::class, SongEntity::class, SongPlaylistEntity::class, SongFavouriteEntity::class],
    version = 1,
    exportSchema = false
)
@TypeConverters(Converters::class, ListConverters::class)
abstract class MFTauDatabase : RoomDatabase() {

    abstract fun drawsDao(): DrawsDao
    abstract fun membersDao(): MembersDao
    abstract fun songBookDao(): SongBookDao

    companion object {
        private var INSTANCE: MFTauDatabase? = null

        @WorkerThread
        fun getInstance(context: Context): MFTauDatabase? {
            if (INSTANCE == null) {
                synchronized(MFTauDatabase::class) {
                    INSTANCE = Room.databaseBuilder(
                        context.applicationContext,
                        MFTauDatabase::class.java, "mf_tau_database.db"
                    ).build()
                }
            }
            return INSTANCE
        }
    }
}