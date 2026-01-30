package com.example.myapplication.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.example.myapplication.data.database.DeliveryWithMaterialDetails
import com.example.myapplication.data.database.Material
import com.example.myapplication.debug.Prodotto
import com.example.myapplication.ui.theme.TabelStyle
import com.example.myapplication.ui.theme.TableStyleDefaults
import com.example.myapplication.ui.theme.TableColumn

fun LazyListScope.materialTable(
    listData: List<DeliveryWithMaterialDetails>,
    headerColumns: List<TableColumn>,
    style: TabelStyle,
){
    val borderWidth = style.measures.borderWidth
    val borderColor = style.colors.border
    /* Header Cell */
    item {
        Row(
            modifier = Modifier
                .height(TableStyleDefaults.measures().heightHeaderRow)
                .border(width = borderWidth, color = borderColor)
                .background(MaterialTheme.colorScheme.primaryContainer, shape = RectangleShape),
            verticalAlignment = Alignment.CenterVertically
        ) {
            headerColumns.forEach{column ->
                Box(
                    modifier = Modifier
                        .weight(column.weight)
                        .height(TableStyleDefaults.measures().heightHeaderRow),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = column.title,
                        modifier = Modifier.padding(horizontal = 4.dp),
                        style = TableStyleDefaults.typography().header.copy(fontWeight = FontWeight.Bold)
                    )
                }
            }
        }
    }

    /* Rows */
    itemsIndexed(listData) { index, data ->
        if(index > 0) {
            MenuDivider()
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .drawBehind {
                    // Disegniamo solo i bordi laterali per non sovrapporli
                    val stroke = borderWidth.toPx()
                    drawLine(borderColor, Offset(0f, 0f), Offset(0f, size.height), stroke) // Sinistra
                    drawLine(borderColor, Offset(size.width, 0f), Offset(size.width, size.height), stroke) // Destra
                    if (index == listData.lastIndex) {
                        drawLine(borderColor, Offset(0f, size.height), Offset(size.width, size.height), stroke)
                    }
                }
            ,
            verticalAlignment = Alignment.CenterVertically
        ) {
            /* Column 1 */
            Box(
                modifier = Modifier
                    .weight(headerColumns.get(0).weight)
                    .height(style.measures.heightRow),
            ) {
                Column {
                    Text(
                        text = data.material.category,
                        modifier = Modifier.padding(horizontal = 4.dp),
                        fontSize = style.typography.text.fontSize,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                    Text(
                        text = "${data.material.model} - ${data.material.brand}",
                        modifier = Modifier.padding(horizontal = 4.dp),
                        fontSize = style.typography.description.fontSize
                    )
                }
            }
            /* Column 2 */
            Box(
                modifier = Modifier
                    .weight(headerColumns.get(1).weight)
                    .height(style.measures.heightRow),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "${data.delivery.quantity} ${data.material.unitMeasurement}",
                    modifier = Modifier.padding(horizontal = 4.dp),
                    fontSize = style.typography.text.fontSize
                )
            }
            /* Column 3 */
            Box(
                modifier = Modifier
                    .weight(headerColumns.get(2).weight)
                    .height(style.measures.heightRow),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "${data.delivery.unitPrice}€",
                    modifier = Modifier.padding(horizontal = 4.dp),
                    fontSize = style.typography.text.fontSize
                )
            }
            /* Column 4 */
            Box(
                modifier = Modifier
                    .weight(headerColumns.get(3).weight)
                    .height(style.measures.heightRow),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "${data.delivery.vatNumber}%",
                    modifier = Modifier.padding(horizontal = 4.dp),
                    fontSize = style.typography.text.fontSize
                )
            }
        }
    }
}
