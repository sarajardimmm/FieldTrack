package com.example.fieldtrack.data.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.fieldtrack.data.db.entity.ZoneEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface ZoneDao {
    @Query("SELECT * FROM zones")
    fun getZones(): Flow<List<ZoneEntity>>

    @Query("SELECT * FROM zones WHERE id = :id LIMIT 1")
    suspend fun getById(id: Long): ZoneEntity?

    @Query("SELECT * FROM zones WHERE id = :id LIMIT 1")
    fun getByIdFlow(id: Long): Flow<ZoneEntity?>

    @Query("SELECT * FROM zones WHERE normalizedName = :normalizedName LIMIT 1")
    suspend fun getByNormalizedName(normalizedName: String): ZoneEntity?

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(zone: ZoneEntity): Long

    @Update
    suspend fun update(zone: ZoneEntity)

    @Query("DELETE FROM zones WHERE id = :id")
    suspend fun delete(id: Long)
}