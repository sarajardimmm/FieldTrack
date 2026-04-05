package com.example.fieldtrack.data.db.model

data class Product(
    val id: Long,
    val name: String,
    val category: String?,
    val defaultReapplyDays: Int?,
    val storageLocation: String?,
    val notes: String?
)