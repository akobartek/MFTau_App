package pl.mftau.mftau.model.local_db

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Song(
    var title: String,
    var text: String,
    var chords: String,
    var isOriginallyInSongBook: Boolean,
    var isOnPlaylist: Boolean,
    var isFavourite: Boolean,
    var databaseId: Long = -1,
    var databaseTopics: String = ""
): Parcelable
