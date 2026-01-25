package com.example.myapplication.ui.screen.Invoice

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.calculateEndPadding
import androidx.compose.foundation.layout.calculateStartPadding
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.ChevronRight
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
import com.example.myapplication.debug.customersType
import com.example.myapplication.debug.interventi
import com.example.myapplication.debug.scadenze
import com.example.myapplication.ui.component.BackButton
import com.example.myapplication.ui.component.CustomOutlineTextField
import com.example.myapplication.ui.component.DatePickerFieldToModal
import com.example.myapplication.ui.component.DeleteButton
import com.example.myapplication.ui.component.GenericCard
import com.example.myapplication.ui.NavigationRoute
import com.example.myapplication.ui.component.ListItemCheckbox
import com.example.myapplication.ui.component.SplitButtonList
import com.example.myapplication.ui.component.TopAppBar

@Composable
fun InvoiceAddActivity(
    navController: NavHostController
) {
    val previousBackStackEntry = navController.previousBackStackEntry
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            TopAppBar(
                navigationIcon = {BackButton{navController.navigateUp()}},
                id = stringResource(R.string.invoice),
                trailingIcon = {
                    IconButton(
                        onClick = {
                            navController.navigate(NavigationRoute.SingleInvoiceSummary){
                                popUpTo(NavigationRoute.InvoiceAdd){inclusive = true}
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
        var showItems by remember{ mutableStateOf(false) }
        LazyColumn(
            modifier = Modifier
                .padding(
                    top = contentPadding.calculateTopPadding() + 8.dp,
                    start = contentPadding.calculateStartPadding(LayoutDirection.Ltr) + 8.dp,
                    end = contentPadding.calculateEndPadding(LayoutDirection.Ltr) + 8.dp,
                    bottom = contentPadding.calculateBottomPadding()
                )
        ) {
            item{
                GenericCard(
                    text = stringResource(R.string.customer),
                    trailingContent = {
                        Icon(
                            imageVector = Icons.Filled.ChevronRight,
                            contentDescription = stringResource(R.string.edit),
                            modifier = Modifier.size(35.dp)
                        )
                    },
                    onClick = {navController.navigate(NavigationRoute.Select("Cliente", "CustomerAdd"))}
                )
            }
            item{Spacer(Modifier.size(8.dp))}
            item{
                CustomOutlineTextField(
                    label = stringResource(R.string.number),
                    onValueChange = {}
                )
            }
            item{Spacer(Modifier.size(8.dp))}
            item{
                DatePickerFieldToModal(
                    title = stringResource(R.string.date_issue),
                    value = "",
                    onValueChange = {}
                )
            }
            item{Spacer(Modifier.size(8.dp))}
            item{
                CustomOutlineTextField(
                    label = stringResource(R.string.amount),
                    onValueChange = {}
                )
            }
            item{Spacer(Modifier.size(8.dp))}
            item{
                SplitButtonList(
                    text = stringResource(R.string.interventions),
                    showItems = showItems,
                    onClick = {showItems = !showItems}
                )
            }
            item{Spacer(Modifier.size(8.dp))}
            if(showItems) {
                items(interventi){ item ->
                    var checked by remember { mutableStateOf(false) }
                    ListItemCheckbox(
                        char = item.tipo.get(0),
                        text = item.indirizzo + ", " + item.comune,
                        textDescription = item.data,
                        checked = checked,
                        onCheckedChange = {checked = !checked},
                        type = item.tipo,
                        onClick = {navController.navigate(NavigationRoute.SingleJobSummary)}
                    )
                    Spacer(Modifier.size(8.dp))
                }
            }
            item{Spacer(Modifier.size(8.dp))}
            if(previousBackStackEntry?.destination?.hasRoute<NavigationRoute.SingleInvoiceSummary>() == true) {
                item {
                    DeleteButton {
                        /* Delete item from list */
                        navController.navigate(NavigationRoute.AllInvoicesSummary){
                            popUpTo(NavigationRoute.AllInvoicesSummary){inclusive = true}
                            launchSingleTop = true
                        }
                    }
                }
                item {Spacer(Modifier.size(8.dp))}
            }
        }
    }
}