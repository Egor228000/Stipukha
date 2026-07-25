package com.example.stipukha.ui.theme

import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext

private val DarkColorScheme = darkColorScheme(
    primary = Slate900,         // Screen background (app uses primary as background)
    onPrimary = Slate800,       // Cards and bottom bar background
    secondary = EmeraldSuccess,  // Positive actions
    tertiary = White,           // Main text
    error = RoseError,          // Negative actions
    background = Slate900,
    surface = Slate800,
    onBackground = White,
    onSurface = Slate300
)

private val LightColorScheme = lightColorScheme(
    primary = Slate50,          // Screen background
    onPrimary = White,          // Cards and bottom bar background
    secondary = EmeraldSuccess,  // Positive actions
    tertiary = Slate950,        // Main text
    error = RoseError,          // Negative actions
    background = Slate50,
    surface = White,
    onBackground = Slate950,
    onSurface = Slate700
)

@Composable
fun StipukhaTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
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
