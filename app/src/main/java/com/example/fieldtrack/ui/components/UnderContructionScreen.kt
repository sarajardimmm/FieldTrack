package com.example.fieldtrack.ui.components

import android.content.res.Configuration
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.LockClock
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import com.example.fieldtrack.R
import com.example.fieldtrack.ui.theme.FieldTrackTheme
import com.example.fieldtrack.ui.theme.LocalSpacing

@Composable
fun UnderConstructionScreen(
    title: String = stringResource(R.string.under_construction_title),
    message: String = stringResource(R.string.under_construction_message)
) {
    val spacing = LocalSpacing.current
    
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(spacing.large),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Icon(
            imageVector = Icons.Default.LockClock,
            contentDescription = null,
            modifier = Modifier.size(spacing.extraExtraExtraLarge),
            tint = MaterialTheme.colorScheme.primary
        )

        Spacer(modifier = Modifier.height(spacing.medium))

        Text(
            text = title,
            style = MaterialTheme.typography.headlineSmall
        )

        Spacer(modifier = Modifier.height(spacing.small))

        Text(
            textAlign = TextAlign.Center,
            text = message,
            style = MaterialTheme.typography.bodyMedium
        )
    }
}

@Preview(
    name = "Under Construction - Light",
    showBackground = true,
    uiMode = Configuration.UI_MODE_NIGHT_NO
)
@Preview(
    name = "Under Construction - Dark",
    showBackground = true,
    uiMode = Configuration.UI_MODE_NIGHT_YES
)
@Composable
fun UnderConstructionScreenPreview() {
    FieldTrackTheme {
        Surface {
            UnderConstructionScreen()
        }
    }
}