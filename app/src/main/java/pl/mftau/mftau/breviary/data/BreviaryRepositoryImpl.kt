package pl.mftau.mftau.breviary.data

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import org.jsoup.Jsoup
import org.jsoup.nodes.Document
import org.jsoup.nodes.Element
import org.jsoup.nodes.TextNode
import pl.mftau.mftau.breviary.model.Breviary
import pl.mftau.mftau.breviary.model.BreviaryType
import pl.mftau.mftau.breviary.model.Invitatory
import pl.mftau.mftau.breviary.model.Psalm
import java.util.Calendar

class BreviaryRepositoryImpl(private val accentColor: Color) : BreviaryRepository {

    private val mBreviaryUrlTypes = arrayOf(
        "wezw", "godzczyt", "jutrznia", "modlitwa1",
        "modlitwa2", "modlitwa3", "nieszpory", "kompleta"
    )

    override fun checkIfThereAreMultipleOffices(
        daysFromToday: Int
    ): Flow<Result<Map<String, String>?>> = flow {
        try {
            val document = Jsoup.connect(buildBaseBreviaryUrl(daysFromToday, true) + "index.php3")
                .timeout(15000)
                .get()
            if (!document.html().contains("WYBIERZ OFICJUM", true))
                emit(Result.success(null))
            else {
                val offices = mutableMapOf<String, String>()
                val officesDivs = document.select("div")
                    .last { it.html().contains("OFICJUM") }
                    .selectFirst("table")
                    ?.selectFirst("tbody")
                    ?.select("div")
                officesDivs?.forEach {
                    val link = it.selectFirst("a")!!.attr("href").split("/")[1]
                    offices[link] = it.text()
                }
                emit(Result.success(offices))
            }
        } catch (throwable: Throwable) {
            throwable.printStackTrace()
            emit(Result.failure(throwable))
        }
    }.flowOn(Dispatchers.IO)

    override fun loadBreviary(
        office: String,
        daysFromToday: Int,
        type: BreviaryType
    ): Flow<Result<Breviary>> = flow {
        try {
            val breviaryUrl = buildBaseBreviaryUrl(daysFromToday, office == "") +
                    "${office}/${mBreviaryUrlTypes[type.type]}.php3"
            val document = Jsoup.connect(breviaryUrl).timeout(30000).get()
            emit(Result.success(getProperBreviaryObject(document, type)))
        } catch (throwable: Throwable) {
            throwable.printStackTrace()
            emit(Result.failure(throwable))
        }
    }.flowOn(Dispatchers.IO)

    private fun buildBaseBreviaryUrl(daysFromToday: Int, withDays: Boolean): String {
        val romanMonths =
            arrayOf("i", "ii", "iii", "iv", "v", "vi", "vii", "viii", "ix", "x", "xi", "xii")
        val calendar = Calendar.getInstance()
        val dayInt = calendar.get(Calendar.DAY_OF_MONTH) + daysFromToday
        val day = if (dayInt < 10) "0$dayInt" else dayInt.toString()
        val monthInt = calendar.get(Calendar.MONTH) + 1
        val month = if (monthInt < 10) "0$monthInt" else monthInt.toString()
        val year = calendar.get(Calendar.YEAR).toString().substring(2)

        return if (withDays) "https://brewiarz.pl/${romanMonths[monthInt - 1]}_$year/$day$month/"
        else "https://brewiarz.pl/${romanMonths[monthInt - 1]}_$year/"
    }

    private fun getProperBreviaryObject(document: Document, type: BreviaryType): Breviary {
        val element =
            document.select("table").last { it.outerHtml().contains("Psalm ") }

        if (type == BreviaryType.OFFICE_OF_READINGS) {
            element.select("i").first { it.html().contains("Te Deum") }.parentNode()?.remove()
            if (!element.html().contains("\"def1\"")) {
                val def1Elem = document.getElementById("def1")
                def1Elem?.let { elem ->
                    element.appendChild(elem.child(0))
                    element.append("<br><br>")
                    element.appendChild(
                        elem.parent()!!.select("table")
                            .first { it.html().contains("RESPONSORIUM") })
                }
            }
        }

        // Remove Premium
        element.select("a")
            .filter { elem ->
                elem.attr("href").contains("premium", ignoreCase = true)
                        || elem.attr("href").contains("access")
            }
            .forEach { it.parentNode()?.remove() }
        // Remove different variants
        element.select("ul").forEach { ulElem ->
            val idsToRemove = ulElem.select("a").map { it.attr("rel") }
            idsToRemove.forEachIndexed { index, id ->
                if (index > 0) element.getElementById(id)?.remove()
            }
            ulElem.remove()
        }
        // Remove unnecessary links
        element.select("a")
            .filter { elem ->
                elem.attr("href")
                    .contains("javascript") || elem.hasAttr("onclick")
            }
            .forEach { it.remove() }
        element.select("a")
            .lastOrNull { it.attr("href").contains("jutrznia") }?.parentNode()?.remove()
        // Clear text links
        element.select("a")
            .filter { elem ->
                elem.attr("href").contains("appendix")
            }
            .forEach { it.replaceWith(TextNode(it.text())) }

        return when (type) {
            BreviaryType.INVITATORY -> getInvitatory(element)
            else -> object : Breviary() {}
        }
    }

    private fun getInvitatory(element: Element): Invitatory {
        val beginning = buildAnnotatedString {
            element.select("div").first()?.children()?.forEach { div ->
                div.child(0).let { font ->
                    withStyle(
                        style = SpanStyle(color = accentColor, fontWeight = FontWeight.Bold)
                    ) {
                        append(font.text())
                    }
                }
                append(div.textNodes()[0].text() + "\n")
            }
        }

        val psalm = Psalm()
        val psalmElements = element
            .children().firstOrNull { it.html().contains("Psalm ") }
            ?.children()?.last()
            ?.firstElementChild()?.firstElementChild()?.children()
        psalmElements?.first()?.children()?.first()?.let { elem ->
            psalm.number = elem.child(0).text() ?: ""
            psalm.title = elem.child(2).text() ?: ""
        }
        psalmElements?.removeAt(0)
        psalmElements?.first()?.children()?.first()?.let { elem ->
            psalm.subtitle = elem.text()
        }
        psalmElements?.removeAt(0)
        psalmElements?.first()?.let { elem ->
            psalm.breviaryPages =
                elem.textNodes()[0]?.text() + "\n" + elem.lastElementChild()?.text()
        }
        psalmElements?.removeAt(0)
        psalmElements?.select(".cd")?.first()?.let { div ->
            val fonts = div.children()
            val texts = div.textNodes()
            psalm.antiphon = buildAnnotatedString {
                fonts.forEachIndexed { index, element ->
                    withStyle(style = SpanStyle(color = accentColor)) {
                        append(element.text())
                    }
                    append(texts[index].text())
                }
            }
        }
        psalm.text = buildAnnotatedString {
            append(psalm.antiphon)
            psalmElements?.select(".zak")?.forEach { element ->
                append("\n\n")
                element.textNodes().forEach { textNode ->
                    append(textNode.text() + "\n")
                }
                append(psalm.antiphon)
            }
        }

        val ending = buildAnnotatedString {
            withStyle(style = SpanStyle(color = accentColor)) {
                append(element.select("div").last()?.child(0)?.text())
            }
        }

        return Invitatory(
            beginning = beginning,
            psalm = psalm,
            ending = ending,
        )
    }
}