package pl.mftau.mftau.leaders.presentation.people

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
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import mftau.composeapp.generated.resources.Res
import mftau.composeapp.generated.resources.add_song
import mftau.composeapp.generated.resources.emaus
import mftau.composeapp.generated.resources.ic_draws
import mftau.composeapp.generated.resources.people
import org.jetbrains.compose.resources.stringResource
import org.jetbrains.compose.resources.vectorResource
import org.koin.compose.koinInject
import pl.mftau.mftau.common.presentation.composables.LoadingBox
import pl.mftau.mftau.common.presentation.composables.TauCenteredTopBar
import pl.mftau.mftau.leaders.domain.model.Person
import pl.mftau.mftau.leaders.presentation.people.components.PeopleEmptyListInfo
import pl.mftau.mftau.leaders.presentation.people.components.PersonCard
import pl.mftau.mftau.leaders.presentation.people.components.PersonEditorDialog

@Composable
fun LeadersPeopleScreen(
    navigateUp: () -> Unit,
    openEmauses: () -> Unit,
    viewModel: LeadersPeopleViewModel = koinInject(),
) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    LeadersPeopleScreenContent(
        navigateUp = navigateUp,
        openEmauses = openEmauses,
        state = state,
        togglePersonEditorVisibility = viewModel::togglePersonEditorVisibility,
        savePerson = viewModel::savePerson,
        deletePerson = viewModel::deletePerson,
    )
}

@Composable
fun LeadersPeopleScreenContent(
    navigateUp: () -> Unit,
    openEmauses: () -> Unit,
    state: LeadersPeopleScreenState,
    savePerson: (Person) -> Unit,
    deletePerson: (Person?) -> Unit,
    togglePersonEditorVisibility: (Person?) -> Unit,
) {
    Scaffold(
        topBar = {
            TauCenteredTopBar(
                title = stringResource(Res.string.people),
                onNavClick = navigateUp,
                actions = {
                    IconButton(onClick = openEmauses) {
                        Icon(
                            imageVector = vectorResource(Res.drawable.ic_draws),
                            contentDescription = stringResource(Res.string.emaus),
                            modifier = Modifier.size(24.dp),
                        )
                    }
                },
            )
        },
        floatingActionButton = {
            FloatingActionButton(onClick = { togglePersonEditorVisibility(null) }) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = stringResource(Res.string.add_song),
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
                    .fillMaxSize(),
            ) {
                items(
                    count = state.people.size,
                    key = { state.people[it].id },
                ) { index ->
                    val person = state.people[index]
                    PersonCard(
                        person = person,
                        onClick = { togglePersonEditorVisibility(person) },
                        additionalContent = {
                            if (person.notes.isNotBlank())
                                Text(
                                    text = person.notes,
                                    fontStyle = FontStyle.Italic,
                                    style = MaterialTheme.typography.bodySmall
                                )
                        },
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
            onSave = savePerson,
            onDelete = deletePerson,
            onDismiss = { togglePersonEditorVisibility(null) },
        )
}