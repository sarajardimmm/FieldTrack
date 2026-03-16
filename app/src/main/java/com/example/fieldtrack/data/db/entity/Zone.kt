package com.example.fieldtrack.data.db.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Zone(
    @PrimaryKey val uid: Int,
    @ColumnInfo(name = "name") val name: String?,
    //cultivo
    @ColumnInfo(name = "notes") val notes: String?)