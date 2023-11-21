package pl.mftau.mftau.readings.presentation

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.AnimatedContentTransitionScope.SlideDirection
import androidx.compose.animation.core.EaseIn
import androidx.compose.animation.core.EaseOut
import androidx.compose.animation.core.tween
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.HistoryEdu
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.PrimaryTabRow
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Tab
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import cafe.adriel.voyager.koin.getScreenModel
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import pl.mftau.mftau.R
import pl.mftau.mftau.core.presentation.components.TauCenteredTopBar

class ReadingsListScreen : ReadingsScreen() {
    @Composable
    override fun Content() {
        ReadingsListScreenContent(getScreenModel())
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ReadingsListScreenContent(screenModel: ReadingsListScreenModel) {
    val navigator = LocalNavigator.currentOrThrow
    val state by screenModel.state.collectAsStateWithLifecycle()

    Scaffold(
        topBar = {
            Column {
                TauCenteredTopBar(
                    title = stringResource(id = R.string.readings),
                    onNavClick = navigator::pop,
                )
                PrimaryTabRow(
                    selectedTabIndex = state.selectedTab,
                    tabs = {
                        Tab(
                            selected = state.selectedTab == 0,
                            onClick = { screenModel.updateSelection(0) },
                            icon = {
                                Icon(
                                    imageVector = ImageVector.vectorResource(id = R.drawable.ic_pray),
                                    contentDescription = null,
                                    modifier = Modifier.size(24.dp)
                                )
                            },
                            text = { Text(text = stringResource(id = R.string.prayers)) },
                            unselectedContentColor = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                        Tab(
                            selected = state.selectedTab == 1,
                            onClick = { screenModel.updateSelection(1) },
                            icon = {
                                Icon(
                                    imageVector = Icons.Outlined.HistoryEdu,
                                    contentDescription = null
                                )
                            },
                            text = { Text(text = stringResource(id = R.string.writings)) },
                            unselectedContentColor = MaterialTheme.colorScheme.onSurfaceVariant
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
        ) { targetState ->
            ReadingsListLayout(readings = if (targetState == 0) state.prayers else state.writings)
        }
    }
}