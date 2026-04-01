package com.example.fieldtrack.ui.components.buttons

import android.content.res.Configuration
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Button
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.fieldtrack.ui.theme.FieldTrackTheme

@Composable
fun SingleButton(label: String, onClick: () -> Unit, modifier: Modifier = Modifier) {
    Button(
        modifier = modifier.fillMaxWidth(),
        onClick = onClick,
    ) { Text(label) }
}

@Preview(
    name = "Single Button - Light",
    showBackground = true,
    uiMode = Configuration.UI_MODE_NIGHT_NO
)
@Preview(
    name = "Single Button - Dark",
    showBackground = true,
    uiMode = Configuration.UI_MODE_NIGHT_YES
)
@Composable
fun SingleButtonPreview() {
    FieldTrackTheme {
        Surface {
            SingleButton(label = "Add", onClick = {})
        }
    }
}