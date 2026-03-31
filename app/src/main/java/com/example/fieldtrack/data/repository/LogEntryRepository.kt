package com.example.fieldtrack.data.repository

import androidx.room.withTransaction
import com.example.fieldtrack.data.db.AppDatabase
import com.example.fieldtrack.data.db.dao.LogEntryDao
import com.example.fieldtrack.data.db.dao.ProductDao
import com.example.fieldtrack.data.db.dao.ZoneDao
import com.example.fieldtrack.data.db.entity.LogEntryEntity
import com.example.fieldtrack.data.db.entity.ProductEntity
import com.example.fieldtrack.data.db.entity.ZoneEntity
import java.time.LocalDate
import javax.inject.Inject

class LogEntryRepository @Inject constructor(
    private val database: AppDatabase,
    private val logEntryDao: LogEntryDao,
    private val zoneDao: ZoneDao,
    private val productDao: ProductDao
) {
    fun getLogEntriesForDisplay() = logEntryDao.getLogEntriesDisplay()
    suspend fun getLogEntryForDisplay(id: Long) = logEntryDao.getLogEntryDisplayById(id)
    suspend fun insertLogEntry(logEntryEntity: LogEntryEntity) {
        logEntryDao.insert(logEntryEntity)
    }

    suspend fun createLogEntryFromNames(
        zoneName: String,
        productName: String,
        appliedAt: LocalDate,
        reapplyDays: Int?,
        quantity: String?,
        notes: String?
    ) {
        database.withTransaction {
            val resolvedZoneId = resolveZoneId(zoneName)
            val resolvedProductId = resolveProductId(productName)

            logEntryDao.insert(
                LogEntryEntity(
                    zoneId = resolvedZoneId,
                    productId = resolvedProductId,
                    appliedAt = appliedAt,
                    reapplyDays = reapplyDays,
                    quantity = quantity?.trim()?.takeIf { it.isNotBlank() },
                    notes = notes?.trim()?.takeIf { it.isNotBlank() }
                )
            )
        }
    }
    suspend fun deleteLogEntry(id: Long) {
        logEntryDao.delete(id)
    }



    private suspend fun resolveZoneId(zoneName: String): Long {
        val trimmedName = zoneName.trim()
        val normalizedName = trimmedName.lowercase()

        val existingZone = zoneDao.getByNormalizedName(normalizedName)
        if (existingZone != null) return existingZone.id

        return zoneDao.insert(
            ZoneEntity(
                name = trimmedName,
                normalizedName = normalizedName,
                notes = null
            )
        )
    }

    private suspend fun resolveProductId(productName: String): Long {
        val trimmedName = productName.trim()
        val normalizedName = trimmedName.lowercase()

        val existingProduct = productDao.getByNormalizedName(normalizedName)
        if (existingProduct != null) return existingProduct.id

        return productDao.insert(
            ProductEntity(
                name = trimmedName,
                normalizedName = normalizedName,
                category = null,
                defaultReapplyDays = null,
                storageLocation = null,
                notes = null
            )
        )
    }
}