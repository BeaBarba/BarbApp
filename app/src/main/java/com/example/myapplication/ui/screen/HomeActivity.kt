package com.example.myapplication.ui.screen

import android.content.Intent
import com.example.myapplication.ui.component.*
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.calculateEndPadding
import androidx.compose.foundation.layout.calculateStartPadding
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.myapplication.R
import com.example.myapplication.ui.component.TopAppBar
import com.example.myapplication.ui.theme.MyApplicationTheme

@Composable
fun HomeActivity(
    modifier: Modifier,
    navController : NavHostController
) {
    Scaffold(
        modifier = modifier.fillMaxSize(),
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
                    start = contentPadding.calculateStartPadding(layoutDirection = LayoutDirection.Ltr)
                ),
            columns = GridCells.Fixed(2),
            horizontalArrangement = Arrangement.spacedBy(1.dp),
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            item{
                HomeCard(
                    item = "Interventi",
                    iconName =  painterResource(R.drawable.wrench_interventi),
                    onclick = {navController.navigate(NavigationRoute.AllJobsSummary)}
                )
            }
            item{
                HomeCard(
                    item = "Clienti",
                    iconName = painterResource(R.drawable.account_circle_24dp),
                    onclick = {navController.navigate(NavigationRoute.AllCustomersSummary)}
                )
            }
            item{
                HomeCard(
                    item = "Calendario",
                    iconName = painterResource(R.drawable.calendar),
                    onclick = {navController.navigate(NavigationRoute.TodayCalendar)}
                )
            }
            item{
                HomeCard(
                    item = "Pulizie",
                    iconName = painterResource(R.drawable.vacuum_pulizie),
                    onclick = {navController.navigate(NavigationRoute.AllCleaningSummary)}
                )
            }
            item{
                HomeCard(
                    item = "Pagamenti",
                    iconName = painterResource(R.drawable.savings_pagamenti),
                    onclick = {navController.navigate(NavigationRoute.AllPaymentsSummary)}
                )
            }
            item{
                HomeCard(
                    item = "Scadenze",
                    iconName = painterResource(R.drawable.hourglass_scadenze),
                    onclick = {navController.navigate(NavigationRoute.AllDeadlinesSummary)}
                )
            }
            item{
                HomeCard(
                    item = "Magazzino",
                    painterResource(R.drawable.warehouse_magazzino),
                    onclick = {navController.navigate(NavigationRoute.Warehouse)}
                )
            }
            item{
                HomeCard(
                    item = "Statistiche",
                    iconName = painterResource(R.drawable.monitor_statistiche),
                    onclick = {navController.navigate(NavigationRoute.AllStatistics)}
                )
            }
            item{
                HomeCard(
                    item = "Bolle",
                    iconName = painterResource(R.drawable.receipt_bolle),
                    onclick = {navController.navigate(NavigationRoute.AllBubblesSummary)}
                )
            }
            item{
                HomeCard(
                    item = "Fatture",
                    iconName = painterResource(R.drawable.description_24dp),
                    onclick = {/*navController.navigate(NavigationRoute.)*/}
                )
            }
        }
    }
}
