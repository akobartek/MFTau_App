package pl.mftau.mftau.leaders.presentation.meetings.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import pl.mftau.mftau.common.utils.getFormattedDate
import pl.mftau.mftau.leaders.domain.model.Meeting

@Composable
fun MeetingCard(
    modifier: Modifier = Modifier,
    meeting: Meeting,
    onClick: (Meeting) -> Unit = {},
    background: Color? = null,
) {
    Card(
        colors = CardDefaults.cardColors(
            containerColor = background ?: MaterialTheme.colorScheme.secondaryContainer
        ),
        onClick = { onClick(meeting) },
        modifier = modifier.fillMaxWidth(),
    ) {
        Column(
            verticalArrangement = Arrangement.spacedBy(6.dp),
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 8.dp, vertical = 16.dp),
        ) {
            val textColor =
                if (background == null) MaterialTheme.colorScheme.onSecondaryContainer
                else MaterialTheme.colorScheme.scrim
            Row {
                Text(
                    text = meeting.name,
                    color = textColor,
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.weight(1f),
                )
                Text(
                    text = meeting.date.getFormattedDate(),
                    color = textColor,
                    style = MaterialTheme.typography.bodySmall,
                )
            }
            if (meeting.notes.isNotBlank())
                Text(
                    text = meeting.notes,
                    color = textColor,
                    fontStyle = FontStyle.Italic,
                    style = MaterialTheme.typography.bodySmall,
                )
        }
    }
}