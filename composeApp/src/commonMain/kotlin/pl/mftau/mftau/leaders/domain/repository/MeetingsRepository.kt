package pl.mftau.mftau.leaders.domain.repository

import kotlinx.coroutines.flow.Flow
import pl.mftau.mftau.leaders.domain.model.Meeting

interface MeetingsRepository {
    fun getMeetings(): Flow<List<Meeting>>
    suspend fun saveMeeting(meeting: Meeting)
    suspend fun deleteMeeting(meeting: Meeting)
    suspend fun clearMeetings()
}