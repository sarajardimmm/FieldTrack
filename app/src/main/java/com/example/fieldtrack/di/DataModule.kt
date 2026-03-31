package com.example.fieldtrack.di

import android.content.Context
import com.example.fieldtrack.data.db.AppDatabase
import com.example.fieldtrack.data.db.dao.LogEntryDao
import com.example.fieldtrack.data.db.dao.ProductDao
import com.example.fieldtrack.data.db.dao.ZoneDao
import com.example.fieldtrack.data.repository.LogEntryRepository
import com.example.fieldtrack.data.repository.ProductRepository
import com.example.fieldtrack.data.repository.ZoneRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataModule {

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): AppDatabase =
        AppDatabase.getInstance(context)

    @Provides
    fun provideLogEntryDao(database: AppDatabase): LogEntryDao {
        return database.logEntryDao()
    }

    @Provides
    fun provideZoneDao(database: AppDatabase): ZoneDao {
        return database.zoneDao()
    }

    @Provides
    fun provideProductDao(database: AppDatabase): ProductDao {
        return database.productDao()
    }

    @Provides
    @Singleton
    fun provideLogEntryRepository(
        database: AppDatabase,
        logEntryDao: LogEntryDao,
        zoneDao: ZoneDao,
        productDao: ProductDao
    ): LogEntryRepository {
        return LogEntryRepository(database, logEntryDao, zoneDao, productDao)
    }

    @Provides
    @Singleton
    fun provideProductRepository(
        productDao: ProductDao
    ): ProductRepository {
        return ProductRepository(productDao)
    }

    @Provides
    @Singleton
    fun provideZoneRepository(
        zoneDao: ZoneDao
    ): ZoneRepository {
        return ZoneRepository(zoneDao)
    }
}