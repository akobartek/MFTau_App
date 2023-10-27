package pl.mftau.mftau.ui

import androidx.compose.animation.ContentTransform
import androidx.compose.animation.core.FiniteAnimationSpec
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.VisibilityThreshold
import androidx.compose.animation.core.spring
import androidx.compose.animation.scaleIn
import androidx.compose.animation.scaleOut
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.animation.slideOutVertically
import androidx.compose.animation.togetherWith
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.IntOffset
import cafe.adriel.voyager.core.stack.StackEvent
import cafe.adriel.voyager.navigator.Navigator
import cafe.adriel.voyager.transitions.ScreenTransition
import cafe.adriel.voyager.transitions.ScreenTransitionContent
import cafe.adriel.voyager.transitions.SlideOrientation
import pl.mftau.mftau.core.presentation.screens.MainScreen
import pl.mftau.mftau.gospel.presentation.GospelScreen
import pl.mftau.mftau.prayers.presentation.PrayersScreen
import pl.mftau.mftau.songbook.presentation.SongBookScreen

sealed class TransitionType {
    data class Slide(
        val orientation: SlideOrientation = SlideOrientation.Horizontal,
        val isReversed: Boolean = false
    ) : TransitionType()

    data object Scale : TransitionType()
}

@Composable
fun CustomTransition(
    navigator: Navigator,
    modifier: Modifier = Modifier,
    animationSpec: FiniteAnimationSpec<IntOffset> = spring(
        stiffness = Spring.StiffnessMediumLow,
        visibilityThreshold = IntOffset.VisibilityThreshold
    ),
    content: ScreenTransitionContent = { it.Content() }
) {
    var currentTransitionType: TransitionType? by remember { mutableStateOf(null) }

    ScreenTransition(
        navigator = navigator,
        modifier = modifier,
        content = content,
        transition = {
            currentTransitionType = when (navigator.lastItem) {
                is MainScreen -> currentTransitionType ?: TransitionType.Scale
                is SongBookScreen -> TransitionType.Scale
                is GospelScreen -> TransitionType.Slide(SlideOrientation.Vertical)
                is PrayersScreen -> TransitionType.Slide()
                else -> TransitionType.Slide(isReversed = true)
            }

            when (currentTransitionType!!) {
                is TransitionType.Scale ->
                    scaleScreenTransition(lastEvent = navigator.lastEvent)

                is TransitionType.Slide -> {
                    slideScreenTransition(
                        lastEvent = navigator.lastEvent,
                        animationSpec = animationSpec,
                        transitionType = currentTransitionType as TransitionType.Slide
                    )
                }
            }
        }
    )
}

private fun slideScreenTransition(
    lastEvent: StackEvent,
    animationSpec: FiniteAnimationSpec<IntOffset>,
    transitionType: TransitionType.Slide
): ContentTransform {
    val (initialOffset, targetOffset) = when {
        (lastEvent == StackEvent.Pop && !transitionType.isReversed) ||
                (lastEvent != StackEvent.Pop && transitionType.isReversed) ->
            ({ size: Int -> size }) to ({ size: Int -> -size })

        else -> ({ size: Int -> -size }) to ({ size: Int -> size })
    }

    return when (transitionType.orientation) {
        SlideOrientation.Horizontal ->
            slideInHorizontally(animationSpec, initialOffset) togetherWith
                    slideOutHorizontally(animationSpec, targetOffset)

        SlideOrientation.Vertical ->
            slideInVertically(animationSpec, initialOffset) togetherWith
                    slideOutVertically(animationSpec, targetOffset)
    }
}

private fun scaleScreenTransition(
    lastEvent: StackEvent,
    animationSpec: FiniteAnimationSpec<Float> = spring(stiffness = Spring.StiffnessMediumLow),
    enterScales: Pair<Float, Float> = 1.1f to 0.95f
): ContentTransform {
    val (initialScale, targetScale) = when (lastEvent) {
        StackEvent.Pop -> enterScales.second to enterScales.first
        else -> enterScales
    }

    return scaleIn(initialScale = initialScale, animationSpec = animationSpec) togetherWith
            scaleOut(targetScale = targetScale, animationSpec = animationSpec)
}