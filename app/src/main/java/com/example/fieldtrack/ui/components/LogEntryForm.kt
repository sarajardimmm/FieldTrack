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
import com.example.fieldtrack.feature.logentryhistory.LogEntryFormData
import com.example.fieldtrack.ui.theme.FieldTrackTheme
import java.time.LocalDate
import java.time.format.DateTimeFormatter

@Composable
fun LogEntryForm(
    actionLabel: String,
    onLogEntryAction: (LogEntryFormData) -> Unit,
    modifier: Modifier = Modifier,
    logEntryFormData: LogEntryFormData? = null
) {
    var zoneName by remember { mutableStateOf(logEntryFormData?.zoneName ?: "") }
    var productName by remember { mutableStateOf(logEntryFormData?.productName ?: "") }
    var appliedAt by remember { mutableStateOf(logEntryFormData?.appliedAt ?: LocalDate.now()) }
    var reapplyDays by remember { mutableStateOf(logEntryFormData?.reapplyDays ?: "") }
    var quantity by remember { mutableStateOf(logEntryFormData?.quantity ?: "") }
    var notes by remember { mutableStateOf(logEntryFormData?.notes ?: "") }

    var showDatePicker by remember { mutableStateOf(false) }

    if (showDatePicker) {
        FieldTrackDatePickerDialog(
            initialDate = appliedAt,
            onDateSelected = { appliedAt = it },
            onDismiss = { showDatePicker = false }
        )
    }

    Column(modifier.padding(12.dp)) {
        FormField(
            value = zoneName,
            onValueChange = { zoneName = it },
            label = "zone",
        )
        FormField(
            value = productName,
            onValueChange = { productName = it },
            label = "product"
        )
        
        Box(modifier = Modifier.fillMaxWidth()) {
            FormField(
                value = appliedAt.format(DateTimeFormatter.ofPattern("dd MMM yyyy")),
                onValueChange = { },
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
            value = reapplyDays,
            onValueChange = { reapplyDays = it },
            label = "reapply in",
        )
        FormField(
            value = quantity,
            onValueChange = { quantity = it },
            label = "quantity",
        )
        FormField(
            value = notes,
            onValueChange = { notes = it },
            label = "notes",
        )

        SingleButton(
            label = actionLabel,
            onClick = {
                onLogEntryAction(
                    LogEntryFormData(
                        zoneName, productName, appliedAt, reapplyDays, quantity, notes
                    )
                )
            })
    }
}

@Preview
@Composable
fun AddLogEntryFormPreview() {
    FieldTrackTheme {
        Surface {
            LogEntryForm("Add", {})
        }
    }
}

@Preview
@Composable
fun EditLogEntryFormPreview() {
    val logEntryFormData = LogEntryFormData(
        "Front garden",
        "adubo",
        LocalDate.of(2025, 12, 5),
        "5 Apr 2026",
        "quantity",
        "neighbour gave us the adubo"
    )

    FieldTrackTheme {
        Surface {
            LogEntryForm("Edit", {}, logEntryFormData = logEntryFormData)
        }
    }
}
