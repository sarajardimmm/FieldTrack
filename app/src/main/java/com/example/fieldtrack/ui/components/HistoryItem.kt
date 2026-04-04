package com.example.fieldtrack.ui.components

import android.content.res.Configuration
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.fieldtrack.R
import com.example.fieldtrack.data.db.model.LogEntry
import com.example.fieldtrack.ui.components.LogEntrySamplePreviewData.logEntrySample
import com.example.fieldtrack.ui.theme.FieldTrackTheme
import java.time.format.DateTimeFormatter

@Composable
fun HistoryItem(logEntry: LogEntry, modifier: Modifier) {
    val formatter = DateTimeFormatter.ofPattern("dd MMM yyyy")

    ListItem(
        modifier,
        contentLeft = {
            Text(
                text = logEntry.zoneName,
                style = MaterialTheme.typography.titleMedium
            )

            Text(
                text = logEntry.productName,
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        },
        contentRight = {
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
            }
        }
    )
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

    FieldTrackTheme {
        Surface {
            HistoryItem(logEntry = logEntryEntity, modifier = Modifier)
        }
    }
}
