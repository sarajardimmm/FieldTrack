package com.example.fieldtrack.feature.zone.form

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.toRoute
import com.example.fieldtrack.R
import com.example.fieldtrack.data.db.entity.ZoneEntity
import com.example.fieldtrack.data.repository.ZoneRepository
import com.example.fieldtrack.navigation.Routes
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ZoneFormViewModel @Inject constructor(
    private val zoneRepository: ZoneRepository,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val zoneId: Long = savedStateHandle.toRoute<Routes.ZoneForm>().zoneId ?: -1L

    var uiState by mutableStateOf(ZoneUiState(isEditing = zoneId != -1L))
        private set

    init {
        if (zoneId != -1L) {
            loadZone(zoneId)
        }
    }

    private fun loadZone(id: Long) {
        viewModelScope.launch {
            zoneRepository.getZoneById(id)?.let { zone ->
                uiState = uiState.copy(
                    id = zone.id,
                    name = zone.name,
                    notes = zone.notes ?: ""
                )
            }
        }
    }

    fun onEvent(event: ZoneEvent) {
        when (event) {
            is ZoneEvent.NameChanged -> {
                uiState = uiState.copy(name = event.name, nameErrorRes = null)
            }
            is ZoneEvent.NotesChanged -> {
                uiState = uiState.copy(notes = event.notes)
            }
            ZoneEvent.SaveClicked -> {
                saveZone()
            }
        }
    }

    private fun saveZone() {
        if (uiState.name.isBlank()) {
            uiState = uiState.copy(nameErrorRes = R.string.error_zone_required)
            return
        }

        viewModelScope.launch {
            uiState = uiState.copy(isSaving = true)
            val zone = ZoneEntity(
                id = if (zoneId != -1L) zoneId else 0L,
                name = uiState.name,
                normalizedName = uiState.name.lowercase().trim(),
                notes = uiState.notes.ifBlank { null }
            )
            
            if (zoneId != -1L) {
                zoneRepository.updateZone(zone)
            } else {
                zoneRepository.insertZone(zone)
            }

            uiState = uiState.copy(isSaving = false, isSaveSuccess = true)
        }
    }
}