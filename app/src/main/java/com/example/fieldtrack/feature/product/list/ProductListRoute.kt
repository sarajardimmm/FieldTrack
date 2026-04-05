package com.example.fieldtrack.feature.product.list

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle

@Composable
fun ProductListRoute(
    onProductClick: (Long) -> Unit,
    onNavigateBack: () -> Unit,
    viewModel: ProductListViewModel = hiltViewModel()
) {
    val products by viewModel.products.collectAsStateWithLifecycle()
    
    ProductListScreen(
        onNavigateBack = onNavigateBack,
        onProductClick = onProductClick,
        products = products
    )
}