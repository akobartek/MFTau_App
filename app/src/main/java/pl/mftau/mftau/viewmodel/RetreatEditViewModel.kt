package pl.mftau.mftau.viewmodel

import android.app.Activity
import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.google.firebase.Timestamp
import pl.mftau.mftau.model.Retreat
import pl.mftau.mftau.model.repositories.FirebaseRepository
import pl.mftau.mftau.utils.FirestoreUtils
import java.text.SimpleDateFormat
import java.util.*

class RetreatEditViewModel(val app: Application) : AndroidViewModel(app) {

    private val mFirebaseRepository = FirebaseRepository(app)

    var retreat: Retreat? = null
    var beginDate: Date = Date()
    var endDate: Date = Date()
    var registerDate: Date = Date()

    fun getDateFormatted(date: Date): String =
            SimpleDateFormat("dd.MM.yyyy", Locale.getDefault()).format(date)

    fun getPriceFormatted(): String = if (retreat != null) retreat!!.price.toString() else ""

    fun createRetreatValues(name: String, city: String, address: String, price: Int, isPaymentRequired: Boolean)
            : HashMap<String, Any> {
        val retreatValues = HashMap<String, Any>()
        retreatValues[FirestoreUtils.firestoreKeyName] = name
        retreatValues[FirestoreUtils.firestoreKeyCity] = city
        retreatValues[FirestoreUtils.firestoreKeyAddress] = address
        retreatValues[FirestoreUtils.firestoreKeyPrice] = price
        retreatValues[FirestoreUtils.firestoreKeyBeginDate] = Timestamp(beginDate)
        retreatValues[FirestoreUtils.firestoreKeyEndDate] = Timestamp(endDate)
        retreatValues[FirestoreUtils.firestoreKeyRegisterLimitDate] = Timestamp(registerDate)
        retreatValues[FirestoreUtils.firestoreKeyAdvancePayment] = isPaymentRequired
        return retreatValues
    }

    fun addRetreat(activity: Activity, retreatValues: HashMap<String, Any>) =
            mFirebaseRepository.addRetreat(activity, retreatValues)

    fun updateRetreat(activity: Activity, retreatValues: HashMap<String, Any>) {
        if (retreat != null)
            mFirebaseRepository.updateRetreat(activity, retreat!!.id, retreatValues)
    }

    fun deleteRetreat(activity: Activity) {
        if (retreat != null) {
            mFirebaseRepository.deleteRetreat(activity, retreat!!.id, true)
        }
    }
}