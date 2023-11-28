package pl.mftau.mftau.breviary.presentation.components

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import pl.mftau.mftau.breviary.domain.model.Hymn

@Composable
internal fun HymnLayout(hymn: Hymn) {
    if (hymn.version2 != null) {
        var optionSelected by rememberSaveable { mutableIntStateOf(0) }
        val options = listOf("Wariant I", "Wariant II")

        BreviaryPartWithSelectionLayout(
            title = "Hymn",
            breviaryPages = hymn.breviaryPages,
            verses = hymn.verses,
            optionSelected = optionSelected,
            options = options,
            texts = listOf(hymn.text, hymn.version2),
            onOptionSelected = { optionSelected = it }
        )
    } else {
        BreviaryPartLayout(title = "Hymn", breviaryPart = hymn.toBreviaryPart())
    }
}