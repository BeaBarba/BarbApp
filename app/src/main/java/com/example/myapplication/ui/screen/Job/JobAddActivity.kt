package com.example.myapplication.ui.screen.Job

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
import androidx.compose.material.icons.outlined.AddPhotoAlternate
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
import com.example.myapplication.debug.cantieri_menu
import com.example.myapplication.debug.interventi
import com.example.myapplication.debug.tipi_menu
import com.example.myapplication.ui.component.BackButton
import com.example.myapplication.ui.component.CustomOutlineTextField
import com.example.myapplication.ui.component.CustomTimePicker
import com.example.myapplication.ui.component.DatePickerFieldToModal
import com.example.myapplication.ui.component.DeleteButton
import com.example.myapplication.ui.component.GenericCard
import com.example.myapplication.ui.NavigationRoute
import com.example.myapplication.ui.component.SplitButtonMenu
import com.example.myapplication.ui.component.TopAppBar

@Composable
fun JobAddActivity(
    navController : NavHostController
){
    val previousBackStackEntry = navController.previousBackStackEntry
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            TopAppBar(
                id = stringResource(R.string.intervention),
                navigationIcon = {BackButton{navController.navigateUp()}},
                trailingIcon = {
                    IconButton(
                        onClick = {
                            navController.navigate(NavigationRoute.SingleJobSummary){
                                popUpTo(NavigationRoute.JobAdd){inclusive = true}
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
                    leadingContent = {
                        IconButton(
                            onClick = {navController.navigate(NavigationRoute.CustomerAdd)}
                        ) {
                            Icon(Icons.Filled.Add, contentDescription = stringResource(R.string.add_item))
                        }
                    },
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
                    leadingContent = {Icon(Icons.Filled.Add, contentDescription = stringResource(R.string.add_item))},
                    text = stringResource(R.string.add) + " " + stringResource(R.string.address).lowercase(),
                    trailingContent = {
                        Icon(
                            Icons.Filled.ChevronRight,
                            contentDescription = stringResource(R.string.edit),
                            modifier = Modifier.size(35.dp)
                        )
                    },
                    onClick = {navController.navigate(NavigationRoute.AddressAdd)}
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
            item{SplitButtonMenu(content = stringResource(R.string.type), items = tipi_menu, heightMenu = (tipi_menu.size *55).dp)}
            item{
                CustomOutlineTextField(
                    label = stringResource(R.string.people_number),
                    onValueChange = {}
                )
            }
            item{Spacer(Modifier.size(8.dp))}
            item{DatePickerFieldToModal()}
            item{Spacer(Modifier.size(8.dp))}
            item{CustomTimePicker(stringResource(R.string.start_time))}
            item{Spacer(Modifier.size(8.dp))}
            item{CustomTimePicker(stringResource(R.string.end_time))}
            item{Spacer(Modifier.size(8.dp))}
            item{
                CustomOutlineTextField(
                    label = stringResource(R.string.price),
                    onValueChange = {}
                )
            }
            item{Spacer(Modifier.size(8.dp))}
            item{
                CustomOutlineTextField(
                    label = stringResource(R.string.description),
                    onValueChange = {}
                )
            }
            item{Spacer(Modifier.size(8.dp))}
            item{SplitButtonMenu(content = stringResource(R.string.construction_site), items = cantieri_menu, heightMenu = (cantieri_menu.size *50).dp)}
            item{
                GenericCard(
                    text = stringResource(R.string.material),
                    trailingContent = {
                        Icon(
                            Icons.Filled.ChevronRight,
                            contentDescription = stringResource(R.string.edit),
                            modifier = Modifier.size(35.dp)
                        )
                    },
                    onClick = {navController.navigate(NavigationRoute.JobMaterials)}
                )
            }
            item{Spacer(Modifier.size(8.dp))}
            item{GenericCard(
                text = stringResource(R.string.photo_add),
                trailingContent = {
                    Icon(
                        imageVector = Icons.Outlined.AddPhotoAlternate,
                        contentDescription = stringResource(R.string.photo_add),
                        modifier = Modifier.size(40.dp)
                    )
                }
            )}
            item{Spacer(Modifier.size(8.dp))}
            if (previousBackStackEntry?.destination?.hasRoute<NavigationRoute.AllJobsSummary>() == false) {
                item {
                    DeleteButton {
                        interventi = interventi.subList(1, interventi.size)
                        navController.navigate(NavigationRoute.AllJobsSummary){
                            popUpTo(NavigationRoute.AllJobsSummary){inclusive = true}
                            launchSingleTop = true
                        }
                    }
                }
                item { Spacer(Modifier.size(8.dp)) }
            }
        }
    }
}