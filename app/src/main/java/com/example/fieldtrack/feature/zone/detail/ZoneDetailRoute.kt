package com.example.fieldtrack.feature.zone.detail

import androidx.compose.runtime.Composable
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel

@Composable
fun ZoneDetailRoute(
    onEditClick: (Long) -> Unit,
    onNavigateBack: () -> Unit,
    viewModel: ZoneDetailViewModel = hiltViewModel()
) {
    ZoneDetailScreen(
        zone = viewModel.zone,
        onEditClick = onEditClick,
        onNavigateBack = onNavigateBack
    )
}