package com.example.fieldtrack.feature.logentrydetail

import androidx.compose.runtime.Composable
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel

@Composable
fun LogEntryDetailRoute(
    onDelete: () -> Unit,
    viewModel: LogEntryDetailViewModel = hiltViewModel()
) {

    LogEntryDetailScreen(
        logEntryEntity = viewModel.logEntryEntity,
        onPrimaryAction = {
            viewModel.onDelete()
            onDelete()
        }
    )
}