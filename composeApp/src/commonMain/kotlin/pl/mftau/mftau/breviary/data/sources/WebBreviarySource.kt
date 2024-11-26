package pl.mftau.mftau.breviary.data.sources

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
import com.fleeksoft.ksoup.Ksoup
import com.fleeksoft.ksoup.nodes.Document
import com.fleeksoft.ksoup.nodes.Element
import com.fleeksoft.ksoup.nodes.Node
import com.fleeksoft.ksoup.nodes.TextNode
import com.fleeksoft.ksoup.select.Elements
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import pl.mftau.mftau.breviary.domain.model.Breviary
import pl.mftau.mftau.breviary.domain.model.Breviary.*
import pl.mftau.mftau.breviary.domain.model.BreviaryPart
import pl.mftau.mftau.breviary.domain.model.BreviaryType
import pl.mftau.mftau.breviary.domain.model.Canticle
import pl.mftau.mftau.breviary.domain.model.Hymn
import pl.mftau.mftau.breviary.domain.model.Psalm
import pl.mftau.mftau.breviary.domain.model.Psalmody
import kotlin.coroutines.cancellation.CancellationException

class WebBreviarySource {

    private val breviaryUrlTypes = arrayOf(
        "wezw", "godzczyt", "jutrznia", "modlitwa1",
        "modlitwa2", "modlitwa3", "nieszpory", "kompleta"
    )
    private var accentColor = Color.White

    private fun buildBaseBreviaryUrl(date: String, withDays: Boolean): String {
        val romanMonths =
            arrayOf("i", "ii", "iii", "iv", "v", "vi", "vii", "viii", "ix", "x", "xi", "xii")
        val split = date.split(".")
        val day = split[0]
        val month = split[1]
        val monthInt = month.toInt()
        val year = split[2]

        return if (withDays) "https://brewiarz.pl/${romanMonths[monthInt - 1]}_$year/$day$month/"
        else "https://brewiarz.pl/${romanMonths[monthInt - 1]}_$year/"
    }

    private suspend fun getDocumentFromUrl(url: String): Document {
        val client = HttpClient {
            this.followRedirects = false
        }
        val html = client.get(url).body<ByteArray>()
            .decodeToString()
            .replace("±", "ą")
            .replace("¡", "Ą")
            .replace("æ", "ć")
            .replace("Æ", "Ć")
            .replace("ê", "ę")
            .replace("Ê", "Ę")
            .replace("³", "ł")
            .replace("£", "Ł")
            .replace("ñ", "ń")
            .replace("Ñ", "Ń")
            .replace("¶", "ś")
            .replace("¦", "Ś")
            .replace("¿", "ż")
            .replace("¯", "Ż")
            .replace("¼", "ź")
            .replace("¿", "Ź") // TODO

        return Ksoup.parse(html = html)
    }

