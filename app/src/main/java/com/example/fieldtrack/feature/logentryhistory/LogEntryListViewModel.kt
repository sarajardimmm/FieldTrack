package com.example.fieldtrack.feature.logentryhistory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.fieldtrack.data.db.entity.LogEntryEntity
import com.example.fieldtrack.data.repository.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LogEntryListViewModel @Inject constructor(
    private val repository: Repository
) : ViewModel() {

    private val _uiState = MutableStateFlow(LogEntryUiState())
    val uiState: StateFlow<LogEntryUiState> = _uiState
    val logEntries = repository.getLogEntries()

    fun onEvent(event: LogEntryEvent) {
        when (event) {
            is LogEntryEvent.ZoneChanged -> {
                _uiState.update { it.copy(zoneName = event.value) }
            }
            is LogEntryEvent.ProductChanged -> {
                _uiState.update { it.copy(productName = event.value) }
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

    fun addLogEntry() {
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
        }
    }

    //zone is required
    //applied date is required
    //for now, product required, later, when we support pruning/harvesting, product can become optional

}