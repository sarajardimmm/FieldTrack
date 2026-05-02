package com.example.fieldtrack.feature.zone.detail

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
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
import com.example.fieldtrack.data.db.model.Zone
import com.example.fieldtrack.ui.components.HistoryItem
import com.example.fieldtrack.ui.theme.FieldTrackTheme
import com.example.fieldtrack.ui.theme.LocalSpacing
import java.time.LocalDate

@Composable
fun ZoneDetailScreen(
    zone: Zone?,
    logEntries: List<LogEntry>,
    onEditClick: (Long) -> Unit,
    onLogEntryClick: (Long) -> Unit,
    onNavigateBack: () -> Unit
) {
    ZoneDetailContent(
        zone = zone,
        logEntries = logEntries,
        onEditClick = onEditClick,
        onLogEntryClick = onLogEntryClick,
        modifier = Modifier.fillMaxSize()
    )
}

@Composable
fun ZoneDetailContent(
    zone: Zone?,
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
                    containerColor = MaterialTheme.colorScheme.surfaceContainer
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
                            text = stringResource(R.string.title_zone_detail),
                            style = MaterialTheme.typography.titleLarge
                        )

                        Spacer(modifier = Modifier.weight(1f))

                        zone?.let {
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
                        label = stringResource(R.string.label_zone),
                        value = zone?.name
                    )

                    DetailItem(
                        label = stringResource(R.string.label_notes),
                        value = zone?.notes,
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
fun ZoneDetailScreenPreview() {
    FieldTrackTheme {
        Surface {
            ZoneDetailScreen(
                zone = Zone(id = 1, name = "North Orchard", notes = "Notes"),
                logEntries = listOf(
                    LogEntry(
                        id = 1,
                        zoneName = "North Orchard",
                        productName = "Product 1",
                        appliedAt = LocalDate.now(),
                        reapplyDays = 7,
                        quantity = "10L",
                        notes = "Notes"
                    )
                ),
                onEditClick = {},
                onLogEntryClick = {},
                onNavigateBack = {}
            )
        }
    }
}
