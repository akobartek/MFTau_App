package pl.mftau.mftau.leaders.presentation.meetings.screens

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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import cafe.adriel.voyager.core.screen.ScreenKey
import cafe.adriel.voyager.koin.getScreenModel
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import pl.mftau.mftau.R
import pl.mftau.mftau.common.presentation.components.LoadingBox
import pl.mftau.mftau.common.presentation.components.TauCenteredTopBar
import pl.mftau.mftau.common.utils.safePop
import pl.mftau.mftau.leaders.domain.model.MeetingType
import pl.mftau.mftau.leaders.presentation.LeadersScreen
import pl.mftau.mftau.leaders.presentation.meetings.components.PresenceCard
import pl.mftau.mftau.leaders.presentation.meetings.components.PresenceDetailsDialog
import pl.mftau.mftau.leaders.presentation.meetings.screenmodels.PresentListScreenModel

class PresenceListScreen : LeadersScreen() {
    override val key: ScreenKey
        get() = KEY

    @Composable
    override fun Content() {
        PresenceListContent(getScreenModel())
    }

    companion object {
        const val KEY = "PresenceListScreen"
    }
}

@Composable
fun PresenceListContent(screenModel: PresentListScreenModel) {
    val navigator = LocalNavigator.currentOrThrow
    val state by screenModel.state.collectAsStateWithLifecycle()

    Scaffold(
        topBar = {
            TauCenteredTopBar(
                title = stringResource(R.string.presence),
                onNavClick = { navigator.safePop(PresenceListScreen.KEY) },
                actions = {
                    IconButton(onClick = screenModel::toggleShowJustified) {
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
                    .fillMaxSize()
            ) {
                item(key = "HEADER", span = { GridItemSpan(this.maxLineSpan) }) {
                    Row(
                        horizontalArrangement = Arrangement.Center,
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(bottom = 8.dp)
                    ) {
                        val types =
                            listOf(MeetingType.FORMATION, MeetingType.PRAYERFUL, MeetingType.OTHER)
                        types.forEach { type ->
                            Spacer(
                                modifier = Modifier
                                    .size(8.dp)
                                    .background(type.getProperColors().first)
                            )
                            Text(
                                text = stringResource(id = type.getNameResourceId()),
                                style = MaterialTheme.typography.bodySmall,
                                modifier = Modifier.padding(horizontal = 6.dp)
                            )
                        }
                    }
                }
                items(
                    count = state.presence.size,
                    key = { state.presence[it].personId }
                ) { index ->
                    val personPresence = state.presence[index]
                    PresenceCard(
                        presence = personPresence,
                        showJustified = state.showJustified,
                        onClick = screenModel::toggleDetailsVisibility
                    )
                }
            }
        } else LoadingBox()
    }

    state.detailsPersonSelected?.let { personSelected ->
        PresenceDetailsDialog(
            personPresence = personSelected,
            meetingsMap = state.meetings,
            onDismiss = { screenModel.toggleDetailsVisibility(null) }
        )
    }
}