package com.example.fieldtrack.feature.applicationlist

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.fieldtrack.data.db.entity.ApplicationEntity
import com.example.fieldtrack.ui.components.AppTopBar
import com.example.fieldtrack.ui.components.ApplicationForm
import com.example.fieldtrack.ui.components.ApplicationSamplePreviewData
import com.example.fieldtrack.ui.components.HistoryItem
import com.example.fieldtrack.ui.theme.FieldTrackTheme

@Composable
fun ApplicationListScreen(
    onApplicationClick: (String) -> Unit,
    onAddApplication: (ApplicationFormData) -> Unit,
    applicationHistory: List<ApplicationEntity>
) {
    Scaffold(
        topBar = {
            AppTopBar("")
        }
    ) { innerPadding ->
        ApplicationListContent(
            onApplicationClick,
            onAddApplication,
            applicationHistory,
            Modifier.padding(innerPadding)
        )
    }

}

@Composable
fun ApplicationListContent(
    onApplicationClick: (String) -> Unit,
    onAddApplication: (ApplicationFormData) -> Unit,
    applicationHistory: List<ApplicationEntity>,
    modifier: Modifier
) {
    Column(modifier.padding(12.dp)) {
        ApplicationForm(
            actionLabel = "Add",
            onApplicationAction = onAddApplication
        )

        Spacer(modifier = Modifier.size(16.dp))

        LazyColumn {
            items(applicationHistory) { application ->

                HistoryItem(
                    application = application,
                    modifier = Modifier.clickable {
                        onApplicationClick(application.aid.toString())
                    }
                )
            }
        }
    }
}

@Preview
@Composable
fun ApplicationListScreenPreview() {

    FieldTrackTheme {
        Surface {
            ApplicationListScreen({}, {}, ApplicationSamplePreviewData.applicationListSample)
        }
    }
}