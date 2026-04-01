package com.example.fieldtrack.feature.logentryhistory

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.fieldtrack.R
import com.example.fieldtrack.ui.components.AppTopBar
import com.example.fieldtrack.ui.components.HistoryItem
import com.example.fieldtrack.ui.components.LogEntrySamplePreviewData.logEntryEntityListSample
import com.example.fieldtrack.ui.theme.FieldTrackTheme
import com.example.fieldtrack.data.db.model.LogEntry
import com.example.fieldtrack.ui.components.EmptyStateCard

@Composable
fun LogEntryListScreen(
    onLogEntryClick: (Long) -> Unit,
    logEntries: List<LogEntry>
) {
    Scaffold(
        topBar = {
            AppTopBar(stringResource(R.string.title_log_history))
        }
    ) { innerPadding ->
        LogEntryListContent(
            onLogEntryClick,
            logEntries,
            Modifier.padding(innerPadding)
        )
    }
}

@Composable
fun LogEntryListContent(
    onLogEntryClick: (Long) -> Unit,
    logEntryHistory: List<LogEntry>,
    modifier: Modifier
) {
    if (logEntryHistory.isEmpty()) {
        EmptyStateCard(
            title = stringResource(R.string.title_no_log_entries),
            description = stringResource(R.string.description_no_log_entries),
            modifier = modifier
        )
    } else {
        LazyColumn(
            modifier = modifier
                .fillMaxSize()
                .padding(top = 12.dp, start = 12.dp, end = 12.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp),
        ) {

            items(logEntryHistory) { logEntry ->
                HistoryItem(
                    logEntry = logEntry,
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable {
                            onLogEntryClick(logEntry.id)
                        }
                )
            }
        }
    }
}


@Preview
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

@Preview
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