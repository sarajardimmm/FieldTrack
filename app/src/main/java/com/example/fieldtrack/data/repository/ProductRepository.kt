package com.example.fieldtrack.data.repository

import com.example.fieldtrack.data.db.dao.ProductDao
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class ProductRepository @Inject constructor(
    private val productDao: ProductDao
) {

    fun getAllProductNames() = productDao.getProducts().map { products ->
        products.map { it.name }.distinct()
    }
}