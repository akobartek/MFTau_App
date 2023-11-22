package pl.mftau.mftau.songbook.presentation.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import cafe.adriel.voyager.core.screen.ScreenKey
import cafe.adriel.voyager.koin.getScreenModel
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import pl.mftau.mftau.R
import pl.mftau.mftau.core.presentation.components.LoadingBox
import pl.mftau.mftau.core.presentation.components.TauCenteredTopBar
import pl.mftau.mftau.core.utils.safePop
import pl.mftau.mftau.songbook.presentation.components.SongBookEmptyListInfo
import pl.mftau.mftau.songbook.presentation.components.SongCard
import pl.mftau.mftau.songbook.presentation.screenmodels.UserSongsListScreenModel

class AddedSongsListScreen : SongBookScreen() {
    override val key: ScreenKey
        get() = KEY

    @Composable
    override fun Content() {
        AddedSongsListScreenContent(getScreenModel())
    }

    companion object {
        const val KEY = "AddedSongsListScreen"
    }
}

@Composable
fun AddedSongsListScreenContent(screenModel: UserSongsListScreenModel) {
    val navigator = LocalNavigator.currentOrThrow
    val state by screenModel.state.collectAsStateWithLifecycle()

    Scaffold(
        topBar = {
            TauCenteredTopBar(
                title = stringResource(R.string.my_songs),
                onNavClick = { navigator.safePop(AddedSongsListScreen.KEY) }
            )
        },
        floatingActionButton = {
            FloatingActionButton(onClick = {
                //TODO
            }) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = stringResource(id = R.string.add_song)
                )
            }
        }
    ) { paddingValues ->
        if (state.songs != null) {
            LazyColumn(
                verticalArrangement = Arrangement.spacedBy(4.dp),
                modifier = Modifier
                    .padding(paddingValues)
                    .fillMaxSize()
            ) {
                items(state.songs ?: listOf(), key = { it.title }) { song ->
                    SongCard(
                        song = song,
                        onClick = {
                            // TODO
                        }
                    )
                }
            }

            if (state.songs?.isEmpty() == true)
                SongBookEmptyListInfo(messageId = R.string.empty_user_songs_list)
        } else LoadingBox()
    }
}