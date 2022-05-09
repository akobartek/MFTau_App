package pl.mftau.mftau.model.local_db

import android.os.Parcelable
import com.google.firebase.Timestamp
import kotlinx.parcelize.Parcelize
import java.util.*

@Parcelize
data class Retreat(
    var id: String = "",
    var name: String = "",
    var beginDate: Timestamp = Timestamp(Date()),
    var endDate: Timestamp = Timestamp(Date()),
    var registerLimitDate: Timestamp = Timestamp(Date()),
    var city: String = "",
    var address: String = "",
    var price: Int = 0,
    var retreatType: Int = 0,
    var advancePayment: Boolean = false
) : Parcelable