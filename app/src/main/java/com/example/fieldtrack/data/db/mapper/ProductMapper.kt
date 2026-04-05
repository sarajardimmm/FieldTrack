package com.example.fieldtrack.data.db.mapper

import com.example.fieldtrack.data.db.entity.ProductEntity
import com.example.fieldtrack.data.db.model.Product

fun ProductEntity.toDomain(): Product {
    return Product(
        id = id,
        name = name,
        category = category,
        defaultReapplyDays = defaultReapplyDays,
        storageLocation = storageLocation,
        notes = notes
    )
}