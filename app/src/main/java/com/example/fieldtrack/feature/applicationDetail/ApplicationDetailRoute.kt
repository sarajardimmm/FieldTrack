package com.example.fieldtrack.feature.applicationDetail

import androidx.compose.runtime.Composable
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel

@Composable
fun ApplicationDetailRoute(
    applicationId: String,
    onBackClick: () -> Unit,
    viewModel: ApplicationDetailViewModel = hiltViewModel()
) {
    ApplicationDetailScreen(
        applicationId = applicationId,
        onBackClick = onBackClick
    )
}