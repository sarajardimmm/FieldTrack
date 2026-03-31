package com.example.fieldtrack.data.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.fieldtrack.data.db.entity.ZoneEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface ZoneDao {
    @Query("SELECT * FROM zones")
    fun getZones(): Flow<List<ZoneEntity>>

    @Query("SELECT * FROM zones WHERE normalizedName = :normalizedName LIMIT 1")
    suspend fun getByNormalizedName(normalizedName: String): ZoneEntity?

    @Insert
    suspend fun insert(zone: ZoneEntity): Long
}