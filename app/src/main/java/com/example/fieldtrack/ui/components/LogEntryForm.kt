package com.example.fieldtrack.ui.components

import android.content.res.Configuration
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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.fieldtrack.R
import com.example.fieldtrack.feature.logentry.form.LogEntryEvent
import com.example.fieldtrack.feature.logentry.form.LogEntryUiState
import com.example.fieldtrack.ui.components.LogEntrySamplePreviewData.logEntryUiStateSample
import com.example.fieldtrack.ui.components.buttons.SingleButton
import com.example.fieldtrack.ui.theme.FieldTrackTheme
import java.time.format.DateTimeFormatter
import java.time.format.FormatStyle

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
            modifier = Modifier
                .padding(16.dp)
                .verticalScroll(rememberScrollState()),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Text(
                text = stringResource(R.string.header_basic_info),
                style = MaterialTheme.typography.titleMedium
            )
            
            AutocompleteField(
                label = stringResource(R.string.label_zone),
                value = uiState.zoneName,
                suggestions = uiState.zoneSuggestions,
                onValueChange = { onEvent(LogEntryEvent.ZoneChanged(it)) },
                errorMessage = uiState.zoneNameErrorRes?.let { stringResource(it) }
            )

            AutocompleteField(
                label = stringResource(R.string.label_product),
                value = uiState.productName,
                suggestions = uiState.productSuggestions,
                onValueChange = { onEvent(LogEntryEvent.ProductChanged(it)) },
                errorMessage = uiState.productNameErrorRes?.let { stringResource(it) }
            )

            Box(modifier = Modifier.fillMaxWidth()) {
                Text(
                    text = stringResource(R.string.label_applied_at),
                    style = MaterialTheme.typography.labelMedium
                )

                FormField(
                    value = uiState.appliedAt.format(
                        DateTimeFormatter.ofLocalizedDate(FormatStyle.MEDIUM)
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
                text = stringResource(R.string.header_details),
                style = MaterialTheme.typography.titleMedium
            )

            FormField(
                value = uiState.reapplyDays ?: "",
                onValueChange = { onEvent(LogEntryEvent.ReapplyDaysChanged(it)) },
                label = stringResource(R.string.label_reapply_in_days),
                digitsOnly = true
            )
            FormField(
                value = uiState.quantity ?: "",
                onValueChange = { onEvent(LogEntryEvent.QuantityChanged(it)) },
                label = stringResource(R.string.label_quantity),
            )
            FormField(
                value = uiState.notes ?: "",
                onValueChange = { onEvent(LogEntryEvent.NotesChanged(it)) },
                label = stringResource(R.string.label_notes),
            )
            Spacer(modifier = Modifier.height(8.dp))

            SingleButton(
                label = actionLabel,
                onClick = { onEvent(LogEntryEvent.SaveClicked) },
                modifier = Modifier.fillMaxWidth()
            )
        }
    }
}

@Preview(
    name = "Log Entry Form - Light",
    showBackground = true,
    uiMode = Configuration.UI_MODE_NIGHT_NO,
    locale = "en"
)
@Preview(
    name = "Log Entry Form - Dark",
    showBackground = true,
    uiMode = Configuration.UI_MODE_NIGHT_YES,
    locale = "en"
)
@Composable
fun AddLogEntryFormPreview() {
    FieldTrackTheme {
        Surface {
            LogEntryForm(
                onEvent = {},
                actionLabel = stringResource(R.string.action_add),
                uiState = LogEntryUiState()
            )
        }
    }
}

@Preview(locale = "en")
@Composable
fun EditLogEntryFormPreview() {
    FieldTrackTheme {
        Surface {
            LogEntryForm(
                onEvent = {},
                actionLabel = stringResource(R.string.action_add),
                uiState = logEntryUiStateSample
            )
        }
    }
}