package com.example.fieldtrack.feature.logentry.form

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.toRoute
import com.example.fieldtrack.R
import com.example.fieldtrack.data.repository.LogEntryRepository
import com.example.fieldtrack.navigation.Routes
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LogEntryFormViewModel @Inject constructor(
    private val logEntryRepository: LogEntryRepository,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val logEntryId: Long = savedStateHandle.toRoute<Routes.LogEntryForm>().logEntryId ?: -1L

    private val _uiState = MutableStateFlow(LogEntryUiState(isEditing = logEntryId != -1L))
    val uiState: StateFlow<LogEntryUiState> = _uiState

    private val _effect = MutableSharedFlow<LogEntryEffect>()
    val effect = _effect.asSharedFlow()

    init {
        if (logEntryId != -1L) {
            loadLogEntry(logEntryId)
        }
    }

    private fun loadLogEntry(id: Long) {
        viewModelScope.launch {
            logEntryRepository.getLogEntryForDisplay(id)?.let { logEntry ->
                _uiState.update {
                    it.copy(
                        zoneName = logEntry.zoneName,
                        productName = logEntry.productName,
                        appliedAt = logEntry.appliedAt,
                        quantity = logEntry.quantity,
                        reapplyDays = logEntry.reapplyDays?.toString(),
                        notes = logEntry.notes
                    )
                }
            }
        }
    }

    fun onEvent(event: LogEntryEvent) {
        when (event) {
            is LogEntryEvent.ZoneChanged -> {
                _uiState.update {
                    it.copy(
                        zoneName = event.value,
                        zoneNameErrorRes = null
                    )
                }
            }

            is LogEntryEvent.ProductChanged -> {
                _uiState.update {
                    it.copy(
                        productName = event.value,
                        productNameErrorRes = null
                    )
                }
            }

            is LogEntryEvent.ReapplyDaysChanged -> {
                _uiState.update { it.copy(reapplyDays = event.value) }
            }

            is LogEntryEvent.AppliedAtChanged -> {
                _uiState.update { it.copy(appliedAt = event.value) }
            }

            is LogEntryEvent.QuantityChanged -> {
                _uiState.update { it.copy(quantity = event.value) }
            }

            is LogEntryEvent.NotesChanged -> {
                _uiState.update { it.copy(notes = event.value) }
            }

            LogEntryEvent.SaveClicked -> {
                saveLogEntry()
            }
        }
    }

    private fun validateForm(): Boolean {
        val state = _uiState.value

        val zoneError =
            if (state.zoneName.isBlank()) R.string.error_zone_required else null

        val productError =
            if (state.productName.isBlank()) R.string.error_product_required else null

        _uiState.update {
            it.copy(
                zoneNameErrorRes = zoneError,
                productNameErrorRes = productError,
            )
        }

        return zoneError == null && productError == null
    }

    private fun saveLogEntry() {
        if (!validateForm()) return

        val state = _uiState.value

        viewModelScope.launch {
            logEntryRepository.saveLogEntry(
                id = if (logEntryId != -1L) logEntryId else null,
                zoneName = state.zoneName,
                productName = state.productName,
                appliedAt = state.appliedAt,
                reapplyDays = state.reapplyDays?.toIntOrNull(),
                quantity = state.quantity,
                notes = state.notes
            )
            _effect.emit(LogEntryEffect.NavigateBack)
        }
    }
}
