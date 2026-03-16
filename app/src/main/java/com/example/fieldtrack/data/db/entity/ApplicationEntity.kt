package com.example.fieldtrack.data.db.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class ApplicationEntity (
    @PrimaryKey(autoGenerate = true) val aid: Int = 0,
    @ColumnInfo(name = "zoneName") val zoneName: String?,
    @ColumnInfo(name = "productName") val productName: String?,
    @ColumnInfo(name = "appliedAt") val appliedAt: String?,
    @ColumnInfo(name = "reapplyDays") val reapplyDays: String?,
    @ColumnInfo(name = "quantity") val quantity: String?,
    @ColumnInfo(name = "notes") val notes: String?,
)