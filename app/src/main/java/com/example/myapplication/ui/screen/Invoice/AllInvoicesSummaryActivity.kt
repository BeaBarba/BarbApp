package com.example.myapplication.ui.screen.Invoice

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
import com.example.myapplication.debug.pagamenti
import com.example.myapplication.ui.NavigationRoute
import com.example.myapplication.ui.component.AddButton
import com.example.myapplication.ui.component.BackButton
import com.example.myapplication.ui.component.CustomSearchBar
import com.example.myapplication.ui.component.GenericCard
import com.example.myapplication.ui.component.TopAppBar

@Composable
fun AllInvoicesSummaryActivity(
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
        floatingActionButton = {AddButton{navController.navigate(NavigationRoute.InvoiceAdd)}}

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
            item{CustomSearchBar(stringResource(R.string.invoice), onValueChange = {})}
            items(pagamenti){ item ->
                GenericCard(
                    text = item.fattura + " - " + item.cliente,
                    textDescription = item.data,
                    onClick = {navController.navigate(NavigationRoute.SingleInvoiceSummary)}
                )
                Spacer(Modifier.size(8.dp))
            }
        }
    }
}