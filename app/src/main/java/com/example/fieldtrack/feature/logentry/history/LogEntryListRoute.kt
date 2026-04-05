package com.example.fieldtrack.feature.logentry.history

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle

@Composable
fun LogEntryListRoute(
    onLogEntryClick: (Long) -> Unit,
    viewModel: LogEntryListViewModel = hiltViewModel()
) {
    val logEntries by viewModel.logEntries.collectAsStateWithLifecycle(initialValue = emptyList())


    LogEntryListScreen(
        onLogEntryClick = onLogEntryClick,
        logEntries = logEntries
    )
}