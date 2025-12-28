package com.example.myapplication.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

data class TableColumn(
    val title: String,
    val weight: Float,
)

data class TableTypography(
    val header: TextStyle,
    val text: TextStyle,
    val description: TextStyle
)

data class TableColors(
    val border : Color
)

data class TableMeasures(
    val borderWidth : Dp,
    val heightHeaderRow : Dp,
    val heightRow : Dp
)

object TableStyleDefaults {
    @Composable
    fun typography(): TableTypography =
        TableTypography(
            header = MaterialTheme.typography.titleMedium,
            text = MaterialTheme.typography.bodyLarge,
            description = MaterialTheme.typography.bodyMedium
        )
    @Composable
    fun colors() : TableColors =
        TableColors(
            border = MaterialTheme.colorScheme.onPrimaryContainer
        )

    @Composable
    fun measures() : TableMeasures =
        TableMeasures(
            borderWidth = 2.dp,
            heightRow = 50.dp,
            heightHeaderRow = 60.dp
        )
}