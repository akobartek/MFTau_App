package pl.mftau.mftau.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import pl.mftau.mftau.db.converters.ListConverters
import pl.mftau.mftau.db.converters.Converters
import pl.mftau.mftau.db.daos.DrawsDao
import pl.mftau.mftau.db.daos.MembersDao
import pl.mftau.mftau.db.entities.DrawEntity
import pl.mftau.mftau.db.entities.MemberEntity

@Database(entities = [MemberEntity::class, DrawEntity:: class], version = 1)
@TypeConverters(Converters::class, ListConverters::class)
abstract class EmausDatabase : RoomDatabase() {

    abstract fun drawsDao(): DrawsDao
    abstract fun membersDao(): MembersDao

    companion object {
        private var INSTANCE: EmausDatabase? = null

        fun getInstance(context: Context): EmausDatabase? {
            if (INSTANCE == null) {
                synchronized(EmausDatabase::class) {
                    INSTANCE = Room.databaseBuilder(context.applicationContext,
                            EmausDatabase::class.java, "emaus_database.db").build()
                }
            }
            return INSTANCE
        }
    }
}