package pl.mftau.mftau.breviary.data

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.ParagraphStyle
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextIndent
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import org.jsoup.Jsoup
import org.jsoup.nodes.Document
import org.jsoup.nodes.Element
import org.jsoup.nodes.Node
import org.jsoup.nodes.TextNode
import org.jsoup.select.Elements
import pl.mftau.mftau.breviary.model.Breviary
import pl.mftau.mftau.breviary.model.BreviaryHtml
import pl.mftau.mftau.breviary.model.BreviaryType
import pl.mftau.mftau.breviary.model.Invitatory
import pl.mftau.mftau.breviary.model.MajorHour
import pl.mftau.mftau.breviary.model.BreviaryPart
import pl.mftau.mftau.breviary.model.Canticle
import pl.mftau.mftau.breviary.model.Compline
import pl.mftau.mftau.breviary.model.MinorHour
import pl.mftau.mftau.breviary.model.OfficeOfReadings
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
                    "${if (office != "") "$office/" else ""}${mBreviaryUrlTypes[type.type]}.php3"
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
                document.getElementById("def1")?.let { elem ->
                    element.appendChild(elem.child(0))
                    document.getElementById("def2")?.let { elem2 ->
                        element.appendChild(elem2.child(0))
                    }
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

        var breviaryHtml = element.html()
        for (i in 1..5)
            breviaryHtml = breviaryHtml.replaceFirst("class=\"c\"", "class=\"xD\"")
        breviaryHtml = breviaryHtml
            .replace("<tr><td colspan=2 width=490 class=ww>\n", "")
            .replace("width=\"490\"", "")
            .replace("width:490px", "")
            .replace("color=\"red\">", "color=\"saddlebrown\">")
            .replace("color=\"Red\">", "color=\"saddlebrown\">")
            .replace("color:red", "color:saddlebrown")
            .replace("color:Red", "color:saddlebrown")
            .replace("</a> - ", "</a>")
            .replace("align=\"center\"", "")
            .replace("class=\"b\"", "style=\"text-indent:12pt\"")
            .replace("class=\"c\"", "style=\"text-indent:16pt\"")
            .replace("style=\"margin-left:15pt\"", "")
            .replace("font-size:10pt", "")
            .replace("font-size: 10pt", "")
            .replace("font-size:8pt", "")
            .replace("font-size: 8pt", "")
            .replaceFirst("<br>", "")

        val breviaryChildren =
            if (type == BreviaryType.OFFICE_OF_READINGS) element.children()
            else element.firstElementChild()?.children()
        return breviaryChildren?.let {
            when (type) {
                BreviaryType.INVITATORY -> getInvitatory(breviaryChildren)

                BreviaryType.OFFICE_OF_READINGS -> getOfficeOfReading(breviaryChildren)

                BreviaryType.LAUDS, BreviaryType.VESPERS -> getMajorHour(breviaryChildren)

                BreviaryType.MIDMORNING_PRAYER, BreviaryType.MIDDAY_PRAYER,
                BreviaryType.MIDAFTERNOON_PRAYER -> getMinorHour(breviaryChildren)

                BreviaryType.COMPLINE -> getCompline(breviaryChildren)
            }
        } ?: BreviaryHtml(breviaryHtml)
    }

    private fun getInvitatory(elements: Elements): Invitatory {
        val opening = processTextDiv(elements.first()?.firstElementChild()?.firstElementChild())

        val psalm = Psalm()
        val psalmElements = elements.last()
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
                appendLine().appendLine()
                element.textNodes().forEach { textNode ->
                    append(textNode.text() + "\n")
                }
                append(psalm.antiphon1)
            }
        }

        val ending = buildAnnotatedString {
            withStyle(style = SpanStyle(color = accentColor)) {
                append(elements.select("div").last()?.child(0)?.text())
            }
        }

        return Invitatory(
            opening = opening,
            psalm = psalm,
            ending = ending,
        )
    }

    private fun getOfficeOfReading(elements: Elements): OfficeOfReadings {
        val openingAndPsalmodyElements = elements.first()!!.children()
        val aElems = openingAndPsalmodyElements.first()?.select("a")
            ?.filter { elem -> elem.text().contains("LG tom") }
            ?.takeLast(2)
        val additionalPartText = buildAnnotatedString {
            val divs = openingAndPsalmodyElements.first()?.select(".cd")?.takeLast(2)
            appendLine(processTextDiv(divs?.get(0)))
            append(processTextDiv(divs?.get(1)))
        }
        val firstReadingPages = aElems?.last()?.text()

        return OfficeOfReadings(
            opening = processOpening(openingAndPsalmodyElements),
            hymn = processHymn(openingAndPsalmodyElements),
            psalmody = processPsalmody(openingAndPsalmodyElements),
            additionalPart = BreviaryPart(aElems?.first()?.text() ?: "", additionalPartText),
            firstReading = processOfficeOfReadingsReading(
                elements[1].firstElementChild()?.children(), firstReadingPages
            ),
            firstReadingVersion2 = processOfficeOfReadingsReading(
                elements[2].firstElementChild()?.children(), firstReadingPages
            ),
//            firstResponsory = ,
//            secondReading = ,
//            secondResponsory = ,
//            teDeum = ,
//            prayer = ,
//            ending =
        )
    }

    private fun getMajorHour(elements: Elements): MajorHour {
        val canticleAndIntercessions = elements[4]?.child(0)
        val intercessionsPages = canticleAndIntercessions?.select("a")
            ?.first { it.html().contains("LG skrócone") }?.text()
        val intercessions = processTextDiv(canticleAndIntercessions?.select("div")?.last())

        val lastChild = elements.last()?.child(0)?.children()
        val lordsPrayer = buildAnnotatedString {
            val lordsPrayerFormula = lastChild?.first()?.text()
            val lordsPrayerTexts = lastChild?.last()?.textNodes()?.take(8)
            withStyle(style = SpanStyle(color = accentColor)) {
                append(lordsPrayerFormula + "\n")
            }
            lordsPrayerTexts?.forEach { append(it.text() + "\n") }
        }
        val endingDivs = lastChild?.select(".ww")
        val prayer = processPrayer(elements, endingDivs)
        endingDivs?.removeAt(0)

        val ending = processTextDiv(endingDivs?.get(0))
            .plus(buildAnnotatedString { appendLine().appendLine() })
            .plus(processTextDiv(endingDivs?.get(1)))

        return MajorHour(
            opening = processOpening(elements),
            hymn = processHymn(elements),
            psalmody = processPsalmody(elements),
            reading = processReading(elements),
            responsory = processResponsory(elements),
            canticle = processCanticle(elements),
            intercessions = BreviaryPart(intercessionsPages ?: "", intercessions),
            lordsPrayer = lordsPrayer,
            prayer = prayer,
            ending = ending
        )
    }

    private fun getMinorHour(elements: Elements): MinorHour {
        val lastChild = elements.last()?.child(0)
        val endingDivs = lastChild?.children()?.select(".ww")
        endingDivs?.removeAt(0)
        val prayer = processPrayer(elements, endingDivs)

        val nodes = lastChild?.childNodes()
        val startIndex = (nodes?.indexOfLast { it is Element && it.tagName() == "div" } ?: -1) + 1
        val ending = processNodesText(nodes?.slice(startIndex..<nodes.size))

        return MinorHour(
            opening = processOpening(elements),
            hymn = processHymn(elements),
            psalmody = processPsalmody(elements),
            reading = processReading(elements),
            prayer = prayer,
            ending = ending
        )
    }

    private fun getCompline(elements: Elements): Compline {
        val lastChild = elements.last()?.child(0)?.children()
        val pages = lastChild?.select("a")
            ?.filter { node -> node.html().contains("LG skrócone") }
            ?.takeLast(2)
            ?.map { it.text() }
        val textsDivs = lastChild?.select(".ww")?.takeLast(2)
        val prayerText = buildAnnotatedString {
            textsDivs?.let {
                append(processTextDiv(textsDivs[0]))
                appendLine()
                append(processTextDiv(textsDivs[0].nextElementSibling()))
            }
        }
        val antiphonText = buildAnnotatedString { append(processTextDiv(textsDivs?.get(1))) }

        return Compline(
            opening = processOpening(elements),
            hymn = processHymn(elements),
            psalmody = processPsalmody(elements),
            reading = processReading(elements),
            responsory = processResponsory(elements),
            canticle = processCanticle(elements, true),
            prayer = BreviaryPart(pages?.get(0) ?: "", prayerText),
            antiphon = BreviaryPart(pages?.get(1) ?: "", antiphonText)
        )
    }

    private fun processTextDiv(div: Element?, ignoreNewLines: Boolean = false): AnnotatedString =
        processNodesText(div?.childNodes()?.toList(), ignoreNewLines)

    private fun processNodesText(nodes: List<Node>?, ignoreNewLines: Boolean = false) =
        buildAnnotatedString {
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
                                if (node.html().endsWith("<br>")) appendLine()
                            }

                            "i" -> withStyle(style = SpanStyle(fontStyle = FontStyle.Italic)) {
                                append(processTextDiv(node))
                                if (node.html().endsWith("<br>")) appendLine()
                            }

                            "b" -> withStyle(style = SpanStyle(fontWeight = FontWeight.Bold)) {
                                append(processTextDiv(node))
                            }

                            "div" -> {
                                val textIndent =
                                    if (node.hasClass("c")) TextIndent(firstLine = 12.sp)
                                    else null
                                val textAlign =
                                    if (node.hasAttr("align") && node.attr("align") == "center")
                                        TextAlign.Center
                                    else null
                                val append = { append(processTextDiv(node)) }
                                try {
                                    if (textIndent != null || textAlign != null)
                                        withStyle(
                                            style = ParagraphStyle(
                                                textIndent = textIndent,
                                                textAlign = textAlign ?: TextAlign.Left
                                            )
                                        ) {
                                            append()
                                        }
                                    else append()
                                } catch (exc: IllegalArgumentException) {
                                    append()
                                }
                            }

                            "br" -> if (!ignoreNewLines) appendLine()

                            else -> append(node.text())
                        }
                    }
                }
        }

    private fun processOpening(elements: Elements): AnnotatedString {
        val openingAndPsalmodyElement = elements.first()?.firstElementChild()?.lastElementChild()
        return buildAnnotatedString {
            openingAndPsalmodyElement?.select(".c")?.take(5)?.forEach { elem ->
                append(processTextDiv(elem))
                appendLine()
            }
            val additionInfoDiv = openingAndPsalmodyElement?.selectFirst(".zak")
            additionInfoDiv?.let {
                withStyle(style = SpanStyle(color = accentColor)) {
                    appendLine()
                    append(processTextDiv(additionInfoDiv))
                }
            }
        }
    }

    private fun processHymn(elements: Elements): BreviaryPart {
        val openingAndPsalmodyElement = elements.first()?.firstElementChild()?.lastElementChild()
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
                if (index > 0 && div.hasClass("a")) appendLine()
                if (div.hasClass("b")) append("\u00A0\u00A0")
                append(processTextDiv(div))
                if (index < hymnElements.size - 1)
                    appendLine()
            }
        }
        return BreviaryPart(hymnPages ?: "", hymnText)
    }

    private fun processPsalmody(elements: Elements): Psalmody {
        val openingAndPsalmodyElement = elements.first()?.firstElementChild()?.lastElementChild()
        val psalmodyChildren = openingAndPsalmodyElement?.children()?.let { children ->
            children.slice(
                children.indexOfFirst { it.html().contains("Psalmodia", ignoreCase = true) } + 1
                        ..<children.size
            ).toMutableList()
        }
        if (psalmodyChildren?.first()?.tagName() == "div") psalmodyChildren.removeAt(0)
        val psalmodyPages = processTextDiv(psalmodyChildren?.first { it.tagName() == "a" })

        val psalms = arrayListOf<Psalm>()
        val allPsalmsDivs = psalmodyChildren?.filter { it.tagName() == "div" }?.toMutableList()
        repeat(3) {
            val antiphonDivs = allPsalmsDivs?.filter { it.className() == "cd" }?.take(2)
            antiphonDivs?.let {
                val psalmDivs =
                    if (allPsalmsDivs.isEmpty()) listOf()
                    else allPsalmsDivs.slice(
                        allPsalmsDivs.indexOf(it[0])..allPsalmsDivs.indexOf(it[1])
                    )
                if (psalmDivs.isNotEmpty())
                    psalms.add(processPsalm(psalmDivs))
                allPsalmsDivs.removeAll(psalmDivs)
            }
        }
        return Psalmody(psalmodyPages.toString(), psalms)
    }

    private fun processPsalm(divs: List<Element>): Psalm {
        val psalm = Psalm()
        psalm.antiphon1 = processTextDiv(divs.first())
        psalm.antiphon2 = processTextDiv(divs.last())
        if (divs.size >= 5) {
            divs[1].child(0).let { elem ->
                psalm.name = elem.child(0).text() ?: ""
                psalm.title = elem.child(2).text() ?: ""
            }
            psalm.subtitle = divs[2].child(0).text()
        }
        psalm.part = when (divs.size) {
            4 -> divs[1].text()
            6 -> divs[3].text()
            else -> null
        }
        psalm.text = buildAnnotatedString {
            divs[divs.size - 2].children().forEachIndexed { index, element ->
                if (element.hasClass("c")) append("\u00A0\u00A0\u00A0\u00A0")
                append(processTextDiv(element))
                if (index < divs[divs.size - 2].children().size - 1)
                    appendLine()
            }
        }
        return psalm
    }

    private fun processReading(elements: Elements): BreviaryPart {
        val readingHeaderElements = elements[1]?.children()
        val readingPages = readingHeaderElements?.first()
            ?.select("a")
            ?.first { it.html().contains("LG skrócone") }?.text()
        val readingVerses = readingHeaderElements?.last()?.text()

        val readingAndResponsory = elements[2]?.child(0)
        val readingText = buildAnnotatedString {
            val slice = readingAndResponsory?.children()?.slice(
                0..readingAndResponsory.children().indexOf(readingAndResponsory.selectFirst("a"))
            )?.filter { it.tagName() == "div" }
            slice?.forEachIndexed { index, element ->
                if (index > 0) appendLine()
                append(processTextDiv(element))
            }
        }
        return BreviaryPart(readingPages ?: "", readingText, readingVerses ?: "")
    }

    private fun processOfficeOfReadingsReading(elements: Elements?, pages: String?): BreviaryPart {
        val verses = elements?.first()?.lastElementChild()?.text()
        val text = buildAnnotatedString {
            append(processTextDiv(elements?.first()?.firstElementChild()))
            append(processTextDiv(elements?.last()?.firstElementChild(), true))
        }
        return BreviaryPart(pages ?: "", text, verses ?: "")
    }

    private fun processResponsory(elements: Elements): BreviaryPart {
        val readingAndResponsory = elements[2]?.child(0)
        val responsoryPages = readingAndResponsory?.select("a")
            ?.first { it.html().contains("LG skrócone") }?.text()
        val responsory = processTextDiv(readingAndResponsory?.select("div")?.last())
        return BreviaryPart(responsoryPages ?: "", responsory)
    }

    private fun processCanticle(elements: Elements, isCompline: Boolean = false): Canticle {
        val canticleHeaderElements = elements[3]?.children()
        val canticleName = canticleHeaderElements?.first()?.selectFirst("div")?.text() ?: ""
        val canticlePages = canticleHeaderElements?.first()?.select("a")
            ?.first { it.html().contains("LG skrócone") }?.text() ?: ""
        val canticleVerses = canticleHeaderElements?.last()?.text() ?: ""

        val canticleAndIntercessions = elements[4]?.child(0)
        val divsList = canticleAndIntercessions?.select("div")?.first()?.children()?.toList()
        val antiphonDivs = divsList
            ?.filter { it.className() == "cd" || it.className() == "cdx" }
            ?.take(2)
        val canticleDivs = antiphonDivs?.let {
            val divs = divsList.slice(divsList.indexOf(it[0]) + 1..<divsList.indexOf(it[1]))

            if (!isCompline) divs[0].children()
            else divs
        } ?: listOf()

        return Canticle(
            breviaryPages = canticlePages,
            name = canticleName,
            verses = canticleVerses,
            antiphon1 = processTextDiv(antiphonDivs?.first()),
            text = getCanticleText(canticleDivs),
            antiphon2 = processTextDiv(antiphonDivs?.last())
        )
    }

    private fun getCanticleText(divs: List<Element>): String {
        val textBuilder = StringBuilder()
        divs.forEachIndexed { index, element ->
            if (element.hasClass("c")) textBuilder.append("\u00A0\u00A0\u00A0\u00A0")
            textBuilder.append(element.text())
            if (index < divs.size - 1)
                textBuilder.appendLine()
        }
        return textBuilder.toString()
    }

    private fun processPrayer(elements: Elements, endingDivs: Elements?): BreviaryPart {
        val lastChild = elements.last()?.child(0)?.children()
        val prayerPages = lastChild?.select("a")
            ?.last { elem -> elem.html().contains("LG skrócone") }?.text()
        val prayerText = processTextDiv(endingDivs?.first()?.child(0))
        return BreviaryPart(prayerPages ?: "", prayerText)
    }
}