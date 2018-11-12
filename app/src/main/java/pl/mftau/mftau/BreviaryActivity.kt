package pl.mftau.mftau

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.app.NavUtils
import android.view.MenuItem
import android.widget.ArrayAdapter
import kotlinx.android.synthetic.main.activity_breviary.*
import android.os.AsyncTask
import android.os.Build
import android.util.Log
import android.view.View
import java.io.InputStreamReader
import java.net.URL
import java.io.BufferedReader
import java.io.IOException


class BreviaryActivity : AppCompatActivity() {

    companion object {
        private var isTextShowed = false
        private const val animationDuration = 1000L

        private const val breviaryUrl = "http://skrzynkaintencji.pl/brewiarz/"
        private val beginningValues = arrayOf(
                "<h2><a name=\"wezwanie\">Wezwanie</a></h2>",
                "<h2><a name=\"godzina_czytan\">Godzina Czytań</a></h2>",
                "<h2><a name=\"jutrznia\">Jutrznia</h2>",
                "<h2><a name=\"przedpoludniowa\">Modlitwa przedpołudniowa</a></h2>",
                "<h2><a name=\"poludniowa\">Modlitwa południowa</a></h2>",
                "<h2><a name=\"popoludniowa\">Modlitwa popołudniowa</a></h2>",
                "<h2><a name=\"nieszpory\">Nieszpory</a></h2>",
                "<h2><a name=\"kompleta\">Kompleta</a></h2>"
        )
        private const val end = "<p></body></html></div>"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_breviary)
        setSupportActionBar(breviaryToolbar)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
            window.statusBarColor = Color.WHITE
        }

        val breviaryHtml = DownloadTask().execute(breviaryUrl).get().replace("e88b40", "4e342e")

        breviaryList.adapter = ArrayAdapter<String>(this@BreviaryActivity,
                android.R.layout.simple_list_item_1, resources.getStringArray(R.array.breviary_list))

        breviaryList.setOnItemClickListener { parent, _, position, _ ->
            isTextShowed = true
            breviaryText.loadData(getStringBetweenTwoValues(breviaryHtml, beginningValues[position], end),
                    "text/html", "UTF-8")
            breviaryText.visibility = View.VISIBLE
            breviaryText.scrollTo(0, 0)
            breviaryText.animate().alpha(1f).duration = animationDuration
            title = parent.getItemAtPosition(position).toString()
        }
    }

    override fun onBackPressed() {
        if (!isTextShowed) NavUtils.navigateUpFromSameTask(this@BreviaryActivity)
        else {
            isTextShowed = false
            title = getString(R.string.breviary)
            breviaryText.animate()
                    .alpha(-1f)
                    .withEndAction { breviaryText.visibility = View.INVISIBLE }
                    .duration = animationDuration * 3 / 5
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                onBackPressed()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun getStringBetweenTwoValues(input: String, startValue: String, endValue: String): String {
        try {
            val start = input.indexOf(startValue)
            if (start != -1) {
                val end = input.indexOf(endValue, start + startValue.length)
                if (end != -1) {
                    return input.substring(start + startValue.length, end)
                }
            }
        } catch (exc: Exception) {
            Log.e(BreviaryActivity::class.java.name, exc.toString())
            return ""
        }
        return ""
    }
}

class DownloadTask : AsyncTask<String, Void, String>() {

    override fun doInBackground(vararg urls: String): String? {

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