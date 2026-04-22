package com.dmitry.test.messenger.presentation.ui.theme

import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext

private val DarkColorScheme = darkColorScheme(
    primary = Color(0xFFEAEAEA),        // акцент (светлый)
    onPrimary = Color(0xFF121212),

    secondary = Color(0xFFB0B0B0),
    onSecondary = Color(0xFF121212),

    tertiary = Color(0xFF8A8A8A),

    background = Color(0xFF121212),     // основной фон
    onBackground = Color(0xFFEAEAEA),

    surface = Color(0xFF1A1A1A),        // карточки
    onSurface = Color(0xFFEAEAEA),

    surfaceVariant = Color(0xFF2A2A2A), // инпуты / вторичные блоки
    onSurfaceVariant = Color(0xFFB0B0B0),

    outline = Color(0xFF3A3A3A),

    error = Color(0xFFEAEAEA),          // тоже монохром
    onError = Color(0xFF121212)
)

@Composable
fun MessengerTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    // Dynamic color is available on Android 12+
    dynamicColor: Boolean = true,
    content: @Composable () -> Unit
) {
    val colorScheme = when {
        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            val context = LocalContext.current
            if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
        }

        darkTheme -> DarkColorScheme
        else -> DarkColorScheme
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}