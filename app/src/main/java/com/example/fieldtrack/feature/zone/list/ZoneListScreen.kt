package com.example.fieldtrack.feature.zone.list

import android.content.res.Configuration
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.fieldtrack.R
import com.example.fieldtrack.data.db.model.Zone
import com.example.fieldtrack.ui.components.AppTopBar
import com.example.fieldtrack.ui.components.EmptyStateCard
import com.example.fieldtrack.ui.components.ListItem
import com.example.fieldtrack.ui.theme.FieldTrackTheme

@Composable
fun ZoneListScreen(
    onNavigateBack: () -> Unit,
    onZoneClick: (Long) -> Unit,
    zones: List<Zone>
) {
    Scaffold(
        topBar = {
            AppTopBar(
                title = stringResource(R.string.title_zone_list),
                onBack = onNavigateBack
            )
        }
    ) { innerPadding ->
        ZoneListContent(
            onZoneClick,
            zones,
            Modifier.padding(innerPadding)
        )
    }
}

@Composable
fun ZoneListContent(
    onZoneClick: (Long) -> Unit,
    zones: List<Zone>,
    modifier: Modifier
) {

    if (zones.isEmpty()) {
        Box(
            modifier = modifier
                .fillMaxSize()
                .padding(horizontal = 12.dp),
            contentAlignment = Alignment.Center
        ) {
            EmptyStateCard(
                title = stringResource(R.string.title_no_zones),
                description = stringResource(R.string.description_no_zones),
                modifier = modifier
            )
        }
    } else {
    LazyColumn(
        modifier = modifier
            .fillMaxSize()
            .padding(top = 12.dp, start = 12.dp, end = 12.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp),
    ) {

        items(zones) { zone ->
            ZoneListItem(
                zone = zone,
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable {
                        onZoneClick(zone.id)
                    }
            )
        }
    }
}
}

@Composable
fun ZoneListItem(zone: Zone, modifier: Modifier) {
    ListItem(
        modifier,
        contentLeft = {
            Text(
                text = zone.name,
                style = MaterialTheme.typography.titleMedium
            )

            zone.notes?.let {
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
    name = "History Item - Light",
    showBackground = true,
    uiMode = Configuration.UI_MODE_NIGHT_NO
)
@Preview(
    name = "History Item - Dark",
    showBackground = true,
    uiMode = Configuration.UI_MODE_NIGHT_YES
)
@Composable
fun ZoneListScreenPreview() {
    val zones = listOf(
        Zone(
            id = 1,
            name = "Zone 1",
            notes = "Notes for Zone 1"
        ),
    )
    FieldTrackTheme {
        Surface {
            ZoneListContent(onZoneClick = {}, zones = zones, modifier = Modifier)
        }
    }
}