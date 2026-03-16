package com.example.fieldtrack.data.db.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.fieldtrack.data.db.entity.ApplicationEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface ApplicationDao {
    @Query("SELECT * FROM applicationentity")
    fun getAll(): Flow<List<ApplicationEntity>>

    @Query("SELECT * FROM applicationentity WHERE aid IN (:applicationIds)")
    suspend fun getById(applicationIds: String): ApplicationEntity

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(application: ApplicationEntity)

    @Query("DELETE FROM applicationentity WHERE aid = :id")
    suspend fun delete(id: String)

    @Update
    //UPDATE table SET ... WHERE id = ?
    suspend fun update(application: ApplicationEntity)
}