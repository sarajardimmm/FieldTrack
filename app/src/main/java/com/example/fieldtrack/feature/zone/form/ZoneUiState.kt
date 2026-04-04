package com.example.fieldtrack.feature.zone.form

import androidx.annotation.StringRes

data class ZoneUiState(
    val name: String = "",
    val notes: String = "",
    @StringRes val nameErrorRes: Int? = null,
    val isSaving: Boolean = false,
    val isSaveSuccess: Boolean = false
)