package com.example.myapplication.ui.screen.Material

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.calculateEndPadding
import androidx.compose.foundation.layout.calculateStartPadding
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.ChevronRight
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import androidx.navigation.NavDestination.Companion.hasRoute
import androidx.navigation.NavHostController
import com.example.myapplication.R
import com.example.myapplication.debug.prodotti
import com.example.myapplication.ui.component.BackButton
import com.example.myapplication.ui.component.CustomOutlineTextField
import com.example.myapplication.ui.NavigationRoute
import com.example.myapplication.ui.component.DeleteButton
import com.example.myapplication.ui.component.GenericCard
import com.example.myapplication.ui.component.TopAppBar

@Composable
fun MaterialAddActivity(
    navController : NavHostController
){
    val previousBackStackEntry = navController.previousBackStackEntry
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            TopAppBar(id = stringResource(R.string.material),
                navigationIcon = {BackButton{navController.navigateUp()}},
                trailingIcon = {
                    IconButton(
                        onClick = {
                            navController.navigate(NavigationRoute.SingleMaterialSummary){
                                popUpTo(NavigationRoute.MaterialAdd){inclusive = true}
                            }
                        },
                        colors = IconButtonDefaults.iconButtonColors(
                            contentColor = MaterialTheme.colorScheme.onPrimary
                        )
                    ) {
                        Icon(Icons.Filled.Check, contentDescription = stringResource(R.string.save))
                    }
                }
            )
        }
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
            item{CustomOutlineTextField(stringResource(R.string.name))}
            item{Spacer(Modifier.size(8.dp))}
            item{CustomOutlineTextField(stringResource(R.string.brand))}
            item{Spacer(Modifier.size(8.dp))}
            item{CustomOutlineTextField(stringResource(R.string.model))}
            item{Spacer(Modifier.size(8.dp))}
            item{CustomOutlineTextField(stringResource(R.string.quantity))}
            item{Spacer(Modifier.size(8.dp))}
            if(previousBackStackEntry?.destination?.hasRoute<NavigationRoute.JobMaterials>() == false) {
                item { CustomOutlineTextField(stringResource(R.string.unit_price)) }
                item { Spacer(Modifier.size(8.dp)) }
                /*val v_size = if(venditori.size < 5){
                    venditori.size}else{5}
                item{SplitButtonMenu(content = stringResource(R.string.seller), venditori, heightMenu = (v_size * 55).dp)}
                item{Spacer(Modifier.size(8.dp))}
                val b_size = if(bolle.size < 5){
                    bolle.size}else{5}
                item{SplitButtonMenu(content = stringResource(R.string.bubble), bolle, heightMenu = (b_size * 55).dp)}
                item{Spacer(Modifier.size(8.dp))}
                val f_size = if(fatture.size < 5){
                    fatture.size}else{5}
                item{SplitButtonMenu(content = stringResource(R.string.invoice), fatture, heightMenu =  (f_size * 55).dp)}
                item{Spacer(Modifier.size(8.dp))}
                item{Images()}
                item{Spacer(Modifier.size(8.dp))}
                */
            }else{
                item{
                    GenericCard(
                        text = stringResource(R.string.customer),
                        trailingContent = {
                            Icon(
                                imageVector = Icons.Filled.ChevronRight,
                                contentDescription = stringResource(R.string.customers)
                            )
                        },
                        onClick = {navController.navigate(NavigationRoute.Select("Cliente", "CustomerAdd"))}
                    )
                }
            }
            if(previousBackStackEntry?.destination?.hasRoute<NavigationRoute.SingleMaterialSummary>() == true) {
                item {
                    DeleteButton {
                        prodotti = prodotti.subList(1, prodotti.size)
                        navController.navigate(NavigationRoute.Warehouse)
                    }
                }
                item { Spacer(Modifier.size(8.dp)) }
            }
        }
    }
}