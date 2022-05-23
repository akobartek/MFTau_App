package pl.mftau.mftau.db.daos

import androidx.lifecycle.LiveData
import androidx.room.*
import pl.mftau.mftau.db.entities.MemberEntity

@Dao
interface MembersDao {

    @Query("SELECT * FROM members;")
    fun getAllMembers(): LiveData<List<MemberEntity>>

    @Query("SELECT name FROM members WHERE id = :memberId;")
    suspend fun getNameById(memberId: String): String

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertMember(member: MemberEntity)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertAllMembers(vararg member: MemberEntity)

    @Update
    suspend fun updateMember(member: MemberEntity)

    @Update
    suspend fun updateAllMembers(vararg member: MemberEntity)

    @Delete
    suspend fun deleteMembers(vararg member: MemberEntity)

    @Query("DELETE FROM members")
    suspend fun deleteAllMembers()
}