package pl.mftau.mftau.leaders.presentation.screens

import androidx.compose.runtime.Composable
import cafe.adriel.voyager.core.screen.ScreenKey
import cafe.adriel.voyager.koin.getScreenModel
import pl.mftau.mftau.leaders.presentation.screenmodels.EmausesScreenModel

class EmausesScreen: LeadersScreen() {
    override val key: ScreenKey
        get() = KEY

    @Composable
    override fun Content() {
        EmausesScreenContent(getScreenModel())
    }

    companion object {
        const val KEY = "EmausesScreen"
    }
}

@Composable
fun EmausesScreenContent(screenModel: EmausesScreenModel) {

}