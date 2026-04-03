package com.example.fieldtrack.feature.logentry.form

import java.time.LocalDate

sealed class LogEntryEvent {
    data class ZoneChanged(val value: String) : LogEntryEvent()
    data class ProductChanged(val value: String) : LogEntryEvent()
    data class AppliedAtChanged(val value: LocalDate) : LogEntryEvent()
    data class ReapplyDaysChanged(val value: String?) : LogEntryEvent()
    data class QuantityChanged(val value: String?) : LogEntryEvent()
    data class NotesChanged(val value: String?) : LogEntryEvent()
    object SaveClicked : LogEntryEvent()
}