package pl.mftau.mftau.model

import android.os.Parcelable
import com.google.firebase.Timestamp
import kotlinx.android.parcel.Parcelize
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

@Parcelize
data class Meeting(var id: String = "",
                   var name: String = "",
                   var meetingType: Int = 0,
                   var date: Timestamp = Timestamp(Date()),
                   var attendanceList: ArrayList<String> = arrayListOf()) : Parcelable {

    fun getDateFormatted(): String = SimpleDateFormat("dd.MM.yyyy", Locale.getDefault()).format(date.toDate())
}