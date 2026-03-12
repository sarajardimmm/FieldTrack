package com.example.fieldtrack.feature.applicationlist

import androidx.compose.runtime.Composable
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel

@Composable
fun ApplicationListRoute(
    onApplicationClick: (String) -> Unit,
    viewModel: ApplicationListViewModel = hiltViewModel()
) {

    ApplicationListScreen(
        onApplicationClick = onApplicationClick,
        //state = viewModel.uiState
    )
}