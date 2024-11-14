package pl.mftau.mftau

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.SnackbarResult
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import kotlinx.coroutines.launch
import org.jetbrains.compose.resources.getString
import pl.mftau.mftau.Screen.*
import pl.mftau.mftau.auth.presentation.AuthScreen
import pl.mftau.mftau.breviary.presentation.BreviarySaveScreen
import pl.mftau.mftau.breviary.presentation.BreviarySelectScreen
import pl.mftau.mftau.breviary.presentation.BreviaryTextScreen
import pl.mftau.mftau.common.presentation.ObserveAsEvents
import pl.mftau.mftau.common.presentation.snackbars.SnackbarController
import pl.mftau.mftau.common.utils.navigateSafely
import pl.mftau.mftau.common.utils.navigateUpSafely
import pl.mftau.mftau.core.presentation.home.HomeScreen
import pl.mftau.mftau.core.presentation.settings.SettingsScreen
import pl.mftau.mftau.leaders.presentation.emaus.LeadersEmausScreen
import pl.mftau.mftau.leaders.presentation.meetings.LeadersMeetingsScreen
import pl.mftau.mftau.leaders.presentation.people.LeadersPeopleScreen
import pl.mftau.mftau.leaders.presentation.presence.LeadersPresenceScreen
import pl.mftau.mftau.readings.presentation.ReadingsScreen
import pl.mftau.mftau.songbook.presentation.playlists.PlaylistDetailsScreen
import pl.mftau.mftau.songbook.presentation.playlists.PlaylistsScreen
import pl.mftau.mftau.songbook.presentation.songs.UserSongScreen
import pl.mftau.mftau.ui.theme.MFTauTheme

@Composable
fun App(startDestination: Screen = Home) {
    MFTauTheme {
        val navController = rememberNavController()
        val snackbarHostState = remember { SnackbarHostState() }
        val scope = rememberCoroutineScope()

        ObserveAsEvents(
            flow = SnackbarController.events,
            key1 = snackbarHostState,
        ) { event ->
            scope.launch {
                snackbarHostState.currentSnackbarData?.dismiss()

                val result = snackbarHostState.showSnackbar(
                    message = getString(event.message),
                    actionLabel = event.action?.let { getString(it.name) },
                    withDismissAction = event.action == null,
                    duration = SnackbarDuration.Short,
                )

                if (result == SnackbarResult.ActionPerformed) {
                    event.action?.action?.invoke()
                }
            }
        }

        Scaffold(
            snackbarHost = {
                SnackbarHost(hostState = snackbarHostState)
            },
        ) {
            NavHost(
                navController = navController,
                startDestination = startDestination,
                modifier = Modifier
                    .background(MaterialTheme.colorScheme.surface)
                    .windowInsetsPadding(WindowInsets.safeDrawing)
            ) {
                composable<Home> {
                    HomeScreen(
                        navigate = { screen -> navController.navigateSafely(route = screen) },
                    )
                }
                composable<Auth> {
                    AuthScreen(
                        navigateUp = { navController.navigateUpSafely(source = Auth) },
                    )
                }
                composable<Gospel> {
                    // TODO
                }
                composable<SongBook> {
                    // TODO
                }
                composable<Playlists> {
                    PlaylistsScreen(
                        navigateUp = { navController.navigateUpSafely(source = Playlists) },
                        openDetails = { playlistId, importCode ->
                            navController.navigateSafely(
                                PlaylistDetails(
                                    playlistId = playlistId,
                                    importCode = importCode,
                                )
                            )
                        },
                    )
                }
                composable<PlaylistDetails> {
                    val screen = it.toRoute<PlaylistDetails>()
                    PlaylistDetailsScreen(
                        navigateUp = { navController.navigateUpSafely(source = screen) },
                        playlistId = screen.playlistId,
                        importCode = screen.importCode,
                    )
                }
                composable<UserSongs> {
                    UserSongScreen(
                        navigateUp = { navController.navigateUpSafely(source = UserSongs) },
                    )
                }
                composable<Settings> {
                    SettingsScreen(
                        navigateUp = { navController.navigateUpSafely(source = Settings) },
                    )
                }
                composable<BreviarySelect> {
                    BreviarySelectScreen(
                        navigateUp = { navController.navigateUpSafely(source = BreviarySelect) },
                        navigate = { screen -> navController.navigateSafely(route = screen) },
                    )
                }
                composable<BreviaryText> {
                    val screen = it.toRoute<BreviaryText>()
                    BreviaryTextScreen(
                        navigateUp = { navController.navigateUpSafely(source = screen) },
                        position = screen.position,
                        date = screen.date,
                    )
                }
                composable<BreviarySave> {
                    val screen = it.toRoute<BreviarySave>()
                    BreviarySaveScreen(
                        navigateUp = { navController.navigateUpSafely(source = screen) },
                        date = screen.date,
                    )
                }
                composable<Readings> {
                    ReadingsScreen(
                        navigateUp = { navController.navigateUpSafely(source = Readings) },
                    )
                }
                composable<LeadersPeople> {
                    LeadersPeopleScreen(
                        navigateUp = { navController.navigateUpSafely(source = LeadersPeople) },
                        openEmauses = { navController.navigateSafely(LeadersEmaus) },
                    )
                }
                composable<LeadersEmaus> {
                    LeadersEmausScreen(
                        navigateUp = { navController.navigateUpSafely(source = LeadersEmaus) },
                    )
                }
                composable<LeadersMeetings> {
                    LeadersMeetingsScreen(
                        navigateUp = { navController.navigateUpSafely(source = LeadersMeetings) },
                        openPresenceScreen = { navController.navigateSafely(LeadersPresence) },
                    )
                }
                composable<LeadersPresence> {
                    LeadersPresenceScreen(
                        navigateUp = { navController.navigateUpSafely(source = LeadersPresence) },
                    )
                }
            }
        }
    }
}