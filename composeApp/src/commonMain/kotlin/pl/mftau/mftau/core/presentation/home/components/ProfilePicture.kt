package pl.mftau.mftau.core.presentation.home.components

import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.AccountCircle
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import mftau.composeapp.generated.resources.Res
import mftau.composeapp.generated.resources.cd_profile_pic
import org.jetbrains.compose.resources.stringResource

@Composable
fun ProfilePicture(photoUrl: String?) {
    val placeholder = rememberVectorPainter(Icons.Outlined.AccountCircle)
    if (photoUrl.isNullOrBlank())
        Icon(
            painter = placeholder,
            tint = MaterialTheme.colorScheme.onSurfaceVariant,
            contentDescription = stringResource(Res.string.cd_profile_pic),
        )
    else
        AsyncImage(
            model = photoUrl,
            contentDescription = stringResource(Res.string.cd_profile_pic),
            contentScale = ContentScale.Crop,
            error = placeholder,
            placeholder = placeholder,
            modifier = Modifier
                .size(36.dp)
                .clip(CircleShape),
        )
}