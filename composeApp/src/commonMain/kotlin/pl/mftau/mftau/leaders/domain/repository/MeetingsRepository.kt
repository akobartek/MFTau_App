package pl.mftau.mftau.leaders.domain.repository

import kotlinx.coroutines.flow.Flow
import pl.mftau.mftau.leaders.domain.model.Meeting

interface MeetingsRepository {
    val meetings: Flow<List<Meeting>>
    suspend fun saveMeeting(meeting: Meeting): Boolean
    suspend fun deleteMeeting(meeting: Meeting): Boolean
    suspend fun clearMeetings()
}