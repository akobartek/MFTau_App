package pl.mftau.mftau.songbook.domain.repository

import pl.mftau.mftau.songbook.domain.model.Song
import pl.mftau.mftau.songbook.domain.model.SongTopic

abstract class TextsSongBookRepository {
    protected abstract val titles: Array<String>
    protected abstract val texts: Array<String>
    protected abstract val chords: Array<String>
    protected abstract val topics: Map<SongTopic, List<Int>>

    fun getSongs(): List<Song> = titles.mapIndexed { index, title ->
        //Indexes start from 0 and song book numbers from 1
        val songTopics = topics.filter { (_, numbers) -> numbers.contains(index + 1) }.keys
        Song(title = title, text = texts[index], chords = chords[index], topics = songTopics)
    }
}