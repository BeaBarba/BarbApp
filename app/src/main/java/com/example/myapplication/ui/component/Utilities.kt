package com.example.myapplication.ui.component

import androidx.compose.material3.CheckboxColors
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import com.example.myapplication.R
import java.time.Instant
import java.time.LocalDate
import java.time.ZoneId
import java.time.format.DateTimeFormatter

val colors = listOf(
    Color(0xFFD32F2F),
    Color(0xFF00BCD4),
    Color(0xFFFFEB3B),
    Color(0xFF673AB7),
    Color(0xFF00E676),
    Color(0xFFFF5722),
    Color(0xFF2196F3),
    Color(0xFFE91E63),
    Color(0xFFCDDC39),
    Color(0xFF3F51B5),
    Color(0xFF1DE9B6),
    Color(0xFFFBC02D),
    Color(0xFF9C27B0),
    Color(0xFF4CAF50),
    Color(0xFFE040FB),
    Color(0xFFFF9800),
    Color(0xFF00B0FF),
    Color(0xFF8BC34A),
    Color(0xFF880E4F),
    Color(0xFFFF4081),
    Color(0xFF009688),
    Color(0xFF1A237E),
    Color(0xFFFFC107),
    Color(0xFFBF360C),
    Color(0xFF1B5E20),
    Color(0xFF607D8B),
    Color(0xFF004D40),
    Color(0xFF311B92),
    Color(0xFFFF6E40),
    Color(0xFFD4E157)
)

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
    val idValues : Pair<Int,String>,
    val name : String,
    val onClick : () -> Unit
)

fun convertStringToDate(date : String) : LocalDate? {
    return if(date.isBlank()) null
    else LocalDate.parse(date, DateTimeFormatter.ofPattern("dd/MM/yyyy"))
}

fun convertDateToLong(date : LocalDate): Long {
    return date.atStartOfDay(ZoneId.systemDefault()).toInstant().toEpochMilli()
}

fun convertLongToDate(long :Long) : LocalDate{
    return Instant.ofEpochMilli(long).atZone(ZoneId.systemDefault()).toLocalDate()
}

fun checkStringIsBigDecimal(value: String): Boolean {
    return value.toBigDecimalOrNull() != null || value.isBlank()
}

fun checkStringIsInt(value: String): Boolean {
    if(value.isBlank()) return false
    return value.all { char -> char.isDigit() }
}

fun checkStringIsFloat(value : String) : Boolean{
    if(value.isBlank()) return false
    val normalizeValue = value.replace(",",".").toFloatOrNull()
    return normalizeValue != null
}

