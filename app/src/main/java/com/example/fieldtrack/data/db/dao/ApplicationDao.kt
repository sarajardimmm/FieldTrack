package com.example.fieldtrack.data.db.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.fieldtrack.data.db.entity.Application
import kotlinx.coroutines.flow.Flow

@Dao
interface ApplicationDao {
    @Query("SELECT * FROM application")
    fun getAll(): Flow<List<Application>>

    @Query("SELECT * FROM application WHERE uid IN (:applicationIds)")
    fun loadAllByIds(applicationIds: IntArray): List<Application>

    @Insert
    fun insertAll(vararg users: Application)

    @Delete
    fun delete(user: Application)
}