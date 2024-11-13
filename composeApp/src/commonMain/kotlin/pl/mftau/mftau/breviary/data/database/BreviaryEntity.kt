package pl.mftau.mftau.breviary.data.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import pl.mftau.mftau.breviary.domain.model.BreviaryDay

@Entity(tableName = "breviary", indices = [Index(value = ["date"], unique = true)])
data class BreviaryEntity(
    @PrimaryKey(autoGenerate = true) val id: Long = 0L,
    @ColumnInfo(name = "date") val date: Long,
    @ColumnInfo(name = "invitatory") val invitatory: String? = "",
    @ColumnInfo(name = "office_of_readings") val officeOfReadings: String? = "",
    @ColumnInfo(name = "lauds") val lauds: String? = "",
    @ColumnInfo(name = "prayer1") val prayer1: String? = "",
    @ColumnInfo(name = "prayer2") val prayer2: String? = "",
    @ColumnInfo(name = "prayer3") val prayer3: String? = "",
    @ColumnInfo(name = "vespers") val vespers: String? = "",
    @ColumnInfo(name = "compline") val compline: String? = "",
) {
    companion object {
        fun fromDomainObject(obj: BreviaryDay) =
            BreviaryEntity(
                date = getDbDate(obj.date),
                invitatory = obj.invitatory?.html.orEmpty(),
                officeOfReadings = obj.officeOfReadings?.html.orEmpty(),
                lauds = obj.lauds?.html.orEmpty(),
                prayer1 = obj.prayer1?.html.orEmpty(),
                prayer2 = obj.prayer2?.html.orEmpty(),
                prayer3 = obj.prayer3?.html.orEmpty(),
                vespers = obj.vespers?.html.orEmpty(),
                compline = obj.compline?.html.orEmpty(),
            )

        fun getDbDate(date: String) =
            date.split(".").reversed().joinToString("").toLong()
    }
}