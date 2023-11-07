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
                    .lastOrNull { it.html().contains("OFICJUM") }
                    ?.selectFirst("table")
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
            element.select("i")
                .firstOrNull { it.html().contains("Te Deum") }
                ?.parentNode()?.remove()
            if (!element.html().contains("\"def1\"")) {
                document.getElementById("def1")?.let { elem ->
                    element.appendChild(elem.child(0))
                    document.getElementById("def2")?.let { elem2 ->
                        element.appendChild(elem2.child(0))
                    }
                    elem.parent()!!.select("table")
                        .firstOrNull { it.html().contains("RESPONSORIUM") }
                        ?.let { element.appendChild(it) }
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
        val opening =
            processTextDiv(elements.firstOrNull()?.firstElementChild()?.firstElementChild())

        val psalm = Psalm()
        val psalmElements = elements.lastOrNull()
            ?.firstElementChild()
            ?.firstElementChild()
            ?.children()
        psalmElements?.firstOrNull()?.children()?.firstOrNull()?.let { elem ->
            psalm.name = elem.child(0).text() ?: ""
            psalm.title = elem.child(2).text() ?: ""
        }
        psalmElements?.removeAt(0)
        psalmElements?.firstOrNull()?.children()?.firstOrNull()?.let { elem ->
            psalm.subtitle = elem.text()
        }
        psalmElements?.removeAt(0)
        psalmElements?.firstOrNull()?.let { elem ->
            psalm.breviaryPages =
                elem.textNodes()[0]?.text() + "\n" + elem.lastElementChild()?.text()
        }
        psalmElements?.removeAt(0)
        psalmElements?.select(".cd")?.firstOrNull()?.let { div ->
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
                append(elements.select("div").lastOrNull()?.child(0)?.text())
            }
        }

        return Invitatory(
            opening = opening,
            psalm = psalm,
            ending = ending,
        )
    }

    private fun getOfficeOfReading(elements: Elements): OfficeOfReadings {
        val openingAndPsalmodyElements = elements.firstOrNull()!!.children()
        val aElems = openingAndPsalmodyElements.firstOrNull()?.select("a")
            ?.filter { elem -> elem.text().contains("LG tom") }
            ?.takeLast(2)
        val additionalPartText = buildAnnotatedString {
            val divs = openingAndPsalmodyElements.firstOrNull()?.select(".cd")?.takeLast(2)
            appendLine(processTextDiv(divs?.get(0)))
            append(processTextDiv(divs?.get(1)))
        }

        val firstReadingPages = aElems?.lastOrNull()?.text()
        val firstReadingDiv =
            if (elements.size > 1) elements[1]
            else openingAndPsalmodyElements.firstOrNull()
                ?.firstElementChild()
                ?.select("div")
                ?.lastOrNull { it.outerHtml().contains("def1") }
                ?.firstElementChild()
        val firstReading = processOfficeOfReadingsReading(
            firstReadingDiv?.firstElementChild()?.children(), firstReadingPages
        )
        val firstReadingV2 =
            if (elements.size > 1) processOfficeOfReadingsReading(
                elements[2].firstElementChild()?.children(), firstReadingPages
            ) else firstReading

        val lastTableChildren =
            if (elements.size > 1) elements.lastOrNull()?.firstElementChild()?.children()
            else elements.lastOrNull()?.select("table")
                ?.lastOrNull()?.firstElementChild()?.children()

        val resp1Verses = lastTableChildren?.getOrNull(0)?.lastElementChild()?.text() ?: ""
        val reading2Elem = lastTableChildren?.getOrNull(1)?.firstElementChild()
        val resp1Text = reading2Elem?.selectFirst("div.ww")?.let {
            processTextDiv(it)
        } ?: buildAnnotatedString { }
        val resp2Verses = lastTableChildren?.getOrNull(2)?.lastElementChild()?.text() ?: ""
        val lastElem = lastTableChildren?.getOrNull(3)?.firstElementChild()
        val resp2Text = lastElem?.selectFirst("div.ww")?.let {
            processTextDiv(it)
        } ?: buildAnnotatedString { }

        var index = reading2Elem?.children()
            ?.indexOfFirst { elem -> elem.text().contains("LG tom") } ?: -1
        val reading2Text = buildAnnotatedString {
            reading2Elem?.children()?.let { children ->
                if (index == -1)
                    index = children.indexOfFirst { elem ->
                        elem.text().contains("czytanie", ignoreCase = true)
                    }
                val nodes = children.slice(index + 1..<children.size).toMutableList()
                val textDiv = nodes.lastOrNull { it.hasClass("ww") }
                nodes.remove(textDiv)

                val filter = nodes.filter { it.hasClass("c") }
                filter.forEach {
                    append(it.text().replace("\n", "") + " ")
                }
                nodes.removeAll(filter)
                appendLine()

                nodes.filter { it.tagName() == "div" }.forEach {
                    withStyle(style = ParagraphStyle(textAlign = TextAlign.Center)) {
                        append(processTextDiv(it))
                    }
                }
                append(processTextDiv(textDiv))
            }
        }
        val reading2Pages = reading2Elem?.children()?.get(index)?.text() ?: ""

        val teDeum = if (lastElem?.html()?.contains("tedeum") == true) {
            lastElem.children().lastOrNull { it.tagName() == "div" }?.let { teDeumDiv ->
                val pages = teDeumDiv.children()
                    .firstOrNull { elem -> elem.text().contains("LG tom") }
                    ?.text() ?: ""
                val text = buildAnnotatedString {
                    val children = teDeumDiv.children()
                        .firstOrNull { it.html().contains("abc1") }
                        ?.children()?.firstOrNull { it.id() == "abc1" }
                        ?.firstElementChild()?.children()
                    val nestedText = children?.last()?.children() ?: listOf()
                    children?.removeLast()
                    children?.addAll(nestedText)
                    children?.forEachIndexed { index, element ->
                        if (element.hasClass("c")) append("\u00A0\u00A0\u00A0\u00A0")
                        append(processTextDiv(element))
                        if (index < children.size - 1) appendLine()
                    }
                }
                BreviaryPart(pages, text)
            }
        } else null

        val prayerPages = lastElem?.select("a")
            ?.lastOrNull { elem -> elem.text().contains("LG tom") }
            ?.text() ?: ""
        val prayerText = lastElem?.select("div.ww")?.lastOrNull()?.let {
            processTextDiv(it)
        } ?: buildAnnotatedString { }

        val ending = lastElem?.select("div")?.lastOrNull()?.let {
            processTextDiv(it)
        } ?: buildAnnotatedString { }

        return OfficeOfReadings(
            opening = processOpening(openingAndPsalmodyElements),
            hymn = processHymn(openingAndPsalmodyElements),
            psalmody = processPsalmody(openingAndPsalmodyElements),
            additionalPart = BreviaryPart(aElems?.firstOrNull()?.text() ?: "", additionalPartText),
            firstReading = firstReading,
            firstReadingVersion2 = firstReadingV2,
            firstResponsory = BreviaryPart("", resp1Text, resp1Verses),
            secondReading = BreviaryPart(reading2Pages, reading2Text),
            secondResponsory = BreviaryPart("", resp2Text, resp2Verses),
            teDeum = teDeum,
            prayer = BreviaryPart(prayerPages, prayerText),
            ending = ending
        )
    }

    private fun getMajorHour(elements: Elements): MajorHour {
        val canticleAndIntercessions = elements[4]?.child(0)
        val intercessionsPages = canticleAndIntercessions?.select("a")
            ?.firstOrNull { it.html().contains("LG skrócone") }?.text()
        val intercessions = processTextDiv(canticleAndIntercessions?.select("div")?.lastOrNull())

        val lastChild = elements.lastOrNull()?.child(0)?.children()
        val lordsPrayer = buildAnnotatedString {
            val lordsPrayerFormula = lastChild?.firstOrNull()?.text()
            val lordsPrayerTexts = lastChild?.lastOrNull()?.textNodes()?.take(8)
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
        val lastChild = elements.lastOrNull()?.child(0)
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
        val lastChild = elements.lastOrNull()?.child(0)?.children()
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
            val firstIndex = nodes?.indexOfFirst(checkIfNotEmpty) ?: -1
            val lastIndex = nodes?.indexOfLast(checkIfNotEmpty) ?: -1

            if (firstIndex != -1 && lastIndex != -1)
                nodes?.slice(firstIndex..lastIndex)?.forEach { node ->
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
                                node.hasText()
                                val textIndent =
                                    if (node.hasClass("c")) TextIndent(firstLine = 12.sp)
                                    else null
                                val textAlign =
                                    if (node.hasAttr("align") && node.attr("align") == "center")
                                        TextAlign.Center
                                    else null
                                val append = { append(processTextDiv(node)) }
                                try {
                                    if ((node.ownText() != "" && textIndent != null) || textAlign != null)
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
                                    exc.printStackTrace()
                                }
                            }

                            "br" -> if (!ignoreNewLines) appendLine()

                            else -> append(node.text())
                        }
                    }
                }
        }

    private fun processOpening(elements: Elements): AnnotatedString {
        val openingAndPsalmodyElement =
            elements.firstOrNull()?.firstElementChild()?.lastElementChild()
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
        val openingAndPsalmodyElement =
            elements.firstOrNull()?.firstElementChild()?.lastElementChild()
        val hymnPages = openingAndPsalmodyElement?.select("a")
            ?.firstOrNull { it.html().contains("LG skrócone") }
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
        val openingAndPsalmodyElement =
            elements.firstOrNull()?.firstElementChild()?.lastElementChild()
        val psalmodyChildren = openingAndPsalmodyElement?.children()?.let { children ->
            children.slice(
                children.indexOfFirst { it.html().contains("Psalmodia", ignoreCase = true) } + 1
                        ..<children.size
            ).toMutableList()
        }
        if (psalmodyChildren?.firstOrNull()?.tagName() == "div") psalmodyChildren.removeAt(0)
        val psalmodyPages = processTextDiv(psalmodyChildren?.firstOrNull { it.tagName() == "a" })

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
        psalm.antiphon1 = processTextDiv(divs.firstOrNull())
        psalm.antiphon2 = processTextDiv(divs.lastOrNull())
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
        val readingPages = readingHeaderElements?.firstOrNull()
            ?.select("a")
            ?.firstOrNull { it.html().contains("LG skrócone") }?.text()
        val readingVerses = readingHeaderElements?.lastOrNull()?.text()

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
        val verses = elements?.firstOrNull()?.lastElementChild()?.text()
        val text = buildAnnotatedString {
            append(processTextDiv(elements?.firstOrNull()?.firstElementChild()))
            append(processTextDiv(elements?.lastOrNull()?.firstElementChild(), true))
        }
        return BreviaryPart(pages ?: "", text, verses ?: "")
    }

    private fun processResponsory(elements: Elements): BreviaryPart {
        val readingAndResponsory = elements[2]?.child(0)
        val responsoryPages = readingAndResponsory?.select("a")
            ?.firstOrNull { it.html().contains("LG skrócone") }?.text()
        val responsory = processTextDiv(readingAndResponsory?.select("div")?.lastOrNull())
        return BreviaryPart(responsoryPages ?: "", responsory)
    }

    private fun processCanticle(elements: Elements, isCompline: Boolean = false): Canticle {
        val canticleHeaderElements = elements[3]?.children()
        val canticleName = canticleHeaderElements?.firstOrNull()?.selectFirst("div")?.text() ?: ""
        val canticlePages = canticleHeaderElements?.firstOrNull()?.select("a")
            ?.firstOrNull { it.html().contains("LG skrócone") }?.text() ?: ""
        val canticleVerses = canticleHeaderElements?.lastOrNull()?.text() ?: ""

        val canticleAndIntercessions = elements[4]?.child(0)
        val divsList = canticleAndIntercessions?.select("div")?.firstOrNull()?.children()?.toList()
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
            antiphon1 = processTextDiv(antiphonDivs?.firstOrNull()),
            text = getCanticleText(canticleDivs),
            antiphon2 = processTextDiv(antiphonDivs?.lastOrNull())
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
        val lastChild = elements.lastOrNull()?.child(0)?.children()
        val prayerPages = lastChild?.select("a")
            ?.lastOrNull { elem -> elem.html().contains("LG skrócone") }?.text()
        val prayerText = processTextDiv(endingDivs?.firstOrNull()?.child(0))
        return BreviaryPart(prayerPages ?: "", prayerText)
    }
}