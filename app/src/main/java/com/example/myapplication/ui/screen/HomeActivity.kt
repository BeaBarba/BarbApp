package com.example.myapplication.ui.screen

import com.example.myapplication.ui.component.*
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.calculateEndPadding
import androidx.compose.foundation.layout.calculateStartPadding
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
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
import com.example.myapplication.ui.NavigationRoute
import com.example.myapplication.ui.component.TopAppBar

@Composable
fun HomeActivity(
    navController : NavHostController
) {
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            TopAppBar(
                id = "Home",
                navigationIcon = { DropDownMenuHome()},
                trailingIcon = {
                    IconButton(
                        onClick = {navController.navigate(NavigationRoute.Cart)}
                    ){
                        Icon(Icons.Filled.ShoppingCart, contentDescription = "Shop item", tint = MaterialTheme.colorScheme.onPrimary)
                    }
                }
            )
        },
    ) { contentPadding ->
        LazyVerticalGrid(
            modifier = Modifier.fillMaxSize()
                .padding(
                    top = contentPadding.calculateTopPadding(),
                    end = contentPadding.calculateEndPadding(layoutDirection = LayoutDirection.Ltr),
                    start = contentPadding.calculateStartPadding(layoutDirection = LayoutDirection.Ltr),
                    bottom = contentPadding.calculateBottomPadding()
                ),
            columns = GridCells.Fixed(2),
            horizontalArrangement = Arrangement.spacedBy(1.dp),
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            item{
                HomeCard(
                    item = stringResource(R.string.interventions),
                    iconName =  painterResource(R.drawable.wrench_interventi),
                    onclick = {navController.navigate(NavigationRoute.AllJobsSummary)}
                )
            }
            item{
                HomeCard(
                    item = stringResource(R.string.customers),
                    iconName = painterResource(R.drawable.account_circle_24dp),
                    onclick = {navController.navigate(NavigationRoute.AllCustomersSummary)}
                )
            }
            item{
                HomeCard(
                    item = stringResource(R.string.calendar),
                    iconName = painterResource(R.drawable.calendar),
                    onclick = {navController.navigate(NavigationRoute.TodayCalendar)}
                )
            }
            item{
                HomeCard(
                    item = stringResource(R.string.warehouse),
                    painterResource(R.drawable.warehouse_magazzino),
                    onclick = {navController.navigate(NavigationRoute.Warehouse)}
                )
            }
            item{
                HomeCard(
                    item = stringResource(R.string.cleaning_all),
                    iconName = painterResource(R.drawable.vacuum_pulizie),
                    onclick = {navController.navigate(NavigationRoute.AllCleaningSummary)}
                )
            }
            item{
                HomeCard(
                    item = stringResource(R.string.construction_sites),
                    painterResource(R.drawable.brickwall),
                    onclick = {navController.navigate(NavigationRoute.AllConstructionSummary)}
                )
            }
            item{
                HomeCard(
                    item = stringResource(R.string.payments),
                    iconName = painterResource(R.drawable.savings_pagamenti),
                    onclick = {navController.navigate(NavigationRoute.AllPaymentsSummary)}
                )
            }
            item{
                HomeCard(
                    item = stringResource(R.string.deadlines),
                    iconName = painterResource(R.drawable.hourglass_scadenze),
                    onclick = {navController.navigate(NavigationRoute.AllDeadlinesSummary)}
                )
            }
            item{
                HomeCard(
                    item = stringResource(R.string.bubbles),
                    iconName = painterResource(R.drawable.receipt_bolle),
                    onclick = {navController.navigate(NavigationRoute.AllBubblesSummary)}
                )
            }
            item{
                HomeCard(
                    item = stringResource(R.string.invoices),
                    iconName = painterResource(R.drawable.description_24dp),
                    onclick = {/*navController.navigate(NavigationRoute.)*/}
                )
            }
            item{
                HomeCard(
                    item = stringResource(R.string.statistics),
                    iconName = painterResource(R.drawable.monitor_statistiche),
                    onclick = {navController.navigate(NavigationRoute.AllStatistics)}
                )
            }
            item{Spacer(Modifier.size(8.dp))}
        }
    }
}
