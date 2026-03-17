package com.example.fieldtrack.feature.logentryhistory

data class LogEntryFormData(
    val zoneName: String,
    val productName: String,
    val appliedAt: String,
    val reapplyDays: String,
    val quantity: String,
    val notes: String,
)