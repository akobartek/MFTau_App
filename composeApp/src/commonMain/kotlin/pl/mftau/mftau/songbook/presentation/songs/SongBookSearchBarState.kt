package pl.mftau.mftau.songbook.presentation.songs

import pl.mftau.mftau.songbook.domain.model.SongTopic

data class SongBookSearchBarState(
    val searchQuery: String = "",
    val selectedFilter: SongTopic = SongTopic.ALL,
) {
    fun isChanged() = isQueryChanged() || isFilterChanged()
    fun isQueryChanged() = searchQuery.isNotBlank()
    fun isFilterChanged() = selectedFilter != SongTopic.ALL
}