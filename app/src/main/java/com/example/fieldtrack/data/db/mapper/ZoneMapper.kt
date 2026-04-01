package com.example.fieldtrack.data.db.mapper

import com.example.fieldtrack.data.db.entity.ZoneEntity
import com.example.fieldtrack.data.db.model.Zone

fun ZoneEntity.toDomain(): Zone{
    return Zone(
        id = id,
        name = name,
        notes = notes)
}