package pl.mftau.mftau.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.jsoup.Jsoup
import pl.mftau.mftau.utils.PreferencesManager
import java.util.*

@Suppress("BlockingMethodInNonBlockingContext")
class GospelViewModel : ViewModel() {

    private var mGospelHtml: String? = null
    private var mGospelToRead: String? = null

    fun loadGospelHtml(onLoadSuccess: (String) -> Unit, onLoadFailed: () -> Unit) {
        viewModelScope.launch {
            try {
                if (mGospelHtml == null) {
                    val document = withContext(Dispatchers.IO) {
                        Jsoup.connect(buildGospelUrl()).timeout(30000).get()
                    }
                    var counter = 3
                    while (mGospelHtml == null) {
                        val elementHtml = document.getElementById("tabnowy0$counter")?.html()
                            ?: document.getElementById("tabstary0$counter").html()
                        if (elementHtml.contains("Ewangelia (")) mGospelHtml = elementHtml
                        else ++counter
                    }
                }
                onLoadSuccess(checkGospelNightMode())
            } catch (exc: Exception) {
                exc.printStackTrace()
                onLoadFailed()
            }
        }
    }

    fun getGospelToRead(): String? {
        if (mGospelHtml == null) return null

        if (mGospelToRead == null) {
            mGospelToRead = mGospelHtml
            mGospelToRead =
                mGospelToRead!!.substring(mGospelToRead!!.indexOf("<p>"), mGospelToRead!!.length)
                    .replace("<p>", "")
                    .replace("</p>", "\n")
                    .replace("<br>", "")
                    .replace("<strong>", "")
                    .replace("</strong>", "")
                    .replace(":", ".")

            val list = mGospelToRead!!.split("; ").toMutableList()
            val stringBuilder = StringBuilder()
            for (sentence in list) {
                stringBuilder.append(sentence.replaceFirstChar(Char::titlecase)).append(". ")
            }
            mGospelToRead = stringBuilder.toString().trim()
            mGospelToRead = mGospelToRead!!.substring(0, mGospelToRead!!.length - 1)
        }
        return mGospelToRead
    }

    private fun buildGospelUrl(): String {
        val calendar = Calendar.getInstance()
        val dayInt = calendar.get(Calendar.DAY_OF_MONTH)
        val day = if (dayInt < 10) "0$dayInt" else dayInt.toString()
        val monthInt = calendar.get(Calendar.MONTH) + 1
        val month = if (monthInt < 10) "0$monthInt" else monthInt.toString()
        val year = calendar.get(Calendar.YEAR).toString()

        return "http://niezbednik.niedziela.pl/liturgia/$year-$month-$day/"
    }

    private fun checkGospelNightMode(): String {
        return if (PreferencesManager.getNightMode()) {
            val result = "<html><head>" +
                    "<style type=\"text/css\">body{color: #fff; background-color: #28292e;}" +
                    "</style></head>" +
                    "<body>" +
                    mGospelHtml +
                    "</body></html>"
            result.replace("black", "white")
        } else {
            mGospelHtml ?: ""
        }
    }
}