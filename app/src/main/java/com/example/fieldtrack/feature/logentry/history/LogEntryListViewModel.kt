package com.example.fieldtrack.feature.logentry.history

import androidx.lifecycle.ViewModel
import com.example.fieldtrack.data.repository.LogEntryRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class LogEntryListViewModel @Inject constructor(
    logEntryRepository: LogEntryRepository
) : ViewModel() {
    val logEntries = logEntryRepository.getLogEntriesForDisplay()
}