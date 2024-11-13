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
import kotlinx.coroutines.launch
import org.jetbrains.compose.resources.getString
import pl.mftau.mftau.Screen.*
import pl.mftau.mftau.auth.presentation.AuthScreen
import pl.mftau.mftau.common.presentation.ObserveAsEvents
import pl.mftau.mftau.common.presentation.snackbars.SnackbarController
import pl.mftau.mftau.common.utils.navigateSafely
import pl.mftau.mftau.common.utils.navigateUpSafely
import pl.mftau.mftau.core.presentation.home.HomeScreen
import pl.mftau.mftau.core.presentation.settings.SettingsScreen

@Composable
fun App(startDestination: Screen = Home) {
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
            composable<Settings> {
                SettingsScreen(
                    navigateUp = { navController.navigateUpSafely(source = Settings) },
                )
            }
        }
    }
}