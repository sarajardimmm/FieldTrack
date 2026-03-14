package com.example.fieldtrack.ui.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun FormField (
    value: String,
    onValueChange : (String) -> Unit,
    label: String,
    singleLine: Boolean = true,
    isError: Boolean = false,
    ){
    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        label = { Text(label) },
        singleLine = singleLine,
        isError = isError,
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(12.dp),
        )
}