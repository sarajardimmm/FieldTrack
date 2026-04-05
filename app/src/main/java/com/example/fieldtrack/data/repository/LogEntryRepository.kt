package com.example.fieldtrack.data.repository

import androidx.room.withTransaction
import com.example.fieldtrack.data.db.AppDatabase
import com.example.fieldtrack.data.db.dao.LogEntryDao
import com.example.fieldtrack.data.db.entity.LogEntryEntity
import com.example.fieldtrack.data.db.model.LogEntry
import kotlinx.coroutines.flow.Flow
import java.time.LocalDate
import javax.inject.Inject

class LogEntryRepository @Inject constructor(
    private val database: AppDatabase,
    private val logEntryDao: LogEntryDao,
    private val zoneRepository: ZoneRepository,
    private val productRepository: ProductRepository
) {
    fun getLogEntriesForDisplay() = logEntryDao.getLogEntriesDisplay()
    
    fun getLogEntriesByZone(zoneId: Long): Flow<List<LogEntry>> =
        logEntryDao.getLogEntriesDisplayByZone(zoneId)

    fun getLogEntriesByProduct(productId: Long): Flow<List<LogEntry>> =
        logEntryDao.getLogEntriesDisplayByProduct(productId)

    fun getLogEntryForDisplay(id: Long) = logEntryDao.getLogEntryDisplayById(id)

    suspend fun saveLogEntry(
        id: Long? = null,
        zoneName: String,
        productName: String,
        appliedAt: LocalDate,
        reapplyDays: Int?,
        quantity: String?,
        notes: String?
    ) {
        database.withTransaction {
            val zoneId = zoneRepository.resolveZoneIdByName(zoneName)
            val productId = productRepository.resolveProductIdByName(productName)

            val logEntry = createLogEntryEntity(
                id = id,
                zoneId = zoneId,
                productId = productId,
                appliedAt = appliedAt,
                reapplyDays = reapplyDays,
                quantity = quantity,
                notes = notes
            )

            if (logEntry.id != 0L) {
                logEntryDao.update(logEntry)
            } else {
                logEntryDao.insert(logEntry)
            }
        }
    }

    private suspend fun createLogEntryEntity(
        id: Long?,
        zoneId: Long,
        productId: Long,
        appliedAt: LocalDate,
        reapplyDays: Int?,
        quantity: String?,
        notes: String?
    ): LogEntryEntity {
        val createdAt = if (id != null && id != 0L) {
            logEntryDao.getCreatedAt(id) ?: System.currentTimeMillis()
        } else {
            System.currentTimeMillis()
        }

        return LogEntryEntity(
            id = id ?: 0L,
            zoneId = zoneId,
            productId = productId,
            appliedAt = appliedAt,
            reapplyDays = reapplyDays,
            quantity = quantity?.trim()?.takeIf { it.isNotBlank() },
            notes = notes?.trim()?.takeIf { it.isNotBlank() },
            createdAt = createdAt
        )
    }

    suspend fun deleteLogEntry(id: Long) {
        logEntryDao.delete(id)
    }
}