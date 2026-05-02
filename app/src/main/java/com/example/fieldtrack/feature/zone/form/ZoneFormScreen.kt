package com.example.fieldtrack.feature.zone.form

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.fieldtrack.R
import com.example.fieldtrack.ui.components.FormContainer
import com.example.fieldtrack.ui.components.FormField
import com.example.fieldtrack.ui.components.buttons.SingleButton
import com.example.fieldtrack.ui.theme.FieldTrackTheme
import com.example.fieldtrack.ui.theme.LocalSpacing

@Composable
fun ZoneFormScreen(
    uiState: ZoneUiState,
    onEvent: (ZoneEvent) -> Unit,
    onNavigateBack: () -> Unit
) {
    ZoneFormContent(
        uiState = uiState,
        onEvent = onEvent,
        modifier = Modifier.fillMaxSize()
    )
}

@Composable
fun ZoneFormContent(
    uiState: ZoneUiState,
    onEvent: (ZoneEvent) -> Unit,
    modifier: Modifier = Modifier
) {
    val spacing = LocalSpacing.current
    
    FormContainer(modifier = modifier) {
        Text(
            text = stringResource(R.string.header_basic_info),
            style = MaterialTheme.typography.titleMedium
        )

        FormField(
            value = uiState.name,
            onValueChange = { onEvent(ZoneEvent.NameChanged(it)) },
            label = stringResource(R.string.label_zone),
            errorMessage = uiState.nameErrorRes?.let { stringResource(it) }
        )

        Text(
            text = stringResource(R.string.header_details),
            style = MaterialTheme.typography.titleMedium
        )

        FormField(
            value = uiState.notes,
            onValueChange = { onEvent(ZoneEvent.NotesChanged(it)) },
            label = stringResource(R.string.label_notes)
        )

        Spacer(modifier = Modifier.height(spacing.small))

        SingleButton(
            label = if (uiState.isEditing) {
                stringResource(R.string.action_edit)
            } else {
                stringResource(R.string.action_add)
            },
            onClick = { onEvent(ZoneEvent.SaveClicked) },
            enabled = !uiState.isSaving,
            modifier = Modifier.fillMaxWidth()
        )
    }
}

@Preview(showBackground = true)
@Composable
fun ZoneFormScreenPreview() {
    FieldTrackTheme {
        ZoneFormScreen(
            uiState = ZoneUiState(),
            onEvent = {},
            onNavigateBack = {}
        )
    }
}