package com.example.fieldtrack.feature.logentry.detail

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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import com.example.fieldtrack.R
import com.example.fieldtrack.data.db.model.LogEntry
import com.example.fieldtrack.ui.components.LogEntrySamplePreviewData
import com.example.fieldtrack.ui.components.buttons.SingleButton
import com.example.fieldtrack.ui.theme.FieldTrackTheme
import com.example.fieldtrack.ui.theme.LocalSpacing
import java.time.format.DateTimeFormatter

@Composable
fun LogEntryDetailScreen(
    logEntry: LogEntry?,
    onEditClick: (Long) -> Unit,
    onPrimaryAction: () -> Unit,
    onNavigateBack: () -> Unit
) {
    LogEntryDetailContent(
        logEntry = logEntry,
        onEditClick = onEditClick,
        onPrimaryAction = onPrimaryAction,
        onNavigateBack = onNavigateBack,
        modifier = Modifier.fillMaxSize()
    )
}

@Composable
fun LogEntryDetailContent(
    logEntry: LogEntry?,
    onEditClick: (Long) -> Unit,
    onPrimaryAction: () -> Unit,
    onNavigateBack: () -> Unit,
    modifier: Modifier = Modifier
) {
    val spacing = LocalSpacing.current
    val formatter = DateTimeFormatter.ofPattern("dd MMM yyyy")

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

                        logEntry?.let {
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
                        value = logEntry?.zoneName
                    )

                    DetailItem(
                        label = stringResource(R.string.label_product),
                        value = logEntry?.productName
                    )

                    DetailItem(
                        label = stringResource(R.string.label_quantity),
                        value = logEntry?.quantity
                    )

                    DetailItem(
                        label = stringResource(R.string.label_applied_at),
                        value = logEntry?.appliedAt?.format(formatter)
                    )

                    DetailItem(
                        label = stringResource(R.string.label_reapply_in),
                        value = logEntry?.reapplyDays?.let { stringResource(R.string.label_days, it) }
                    )

                    DetailItem(
                        label = stringResource(R.string.label_notes),
                        value = logEntry?.notes,
                        multiline = true
                    )
                }
            }
        }

        item {
            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = MaterialTheme.shapes.large,
                elevation = CardDefaults.cardElevation(defaultElevation = spacing.extraSmall),
                colors = CardDefaults.cardColors(
                    containerColor = MaterialTheme.colorScheme.errorContainer
                )
            ) {
                Column(
                    modifier = Modifier.padding(spacing.medium),
                    verticalArrangement = Arrangement.spacedBy(spacing.small)
                ) {
                    Text(
                        text = stringResource(R.string.msg_delete_log_warning),
                        style = MaterialTheme.typography.titleMedium,
                        color = MaterialTheme.colorScheme.onErrorContainer
                    )

                    SingleButton(
                        label = stringResource(R.string.action_delete),
                        onClick = {
                            onPrimaryAction()
                            onNavigateBack()
                        },
                    )
                }
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

@Preview
@Composable
fun LogEntryDetailScreenPreview() {
    FieldTrackTheme {
        Surface {
            LogEntryDetailScreen(
                logEntry = LogEntrySamplePreviewData.logEntrySample,
                onEditClick = {},
                onPrimaryAction = {},
                onNavigateBack = {}
            )
        }
    }
}
