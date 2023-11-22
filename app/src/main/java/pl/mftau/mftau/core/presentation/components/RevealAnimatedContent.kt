package pl.mftau.mftau.core.presentation.components

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.AnimationSpec
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.unit.IntOffset
import kotlinx.coroutines.launch

@Composable
fun RevealAnimatedContent(
    offset: Offset = Offset.Zero,
    isContentVisible: Boolean = false,
    content: @Composable BoxScope.() -> Unit = {}
) {
    val scope = rememberCoroutineScope()
    val animationSpec: AnimationSpec<Float> = tween(
        durationMillis = 1000,
        easing = LinearOutSlowInEasing
    )
    val offsetX = remember { Animatable(0f) }
    val offsetY = remember { Animatable(0f) }
    val size = remember { Animatable(0f) }
    LaunchedEffect(offset) {
        offsetX.snapTo(offset.x)
        offsetY.snapTo(offset.y)
    }

    Box(
        modifier = Modifier
            .offset { IntOffset(offsetX.value.toInt(), offsetY.value.toInt()) }
            .fillMaxSize(size.value)
            .onGloballyPositioned {
                scope.launch {
                    offsetX.animateTo(
                        targetValue = if (isContentVisible) 0f else offset.x,
                        animationSpec = animationSpec
                    )
                }
                scope.launch {
                    offsetY.animateTo(
                        targetValue = if (isContentVisible) 0f else offset.y,
                        animationSpec = animationSpec
                    )
                }
                scope.launch {
                    size.animateTo(
                        targetValue = if (isContentVisible) 1f else 0f,
                        animationSpec = animationSpec
                    )
                }
            }
    ) {
        content()
    }
}