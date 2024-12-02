package pl.mftau.mftau.common.presentation.composables

import androidx.compose.foundation.layout.RowScope
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.style.TextAlign
import mftau.composeapp.generated.resources.Res
import mftau.composeapp.generated.resources.cd_navigate_up
import org.jetbrains.compose.resources.stringResource
import pl.mftau.mftau.ui.theme.mfTauFont

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TauCenteredTopBar(
    title: String,
    titleContent: (@Composable () -> Unit)? = null,
    onNavClick: () -> Unit,
    navIcon: ImageVector = Icons.AutoMirrored.Filled.ArrowBack,
    actions: @Composable RowScope.() -> Unit = {}
) {
    CenterAlignedTopAppBar(
        title = {
            titleContent?.invoke()
                ?: Text(
                    text = title,
                    fontFamily = mfTauFont(),
                    textAlign = TextAlign.Center,
                    style = MaterialTheme.typography.titleLarge,
                    maxLines = 2,
                )
        },
        navigationIcon = {
            IconButton(onClick = onNavClick) {
                Icon(
                    imageVector = navIcon,
                    contentDescription = stringResource(Res.string.cd_navigate_up),
                )
            }
        },
        actions = actions,
    )
}