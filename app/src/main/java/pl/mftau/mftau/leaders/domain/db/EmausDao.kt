package pl.mftau.mftau.leaders.domain.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow
import pl.mftau.mftau.leaders.domain.model.Emaus

@Dao
interface EmausDao {
    @Query("SELECT * FROM emaus")
    suspend fun getAllDraws(): List<Emaus>

    @Query("SELECT * FROM emaus WHERE draw_time = (SELECT MAX(draw_time) FROM emaus);")
    fun getLastDraw(): Flow<List<Emaus>>

    @Insert(onConflict = OnConflictStrategy.ABORT)
    suspend fun insertDraws(emauses: List<Emaus>)

    @Query("DELETE FROM emaus WHERE draw_time = (SELECT MAX(draw_time) FROM emaus);")
    suspend fun deleteLastDraw(): Int

    @Query("DELETE FROM emaus;")
    suspend fun deleteAllDraws(): Int
}