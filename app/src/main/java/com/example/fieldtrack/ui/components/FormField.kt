package com.example.fieldtrack.ui.components

import android.content.res.Configuration
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import com.example.fieldtrack.ui.theme.FieldTrackTheme
import com.example.fieldtrack.ui.theme.LocalSpacing

@Composable
fun FormField(
    value: String,
    onValueChange: (String) -> Unit,
    label: String,
    modifier: Modifier = Modifier,
    singleLine: Boolean = true,
    errorMessage: String? = null,
    readOnly: Boolean = false,
    enabled: Boolean = true,
    digitsOnly: Boolean = false,
    trailingIcon: @Composable (() -> Unit)? = null,
    colors: TextFieldColors = OutlinedTextFieldDefaults.colors(
        focusedBorderColor = MaterialTheme.colorScheme.primary,
        unfocusedBorderColor = MaterialTheme.colorScheme.outline,
        errorBorderColor = MaterialTheme.colorScheme.error,
        focusedContainerColor = MaterialTheme.colorScheme.surface,
        unfocusedContainerColor = MaterialTheme.colorScheme.surface,
    )
) {
    val spacing = LocalSpacing.current
    
    Column(modifier = modifier) {
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
            isError = errorMessage != null,
            modifier = Modifier.fillMaxWidth(),
            readOnly = readOnly,
            enabled = enabled,
            shape = MaterialTheme.shapes.large, // More rounded corners for modern look
            colors = colors,
            trailingIcon = trailingIcon,
            keyboardOptions = KeyboardOptions(
                keyboardType = if (digitsOnly) KeyboardType.Number else KeyboardType.Text
            ),
        )
        if (errorMessage != null) {
            Text(
                text = errorMessage,
                color = MaterialTheme.colorScheme.error,
                style = MaterialTheme.typography.bodySmall,
                modifier = Modifier.padding(start = spacing.medium, top = spacing.extraSmall)
            )
        }
    }
}

@Preview(
    name = "Form Field - Light",
    showBackground = true,
    uiMode = Configuration.UI_MODE_NIGHT_NO
)
@Preview(
    name = "Form Field - Dark",
    showBackground = true,
    uiMode = Configuration.UI_MODE_NIGHT_YES
)
@Composable
fun FormFieldPreview() {
    FieldTrackTheme {
        val spacing = LocalSpacing.current
        Surface {
            FormField("Name", {}, "Name", modifier = Modifier.padding(spacing.medium))
        }
    }
}

@Preview(
    name = "Form Field - Light",
    showBackground = true,
    uiMode = Configuration.UI_MODE_NIGHT_NO
)
@Preview(
    name = "Form Field - Dark",
    showBackground = true,
    uiMode = Configuration.UI_MODE_NIGHT_YES
)
@Composable
fun FormFieldWithErrorPreview() {
    FieldTrackTheme {
        Surface {
            FormField("Name", {}, "Name", errorMessage = "Error")
        }
    }
}