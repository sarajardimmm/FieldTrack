package com.example.fieldtrack.feature.logentry.history

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
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
import com.example.fieldtrack.ui.components.EmptyStateCard
import com.example.fieldtrack.ui.components.HistoryItem
import com.example.fieldtrack.ui.components.LogEntrySamplePreviewData.logEntryEntityListSample
import com.example.fieldtrack.ui.theme.FieldTrackTheme
import com.example.fieldtrack.ui.theme.LocalSpacing
import java.time.LocalDate

@Composable
fun LogEntryListScreen(
    onLogEntryClick: (Long) -> Unit,
    uiState: LogEntryListUiState
) {
    LogEntryListContent(
        onLogEntryClick = onLogEntryClick,
        uiState = uiState,
        modifier = Modifier.fillMaxSize()
    )
}

@Composable
fun LogEntryListContent(
    onLogEntryClick: (Long) -> Unit,
    uiState: LogEntryListUiState,
    modifier: Modifier = Modifier
) {
    val spacing = LocalSpacing.current
    
    if (uiState.history.isEmpty() && uiState.pendingReapplies.isEmpty()) {
        EmptyStateCard(
            title = stringResource(R.string.title_no_log_entries),
            description = stringResource(R.string.description_no_log_entries),
            modifier = modifier.padding(spacing.medium)
        )
    } else {
        LazyColumn(
            modifier = modifier.fillMaxSize(),
            contentPadding = PaddingValues(spacing.medium, 0.dp),
            verticalArrangement = Arrangement.spacedBy(spacing.small),
        ) {
            if (uiState.pendingReapplies.isNotEmpty()) {
                item {
                    Text(
                        text = stringResource(R.string.title_pending_reapplies),
                        style = MaterialTheme.typography.titleMedium,
                        modifier = Modifier.padding(vertical = spacing.small)
                    )
                }
                items(uiState.pendingReapplies) { pending ->
                    PendingReapplyItem(
                        pending = pending,
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable { onLogEntryClick(pending.logEntry.id) }
                    )
                }
                item {
                    Text(
                        text = stringResource(R.string.title_log_history),
                        style = MaterialTheme.typography.titleMedium,
                        modifier = Modifier.padding(top = spacing.medium, bottom = spacing.small)
                    )
                }
            }

            items(uiState.history) { logEntry ->
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
fun PendingReapplyItem(
    pending: PendingReapply,
    modifier: Modifier = Modifier
) {
    val spacing = LocalSpacing.current
    val color = if (pending.isOverdue) MaterialTheme.colorScheme.error else MaterialTheme.colorScheme.primary

    Surface(
        modifier = modifier,
        shape = MaterialTheme.shapes.medium,
        color = MaterialTheme.colorScheme.surfaceContainer,
        tonalElevation = 1.dp
    ) {
        Row(
            modifier = Modifier
                .padding(spacing.medium)
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = pending.logEntry.zoneName,
                    style = MaterialTheme.typography.bodyLarge,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = pending.logEntry.productName,
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
            
            Text(
                text = if (pending.isOverdue) {
                    stringResource(R.string.label_overdue)
                } else {
                    stringResource(R.string.label_due_in_days, pending.daysRemaining)
                },
                style = MaterialTheme.typography.labelLarge,
                color = color,
                fontWeight = FontWeight.Bold
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun LogEntryListScreenPreview() {
    FieldTrackTheme {
        Surface {
            LogEntryListScreen(
                onLogEntryClick = {},
                uiState = LogEntryListUiState(
                    history = logEntryEntityListSample,
                    pendingReapplies = listOf(
                        PendingReapply(
                            logEntry = logEntryEntityListSample[0],
                            dueDate = LocalDate.now().minusDays(2),
                            daysRemaining = -2,
                            isOverdue = true
                        ),
                        PendingReapply(
                            logEntry = logEntryEntityListSample[1],
                            dueDate = LocalDate.now().plusDays(5),
                            daysRemaining = 5,
                            isOverdue = false
                        )
                    )
                )
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun LogEntryListScreenEmptyPreview() {
    FieldTrackTheme {
        Surface {
            LogEntryListScreen(
                onLogEntryClick = {},
                uiState = LogEntryListUiState(),
            )
        }
    }
}
