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
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.fieldtrack.R
import com.example.fieldtrack.data.db.model.LogEntry
import com.example.fieldtrack.data.db.model.Zone
import com.example.fieldtrack.ui.components.AppTopBar
import com.example.fieldtrack.ui.components.HistoryItem
import com.example.fieldtrack.ui.theme.FieldTrackTheme
import java.time.LocalDate

@Composable
fun ZoneDetailScreen(
    zone: Zone?,
    logEntries: List<LogEntry>,
    onEditClick: (Long) -> Unit,
    onLogEntryClick: (Long) -> Unit,
    onNavigateBack: () -> Unit
) {
    Scaffold(
        topBar = {
            AppTopBar(
                title = stringResource(R.string.title_zone_detail),
                onBack = onNavigateBack
            )
        }
    ) { innerPadding ->
        ZoneDetailContent(
            zone = zone,
            logEntries = logEntries,
            onEditClick = onEditClick,
            onLogEntryClick = onLogEntryClick,
            modifier = Modifier.padding(innerPadding)
        )
    }
}

@Composable
fun ZoneDetailContent(
    zone: Zone?,
    logEntries: List<LogEntry>,
    onEditClick: (Long) -> Unit,
    onLogEntryClick: (Long) -> Unit,
    modifier: Modifier = Modifier
) {
    LazyColumn(
        modifier = modifier.fillMaxSize(),
        contentPadding = PaddingValues(horizontal = 16.dp, vertical = 12.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        item {
            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = MaterialTheme.shapes.large,
                elevation = CardDefaults.cardElevation(defaultElevation = 3.dp),
                colors = CardDefaults.cardColors(
                    containerColor = MaterialTheme.colorScheme.surfaceVariant
                )
            ) {
                Column(
                    modifier = Modifier.padding(20.dp),
                    verticalArrangement = Arrangement.spacedBy(20.dp)
                ) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = stringResource(R.string.title_zone_detail),
                            style = MaterialTheme.typography.titleLarge,
                            fontWeight = FontWeight.SemiBold
                        )

                        Spacer(modifier = Modifier.weight(1f))

                        zone?.let {
                            IconButton(
                                onClick = { onEditClick(it.id) },
                                modifier = Modifier.padding(start = 4.dp).size(24.dp)
                            ) {
                                Icon(
                                    imageVector = Icons.Default.Edit,
                                    contentDescription = stringResource(R.string.action_edit),
                                    tint = MaterialTheme.colorScheme.primary,
                                    modifier = Modifier.size(20.dp)
                                )
                            }
                        }
                    }

                    HorizontalDivider()

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
                    modifier = Modifier.padding(top = 8.dp)
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
    Column(verticalArrangement = Arrangement.spacedBy(6.dp)) {
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
