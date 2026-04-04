package com.example.fieldtrack.feature.logentry.detail

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.fieldtrack.data.repository.LogEntryRepository
import com.example.fieldtrack.data.db.model.LogEntry
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LogEntryDetailViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val logEntryRepository: LogEntryRepository
) : ViewModel() {

    private val logEntryId: Long = checkNotNull(savedStateHandle["logEntryId"])
    var logEntry by mutableStateOf<LogEntry?>(null)
        private set

    init {
        viewModelScope.launch {
            logEntry = logEntryRepository.getLogEntryForDisplay(logEntryId)
        }
    }

    fun onDelete() {
        viewModelScope.launch {
            logEntryRepository.deleteLogEntry(logEntryId)
        }
    }
}