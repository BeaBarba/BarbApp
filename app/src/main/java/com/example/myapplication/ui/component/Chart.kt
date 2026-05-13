package com.example.myapplication.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import com.github.mikephil.charting.charts.PieChart
import com.github.mikephil.charting.components.Legend
import com.github.mikephil.charting.data.PieData
import com.github.mikephil.charting.data.PieDataSet
import com.github.mikephil.charting.data.PieEntry
import com.patrykandpatrick.vico.compose.cartesian.CartesianChartHost
import com.patrykandpatrick.vico.compose.cartesian.axis.rememberBottom
import com.patrykandpatrick.vico.compose.cartesian.axis.rememberStart
import com.patrykandpatrick.vico.compose.cartesian.layer.rememberColumnCartesianLayer
import com.patrykandpatrick.vico.compose.cartesian.layer.rememberLineCartesianLayer
import com.patrykandpatrick.vico.compose.cartesian.rememberCartesianChart
import com.patrykandpatrick.vico.compose.common.component.rememberTextComponent
import com.patrykandpatrick.vico.compose.common.rememberVerticalLegend
import com.patrykandpatrick.vico.core.cartesian.axis.HorizontalAxis
import com.patrykandpatrick.vico.core.cartesian.axis.VerticalAxis
import com.patrykandpatrick.vico.core.cartesian.data.CartesianChartModelProducer
import com.patrykandpatrick.vico.core.cartesian.data.CartesianValueFormatter
import com.patrykandpatrick.vico.core.cartesian.layer.ColumnCartesianLayer
import com.patrykandpatrick.vico.core.common.Fill
import com.patrykandpatrick.vico.core.common.component.LineComponent
import com.patrykandpatrick.vico.compose.common.component.rememberShapeComponent
import com.patrykandpatrick.vico.compose.common.shape.toVicoShape
import com.patrykandpatrick.vico.core.cartesian.layer.LineCartesianLayer
import com.patrykandpatrick.vico.core.common.Insets
import com.patrykandpatrick.vico.core.common.LegendItem
import com.patrykandpatrick.vico.core.common.shape.CorneredShape
import java.text.DecimalFormat
import java.time.LocalDate
import java.time.format.DateTimeFormatter

@Composable
fun LineCartesianChar(
    modifier : Modifier = Modifier,
    colors : List<Color>,
    labels : List<String>,
    lineModelProducer : CartesianChartModelProducer,
    xLabels : Map<Float, String> = emptyMap(),
    yLabels : Map<Float, String> = emptyMap(),

){

    val textComponent = rememberTextComponent(color = if (isSystemInDarkTheme()) Color.White else Color.Black)
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
                valueFormatter =
                    if (yLabels.isEmpty()) {
                        remember { CartesianValueFormatter.decimal( decimalFormat = DecimalFormat("#.# €;−#.# €")) }
                    }else{
                        CartesianValueFormatter { _, y, _ ->
                            val key = (y as? Number)?.toFloat() ?: 0f
                            yLabels[key] ?: key.toInt().toString()
                        }
                    }
            ),
            bottomAxis = HorizontalAxis.rememberBottom(
                itemPlacer = HorizontalAxis.ItemPlacer.aligned(spacing = {_ -> 2}, shiftExtremeLines = true),
                valueFormatter =
                if(xLabels.isEmpty()) {
                    remember { CartesianValueFormatter. decimal() }
                }else{
                    CartesianValueFormatter{ _, x , _ ->
                        val key = (x as? Number)?.toFloat() ?: 0f
                        val localDate = LocalDate.ofEpochDay(key.toLong())
                        val formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy")
                        xLabels[key] ?: formatter.format(localDate)
                    }
                }
            ),
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
                padding = Insets(0f, 16f, 0f, 0f)
            )
        ),
        modelProducer = lineModelProducer,
        modifier = modifier.padding(start = 16.dp, end = 24.dp).height(350.dp)
    )
}

@Composable
fun PieChartComposable(
    modifier: Modifier = Modifier,
    title : String = "",
    labels : List<String>,
    values : List<Float>,
    colors : List<Color>
) {
    val textColor = android.graphics.Color.BLACK

    AndroidView(
        factory = { context ->
            PieChart(context).apply {
                description.isEnabled = false
                isDrawHoleEnabled = true
                holeRadius = 40f
                transparentCircleRadius = 15f
                setUsePercentValues(true)
                isRotationEnabled = true

                legend.apply {
                    isEnabled = true
                    verticalAlignment = Legend.LegendVerticalAlignment.BOTTOM
                    horizontalAlignment = Legend.LegendHorizontalAlignment.CENTER
                    orientation = Legend.LegendOrientation.HORIZONTAL
                    setDrawInside(false)
                }

            }
        },
        update = { chart ->
            val pieEntries = values.mapIndexed { index, value ->
                PieEntry(value, labels[index])
            }

            val pieDataSet = PieDataSet(pieEntries, title).apply {
                this.colors = colors.map { it.toArgb() }
                sliceSpace = 3f

                setDrawValues(true)
                valueTextColor = textColor
                valueTextSize = 14f
            }

            val pieData = PieData(pieDataSet)
            chart.data = pieData

            chart.setEntryLabelColor(textColor)
            chart.setEntryLabelTextSize(14f)
            chart.setDrawEntryLabels(true)

            chart.invalidate()
        },
        modifier = modifier.size(300.dp).fillMaxWidth()
    )
}

@Composable
fun ColumnCartesianChart(
    columnComponents: List<LineComponent>,
    labels: List<String>,
    modelProducer: CartesianChartModelProducer,
    titleAxisX : String? = null,
    titleAxisY : String? = null,
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 16.dp, end = 24.dp)
    ) {
        CartesianChartHost(
            chart = rememberCartesianChart(
                rememberColumnCartesianLayer(
                    columnProvider = ColumnCartesianLayer.ColumnProvider.series(columnComponents),
                    mergeMode = { ColumnCartesianLayer.MergeMode.Stacked }
                ),
                startAxis = VerticalAxis.rememberStart(
                    title = titleAxisY,
                    titleComponent = rememberTextComponent(),
                ),
                bottomAxis = HorizontalAxis.rememberBottom(
                    title = titleAxisX,
                    titleComponent = rememberTextComponent(),
                    valueFormatter = CartesianValueFormatter { _, x, _ ->
                        labels.getOrNull(x.toInt()) ?: ""
                    }
                )
            ),
            modelProducer = modelProducer
        )
    }
}

@Composable
fun CustomLegend(
    labels: List<String>,
    colors: List<Color>,
    modifier: Modifier = Modifier
) {
    FlowRow(
        modifier = modifier
            .fillMaxWidth()
            .padding(16.dp),
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        labels.forEachIndexed { index, label ->
            val color = if (colors.isNotEmpty()) { colors[index % colors.size] } else { Color.Gray }

            Row(verticalAlignment = Alignment.CenterVertically) {
                Box(
                    modifier = Modifier
                        .size(12.dp)
                        .background(color, shape = CircleShape)
                )
                Spacer(modifier = Modifier.width(6.dp))
                Text(
                    text = label,
                    style = MaterialTheme.typography.bodyMedium
                )
            }
        }
    }
}