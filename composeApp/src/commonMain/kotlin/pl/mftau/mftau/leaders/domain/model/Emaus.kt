package pl.mftau.mftau.leaders.domain.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.datetime.Clock

@Entity(tableName = "emaus")
data class Emaus(
    @PrimaryKey(autoGenerate = true) val id: Long = 0L,
    @ColumnInfo(name = "first_person_id") val person1: String = "",
    @ColumnInfo(name = "second_person_id") val person2: String = "",
    @ColumnInfo(name = "draw_time") val drawTime: Long = Clock.System.now().toEpochMilliseconds(),
)