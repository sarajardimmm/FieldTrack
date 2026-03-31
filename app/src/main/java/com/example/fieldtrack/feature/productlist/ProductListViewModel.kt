package com.example.fieldtrack.feature.productlist

import androidx.lifecycle.ViewModel
import com.example.fieldtrack.data.repository.LogEntryRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ProductListViewModel @Inject constructor(
    private val logEntryRepository: LogEntryRepository
) : ViewModel() {
    //Each product can have:
    //name
    //type/category: insecticide, fungicide, fertilizer/adubo, herbicide, other
    //default reapply interval in days
    //notes
    //maybe storage location later
    //
    //Actions:
    //
    //add product
    //edit product
    //delete product
    //open product details
}