package pl.mftau.mftau.viewmodel

import android.app.Activity
import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import pl.mftau.mftau.model.local_db.Member
import pl.mftau.mftau.model.local_db.repositories.FirebaseRepository
import java.io.InputStream

class MembersViewModel(val app: Application) : AndroidViewModel(app) {

    private val mFirebaseRepository = FirebaseRepository(app)

    fun getAllMembers(): LiveData<List<Member>> = mFirebaseRepository.getAllMembers()

//    fun getMemberById(memberId: String): LiveData<Member> = mFirebaseRepository.getMemberById(memberId)

    fun addMember(activity: Activity, memberValues: HashMap<String, Any>, filePath: InputStream?) =
        mFirebaseRepository.addMember(activity, memberValues, filePath)

    fun updatePhoto(activity: Activity, memberId: String, filePath: InputStream?) =
        mFirebaseRepository.updateMemberPhoto(activity, memberId, filePath)

    fun updateMember(
        activity: Activity,
        memberId: String,
        memberValues: HashMap<String, Any>,
        filePath: InputStream?
    ) = mFirebaseRepository.updateMember(activity, memberId, memberValues, filePath)


    fun deleteMember(activity: Activity, memberId: String) =
        mFirebaseRepository.deleteMember(activity, memberId)
}