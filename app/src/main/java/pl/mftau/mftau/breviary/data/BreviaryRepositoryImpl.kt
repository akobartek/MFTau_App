package pl.mftau.mftau.breviary.data

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.withStyle
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import org.jsoup.Jsoup
import org.jsoup.nodes.Document
import org.jsoup.nodes.Element
import org.jsoup.nodes.Node
import org.jsoup.nodes.TextNode
import pl.mftau.mftau.breviary.model.Breviary
import pl.mftau.mftau.breviary.model.BreviaryType
import pl.mftau.mftau.breviary.model.Invitatory
import pl.mftau.mftau.breviary.model.MajorHour
import pl.mftau.mftau.breviary.model.BreviaryPart
import pl.mftau.mftau.breviary.model.Canticle
import pl.mftau.mftau.breviary.model.Psalm
import pl.mftau.mftau.breviary.model.Psalmody
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

        val breviaryElement = element.firstElementChild()
        return when (type) {
            BreviaryType.INVITATORY -> getInvitatory(breviaryElement)
            BreviaryType.LAUDS, BreviaryType.VESPERS -> getMajorHour(breviaryElement)
            else -> object : Breviary() {}
        }
    }

    private fun getInvitatory(element: Element?): Invitatory {
        val opening =
            processTextDiv(element?.firstElementChild()?.firstElementChild()?.firstElementChild())

        val psalm = Psalm()
        val psalmElements = element?.lastElementChild()
            ?.firstElementChild()
            ?.firstElementChild()
            ?.children()
        psalmElements?.first()?.children()?.first()?.let { elem ->
            psalm.name = elem.child(0).text() ?: ""
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
            psalm.antiphon1 = buildAnnotatedString {
                fonts.forEachIndexed { index, element ->
                    withStyle(style = SpanStyle(color = accentColor)) {
                        append(element.text())
                    }
                    append(texts[index].text())
                }
            }
        }
        psalm.text = buildAnnotatedString {
            append(psalm.antiphon1)
            psalmElements?.select(".zak")?.forEach { element ->
                append("\n\n")
                element.textNodes().forEach { textNode ->
                    append(textNode.text() + "\n")
                }
                append(psalm.antiphon1)
            }
        }

        val ending = buildAnnotatedString {
            withStyle(style = SpanStyle(color = accentColor)) {
                append(element?.select("div")?.last()?.child(0)?.text())
            }
        }

        return Invitatory(
            opening = opening,
            psalm = psalm,
            ending = ending,
        )
    }

    private fun getMajorHour(element: Element?): MajorHour {
        val elements = element?.children()
        val openingAndPsalmodyElement = elements?.first()?.firstElementChild()?.lastElementChild()
        val opening = buildAnnotatedString {
            openingAndPsalmodyElement?.select(".c")?.take(5)?.forEach { elem ->
                append(processTextDiv(elem))
                append("\n")
            }
            append(processTextDiv(openingAndPsalmodyElement?.selectFirst(".zak")))
            append("\n")
        }

        val hymnPages = openingAndPsalmodyElement?.select("a")
            ?.first { it.html().contains("LG skrócone") }
            ?.text()
        val hymnText = buildAnnotatedString {
            val hymnElements = openingAndPsalmodyElement?.children()?.let { children ->
                children.slice(
                    children.indexOfFirst { it.hasClass("a") }
                            ..children.indexOfLast { it.hasClass("b") }
                )
            }
            hymnElements?.forEachIndexed { index, div ->
                if (index > 0 && div.hasClass("a")) append("\n")
                if (div.hasClass("b")) append("\u00A0\u00A0")
                append(processTextDiv(div))
                if (index < hymnElements.size - 1)
                    append("\n")
            }
        }

        val psalmody = openingAndPsalmodyElement?.children()?.let { children ->
            children.slice(
                children.indexOfFirst { it.html().contains("Psalmodia", ignoreCase = true) } + 1
                        ..<children.size
            ).toMutableList()
        }
        val psalmodyPages = processTextDiv(psalmody?.first { it.tagName() == "a" })
        val psalms = arrayListOf<Psalm>()
        repeat(3) {
            val psalmDivs = psalmody?.filter { it.tagName() == "div" }?.take(5)
            psalmDivs?.let {
                psalms.add(processPsalm(it))
                psalmody.removeAll(it)
            }
        }

        val readingHeaderElements = elements?.get(1)?.children()
        val readingPages = readingHeaderElements?.first()
            ?.select("a")
            ?.first { it.html().contains("LG skrócone") }?.text()
        val readingVerses = readingHeaderElements?.last()?.text()

        val readingAndResponsory = elements?.get(2)?.child(0)
        val readingText = processTextDiv(readingAndResponsory?.select("div")?.first())
        val responsoryPages = readingAndResponsory?.select("a")
            ?.first { it.html().contains("LG skrócone") }?.text()
        val responsory = processTextDiv(readingAndResponsory?.select("div")?.last())

        val canticleHeaderElements = elements?.get(3)?.children()
        val canticleName = canticleHeaderElements?.first()?.selectFirst("div")?.text()
        val canticlePages = canticleHeaderElements?.first()?.select("a")
            ?.first { it.html().contains("LG skrócone") }?.text()
        val canticleVerses = canticleHeaderElements?.last()?.text()

        val canticleAndIntercessions = elements?.get(4)?.child(0)
        val canticle = processCanticle(
            canticleAndIntercessions?.select("div")?.first()?.children()?.toList() ?: listOf(),
            canticleName ?: "", canticlePages ?: "", canticleVerses ?: ""
        )
        val intercessionsPages = canticleAndIntercessions?.select("a")
            ?.first { it.html().contains("LG skrócone") }?.text()
        val intercessions = processTextDiv(canticleAndIntercessions?.select("div")?.last())

        val lastChild = elements?.last()?.child(0)?.children()
        val lordsPrayer = buildAnnotatedString {
            val lordsPrayerFormula = lastChild?.first()?.text()
            val lordsPrayerTexts = lastChild?.last()?.textNodes()?.take(8)
            withStyle(style = SpanStyle(color = accentColor)) {
                append(lordsPrayerFormula + "\n")
            }
            lordsPrayerTexts?.forEach { append(it.text() + "\n") }
        }
        val endingDivs = lastChild?.select(".ww")
        val prayerPages = lastChild?.select("a")?.last { elem -> elem.text() != "" }?.text()
        val prayerText = processTextDiv(endingDivs?.first()?.child(0))
        endingDivs?.removeAt(0)

        val ending = processTextDiv(endingDivs?.get(0))
            .plus(buildAnnotatedString { append("\n\n") })
            .plus(processTextDiv(endingDivs?.get(1)))

        return MajorHour(
            opening = opening,
            hymn = BreviaryPart(hymnPages ?: "", hymnText),
            psalmody = Psalmody(psalmodyPages.toString(), psalms),
            reading = BreviaryPart(readingPages ?: "", readingText, readingVerses ?: ""),
            responsory = BreviaryPart(responsoryPages ?: "", responsory),
            canticle = canticle,
            intercessions = BreviaryPart(intercessionsPages ?: "", intercessions),
            lordsPrayer = lordsPrayer,
            prayer = BreviaryPart(prayerPages ?: "", prayerText),
            ending = ending
        )
    }

    private fun processTextDiv(div: Element?): AnnotatedString = buildAnnotatedString {
        val nodes = div?.childNodes()?.toMutableList()
        val checkIfNotEmpty = { node: Node ->
            (node !is Element && node.outerHtml() != "") || (node is Element && node.tagName() != "br")
        }
        val firstIndex = nodes?.indexOfFirst(checkIfNotEmpty)
        val lastIndex = nodes?.indexOfLast(checkIfNotEmpty)

        if (firstIndex != null && lastIndex != null)
            nodes.slice(firstIndex..lastIndex).forEach { node ->
                if (node is TextNode) append(node.text())
                else if (node is Element) {
                    when (node.tagName()) {
                        "font" -> withStyle(style = SpanStyle(color = accentColor)) {
                            append(node.text())
                            if (node.html().endsWith("<br>")) append("\n")
                        }

                        "i" -> withStyle(style = SpanStyle(fontStyle = FontStyle.Italic)) {
                            append(node.text())
                            if (node.html().endsWith("<br>")) append("\n")
                        }

                        "div" -> {
                            append(processTextDiv(node))
                            append("\n")
                        }

                        "br" -> append("\n")

                        else -> append(node.text())
                    }
                }
            }
    }

    private fun processPsalm(divs: List<Element>): Psalm {
        val psalm = Psalm()
        psalm.antiphon1 = processTextDiv(divs.first())
        psalm.antiphon2 = processTextDiv(divs.last())
        divs[1].child(0).let { elem ->
            psalm.name = elem.child(0).text() ?: ""
            psalm.title = elem.child(2).text() ?: ""
        }
        psalm.subtitle = divs[2].child(0).text()
        psalm.text = buildAnnotatedString {
            divs[3].children().forEachIndexed { index, element ->
                if (element.hasClass("c")) append("\u00A0\u00A0\u00A0\u00A0")
                append(processTextDiv(element))
                if (index < divs[3].children().size - 1)
                    append("\n")
            }
        }
        return psalm
    }

    private fun processCanticle(
        divs: List<Element>, name: String, pages: String, verses: String
    ): Canticle {
        val antiphon1 = processTextDiv(divs.first())
        val antiphon2 = processTextDiv(divs.last())
        val textBuilder = StringBuilder()
        divs[1].children().forEachIndexed { index, element ->
            if (element.hasClass("c")) textBuilder.append("\u00A0\u00A0\u00A0\u00A0")
            textBuilder.append(element.text())
            if (index < divs[1].children().size - 1)
                textBuilder.append("\n")
        }
        return Canticle(pages, name, verses, antiphon1, textBuilder.toString(), antiphon2)
    }
}