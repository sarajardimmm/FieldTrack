package com.example.fieldtrack.data.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.fieldtrack.data.db.entity.LogEntryEntity
import com.example.fieldtrack.data.db.model.LogEntry
import kotlinx.coroutines.flow.Flow

@Dao
interface LogEntryDao {
    @Query("SELECT * FROM log_entries ORDER BY createdAt DESC")
    fun getAll(): Flow<List<LogEntryEntity>>

    @Query("SELECT le.id, z.name AS zoneName, p.name AS productName, le.appliedAt, le.reapplyDays, le.quantity, le.notes FROM log_entries le JOIN zones z ON le.zoneId = z.id JOIN products p ON le.productId = p.id WHERE le.id = :id LIMIT 1")
    fun getLogEntryDisplayById(id: Long): Flow<LogEntry?>

    @Query("SELECT le.id, z.name AS zoneName, p.name AS productName, le.appliedAt, le.reapplyDays, le.quantity, le.notes FROM log_entries le JOIN zones z ON le.zoneId = z.id JOIN products p ON le.productId = p.id WHERE le.zoneId = :zoneId ORDER BY le.appliedAt DESC")
    fun getLogEntriesDisplayByZone(zoneId: Long): Flow<List<LogEntry>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(logEntry: LogEntryEntity): Long

    @Update
    suspend fun update(logEntry: LogEntryEntity)

    @Query("SELECT createdAt FROM log_entries WHERE id = :id")
    suspend fun getCreatedAt(id: Long): Long?

    @Query("DELETE FROM log_entries WHERE id = :id")
    suspend fun delete(id: Long)

    @Query("SELECT le.id, z.name AS zoneName, p.name AS productName, le.appliedAt, le.reapplyDays, le.quantity, le.notes FROM log_entries le JOIN zones z ON le.zoneId = z.id JOIN products p ON le.productId = p.id ORDER BY le.appliedAt DESC")
    fun getLogEntriesDisplay(): Flow<List<LogEntry>>
}