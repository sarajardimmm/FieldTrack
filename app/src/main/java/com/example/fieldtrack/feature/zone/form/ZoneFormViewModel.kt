package com.example.fieldtrack.feature.zone.form

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.toRoute
import com.example.fieldtrack.R
import com.example.fieldtrack.data.db.entity.ZoneEntity
import com.example.fieldtrack.data.repository.ZoneRepository
import com.example.fieldtrack.navigation.Routes
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ZoneFormViewModel @Inject constructor(
    private val zoneRepository: ZoneRepository,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val zoneId: Long = savedStateHandle.toRoute<Routes.ZoneForm>().zoneId ?: -1L

    private val _uiState = MutableStateFlow(ZoneUiState(isEditing = zoneId != -1L))
    val uiState: StateFlow<ZoneUiState> = _uiState.asStateFlow()

    private val _effect = MutableSharedFlow<ZoneEffect>()
    val effect = _effect.asSharedFlow()

    init {
        if (zoneId != -1L) {
            loadZone(zoneId)
        }
    }

    private fun loadZone(id: Long) {
        viewModelScope.launch {
            zoneRepository.getZoneById(id)?.let { zone ->
                _uiState.update { it.copy(
                    id = zone.id,
                    name = zone.name,
                    notes = zone.notes ?: ""
                ) }
            }
        }
    }

    fun onEvent(event: ZoneEvent) {
        when (event) {
            is ZoneEvent.NameChanged -> {
                _uiState.update { it.copy(name = event.name, nameErrorRes = null) }
            }
            is ZoneEvent.NotesChanged -> {
                _uiState.update { it.copy(notes = event.notes) }
            }
            ZoneEvent.SaveClicked -> {
                saveZone()
            }
        }
    }

    private fun saveZone() {
        if (_uiState.value.name.isBlank()) {
            _uiState.update { it.copy(nameErrorRes = R.string.error_zone_required) }
            return
        }

        viewModelScope.launch {
            // Check for duplicate name if creating new zone
            if (zoneId == -1L && zoneRepository.isZoneNameTaken(_uiState.value.name)) {
                _uiState.update { it.copy(nameErrorRes = R.string.error_zone_already_exists) }
                return@launch
            }

            _uiState.update { it.copy(isSaving = true) }
            val zone = ZoneEntity(
                id = if (zoneId != -1L) zoneId else 0L,
                name = _uiState.value.name,
                normalizedName = _uiState.value.name.lowercase().trim(),
                notes = _uiState.value.notes.ifBlank { null }
            )
            
            if (zoneId != -1L) {
                zoneRepository.updateZone(zone)
            } else {
                zoneRepository.insertZone(zone)
            }

            _uiState.update { it.copy(isSaving = false) }
            _effect.emit(ZoneEffect.NavigateBack)
        }
    }
}