package pl.mftau.mftau.model

data class Member(var id: String = "",
                  var name: String = "",
                  var city: String = "",
                  var isResponsible: Boolean = false,
                  var isPhotoAdded: Boolean = false)