package com.example.myapplication.ui.component

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

/* Function that returns a specific color */
@Composable
fun checkColor(
    type: String = "NONE",
    primary: Boolean = false,
    onPrimary: Boolean = false,
    primaryContainer: Boolean = false,
    onPrimaryContainer: Boolean = false
): Color {
    return when (type) {
        "ALA" -> when {
            primary -> MaterialTheme.colorScheme.secondary
            onPrimary -> MaterialTheme.colorScheme.onSecondary
            primaryContainer -> MaterialTheme.colorScheme.secondaryContainer
            onPrimaryContainer -> MaterialTheme.colorScheme.onSecondaryContainer
            else -> MaterialTheme.colorScheme.surfaceBright
        }
        "ELE" -> when{
            primary -> MaterialTheme.colorScheme.tertiary
            onPrimary -> MaterialTheme.colorScheme.onTertiary
            primaryContainer -> MaterialTheme.colorScheme.tertiaryContainer
            onPrimaryContainer -> MaterialTheme.colorScheme.onTertiaryContainer
            else -> MaterialTheme.colorScheme.surfaceBright
        }
        "CDZ" -> when{
            primary -> MaterialTheme.colorScheme.surface
            onPrimary -> MaterialTheme.colorScheme.onSurface
            primaryContainer -> MaterialTheme.colorScheme.surfaceVariant
            onPrimaryContainer -> MaterialTheme.colorScheme.onSurfaceVariant
            else -> MaterialTheme.colorScheme.surfaceBright
        }
        "NONE" -> when{
            primary -> MaterialTheme.colorScheme.primary
            onPrimary -> MaterialTheme.colorScheme.onPrimary
            primaryContainer -> MaterialTheme.colorScheme.primaryContainer
            onPrimaryContainer -> MaterialTheme.colorScheme.onPrimaryContainer
            else -> MaterialTheme.colorScheme.surfaceBright
        }
        else -> throw IllegalArgumentException("Tipo non supportato")
    }
}