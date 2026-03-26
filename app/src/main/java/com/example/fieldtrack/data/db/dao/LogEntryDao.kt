package com.example.fieldtrack.data.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.fieldtrack.data.db.entity.LogEntryEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface LogEntryDao {
    @Query("SELECT * FROM logentryentity ORDER BY createdAt DESC")
    fun getAll(): Flow<List<LogEntryEntity>>

    @Query("SELECT * FROM logentryentity WHERE aid IN (:logEntryIds)")
    suspend fun getById(logEntryIds: String): LogEntryEntity

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(logEntry: LogEntryEntity)

    @Query("DELETE FROM logentryentity WHERE aid = :id")
    suspend fun delete(id: String)

    @Update
    //UPDATE table SET ... WHERE id = ?
    suspend fun update(logEntry: LogEntryEntity)
}