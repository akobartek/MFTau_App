package pl.mftau.mftau.model.repositories

import android.app.Application
import androidx.lifecycle.LiveData
import pl.mftau.mftau.db.EmausDatabase
import pl.mftau.mftau.db.daos.DrawsDao
import pl.mftau.mftau.db.daos.MembersDao
import pl.mftau.mftau.db.entities.MemberEntity
import android.os.AsyncTask
import pl.mftau.mftau.db.entities.DrawEntity


class EmausRepository(application: Application) {

    private var mMembersDao: MembersDao
    private var mDrawsDao: DrawsDao

    private var mAllMembers: LiveData<List<MemberEntity>>
    private var mLastDraws: LiveData<List<String>>

    init {
        val db = EmausDatabase.getInstance(application)!!
        mMembersDao = db.membersDao()
        mDrawsDao = db.drawsDao()
        mAllMembers = mMembersDao.getAllMembers()
        mLastDraws = mDrawsDao.getLastDraws()
    }

    fun getAllMembers(): LiveData<List<MemberEntity>> = mAllMembers

    fun getLastDraws(): LiveData<List<String>> = mLastDraws

    fun getMemberNameById(id: String): String = GetMemberNameByIdAsyncTask(mMembersDao).execute(id).get()

    fun getOddPersonId(): String? = GetOddPersonIdAsyncTask(mDrawsDao).execute().get()

    fun getMaxNumberOfDraw(): Int = GetMaxNumberOfDrawAsyncTask(mDrawsDao).execute().get()

    fun insertMembers(members: List<MemberEntity>) {
        InsertMembersAsyncTask(mMembersDao).execute(*members.toTypedArray())
    }

    fun updateMembersLists(members: List<MemberEntity>) {
        UpdateMembersAsyncTask(mMembersDao).execute(*members.toTypedArray())
    }

    fun deleteMembers(members: List<MemberEntity>) {
        DeleteMemberAsyncTask(mMembersDao).execute(*members.toTypedArray())
    }

    fun insertDraw(draw: DrawEntity) {
        InsertDrawAsyncTask(mDrawsDao).execute(draw)
    }

    fun deleteLastDraw() {
        DeleteLastDrawAsyncTask(mDrawsDao).execute()
    }

    fun deleteAllDraws() {
        DeleteAllDrawsAsyncTask(mDrawsDao).execute()
    }


    private class GetMemberNameByIdAsyncTask internal constructor(private val mAsyncTaskDao: MembersDao)
        : AsyncTask<String, Void, String>() {

        override fun doInBackground(vararg params: String): String {
            return mAsyncTaskDao.getNameById(params[0])
        }
    }

    private class GetOddPersonIdAsyncTask internal constructor(private val mAsyncTaskDao: DrawsDao)
        : AsyncTask<String, Void, String>() {

        override fun doInBackground(vararg params: String): String {
            return mAsyncTaskDao.getOddPersonId()
        }
    }

    private class GetMaxNumberOfDrawAsyncTask internal constructor(private val mAsyncTaskDao: DrawsDao)
        : AsyncTask<DrawEntity, Void, Int>() {

        override fun doInBackground(vararg p0: DrawEntity?): Int {
            return mAsyncTaskDao.getMaxNumberOfDraw()
        }
    }

    private class InsertMembersAsyncTask internal constructor(private val mAsyncTaskDao: MembersDao)
        : AsyncTask<MemberEntity, Void, Void>() {

        override fun doInBackground(vararg params: MemberEntity): Void? {
            mAsyncTaskDao.insertAllMembers(*params)
            return null
        }
    }

    private class UpdateMembersAsyncTask internal constructor(private val mAsyncTaskDao: MembersDao)
        : AsyncTask<MemberEntity, Void, Void>() {

        override fun doInBackground(vararg params: MemberEntity): Void? {
            mAsyncTaskDao.updateAllMembers(*params)
            return null
        }
    }

    private class DeleteMemberAsyncTask internal constructor(private val mAsyncTaskDao: MembersDao)
        : AsyncTask<MemberEntity, Void, Void>() {

        override fun doInBackground(vararg params: MemberEntity): Void? {
            mAsyncTaskDao.deleteMembers(*params)
            return null
        }
    }

    private class InsertDrawAsyncTask internal constructor(private val mAsyncTaskDao: DrawsDao)
        : AsyncTask<DrawEntity, Void, Void>() {

        override fun doInBackground(vararg params: DrawEntity): Void? {
            mAsyncTaskDao.insertDraw(params[0])
            return null
        }
    }

    private class DeleteLastDrawAsyncTask internal constructor(private val mAsyncTaskDao: DrawsDao)
        : AsyncTask<DrawEntity, Void, Void>() {

        override fun doInBackground(vararg params: DrawEntity): Void? {
            mAsyncTaskDao.deleteLastDraw()
            return null
        }
    }

    private class DeleteAllDrawsAsyncTask internal constructor(private val mAsyncTaskDao: DrawsDao)
        : AsyncTask<DrawEntity, Void, Void>() {

        override fun doInBackground(vararg params: DrawEntity): Void? {
            mAsyncTaskDao.deleteAllDraws()
            return null
        }
    }
}