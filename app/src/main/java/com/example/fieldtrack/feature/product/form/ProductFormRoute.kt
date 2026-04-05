package com.example.fieldtrack.feature.product.form

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
fun ProductFormRoute(
    onNavigateBack: () -> Unit,
    viewModel: ProductFormViewModel = hiltViewModel()
) {
    val uiState = viewModel.uiState

    LaunchedEffect(uiState.isSaveSuccess) {
        if (uiState.isSaveSuccess) {
            onNavigateBack()
        }
    }

    ProductFormScreen(
        uiState = uiState,
        onEvent = { viewModel.onEvent(it) },
        onNavigateBack = onNavigateBack
    )
}