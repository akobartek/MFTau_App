package pl.mftau.mftau.breviary.data.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "breviary")
data class BreviaryEntity(
    @PrimaryKey(autoGenerate = true) val id: Int,
    @ColumnInfo(name = "date") val date: Long,
    @ColumnInfo(name = "invitatory") val invitatory: String,
    @ColumnInfo(name = "office_of_readings") val officeOfReadings: String,
    @ColumnInfo(name = "lauds") val lauds: String,
    @ColumnInfo(name = "prayer1") val prayer1: String,
    @ColumnInfo(name = "prayer2") val prayer2: String,
    @ColumnInfo(name = "prayer3") val prayer3: String,
    @ColumnInfo(name = "vespers") val vespers: String,
    @ColumnInfo(name = "compline") val compline: String,
)