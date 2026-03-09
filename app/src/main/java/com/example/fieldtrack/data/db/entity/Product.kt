package com.example.fieldtrack.data.db.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Product (
    @PrimaryKey val uid: Int,
    @ColumnInfo(name = "name") val name: String?,
    @ColumnInfo(name = "category") val category: String?,
    @ColumnInfo(name = "defaultReapplyDays") val defaultReapplyDays: String?,
    @ColumnInfo(name = "storageLocation") val storageLocation: String?,
    @ColumnInfo(name = "notes") val notes: String?,
)