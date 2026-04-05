package com.example.fieldtrack.feature.zone.list

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle

@Composable
fun ZoneListRoute(
    onNavigateBack: () -> Unit,
    onZoneClick: (Long) -> Unit,
    zoneListViewModel: ZoneListViewModel = hiltViewModel()
) {
    val zones by zoneListViewModel.zones.collectAsStateWithLifecycle(initialValue = emptyList())

    ZoneListScreen(
        onNavigateBack = onNavigateBack,
        onZoneClick = onZoneClick,
        zones = zones
    )
}