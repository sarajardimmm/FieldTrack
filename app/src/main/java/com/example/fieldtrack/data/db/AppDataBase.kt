package com.example.fieldtrack.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.fieldtrack.data.db.dao.ApplicationDao
import com.example.fieldtrack.data.db.entity.ApplicationEntity

@Database(entities = [ApplicationEntity::class], version = 4, exportSchema = false)
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
                        .fallbackToDestructiveMigration(false)
                        .build()
                    INSTANCE = instance
                }
                return instance
            }
        }
    }
}