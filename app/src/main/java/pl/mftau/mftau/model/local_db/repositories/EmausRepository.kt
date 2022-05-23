package pl.mftau.mftau.model.local_db.repositories

import android.app.Application
import androidx.annotation.WorkerThread
import androidx.lifecycle.LiveData
import pl.mftau.mftau.db.MFTauDatabase
import pl.mftau.mftau.db.daos.DrawsDao
import pl.mftau.mftau.db.daos.MembersDao
import pl.mftau.mftau.db.entities.DrawEntity
import pl.mftau.mftau.db.entities.MemberEntity

class EmausRepository(application: Application) {

    private var mMembersDao: MembersDao
    private var mDrawsDao: DrawsDao
    private var mAllMembers: LiveData<List<MemberEntity>>
    private var mLastDraws: LiveData<List<String>>

    init {
        val db = MFTauDatabase.getInstance(application)!!
        mMembersDao = db.membersDao()
        mDrawsDao = db.drawsDao()
        mAllMembers = mMembersDao.getAllMembers()
        mLastDraws = mDrawsDao.getLastDraws()
    }

    fun getAllMembers(): LiveData<List<MemberEntity>> = mAllMembers

    fun getLastDraws(): LiveData<List<String>> = mLastDraws

    @WorkerThread
    suspend fun getMemberNameById(id: String): String = mMembersDao.getNameById(id)

    @WorkerThread
    suspend fun getOddPersonId(): String? = mDrawsDao.getOddPersonId()

    @WorkerThread
    suspend fun getMaxNumberOfDraw(): Int? = mDrawsDao.getMaxNumberOfDraw()

    @WorkerThread
    suspend fun insertMembers(members: List<MemberEntity>) =
        mMembersDao.insertAllMembers(*members.toTypedArray())

    @WorkerThread
    suspend fun updateMembersLists(members: List<MemberEntity>) =
        mMembersDao.updateAllMembers(*members.toTypedArray())

    @WorkerThread
    suspend fun deleteMembers(members: List<MemberEntity>) =
        mMembersDao.deleteMembers(*members.toTypedArray())

    @WorkerThread
    suspend fun insertDraw(draw: DrawEntity) = mDrawsDao.insertDraw(draw)

    @WorkerThread
    suspend fun deleteLastDraw() = mDrawsDao.deleteLastDraw()

    @WorkerThread
    suspend fun deleteAllDraws() = mDrawsDao.deleteAllDraws()
}