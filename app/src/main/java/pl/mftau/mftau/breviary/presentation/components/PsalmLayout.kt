package pl.mftau.mftau.breviary.presentation.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import pl.mftau.mftau.breviary.domain.model.Psalm

@Composable
internal fun PsalmLayout(psalm: Psalm, isInvitatoryPsalm: Boolean = false) {
    Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
        if (!isInvitatoryPsalm)
            Text(
                text = psalm.antiphon1,
                fontSize = 15.sp,
            )
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.fillMaxWidth()
        ) {
            if (psalm.name != null)
                Text(
                    text = "${psalm.name}${if (psalm.title != null) "\n${psalm.title}" else ""}",
                    color = MaterialTheme.colorScheme.primary,
                    textAlign = TextAlign.Center,
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp,
                    modifier = Modifier.fillMaxWidth()
                )
            if (psalm.subtitle != null)
                Text(
                    text = psalm.subtitle!!,
                    textAlign = TextAlign.Center,
                    fontStyle = FontStyle.Italic,
                    fontSize = 13.sp,
                    modifier = Modifier.fillMaxWidth(0.9f)
                )
        }
        if (psalm.breviaryPages != null)
            Text(
                text = psalm.breviaryPages!!,
                fontSize = 10.sp
            )
        if (psalm.part != null)
            Text(
                text = psalm.part!!,
                color = MaterialTheme.colorScheme.primary,
                textAlign = TextAlign.Center,
                fontWeight = FontWeight.Bold,
                fontSize = 15.sp,
                modifier = Modifier.fillMaxWidth()
            )
        Text(
            text = psalm.text,
            fontSize = 15.sp,
        )
        if (!isInvitatoryPsalm)
            Text(
                text = psalm.antiphon2,
                fontSize = 15.sp,
            )
    }
}