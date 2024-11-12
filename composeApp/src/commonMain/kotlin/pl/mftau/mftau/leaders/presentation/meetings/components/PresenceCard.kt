package pl.mftau.mftau.leaders.presentation.meetings.components

import androidx.compose.animation.core.Animatable
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.drawText
import androidx.compose.ui.text.rememberTextMeasurer
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.launch
import pl.mftau.mftau.leaders.domain.model.MeetingType
import pl.mftau.mftau.leaders.domain.model.PersonPresence
import pl.mftau.mftau.ui.theme.ColorTheme
import pl.mftau.mftau.ui.theme.MFTauTheme

@Composable
fun PresenceCard(
    modifier: Modifier = Modifier,
    presence: PersonPresence,
    showJustified: Boolean = true,
    onClick: (PersonPresence) -> Unit = {},
) {
    val scope = rememberCoroutineScope()
    val absenceHeights = remember {
        arrayOf(Animatable(0f), Animatable(0f), Animatable(0f))
    }

    Card(
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.secondaryContainer
        ),
        onClick = { onClick(presence) },
        modifier = modifier.fillMaxWidth()
    ) {
        Column(
            verticalArrangement = Arrangement.spacedBy(6.dp),
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 8.dp, vertical = 12.dp)
        ) {
            Text(
                text = presence.personName,
                style = MaterialTheme.typography.titleMedium,
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth()
            )

            val lineColor = MaterialTheme.colorScheme.secondary
            val textMeasurer = rememberTextMeasurer()
            val textStyle = TextStyle(
                color = MaterialTheme.colorScheme.onSecondaryContainer,
                textAlign = TextAlign.Center
            )
            Box(modifier = Modifier
                .padding(horizontal = 4.dp)
                .padding(top = 16.dp)
                .fillMaxWidth()
                .height(120.dp)
                .border(width = 0.5.dp, color = lineColor)
                .drawBehind {
                    val breakSize = (size.width - 120.dp.toPx()) / 4
                    repeat(5) {
                        drawLine(
                            color = lineColor,
                            start = Offset(0f, (24 * it).dp.toPx()),
                            end = Offset(size.width, (24 * it).dp.toPx())
                        )
                    }
                    presence.presence.keys.forEachIndexed { index, type ->
                        val firstVal = presence.presence[type]!!.first
                        val secondVal = presence.presence[type]!!.second
                        val presenceHeight = size.height * firstVal
                        val justifiedHeight = size.height * secondVal
                        val startX = (breakSize * (index + 1)) + (40.dp.toPx() * index)
                        val colors = type.getProperColors()
                        val textStart =
                            size.height - presenceHeight - 16.dp.toPx() - absenceHeights[index].value

                        drawRect(
                            color = colors.first,
                            topLeft = Offset(x = startX, y = (size.height - presenceHeight)),
                            size = Size(40.dp.toPx(), presenceHeight)
                        )

                        drawRect(
                            color = colors.second,
                            topLeft = Offset(
                                x = startX,
                                y = (size.height - (absenceHeights[index].value + presenceHeight))
                            ),
                            size = Size(40.dp.toPx(), absenceHeights[index].value)
                        )

                        val percentVal =
                            ((if (showJustified) firstVal + secondVal else firstVal) * 100).toInt()
                        drawText(
                            textMeasurer = textMeasurer,
                            text = "$percentVal%",
                            topLeft = Offset(startX, textStart),
                            size = Size(40.dp.toPx(), 20.dp.toPx()),
                            style = textStyle
                        )

                        scope.launch {
                            absenceHeights[index].animateTo(
                                targetValue = if (showJustified) justifiedHeight else 0f
                            )
                        }
                    }
                }
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PresenceCardPreview() {
    MFTauTheme(dynamicColor = false, colorTheme = ColorTheme.DARK) {
        PresenceCard(
            presence = PersonPresence(
                personId = "",
                personName = "Franciszek z Asyżu",
                presence = mapOf(
                    MeetingType.FORMATION to Triple(0.7f, 0.3f, 0f),
                    MeetingType.PRAYERFUL to Triple(0.3f, 0.2f, 0.5f),
                    MeetingType.OTHER to Triple(0.2f, 0.5f, 0.3f)
                )
            )
        )
    }
}

@Preview(showBackground = true)
@Composable
fun PresenceCardPreviewWithoutJustified() {
    MFTauTheme(dynamicColor = false, colorTheme = ColorTheme.DARK) {
        PresenceCard(
            showJustified = false,
            presence = PersonPresence(
                personId = "",
                personName = "Franciszek z Asyżu",
                presence = mapOf(
                    MeetingType.FORMATION to Triple(0.5f, 0.3f, 0.2f),
                    MeetingType.PRAYERFUL to Triple(0.3f, 0.2f, 0.5f),
                    MeetingType.OTHER to Triple(0.2f, 0.5f, 0.3f)
                )
            )
        )
    }
}