package com.example.fieldtrack.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.fieldtrack.feature.applicationlist.ApplicationFormData
import com.example.fieldtrack.ui.theme.FieldTrackTheme


@Composable
fun ApplicationForm(
    actionLabel: String,
    onApplicationAction: (ApplicationFormData) -> Unit,
    modifier: Modifier = Modifier,
    applicationFormData: ApplicationFormData? = null
) {
    var zoneName by remember { mutableStateOf(applicationFormData?.zoneName ?: "") }
    var productName by remember { mutableStateOf(applicationFormData?.productName ?: "") }
    var appliedAt by remember { mutableStateOf(applicationFormData?.appliedAt ?: "") }
    var reapplyDays by remember { mutableStateOf(applicationFormData?.reapplyDays ?: "") }
    var quantity by remember { mutableStateOf(applicationFormData?.quantity ?: "") }
    var notes by remember { mutableStateOf(applicationFormData?.notes ?: "") }

    Column(modifier.padding(12.dp)) {
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

        SingleButton(
            label = actionLabel,
            onClick = {
                onApplicationAction(
                    ApplicationFormData(
                        zoneName, productName, appliedAt, reapplyDays, quantity, notes
                    )
                )
            })
    }
}

@Preview
@Composable
fun AddApplicationFormPreview() {
    FieldTrackTheme {
        Surface {
            ApplicationForm("Add", {})
        }
    }
}

@Preview
@Composable
fun EditApplicationFormPreview() {
    val applicationToEdit = ApplicationFormData(
        "Front garden",
        "adubo",
        "5 Dec 2025",
        "5 Apr 2026",
        "quantity",
        "neighbour gave us the adubo"
    )

    FieldTrackTheme {
        Surface {
            ApplicationForm("Edit", {}, applicationFormData = applicationToEdit)
        }
    }
}
