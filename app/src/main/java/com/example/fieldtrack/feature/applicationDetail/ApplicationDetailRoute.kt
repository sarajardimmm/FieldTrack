package com.example.fieldtrack.feature.applicationDetail

import androidx.compose.runtime.Composable
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel

@Composable
fun ApplicationDetailRoute(
    onDelete: () -> Unit,
    viewModel: ApplicationDetailViewModel = hiltViewModel()
) {

    ApplicationDetailScreen(
        application = viewModel.application,
        onPrimaryAction = {
            viewModel.onDelete()
            onDelete()
        }
    )
}