package com.example.fieldtrack.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.fieldtrack.data.db.entity.LogEntryEntity
import com.example.fieldtrack.ui.theme.FieldTrackTheme

@Composable
fun HistoryItem(logEntry: LogEntryEntity, modifier: Modifier) {
    Card(
        modifier = modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surfaceVariant,
        ),
    ) {
        Row(
            modifier = Modifier.padding(vertical = 12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column {
                logEntry.zoneName?.let { Text(it, style = MaterialTheme.typography.bodyMedium) }
                Spacer(modifier = Modifier.height(4.dp))
                logEntry.productName?.let {
                    Text(
                        it,
                        style = MaterialTheme.typography.bodyMedium
                    )
                }
            }
            Spacer(modifier = Modifier.height(4.dp))

            Column {
                logEntry.appliedAt?.let { Text(it, style = MaterialTheme.typography.bodyMedium) }
                Spacer(modifier = Modifier.height(4.dp))
                logEntry.reapplyDays?.let {
                    Text(
                        it,
                        style = MaterialTheme.typography.bodyMedium
                    )
                }
            }
        }
    }
}

@Preview
@Composable
fun HistoryItemPreview() {
    val logEntryEntity = LogEntryEntity(
        zoneName = "Back yard",
        productName = "Pesticide x",
        appliedAt = "Dec 5 2025",
        reapplyDays = "Mar 5 2026",
        quantity = "",
        notes = "applied just before blooming started"
    )

    FieldTrackTheme {
        Surface {
            HistoryItem(logEntry = logEntryEntity, modifier = Modifier)
        }
    }
}
