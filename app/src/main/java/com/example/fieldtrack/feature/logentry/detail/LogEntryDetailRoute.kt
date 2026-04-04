package com.example.fieldtrack.feature.logentry.detail

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle

@Composable
fun LogEntryDetailRoute(
    onEditClick: (Long) -> Unit,
    onNavigateBack: () -> Unit,
    viewModel: LogEntryDetailViewModel = hiltViewModel()
) {

    val logEntry by viewModel.logEntry.collectAsStateWithLifecycle()

    LogEntryDetailScreen(
        logEntry = logEntry,
        onEditClick = onEditClick,
        onPrimaryAction = viewModel::onDelete,
        onNavigateBack = onNavigateBack
    )
}