package com.example.myapplication.ui.screen.Payment

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
import com.example.myapplication.debug.interventi
import com.example.myapplication.debug.listaFatture
import com.example.myapplication.debug.pagamenti
import com.example.myapplication.ui.component.Avatar
import com.example.myapplication.ui.component.BackButton
import com.example.myapplication.ui.component.CustomOutlineTextField
import com.example.myapplication.ui.component.DatePickerFieldToModal
import com.example.myapplication.ui.component.DeleteButton
import com.example.myapplication.ui.component.GenericCard
import com.example.myapplication.ui.component.ListItemCheckbox
import com.example.myapplication.ui.NavigationRoute
import com.example.myapplication.ui.component.DoubleKeyValueLabel
import com.example.myapplication.ui.component.KeyValueLabel
import com.example.myapplication.ui.component.SplitButtonList
import com.example.myapplication.ui.component.TopAppBar

@Composable
fun PaymentAddActivity(
    navController: NavHostController
){
    val previousBackStackEntry = navController.previousBackStackEntry
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            TopAppBar(
                navigationIcon = {BackButton{navController.navigateUp()}},
                id = stringResource(R.string.payment),
                trailingIcon = {
                    IconButton(
                        onClick = {
                            navController.navigate(NavigationRoute.SinglePaymentSummary){
                                popUpTo(NavigationRoute.PaymentAdd){inclusive = true}
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
    ){ contentPadding ->
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
                    text = stringResource(R.string.invoices),
                    trailingContent = {
                        Icon(Icons.Filled.ChevronRight, contentDescription = stringResource(R.string.edit))
                    },
                    onClick = {navController.navigate(NavigationRoute.Select("Fattura", "InvoiceAdd"))}
                )
            }
            item{Spacer(Modifier.size(8.dp))}
            item{
                DoubleKeyValueLabel(
                    firstTitle = stringResource(R.string.price),
                    firstDescription = "1.00,00€",
                    secondTitle = stringResource(R.string.date),
                    secondDescription = listaFatture.get(0).type,
                )
            }
            item{Spacer(Modifier.size(8.dp))}
            item{CustomOutlineTextField(stringResource(R.string.amount))}
            item{Spacer(Modifier.size(8.dp))}
            item{DatePickerFieldToModal(stringResource(R.string.date_collection))}
            item{Spacer(Modifier.size(8.dp))}
            item{CustomOutlineTextField(stringResource(R.string.percentage))}
            item{Spacer(Modifier.size(8.dp))}
            if (previousBackStackEntry?.destination?.hasRoute<NavigationRoute.SinglePaymentSummary>() == true) {
                item {
                    DeleteButton {
                        pagamenti = pagamenti.subList(1, pagamenti.size)
                        navController.navigate(NavigationRoute.AllPaymentsSummary){
                            popUpTo(NavigationRoute.AllPaymentsSummary){inclusive = true}
                            launchSingleTop = true
                        }
                    }
                }
                item {Spacer(Modifier.size(8.dp))}
            }
        }
    }
}