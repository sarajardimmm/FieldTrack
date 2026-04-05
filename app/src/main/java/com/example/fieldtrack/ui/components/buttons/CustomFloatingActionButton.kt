package com.example.fieldtrack.ui.components.buttons

import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun CustomFloatingActionButton (onClick: () -> Unit, content: @Composable () -> Unit){
    FloatingActionButton(
        onClick = onClick,
        containerColor = MaterialTheme.colorScheme.tertiary,
    ) {
        content()
    }
}

@Preview(showBackground = true)
@Composable
fun CustomFloatingActionButtonPreview() {
    CustomFloatingActionButton(
        onClick = {},
        content = {}
    )
}