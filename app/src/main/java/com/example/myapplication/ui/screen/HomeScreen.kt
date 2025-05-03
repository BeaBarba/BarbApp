package com.example.myapplication.ui.screen

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.myapplication.R
import com.example.myapplication.ui.component.TopAppBar

@Composable
fun HomeScreen(modifier : Modifier){
    Scaffold(
        modifier = modifier,
        topBar = {TopAppBar("Home")},
    ) { contentPadding ->
        LazyVerticalGrid(
            modifier = Modifier.fillMaxSize().padding(top = contentPadding.calculateTopPadding()),
            columns = GridCells.Fixed(2),
            horizontalArrangement = Arrangement.spacedBy(10.dp),
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            item { HomeCard("Interventi", painterResource(R.drawable.wrench_interventi)) }
            item { HomeCard("Clienti", painterResource(R.drawable.account_circle_24dp)) }
            item { HomeCard("Calendario", painterResource(R.drawable.calendar)) }
            item { HomeCard("Pulizie", painterResource(R.drawable.vacuum_pulizie)) }
            item { HomeCard("Pagamenti", painterResource(R.drawable.savings_pagamenti)) }
            item { HomeCard("Scadenze", painterResource(R.drawable.hourglass_scadenze)) }
            item { HomeCard("Magazzino", painterResource(R.drawable.warehouse_magazzino)) }
            item { HomeCard("Statistiche", painterResource(R.drawable.monitor_statistiche)) }
            item { HomeCard("Bolle", painterResource(R.drawable.receipt_bolle)) }
            item { HomeCard("Fatture", painterResource(R.drawable.description_24dp)) }
        }
    }
}

@Composable
fun HomeCard(item : String, iconName : Painter){
    Card(
        onClick = {},
        modifier = Modifier
            .size(150.dp)
            .fillMaxSize(),
        border = BorderStroke(16.dp, MaterialTheme.colorScheme.background),
        shape = RoundedCornerShape(40.dp),
        colors = CardColors(
            contentColor = MaterialTheme.colorScheme.onPrimaryContainer,
            containerColor = MaterialTheme.colorScheme.primaryContainer.copy(alpha = 0.5f),
            disabledContentColor = MaterialTheme.colorScheme.onPrimaryContainer,
            disabledContainerColor = MaterialTheme.colorScheme.primaryContainer
        )

    ){
        Column(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Icon(
                painter = iconName,
                "Wrench",
                modifier = Modifier
                    .size(60.dp)
                    .padding(2.dp),
                tint = MaterialTheme.colorScheme.onPrimaryContainer,
            )
            Spacer(Modifier.size(8.dp))
            Text(
                text = item,
                color = MaterialTheme.colorScheme.onPrimaryContainer
            )
        }
    }
}