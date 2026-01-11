package com.example.myapplication.ui.component

import androidx.compose.material3.CheckboxColors
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import com.example.myapplication.R

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
        else -> throw IllegalArgumentException(stringResource(R.string.type_not))
    }
}

@Composable
fun checkColorAvatar(
    type: String = "NONE",
    primary: Boolean = false,
    onPrimary: Boolean = false,
    primaryContainer: Boolean = false,
    onPrimaryContainer: Boolean = false
): Color {
    return when (type){
        "ALA" -> when{
            primary -> checkColor(type, onPrimary = true)
            onPrimary -> checkColor(type, primary = true)
            else -> checkColor(type, primary, onPrimary, primaryContainer, onPrimaryContainer)
        }
        "NONE" -> when{
            primary -> checkColor(type, onPrimary = true)
            onPrimary -> checkColor(type, primary = true)
            else -> checkColor(type, primary, onPrimary, primaryContainer, onPrimaryContainer)
        }
        else -> checkColor(type, primary, onPrimary, primaryContainer, onPrimaryContainer)
    }
}

@Composable
fun checkboxColors(
    type : String = "NONE"
) : CheckboxColors{
    return CheckboxColors(
        /* Selected */
        checkedCheckmarkColor = checkColorAvatar(type, primary = true),
        checkedBoxColor = checkColorAvatar(type, onPrimary = true),
        checkedBorderColor = checkColorAvatar(type, onPrimary = true),
        /* Unselected*/
        uncheckedCheckmarkColor = checkColorAvatar(type, onPrimary = true),
        uncheckedBoxColor = checkColorAvatar(type, onPrimary = true),
        uncheckedBorderColor = checkColorAvatar(type, onPrimary = true),

        /* Disable */
        disabledCheckedBoxColor = checkColorAvatar(type, primary = true).copy(alpha = 0.5f),
        disabledUncheckedBoxColor = checkColorAvatar(type, primary = true).copy(alpha = 0.5f),
        disabledIndeterminateBoxColor = checkColorAvatar(type, primary = true).copy(alpha = 0.5f),
        disabledBorderColor = checkColorAvatar(type, primary = true).copy(alpha = 0.5f),
        disabledUncheckedBorderColor = checkColorAvatar(type, primary = true).copy(alpha = 0.5f),
        disabledIndeterminateBorderColor = checkColorAvatar(type, primary = true).copy(alpha = 0.5f),
    )
}

data class MenuItem(
    val name : String,
    val onClick : (String) -> Unit
)