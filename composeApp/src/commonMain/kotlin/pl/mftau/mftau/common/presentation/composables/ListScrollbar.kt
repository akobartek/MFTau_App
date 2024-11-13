package pl.mftau.mftau.common.presentation.composables

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.gestures.detectVerticalDragGestures
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import kotlin.math.roundToInt

@Composable
fun ListScrollbar(
    isVisible: Boolean,
    headerHeight: Dp = 0.dp,
    listState: LazyListState,
    listSize: Int,
    onDrag: (Int) -> Unit
) {
    if (isVisible) {
        val firstVisibleItemIndex = remember { derivedStateOf { listState.firstVisibleItemIndex } }
        val visibleItems =
            remember { derivedStateOf { listState.layoutInfo.visibleItemsInfo.size } }

        BoxWithConstraints(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .padding(top = headerHeight)
                .fillMaxHeight()
                .width(12.dp)
        ) {
            val boxScope = this
            val height = boxScope.maxHeight - 24.dp
            val heightPx = with(LocalDensity.current) { height.toPx() }
            val colorOn = MaterialTheme.colorScheme.primary
            val colorOff = MaterialTheme.colorScheme.surfaceVariant

            if (heightPx > 0)
                Canvas(
                    modifier = Modifier
                        .height(height)
                        .pointerInput(remember { MutableInteractionSource() }) {
                            detectVerticalDragGestures(
                                onVerticalDrag = { change, _ ->
                                    val newPositionPx = change.position.y.coerceIn(0f, heightPx)
                                    onDrag((listSize * (newPositionPx / heightPx)).roundToInt())
                                }
                            )
                        }
                ) {
                    val visible = visibleItems.value - 2 // it works best with -2 value
                    val rectHeightPx =
                        ((heightPx / listSize) * visible).coerceIn(20.dp.toPx(), heightPx)
                    val middleItemPosition =
                        firstVisibleItemIndex.value.toFloat() + visible.toFloat() / 2
                    val centerY = middleItemPosition / listSize * heightPx

                    drawLine(
                        color = colorOn,
                        start = Offset(center.x, 0f),
                        end = Offset(center.x, centerY),
                        strokeWidth = 1.dp.toPx()
                    )
                    drawLine(
                        color = colorOff,
                        start = Offset(center.x, centerY),
                        end = Offset(center.x, size.height),
                        strokeWidth = 1.dp.toPx()
                    )
                    drawRoundRect(
                        color = colorOn,
                        topLeft = Offset(
                            x = center.x - 4.dp.toPx(),
                            y = centerY - rectHeightPx / 2
                        ),
                        size = Size(width = 8.dp.toPx(), height = rectHeightPx),
                        cornerRadius = CornerRadius(x = 4.dp.toPx(), y = 4.dp.toPx())
                    )
                }
        }
    }
}