package com.example.fieldtrack.feature.logentry.detail

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.toRoute
import com.example.fieldtrack.data.repository.LogEntryRepository
import com.example.fieldtrack.data.db.model.LogEntry
import com.example.fieldtrack.data.db.model.Zone
import com.example.fieldtrack.navigation.Routes
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LogEntryDetailViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val logEntryRepository: LogEntryRepository
) : ViewModel() {

    private val logEntryId: Long = savedStateHandle.toRoute<Routes.LogEntryDetail>().logEntryId
    var logEntry: StateFlow<LogEntry?> = logEntryRepository.getLogEntryForDisplay(logEntryId)
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = null
        )


    fun onDelete() {
        viewModelScope.launch {
            logEntryRepository.deleteLogEntry(logEntryId)
        }
    }
}