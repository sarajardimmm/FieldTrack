package com.example.fieldtrack.feature.logEntryForm

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.example.fieldtrack.R
import com.example.fieldtrack.ui.components.AppTopBar
import com.example.fieldtrack.ui.components.LogEntryForm

@Composable
fun LogEntryFormScreen(
    uiState: LogEntryUiState,
    onEvent: (LogEntryEvent) -> Unit,
    onNavigateBack: () -> Unit,
) {
    Scaffold(
        topBar = {
            AppTopBar(stringResource(R.string.title_log_form), onBack = onNavigateBack)
        }
    ) { innerPadding ->
        Column(Modifier.padding(innerPadding)) {
            LogEntryForm(
                uiState = uiState,
                actionLabel = "Add",
                onEvent = onEvent,
            )
        }
    }
}