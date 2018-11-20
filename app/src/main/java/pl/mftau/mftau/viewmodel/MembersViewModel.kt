package pl.mftau.mftau.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import pl.mftau.mftau.model.Member
import pl.mftau.mftau.model.repositories.FirebaseRepository

class MembersViewModel(val app: Application) : AndroidViewModel(app) {

    private val mFirebaseRepository = FirebaseRepository(app)


    fun getAllMembers(): LiveData<List<Member>> = mFirebaseRepository.getAllMembers()
}