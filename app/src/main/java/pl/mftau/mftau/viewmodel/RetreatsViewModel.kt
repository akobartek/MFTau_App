package pl.mftau.mftau.viewmodel

import android.app.Activity
import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import pl.mftau.mftau.model.local_db.Retreat
import pl.mftau.mftau.model.local_db.repositories.FirebaseRepository

class RetreatsViewModel(val app: Application) : AndroidViewModel(app) {

    private val mFirebaseRepository = FirebaseRepository(app)

    fun getAllRetreats(): LiveData<List<Retreat>> = mFirebaseRepository.getAllRetreats()

    fun addRetreat(activity: Activity, retreatValues: HashMap<String, Any>) =
        mFirebaseRepository.addRetreat(activity, retreatValues)

    fun updateRetreat(activity: Activity, retreatId: String, retreatValues: HashMap<String, Any>) =
        mFirebaseRepository.updateRetreat(activity, retreatId, retreatValues)

    fun deleteRetreat(activity: Activity, retreatId: String, withToast: Boolean) =
        mFirebaseRepository.deleteRetreat(activity, retreatId, withToast)
}