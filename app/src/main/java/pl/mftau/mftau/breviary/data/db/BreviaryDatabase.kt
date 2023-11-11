package pl.mftau.mftau.breviary.data.db

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [BreviaryEntity::class], version = 1)
abstract class BreviaryDatabase : RoomDatabase() {
    abstract fun breviaryDao(): BreviaryDao
}