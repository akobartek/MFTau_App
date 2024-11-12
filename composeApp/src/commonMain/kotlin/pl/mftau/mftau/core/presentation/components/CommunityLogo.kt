package pl.mftau.mftau.core.presentation.components

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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import pl.mftau.mftau.R
import pl.mftau.mftau.ui.theme.ColorTheme
import pl.mftau.mftau.ui.theme.MFTauTheme
import pl.mftau.mftau.ui.theme.getMfTauColorPrimary
import pl.mftau.mftau.ui.theme.getMfTauColorSecondary
import pl.mftau.mftau.ui.theme.mfTauFont

@Preview(showBackground = true)
@Composable
fun CommunityLogoPreview() {
    MFTauTheme(dynamicColor = false, colorTheme = ColorTheme.LIGHT) {
        CommunityLogo()
    }
}

@Preview(showBackground = true)
@Composable
fun CommunityLogoDarkPreview() {
    MFTauTheme(dynamicColor = false, colorTheme = ColorTheme.DARK) {
        CommunityLogo()
    }
}

@Preview(showBackground = true)
@Composable
fun CommunityLogoDynamicPreview() {
    MFTauTheme(dynamicColor = true, colorTheme = ColorTheme.DARK) {
        CommunityLogo()
    }
}

@Composable
fun CommunityLogo(modifier: Modifier = Modifier) {
    Row(
        modifier = modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(modifier = Modifier.size(100.dp)) {
            Icon(
                painter = painterResource(id = R.drawable.logo_part1),
                tint = getMfTauColorSecondary(),
                contentDescription = stringResource(id = R.string.community_logo)
            )
            Icon(
                painter = painterResource(id = R.drawable.logo_part2),
                tint = getMfTauColorPrimary(),
                contentDescription = stringResource(id = R.string.community_logo)
            )
        }
        Spacer(Modifier.width(16.dp))
        Text(
            text = stringResource(id = R.string.community_name),
            color = getMfTauColorSecondary(),
            fontSize = 32.sp,
            fontFamily = mfTauFont
        )
    }
}