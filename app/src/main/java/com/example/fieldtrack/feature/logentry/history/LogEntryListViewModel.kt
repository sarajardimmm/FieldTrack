package com.example.fieldtrack.feature.logentry.history

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.fieldtrack.data.repository.LogEntryRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import java.time.LocalDate
import java.time.temporal.ChronoUnit
import javax.inject.Inject

@HiltViewModel
class LogEntryListViewModel @Inject constructor(
    logEntryRepository: LogEntryRepository
) : ViewModel() {
    
    val uiState: StateFlow<LogEntryListUiState> = logEntryRepository.getLogEntriesForDisplay()
        .map { entries ->
            val today = LocalDate.now()
            
            val pending = entries
                .filter { it.reapplyDays != null }
                .groupBy { it.zoneName to it.productName }
                .map { (_, zoneProductEntries) ->
                    // Get the latest entry for this zone/product combination
                    zoneProductEntries.maxBy { it.appliedAt }
                }
                .map { entry ->
                    val dueDate = entry.appliedAt.plusDays(entry.reapplyDays!!.toLong())
                    val daysRemaining = ChronoUnit.DAYS.between(today, dueDate)
                    PendingReapply(
                        logEntry = entry,
                        dueDate = dueDate,
                        daysRemaining = daysRemaining,
                        isOverdue = daysRemaining < 0
                    )
                }
                .sortedWith(compareBy({ it.isOverdue }, { it.dueDate }))

            LogEntryListUiState(
                pendingReapplies = pending,
                history = entries,
                isLoading = false
            )
        }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = LogEntryListUiState()
        )
}
