package pl.mftau.mftau.breviary.data.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import pl.mftau.mftau.breviary.domain.model.BreviaryEntity

@Dao
interface BreviaryDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertBreviary(entity: BreviaryEntity): Long

    @Query("SELECT :value FROM breviary WHERE date = :date")
    suspend fun loadBreviary(value: String, date: Long): String

    @Query("DELETE FROM breviary WHERE date < :date")
    suspend fun clearBreviary(date: Long)
}