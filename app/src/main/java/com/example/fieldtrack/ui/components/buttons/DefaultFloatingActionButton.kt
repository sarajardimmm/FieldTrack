package com.example.fieldtrack.ui.components.buttons

import android.content.res.Configuration
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import com.example.fieldtrack.ui.theme.FieldTrackTheme

@Composable
fun DefaultFloatingActionButton(
    onClick: () -> Unit,
    icon: ImageVector,
    contentDescription: String
) {
    FloatingActionButton(
        onClick = { onClick() },
        containerColor = MaterialTheme.colorScheme.tertiary,
    ) {
        Icon(icon, contentDescription = contentDescription)
    }
}

@Preview(
    name = "Floating Action Button - Light",
    showBackground = true,
    uiMode = Configuration.UI_MODE_NIGHT_NO
)
@Preview(
    name = "Floating Action Button - Dark",
    showBackground = true,
    uiMode = Configuration.UI_MODE_NIGHT_YES
)
@Composable
fun DefaultFloatingActionButtonPreview() {
    FieldTrackTheme {
        Surface {
            DefaultFloatingActionButton(
                onClick = {},
                icon = Icons.Default.Add,
                contentDescription = "Add log entry"
            )
        }
    }
}