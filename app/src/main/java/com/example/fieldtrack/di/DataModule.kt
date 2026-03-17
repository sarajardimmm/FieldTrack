package com.example.fieldtrack.di

import android.content.Context
import com.example.fieldtrack.data.db.AppDatabase
import com.example.fieldtrack.data.db.dao.LogEntryDao
import com.example.fieldtrack.data.repository.Repository
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
    fun provideDao(database: AppDatabase): LogEntryDao {
        return database.logEntryDao()
    }

    @Provides
    @Singleton
    fun provideApplicationRepository(
        logEntryDao: LogEntryDao
    ): Repository {
        return Repository(logEntryDao)
    }
}