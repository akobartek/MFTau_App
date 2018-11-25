package pl.mftau.mftau.db.entities

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import pl.mftau.mftau.db.converters.ListConverters
import java.util.*

@Entity(tableName = "draws_table")
data class DrawEntity(@PrimaryKey(autoGenerate = true) var id: Long? = null,
                      @ColumnInfo(name = "number_of_draw") @NonNull var numberOfDraw: Int,
                      @ColumnInfo(name = "draw_date") @NonNull var drawDate: Date,
                      @ColumnInfo(name = "odd_person_id") var oddPersonId: String,
                      @TypeConverters(ListConverters::class)
                      @ColumnInfo(name = "draws") @NonNull var draws: List<String>)