    suspend fun checkIfThereAreMultipleOffices(
        date: String
    ): Result<Map<String, String>?> {
        try {
            val breviaryUrl = buildBaseBreviaryUrl(date, true) + "index.php3"
            val document = getDocumentFromUrl(breviaryUrl)

            return if (!document.html().contains("WYBIERZ OFICJUM", true))
                Result.success(null)
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
                Result.success(offices)
            }
        } catch (exc: Exception) {
            exc.printStackTrace()
            if (exc !is CancellationException) return Result.failure(exc)
            else throw exc
        }
    }

    suspend fun loadBreviary(
        office: String,
        date: String,
        type: BreviaryType,
        onlyHtml: Boolean,
        accentColor: Color,
    ): Result<Breviary> {
        val html = try {
            this.accentColor = accentColor
            val breviaryUrl = buildBaseBreviaryUrl(date, office == "") +
                    "${if (office != "") "$office/" else ""}${breviaryUrlTypes[type.type]}.php3"
            getDocumentFromUrl(breviaryUrl)
        } catch (exc: Exception) {
            exc.printStackTrace()
            if (exc !is CancellationException) return Result.failure(exc)
            else throw exc
        }.html()

        if (onlyHtml) return Result.success(BreviaryHtml(html))

        return processBreviaryDocument(
            html = html,
            type = type,
            onlyHtml = onlyHtml,
        )
    }

    fun loadBreviaryFromHtml(
        html: String,
        type: BreviaryType,
    ): Result<Breviary> =
        processBreviaryDocument(
            html = html,
            type = type,
            onlyHtml = false,
        )

    private fun processBreviaryDocument(
        html: String,
        type: BreviaryType,
        onlyHtml: Boolean,
    ): Result<Breviary> {
        return try {
            Result.success(getProperBreviaryObject(Ksoup.parse(html = html), type, onlyHtml))
        } catch (exc: Exception) {
            exc.printStackTrace()
            if (exc !is CancellationException)
                Result.success(getProperBreviaryObject(Ksoup.parse(html = html), type, true))
            else throw exc
        }
    }

    private fun getProperBreviaryObject(
        document: Document,
        type: BreviaryType,
        onlyHtml: Boolean,
    ): Breviary {
        val searchForString = if (type == BreviaryType.INVITATORY) " Psalm " else "Psalmodia"
        val element = document.select("table")
            .last { it.outerHtml().contains(searchForString, ignoreCase = true) }

        if (type == BreviaryType.OFFICE_OF_READINGS) {
            element.select("i")
                .firstOrNull { it.html().contains("Te Deum") }
                ?.parentNode()?.remove()
            if (!element.html().contains("\"def1\"")) {
                document.getElementById("def1")?.let { elem ->
                    element.appendChild(elem.child(0))
                    if (!onlyHtml)
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
        if (onlyHtml)
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
                elem.attr("href").contains("javascript")
                        || elem.attr("href").contains("../../")
                        || elem.hasAttr("onclick")
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

        if (onlyHtml) return BreviaryHtml(breviaryHtml)

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

        val psalmElements = elements.lastOrNull()
            ?.firstElementChild()
            ?.firstElementChild()
            ?.children()
        var psalmName: String? = null
        var psalmTitle: String? = null
        var psalmSubtitle: String? = null
        psalmElements?.firstOrNull()?.children()?.firstOrNull()?.let { elem ->
            psalmName = elem.child(0).text()
            psalmTitle = elem.child(2).text()
        }
        psalmElements?.removeAt(0)
        psalmElements?.select(".ww")?.firstOrNull()?.let { elem ->
            psalmSubtitle = elem.text()
        }
        psalmElements?.removeAt(0)
        val psalmPages = psalmElements?.select("a")?.firstOrNull()?.let { elem ->
            elem.textNodes().getOrNull(0)?.text() + "\n" + elem.lastElementChild()?.text()
        }
        psalmElements?.removeAt(0)
        val antiphon = psalmElements?.select(".cd")?.firstOrNull()?.let { div ->
            val fonts = div.children()
            val texts = div.textNodes()
            buildAnnotatedString {
                fonts.forEachIndexed { index, element ->
                    withStyle(style = SpanStyle(color = accentColor)) {
                        append(element.text())
                    }
                    append(texts[index].text())
                }
            }
        } ?: buildAnnotatedString { }
        val psalmText = buildAnnotatedString {
            append(antiphon)
            psalmElements?.select(".zak")?.forEach { element ->
                appendLine().appendLine()
                element.textNodes().forEach { textNode ->
                    append(textNode.text() + "\n")
                }
                append(antiphon)
            }
        }
        val psalm = Psalm(
            antiphon1 = antiphon,
            name = psalmName,
            title = psalmTitle,
            subtitle = psalmSubtitle,
            breviaryPages = psalmPages,
            text = psalmText
        )

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
                        .firstOrNull { it.html().contains("abc1") && it.tagName() == "div" }
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
        val canticleAndIntercessions = elements[4].child(0)
        val intercessionsPages = canticleAndIntercessions.select("a")
            .firstOrNull { it.html().contains("LG skrócone") }?.text()
        val intercessions = processTextDiv(canticleAndIntercessions.select("div").lastOrNull())

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
        val reading = processReading(elements)
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
            reading = reading,
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

    private fun processHymn(elements: Elements): Hymn {
        val openingAndPsalmodyElement =
            elements.firstOrNull()?.firstElementChild()?.lastElementChild()
        val hymnPages = openingAndPsalmodyElement?.select("a")
            ?.firstOrNull { it.html().contains("LG skrócone") }
            ?.text()

        val getText = { children: Elements? ->
            buildAnnotatedString {
                val textElements = children?.slice(
                    children.indexOfFirst { it.hasClass("a") }
                            ..children.indexOfLast { it.hasClass("b") }
                )
                textElements?.forEachIndexed { index, div ->
                    if (index > 0 && div.hasClass("a")) appendLine()
                    if (div.hasClass("b")) append("\u00A0\u00A0")
                    append(processTextDiv(div))
                    if (index < textElements.size - 1)
                        appendLine()
                }
            }
        }

        val psalmodyElemIndex = openingAndPsalmodyElement?.children()
            ?.indexOfFirst { it.html().contains("Psalmodia", ignoreCase = true) }
        val ulElem = openingAndPsalmodyElement?.select("ul")?.firstOrNull()
        val versionDivs =
            if ((openingAndPsalmodyElement?.children()?.indexOf(ulElem) ?: 0)
                < (psalmodyElemIndex ?: 0)
            ) {
                ulElem
                    ?.select("a")
                    ?.map { it.attr("rel") }
                    ?.map { id -> openingAndPsalmodyElement.getElementById(id) }
            } else null

        val hymnText = getText(
            if (versionDivs.isNullOrEmpty()) openingAndPsalmodyElement?.children()
            else versionDivs[0]?.children()
        )
        val version2Text =
            if (!versionDivs.isNullOrEmpty()) getText(versionDivs[1]?.children())
            else null
        return Hymn(hymnPages ?: "", hymnText, "", version2Text)
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
        // check for nested elements
        allPsalmsDivs?.forEachIndexed { index, elem ->
            val nestedAntiphon = elem.children().firstOrNull { it.hasClass("cd") }
            nestedAntiphon?.let {
                if (!it.text().contains("Jeżeli poniższa pieśń jest śpiewana"))
                    allPsalmsDivs.add(index + 1, it)
                it.remove()
            }

            // nested text divs
            val nested = elem.children().filter { nestedElem ->
                val children = nestedElem.children()
                children.any { it.hasClass("c") } || children.any { it.hasClass("d") }
            }
            nested.forEach { nestedElem ->
                nestedElem.select("div").forEach {
                    it.appendTo(allPsalmsDivs[index])
                }
                elem.children().filter { it.tagName() == "br" }.forEach { it.remove() }
            }
        }
        allPsalmsDivs?.removeAll { it.text().isBlank() }
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
        val antiphon1 = processTextDiv(divs.firstOrNull())
        val antiphon2 = processTextDiv(divs.lastOrNull())

        var name: String? = null
        var title: String? = null
        var subtitle: String? = null
        if (divs.size == 5 || divs.size == 6) {
            divs[1].child(0).let { elem ->
                name =
                    if (elem.children().size == 1)
                        elem.child(0).textNodes().firstOrNull()?.text() ?: ""
                    else elem.child(0).text()
                title =
                    if (elem.children().size == 1)
                        elem.child(0).textNodes().lastOrNull()?.text() ?: ""
                    else elem.child(2).text()
            }
            subtitle = divs[2].child(0).text()
        }
        val part = when (divs.size) {
            4 -> divs[1].text()
            6 -> divs[3].text()
            else -> null
        }
        val text = buildAnnotatedString {
            if (divs.size <= 6) {
                divs[divs.size - 2].children().forEachIndexed { index, element ->
                    if (element.hasClass("c")) append("\u00A0\u00A0\u00A0\u00A0")
                    append(processTextDiv(element))
                    if (index < divs[divs.size - 2].children().size - 1)
                        appendLine()
                }
            } else {
                divs.forEach { div ->
                    if (div.hasAttr("align") || div.hasClass("ww")) {
                        withStyle(style = ParagraphStyle(textAlign = TextAlign.Center)) {
                            append(processTextDiv(div))
                        }
                    } else if (div.hasAttr("style")) {
                        div.children().forEachIndexed { index, element ->
                            if (element.hasClass("c")) append("\u00A0\u00A0\u00A0\u00A0")
                            append(processTextDiv(element))
                            if (index < div.children().size - 1) appendLine()
                        }
                    }
                }
                // process psalms one by one ->
                // if hasAttr("align") -> align processTextDiv to center
                // if has class .ww -> italic processTextDiv
                // else process psalm as above
            }
        }
        return Psalm(
            antiphon1 = antiphon1,
            antiphon2 = antiphon2,
            name = name,
            title = title,
            subtitle = subtitle,
            part = part,
            text = text
        )
    }

    private fun processReading(elements: Elements): BreviaryPart {
        val readingHeaderElements = elements[1].children()
        val readingPages = readingHeaderElements.firstOrNull()
            ?.select("a")
            ?.firstOrNull { it.html().contains("LG skrócone") }?.text()
        val readingVerses = readingHeaderElements.lastOrNull()?.text()

        val readingAndResponsory = elements[2].child(0)
        val readingText = buildAnnotatedString {
            val slice = readingAndResponsory.children().slice(
                0..readingAndResponsory.children().indexOf(readingAndResponsory.selectFirst("a"))
            ).filter { it.tagName() == "div" }
            slice.forEachIndexed { index, element ->
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
        val readingAndResponsory = elements[2].child(0)
        val responsoryPages = readingAndResponsory.select("a")
            .firstOrNull { it.html().contains("LG skrócone") }?.text()
        val responsory = processTextDiv(readingAndResponsory.select("div").lastOrNull())
        return BreviaryPart(responsoryPages ?: "", responsory)
    }

    private fun processCanticle(elements: Elements, isCompline: Boolean = false): Canticle {
        val canticleHeaderElements = elements[3].children()
        val canticleName = canticleHeaderElements.firstOrNull()
            ?.selectFirst("div")?.text()?.replace("-", "") ?: ""
        val canticlePages = canticleHeaderElements.firstOrNull()?.select("a")
            ?.firstOrNull { it.html().contains("LG skrócone") }?.text() ?: ""
        val canticleVerses = canticleHeaderElements.lastOrNull()?.text() ?: ""

        val canticleAndIntercessions = elements[4].child(0)
        val divsList = canticleAndIntercessions.select("div").firstOrNull()?.children()?.toList()
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