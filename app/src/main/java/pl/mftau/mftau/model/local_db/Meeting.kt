package pl.mftau.mftau.model.local_db

import android.os.Parcelable
import com.google.firebase.Timestamp
import kotlinx.parcelize.Parcelize
import pl.mftau.mftau.utils.getDateFormatted
import java.util.*
import kotlin.collections.HashMap

@Parcelize
data class Meeting(
    var id: String = "",
    var name: String = "",
    var meetingType: Int = 0,
    var date: Timestamp = Timestamp(Date()),
    var notes: String = "",
    var attendanceList: ArrayList<String> = arrayListOf(),
    var absenceList: HashMap<String, String> = HashMap()
) : Parcelable {

    fun getDateFormatted(): String = date.toDate().getDateFormatted()
}