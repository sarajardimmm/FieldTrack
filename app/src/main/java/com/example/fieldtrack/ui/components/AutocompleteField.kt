package com.example.fieldtrack.ui.components

import android.content.res.Configuration
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MenuAnchorType
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.fieldtrack.ui.theme.FieldTrackTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AutocompleteField(
    label: String,
    value: String,
    suggestions: List<String>,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    errorMessage: String? = null,
) {
    var expanded by remember { mutableStateOf(false) }

    ExposedDropdownMenuBox(
        expanded = expanded,
        onExpandedChange = { expanded = it },
        modifier = modifier.fillMaxWidth()
    ) {
        FormField(
            value = value,
            onValueChange = {
                onValueChange(it)
                expanded = true
            },
            label = label,
            errorMessage = errorMessage,
            modifier = Modifier.menuAnchor(MenuAnchorType.PrimaryEditable, true),
            trailingIcon = {
                ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded)
            },
            colors = ExposedDropdownMenuDefaults.outlinedTextFieldColors(
                focusedBorderColor = MaterialTheme.colorScheme.primary,
                unfocusedBorderColor = MaterialTheme.colorScheme.outline,
                focusedLabelColor = MaterialTheme.colorScheme.primary,
            ),
        )

        if (suggestions.isNotEmpty()) {
            ExposedDropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false }
            ) {
                suggestions.forEach { suggestion ->
                    DropdownMenuItem(
                        text = { 
                            Text(
                                text = suggestion,
                                style = MaterialTheme.typography.bodyLarge
                            ) 
                        },
                        onClick = {
                            onValueChange(suggestion)
                            expanded = false
                        },
                        contentPadding = ExposedDropdownMenuDefaults.ItemContentPadding
                    )
                }
            }
        }
    }
}

@Preview(
    name = "Autocomplete - Light",
    showBackground = true,
    uiMode = Configuration.UI_MODE_NIGHT_NO
)
@Preview(
    name = "Autocomplete - Dark",
    showBackground = true,
    uiMode = Configuration.UI_MODE_NIGHT_YES
)
@Composable
fun AutocompleteFieldPreview() {
    FieldTrackTheme {
        Surface(modifier = Modifier.padding(16.dp)) {
            var value by remember { mutableStateOf("Nor") }
            val suggestions = listOf("North Orchard", "North Field", "North Gate")
            
            Column {
                AutocompleteField(
                    label = "Zone",
                    value = value,
                    suggestions = suggestions,
                    onValueChange = { value = it }
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun AutocompleteFieldEmptyPreview() {
    FieldTrackTheme {
        Surface(modifier = Modifier.padding(16.dp)) {
            AutocompleteField(
                label = "Zone",
                value = "",
                suggestions = emptyList(),
                onValueChange = {}
            )
        }
    }
}