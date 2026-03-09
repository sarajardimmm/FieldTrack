package com.example.fieldtrack.data.db.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Application (
    @PrimaryKey val uid: Int,
    @ColumnInfo(name = "zoneId") val zoneId: Int?,
    @ColumnInfo(name = "productId") val productId: Int?,
    @ColumnInfo(name = "appliedAt") val appliedAt: String?,
    @ColumnInfo(name = "reapplyDays") val reapplyDays: String?,
    @ColumnInfo(name = "quantity") val quantity: String?,
    @ColumnInfo(name = "notes") val notes: String?,
)