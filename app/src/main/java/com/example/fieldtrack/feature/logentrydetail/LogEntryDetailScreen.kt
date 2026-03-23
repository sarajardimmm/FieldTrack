package com.example.fieldtrack.feature.logentrydetail

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.fieldtrack.data.db.entity.LogEntryEntity
import com.example.fieldtrack.ui.components.AppTopBar
import com.example.fieldtrack.ui.components.LogEntrySamplePreviewData
import com.example.fieldtrack.ui.components.SingleButton
import com.example.fieldtrack.ui.theme.FieldTrackTheme
import java.time.format.DateTimeFormatter

@Composable
fun LogEntryDetailScreen(logEntryEntity: LogEntryEntity?, onPrimaryAction: () -> Unit) {
    Scaffold(
        topBar = {
            AppTopBar("")
        }
    ) { innerPadding ->
        LogEntryDetailContent(logEntryEntity, onPrimaryAction, Modifier.padding(innerPadding))
    }
}

@Composable
fun LogEntryDetailContent(
    logEntryEntity: LogEntryEntity?,
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
            logEntryEntity?.zoneName?.let { Text(it) }
            logEntryEntity?.productName?.let { Text(it) }
            logEntryEntity?.quantity?.let { Text(it) }
            logEntryEntity?.appliedAt?.let { 
                val formatter = DateTimeFormatter.ofPattern("dd MMM yyyy")
                Text(it.format(formatter)) 
            }
            logEntryEntity?.reapplyDays?.let { Text(it) }
            logEntryEntity?.notes?.let { Text(it) }

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
fun LogEntryDetailScreenPreview() {
    FieldTrackTheme {
        Surface {
            LogEntryDetailScreen(LogEntrySamplePreviewData.logEntryEntitySample, {})
        }
    }
}
