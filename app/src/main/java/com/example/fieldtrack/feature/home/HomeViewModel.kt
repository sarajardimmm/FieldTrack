package com.example.fieldtrack.feature.home

import androidx.lifecycle.ViewModel
import com.example.fieldtrack.data.repository.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val repository: Repository
) : ViewModel() {
    //Should show:
    //overdue applications
    //due soon
    //maybe recently applied
    //
    //A card here could say:
    //
    //Zone: Orchard
    //Product: Fungicide X
    //Last applied: Mar 2
    //Due: Mar 16
    //
    //From here user can tap into details or quickly log a new application.
}