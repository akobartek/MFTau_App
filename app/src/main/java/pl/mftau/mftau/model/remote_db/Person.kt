package pl.mftau.mftau.model.remote_db

import java.util.*

data class Person(
    val PersonId: Int,
    var FirstName: String,
    var LastName: String,
    var Gender: Char,
    var Address: String?,
    var CorrespondenceAddress: String?,
    var Pesel: Long?,
    var PhoneNumber: String,
    var EmailAddress: String,
    var BirthDate: Date?,
    var NameDay: Date?,
    var PersonKind: Int?,
    var PersonFunction: Int,
    var HasMusicalSkills: Boolean?,
    var Instruments: String?,
    var HasCookingSkills: Boolean?,
    var HasArtSkills: Boolean?,
    var HasEducatorCourse: Boolean?,
    var HasSanepidBook: Boolean?,
    var IsAcolyte: Boolean?,
    var OtherSkills: String?,
    val CommunityId: Int?,
    var CommunityName: String?
)