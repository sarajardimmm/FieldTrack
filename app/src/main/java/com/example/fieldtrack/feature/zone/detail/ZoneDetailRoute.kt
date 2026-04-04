package com.example.fieldtrack.feature.zone.detail

import androidx.compose.runtime.Composable
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel

@Composable
fun ZoneDetailRoute(
    onNavigateBack: () -> Unit,
    viewModel: ZoneDetailViewModel = hiltViewModel()
) {
    ZoneDetailScreen(
        zoneName = viewModel.zone?.name ?: "",
        zoneNotes = viewModel.zone?.notes ?: "",
        onNavigateBack = onNavigateBack
    )
}