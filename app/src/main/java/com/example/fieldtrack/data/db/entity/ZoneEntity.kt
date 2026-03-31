package com.example.fieldtrack.data.db.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    tableName = "zones",
    indices = [Index(value = ["normalized_name"], unique = true)]
)
data class ZoneEntity(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    @ColumnInfo(name = "name") val name: String,
    @ColumnInfo(name = "normalizedName") val normalizedName: String,
    @ColumnInfo(name = "notes") val notes: String?)