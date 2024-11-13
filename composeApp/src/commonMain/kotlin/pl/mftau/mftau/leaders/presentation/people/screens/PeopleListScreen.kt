package pl.mftau.mftau.leaders.presentation.people.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import cafe.adriel.voyager.core.screen.ScreenKey
import cafe.adriel.voyager.koin.getScreenModel
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import kotlinx.coroutines.launch
import pl.mftau.mftau.R
import pl.mftau.mftau.common.presentation.composables.LoadingBox
import pl.mftau.mftau.common.presentation.composables.TauCenteredTopBar
import pl.mftau.mftau.common.utils.safePop
import pl.mftau.mftau.common.utils.safePush
import pl.mftau.mftau.leaders.presentation.LeadersScreen
import pl.mftau.mftau.leaders.presentation.people.components.PeopleEmptyListInfo
import pl.mftau.mftau.leaders.presentation.people.components.PersonCard
import pl.mftau.mftau.leaders.presentation.people.components.PersonEditorDialog
import pl.mftau.mftau.leaders.presentation.people.screenmodels.PeopleListScreenModel

class PeopleListScreen : LeadersScreen() {
    override val key: ScreenKey
        get() = KEY

    @Composable
    override fun Content() {
        PeopleListScreenContent(getScreenModel())
    }

    companion object {
        const val KEY = "PeopleListScreen"
    }
}

@Composable
fun PeopleListScreenContent(screenModel: PeopleListScreenModel) {
    val navigator = LocalNavigator.currentOrThrow
    val state by screenModel.state.collectAsStateWithLifecycle()

    val context = LocalContext.current
    val scope = rememberCoroutineScope()
    val snackbarHostState = remember { SnackbarHostState() }
    LaunchedEffect(state.personSavedSuccessfully) {
        val savedSuccessfully = state.personSavedSuccessfully
        scope.launch {
            if (savedSuccessfully != null) {
                screenModel.togglePersonSavedVisibility()
                snackbarHostState.showSnackbar(
                    message = context.getString(
                        if (savedSuccessfully) R.string.person_saved
                        else R.string.person_save_error
                    ),
                    withDismissAction = true
                )
            }
        }
    }
    LaunchedEffect(state.personDeletedSuccessfully) {
        val deletedSuccessfully = state.personDeletedSuccessfully
        scope.launch {
            if (deletedSuccessfully != null) {
                screenModel.togglePersonDeletedVisibility()
                snackbarHostState.showSnackbar(
                    message = context.getString(
                        if (deletedSuccessfully) R.string.person_delete_success
                        else R.string.person_delete_error
                    ),
                    withDismissAction = true
                )
            }
        }
    }

    Scaffold(
        snackbarHost = {
            SnackbarHost(hostState = snackbarHostState)
        },
        topBar = {
            TauCenteredTopBar(
                title = stringResource(R.string.people),
                onNavClick = { navigator.safePop(PeopleListScreen.KEY) },
                actions = {
                    IconButton(onClick = { navigator.safePush(EmausScreen()) }) {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_draws),
                            contentDescription = stringResource(id = R.string.emaus),
                            modifier = Modifier.size(24.dp)
                        )
                    }
                }
            )
        },
        floatingActionButton = {
            FloatingActionButton(onClick = screenModel::togglePersonEditorVisibility) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = stringResource(id = R.string.add_song)
                )
            }
        }
    ) { paddingValues ->
        if (!state.isLoading) {
            LazyVerticalGrid(
                columns = GridCells.Adaptive(150.dp),
                verticalArrangement = Arrangement.spacedBy(4.dp),
                horizontalArrangement = Arrangement.spacedBy(4.dp),
                modifier = Modifier
                    .padding(paddingValues)
                    .fillMaxSize()
            ) {
                items(
                    count = state.people.size,
                    key = { state.people[it].id }
                ) { index ->
                    val person = state.people[index]
                    PersonCard(
                        person = person,
                        onClick = { screenModel.togglePersonEditorVisibility(person) },
                        additionalContent = {
                            if (person.notes.isNotBlank())
                                Text(
                                    text = person.notes,
                                    fontStyle = FontStyle.Italic,
                                    style = MaterialTheme.typography.bodySmall
                                )
                        }
                    )
                }
            }

            if (state.people.isEmpty())
                PeopleEmptyListInfo()
        } else LoadingBox()
    }

    if (state.personEditorVisible)
        PersonEditorDialog(
            person = state.personToEdit,
            onSave = screenModel::savePerson,
            onDelete = screenModel::deletePerson,
            onDismiss = screenModel::togglePersonEditorVisibility
        )
}