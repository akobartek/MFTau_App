package pl.mftau.mftau.model.remote_db

data class RetreatsParticipation(
    val PersonId: Int,
    val RetreatId: Int,
    var ParticipationType: Int,
    var PromisesSaid: Boolean,
    val FirstName: String?,
    val LastName: String?,
    val PhoneNumber: String?,
    val EmailAddress: String?
)
