package com.example.myapplication.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import com.example.myapplication.R
import com.example.myapplication.debug.Prodotto
import com.example.myapplication.ui.theme.TableStyleDefaults
import com.example.myapplication.ui.theme.TableColumn

@Composable
fun MaterialTable(
    padding : PaddingValues,
    listData: List<Prodotto>,
){
    val headerColumns = listOf(
        TableColumn(title = stringResource(R.string.name).uppercase(), 3.0f),
        TableColumn(title = stringResource(R.string.short_quantity), 1.0f),
        TableColumn(title = stringResource(R.string.price), 1.5f),
        TableColumn(title = stringResource(R.string.vat), 1.0f)
    )
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(padding)
            .border(TableStyleDefaults.measures().borderWidth, TableStyleDefaults.colors().border),
    ){
        LazyColumn() {
            /* Header Cell */
            item {
                Row(
                    modifier = Modifier
                        .height(TableStyleDefaults.measures().heightHeaderRow)
                        .background(MaterialTheme.colorScheme.primaryContainer, shape = RectangleShape),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    for (column in headerColumns) {
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
            items(listData) { data ->
                MenuDivider()
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    /* Column 1 */
                    Box(
                        modifier = Modifier
                            .weight(headerColumns.get(0).weight)
                            .height(TableStyleDefaults.measures().heightRow),
                    ) {
                        Column {
                            Text(
                                text = data.nome,
                                modifier = Modifier.padding(horizontal = 4.dp),
                                fontSize = TableStyleDefaults.typography().text.fontSize
                            )
                            Text(
                                text = data.modello,
                                modifier = Modifier.padding(horizontal = 4.dp),
                                fontSize = TableStyleDefaults.typography().description.fontSize
                            )
                        }
                    }
                    /* Column 2 */
                    Box(
                        modifier = Modifier
                            .weight(headerColumns.get(1).weight)
                            .height(TableStyleDefaults.measures().heightRow),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = "${data.quantita} ${data.unitaMisura}",
                            modifier = Modifier.padding(horizontal = 4.dp),
                            fontSize = TableStyleDefaults.typography().text.fontSize
                        )
                    }
                    /* Column 3 */
                    Box(
                        modifier = Modifier
                            .weight(headerColumns.get(2).weight)
                            .height(TableStyleDefaults.measures().heightRow),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = "${data.prezzo}€",
                            modifier = Modifier.padding(horizontal = 4.dp),
                            fontSize = TableStyleDefaults.typography().text.fontSize
                        )
                    }
                    /* Column 4 */
                    Box(
                        modifier = Modifier
                            .weight(headerColumns.get(3).weight)
                            .height(TableStyleDefaults.measures().heightRow),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = "${data.iva}%",
                            modifier = Modifier.padding(horizontal = 4.dp),
                            fontSize = TableStyleDefaults.typography().text.fontSize
                        )
                    }
                }
            }
        }
    }
}

