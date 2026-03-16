package com.example.fieldtrack.feature.applicationDetail

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.fieldtrack.data.db.entity.ApplicationEntity
import com.example.fieldtrack.ui.components.AppTopBar
import com.example.fieldtrack.ui.components.ApplicationSamplePreviewData
import com.example.fieldtrack.ui.components.SingleButton
import com.example.fieldtrack.ui.theme.FieldTrackTheme

@Composable
fun ApplicationDetailScreen(application: ApplicationEntity?, onPrimaryAction: () -> Unit) {
    Scaffold(
        topBar = {
            AppTopBar("")
        }
    ) { innerPadding ->
        ApplicationDetailContent(application, onPrimaryAction, Modifier.padding(innerPadding))
    }
}

@Composable
fun ApplicationDetailContent(
    application: ApplicationEntity?,
    onPrimaryAction: () -> Unit,
    modifier: Modifier
) {
    Card(
        modifier = modifier
            .fillMaxSize()
            .padding(12.dp)
    ) {
        Column(modifier = Modifier.padding(4.dp)) {
            Spacer(modifier = Modifier.height(24.dp))
            application?.zoneName?.let { Text(it) }
            application?.productName?.let { Text(it) }
            application?.quantity?.let { Text(it) }
            application?.appliedAt?.let { Text(it) }
            application?.reapplyDays?.let { Text(it) }
            application?.notes?.let { Text(it) }

            Spacer(modifier = Modifier.height(24.dp))

            SingleButton(
                label = "Delete",
                onClick = onPrimaryAction,
            )
        }
    }
}


@Preview
@Composable
fun ApplicationDetailScreenPreview() {
    FieldTrackTheme {
        Surface {
            ApplicationDetailScreen(ApplicationSamplePreviewData.applicationSample, {})
        }
    }
}