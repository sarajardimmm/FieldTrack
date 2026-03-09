package com.example.fieldtrack

import android.content.Context
import com.example.fieldtrack.data.db.AppDatabase
import com.example.fieldtrack.data.db.dao.ApplicationDao
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
    fun provideDao(database: AppDatabase): ApplicationDao {
        return database.applicationDao()
    }
}