package pl.mftau.mftau.readings.presentation

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.AnimatedContentTransitionScope.SlideDirection
import androidx.compose.animation.core.EaseIn
import androidx.compose.animation.core.EaseOut
import androidx.compose.animation.core.tween
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.HistoryEdu
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.PrimaryTabRow
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Tab
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import mftau.composeapp.generated.resources.Res
import mftau.composeapp.generated.resources.ic_pray
import mftau.composeapp.generated.resources.prayers
import mftau.composeapp.generated.resources.readings
import mftau.composeapp.generated.resources.writings
import org.jetbrains.compose.resources.stringResource
import org.jetbrains.compose.resources.vectorResource
import pl.mftau.mftau.common.presentation.composables.TauCenteredTopBar
import pl.mftau.mftau.readings.domain.model.Reading

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ReadingsListPane(
    navigateUp: () -> Unit,
    state: ReadingsScreenState,
    tabSelected: (Int) -> Unit,
    onReadingSelected: (Reading?) -> Unit,
) {
    Scaffold(
        topBar = {
            Column {
                TauCenteredTopBar(
                    title = stringResource(Res.string.readings),
                    onNavClick = navigateUp,
                    titleContent = {
                        PrimaryTabRow(
                            selectedTabIndex = state.selectedTab,
                            tabs = {
                                arrayOf(
                                    vectorResource(Res.drawable.ic_pray) to stringResource(Res.string.prayers),
                                    Icons.Outlined.HistoryEdu to stringResource(Res.string.writings),
                                ).forEachIndexed { index, (imageVector, text) ->
                                    Tab(
                                        selected = state.selectedTab == index,
                                        onClick = { tabSelected(index) },
                                        icon = {
                                            Icon(
                                                imageVector = imageVector,
                                                contentDescription = null,
                                                modifier = Modifier.size(24.dp)
                                            )
                                        },
                                        text = { Text(text = text) },
                                        unselectedContentColor = MaterialTheme.colorScheme.onSurfaceVariant,
                                    )
                                }
                            }
                        )
                    }
                )
            }
        }
    ) { paddingValues ->
        AnimatedContent(
            targetState = state.selectedTab,
            transitionSpec = {
                slideIntoContainer(
                    animationSpec = tween(300, easing = EaseIn),
                    towards = if (state.selectedTab == 0) SlideDirection.Right else SlideDirection.Left
                ).togetherWith(
                    slideOutOfContainer(
                        animationSpec = tween(300, easing = EaseOut),
                        towards = if (state.selectedTab == 0) SlideDirection.Right else SlideDirection.Left
                    )
                )
            },
            label = "reading",
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize()
        ) { targetState: Int ->
            ReadingsList(
                readings = if (targetState == 0) state.prayers else state.writings,
                onReadingSelected = onReadingSelected,
            )
        }
    }

}

@Composable
fun ReadingsList(
    readings: List<Reading>,
    onReadingSelected: (Reading) -> Unit,
) {
    LazyColumn(modifier = Modifier.fillMaxSize()) {
        items(items = readings, key = { it.id }) { reading ->
            Column(Modifier.clickable { onReadingSelected(reading) }) {
                Text(
                    text = reading.name,
                    style = MaterialTheme.typography.bodyLarge,
                    modifier = Modifier.padding(12.dp),
                )
                HorizontalDivider(
                    modifier = Modifier
                        .fillMaxWidth()
                        .width(1.dp),
                )
            }
        }
    }
}