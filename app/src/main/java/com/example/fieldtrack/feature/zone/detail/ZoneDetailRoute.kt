package com.example.fieldtrack.feature.zone.detail

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel

@Composable
fun ZoneDetailRoute(
    onEditClick: (Long) -> Unit,
    onLogEntryClick: (Long) -> Unit,
    onNavigateBack: () -> Unit,
    viewModel: ZoneDetailViewModel = hiltViewModel()
) {
    val logEntries by viewModel.logEntries.collectAsState()

    ZoneDetailScreen(
        zone = viewModel.zone,
        logEntries = logEntries,
        onEditClick = onEditClick,
        onLogEntryClick = onLogEntryClick,
        onNavigateBack = onNavigateBack
    )
}