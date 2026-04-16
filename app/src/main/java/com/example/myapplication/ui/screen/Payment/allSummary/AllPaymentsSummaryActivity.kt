package com.example.myapplication.ui.screen.Payment.allSummary

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.calculateStartPadding
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.myapplication.R
import com.example.myapplication.ui.component.BackButton
import com.example.myapplication.ui.component.DropDownMenuPayments
import com.example.myapplication.ui.component.ListItemCheckbox
import com.example.myapplication.ui.NavigationRoute
import com.example.myapplication.ui.component.CustomDivider
import com.example.myapplication.ui.component.CustomOutlineTextField
import com.example.myapplication.ui.component.CustomSearchBar
import com.example.myapplication.ui.component.SplitButtonList
import com.example.myapplication.ui.component.TopAppBar

@Composable
fun AllPaymentsSummaryActivity(
    state : AllPaymentsSummaryState,
    actions : AllPaymentsSummaryActions,
    navController : NavHostController
){

    if (state.showDialog) {
        AlertDialog(
            onDismissRequest = actions::closeDialog,
            title = { Text(stringResource(R.string.amountPaid)) },
            text = {
                CustomOutlineTextField(
                    value = state.inputAmount,
                    onValueChange = actions::setAmountPaid,
                    label = stringResource(R.string.amount),
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Decimal),
                    singleLine = true
                )
            },
            confirmButton = {
                TextButton(onClick = actions::onConfirmPayment) {
                    Text(stringResource(R.string.ok))
                }
            },
            dismissButton = {
                TextButton(onClick = actions::closeDialog) {
                    Text(stringResource(R.string.cancel))
                }
            }
        )
    }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            TopAppBar(
                navigationIcon = {BackButton{navController.navigate(NavigationRoute.Home)}},
                id = stringResource(R.string.payments),
                trailingIcon = {
                    DropDownMenuPayments(
                        ascendingDateOnClick = actions::sortAscendingByDate,
                        descendingDateOnClick = actions::sortDescendingByDate,
                        ascendingAmountOnClick = actions::sortAscendingByAmount,
                        descendingAmountOnClick = actions::sortDescendingByAmount
                    )
                }
            )
        }
    ) { contentPadding ->

        var showItems by remember {mutableStateOf(false)}

        LazyColumn(
            modifier = Modifier
                .padding(
                    top = contentPadding.calculateTopPadding(),
                    start = contentPadding.calculateStartPadding(LayoutDirection.Ltr) + 8.dp,
                    end = contentPadding.calculateLeftPadding(LayoutDirection.Ltr) + 8.dp,
                    bottom = contentPadding.calculateBottomPadding()
                )
        ) {
            item{
                CustomSearchBar(
                    label = stringResource(R.string.customers),
                    value = state.searchString,
                    onValueChange = actions::searchPayment
                )
            }
            item{Spacer(Modifier.size(8.dp))}
            items(state.paymentsView.filter { !it.checked }){ item ->
                ListItemCheckbox(
                    char = if(item.customer.isBlank()) '?' else item.customer[0],
                    text = item.customer,
                    textDescription = item.invoiceNumber,
                    trailingText = item.price,
                    checked = item.checked,
                    onCheckedChange = {actions.setChecked(item.revenueId)},
                    onClick = {navController.navigate(NavigationRoute.SingleInvoiceSummary(item.revenueId))}
                )
                Spacer(Modifier.size(8.dp))
            }
            item{CustomDivider()}
            item{
                SplitButtonList(
                    text = stringResource(R.string.collected),
                    showItems = showItems,
                    onClick = {showItems = !showItems}
                )
            }
            item{Spacer(Modifier.size(8.dp))}
            if(showItems) {
                items(state.paymentsView.filter { it.checked }) { item ->
                    ListItemCheckbox(
                        char = if(item.customer.isBlank()) '?' else item.customer[0],
                        text = item.customer,
                        textDescription = item.invoiceNumber,
                        trailingText = item.price,
                        checked = item.checked,
                        onCheckedChange = {actions.setChecked(item.revenueId)},
                        onClick = {navController.navigate(NavigationRoute.SingleInvoiceSummary(item.revenueId))}
                    )
                    Spacer(Modifier.size(8.dp))
                }
            }
        }
    }
}