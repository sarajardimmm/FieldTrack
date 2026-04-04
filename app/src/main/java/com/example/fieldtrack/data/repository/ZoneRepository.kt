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

    suspend fun getZoneById(id: Long) =
        zoneDao.getById(id)?.toDomain()

    suspend fun getZoneByNormalizedName(normalizedName: String) =
        zoneDao.getByNormalizedName(normalizedName)?.toDomain()


    suspend fun insertZone(zone: ZoneEntity) = zoneDao.insert(zone)

}
