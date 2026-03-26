package com.example.fieldtrack.data.db.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.time.LocalDate

@Entity
data class LogEntryEntity (
    @PrimaryKey(autoGenerate = true) val aid: Int = 0,
    @ColumnInfo(name = "zoneName") val zoneName: String?,
    @ColumnInfo(name = "productName") val productName: String?,
    @ColumnInfo(name = "appliedAt") val appliedAt: LocalDate?,
    @ColumnInfo(name = "reapplyDays") val reapplyDays: Int?,
    @ColumnInfo(name = "quantity") val quantity: String?,
    @ColumnInfo(name = "notes") val notes: String?,
    @ColumnInfo(name = "createdAt") val createdAt: Long = System.currentTimeMillis()
)