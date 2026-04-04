package com.example.fieldtrack.feature.zone.detail

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
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
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ZoneDetailViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val zoneRepository: ZoneRepository,
    private val logEntryRepository: LogEntryRepository
) : ViewModel() {
    private val zoneId: Long = checkNotNull(savedStateHandle["zoneId"])

    var zone by mutableStateOf<Zone?>(null)
        private set

    val logEntries: StateFlow<List<LogEntry>> = logEntryRepository.getLogEntriesByZone(zoneId)
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = emptyList()
        )

    init {
        viewModelScope.launch {
            zone = zoneRepository.getZoneById(zoneId)
        }
    }
}