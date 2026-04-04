package com.example.fieldtrack.feature.zone.form

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.fieldtrack.R
import com.example.fieldtrack.data.db.entity.ZoneEntity
import com.example.fieldtrack.data.repository.ZoneRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ZoneFormViewModel @Inject constructor(
    private val zoneRepository: ZoneRepository
) : ViewModel() {

    var uiState by mutableStateOf(ZoneUiState())
        private set

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
                name = uiState.name,
                normalizedName = uiState.name.lowercase().replace(" ", "_"),
                notes = uiState.notes.ifBlank { null }
            )
            zoneRepository.insertZone(zone)
            uiState = uiState.copy(isSaving = false, isSaveSuccess = true)
        }
    }
}