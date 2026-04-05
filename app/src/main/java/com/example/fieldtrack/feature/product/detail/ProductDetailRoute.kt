package com.example.fieldtrack.feature.product.detail

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle

@Composable
fun ProductDetailRoute(
    onEditClick: (Long) -> Unit,
    onNavigateBack: () -> Unit,
    viewModel: ProductDetailViewModel = hiltViewModel()
) {
    val product by viewModel.product.collectAsStateWithLifecycle()

    ProductDetailScreen(
        product = product,
        onEditClick = onEditClick,
        onNavigateBack = onNavigateBack
    )
}