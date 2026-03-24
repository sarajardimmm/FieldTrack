package com.example.fieldtrack.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.fieldtrack.feature.logEntryForm.LogEntryEvent
import com.example.fieldtrack.feature.logEntryForm.LogEntryUiState
import com.example.fieldtrack.ui.components.LogEntrySamplePreviewData.logEntryUiStateSample
import com.example.fieldtrack.ui.theme.FieldTrackTheme
import java.time.format.DateTimeFormatter

@Composable
fun LogEntryForm(
    onEvent: (LogEntryEvent) -> Unit,
    actionLabel: String,
    modifier: Modifier = Modifier,
    uiState: LogEntryUiState,
) {

    var showDatePicker by remember { mutableStateOf(false) }

    if (showDatePicker) {
        FieldTrackDatePickerDialog(
            initialDate = uiState.appliedAt,
            onDateSelected = {date ->
                onEvent(LogEntryEvent.AppliedAtChanged(date))
            },
            onDismiss = { showDatePicker = false }
        )
    }

    Column(modifier.padding(12.dp)) {
        FormField(
            value = uiState.zoneName,
            onValueChange = { onEvent(LogEntryEvent.ZoneChanged(it)) },
            label = "zone",
        )
        FormField(
            value = uiState.productName ?: "",
            onValueChange = { onEvent(LogEntryEvent.ProductChanged(it))  },
            label = "product"
        )
        
        Box(modifier = Modifier.fillMaxWidth()) {
            FormField(
                value = uiState.appliedAt.format(DateTimeFormatter.ofPattern("dd MMM yyyy")) ?: "null",
                onValueChange = {},
                label = "applied at",
                readOnly = true,
                enabled = true
            )
            // Invisible overlay to catch clicks and prevent focus/keyboard
            Box(
                modifier = Modifier
                    .matchParentSize()
                    .clickable { showDatePicker = true }
            )
        }
        
        FormField(
            value = uiState.reapplyDays ?: "",
            onValueChange = { onEvent(LogEntryEvent.ReapplyDaysChanged(it)) },
            label = "reapply in days",
            digitsOnly = true
        )
        FormField(
            value = uiState.quantity ?: "",
            onValueChange = { onEvent(LogEntryEvent.QuantityChanged(it))  },
            label = "quantity",
        )
        FormField(
            value = uiState.notes ?: "",
            onValueChange = { onEvent(LogEntryEvent.NotesChanged(it))  },
            label = "notes",
        )

        SingleButton(
            label = actionLabel,
            onClick = { onEvent(LogEntryEvent.SaveClicked) }
        )
    }
}

@Preview
@Composable
fun AddLogEntryFormPreview() {
    FieldTrackTheme {
        Surface {
            LogEntryForm(
                onEvent = {},
                actionLabel = "Add",
                uiState = LogEntryUiState()
            )
        }
    }
}

@Preview
@Composable
fun EditLogEntryFormPreview() {

    FieldTrackTheme {
        Surface {
            LogEntryForm(
                onEvent = {},
                actionLabel = "Add",
                uiState = logEntryUiStateSample
            )
        }
    }
}
