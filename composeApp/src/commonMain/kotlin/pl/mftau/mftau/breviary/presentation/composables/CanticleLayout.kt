package pl.mftau.mftau.breviary.presentation.composables

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import pl.mftau.mftau.breviary.domain.model.Canticle

@Composable
internal fun CanticleLayout(canticle: Canticle) {
    Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
        BreviaryPartHeader(
            title = canticle.name,
            pages = canticle.breviaryPages,
            verses = canticle.verses
        )
        Text(
            text = canticle.antiphon1,
            fontSize = 15.sp,
        )
        Text(
            text = canticle.text,
            fontSize = 15.sp,
        )
        Text(
            text = canticle.antiphon2,
            fontSize = 15.sp,
        )
    }
}