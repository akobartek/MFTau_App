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
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.HistoryEdu
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.PrimaryTabRow
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Tab
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import pl.mftau.mftau.R
import pl.mftau.mftau.core.presentation.components.TauTopBar

class ReadingsListScreen : Screen {
    @Composable
    override fun Content() {
        ReadingsListScreenContent()
    }
}

@Composable
fun ReadingsListScreenContent() {
    val navigator = LocalNavigator.currentOrThrow
    var selectedTab by remember { mutableIntStateOf(0) }

    Scaffold(
        topBar = {
            Column {
                TauTopBar(
                    title = stringResource(id = R.string.readings),
                    onNavClick = navigator::pop,
                )
                PrimaryTabRow(
                    selectedTabIndex = selectedTab,
                    tabs = {
                        Tab(
                            selected = selectedTab == 0,
                            onClick = { selectedTab = 0 },
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
                            selected = selectedTab == 1,
                            onClick = { selectedTab = 1 },
                            icon = {
                                Icon(
                                    imageVector = Icons.Outlined.HistoryEdu,
                                    contentDescription = null
                                )
                            },
                            text = { Text(text = stringResource(id = R.string.readings)) },
                            unselectedContentColor = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    }
                )
            }
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
        ) {
            AnimatedContent(
                targetState = selectedTab,
                transitionSpec = {
                    slideIntoContainer(
                        animationSpec = tween(200, easing = EaseIn),
                        towards = if (selectedTab == 0) SlideDirection.Right else SlideDirection.Left
                    ).togetherWith(
                        slideOutOfContainer(
                            animationSpec = tween(200, easing = EaseOut),
                            towards = if (selectedTab == 0) SlideDirection.Right else SlideDirection.Left
                        )
                    )
                },
                label = "reading",
                modifier = Modifier.fillMaxSize()
            ) { targetState ->
                Text(text = targetState.toString(), fontSize = 60.sp)
            }
        }
    }
}