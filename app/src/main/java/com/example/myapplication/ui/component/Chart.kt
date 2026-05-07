package com.example.myapplication.ui.component

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.unit.dp
import com.patrykandpatrick.vico.compose.cartesian.CartesianChartHost
import com.patrykandpatrick.vico.compose.cartesian.axis.rememberBottom
import com.patrykandpatrick.vico.compose.cartesian.axis.rememberStart
import com.patrykandpatrick.vico.compose.cartesian.layer.rememberLineCartesianLayer
import com.patrykandpatrick.vico.compose.cartesian.rememberCartesianChart
import com.patrykandpatrick.vico.compose.common.component.rememberTextComponent
import com.patrykandpatrick.vico.compose.common.rememberVerticalLegend
import com.patrykandpatrick.vico.core.cartesian.axis.HorizontalAxis
import com.patrykandpatrick.vico.core.cartesian.axis.VerticalAxis
import com.patrykandpatrick.vico.core.cartesian.data.CartesianChartModelProducer
import com.patrykandpatrick.vico.core.common.Fill
import com.patrykandpatrick.vico.compose.common.component.rememberShapeComponent
import com.patrykandpatrick.vico.compose.common.shape.toVicoShape
import com.patrykandpatrick.vico.core.cartesian.data.CartesianValueFormatter
import com.patrykandpatrick.vico.core.cartesian.layer.LineCartesianLayer
import com.patrykandpatrick.vico.core.common.Insets
import com.patrykandpatrick.vico.core.common.LegendItem
import com.patrykandpatrick.vico.core.common.shape.CorneredShape
import java.text.DecimalFormat

@Composable
fun LineCartesianChar(
    colors : List<Color>,
    labels : List<String>,
    lineModelProducer : CartesianChartModelProducer,
    modifier : Modifier = Modifier.height(350.dp)
){

    val textComponent = rememberTextComponent(color = Color.Black)
    val iconShape = CorneredShape.Pill
    val legendIcons = colors.map { color ->
        rememberShapeComponent(fill = Fill(color.toArgb()), shape = iconShape)
    }


    val lines = colors.map { color ->
        val shapeComponent = rememberShapeComponent(
            fill = Fill(color.toArgb()),
            shape = RoundedCornerShape(50).toVicoShape(),
            strokeFill = Fill(color.toArgb()),
            strokeThickness = 10.dp,
        )
        LineCartesianLayer.Line(
            fill = LineCartesianLayer.LineFill.single(Fill(color.toArgb())),
            stroke = LineCartesianLayer.LineStroke.Continuous(thicknessDp = 2.dp.value),
            pointProvider = LineCartesianLayer.PointProvider.single(
                point = LineCartesianLayer.Point(
                    component = shapeComponent,
                    sizeDp = 12.dp.value
                )
            )
        )
    }

    CartesianChartHost(
        chart = rememberCartesianChart(
            rememberLineCartesianLayer(
                lineProvider = LineCartesianLayer.LineProvider.series(lines)
            ),
            startAxis = VerticalAxis.rememberStart(
                itemPlacer = VerticalAxis.ItemPlacer.step(step = {_ -> 0.5}, shiftTopLines = true),
                valueFormatter =  remember { CartesianValueFormatter.decimal(decimalFormat = DecimalFormat("#.#;−#.#")) }
            ),
            bottomAxis = HorizontalAxis.rememberBottom(),
            legend = rememberVerticalLegend(
                items = {
                    labels.forEachIndexed { index, labelText ->
                        add(
                            LegendItem(
                                icon = legendIcons[index],
                                labelComponent = textComponent,
                                label = labelText
                            )
                        )
                    }
                },
                iconSize = 8.dp,
                iconLabelSpacing = 8.dp,
                rowSpacing = 4.dp,
                padding = Insets(0f, 16f, 0f, 0f) // Spazio sopra la legenda
            )
        ),
        modelProducer = lineModelProducer,
        modifier = modifier.padding(start = 16.dp, end = 24.dp)
    )
}