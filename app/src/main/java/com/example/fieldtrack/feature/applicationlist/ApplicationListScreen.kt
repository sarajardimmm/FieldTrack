package com.example.fieldtrack.feature.applicationlist

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.fieldtrack.data.db.entity.ApplicationEntity

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

    Column {
        OutlinedTextField(
            value = zoneName,
            onValueChange = { zoneName = it },
            label = { Text("zone") },
        )
        OutlinedTextField(
            value = productName,
            onValueChange = { productName = it },
            label = { Text("product") }
        )
        OutlinedTextField(
            value = appliedAt,
            onValueChange = { appliedAt = it },
            label = { Text("applied at") },
        )
        OutlinedTextField(
            value = reapplyDays,
            onValueChange = { reapplyDays = it },
            label = { Text("reapply in") },
        )
        OutlinedTextField(
            value = quantity,
            onValueChange = { quantity = it },
            label = { Text("quantity") },
        )
        OutlinedTextField(
            value = notes,
            onValueChange = { notes = it },
            label = { Text("notes") },
        )

        Button(onClick = {
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
        }) { }

        Spacer(modifier = Modifier.size(16.dp))

        LazyColumn {
            items(applicationHistory) { application ->

                Text(
                    text = (application.zoneName + application.productName),
                    modifier = Modifier.clickable {
                        onApplicationClick(application.uid.toString())
                    }
                )
            }
        }
    }
}