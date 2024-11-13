package pl.mftau.mftau.breviary.data.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface BreviaryDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertBreviary(entity: BreviaryEntity): Long

    @Query("SELECT * FROM breviary WHERE date = :date")
    suspend fun loadBreviary(date: Long): BreviaryEntity?

    @Query("SELECT EXISTS(SELECT * FROM breviary WHERE date = :date)")
    suspend fun checkIfExists(date: Long): Boolean

    @Query("DELETE FROM breviary WHERE date <= :date")
    suspend fun clearBreviary(date: Long)
}