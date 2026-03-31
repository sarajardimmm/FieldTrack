package com.example.fieldtrack.feature.logEntryForm

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.fieldtrack.R
import com.example.fieldtrack.data.db.entity.LogEntryEntity
import com.example.fieldtrack.data.repository.Repository
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
    private val repository: Repository
) : ViewModel() {

    private val _uiState = MutableStateFlow(LogEntryUiState())
    val uiState: StateFlow<LogEntryUiState> = _uiState

    private val _effect = MutableSharedFlow<LogEntryEffect>()
    val effect = _effect.asSharedFlow()

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
                addLogEntry()
            }
        }
    }

    private fun validateForm(): Boolean {
        val state = _uiState.value

        val zoneError =
            if (state.zoneName.isNullOrBlank()) R.string.error_zone_required else null

        val productError =
            if (state.productName.isNullOrBlank()) R.string.error_product_required else null

        _uiState.update {
            it.copy(
                zoneNameErrorRes = zoneError,
                productNameErrorRes = productError,
            )
        }

        return zoneError == null && productError == null
    }

    fun addLogEntry() {
        if (!validateForm()) return

        val state = _uiState.value

        val logEntryEntity = LogEntryEntity(
            zoneName = state.zoneName,
            productName = state.productName,
            appliedAt = state.appliedAt,
            reapplyDays = state.reapplyDays?.toIntOrNull(),
            quantity = state.quantity,
            notes = state.notes
        )

        viewModelScope.launch {
            repository.insertLogEntry(logEntryEntity)
            _effect.emit(LogEntryEffect.NavigateBack)
        }
    }
}
