package com.example.fieldtrack.feature.zone.detail

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.fieldtrack.R
import com.example.fieldtrack.ui.components.AppTopBar
import com.example.fieldtrack.ui.theme.FieldTrackTheme

@Composable
fun ZoneDetailScreen(
    zoneName: String,
    zoneNotes: String,
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
            zoneName = zoneName,
            zoneNotes = zoneNotes,
            modifier = Modifier.padding(innerPadding)
        )
    }
}

@Composable
fun ZoneDetailContent(
    zoneName: String,
    zoneNotes: String,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp, vertical = 12.dp)
            .verticalScroll(rememberScrollState())
            .navigationBarsPadding(),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
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
                Text(
                    text = stringResource(R.string.title_zone_detail),
                    style = MaterialTheme.typography.titleLarge,
                    fontWeight = FontWeight.SemiBold
                )

                HorizontalDivider()

                DetailItem(
                    label = stringResource(R.string.label_zone),
                    value = zoneName
                )

                DetailItem(
                    label = stringResource(R.string.label_notes),
                    value = zoneNotes,
                    multiline = true
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
                zoneName = "North Orchard",
                zoneNotes = "This zone contains mostly apple trees and requires frequent irrigation.",
                onNavigateBack = {}
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ZoneDetailScreenNoNotesPreview() {
    FieldTrackTheme {
        Surface {
            ZoneDetailScreen(
                zoneName = "South Vineyard",
                zoneNotes = "",
                onNavigateBack = {}
            )
        }
    }
}