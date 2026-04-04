package com.example.fieldtrack.feature.zone.detail

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.fieldtrack.data.db.model.Zone
import com.example.fieldtrack.data.repository.ZoneRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ZoneDetailViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val zoneRepository: ZoneRepository
) : ViewModel() {
    private val zoneId: Long = checkNotNull(savedStateHandle["zoneId"])

    var zone by mutableStateOf<Zone?>(null)
        private set

    init {
        viewModelScope.launch {
            zone = zoneRepository.getZoneById(zoneId)
        }
    }
}