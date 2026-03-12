package com.example.fieldtrack.feature.applicationDetail

import android.util.Log
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable

@Composable
fun ApplicationDetailScreen(applicationId: String, onBackClick: () -> Unit) {
    Log.d("Sara", "ApplicationDetailScreen")
    Text(text = "Hello ApplicationDetailScreen!")
}