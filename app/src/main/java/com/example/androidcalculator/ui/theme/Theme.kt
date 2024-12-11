package com.example.androidcalculator.ui.theme

import android.app.Activity
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat

private val CyberColorScheme = darkColorScheme(
    primary = NeonBlue,
    onPrimary = CyberBlack,
    primaryContainer = NeonBlue.copy(alpha = 0.1f),
    onPrimaryContainer = NeonBlue,

    secondary = NeonPink,
    onSecondary = CyberBlack,
    secondaryContainer = NeonPink.copy(alpha = 0.1f),
    onSecondaryContainer = NeonPink,

    tertiary = NeonGreen,
    onTertiary = CyberBlack,
    tertiaryContainer = NeonGreen.copy(alpha = 0.1f),
    onTertiaryContainer = NeonGreen,

    background = CyberBlack,
    onBackground = CyberWhite,

    surface = DeepBlue,
    onSurface = CyberWhite,
    surfaceVariant = DarkBlue,
    onSurfaceVariant = CyberWhite.copy(alpha = 0.7f),

    error = NeonPink,
    onError = CyberBlack,

    outline = NeonBlue.copy(alpha = 0.5f)
)

@Composable
fun AndroidCalculatorTheme(
    content: @Composable () -> Unit
) {
    val colorScheme = CyberColorScheme

    val view = LocalView.current
    if (!view.isInEditMode) {
        SideEffect {
            val window = (view.context as Activity).window

            WindowCompat.getInsetsController(window, view).apply {
                isAppearanceLightStatusBars = false
            }
            WindowCompat.setDecorFitsSystemWindows(window, false)
        }
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}