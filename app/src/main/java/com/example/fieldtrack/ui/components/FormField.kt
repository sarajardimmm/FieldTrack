package com.example.fieldtrack.ui.components

import androidx.compose.foundation.text.input.TextFieldLineLimits
import androidx.compose.foundation.text.input.rememberTextFieldState
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable

@Composable
fun FormField (){
    OutlinedTextField(
        state = rememberTextFieldState(initialText = "Hello"),
        label = { Text("zone") },
        lineLimits = TextFieldLineLimits.SingleLine
    )
}