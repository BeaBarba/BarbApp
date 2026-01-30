package com.example.myapplication.ui.screen.PurchaseInvoice

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.calculateEndPadding
import androidx.compose.foundation.layout.calculateStartPadding
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.myapplication.R
import com.example.myapplication.debug.listaBolle
import com.example.myapplication.debug.prodotti
import com.example.myapplication.debug.provenienze
import com.example.myapplication.ui.component.BackButton
import com.example.myapplication.ui.component.GenericCard
import com.example.myapplication.ui.component.KeyValueLabel
import com.example.myapplication.ui.NavigationRoute
import com.example.myapplication.ui.component.CustomDivider
import com.example.myapplication.ui.component.TitleLabel
import com.example.myapplication.ui.component.TopAppBar
import com.example.myapplication.ui.component.materialTable
import com.example.myapplication.ui.theme.TableStyleDefaults

@Composable
fun SinglePurchaseInvoiceSummaryActivity(
    navController : NavHostController
){
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            TopAppBar(
                navigationIcon = {BackButton{navController.navigateUp()}},
                id = stringResource(R.string.invoice_purchase),
                trailingIcon = {
                    IconButton(
                        onClick = {
                            navController.navigate(NavigationRoute.BubbleAdd(null))
                        },
                        colors = IconButtonDefaults.iconButtonColors(
                            contentColor = MaterialTheme.colorScheme.onPrimary
                        )
                    ) {
                        Icon(
                            painter = painterResource(R.drawable.edit_square_24dp),
                            contentDescription = stringResource(R.string.edit)
                        )
                    }
                }
            )
        }
    ) { contentPadding ->
        val headerColumns = TableStyleDefaults.defaultColumns()
        val tableStyle = TableStyleDefaults.defaultStyle()
        LazyColumn(
            modifier = Modifier
                .padding(
                    top = contentPadding.calculateTopPadding() + 8.dp,
                    start = contentPadding.calculateStartPadding(LayoutDirection.Ltr) + 8.dp,
                    end = contentPadding.calculateEndPadding(LayoutDirection.Ltr) + 8.dp,
                    bottom = contentPadding.calculateBottomPadding()
                )
                .fillMaxSize()
        ) {

            item{
                KeyValueLabel(
                    title = stringResource(R.string.seller),
                    description = provenienze.get(0).fornitore,
                    weightTitle = 1.0f,
                    weighDescription = 2.0f
                )
            }
            item{Spacer(Modifier.size(8.dp))}
            item{
                KeyValueLabel(
                    title = stringResource(R.string.number),
                    description = provenienze.get(0).numeroBolla.toString(),
                    weightTitle = 1.0f,
                    weighDescription = 2.0f
                )
            }
            item{Spacer(Modifier.size(8.dp))}
            item{
                KeyValueLabel(
                    title = stringResource(R.string.date_issue),
                    description = provenienze.get(0).data.toString(),
                    weightTitle = 1.0f,
                    weighDescription = 2.0f
                )
            }
            item{CustomDivider()}
            item{TitleLabel(stringResource(R.string.bubbles))}
            item{Spacer(Modifier.size(8.dp))}
            itemsIndexed(listaBolle.subList(0,3)){index, item ->
                GenericCard(
                    leadingContent = {
                        Icon(
                            painter = painterResource(R.drawable.receipt_bolle),
                            contentDescription = stringResource(R.string.bubble)
                        )
                    },
                    text = item.name,
                    textDescription = item.type
                )
                if(index < 2) {
                    Spacer(Modifier.size(8.dp))
                }
            }
            item{CustomDivider()}
            item{TitleLabel(stringResource(R.string.materials))}
            item{Spacer(Modifier.size(8.dp))}
            //materialTable(prodotti, headerColumns, tableStyle)
            item{Spacer(Modifier.size(8.dp))}
        }
    }
}