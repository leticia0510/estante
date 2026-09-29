package com.avanade.estante.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable

private val LightColorScheme = lightColorScheme(

    // Cor principal
    primary = Purple,
    onPrimary = White,

    // Cor secundária
    secondary = Pink,
    onSecondary = White,

    // Destaques
    tertiary = DarkBlue,
    onTertiary = White,

    // Fundo
    background = LightPink,
    onBackground = Purple,

    // Superfícies
    surface = White,
    onSurface = Purple,

    // Campos/containers
    surfaceVariant = LightBlue,
    onSurfaceVariant = DarkBlue
)

private val DarkColorScheme = darkColorScheme(

    // Cor principal
    primary = Pink,
    onPrimary = White,

    // Cor secundária
    secondary = LightBlue,
    onSecondary = DarkBlue,

    // Destaques
    tertiary = Purple,
    onTertiary = White,

    // Fundo
    background = DarkBlue,
    onBackground = White,

    // Superfícies
    surface = Purple,
    onSurface = White,

    // Containers
    surfaceVariant = DarkBlue,
    onSurfaceVariant = LightBlue
)

@Composable
fun EstanteTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {

    val colorScheme = if (darkTheme) {
        DarkColorScheme
    } else {
        LightColorScheme
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}
