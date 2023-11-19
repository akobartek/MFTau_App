package pl.mftau.mftau.songbook.presentation.components

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.sp

@Composable
fun SongText(text: String, fontSize: Int, modifier: Modifier = Modifier) {
    Text(
        text = text,
        fontSize = fontSize.sp,
        letterSpacing = 0.sp,
        lineHeight = (fontSize + 6).sp,
        modifier = modifier
    )
}