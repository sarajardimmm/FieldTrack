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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
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

@Composable
fun LogEntryListScreen(
    onLogEntryClick: (String) -> Unit,
    onAddLogEntry: () -> Unit,
    logEntryEntities: List<LogEntryEntity>
) {
    Scaffold(
        topBar = {
            AppTopBar(stringResource(R.string.title_log_history))
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = { onAddLogEntry() },
                containerColor = MaterialTheme.colorScheme.tertiary,
            ) {
                Icon(Icons.Default.Add, contentDescription = "Add")
            }
        }
    ) { innerPadding ->
        LogEntryListContent(
            onLogEntryClick,
            logEntryEntities,
            Modifier.padding(innerPadding)
        )
    }
}

@Composable
fun LogEntryListContent(
    onLogEntryClick: (String) -> Unit,
    logEntryHistory: List<LogEntryEntity>,
    modifier: Modifier
) {
    if (logEntryHistory.isEmpty()) {
        Box(
            modifier = modifier
                .fillMaxSize()
                .padding(horizontal = 12.dp),
            contentAlignment = Alignment.Center
        ) {
            Text("No logged entries, please add one")
        }
    } else {
        LazyColumn(
            modifier = modifier
                .fillMaxSize().padding(top = 12.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp),
        ) {
            items(logEntryHistory) { logEntry ->
                HistoryItem(
                    logEntry = logEntry,
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable {
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
                onLogEntryClick = {},
                logEntryEntities = logEntryEntityListSample,
                onAddLogEntry = {}
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
                logEntryEntities = emptyList(),
                onAddLogEntry = {}
            )
        }
    }
}