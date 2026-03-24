package com.example.fieldtrack.feature.logentryhistory

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
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
import com.example.fieldtrack.ui.components.LogEntrySamplePreviewData.logEntryUiStateSample
import com.example.fieldtrack.ui.theme.FieldTrackTheme

@Composable
fun LogEntryListScreen(
    onLogEntryClick: (String) -> Unit,
    onAddLogEntry: () -> Unit,
    logEntryEntities: List<LogEntryEntity>
) {
    Scaffold(
        topBar = {
            AppTopBar("")
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = { onAddLogEntry() }
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
    Scaffold(
        topBar = {
            AppTopBar(stringResource(R.string.title_log_list))
        }
    ) { innerPadding ->
        Column(modifier.padding(innerPadding)) {

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