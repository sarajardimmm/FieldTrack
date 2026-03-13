package com.example.fieldtrack.feature.applicationlist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.fieldtrack.data.db.entity.ApplicationEntity
import com.example.fieldtrack.data.repository.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ApplicationListViewModel @Inject constructor(
    private val repository: Repository
) : ViewModel() {

    val applications = repository.getApplications()

    fun addApplication(
        applicationFormData: ApplicationFormData
    ) {
        val application = ApplicationEntity(
            zoneName = applicationFormData.zoneName,
            productName = applicationFormData.productName,
            appliedAt = applicationFormData.appliedAt,
            reapplyDays = applicationFormData.reapplyDays,
            quantity = applicationFormData.quantity,
            notes = applicationFormData.notes
        )

        viewModelScope.launch {
            repository.insert(application)
        }
    }

    //This is the activity/history screen.

    //A list of all applications across all zones:
    //product
    //zone
    //date
    //notes
    //due date

    //Actions:
    //
    //add application
    //edit application
    //delete application

}