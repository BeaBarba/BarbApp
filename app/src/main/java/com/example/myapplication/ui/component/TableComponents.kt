package com.example.myapplication.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.calculateEndPadding
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import com.example.myapplication.ui.screen.Prodotto

data class TableColumn(
    val title: String,
    val weight: Float,
)

@Composable
fun Table(contentPadding: PaddingValues,
          listData: List<Prodotto>,
          headerColumns: List<TableColumn>
){
    val TABLE_BORDER_WIDTH = 2.dp
    val TABLE_BORDER_COLOR = MaterialTheme.colorScheme.onPrimaryContainer
    val HEIGHT_HEADER_ROW = 60.dp
    val HEADER_TYPOGRAPHY = MaterialTheme.typography.titleSmall
    val HEIGHT_ROW = 50.dp
    val TEXT = MaterialTheme.typography.bodyLarge
    val DESCRIPTION = MaterialTheme.typography.bodyMedium

    LazyColumn(modifier = Modifier
        .fillMaxWidth()
        .padding(
            start = contentPadding.calculateRightPadding(LayoutDirection.Ltr) + 8.dp,
            end = contentPadding.calculateLeftPadding(LayoutDirection.Ltr) + 8.dp,
        )
        .border(TABLE_BORDER_WIDTH, TABLE_BORDER_COLOR),
        contentPadding = PaddingValues(
            bottom = contentPadding.calculateEndPadding(LayoutDirection.Ltr) + 90.dp
        )
    ) {
        /* Header Cell */
        item{
            Row(
                modifier = Modifier
                    .height(HEIGHT_HEADER_ROW)
                    .background(MaterialTheme.colorScheme.primaryContainer, shape = RectangleShape),
                verticalAlignment = Alignment.CenterVertically
            ) {
                for(column in headerColumns) {
                    Box(
                        modifier = Modifier
                            .weight(column.weight)
                            .height(HEIGHT_HEADER_ROW),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = column.title, modifier = Modifier
                                .padding(horizontal = 4.dp), style = HEADER_TYPOGRAPHY
                        )
                    }
                }
            }
        }

        /* Rows */
        items(listData) {data ->
            MenuDivider()
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                /* Column 1 */
                Box(modifier = Modifier
                    .weight(headerColumns.get(0).weight)
                    .height(HEIGHT_ROW),
                ){
                    Column {
                        Text(
                            text = data.nome,
                            modifier = Modifier.padding(horizontal = 4.dp),
                            fontSize = TEXT.fontSize
                        )
                        Text(
                            text = data.modello,
                            modifier = Modifier.padding(horizontal = 4.dp),
                            fontSize = DESCRIPTION.fontSize
                        )
                    }
                }
                /* Column 2 */
                Box(modifier = Modifier
                    .weight(headerColumns.get(1).weight)
                    .height(HEIGHT_ROW),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "${data.quantita} ${data.unitaMisura}",
                        modifier = Modifier.padding(horizontal = 4.dp),
                        fontSize = TEXT.fontSize
                    )
                }
                /* Column 3 */
                Box(modifier = Modifier
                    .weight(headerColumns.get(2).weight)
                    .height(HEIGHT_ROW),
                    contentAlignment = Alignment.Center
                ){
                    Text(
                        text = "${data.prezzo}€",
                        modifier = Modifier.padding(horizontal = 4.dp),
                        fontSize = TEXT.fontSize
                    )
                }
                /* Column 4 */
                Box(modifier = Modifier
                    .weight(headerColumns.get(3).weight)
                    .height(HEIGHT_ROW),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "${data.iva}%",
                        modifier = Modifier.padding(horizontal = 4.dp),
                        fontSize = TEXT.fontSize
                    )
                }
            }
            MenuDivider()
        }
    }
}