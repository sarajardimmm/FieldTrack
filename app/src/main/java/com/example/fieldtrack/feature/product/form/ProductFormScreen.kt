package com.example.fieldtrack.feature.product.form

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.fieldtrack.R
import com.example.fieldtrack.ui.components.FormContainer
import com.example.fieldtrack.ui.components.FormField
import com.example.fieldtrack.ui.components.buttons.SingleButton
import com.example.fieldtrack.ui.theme.FieldTrackTheme

@Composable
fun ProductFormScreen(
    uiState: ProductUiState,
    onEvent: (ProductEvent) -> Unit,
    onNavigateBack: () -> Unit
) {
    ProductFormContent(
        uiState = uiState,
        onEvent = onEvent,
        modifier = Modifier.fillMaxSize()
    )
}

@Composable
fun ProductFormContent(
    uiState: ProductUiState,
    onEvent: (ProductEvent) -> Unit,
    modifier: Modifier = Modifier
) {
    FormContainer(modifier = modifier) {
        Text(
            text = stringResource(R.string.header_basic_info),
            style = MaterialTheme.typography.titleMedium
        )

        FormField(
            value = uiState.name,
            onValueChange = { onEvent(ProductEvent.NameChanged(it)) },
            label = stringResource(R.string.label_product),
            errorMessage = uiState.nameErrorRes?.let { stringResource(it) }
        )

        FormField(
            value = uiState.category,
            onValueChange = { onEvent(ProductEvent.CategoryChanged(it)) },
            label = stringResource(R.string.label_category)
        )

        Text(
            text = stringResource(R.string.header_details),
            style = MaterialTheme.typography.titleMedium
        )

        FormField(
            value = uiState.defaultReapplyDays,
            onValueChange = { onEvent(ProductEvent.DefaultReapplyDaysChanged(it)) },
            label = stringResource(R.string.label_default_reapply_days),
            digitsOnly = true
        )

        FormField(
            value = uiState.storageLocation,
            onValueChange = { onEvent(ProductEvent.StorageLocationChanged(it)) },
            label = stringResource(R.string.label_storage_location)
        )

        FormField(
            value = uiState.notes,
            onValueChange = { onEvent(ProductEvent.NotesChanged(it)) },
            label = stringResource(R.string.label_notes)
        )

        Spacer(modifier = Modifier.height(8.dp))

        SingleButton(
            label = if (uiState.isEditing) {
                stringResource(R.string.action_edit)
            } else {
                stringResource(R.string.action_add)
            },
            onClick = { onEvent(ProductEvent.SaveClicked) },
            enabled = !uiState.isSaving,
            modifier = Modifier.fillMaxWidth()
        )
    }
}

@Preview(showBackground = true)
@Composable
fun ProductFormScreenPreview() {
    FieldTrackTheme {
        ProductFormScreen(
            uiState = ProductUiState(),
            onEvent = {},
            onNavigateBack = {}
        )
    }
}