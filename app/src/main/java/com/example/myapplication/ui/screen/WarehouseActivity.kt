package com.example.myapplication.ui.screen

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.calculateEndPadding
import androidx.compose.foundation.layout.calculateStartPadding
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.myapplication.R
import com.example.myapplication.ui.component.AddButton
import com.example.myapplication.ui.component.Avatar
import com.example.myapplication.ui.component.BackButton
import com.example.myapplication.ui.component.GenericCard
import com.example.myapplication.ui.NavigationRoute
import com.example.myapplication.ui.component.SearchAppBar
import com.example.myapplication.ui.component.TopAppBar
import com.example.myapplication.ui.component.checkColorAvatar

@Composable
fun WarehouseActivity(
    navController : NavHostController
){
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            TopAppBar(
                navigationIcon = {BackButton{navController.navigate(NavigationRoute.Home)}},
                id = stringResource(R.string.warehouse)
            )
        },
        floatingActionButton = {AddButton{navController.navigate(NavigationRoute.MaterialAdd)}}
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
            item{ SearchAppBar(stringResource(R.string.material))}
            items(prodotti){item ->
                GenericCard(
                    type = item.tipo,
                    leadingContent = {
                        Avatar(
                            char = item.tipo.get(0),
                            textColor = checkColorAvatar(item.tipo, primary = true),
                            backgroundColor = checkColorAvatar(item.tipo, onPrimary = true)
                        )
                    },
                    text = item.nome,
                    textDescription = item.modello,
                    textSpace = 0.8f,
                    trailingContent = {Text(text = item.quantita.toString() + " " + item.unitaMisura)},
                    onClick = {navController.navigate(NavigationRoute.SingleMaterialSummary)}
                )
                Spacer(Modifier.size(8.dp))
            }
            item{Spacer(Modifier.size(80.dp))}
        }
    }
}