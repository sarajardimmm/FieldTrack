package com.example.fieldtrack.feature.logentryhistory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.fieldtrack.data.db.entity.LogEntryEntity
import com.example.fieldtrack.data.repository.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LogEntryListViewModel @Inject constructor(
    private val repository: Repository
) : ViewModel() {

    val logEntries = repository.getLogEntries()

    fun addLogEntry(
        logEntryFormData: LogEntryFormData
    ) {
        val logEntryEntity = LogEntryEntity(
            zoneName = logEntryFormData.zoneName,
            productName = logEntryFormData.productName,
            appliedAt = logEntryFormData.appliedAt,
            reapplyDays = logEntryFormData.reapplyDays,
            quantity = logEntryFormData.quantity,
            notes = logEntryFormData.notes
        )

        viewModelScope.launch {
            repository.insertLogEntry(logEntryEntity)
        }
    }

    //This is the activity/history screen.

    //A list of all applications across all zones:
    //product
    //zone
    //date
    //notes
    //due date

    //Actions:
    //
    //add application
    //edit application
    //delete application

}