package com.example.fieldtrack.navigation

import kotlinx.serialization.Serializable

@Serializable
sealed interface Routes {
    @Serializable
    data object History : Routes

    @Serializable
    data object Zones : Routes

    @Serializable
    data object Products : Routes

    @Serializable
    data class LogEntryDetail(val logEntryId: Long) : Routes

    @Serializable
    data class LogEntryForm(val logEntryId: Long? = null) : Routes

    @Serializable
    data class ZoneDetail(val zoneId: Long) : Routes

    @Serializable
    data class ZoneForm(val zoneId: Long? = null) : Routes
}
