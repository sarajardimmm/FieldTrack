package com.example.fieldtrack.feature.zone.list

import android.content.res.Configuration
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
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
import androidx.compose.ui.tooling.preview.Preview
import com.example.fieldtrack.R
import com.example.fieldtrack.data.db.model.Zone
import com.example.fieldtrack.ui.components.EmptyStateCard
import com.example.fieldtrack.ui.components.ListItem
import com.example.fieldtrack.ui.theme.FieldTrackTheme
import com.example.fieldtrack.ui.theme.LocalSpacing

@Composable
fun ZoneListScreen(
    onNavigateBack: () -> Unit,
    onZoneClick: (Long) -> Unit,
    zones: List<Zone>
) {
    ZoneListContent(
        onZoneClick = onZoneClick,
        zones = zones,
        modifier = Modifier.fillMaxSize()
    )
}

@Composable
fun ZoneListContent(
    onZoneClick: (Long) -> Unit,
    zones: List<Zone>,
    modifier: Modifier = Modifier
) {
    val spacing = LocalSpacing.current
    
    if (zones.isEmpty()) {
        Box(
            modifier = modifier
                .fillMaxSize()
                .padding(horizontal = spacing.medium),
            contentAlignment = Alignment.Center
        ) {
            EmptyStateCard(
                title = stringResource(R.string.title_no_zones),
                description = stringResource(R.string.description_no_zones)
            )
        }
    } else {
        LazyColumn(
            modifier = modifier.fillMaxSize(),
            contentPadding = PaddingValues(spacing.medium),
            verticalArrangement = Arrangement.spacedBy(spacing.small),
        ) {
            items(zones) { zone ->
                ZoneListItem(
                    zone = zone,
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable { onZoneClick(zone.id) }
                )
            }
        }
    }
}

@Composable
fun ZoneListItem(zone: Zone, modifier: Modifier = Modifier) {
    ListItem(
        modifier = modifier,
        contentLeft = {
            Text(
                text = zone.name,
                style = MaterialTheme.typography.titleMedium
            )

            zone.notes?.takeIf { it.isNotBlank() }?.let {
                Text(
                    text = it,
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
        }
    )
}

@Preview(
    name = "Zone List - Light",
    showBackground = true,
    uiMode = Configuration.UI_MODE_NIGHT_NO
)
@Preview(
    name = "Zone List - Dark",
    showBackground = true,
    uiMode = Configuration.UI_MODE_NIGHT_YES
)
@Composable
fun ZoneListScreenPreview() {
    val zones = listOf(
        Zone(id = 1, name = "North Orchard", notes = "Apples and Pears"),
        Zone(id = 2, name = "South Field", notes = "Corn"),
    )
    FieldTrackTheme {
        Surface {
            ZoneListScreen(
                onNavigateBack = {},
                onZoneClick = {},
                zones = zones
            )
        }
    }
}
