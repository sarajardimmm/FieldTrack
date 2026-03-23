package com.example.fieldtrack.ui.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import com.example.fieldtrack.ui.theme.FieldTrackTheme

@Composable
fun FormField(
    value: String,
    onValueChange: (String) -> Unit,
    label: String,
    modifier: Modifier = Modifier,
    singleLine: Boolean = true,
    isError: Boolean = false,
    readOnly: Boolean = false,
    enabled: Boolean = true,
    digitsOnly: Boolean = false
) {
    OutlinedTextField(
        value = value,
        onValueChange = { input ->
            val filtered = if (digitsOnly) {
                input.filter { it.isDigit() }
            } else {
                input
            }
            onValueChange(filtered)
        },
        label = { Text(label) },
        singleLine = singleLine,
        isError = isError,
        modifier = modifier.fillMaxWidth(),
        readOnly = readOnly,
        enabled = enabled,
        keyboardOptions = KeyboardOptions(
            keyboardType = if (digitsOnly) KeyboardType.Number else KeyboardType.Text
        )
    )
}

@Preview
@Composable
fun FormFieldPreview() {
    FieldTrackTheme {
        Surface {
            FormField("Name", {}, "Name")
        }
    }
}