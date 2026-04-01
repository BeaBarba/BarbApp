package com.example.myapplication.ui.screen.PurchaseInvoice.allSummary

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.calculateEndPadding
import androidx.compose.foundation.layout.calculateStartPadding
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.myapplication.R
import com.example.myapplication.ui.NavigationRoute
import com.example.myapplication.ui.component.AddButton
import com.example.myapplication.ui.component.BackButton
import com.example.myapplication.ui.component.CustomSearchBar
import com.example.myapplication.ui.component.DropDownMenuBubbles
import com.example.myapplication.ui.component.GenericCard
import com.example.myapplication.ui.component.TopAppBar

@Composable
fun AllPurchaseInvoicesSummaryActivity(
    state : AllPurchaseInvoicesSummaryState,
    actions: AllPurchaseInvoicesSummaryActions,
    navController : NavHostController
){
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            TopAppBar(
                navigationIcon = {BackButton{navController.navigate(NavigationRoute.Home)}},
                id = stringResource(R.string.invoices_purchase),
                trailingIcon = {
                    DropDownMenuBubbles(
                        dateOnClick = actions::orderedDate,
                        sellerOnClick = actions::orderedSeller,
                        ascendingOnClick = actions::ascendingSort,
                        descendingOnClick = actions::descendingSort
                    )
                }
            )
        },
        floatingActionButton = {AddButton{navController.navigate(NavigationRoute.PurchaseInvoiceAdd(null))} }
    ) { contentPadding ->
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
                CustomSearchBar(
                    stringResource(R.string.invoices_purchase),
                    onValueChange = actions::searchPurchaseInvoice
                )
            }
            item{Spacer(Modifier.size(8.dp))}
            items(state.purchaseInvoicesView){ item ->
                GenericCard(
                    text = item.purchaseInvoice.number,
                    textDescription = "${item.seller.name} - ${item.purchaseInvoice.year}",
                    onClick = {navController.navigate(NavigationRoute.SinglePurchaseInvoiceSummary(item.purchaseInvoice.id))}
                )
                Spacer(Modifier.size(8.dp))
            }
            item{ Spacer(Modifier.size(80.dp)) }
        }
    }
}