package pl.mftau.mftau.gospel.data

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import org.jsoup.Jsoup
import java.util.Calendar

class GospelRepositoryImpl : GospelRepository {
    override fun loadGospel(): Flow<Result<Gospel>> = flow {
        try {
            val document = Jsoup.connect(buildGospelUrl()).timeout(30000).get()
            var counter = 3 // minimum value for the gospel
            while (true) {
                val element = document.getElementById("tabnowy0$counter")
                    ?: document.getElementById("tabstary0$counter")

                if (element == null) {
                    emit(Result.failure(NullPointerException()))
                    return@flow
                }

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
                    emit(Result.success(Gospel(verses, title, author, textBuilder.toString())))
                }
            }
        } catch (throwable: Throwable) {
            throwable.printStackTrace()
            emit(Result.failure(throwable))
        }
    }.flowOn(Dispatchers.IO)


    private fun buildGospelUrl(): String {
        val calendar = Calendar.getInstance()
        val dayInt = calendar.get(Calendar.DAY_OF_MONTH)
        val day = if (dayInt < 10) "0$dayInt" else dayInt.toString()
        val monthInt = calendar.get(Calendar.MONTH) + 1
        val month = if (monthInt < 10) "0$monthInt" else monthInt.toString()
        val year = calendar.get(Calendar.YEAR).toString()

        return "https://niezbednik.niedziela.pl/liturgia/$year-$month-$day/"
    }
}