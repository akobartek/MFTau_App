package pl.mftau.mftau.leaders.presentation.presence.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import mftau.composeapp.generated.resources.Res
import mftau.composeapp.generated.resources.absent
import mftau.composeapp.generated.resources.justified
import mftau.composeapp.generated.resources.present
import org.jetbrains.compose.resources.stringResource
import pl.mftau.mftau.common.presentation.composables.WidthSpacer
import pl.mftau.mftau.common.utils.roundToDecimals
import kotlin.math.min

@Composable
fun PresenceDetailsChart(
    presence: List<Float>,
    colors: List<Color>,
) {
    Row(
        horizontalArrangement = Arrangement.Center,
        modifier = Modifier
            .padding(vertical = 16.dp)
            .fillMaxWidth()
            .height(IntrinsicSize.Min),
    ) {
        Box(
            modifier = Modifier
                .size(200.dp)
                .drawBehind {
                    val colorsList = colors.toList()
                    var startAngle = -90f
                    val size = min(size.width, size.height)

                    presence.forEachIndexed { index, value ->
                        val angle = value * 360
                        drawArc(
                            color = colorsList[index],
                            startAngle = startAngle,
                            sweepAngle = angle,
                            useCenter = true,
                            size = Size(size, size),
                            topLeft = Offset(x = 0f, y = 0f)
                        )
                        startAngle += angle
                    }
                },
        )
        WidthSpacer(width = 12.dp)
        Column(
            verticalArrangement = Arrangement.Center,
            modifier = Modifier.fillMaxHeight(),
        ) {
            val stringRes = listOf(Res.string.present, Res.string.justified, Res.string.absent)
            presence.forEachIndexed { index, value ->
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Spacer(
                        modifier = Modifier
                            .size(8.dp)
                            .background(colors[index]),
                    )
                    Text(
                        text = stringResource(
                            stringRes[index],
                            "${(value * 100).roundToDecimals(2)}%"
                        ),
                        color = MaterialTheme.colorScheme.onBackground,
                        style = MaterialTheme.typography.bodySmall,
                        modifier = Modifier.padding(4.dp),
                    )
                }
            }
        }
    }
}