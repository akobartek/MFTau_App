package pl.mftau.mftau.gospel.data

import kotlinx.datetime.Clock
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime
import org.jsoup.Jsoup
import pl.mftau.mftau.gospel.domain.GospelRepository
import pl.mftau.mftau.gospel.domain.model.Gospel
import kotlin.coroutines.cancellation.CancellationException

class GospelRepositoryImpl : GospelRepository {
    override fun loadGospel(): Result<Gospel> {
        try {
            val document = Jsoup.connect(buildGospelUrl())
                .timeout(30000)
                .get()
            var counter = 3 // minimum value for the gospel
            while (true) {
                val element = document.getElementById("tabnowy0$counter")
                    ?: document.getElementById("tabstary0$counter")
                    ?: return Result.failure(NullPointerException())

                if (!element.html().contains("Ewangelia (")) ++counter
                else {
                    val children = element.children().toMutableList()
                    val versesElement = children.firstOrNull()
                    val verses = versesElement?.text() ?: ""
                    children.remove(versesElement)
                    val titleElement = children.firstOrNull()
                    val title = titleElement?.text() ?: ""
                    children.remove(titleElement)
                    val authorElement = children.firstOrNull()
                    val author = authorElement?.text() ?: ""
                    children.remove(authorElement)
                    val textBuilder = StringBuilder()
                    children.map { it.text() + "\n\n" }
                        .forEach { textBuilder.append(it) }
                    return Result.success(Gospel(verses, title, author, textBuilder.toString()))
                }
            }
        } catch (exc: Exception) {
            exc.printStackTrace()
            if (exc !is CancellationException) return Result.failure(exc)
            else throw exc
        }
    }


    private fun buildGospelUrl(): String {
        val date = Clock.System.now().toLocalDateTime(TimeZone.currentSystemDefault()).date
        val dayInt = date.dayOfMonth
        val day = if (dayInt < 10) "0$dayInt" else dayInt.toString()
        val monthInt = date.monthNumber
        val month = if (monthInt < 10) "0$monthInt" else monthInt.toString()
        val year = date.year

        return "https://niezbednik.niedziela.pl/liturgia/$year-$month-$day/"
    }
}