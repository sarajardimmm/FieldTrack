package com.example.fieldtrack.data.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.fieldtrack.data.db.entity.ProductEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface ProductDao {
    @Query("SELECT * FROM products")
    fun getProducts(): Flow<List<ProductEntity>>

    @Query("SELECT * FROM products WHERE normalizedName = :normalizedName LIMIT 1")
    suspend fun getByNormalizedName(normalizedName: String): ProductEntity?

    @Insert
    suspend fun insert(zone: ProductEntity): Long
}