package com.example.fieldtrack.feature.product.form

sealed interface ProductEvent {
    data class NameChanged(val name: String) : ProductEvent
    data class CategoryChanged(val category: String) : ProductEvent
    data class DefaultReapplyDaysChanged(val days: String) : ProductEvent
    data class StorageLocationChanged(val location: String) : ProductEvent
    data class NotesChanged(val notes: String) : ProductEvent
    data object SaveClicked : ProductEvent
}