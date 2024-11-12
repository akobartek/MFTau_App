package pl.mftau.mftau.readings.domain

import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.ParagraphStyle
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.BaselineShift
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextIndent
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.sp

abstract class ReadingsRepository {
    protected fun justifiedString(text: String, firstLineIndent: Int = 12) =
        buildAnnotatedString {
            withStyle(
                style = ParagraphStyle(
                    textAlign = TextAlign.Justify,
                    textIndent = TextIndent(firstLine = firstLineIndent.sp, restLine = 0.sp),
                )
            ) { append(text) }
        }

    protected fun justifiedString(text: AnnotatedString, firstLineIndent: Int = 12) =
        buildAnnotatedString {
            withStyle(
                style = ParagraphStyle(
                    textAlign = TextAlign.Justify,
                    textIndent = TextIndent(firstLine = firstLineIndent.sp, restLine = 0.sp),
                )
            ) { append(text) }
        }

    protected fun processIndicesAndText(array: Array<Pair<Int, String>>) = buildAnnotatedString {
        array.forEach {(number, text) ->
            withStyle(
                SpanStyle(fontSize = 10.sp, baselineShift = BaselineShift(0.3f))
            ) { append("$number") }
            append(text)
        }
    }

    protected fun buildChapter(name: String, text: String) = buildAnnotatedString {
        withStyle(ParagraphStyle(textAlign = TextAlign.Center)) {
            withStyle(SpanStyle(fontSize = 18.sp, fontWeight = FontWeight.SemiBold)) {
                append("\n$name\n")
            }
        }
        append(justifiedString(text))
    }

    protected fun buildChapter(name: String, array: Array<Pair<Int, String>>) = buildAnnotatedString {
        withStyle(ParagraphStyle(textAlign = TextAlign.Center)) {
            withStyle(SpanStyle(fontSize = 18.sp, fontWeight = FontWeight.SemiBold)) {
                append("\n$name\n")
            }
        }
        append(justifiedString(processIndicesAndText(array)))
    }
}