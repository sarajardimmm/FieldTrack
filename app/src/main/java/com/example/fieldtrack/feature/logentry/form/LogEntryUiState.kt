package com.example.fieldtrack.feature.logentry.form

import androidx.annotation.StringRes
import java.time.LocalDate

data class LogEntryUiState(
    val zoneName: String = "",
    @StringRes val zoneNameErrorRes: Int? = null,
    val productName: String = "",
    @StringRes val productNameErrorRes: Int? = null,
    val appliedAt: LocalDate = LocalDate.now(),
    val quantity: String? = "",
    val reapplyDays: String? = "",
    val notes: String? = "",
    val isSaving: Boolean = false,
    val genericError: String? = null
)