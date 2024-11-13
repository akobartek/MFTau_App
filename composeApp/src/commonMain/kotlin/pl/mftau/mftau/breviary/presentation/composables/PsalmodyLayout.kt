package pl.mftau.mftau.breviary.presentation.composables

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp
import pl.mftau.mftau.breviary.domain.model.Psalmody

@Composable
internal fun PsalmodyLayout(psalmody: Psalmody) {
    Column {
        BreviaryPartHeader(title = "Psalmodia", pages = psalmody.breviaryPages)
        Column(verticalArrangement = Arrangement.spacedBy(20.dp)) {
            psalmody.psalms.forEach { PsalmLayout(psalm = it) }
        }
    }
}