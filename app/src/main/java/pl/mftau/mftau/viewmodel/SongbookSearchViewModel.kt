package pl.mftau.mftau.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class SongbookSearchViewModel : ViewModel() {

    val song = MutableLiveData<Int>()
    val query = MutableLiveData<String>(null)
}