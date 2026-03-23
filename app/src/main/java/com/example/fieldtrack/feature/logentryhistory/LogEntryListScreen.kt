package com.example.fieldtrack.feature.logentryhistory

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.fieldtrack.data.db.entity.LogEntryEntity
import com.example.fieldtrack.ui.components.AppTopBar
import com.example.fieldtrack.ui.components.HistoryItem
import com.example.fieldtrack.ui.components.LogEntryForm
import com.example.fieldtrack.ui.components.LogEntrySamplePreviewData.logEntryEntityListSample
import com.example.fieldtrack.ui.components.LogEntrySamplePreviewData.logEntryUiStateSample
import com.example.fieldtrack.ui.theme.FieldTrackTheme

@Composable
fun LogEntryListScreen(
    uiState: LogEntryUiState,
    onEvent: (LogEntryEvent) -> Unit,
    onLogEntryClick: (String) -> Unit,
    logEntryEntities: List<LogEntryEntity>
) {
    Scaffold(
        topBar = {
            AppTopBar("")
        }
    ) { innerPadding ->
        LogEntryListContent(
            uiState,
            "Add",
            onEvent,
            onLogEntryClick,
            logEntryEntities,
            Modifier.padding(innerPadding)
        )
    }

}

@Composable
fun LogEntryListContent(
    uiState: LogEntryUiState,
    value: String,
    onEvent: (LogEntryEvent) -> Unit,
    onLogEntryClick: (String) -> Unit,
    logEntryHistory: List<LogEntryEntity>,
    modifier: Modifier
) {
    Column(modifier.padding(12.dp)) {
        LogEntryForm(
            uiState = uiState,
            actionLabel = value,
            onEvent = onEvent,
        )

        Spacer(modifier = Modifier.size(16.dp))

        LazyColumn {
            items(logEntryHistory) { logEntry ->

                HistoryItem(
                    logEntry = logEntry,
                    modifier = Modifier.clickable {
                        onLogEntryClick(logEntry.aid.toString())
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
                uiState = logEntryUiStateSample,
                onEvent = {},
                onLogEntryClick = {},
                logEntryEntities = logEntryEntityListSample,
            )
        }
    }
}