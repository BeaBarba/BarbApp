package com.example.myapplication.ui.screen.Material

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.calculateEndPadding
import androidx.compose.foundation.layout.calculateStartPadding
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Remove
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.myapplication.R
import com.example.myapplication.debug.prodotti
import com.example.myapplication.ui.component.AddButton
import com.example.myapplication.ui.component.Avatar
import com.example.myapplication.ui.component.BackButton
import com.example.myapplication.ui.component.GenericCard
import com.example.myapplication.ui.NavigationRoute
import com.example.myapplication.ui.component.CustomSearchBar
import com.example.myapplication.ui.component.TopAppBar
import com.example.myapplication.ui.component.checkColor

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
            item{CustomSearchBar(stringResource(R.string.material), onValueChange = {})}
            items(prodotti){ item ->
                var quantity by remember {mutableStateOf(item.quantita)}
                if(quantity > 0){
                    GenericCard(
                        type = item.tipo,
                        leadingContent = {
                            Avatar(
                                char = item.tipo.get(0),
                                type = item.tipo
                            )
                        },
                        text = item.nome,
                        textDescription = item.modello,
                        textSpace = 0.6f,
                        trailingContent = {
                            Row(
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.Center
                            ) {
                                IconButton(
                                    onClick = {quantity = quantity.dec()},
                                    colors = IconButtonDefaults.iconButtonColors(
                                        contentColor = checkColor(type = item.tipo, onPrimaryContainer = true)
                                    )
                                ) {
                                    Icon(
                                        imageVector = Icons.Filled.Remove,
                                        contentDescription = stringResource(R.string.decrease)
                                    )
                                }
                                Text(
                                    text = quantity.toString() + " " + item.unitaMisura,
                                    color = checkColor(type = item.tipo, onPrimaryContainer = true)
                                )
                                IconButton(
                                    onClick = {quantity = quantity.inc()},
                                    colors = IconButtonDefaults.iconButtonColors(
                                        contentColor = checkColor(type = item.tipo, onPrimaryContainer = true)
                                    )
                                ) {
                                    Icon(
                                        imageVector = Icons.Filled.Add,
                                        contentDescription = stringResource(R.string.increase)
                                    )
                                }
                            }
                        },
                        onClick = {navController.navigate(NavigationRoute.SingleMaterialSummary)}
                    )
                    Spacer(Modifier.size(8.dp))
                }
            }
            item{Spacer(Modifier.size(80.dp))}
        }
    }
}