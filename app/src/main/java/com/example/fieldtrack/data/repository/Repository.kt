package com.example.fieldtrack.data.repository

import com.example.fieldtrack.data.db.dao.ApplicationDao
import com.example.fieldtrack.data.db.entity.ApplicationEntity
import javax.inject.Inject

class Repository @Inject constructor(
    private val applicationDao: ApplicationDao
){
    fun getApplications() = applicationDao.getAll()

    suspend fun getApplication(id: String) = applicationDao.getById(id)
    suspend fun insert(applicationEntity: ApplicationEntity) {
        applicationDao.insert(applicationEntity)
    }
}