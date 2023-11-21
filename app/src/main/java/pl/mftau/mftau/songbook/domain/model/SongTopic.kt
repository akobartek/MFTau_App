package pl.mftau.mftau.songbook.domain.model

import kotlinx.serialization.Serializable

@Serializable
enum class SongTopic(val value: Int) {
    ALL(0), FAVOURITES(1), SAINT_FRANCIS(2), HOLY_SPIRIT(3),
    COMMUNION(4), WORSHIP(5), ADORATION(6), ATONEMENT(7), EASTER(8),
    ADVENT(9), MARY(10), GIFTS(11), KIDS(12), CAROLS(13);

    companion object {
        fun fromValue(value: Int): SongTopic = when (value) {
            1 -> FAVOURITES
            2 -> SAINT_FRANCIS
            3 -> HOLY_SPIRIT
            4 -> COMMUNION
            5 -> WORSHIP
            6 -> ADORATION
            7 -> ATONEMENT
            8 -> EASTER
            9 -> ADVENT
            10 -> MARY
            11 -> GIFTS
            12 -> KIDS
            13 -> CAROLS
            else -> ALL
        }
    }
}