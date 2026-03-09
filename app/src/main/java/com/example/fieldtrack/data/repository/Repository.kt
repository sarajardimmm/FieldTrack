package com.example.fieldtrack.data.repository

import com.example.fieldtrack.data.db.dao.ApplicationDao
import javax.inject.Inject

class Repository @Inject constructor(
    private val applicationDao: ApplicationDao
){

    fun getApplications() = applicationDao.getAll()

}