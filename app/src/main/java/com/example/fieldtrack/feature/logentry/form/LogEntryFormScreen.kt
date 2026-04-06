package com.example.fieldtrack.feature.logentry.form

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.fieldtrack.R
import com.example.fieldtrack.ui.components.AppTopBar
import com.example.fieldtrack.ui.components.AutocompleteField
import com.example.fieldtrack.ui.components.FieldTrackDatePickerDialog
import com.example.fieldtrack.ui.components.FormContainer
import com.example.fieldtrack.ui.components.FormField
import com.example.fieldtrack.ui.components.buttons.SingleButton
import java.time.format.DateTimeFormatter
import java.time.format.FormatStyle

@Composable
fun LogEntryFormScreen(
    uiState: LogEntryUiState,
    onEvent: (LogEntryEvent) -> Unit,
    onNavigateBack: () -> Unit,
) {
    Scaffold(
        topBar = {
            AppTopBar(
                title = if (uiState.isEditing) {
                    stringResource(R.string.title_edit_log_form)
                } else {
                    stringResource(R.string.title_log_form)
                },
                onBack = onNavigateBack
            )
        }
    ) { innerPadding ->
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

        FormContainer(modifier = Modifier.padding(innerPadding)) {
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
                label = if (uiState.isEditing) {
                    stringResource(R.string.action_edit)
                } else {
                    stringResource(R.string.action_add)
                },
                onClick = { onEvent(LogEntryEvent.SaveClicked) },
                modifier = Modifier.fillMaxWidth()
            )
        }
    }
}