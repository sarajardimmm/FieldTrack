package com.example.fieldtrack.feature.logentryhistory

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Description
import androidx.compose.material3.Button
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.fieldtrack.R
import com.example.fieldtrack.data.db.entity.LogEntryEntity
import com.example.fieldtrack.ui.components.AppTopBar
import com.example.fieldtrack.ui.components.HistoryItem
import com.example.fieldtrack.ui.components.LogEntrySamplePreviewData.logEntryEntityListSample
import com.example.fieldtrack.ui.theme.FieldTrackTheme
import androidx.compose.ui.Alignment
import androidx.compose.ui.text.style.TextAlign
import com.example.fieldtrack.feature.logentryhistory.model.LogEntryDisplay
import com.example.fieldtrack.ui.components.EmptyStateCard

@Composable
fun LogEntryListScreen(
    onLogEntryClick: (Long) -> Unit,
    logEntries: List<LogEntryDisplay>
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
    logEntryHistory: List<LogEntryDisplay>,
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