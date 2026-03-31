package com.example.fieldtrack.data.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.fieldtrack.data.db.entity.LogEntryEntity
import com.example.fieldtrack.feature.logentryhistory.model.LogEntryDisplay
import kotlinx.coroutines.flow.Flow

@Dao
interface LogEntryDao {
    @Query("SELECT * FROM log_entries ORDER BY createdAt DESC")
    fun getAll(): Flow<List<LogEntryEntity>>

    @Query("SELECT le.id, z.name AS zoneName, p.name AS productName, le.appliedAt, le.reapplyDays, le.quantity, le.notes FROM log_entries le JOIN zones z ON le.zoneId = z.id JOIN products p ON le.productId = p.id WHERE le.id = :id LIMIT 1")
    suspend fun getLogEntryDisplayById(id: Long): LogEntryDisplay?

    @Query("SELECT * FROM log_entries WHERE zoneId IN (:zoneIds)")
    fun getByZone(zoneIds: String): Flow<List<LogEntryEntity>>

    @Query("SELECT * FROM log_entries WHERE productId IN (:productIds)")
    fun getByProduct(productIds: String): Flow<List<LogEntryEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(logEntry: LogEntryEntity)

    @Query("DELETE FROM log_entries WHERE id = :id")
    suspend fun delete(id: Long)

    @Query(" SELECT le.id, z.name AS zoneName, p.name AS productName, le.appliedAt, le.reapplyDays, le.quantity, le.notes FROM log_entries le JOIN zones z ON le.zoneId = z.id JOIN products p ON le.productId = p.id ORDER BY le.appliedAt DESC")
    fun getLogEntriesDisplay(): Flow<List<LogEntryDisplay>>
}