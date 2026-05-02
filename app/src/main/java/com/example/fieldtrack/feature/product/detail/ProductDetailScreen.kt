package com.example.fieldtrack.feature.product.detail

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.fieldtrack.R
import com.example.fieldtrack.data.db.model.LogEntry
import com.example.fieldtrack.data.db.model.Product
import com.example.fieldtrack.ui.components.HistoryItem
import com.example.fieldtrack.ui.theme.FieldTrackTheme
import com.example.fieldtrack.ui.theme.LocalSpacing

@Composable
fun ProductDetailScreen(
    product: Product?,
    logEntries: List<LogEntry>,
    onEditClick: (Long) -> Unit,
    onLogEntryClick: (Long) -> Unit,
    onNavigateBack: () -> Unit
) {
    ProductDetailContent(
        product = product,
        logEntries = logEntries,
        onEditClick = onEditClick,
        onLogEntryClick = onLogEntryClick,
        modifier = Modifier.fillMaxSize()
    )
}

@Composable
fun ProductDetailContent(
    product: Product?,
    logEntries: List<LogEntry>,
    onEditClick: (Long) -> Unit,
    onLogEntryClick: (Long) -> Unit,
    modifier: Modifier = Modifier
) {
    val spacing = LocalSpacing.current
    
    LazyColumn(
        modifier = modifier.fillMaxSize(),
        contentPadding = PaddingValues(spacing.medium),
        verticalArrangement = Arrangement.spacedBy(spacing.medium)
    ) {
        item {
            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = MaterialTheme.shapes.large,
                elevation = CardDefaults.cardElevation(defaultElevation = spacing.extraSmall),
                colors = CardDefaults.cardColors(
                    containerColor = MaterialTheme.colorScheme.surfaceContainerLow
                )
            ) {
                Column(
                    modifier = Modifier.padding(spacing.medium),
                    verticalArrangement = Arrangement.spacedBy(spacing.medium)
                ) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = stringResource(R.string.title_entry_details),
                            style = MaterialTheme.typography.titleLarge
                        )

                        Spacer(modifier = Modifier.weight(1f))

                        product?.let {
                            IconButton(
                                onClick = { onEditClick(it.id) },
                                modifier = Modifier.size(spacing.large)
                            ) {
                                Icon(
                                    imageVector = Icons.Default.Edit,
                                    contentDescription = stringResource(R.string.action_edit),
                                    tint = MaterialTheme.colorScheme.primary
                                )
                            }
                        }
                    }

                    HorizontalDivider(color = MaterialTheme.colorScheme.outlineVariant)

                    DetailItem(
                        label = stringResource(R.string.label_product),
                        value = product?.name
                    )

                    DetailItem(
                        label = stringResource(R.string.label_category),
                        value = product?.category
                    )

                    DetailItem(
                        label = stringResource(R.string.label_default_reapply_days),
                        value = product?.defaultReapplyDays?.let { stringResource(R.string.label_days, it) }
                    )

                    DetailItem(
                        label = stringResource(R.string.label_storage_location),
                        value = product?.storageLocation
                    )

                    DetailItem(
                        label = stringResource(R.string.label_notes),
                        value = product?.notes,
                        multiline = true
                    )
                }
            }
        }

        if (logEntries.isNotEmpty()) {
            item {
                Text(
                    text = stringResource(R.string.title_log_history),
                    style = MaterialTheme.typography.titleMedium,
                    modifier = Modifier.padding(top = spacing.small)
                )
            }

            items(logEntries) { logEntry ->
                HistoryItem(
                    logEntry = logEntry,
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable { onLogEntryClick(logEntry.id) }
                )
            }
        }
        item {
            Spacer(modifier = Modifier.height(spacing.medium))
        }
    }
}

@Composable
private fun DetailItem(
    label: String,
    value: String?,
    multiline: Boolean = false
) {
    val spacing = LocalSpacing.current
    Column(verticalArrangement = Arrangement.spacedBy(spacing.extraSmall)) {
        Text(
            text = label,
            style = MaterialTheme.typography.labelMedium,
            color = MaterialTheme.colorScheme.primary
        )

        Text(
            text = value?.takeIf { it.isNotBlank() } ?: "—",
            style = if (multiline) {
                MaterialTheme.typography.bodyLarge
            } else {
                MaterialTheme.typography.bodyMedium
            },
            color = MaterialTheme.colorScheme.onSurface
        )
    }
}

@Preview(showBackground = true)
@Composable
fun ProductDetailScreenPreview() {
    FieldTrackTheme {
        Surface {
            ProductDetailScreen(
                product = Product(
                    id = 1,
                    name = "Copper Sulfate",
                    category = "Fungicide",
                    defaultReapplyDays = 14,
                    storageLocation = "Shed B",
                    notes = "Wear protective gear."
                ),
                logEntries = emptyList(),
                onEditClick = {},
                onLogEntryClick = {},
                onNavigateBack = {}
            )
        }
    }
}
