package com.example.fieldtrack.feature.applicationDetail

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable

@Composable
fun ApplicationDetailScreen(applicationId: String, onBackClick: () -> Unit) {
    Text(applicationId)
}