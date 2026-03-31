package com.example.fieldtrack.feature.logentrydetail

import androidx.compose.runtime.Composable
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel

@Composable
fun LogEntryDetailRoute(
    onNavigateBack: () -> Unit,
    viewModel: LogEntryDetailViewModel = hiltViewModel()
) {

    LogEntryDetailScreen(
        logEntry = viewModel.logEntryEntity,
        onPrimaryAction = viewModel::onDelete,
        onNavigateBack = onNavigateBack
    )
}