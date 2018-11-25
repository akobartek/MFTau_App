package pl.mftau.mftau.viewmodel

import android.app.Application
import android.os.AsyncTask
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import pl.mftau.mftau.model.Breviary
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStreamReader
import java.net.URL

class BreviaryViewModel(app: Application) : AndroidViewModel(app) {

    private var mBreviary = Breviary(DownloadTask()
            .execute(Breviary.breviaryUrl)
            .get()
            .replace("e88b40", "4e342e"))

    var isNightMode = false

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

    fun getBreviary(): String {
        if (activityStatus.first)
            try {
                val startIndex = mBreviary.html.indexOf(Breviary.beginningValues[activityStatus.second])
                if (startIndex != -1) {
                    val endIndex = mBreviary.html.indexOf(Breviary.endValue,
                            startIndex + Breviary.beginningValues[activityStatus.second].length)
                    if (endIndex != -1) {
                        return if (isNightMode) {
                            ("<html><head>"
                                    + "<style type=\"text/css\">body{color: #fff; background-color: #28292e;}"
                                    + "</style></head>"
                                    + "<body>"
                                    + mBreviary.html.replace("4e342e", "82574e").substring(
                                    startIndex + Breviary.beginningValues[activityStatus.second].length, endIndex)
                                    + "</body></html>")
                        } else {
                            mBreviary.html.substring(
                                    startIndex + Breviary.beginningValues[activityStatus.second].length, endIndex)
                        }
                    }
                }
            } catch (exc: Exception) {
                Log.e("BreviaryViewModel", exc.toString())
                return ""
            }
        return ""
    }

    private class DownloadTask : AsyncTask<String, Void, String>() {
        override fun doInBackground(vararg urls: String): String? {
            Log.d("DownLoadTask", "Loading html")
            try {
                // Build and set timeout values for the request.
                val connection = URL(urls[0]).openConnection()
                connection.connectTimeout = 5000
                connection.readTimeout = 5000
                connection.connect()

                // Read and store the result line by line then return the entire string.
                val `in` = connection.getInputStream()
                val reader = BufferedReader(InputStreamReader(`in`))
                val html = StringBuilder()
                var line = reader.readLine()
                while (line != null) {
                    html.append(line)
                    line = reader.readLine()
                }
                `in`.close()

                return html.toString()
            } catch (exc: IOException) {
                return exc.toString()
            }
        }
    }
}