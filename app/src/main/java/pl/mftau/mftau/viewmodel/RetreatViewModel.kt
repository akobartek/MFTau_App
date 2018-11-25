package pl.mftau.mftau.viewmodel

import android.app.Activity
import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import pl.mftau.mftau.model.Retreat
import pl.mftau.mftau.model.repositories.FirebaseRepository

class RetreatViewModel(val app: Application):AndroidViewModel(app) {

    private val mFirebaseRepository = FirebaseRepository(app)


    fun getAllRetreats(): LiveData<List<Retreat>> = mFirebaseRepository.getAllRetreats()

    fun deleteRetreat(activity: Activity, retreatId: String) =
            mFirebaseRepository.deleteRetreat(activity, retreatId, false)
}