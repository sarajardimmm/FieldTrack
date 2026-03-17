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
import com.example.fieldtrack.ui.components.LogEntryForm
import com.example.fieldtrack.ui.components.LogEntrySamplePreviewData
import com.example.fieldtrack.ui.components.HistoryItem
import com.example.fieldtrack.ui.theme.FieldTrackTheme

@Composable
fun LogEntryListScreen(
    onLogEntryClick: (String) -> Unit,
    onAddLogEntry: (LogEntryFormData) -> Unit,
    logEntryEntities: List<LogEntryEntity>
) {
    Scaffold(
        topBar = {
            AppTopBar("")
        }
    ) { innerPadding ->
        LogEntryListContent(
            onLogEntryClick,
            onAddLogEntry,
            logEntryEntities,
            Modifier.padding(innerPadding)
        )
    }

}

@Composable
fun LogEntryListContent(
    onLogEntryClick: (String) -> Unit,
    onAddLogEntry: (LogEntryFormData) -> Unit,
    logEntryHistory: List<LogEntryEntity>,
    modifier: Modifier
) {
    Column(modifier.padding(12.dp)) {
        LogEntryForm(
            actionLabel = "Add",
            onLogEntryAction = onAddLogEntry
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
            LogEntryListScreen({}, {}, LogEntrySamplePreviewData.logEntryEntityListSample)
        }
    }
}