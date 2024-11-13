package pl.mftau.mftau.common.presentation.composables

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import mftau.composeapp.generated.resources.Res
import mftau.composeapp.generated.resources.community_logo
import mftau.composeapp.generated.resources.community_name
import mftau.composeapp.generated.resources.logo_part1
import mftau.composeapp.generated.resources.logo_part2
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource
import pl.mftau.mftau.ui.theme.getMfTauColorPrimary
import pl.mftau.mftau.ui.theme.getMfTauColorSecondary
import pl.mftau.mftau.ui.theme.mfTauFont

@Composable
fun CommunityLogo(modifier: Modifier = Modifier) {
    Row(
        modifier = modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(modifier = Modifier.size(100.dp)) {
            Icon(
                painter = painterResource(Res.drawable.logo_part1),
                tint = getMfTauColorSecondary(),
                contentDescription = stringResource(Res.string.community_logo),
            )
            Icon(
                painter = painterResource(Res.drawable.logo_part2),
                tint = getMfTauColorPrimary(),
                contentDescription = null,
            )
        }
        Spacer(Modifier.width(16.dp))
        Text(
            text = stringResource(Res.string.community_name),
            color = getMfTauColorSecondary(),
            fontSize = 32.sp,
            fontFamily = mfTauFont(),
        )
    }
}