package pl.mftau.mftau.common.utils

import androidx.navigation.NavHostController
import pl.mftau.mftau.Screen

fun NavHostController.navigateSafely(route: Screen, screenToClean: Screen? = null) =
    navigate(route) {
        launchSingleTop = true
        restoreState = true

        screenToClean?.let { currentScreen ->
            popUpTo(currentScreen) {
                inclusive = true
            }
        }
    }

fun NavHostController.navigateUpSafely(source: Screen) =
    if (currentDestination?.route?.contains(source::class.simpleName ?: "") == true) navigateUp()
    else false