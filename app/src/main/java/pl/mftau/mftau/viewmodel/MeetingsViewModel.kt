package pl.mftau.mftau.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import pl.mftau.mftau.model.Meeting
import pl.mftau.mftau.model.repositories.FirebaseRepository


class MeetingsViewModel(val app: Application) : AndroidViewModel(app) {

    private val mFirebaseRepository = FirebaseRepository(app)


    fun getAllMeetings(meetingType: Int): LiveData<List<Meeting>> = mFirebaseRepository.getAllMeetings(meetingType)

    fun clearMeetings() = mFirebaseRepository.clearMeetings()
}