package pl.mftau.mftau.ui.theme

import androidx.compose.runtime.Composable
import androidx.compose.ui.text.font.FontFamily
import mftau.composeapp.generated.resources.Res
import mftau.composeapp.generated.resources.mffont
import org.jetbrains.compose.resources.Font

@Composable
fun mfTauFont() = FontFamily(Font(Res.font.mffont))