package com.example.fieldtrack.data.repository

import com.example.fieldtrack.data.db.dao.ProductDao
import com.example.fieldtrack.data.db.entity.ProductEntity
import com.example.fieldtrack.data.db.mapper.toDomain
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class ProductRepository @Inject constructor(
    private val productDao: ProductDao
) {
    fun getAllProductNames() = productDao.getProducts().map { products ->
        products.map { it.name }.distinct()
    }

    suspend fun resolveProductIdByName(name: String): Long {
        val trimmedName = name.trim()
        val normalizedName = trimmedName.lowercase()

        val existingProduct = productDao.getByNormalizedName(normalizedName)
        if (existingProduct != null) return existingProduct.id

        return productDao.insert(
            ProductEntity(
                name = trimmedName,
                normalizedName = normalizedName,
                category = null,
                defaultReapplyDays = null,
                storageLocation = null,
                notes = null
            )
        )
    }

    suspend fun insertProduct(product: ProductEntity) = productDao.insert(product)
}