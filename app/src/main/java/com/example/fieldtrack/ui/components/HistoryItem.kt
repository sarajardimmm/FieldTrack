package com.example.fieldtrack.ui.components

import android.content.res.Configuration
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.fieldtrack.R
import com.example.fieldtrack.data.db.model.LogEntry
import com.example.fieldtrack.ui.components.LogEntrySamplePreviewData.logEntrySample
import com.example.fieldtrack.ui.theme.FieldTrackTheme
import com.example.fieldtrack.ui.theme.LocalSpacing
import java.time.format.DateTimeFormatter

@Composable
fun HistoryItem(logEntry: LogEntry, modifier: Modifier) {
    val formatter = DateTimeFormatter.ofPattern("dd MMM yyyy")

    val spacing = LocalSpacing.current

    Column(modifier = modifier.fillMaxWidth()) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = spacing.medium),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.spacedBy(spacing.extraSmall)
            ) {
                Text(
                    text = logEntry.zoneName,
                    style = MaterialTheme.typography.titleMedium
                )

                Text(
                    text = logEntry.productName,
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )            }

            Column(
                horizontalAlignment = Alignment.End,
                verticalArrangement = Arrangement.spacedBy(spacing.extraSmall)
            ) {
                Text(
                    text = logEntry.appliedAt.format(formatter),
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )

                logEntry.reapplyDays?.let {
                    Text(
                        text = stringResource(R.string.label_days, it),
                        style = MaterialTheme.typography.labelMedium,
                        color = MaterialTheme.colorScheme.primary
                    )
                }            }
        }
        HorizontalDivider(
            color = MaterialTheme.colorScheme.outlineVariant,
            thickness = 0.5.dp
        )
    }
}

@Preview(
    name = "History Item - Light",
    showBackground = true,
    uiMode = Configuration.UI_MODE_NIGHT_NO
)
@Preview(
    name = "History Item - Dark",
    showBackground = true,
    uiMode = Configuration.UI_MODE_NIGHT_YES
)
@Composable
fun HistoryItemPreview() {
    val logEntryEntity = logEntrySample
    val spacing = LocalSpacing.current

    FieldTrackTheme {
        Surface(modifier = Modifier.padding(spacing.medium)) {
            HistoryItem(logEntry = logEntryEntity, modifier = Modifier)
        }
    }
}
