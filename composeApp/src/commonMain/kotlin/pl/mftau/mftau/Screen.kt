package pl.mftau.mftau

import kotlinx.serialization.Serializable

@Serializable
sealed class Screen {
    @Serializable
    data object Home : Screen()

    @Serializable
    data object Auth : Screen()

    @Serializable
    data object Settings : Screen()

    @Serializable
    data object Gospel : Screen()

    @Serializable
    data object SongBook : Screen()

    @Serializable
    data object Playlists : Screen()

    @Serializable
    data class PlaylistDetails(val playlistId: Long, val importCode: String) : Screen()

    @Serializable
    data object UserSongs : Screen()

    @Serializable
    data object BreviarySelect : Screen()

    @Serializable
    data class BreviaryText(val position: Int, val date: String) : Screen()

    @Serializable
    data class BreviarySave(val date: String) : Screen()

    @Serializable
    data object Readings : Screen()

    @Serializable
    data object LeadersPeople : Screen()

    @Serializable
    data object LeadersEmaus : Screen()

    @Serializable
    data object LeadersMeetings : Screen()

    @Serializable
    data object LeadersPresence : Screen()
}