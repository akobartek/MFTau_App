package pl.mftau.mftau.readings.presentation

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.adaptive.ExperimentalMaterial3AdaptiveApi
import androidx.compose.material3.adaptive.layout.AnimatedPane
import androidx.compose.material3.adaptive.layout.ListDetailPaneScaffold
import androidx.compose.material3.adaptive.layout.ListDetailPaneScaffoldRole
import androidx.compose.material3.adaptive.navigation.rememberListDetailPaneScaffoldNavigator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import org.koin.compose.koinInject
import pl.mftau.mftau.common.utils.BackHandler
import pl.mftau.mftau.readings.domain.model.Reading

@Composable
fun ReadingsScreen(
    navigateUp: () -> Unit,
    viewModel: ReadingsViewModel = koinInject(),
) {
    val state by viewModel.state.collectAsStateWithLifecycle()
    val color = MaterialTheme.colorScheme.primary

    LaunchedEffect(Unit) {
        viewModel.init(color)
    }

    ReadingsScreenContent(
        navigateUp = navigateUp,
        state = state,
        tabSelected = viewModel::onTabSelected,
        onReadingSelected = viewModel::onReadingSelected,
    )
}

@OptIn(ExperimentalMaterial3AdaptiveApi::class)
@Composable
fun ReadingsScreenContent(
    navigateUp: () -> Unit,
    state: ReadingsScreenState,
    tabSelected: (Int) -> Unit,
    onReadingSelected: (Reading?) -> Unit,
) {
    val scaffoldNavigator = rememberListDetailPaneScaffoldNavigator()

    BackHandler {
        if (scaffoldNavigator.canNavigateBack())
            scaffoldNavigator.navigateBack()
        else
            navigateUp()
    }

    ListDetailPaneScaffold(
        directive = scaffoldNavigator.scaffoldDirective,
        value = scaffoldNavigator.scaffoldValue,
        listPane = {
            AnimatedPane(modifier = Modifier.preferredWidth(240.dp)) {
                ReadingsListPane(
                    navigateUp = navigateUp,
                    state = state,
                    tabSelected = tabSelected,
                    onReadingSelected = {
                        onReadingSelected(it)
                        scaffoldNavigator.navigateTo(ListDetailPaneScaffoldRole.Detail)
                    },
                )
            }
        },
        detailPane = {
            AnimatedPane(modifier = Modifier) {
                state.selectedReading?.let { reading ->
                    ReadingsText(
                        reading = reading,
                        onClose = {
                            scaffoldNavigator.navigateBack()
                            onReadingSelected(null)
                        }
                    )
                }
            }
        }
    )
}