package com.example.fieldtrack.data.repository

import com.example.fieldtrack.data.db.dao.ProductDao
import com.example.fieldtrack.data.db.entity.ProductEntity
import com.example.fieldtrack.data.db.mapper.toDomain
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class ProductRepository @Inject constructor(
    private val productDao: ProductDao
) {
    fun getProducts() = productDao.getProducts().map { products ->
        products.map { it.toDomain() }
    }

    fun getAllProductNames() = productDao.getProducts().map { products ->
        products.map { it.name }.distinct()
    }

    fun getProductByIdFlow(id: Long) =
        productDao.getByIdFlow(id).map { it?.toDomain() }

    suspend fun getProductById(id: Long) =
        productDao.getById(id)?.toDomain()

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

    suspend fun saveProduct(product: ProductEntity) {
        if (product.id == 0L) {
            productDao.insert(product)
        } else {
            productDao.update(product)
        }
    }
}