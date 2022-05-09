package pl.mftau.mftau.model.remote_db

import java.util.*

data class LocalMembership(
    val PersonId: Int,
    val CommunityId: Int,
    var StartDate: Date,
    var EndDate: Date?,
    val CommunityName: String?
)