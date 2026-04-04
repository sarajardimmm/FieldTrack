package com.example.fieldtrack.feature.logentry.detail

import androidx.compose.runtime.Composable
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel

@Composable
fun LogEntryDetailRoute(
    onNavigateBack: () -> Unit,
    viewModel: LogEntryDetailViewModel = hiltViewModel()
) {

    LogEntryDetailScreen(
        logEntry = viewModel.logEntry,
        onPrimaryAction = viewModel::onDelete,
        onNavigateBack = onNavigateBack
    )
}