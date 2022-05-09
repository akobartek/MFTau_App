package pl.mftau.mftau.db.daos

import androidx.lifecycle.LiveData
import androidx.room.*
import pl.mftau.mftau.db.entities.DrawEntity
import java.util.*

@Dao
interface DrawsDao {

    @Query("SELECT * FROM draws_table;")
    fun getAll(): LiveData<List<DrawEntity>>

    @Query("SELECT MAX(number_of_draw) FROM draws_table;")
    suspend fun getMaxNumberOfDraw(): Int?

    @Query("SELECT MAX(draw_date) FROM draws_table;")
    suspend fun getLastDrawDate(): Date

    @Query("SELECT draws FROM draws_table WHERE number_of_draw = (SELECT MAX(number_of_draw) FROM draws_table);")
    fun getLastDraws(): LiveData<List<String>>

    @Query("SELECT odd_person_id FROM draws_table WHERE number_of_draw = (SELECT MAX(number_of_draw) FROM draws_table);")
    suspend fun getOddPersonId(): String?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertDraw(draw: DrawEntity)

    @Delete
    suspend fun deleteDraws(vararg draw: DrawEntity)

    @Query("DELETE FROM draws_table WHERE number_of_draw = (SELECT MAX(number_of_draw) FROM draws_table);")
    suspend fun deleteLastDraw()

    @Query("DELETE FROM draws_table;")
    suspend fun deleteAllDraws()
}