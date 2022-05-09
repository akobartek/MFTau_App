package pl.mftau.mftau.model.remote_db

import java.util.*

data class Retreat(
    val RetreatId: Int,
    var Name: String,
    var StartDate: Date,
    var EndDate: Date,
    var RegistrationLimitDate: Date?,
    var PlaceId: Int?,
    var PlaceName: String?,
    var Price: Int?,
    var RetreatType: Int?,
    var TargetGroup: Int?,
    var IsAdvancePaymentRequired: Boolean?,
    var WasCancelled: Boolean = false
)
