package com.example.fieldtrack.feature.zone.form

sealed interface ZoneEvent {
    data class NameChanged(val name: String) : ZoneEvent
    data class NotesChanged(val notes: String) : ZoneEvent
    data object SaveClicked : ZoneEvent
}