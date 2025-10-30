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
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import com.example.myapplication.MainActivity
import com.example.myapplication.R
import com.example.myapplication.ui.component.TopAppBar
import com.example.myapplication.ui.theme.MyApplicationTheme

class HomeActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MyApplicationTheme {
                var ctx = LocalContext.current
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Scaffold(
                        modifier = Modifier.fillMaxSize()
                            .padding(bottom = innerPadding.calculateBottomPadding()),
                        topBar = { TopAppBar("Home",{ DropDownMenuHome()}, {
                            IconButton(
                                onClick = {
                                    val intent = Intent(ctx, MainActivity ::class.java)
                                    ctx.startActivity(intent)
                                } ,
                            ){
                                //CartActivity
                                Icon(Icons.Filled.ShoppingCart, contentDescription = "Shop item", tint = MaterialTheme.colorScheme.onPrimary)
                            }
                        })},
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
                            item { HomeCard("Interventi",painterResource(R.drawable.wrench_interventi)){} }
                            item { HomeCard("Clienti",painterResource(R.drawable.account_circle_24dp)){} }
                            item { HomeCard("Calendario", painterResource(R.drawable.calendar)){} }
                            item { HomeCard("Pulizie", painterResource(R.drawable.vacuum_pulizie)){} }
                            item { HomeCard("Pagamenti",painterResource(R.drawable.savings_pagamenti)){} }
                            item { HomeCard("Scadenze",painterResource(R.drawable.hourglass_scadenze)){} }
                            item { HomeCard("Magazzino",painterResource(R.drawable.warehouse_magazzino)){} }
                            item { HomeCard("Statistiche",painterResource(R.drawable.monitor_statistiche)){
                                val intent = Intent(ctx, StatisticsActivity ::class.java)
                                ctx.startActivity(intent)
                            } }
                            item { HomeCard("Bolle", painterResource(R.drawable.receipt_bolle)){} }
                            item { HomeCard("Fatture",painterResource(R.drawable.description_24dp)){} }
                        }
                    }
                }
            }
        }
    }
}

