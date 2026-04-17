package com.example.myapplication.ui.screen.Material.allSummary

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
import androidx.compose.ui.Alignment
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
import com.example.myapplication.ui.component.CustomSearchBar
import com.example.myapplication.ui.component.TopAppBar
import com.example.myapplication.ui.component.checkColor

@Composable
fun WarehouseActivity(
    state : WarehouseState,
    actions : WarehouseActions,
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
        floatingActionButton = {AddButton{navController.navigate(NavigationRoute.MaterialAdd(null))}}
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
            item{
                CustomSearchBar(
                    label = stringResource(R.string.material),
                    value = state.searchString,
                    onValueChange = actions::setSearchString
                )
            }
            item{Spacer(Modifier.size(8.dp))}
            items(state.materialsView){ item ->
                GenericCard(
                    type = item.material.type.name,
                    leadingContent = {
                        Avatar(
                            char = item.material.type.name[0],
                            type = item.material.type.name
                        )
                    },
                    text = item.material.category,
                    textDescription = if (item.isAirConditioner) {
                            "${item.material.model} - ${item.material.brand} (Matricole: ${item.airConditioner.size})"
                        } else {
                            "${item.material.model} - ${item.material.brand}"
                        },
                    textSpace = 0.6f,
                    trailingContent = {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.Center
                        ) {
                            IconButton(
                                onClick = {actions.decQuantity(item.material.id)},
                                colors =
                                    IconButtonDefaults.iconButtonColors(
                                        contentColor = checkColor(
                                            type = item.material.type.name,
                                            onPrimaryContainer = true)
                                    )
                            ) {
                                Icon(
                                    imageVector = Icons.Filled.Remove,
                                    contentDescription = stringResource(R.string.decrease)
                                )
                            }
                            Text(
                                text = "${item.material.availableQuantity} ${item.material.unitMeasurement}",
                                color = checkColor(type = item.material.type.name, onPrimaryContainer = true)
                            )
                            IconButton(
                                onClick = {actions.incQuantity(item.material.id)},
                                colors = IconButtonDefaults.iconButtonColors(
                                    contentColor = checkColor(type = item.material.type.name, onPrimaryContainer = true)
                                )
                            ) {
                                Icon(
                                    imageVector = Icons.Filled.Add,
                                    contentDescription = stringResource(R.string.increase)
                                )
                            }
                        }
                    },
                    onClick = {navController.navigate(NavigationRoute.SingleMaterialSummary(item.material.id))}
                )
                Spacer(Modifier.size(8.dp))
            }
            item{Spacer(Modifier.size(80.dp))}
        }
    }
}