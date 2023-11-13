package pl.mftau.mftau.breviary.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import pl.mftau.mftau.breviary.domain.model.BreviaryEntity

@Database(entities = [BreviaryEntity::class], version = 1, exportSchema = false)
abstract class BreviaryDatabase : RoomDatabase() {
    abstract fun breviaryDao(): BreviaryDao
}