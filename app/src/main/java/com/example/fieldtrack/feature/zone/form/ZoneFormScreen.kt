package com.example.fieldtrack.feature.zone.form

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.fieldtrack.R
import com.example.fieldtrack.ui.components.AppTopBar
import com.example.fieldtrack.ui.components.FormField
import com.example.fieldtrack.ui.components.buttons.SingleButton
import com.example.fieldtrack.ui.theme.FieldTrackTheme

@Composable
fun ZoneFormScreen(
    uiState: ZoneUiState,
    onEvent: (ZoneEvent) -> Unit,
    onNavigateBack: () -> Unit
) {
    Scaffold(
        topBar = {
            AppTopBar(
                title = if (uiState.isEditing) {
                    stringResource(R.string.title_edit_zone)
                } else {
                    stringResource(R.string.title_add_zone)
                },
                onBack = onNavigateBack
            )
        }
    ) { innerPadding ->
        ZoneFormContent(
            uiState = uiState,
            onEvent = onEvent,
            modifier = Modifier.padding(innerPadding)
        )
    }
}

@Composable
fun ZoneFormContent(
    uiState: ZoneUiState,
    onEvent: (ZoneEvent) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .navigationBarsPadding()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Surface(
            tonalElevation = 2.dp,
            shape = MaterialTheme.shapes.large,
            modifier = Modifier.fillMaxWidth()
        ) {
            Column(
                modifier = Modifier.padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
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

                Spacer(modifier = Modifier.height(8.dp))

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