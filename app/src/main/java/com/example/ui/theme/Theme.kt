package com.example.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val DarkColorScheme = darkColorScheme(
    primary = DUGreenNight,
    onPrimary = Color.Black,
    primaryContainer = DUGreenDark,
    onPrimaryContainer = DUGreenLight,
    secondary = DUGreenAccent,
    onSecondary = Color.Black,
    secondaryContainer = Color(0xFF1E3A2F),
    onSecondaryContainer = Color(0xFFC8E6C9),
    tertiary = DUAmber,
    onTertiary = Color.Black,
    tertiaryContainer = Color(0xFF451A03),
    onTertiaryContainer = DUAmberLight,
    background = DUBackgroundNight,
    onBackground = DUTextPrimaryNight,
    surface = DUSurfaceNight,
    onSurface = DUTextPrimaryNight,
    surfaceVariant = DUSurfaceVariantNight,
    onSurfaceVariant = DUTextSecondaryNight,
    outline = Color(0xFF475569),
    outlineVariant = Color(0xFF334155),
    error = Color(0xFFFFB4AB),
    onError = Color(0xFF690005)
)

private val LightColorScheme = lightColorScheme(
    primary = DUGreen,
    onPrimary = Color.White,
    primaryContainer = DUGreenLight,
    onPrimaryContainer = Color(0xFF00210B),
    secondary = Color(0xFF4A6352),
    onSecondary = Color.White,
    secondaryContainer = Color(0xFFCAE7D1),
    onSecondaryContainer = Color(0xFF082013),
    tertiary = DUAmber,
    onTertiary = Color.White,
    tertiaryContainer = DUAmberLight,
    onTertiaryContainer = Color(0xFF78350F),
    background = DUBackground,
    onBackground = DUTextPrimary,
    surface = DUSurface,
    onSurface = DUTextPrimary,
    surfaceVariant = DUSurfaceVariant,
    onSurfaceVariant = DUTextSecondary,
    outline = DUOutline,
    outlineVariant = DUOutlineVariant,
    error = DURed,
    onError = Color.White,
    errorContainer = DURedLight,
    onErrorContainer = Color(0xFF7F1D1D)
)

@Composable
fun GeoAttendDUTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colorScheme = if (darkTheme) DarkColorScheme else LightColorScheme
    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}
