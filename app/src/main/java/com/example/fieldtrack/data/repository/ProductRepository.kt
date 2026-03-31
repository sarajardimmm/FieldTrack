package com.example.fieldtrack.data.repository

import com.example.fieldtrack.data.db.dao.ProductDao
import com.example.fieldtrack.data.db.entity.ProductEntity
import javax.inject.Inject

class ProductRepository @Inject constructor(
    private val productDao: ProductDao
) {
    suspend fun getProducts() = productDao.getProducts()

    suspend fun getProductByNormalizedName(normalizedName: String) =
        productDao.getByNormalizedName(normalizedName)

    suspend fun insertProduct(product: ProductEntity) = productDao.insert(product)

}