package com.example.fieldtrack.feature.logentryhistory

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel

@Composable
fun LotEntryListRoute(
    onLogEntryClick: (String) -> Unit,
    viewModel: LogEntryListViewModel = hiltViewModel()
) {
    val logEntryEntities by viewModel.logEntries.collectAsState(initial = emptyList())
    val uiState by viewModel.uiState.collectAsState()


    LogEntryListScreen(
        uiState = uiState,
        onEvent = viewModel::onEvent,
        onLogEntryClick = onLogEntryClick,
        logEntryEntities = logEntryEntities,
    )
}