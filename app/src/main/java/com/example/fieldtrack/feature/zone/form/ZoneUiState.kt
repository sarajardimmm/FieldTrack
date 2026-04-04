package com.example.fieldtrack.feature.zone.form

import androidx.annotation.StringRes

data class ZoneUiState(
    val id: Long? = null,
    val name: String = "",
    val notes: String = "",
    @StringRes val nameErrorRes: Int? = null,
    val isSaving: Boolean = false,
    val isEditing: Boolean = false,
    val isSaveSuccess: Boolean = false
)