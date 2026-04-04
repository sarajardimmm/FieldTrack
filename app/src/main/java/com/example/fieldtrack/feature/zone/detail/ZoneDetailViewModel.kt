package com.example.fieldtrack.feature.zone.detail

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.toRoute
import com.example.fieldtrack.data.db.model.LogEntry
import com.example.fieldtrack.data.db.model.Zone
import com.example.fieldtrack.data.repository.LogEntryRepository
import com.example.fieldtrack.data.repository.ZoneRepository
import com.example.fieldtrack.navigation.Routes
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class ZoneDetailViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val zoneRepository: ZoneRepository,
    private val logEntryRepository: LogEntryRepository
) : ViewModel() {
    private val zoneId: Long = savedStateHandle.toRoute<Routes.ZoneDetail>().zoneId

    val zone: StateFlow<Zone?> = zoneRepository.getZoneByIdFlow(zoneId)
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = null
        )

    val logEntries: StateFlow<List<LogEntry>> = logEntryRepository.getLogEntriesByZone(zoneId)
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = emptyList()
        )
}