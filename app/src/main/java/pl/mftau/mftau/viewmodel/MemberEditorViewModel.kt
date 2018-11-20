package pl.mftau.mftau.viewmodel

import android.app.Activity
import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.google.firebase.auth.FirebaseAuth
import pl.mftau.mftau.model.Member
import pl.mftau.mftau.model.repositories.FirebaseRepository
import java.io.InputStream

class MemberEditorViewModel(val app: Application) : AndroidViewModel(app) {

    private val mFirebaseRepository = FirebaseRepository(app)

    var member: Member? = null
    var filePath: InputStream? = null


    fun addMember(activity: Activity, memberValues: HashMap<String, Any>)
            = mFirebaseRepository.addMember(activity, memberValues, filePath)

    fun updatePhoto(activity: Activity) {
        if (member != null) {
            mFirebaseRepository.updateMemberPhoto(activity, member!!.id, filePath)
        }
    }

    fun updateMember(activity: Activity, memberValues: HashMap<String, Any>) {
        if (member != null) {
            mFirebaseRepository.updateMember(activity, member!!.id, memberValues, filePath)
        }
    }

    fun deleteMember(activity: Activity) {
        if (member != null) {
            mFirebaseRepository.deleteMember(activity, member!!.id)
        }
    }
}