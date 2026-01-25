package com.example.myapplication.ui.screen.Construction

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.calculateEndPadding
import androidx.compose.foundation.layout.calculateStartPadding
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.ChevronRight
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import androidx.navigation.NavDestination.Companion.hasRoute
import androidx.navigation.NavHostController
import com.example.myapplication.R
import com.example.myapplication.debug.addressType
import com.example.myapplication.debug.cantieri
import com.example.myapplication.debug.customersType
import com.example.myapplication.ui.component.BackButton
import com.example.myapplication.ui.component.CustomOutlineTextField
import com.example.myapplication.ui.component.DatePickerFieldToModal
import com.example.myapplication.ui.component.DeleteButton
import com.example.myapplication.ui.component.GenericCard
import com.example.myapplication.ui.NavigationRoute
import com.example.myapplication.ui.component.TopAppBar

@Composable
fun ConstructionAddActivity(
    navController : NavHostController
){
    val previousBackStackEntry = navController.previousBackStackEntry
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            TopAppBar(
                navigationIcon = {BackButton{navController.navigateUp()}},
                id = stringResource(R.string.construction_site),
                trailingIcon = {
                    IconButton(
                        onClick = {
                            navController.navigate(NavigationRoute.SingleConstructionSummary){
                                popUpTo(NavigationRoute.ConstructionAdd){inclusive = true}
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
        },
    ){ contentPadding ->
        var showItems by remember { mutableStateOf(false)}
        LazyColumn (
            modifier = Modifier
                .padding(
                    top = contentPadding.calculateTopPadding() + 8.dp,
                    start = contentPadding.calculateStartPadding(LayoutDirection.Ltr) + 8.dp,
                    end = contentPadding.calculateEndPadding(LayoutDirection.Ltr) + 8.dp,
                    bottom = contentPadding.calculateBottomPadding()
                )
                .fillMaxSize()
        ){
            item{
                GenericCard(
                    leadingContent = {Icon(Icons.Filled.Add, contentDescription = stringResource(R.string.add_item))},
                    text = stringResource(R.string.customer),
                    trailingContent = {
                        Icon(
                            Icons.Filled.ChevronRight,
                            contentDescription = stringResource(R.string.edit),
                            modifier = Modifier.size(35.dp)
                        )
                    },
                    onClick = {navController.navigate(NavigationRoute.Select("Cliente", "CustomerAdd"))}
                )
            }
            item{Spacer(Modifier.size(8.dp))}
            item{
                GenericCard(
                    leadingContent = {Icon(Icons.Filled.LocationOn, contentDescription = stringResource(R.string.address))},
                    text = stringResource(R.string.address) + " " + stringResource(R.string.existing).lowercase(),
                    trailingContent = {
                        Icon(
                            Icons.Filled.ChevronRight,
                            contentDescription = stringResource(R.string.edit),
                            modifier = Modifier.size(35.dp)
                        )
                    },
                    onClick = {navController.navigate(NavigationRoute.Select("Indirizzo", "AddressAdd"))}
                )
            }
            item{Spacer(Modifier.size(8.dp))}
            item{
                GenericCard(
                    leadingContent = {
                        IconButton(
                            onClick = {showItems = !showItems},
                            colors = IconButtonDefaults.iconButtonColors(
                                contentColor = MaterialTheme.colorScheme.onPrimaryContainer
                            )
                        ) {
                            Icon(
                                imageVector = Icons.Filled.Add,
                                contentDescription = stringResource(R.string.add_item)
                            )
                        }
                    },
                    text = stringResource(R.string.add) + " " + stringResource(R.string.reference).lowercase(),
                    trailingContent = {
                        Icon(
                            Icons.Filled.ChevronRight,
                            contentDescription = stringResource(R.string.edit),
                            modifier = Modifier.size(35.dp)
                        )
                    },
                    onClick = {navController.navigate(NavigationRoute.Select("Riferimento", "CustomerAdd"))}
                )
            }
            if(showItems){
                item{
                    CustomOutlineTextField(
                        label = stringResource(R.string.name),
                        onValueChange = {}
                    )
                }
                item{
                    CustomOutlineTextField(
                        label = stringResource(R.string.last_name),
                        onValueChange = {}
                    )
                }
                item{
                    CustomOutlineTextField(
                        label = stringResource(R.string.phone),
                        onValueChange = {}
                    )
                }
            }
            item{Spacer(Modifier.size(8.dp))}
            item{
                DatePickerFieldToModal(
                    title = stringResource(R.string.date_start),
                    onValueChange = {},
                    value = ""
                )
            }
            item{Spacer(Modifier.size(8.dp))}
            item{
                DatePickerFieldToModal(
                    title = stringResource(R.string.date_end),
                    onValueChange = {},
                    value = ""
                )
            }
            item{Spacer(Modifier.size(8.dp))}
            if (previousBackStackEntry?.destination?.hasRoute<NavigationRoute.SingleConstructionSummary>() == true) {
                item {
                    DeleteButton {
                        cantieri = cantieri.subList(1, cantieri.size)
                        navController.navigate(NavigationRoute.AllConstructionSummary){
                            popUpTo(NavigationRoute.AllConstructionSummary){inclusive=true}
                            launchSingleTop = true
                        }
                    }
                }
                item { Spacer(Modifier.size(8.dp)) }
            }
        }
    }
}