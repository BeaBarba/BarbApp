package com.example.myapplication.ui.screen

import com.example.myapplication.ui.component.*
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.myapplication.R
import com.example.myapplication.ui.component.TopAppBar
import com.example.myapplication.ui.theme.MyApplicationTheme

class HomeActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MyApplicationTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Scaffold(
                        modifier = Modifier.fillMaxSize()
                            .padding(bottom = innerPadding.calculateBottomPadding()),
                        topBar = { TopAppBar("Home") },
                    ) { contentPadding ->
                        LazyVerticalGrid(
                            modifier = Modifier.fillMaxSize()
                                .padding(top = contentPadding.calculateTopPadding()),
                            columns = GridCells.Fixed(2),
                            horizontalArrangement = Arrangement.spacedBy(10.dp),
                            verticalArrangement = Arrangement.spacedBy(10.dp)
                        ) {
                            item { HomeCard("Interventi",painterResource(R.drawable.wrench_interventi)){} }
                            item { HomeCard("Clienti",painterResource(R.drawable.account_circle_24dp)){} }
                            item { HomeCard("Calendario", painterResource(R.drawable.calendar)){} }
                            item { HomeCard("Pulizie", painterResource(R.drawable.vacuum_pulizie)){} }
                            item { HomeCard("Pagamenti",painterResource(R.drawable.savings_pagamenti)){} }
                            item { HomeCard("Scadenze",painterResource(R.drawable.hourglass_scadenze)){} }
                            item { HomeCard("Magazzino",painterResource(R.drawable.warehouse_magazzino)){} }
                            item { HomeCard("Statistiche",painterResource(R.drawable.monitor_statistiche)){} }
                            item { HomeCard("Bolle", painterResource(R.drawable.receipt_bolle)){} }
                            item { HomeCard("Fatture",painterResource(R.drawable.description_24dp)){} }
                        }
                    }
                }
            }
        }
    }
}

