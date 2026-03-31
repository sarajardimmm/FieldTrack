package com.example.fieldtrack.ui.theme

import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext

private val DarkColorScheme = darkColorScheme(
    primary = Green80,
    onPrimary = Green20,
    primaryContainer = Green30,
    onPrimaryContainer = Green90,
    inversePrimary = Green50,
    secondary = GreenGrey80,
    onSecondary = GreenGrey20,
    secondaryContainer = GreenGrey30,
    onSecondaryContainer = GreenGrey90,
    tertiary = Pink70,
    onTertiary = Pink20,
    tertiaryContainer = Pink30,
    onTertiaryContainer = Pink90,
    error = Red80,
    onError = Red20,
    errorContainer = Red30,
    onErrorContainer = Red90,
    background = Grey10,
    onBackground = Grey90,
    surface = GreenGrey30,
    onSurface = GreenGrey80,
    inverseSurface = Grey90,
    inverseOnSurface = Green10,
    surfaceVariant = GreenGrey30,
    onSurfaceVariant = GreenGrey80,
    outline = GreenGrey80

)

private val LightColorScheme = lightColorScheme(
    primary = Green40,
    onPrimary = Color.White,
    primaryContainer = Green90,
    onPrimaryContainer = Green10,
    inversePrimary = Green80,
    secondary = GreenGrey40,
    onSecondary = Color.White,
    secondaryContainer = GreenGrey90,
    onSecondaryContainer = GreenGrey10,
    tertiary = Pink50,
    onTertiary = Color.White,
    tertiaryContainer = Pink90,
    onTertiaryContainer = Pink10,
    error = Red40,
    onError = Color.White,
    errorContainer = Red90,
    onErrorContainer = Red10,
    background = Color.White,
    onBackground = Grey10,
    surface = GreenGrey90,
    onSurface = GreenGrey30,
    inverseSurface = Grey10,
    inverseOnSurface = Green90,
    surfaceVariant = GreenGrey80,
    onSurfaceVariant = GreenGrey30,
    outline = GreenGrey50
)

@Composable
fun FieldTrackTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    // Dynamic color is available on Android 12+
    dynamicColor: Boolean = false,
    content: @Composable () -> Unit
) {
    val colorScheme = when {
        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            val context = LocalContext.current
            if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
        }

        darkTheme -> DarkColorScheme
        else -> LightColorScheme
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}