package com.example.fieldtrack.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.fieldtrack.data.db.dao.ApplicationDao
import com.example.fieldtrack.data.db.entity.Application

@Database(entities = [Application::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun applicationDao(): ApplicationDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null
        fun getInstance(context: Context): AppDatabase {
            synchronized(this) {
                var instance = INSTANCE

                if (instance == null) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        AppDatabase::class.java,
                        "user_table"
                    )
                        .fallbackToDestructiveMigration()
                        .build()
                    INSTANCE = instance
                }
                return instance
            }
        }
    }
}