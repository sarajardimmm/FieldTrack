package com.example.fieldtrack.feature.applicationDetail

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.fieldtrack.data.db.entity.ApplicationEntity
import com.example.fieldtrack.data.repository.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ApplicationDetailViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val repository: Repository
) : ViewModel() {

    private val applicationId: String =
            checkNotNull(savedStateHandle["applicationId"])
    var application by mutableStateOf<ApplicationEntity?>(null)
        private set

    init {
        viewModelScope.launch {
            application = repository.getApplication(applicationId)
        }
    }


}