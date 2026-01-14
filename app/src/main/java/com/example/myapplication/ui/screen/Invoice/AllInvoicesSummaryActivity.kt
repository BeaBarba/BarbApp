package com.example.myapplication.ui.screen.Invoice

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.calculateEndPadding
import androidx.compose.foundation.layout.calculateStartPadding
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
import com.example.myapplication.debug.fatture
import com.example.myapplication.debug.listaFatture
import com.example.myapplication.ui.component.AddButton
import com.example.myapplication.ui.component.BackButton
import com.example.myapplication.ui.component.GenericCard
import com.example.myapplication.ui.component.SearchAppBar
import com.example.myapplication.ui.component.TopAppBar

@Composable
fun AllInvoicesSummaryActivity(
    navController : NavHostController
){
    Scaffold(
        topBar = {
            TopAppBar(
                navigationIcon = { BackButton { navController.navigateUp() }},
                id = stringResource(R.string.invoices)
            )
        },
        floatingActionButton = { AddButton {  }}

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
            item{ SearchAppBar(stringResource(R.string.invoice))}
            items(listaFatture){ item ->
                GenericCard(
                    text = item.name,
                    textDescription = item.type
                )
                Spacer(Modifier.size(8.dp))
            }
        }
    }
}