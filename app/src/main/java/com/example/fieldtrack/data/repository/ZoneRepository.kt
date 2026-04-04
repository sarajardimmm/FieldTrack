package com.example.fieldtrack.data.repository

import com.example.fieldtrack.data.db.dao.ZoneDao
import com.example.fieldtrack.data.db.entity.ZoneEntity
import com.example.fieldtrack.data.db.mapper.toDomain
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class ZoneRepository @Inject constructor(
    private val zoneDao: ZoneDao
) {
    fun getZones() = zoneDao.getZones().map { zones ->
        zones.map { it.toDomain() }
    }

    fun getAllZoneNames() = zoneDao.getZones().map { zones ->
        zones.map { it.name }.distinct()
    }

    suspend fun getZoneById(id: Long) =
        zoneDao.getById(id)?.toDomain()

    fun getZoneByIdFlow(id: Long) =
        zoneDao.getByIdFlow(id).map { it?.toDomain() }

    suspend fun resolveZoneIdByName(name: String): Long {
        val trimmedName = name.trim()
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

    suspend fun insertZone(zone: ZoneEntity) = zoneDao.insert(zone)

    suspend fun updateZone(zone: ZoneEntity) = zoneDao.update(zone)
}