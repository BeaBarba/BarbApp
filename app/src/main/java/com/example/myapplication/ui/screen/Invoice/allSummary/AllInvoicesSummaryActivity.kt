package com.example.myapplication.ui.screen.Invoice.allSummary

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
import com.example.myapplication.ui.component.GenericCard
import com.example.myapplication.ui.component.TopAppBar
import java.time.format.DateTimeFormatter

@Composable
fun AllInvoicesSummaryActivity(
    state : AllInvoiceSummaryState,
    actions : AllInvoiceSummaryActions,
    navController : NavHostController
){
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            TopAppBar(
                navigationIcon = {BackButton{navController.navigateUp()}},
                id = stringResource(R.string.invoices)
            )
        },
        floatingActionButton = {AddButton{navController.navigate(NavigationRoute.InvoiceAdd(null))}}

    ) { contentPadding ->

        LazyColumn(
            modifier = Modifier
                .padding(
                    start = contentPadding.calculateStartPadding(LayoutDirection.Ltr) + 8.dp,
                    end = contentPadding.calculateEndPadding(LayoutDirection.Ltr) + 8.dp,
                    top = contentPadding.calculateTopPadding() + 8.dp,
                    bottom = contentPadding.calculateBottomPadding()
                )
        ) {
            item{
                CustomSearchBar(
                    label = stringResource(R.string.invoice),
                    value = state.searchString,
                    onValueChange = actions::searchInvoice
                )
            }
            item{Spacer(Modifier.size(8.dp))}
            items(state.invoicesView){ item ->
                GenericCard(
                    text = actions.getCustomerName(item.revenue.id),
                    textDescription = item.revenue.issueDate.format(DateTimeFormatter.ofPattern("dd/MM/yyyy")),
                    onClick = {navController.navigate(NavigationRoute.SingleInvoiceSummary(item.revenue.id))}
                )
                Spacer(Modifier.size(8.dp))
            }
        }
    }
}