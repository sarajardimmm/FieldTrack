package com.example.fieldtrack.data.repository

import com.example.fieldtrack.data.db.dao.LogEntryDao
import com.example.fieldtrack.data.db.entity.LogEntryEntity
import javax.inject.Inject

class Repository @Inject constructor(
    private val logEntryDao: LogEntryDao
) {
    fun getLogEntries() = logEntryDao.getAll()

    suspend fun getLogEntry(id: String) = logEntryDao.getById(id)
    suspend fun insertLogEntry(logEntryEntity: LogEntryEntity) {
        logEntryDao.insert(logEntryEntity)
    }

    suspend fun deleteLogEntry(id: String) {
        logEntryDao.delete(id)
    }

    suspend fun updateLogEntry(logEntryEntity: LogEntryEntity) {
        logEntryDao.update(logEntryEntity)
    }
}