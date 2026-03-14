package com.example.fieldtrack.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.fieldtrack.data.db.entity.ApplicationEntity

@Composable
fun HistoryItem(application: ApplicationEntity, modifier: Modifier) {
    Row(
        modifier = modifier.padding(vertical = 12.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column {
            application.zoneName?.let { Text(it, style = MaterialTheme.typography.bodyMedium) }
            application.productName?.let { Text(it, style = MaterialTheme.typography.bodyMedium) }
        }
        Column {
            application.appliedAt?.let { Text(it, style = MaterialTheme.typography.bodyMedium) }
            application.reapplyDays?.let { Text(it, style = MaterialTheme.typography.bodyMedium) }
        }
    }
}

@Preview
@Composable
fun HistoryItemPreview(){
    val application = ApplicationEntity(
        zoneName = "Back yard",
        productName = "Pesticide x",
        appliedAt = "Dec 5 2025",
        reapplyDays = "Mar 5 2026",
        quantity = "",
        notes = "applied just before blooming started"
    )

    HistoryItem(application = application, modifier = Modifier)
}
