package com.example.fieldtrack.feature.applicationlist

import androidx.lifecycle.ViewModel
import com.example.fieldtrack.data.repository.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ApplicationListViewModel @Inject constructor(
    private val repository: Repository
) : ViewModel() {
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