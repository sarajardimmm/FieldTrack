package com.example.fieldtrack.data.db.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey
import java.time.LocalDate

@Entity(
    tableName = "log_entries",
    foreignKeys = [
        ForeignKey(
            entity = ZoneEntity::class,
            parentColumns = ["id"],
            childColumns = ["zoneId"],
            onDelete = ForeignKey.RESTRICT
        ),
        ForeignKey(
            entity = ProductEntity::class,
            parentColumns = ["id"],
            childColumns = ["productId"],
            onDelete = ForeignKey.RESTRICT
        )
    ],
    indices = [
        Index("zoneId"),
        Index("productId"),
        Index("appliedAt")
    ]
)
data class LogEntryEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    @ColumnInfo(name = "zoneId")
    val zoneId: Long,
    @ColumnInfo(name = "productId")
    val productId: Long,
    @ColumnInfo(name = "appliedAt")
    val appliedAt: LocalDate,
    @ColumnInfo(name = "reapplyDays")
    val reapplyDays: Int? = null,
    @ColumnInfo(name = "quantity")
    val quantity: String? = null,
    @ColumnInfo(name = "notes")
    val notes: String? = null,
    @ColumnInfo(name = "createdAt")
    val createdAt: Long = System.currentTimeMillis()
)