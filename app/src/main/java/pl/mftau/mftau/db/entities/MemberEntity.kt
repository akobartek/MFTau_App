package pl.mftau.mftau.db.entities

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "members_table")
data class MemberEntity(
    @PrimaryKey @NonNull var id: String,
    @ColumnInfo(name = "name") @NonNull var name: String,
    @ColumnInfo(name = "draws_history") @NonNull var drawsList: ArrayList<String>
)