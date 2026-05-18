package com.example.fieldtrack.feature.product.detail

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle

@Composable
fun ProductDetailRoute(
    onEditClick: (Long) -> Unit,
    onLogEntryClick: (Long) -> Unit,
    onReapplyClick: (Long) -> Unit,
    onNavigateBack: () -> Unit,
    viewModel: ProductDetailViewModel = hiltViewModel()
) {
    val product by viewModel.product.collectAsStateWithLifecycle()
    val logEntries by viewModel.logEntries.collectAsStateWithLifecycle()

    ProductDetailScreen(
        product = product,
        logEntries = logEntries,
        onEditClick = onEditClick,
        onLogEntryClick = onLogEntryClick,
        onReapplyClick = onReapplyClick,
        onNavigateBack = onNavigateBack
    )
}
