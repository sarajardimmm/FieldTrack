package com.example.fieldtrack.data.db.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    tableName = "products",
    indices = [Index(value = ["normalized_name"], unique = true)]
)
data class ProductEntity (
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    @ColumnInfo(name = "name") val name: String,
    @ColumnInfo(name = "normalizedName") val normalizedName: String,
    @ColumnInfo(name = "category") val category: String?,
    @ColumnInfo(name = "defaultReapplyDays") val defaultReapplyDays: Int?,
    @ColumnInfo(name = "storageLocation") val storageLocation: String?,
    @ColumnInfo(name = "notes") val notes: String?,
)