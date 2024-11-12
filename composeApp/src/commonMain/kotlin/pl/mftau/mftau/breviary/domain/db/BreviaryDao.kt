package pl.mftau.mftau.breviary.domain.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import pl.mftau.mftau.breviary.domain.db.entities.BreviaryEntity

@Dao
interface BreviaryDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertBreviary(entity: BreviaryEntity): Long

    @Query("SELECT * FROM breviary WHERE date = :date")
    suspend fun loadBreviary(date: Long): BreviaryEntity?

    @Query("DELETE FROM breviary WHERE date <= :date")
    suspend fun clearBreviary(date: Long)
}