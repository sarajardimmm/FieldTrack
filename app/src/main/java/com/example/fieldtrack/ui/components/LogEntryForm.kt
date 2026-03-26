package com.example.fieldtrack.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
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
            onDateSelected = { date ->
                onEvent(LogEntryEvent.AppliedAtChanged(date))
            },
            onDismiss = { showDatePicker = false }
        )
    }

    Surface(
        modifier = modifier
            .fillMaxWidth()
            .padding(16.dp),
        tonalElevation = 2.dp,
        shape = MaterialTheme.shapes.large
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
                .verticalScroll(rememberScrollState()),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Text(
                text = "Basic Info",
                style = MaterialTheme.typography.titleMedium
            )
            FormField(
                value = uiState.zoneName ?: "",
                errorMessage = uiState.zoneNameError,
                onValueChange = { onEvent(LogEntryEvent.ZoneChanged(it)) },
                label = "zone",
            )
            FormField(
                value = uiState.productName ?: "",
                errorMessage = uiState.productNameError,
                onValueChange = { onEvent(LogEntryEvent.ProductChanged(it)) },
                label = "product"
            )

            Box(modifier = Modifier.fillMaxWidth()) {
                Text(
                    text = "Applied At",
                    style = MaterialTheme.typography.labelMedium
                )

                FormField(
                    value = uiState.appliedAt.format(
                        DateTimeFormatter.ofPattern("dd MMM yyyy")
                    ),
                    onValueChange = {},
                    label = "",
                    readOnly = true
                )
                Box(
                    modifier = Modifier
                        .matchParentSize()
                        .clickable { showDatePicker = true }
                )

            }

            Text(
                text = "Details",
                style = MaterialTheme.typography.titleMedium
            )

            FormField(
                value = uiState.reapplyDays ?: "",
                onValueChange = { onEvent(LogEntryEvent.ReapplyDaysChanged(it)) },
                label = "reapply in days",
                digitsOnly = true
            )
            FormField(
                value = uiState.quantity ?: "",
                onValueChange = { onEvent(LogEntryEvent.QuantityChanged(it)) },
                label = "quantity",
            )
            FormField(
                value = uiState.notes ?: "",
                onValueChange = { onEvent(LogEntryEvent.NotesChanged(it)) },
                label = "notes",
            )
            Spacer(modifier = Modifier.height(8.dp))

            SingleButton(
                label = actionLabel,
                onClick = { onEvent(LogEntryEvent.SaveClicked) },
            )
        }
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
