package com.example.fieldtrack.ui.components

import com.example.fieldtrack.data.db.entity.LogEntryEntity

object LogEntrySamplePreviewData {
    val logEntryEntitySample = LogEntryEntity(
        zoneName = "Back yard",
        productName = "Pesticide x",
        appliedAt = "Dec 5 2025",
        reapplyDays = "Mar 5 2026",
        quantity = "",
        notes = "applied just before blooming started"
    )

    val logEntryEntityListSample = listOf(
        logEntryEntitySample,
        LogEntryEntity(
            zoneName = "Front yard",
            productName = "Pesticide y",
            appliedAt = "Jan 5 2025",
            reapplyDays = "Jan 5 2026",
            quantity = "",
            notes = "applied just before blooming started on the apple tree"
        ),
        LogEntryEntity(
            zoneName = "Orchids",
            productName = "Fungicide",
            appliedAt = "Jan 5 2025",
            reapplyDays = "Jan 5 2026",
            quantity = "",
            notes = "applied all over with a sprayer"
        )
    )
}