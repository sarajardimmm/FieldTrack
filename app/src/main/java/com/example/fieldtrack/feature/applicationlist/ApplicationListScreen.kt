package com.example.fieldtrack.feature.applicationlist

import android.app.Application
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.fieldtrack.data.db.entity.ApplicationEntity
import com.example.fieldtrack.ui.components.ApplicationSamplePreviewData
import com.example.fieldtrack.ui.components.FormField
import com.example.fieldtrack.ui.components.HistoryItem

@Composable
fun ApplicationListScreen(
    onApplicationClick: (String) -> Unit,
    onAddApplication: (ApplicationFormData) -> Unit,
    applicationHistory: List<ApplicationEntity>
) {
    var zoneName by remember { mutableStateOf("") }
    var productName by remember { mutableStateOf("") }
    var appliedAt by remember { mutableStateOf("") }
    var reapplyDays by remember { mutableStateOf("") }
    var quantity by remember { mutableStateOf("") }
    var notes by remember { mutableStateOf("") }

    Column (Modifier.padding(12.dp)){
        Spacer(modifier = Modifier.size(16.dp))
        FormField(
            value = zoneName,
            onValueChange = { zoneName = it },
            label = "zone",
        )
        FormField(
            value = productName,
            onValueChange = { productName = it },
            label = "product"
        )
        FormField(
            value = appliedAt,
            onValueChange = { appliedAt = it },
            label = "applied at",
        )
        FormField(
            value = reapplyDays,
            onValueChange = { reapplyDays = it },
            label = "reapply in",
        )
        FormField(
            value = quantity,
            onValueChange = { quantity = it },
            label = "quantity",
        )
        FormField(
            value = notes,
            onValueChange = { notes = it },
            label = "notes",
        )

        Button(
            modifier = Modifier.fillMaxWidth(),
            onClick = {
            onAddApplication(
                ApplicationFormData(
                    zoneName,
                    productName,
                    appliedAt,
                    reapplyDays,
                    quantity,
                    notes
                )
            )
        }) {
            Text("Add")
        }

        Spacer(modifier = Modifier.size(16.dp))

        LazyColumn {
            items(applicationHistory) { application ->

                HistoryItem(
                    application = application,
                    modifier = Modifier.clickable {
                        onApplicationClick(application.uid.toString())
                    }
                )
            }
        }
    }
}

@Preview
@Composable
fun ApplicationListScreenPreview(){

    ApplicationListScreen({}, {}, ApplicationSamplePreviewData.applicationListSample )
}