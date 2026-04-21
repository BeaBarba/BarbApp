package com.example.myapplication.ui.screen.Deadline.singleSummary

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.calculateEndPadding
import androidx.compose.foundation.layout.calculateStartPadding
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Mode
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.myapplication.R
import com.example.myapplication.data.modules.DeadlineType
import com.example.myapplication.data.modules.JobType
import com.example.myapplication.ui.component.BackButton
import com.example.myapplication.ui.component.DoubleKeyValueLabel
import com.example.myapplication.ui.component.GenericCard
import com.example.myapplication.ui.component.KeyValueLabel
import com.example.myapplication.ui.NavigationRoute
import com.example.myapplication.ui.component.CustomDivider
import com.example.myapplication.ui.component.CustomOutlineTextField
import com.example.myapplication.ui.component.DatePickerFieldToModal
import com.example.myapplication.ui.component.TitleLabel
import com.example.myapplication.ui.component.TopAppBar
import com.example.myapplication.ui.component.checkColor
import java.time.format.DateTimeFormatter

@Composable
fun SingleDeadlineSummaryActivity (
    expenseId : Int,
    expenseType : DeadlineType,
    state: SingleDeadlineSummaryState,
    actions: SingleDeadlineSummaryActions,
    navController : NavHostController
) {
    LaunchedEffect(expenseId) {
        actions.populateView(expenseId, expenseType)
    }

    if (state.showDialog) {
        AlertDialog(
            onDismissRequest = actions::closeDialog,
            title = { Text(stringResource(R.string.amountPaid)) },
            text = {
                DatePickerFieldToModal(
                    title = stringResource(R.string.date_payment),
                    onValueChange = actions::setDatePaid,
                    value = state.inputDate.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"))
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
            TopAppBar(id = stringResource(R.string.deadline),
                navigationIcon = {BackButton{navController.navigateUp()}},
                trailingIcon = {
                    IconButton(
                        onClick = {navController.navigate(NavigationRoute.DeadlineAdd(state.id, state.type.toString()))},
                        colors = IconButtonDefaults.iconButtonColors(
                            contentColor = MaterialTheme.colorScheme.onPrimary
                        )
                    ) {
                        Icon(
                            painterResource(R.drawable.edit_square_24dp),
                            contentDescription = stringResource(R.string.edit)
                        )
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
            item{
                KeyValueLabel(
                    title = stringResource(R.string.category),
                    description = state.category,
                    weightTitle = 1.0f,
                    weighDescription = 2.0f
                )
            }
            item{Spacer(Modifier.size(8.dp))}
            item {
                KeyValueLabel(
                    title = stringResource(R.string.name),
                    description = state.name,
                    weightTitle = 1.0f,
                    weighDescription = 2.0f
                )
            }
            item{ Spacer(Modifier.size(8.dp)) }
            item{
                KeyValueLabel(
                    title = stringResource(R.string.type),
                    description = "${state.type}",
                    weightTitle = 1.0f,
                    weighDescription = 2.0f
                )
            }
            item{Spacer(Modifier.size(8.dp))}
            if(state.purchaseInvoice != null){
                item {
                    KeyValueLabel(
                        title = stringResource(R.string.seller),
                        description = state.seller,
                        weightTitle = 1.0f,
                        weighDescription = 2.0f
                    )
                }
                item{Spacer(Modifier.size(8.dp))}
            }
            if(state.type == DeadlineType.Periodica){
                item{
                    KeyValueLabel(
                        title = stringResource(R.string.frequency),
                        description = state.frequency.name,
                        weightTitle = 1.0f,
                        weighDescription = 2.0f
                    )
                }
                item{Spacer(Modifier.size(8.dp))}
            }
            item{
                DoubleKeyValueLabel(
                    firstTitle = stringResource(R.string.price),
                    firstDescription = "${state.price} €",
                    secondTitle = stringResource(R.string.date),
                    secondDescription = state.deadlineDate?.format(DateTimeFormatter.ofPattern("dd/MM/yyyy")) ?: ""
                )
            }
            item{Spacer(Modifier.size(8.dp))}
            if(state.purchaseInvoice != null){
                item {
                    GenericCard(
                        leadingContent = {
                            Icon(
                                painter = painterResource(R.drawable.description_24dp),
                                contentDescription = stringResource(R.string.invoice_purchase),
                                modifier = Modifier.size(35.dp)
                            )
                        },
                        text = state.purchaseInvoice.purchaseInvoice.number,
                        onClick = {
                            navController.navigate(
                                NavigationRoute.SinglePurchaseInvoiceSummary(
                                    state.purchaseInvoice.purchaseInvoice.id
                                )
                            )
                        }
                    )
                }
            }
            item{CustomDivider()}
            if(state.payments.isNotEmpty()) {
                item{TitleLabel(title = stringResource(R.string.payments))}
                item{Spacer(Modifier.size(8.dp))}
                items(state.payments) { payment ->
                    GenericCard(
                        text = "${stringResource(R.string.date_payment)} -> ${payment?.paymentDate?.format(DateTimeFormatter.ofPattern("dd/MM/yyyy")) ?: ""}",
                        textDescription = "${stringResource(R.string.date_issue)} -> ${payment?.issueDate?.format
                            (DateTimeFormatter.ofPattern("dd/MM/yyyy")) ?: ""}",
                        trailingContent = {
                            IconButton(
                                onClick = {
                                    payment?.id?.let {
                                        actions.openDialog(payment.id)
                                    }
                                }
                            ) {
                                Icon(
                                    imageVector = Icons.Filled.Mode,
                                    contentDescription = stringResource(R.string.edit),
                                    tint = checkColor(JobType.NONE.name, onPrimaryContainer = true)
                                )
                            }
                        }

                    )
                    Spacer(Modifier.size(8.dp))
                }
            }
        }
    }
}