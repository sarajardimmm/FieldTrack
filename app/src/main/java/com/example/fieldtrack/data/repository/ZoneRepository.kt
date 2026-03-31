package com.example.fieldtrack.data.repository

import com.example.fieldtrack.data.db.dao.ZoneDao
import com.example.fieldtrack.data.db.entity.ZoneEntity
import javax.inject.Inject

class ZoneRepository @Inject constructor(
    private val zoneDao: ZoneDao
) {
    suspend fun getZones() = zoneDao.getZones()

    suspend fun getZoneByNormalizedName(normalizedName: String) =
        zoneDao.getByNormalizedName(normalizedName)

    suspend fun insertZone(zone: ZoneEntity) = zoneDao.insert(zone)

}


