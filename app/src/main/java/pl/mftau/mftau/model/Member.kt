package pl.mftau.mftau.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Member(var id: String = "",
                  var name: String = "",
                  var city: String = "",
                  var isResponsible: Boolean = false) : Parcelable