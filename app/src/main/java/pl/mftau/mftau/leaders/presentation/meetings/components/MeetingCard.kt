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
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import pl.mftau.mftau.common.utils.getDateFormatted
import pl.mftau.mftau.leaders.domain.model.Meeting

@Composable
fun MeetingCard(
    modifier: Modifier = Modifier,
    meeting: Meeting,
    onClick: (Meeting) -> Unit = {}
) {
    Card(
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.secondaryContainer
        ),
        onClick = { onClick(meeting) },
        modifier = modifier.fillMaxWidth()
    ) {
        Column(
            verticalArrangement = Arrangement.spacedBy(6.dp),
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 8.dp, vertical = 16.dp)
        ) {
            Row {
                Text(
                    text = meeting.name,
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.weight(1f)
                )
                Text(
                    text = meeting.date.toDate().getDateFormatted(),
                    style = MaterialTheme.typography.bodySmall
                )
            }
            if (meeting.notes.isNotBlank())
                Text(
                    text = meeting.notes,
                    fontStyle = FontStyle.Italic,
                    style = MaterialTheme.typography.bodySmall
                )
        }
    }
}