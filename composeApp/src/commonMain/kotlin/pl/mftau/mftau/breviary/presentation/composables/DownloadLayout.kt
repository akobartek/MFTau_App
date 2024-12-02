package pl.mftau.mftau.breviary.presentation.composables

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import mftau.composeapp.generated.resources.Res
import mftau.composeapp.generated.resources.breviary_list
import mftau.composeapp.generated.resources.save_in_memory
import org.jetbrains.compose.resources.stringArrayResource
import org.jetbrains.compose.resources.stringResource
import pl.mftau.mftau.breviary.domain.model.BreviaryDay
import pl.mftau.mftau.common.presentation.composables.HeightSpacer
import pl.mftau.mftau.common.utils.BackHandler

@Composable
fun DownloadLayout(breviaryDay: BreviaryDay, onBackPressed: () -> Unit) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 32.dp)
            .verticalScroll(rememberScrollState()),
    ) {
        stringArrayResource(Res.array.breviary_list).forEachIndexed { index, name ->
            DownloadItem(name, breviaryDay.getValueByIndex(index))
        }
        DownloadItem(
            stringResource(Res.string.save_in_memory),
            if (breviaryDay.id == 0L) "" else breviaryDay.id.toString()
        )
        HeightSpacer(height = 24.dp)
    }

    BackHandler(onBack = onBackPressed)
}