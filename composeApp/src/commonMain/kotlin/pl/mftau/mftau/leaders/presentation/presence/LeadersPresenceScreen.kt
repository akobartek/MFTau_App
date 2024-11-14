package pl.mftau.mftau.leaders.presentation.presence

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Balance
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import mftau.composeapp.generated.resources.Res
import mftau.composeapp.generated.resources.presence
import org.jetbrains.compose.resources.stringResource
import org.koin.compose.koinInject
import pl.mftau.mftau.common.presentation.composables.LoadingBox
import pl.mftau.mftau.common.presentation.composables.TauCenteredTopBar
import pl.mftau.mftau.leaders.domain.model.MeetingType
import pl.mftau.mftau.leaders.domain.model.PersonPresence
import pl.mftau.mftau.leaders.presentation.presence.composables.PresenceCard
import pl.mftau.mftau.leaders.presentation.presence.composables.PresenceDetailsDialog

@Composable
fun LeadersPresenceScreen(
    navigateUp: () -> Unit,
    viewModel: LeadersPresenceViewModel = koinInject(),
) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    LeadersPresenceScreenContent(
        navigateUp = navigateUp,
        state = state,
        toggleDetailsVisibility = viewModel::toggleDetailsVisibility,
        toggleShowJustified = viewModel::toggleShowJustified,
    )
}

@Composable
fun LeadersPresenceScreenContent(
    navigateUp: () -> Unit,
    state: LeadersPresenceScreenState,
    toggleDetailsVisibility: (PersonPresence?) -> Unit,
    toggleShowJustified: (Boolean) -> Unit,
) {
    Scaffold(
        topBar = {
            TauCenteredTopBar(
                title = stringResource(Res.string.presence),
                onNavClick = navigateUp,
                actions = {
                    IconButton(onClick = { toggleShowJustified(state.showJustified) }) {
                        Icon(imageVector = Icons.Filled.Balance, contentDescription = null)
                    }
                }
            )
        }
    ) { paddingValues ->
        if (!state.isLoading) {
            LazyVerticalGrid(
                columns = GridCells.Adaptive(200.dp),
                verticalArrangement = Arrangement.spacedBy(4.dp),
                horizontalArrangement = Arrangement.spacedBy(
                    4.dp,
                    Alignment.CenterHorizontally
                ),
                modifier = Modifier
                    .padding(paddingValues)
                    .fillMaxSize(),
            ) {
                item(key = "HEADER", span = { GridItemSpan(this.maxLineSpan) }) {
                    Row(
                        horizontalArrangement = Arrangement.Center,
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(bottom = 8.dp),
                    ) {
                        val types =
                            listOf(MeetingType.FORMATION, MeetingType.PRAYERFUL, MeetingType.OTHER)
                        types.forEach { type ->
                            Spacer(
                                modifier = Modifier
                                    .size(8.dp)
                                    .background(type.getProperColors().first),
                            )
                            Text(
                                text = stringResource(type.getNameResourceId()),
                                style = MaterialTheme.typography.bodySmall,
                                modifier = Modifier.padding(horizontal = 6.dp),
                            )
                        }
                    }
                }
                items(
                    count = state.presence.size,
                    key = { state.presence[it].personId },
                ) { index ->
                    val personPresence = state.presence[index]
                    PresenceCard(
                        presence = personPresence,
                        showJustified = state.showJustified,
                        onClick = toggleDetailsVisibility,
                    )
                }
            }
        } else LoadingBox()
    }

    state.detailsPersonSelected?.let { personSelected ->
        PresenceDetailsDialog(
            personPresence = personSelected,
            meetingsMap = state.meetings,
            onDismiss = { toggleDetailsVisibility(null) },
        )
    }
}