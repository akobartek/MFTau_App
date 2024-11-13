package pl.mftau.mftau.breviary.presentation.composables

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.core.EaseIn
import androidx.compose.animation.core.EaseOut
import androidx.compose.animation.core.tween
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Done
import androidx.compose.material3.Icon
import androidx.compose.material3.MultiChoiceSegmentedButtonRow
import androidx.compose.material3.SegmentedButton
import androidx.compose.material3.SegmentedButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import pl.mftau.mftau.common.presentation.composables.HeightSpacer

@Composable
fun BreviaryPartWithSelectionLayout(
    title: String,
    breviaryPages: String,
    verses: String,
    optionSelected: Int,
    options: List<String>,
    texts: List<AnnotatedString>,
    onOptionSelected: (Int) -> Unit
) {
    Column {
        BreviaryPartHeader(
            title = title,
            pages = breviaryPages,
            verses = verses
        )
        MultiChoiceSegmentedButtonRow(modifier = Modifier.fillMaxWidth()) {
            options.forEachIndexed { index, option ->
                SegmentedButton(
                    shape = SegmentedButtonDefaults.itemShape(
                        index = index,
                        count = options.size
                    ),
                    icon = {
                        SegmentedButtonDefaults.Icon(
                            active = optionSelected == index,
                            activeContent = {
                                Icon(
                                    imageVector = Icons.Filled.Done,
                                    contentDescription = null,
                                    modifier = Modifier.size(SegmentedButtonDefaults.IconSize)
                                )
                            },
                            inactiveContent = null
                        )
                    },
                    onCheckedChange = { onOptionSelected(index) },
                    checked = optionSelected == index
                ) {
                    Text(option)
                }
            }
        }
        HeightSpacer(8.dp)
        AnimatedContent(
            targetState = optionSelected,
            transitionSpec = {
                slideIntoContainer(
                    animationSpec = tween(400, easing = EaseIn),
                    towards = AnimatedContentTransitionScope.SlideDirection.Down
                ).togetherWith(
                    slideOutOfContainer(
                        animationSpec = tween(400, easing = EaseOut),
                        towards = AnimatedContentTransitionScope.SlideDirection.Down
                    )
                )
            },
            label = "animation-$title"
        ) { targetState ->
            Text(text = texts[targetState], fontSize = 15.sp)
        }
    }
}