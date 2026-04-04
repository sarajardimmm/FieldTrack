package com.example.fieldtrack.feature.zone.list

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel

@Composable
fun ZoneListRoute(
    onNavigateBack: () -> Unit,
    onZoneClick: (Long) -> Unit,
    zoneListViewModel: ZoneListViewModel = hiltViewModel()
) {
    val zones by zoneListViewModel.zones.collectAsState(initial = emptyList())

    ZoneListScreen(
        onNavigateBack = onNavigateBack,
        onZoneClick = onZoneClick,
        zones = zones
    )
}