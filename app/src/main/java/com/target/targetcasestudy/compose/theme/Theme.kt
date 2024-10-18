package com.target.targetcasestudy.compose.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import com.target.targetcasestudy.compose.theme.TargetColor.Accent
import com.target.targetcasestudy.compose.theme.TargetColor.Primary
import com.target.targetcasestudy.compose.theme.TargetColor.PrimaryDark


private val DarkColorPalette = darkColorScheme(
    primary = Primary,
    onPrimary = Color.White, // Added onPrimary
    primaryContainer = PrimaryDark, // Changed primaryVariant to primaryContainer
    onPrimaryContainer = Color.White, // Added onPrimaryContainer
    secondary = Accent,
    onSecondary = Color.Black, // Added onSecondary
    secondaryContainer = Accent, // You might want to adjust this
    onSecondaryContainer = Color.Black, // Added onSecondaryContainer
    background = Color.Black, // Added background
    onBackground = Color.White, // Added onBackground
    surface = Color.Black, // Added surface
    onSurface = Color.White // Added onSurface
)

private val LightColorPalette = lightColorScheme(
    primary = Primary,
    onPrimary = Color.Black, // Added onPrimary
    primaryContainer = PrimaryDark, // Changed primaryVariant to primaryContainer
    onPrimaryContainer = Color.Black, // Added onPrimaryContainer
    secondary = Accent,
    onSecondary = Color.White, // Added onSecondary
    secondaryContainer = Accent, // You might want to adjust this
    onSecondaryContainer = Color.White, // Added onSecondaryContainer
    background = Color.White, // Added background
    onBackground = Color.Black, // Added onBackground
    surface = Color.White, // Added surface
    onSurface = Color.Black // Added onSurface
)

@Composable
fun TargetAppTheme(darkTheme: Boolean = isSystemInDarkTheme(), content: @Composable () -> Unit) {
    val colorScheme = if (darkTheme) {
        DarkColorPalette
    } else {
        LightColorPalette
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        shapes = shapes,
        content = content
    )
}