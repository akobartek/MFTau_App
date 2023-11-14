package pl.mftau.mftau.breviary.presentation.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import pl.mftau.mftau.breviary.domain.model.BreviaryPart

@Composable
internal fun BreviaryPartHeader(title: String, pages: String, verses: String = "") {
    Column {
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth()
        ) {
            if (title.isNotBlank())
                Text(
                    text = title.uppercase(),
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.secondary,
                    modifier = Modifier.padding(end = 32.dp)
                )
            Text(
                text = verses,
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.secondary
            )
        }
        if (pages.isNotBlank())
            Text(text = pages, fontSize = 10.sp)
        Spacer(modifier = Modifier.height(12.dp))
    }
}

@Composable
internal fun BreviaryPartLayout(title: String, breviaryPart: BreviaryPart) {
    Column {
        BreviaryPartHeader(
            title = title,
            pages = breviaryPart.breviaryPages,
            verses = breviaryPart.verses
        )
        Text(text = breviaryPart.text, fontSize = 15.sp)
    }
}