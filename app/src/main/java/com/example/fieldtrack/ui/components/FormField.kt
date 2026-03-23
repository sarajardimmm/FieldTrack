package com.example.fieldtrack.ui.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.fieldtrack.ui.theme.FieldTrackTheme

@Composable
fun FormField (
    value: String,
    onValueChange : (String) -> Unit,
    label: String,
    modifier: Modifier = Modifier,
    singleLine: Boolean = true,
    isError: Boolean = false,
    readOnly: Boolean = false,
    enabled: Boolean = true
    ){
    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        label = { Text(label) },
        singleLine = singleLine,
        isError = isError,
        modifier = modifier.fillMaxWidth(),
        shape = RoundedCornerShape(12.dp),
        readOnly = readOnly,
        enabled = enabled
        )
}

@Preview
@Composable
fun FormFieldPreview() {
    FieldTrackTheme{
        Surface{
            FormField("Name", {}, "Name")
        }
    }
}