package pl.mftau.mftau.model.remote_db

data class LocalCommunity(
    val CommunityId: Int,
    var Name: String,
    var MeetingsPlace: String,
    var Address: String,
    var PostalCode: String,
    var City: String,
    var Country: String,
    var EmailAddress: String
)
