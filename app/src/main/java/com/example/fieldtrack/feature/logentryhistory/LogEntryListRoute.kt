package com.example.fieldtrack.feature.logentryhistory

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel

@Composable
fun LotEntryListRoute(
    onLogEntryClick: (Long) -> Unit,
    onAddLogEntry: () -> Unit,
    viewModel: LogEntryListViewModel = hiltViewModel()
) {
    val logEntries by viewModel.logEntries.collectAsState(initial = emptyList())


    LogEntryListScreen(
        onLogEntryClick = onLogEntryClick,
        logEntries = logEntries,
        onAddLogEntry = onAddLogEntry
    )
}