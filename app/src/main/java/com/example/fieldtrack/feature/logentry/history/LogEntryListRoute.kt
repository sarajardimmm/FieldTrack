package com.example.fieldtrack.feature.logentry.history

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle

@Composable
fun LogEntryListRoute(
    onLogEntryClick: (Long) -> Unit,
    viewModel: LogEntryListViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    LogEntryListScreen(
        onLogEntryClick = onLogEntryClick,
        uiState = uiState
    )
}
