package com.example.fieldtrack.ui.components

import com.example.fieldtrack.feature.logentry.form.LogEntryUiState
import com.example.fieldtrack.data.db.model.LogEntry
import java.time.LocalDate

object LogEntrySamplePreviewData {
    val logEntrySample = LogEntry(
        zoneName = "Back yard",
        productName = "Pesticide x",
        appliedAt = LocalDate.of(2025, 12, 5),
        reapplyDays = 90,
        quantity = "",
        notes = "applied just before blooming started",
        id = 0
    )
    val logEntryUiStateSample = LogEntryUiState(
        zoneName = "Back yard",
        productName = "Pesticide x",
        appliedAt = LocalDate.of(2025, 12, 5),
        reapplyDays = "90",
        quantity = "",
        notes = "applied just before blooming started"
    )

    val logEntryEntityListSample = listOf(
        logEntrySample,
        LogEntry(
            zoneName = "Front yard",
            productName = "Pesticide y",
            appliedAt = LocalDate.of(2025, 1, 5),
            reapplyDays = 90,
            quantity = "",
            notes = "applied just before blooming started on the apple tree",
            id = 2
        ),
        LogEntry(
            zoneName = "Orchids",
            productName = "Fungicide",
            appliedAt = LocalDate.of(2025, 1, 5),
            reapplyDays = 30,
            quantity = "",
            notes = "applied all over with a sprayer",
            id = 3
        )
    )
}