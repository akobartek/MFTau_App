package pl.mftau.mftau.model.remote_db

data class RetreatsPlace(
    val PlaceId: Int,
    var PlaneName: String,
    var Address: String,
    var City: String,
    var Country: String,
    var IsAvailableYet: Boolean
)
