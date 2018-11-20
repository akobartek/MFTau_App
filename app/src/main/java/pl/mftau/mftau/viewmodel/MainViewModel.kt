package pl.mftau.mftau.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel

class MainViewModel(app: Application) : AndroidViewModel(app) {

    companion object {
        const val USER_TYPE_ADMIN = 0
        const val USER_TYPE_LEADER = 1
        const val USER_TYPE_MEMBER = 2
        const val USER_TYPE_NONE = 3
    }
}