package pl.mftau.mftau.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import pl.mftau.mftau.model.Retreat
import java.text.SimpleDateFormat
import java.util.*

class RetreatDetailsViewModel(app: Application) : AndroidViewModel(app) {

    var retreat: Retreat? = null

    fun getDateFormatted(date: Date): String =
            SimpleDateFormat("dd.MM.yyyy", Locale.getDefault()).format(date)

    fun getPriceFormatted(): String = if (retreat != null) retreat!!.price.toString() else ""
}