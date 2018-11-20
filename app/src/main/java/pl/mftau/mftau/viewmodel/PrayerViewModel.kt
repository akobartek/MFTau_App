package pl.mftau.mftau.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

class PrayerViewModel(app: Application) : AndroidViewModel(app) {

    private val activityStatusLiveData = MutableLiveData<Triple<Boolean, Int, Boolean>>()
    private var activityStatus = Triple(false, -1, false)

    init {
        activityStatusLiveData.postValue(activityStatus)
    }


    fun getActivityStatus(): LiveData<Triple<Boolean, Int, Boolean>> = activityStatusLiveData
    fun setActivityStatus(isTextShowed: Boolean, position: Int?, isBackPressed: Boolean) {
        activityStatus = Triple(isTextShowed, position ?: activityStatus.second, isBackPressed)
        activityStatusLiveData.postValue(activityStatus)
    }
}