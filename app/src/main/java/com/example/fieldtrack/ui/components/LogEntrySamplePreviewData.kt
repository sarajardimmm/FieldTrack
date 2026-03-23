package com.example.fieldtrack.ui.components

import com.example.fieldtrack.data.db.entity.LogEntryEntity
import com.example.fieldtrack.feature.logentryhistory.LogEntryUiState
import java.time.LocalDate

object LogEntrySamplePreviewData {
    val logEntryEntitySample = LogEntryEntity(
        zoneName = "Back yard",
        productName = "Pesticide x",
        appliedAt = LocalDate.of(2025, 12, 5),
        reapplyDays = 90,
        quantity = "",
        notes = "applied just before blooming started"
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
        logEntryEntitySample,
        LogEntryEntity(
            zoneName = "Front yard",
            productName = "Pesticide y",
            appliedAt = LocalDate.of(2025, 1, 5),
            reapplyDays = 90,
            quantity = "",
            notes = "applied just before blooming started on the apple tree"
        ),
        LogEntryEntity(
            zoneName = "Orchids",
            productName = "Fungicide",
            appliedAt = LocalDate.of(2025, 1, 5),
            reapplyDays = 30,
            quantity = "",
            notes = "applied all over with a sprayer"
        )
    )
}