package com.example.fieldtrack.feature.applicationlist

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel

@Composable
fun ApplicationListRoute(
    onApplicationClick: (String) -> Unit,
    viewModel: ApplicationListViewModel = hiltViewModel()
) {
    val applications by viewModel.applications.collectAsState(initial = emptyList())


    ApplicationListScreen(
        onApplicationClick = onApplicationClick,
        onAddApplication = viewModel::addApplication,
        applicationHistory = applications
    )
}