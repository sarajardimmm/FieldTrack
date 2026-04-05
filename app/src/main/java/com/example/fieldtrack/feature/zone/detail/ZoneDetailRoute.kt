package com.example.fieldtrack.feature.zone.detail

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle

@Composable
fun ZoneDetailRoute(
    onEditClick: (Long) -> Unit,
    onLogEntryClick: (Long) -> Unit,
    onNavigateBack: () -> Unit,
    viewModel: ZoneDetailViewModel = hiltViewModel()
) {
    val logEntries by viewModel.logEntries.collectAsStateWithLifecycle()
    val zone by viewModel.zone.collectAsStateWithLifecycle()

    ZoneDetailScreen(
        zone = zone,
        logEntries = logEntries,
        onEditClick = onEditClick,
        onLogEntryClick = onLogEntryClick,
        onNavigateBack = onNavigateBack
    )
}