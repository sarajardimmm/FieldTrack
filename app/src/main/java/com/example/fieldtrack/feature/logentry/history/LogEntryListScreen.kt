package com.example.fieldtrack.feature.logentry.history

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.fieldtrack.R
import com.example.fieldtrack.data.db.model.LogEntry
import com.example.fieldtrack.ui.components.EmptyStateCard
import com.example.fieldtrack.ui.components.HistoryItem
import com.example.fieldtrack.ui.components.LogEntrySamplePreviewData.logEntryEntityListSample
import com.example.fieldtrack.ui.theme.FieldTrackTheme
import com.example.fieldtrack.ui.theme.LocalSpacing

@Composable
fun LogEntryListScreen(
    onLogEntryClick: (Long) -> Unit,
    logEntries: List<LogEntry>
) {
    LogEntryListContent(
        onLogEntryClick = onLogEntryClick,
        logEntries = logEntries,
        modifier = Modifier.fillMaxSize()
    )
}

@Composable
fun LogEntryListContent(
    onLogEntryClick: (Long) -> Unit,
    logEntries: List<LogEntry>,
    modifier: Modifier = Modifier
) {
    val spacing = LocalSpacing.current
    
    if (logEntries.isEmpty()) {
        EmptyStateCard(
            title = stringResource(R.string.title_no_log_entries),
            description = stringResource(R.string.description_no_log_entries),
            modifier = modifier.padding(spacing.medium)
        )
    } else {
        LazyColumn(
            modifier = modifier.fillMaxSize(),
            contentPadding = PaddingValues(spacing.medium),
            verticalArrangement = Arrangement.spacedBy(spacing.small),
        ) {
            items(logEntries) { logEntry ->
                HistoryItem(
                    logEntry = logEntry,
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable { onLogEntryClick(logEntry.id) }
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun LogEntryListScreenPreview() {
    FieldTrackTheme {
        Surface {
            LogEntryListScreen(
                onLogEntryClick = {},
                logEntries = logEntryEntityListSample,
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun LogEntryListScreenEmptyPreview() {
    FieldTrackTheme {
        Surface {
            LogEntryListScreen(
                onLogEntryClick = {},
                logEntries = emptyList(),
            )
        }
    }
}
