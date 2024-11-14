package pl.mftau.mftau.leaders.presentation.people.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import pl.mftau.mftau.leaders.domain.model.Person

@Composable
fun PersonCard(
    modifier: Modifier = Modifier,
    person: Person,
    showOnlyName: Boolean = false,
    onClick: ((Person) -> Unit) = {},
    additionalContent: @Composable ColumnScope.() -> Unit = {},
) {
    Card(
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.secondaryContainer
        ),
        onClick = { onClick(person) },
        modifier = modifier.fillMaxWidth(),
    ) {
        Column(
            verticalArrangement = if (showOnlyName) Arrangement.Center else Arrangement.spacedBy(4.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxWidth()
                .heightIn(min = if (showOnlyName) 76.dp else 100.dp)
                .padding(8.dp),
        ) {
            if (!showOnlyName)
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .fillMaxWidth()
                        .heightIn(min = 20.dp),
                ) {
                    Text(
                        text = person.city,
                        style = MaterialTheme.typography.bodySmall,
                        modifier = Modifier.weight(1f),
                    )
                    person.type.getTypeIcon()?.let {
                        Icon(
                            imageVector = it,
                            contentDescription = null,
                            modifier = Modifier.size(20.dp),
                        )
                    }
                }
            Text(
                text = person.name.reversed().replaceFirst(" ", "\n").reversed(),
                style = MaterialTheme.typography.titleMedium,
                textAlign = TextAlign.Center,
            )
            additionalContent()
        }
    }
}