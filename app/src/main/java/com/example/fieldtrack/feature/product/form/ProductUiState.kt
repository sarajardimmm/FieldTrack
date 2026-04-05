package com.example.fieldtrack.feature.product.form

import androidx.annotation.StringRes

data class ProductUiState(
    val id: Long = 0,
    val name: String = "",
    @StringRes val nameErrorRes: Int? = null,
    val category: String = "",
    val defaultReapplyDays: String = "",
    val storageLocation: String = "",
    val notes: String = "",
    val isSaving: Boolean = false,
    val isEditing: Boolean = false,
    val isSaveSuccess: Boolean = false
)