package pl.mftau.mftau.readings.presentation

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cafe.adriel.voyager.navigator.LocalNavigator
import pl.mftau.mftau.readings.data.Prayers

@Composable
fun ReadingsListLayout(names: Array<String>, texts: Array<String>) {
    val navigator = LocalNavigator.current
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
    ) {
        names.forEachIndexed { index, elem ->
            Column(
                Modifier.clickable {
                    navigator?.push(ReadingsTextScreen(texts[index], elem))
                }
            ) {
                Text(
                    text = elem,
                    fontSize = 16.sp,
                    modifier = Modifier.padding(12.dp)
                )
                HorizontalDivider(
                    modifier = Modifier
                        .fillMaxWidth()
                        .width(1.dp)
                )
            }
        }
    }
}