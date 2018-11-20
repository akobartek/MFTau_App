package pl.mftau.mftau.db.daos

import androidx.lifecycle.LiveData
import androidx.room.*
import pl.mftau.mftau.db.entities.MemberEntity

@Dao
interface MembersDao {

    @Query("SELECT * FROM members_table;")
    fun getAllMembers(): LiveData<List<MemberEntity>>

    @Query("SELECT name FROM members_table WHERE id = :memberId;")
    fun getNameById(memberId: String): String

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertMember(member: MemberEntity)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertAllMembers(vararg member: MemberEntity)

    @Update
    fun updateMember(member: MemberEntity)

    @Update
    fun updateAllMembers(vararg member: MemberEntity)

    @Delete
    fun deleteMembers(vararg member: MemberEntity)

    @Query("DELETE FROM members_table")
    fun deleteAllMembers()
}