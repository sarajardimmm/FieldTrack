package com.example.fieldtrack.ui.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun SingleButton(label: String, onClick: () -> Unit, modifier: Modifier = Modifier) {
    Button(
        modifier = modifier.fillMaxWidth(),
        onClick = onClick,
    ) { Text(label) }
}