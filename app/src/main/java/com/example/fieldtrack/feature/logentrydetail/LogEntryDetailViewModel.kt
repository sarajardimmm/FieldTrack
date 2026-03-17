package com.example.fieldtrack.feature.logentrydetail

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.fieldtrack.data.db.entity.LogEntryEntity
import com.example.fieldtrack.data.repository.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LogEntryDetailViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val repository: Repository
) : ViewModel() {

    private val logEntryId: String = checkNotNull(savedStateHandle["logEntryId"])
    var logEntryEntity by mutableStateOf<LogEntryEntity?>(null)
        private set

    init {
        viewModelScope.launch {
            logEntryEntity = repository.getLogEntry(logEntryId)
        }
    }

    fun onDelete() {
        viewModelScope.launch {
            repository.deleteLogEntry(logEntryId)

        }
    }
}