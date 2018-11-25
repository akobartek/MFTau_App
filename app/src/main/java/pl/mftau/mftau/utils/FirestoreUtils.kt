package pl.mftau.mftau.utils

object FirestoreUtils {

    const val firestoreCollectionUsers = "users"
    const val firestoreKeyEmail = "email"
    const val firestoreKeyIsLeader = "isLeader"
    const val firestoreKeyIsAdmin = "isAdmin"
    const val firestoreKeyIsMember = "isMember"

    const val firestoreCollectionRetreats = "retreats"
    const val firestoreKeyBeginDate = "beginDate"
    const val firestoreKeyEndDate = "endDate"
    const val firestoreKeyRegisterLimitDate = "registerLimitDate"
    const val firestoreKeyAddress = "address"
    const val firestoreKeyPrice = "price"
    const val firestoreKeyAdvancePayment = "advancePayment"

    const val firestoreCollectionCities = "cities"

    const val firestoreCollectionMembers = "members"
    const val firestoreKeyName = "name"
    const val firestoreKeyCity = "city"
    const val firestoreKeyIsResponsible = "isResponsible"

    const val firestoreCollectionMeetings = "meetings"
    const val firestoreKeyDate = "date"
    const val firestoreKeyMeetingType = "meetingType"
    const val firestoreKeyAttendanceList = "attendanceList"

    val meetingTypes = arrayOf(
            "formation", "prayerful", "other"
    )
}