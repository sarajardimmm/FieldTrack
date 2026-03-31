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
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.fieldtrack.ui.theme.FieldTrackTheme

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
    digitsOnly: Boolean = false
) {
    Column {
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
            modifier = modifier.fillMaxWidth(),
            readOnly = readOnly,
            enabled = enabled,
            shape = MaterialTheme.shapes.medium,
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = MaterialTheme.colorScheme.primary,
                unfocusedBorderColor = MaterialTheme.colorScheme.outline
            ),
            keyboardOptions = KeyboardOptions(
                keyboardType = if (digitsOnly) KeyboardType.Number else KeyboardType.Text
            ),
        )
        if (errorMessage != null) {
            Text(
                text = errorMessage,
                color = MaterialTheme.colorScheme.error,
                style = MaterialTheme.typography.bodySmall,
                modifier = Modifier.padding(start = 16.dp, top = 4.dp)
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
        Surface {
            FormField("Name", {}, "Name")
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