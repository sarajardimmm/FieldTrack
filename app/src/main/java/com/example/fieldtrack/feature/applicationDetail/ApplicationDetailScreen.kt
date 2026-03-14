package com.example.fieldtrack.feature.applicationDetail

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.example.fieldtrack.data.db.entity.ApplicationEntity
import com.example.fieldtrack.ui.components.ApplicationSamplePreviewData

@Composable
fun ApplicationDetailScreen(application: ApplicationEntity?) {
    Column() {
        application?.zoneName?.let { Text(it) }
    }
}


@Preview
@Composable
fun ApplicationDetailScreenPreview(){

    ApplicationDetailScreen(ApplicationSamplePreviewData.applicationSample)
}