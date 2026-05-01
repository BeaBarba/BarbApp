package com.example.myapplication.ui.screen.Invoice.add

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.calculateEndPadding
import androidx.compose.foundation.layout.calculateStartPadding
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.ChevronRight
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavDestination.Companion.hasRoute
import androidx.navigation.NavHostController
import com.example.myapplication.R
import com.example.myapplication.data.modules.SelectKey
import com.example.myapplication.ui.component.BackButton
import com.example.myapplication.ui.component.CustomOutlineTextField
import com.example.myapplication.ui.component.DatePickerFieldToModal
import com.example.myapplication.ui.component.DeleteButton
import com.example.myapplication.ui.component.GenericCard
import com.example.myapplication.ui.NavigationRoute
import com.example.myapplication.ui.component.TopAppBar

@Composable
fun InvoiceAddActivity(
    invoiceId : Int?,
    state : InvoiceAddState,
    actions: InvoiceAddActions,
    navController: NavHostController
) {
    val selectSearchCustomer = stringResource(R.string.customer)
    val selectSearchJobs = stringResource(R.string.intervention)
    val selectSearchWorksite = stringResource(R.string.construction_site)

    val previousBackStackEntry = navController.previousBackStackEntry
    val currentBackStackEntry = navController.currentBackStackEntry

    val selectedCustomer by currentBackStackEntry?.savedStateHandle
        ?.getStateFlow<List<String>?>("customers", emptyList())
        ?.collectAsStateWithLifecycle() ?: remember { mutableStateOf(emptyList())}

    val selectedJob by currentBackStackEntry?.savedStateHandle
        ?.getStateFlow<List<String>?>("jobs", emptyList())
        ?.collectAsStateWithLifecycle() ?: remember { mutableStateOf(emptyList())}

    val selectedWorksite by currentBackStackEntry?.savedStateHandle
        ?.getStateFlow<List<String>?>("worksites", emptyList())
        ?.collectAsStateWithLifecycle() ?: remember { mutableStateOf(emptyList())}

    LaunchedEffect(selectedCustomer) {
        selectedCustomer?.let{ ids ->
            if(ids.isNotEmpty()) {
                actions.setCustomer(ids.first())
            }
        }
        currentBackStackEntry?.savedStateHandle?.remove<List<String>>("customers")
    }

    LaunchedEffect(selectedJob) {
        selectedJob?.let{ ids ->
            if(ids.isNotEmpty()) {
                actions.setJob(ids.first().toInt())
            }
        }
        currentBackStackEntry?.savedStateHandle?.remove<List<String>>("jobs")
    }

    LaunchedEffect(selectedWorksite) {
        selectedWorksite?.let{ ids ->
            if(ids.isNotEmpty()) {
                actions.setWorksite(ids.first().toInt())
            }
        }
        currentBackStackEntry?.savedStateHandle?.remove<List<String>>("worksites")
    }

    LaunchedEffect(invoiceId) {
        actions.populateView(invoiceId)
    }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            TopAppBar(
                navigationIcon = {BackButton{navController.navigateUp()}},
                id = stringResource(R.string.invoice),
                trailingIcon = {
                    IconButton(
                        onClick = {
                            actions.saveInvoice { id ->
                                navController.navigate(NavigationRoute.SingleInvoiceSummary(id)) {
                                    popUpTo(NavigationRoute.AllInvoicesSummary) { inclusive = false }
                                }
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
                    onClick = {
                        if(!state.customerCf.isNullOrEmpty()) {
                            navController.currentBackStackEntry
                                ?.savedStateHandle
                                ?.set("initialIds", listOf(state.customerCf))
                        }
                        navController.navigate(
                            NavigationRoute.Select(
                                selectSearchCustomer,
                                SelectKey.AllCustomers
                            )
                        )
                    }
                )
            }
            item{Spacer(Modifier.size(8.dp))}
            item{
                CustomOutlineTextField(
                    label = stringResource(R.string.number),
                    value = state.invoiceNumber,
                    onValueChange = actions::setInvoiceNumber,
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Decimal)
                )
            }
            item{Spacer(Modifier.size(8.dp))}
            item{
                DatePickerFieldToModal(
                    title = stringResource(R.string.date_issue),
                    value = state.issueDate,
                    onValueChange = actions::setIssueDate
                )
            }
            item{Spacer(Modifier.size(8.dp))}
            item{
                CustomOutlineTextField(
                    label = stringResource(R.string.amount),
                    value = state.amount,
                    onValueChange = actions::setAmount,
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Decimal)
                )
            }
            item{Spacer(Modifier.size(8.dp))}
            item{
                GenericCard(
                    text = stringResource(R.string.interventions),
                    trailingContent = {
                        Icon(
                            imageVector = Icons.Filled.ChevronRight,
                            contentDescription = stringResource(R.string.edit),
                            modifier = Modifier.size(35.dp)
                        )
                    },
                    onClick = {
                        if(state.job != null) {
                            navController.currentBackStackEntry
                                ?.savedStateHandle
                                ?.set("initialIds", listOf(state.job.toString()))
                        }
                        navController.navigate(
                            NavigationRoute.Select(
                                selectSearchJobs,
                                SelectKey.AllJobs
                            )
                        )
                    }
                )
            }
            item{Spacer(Modifier.size(8.dp))}
            item{
                GenericCard(
                    text = stringResource(R.string.construction_sites),
                    trailingContent = {
                        Icon(
                            imageVector = Icons.Filled.ChevronRight,
                            contentDescription = stringResource(R.string.edit),
                            modifier = Modifier.size(35.dp)
                        )
                    },
                    onClick = {
                        if(state.worksite != null) {
                            navController.currentBackStackEntry
                                ?.savedStateHandle
                                ?.set("initialIds", listOf(state.worksite.toString()))
                        }
                        navController.navigate(
                            NavigationRoute.Select(
                                selectSearchWorksite,
                                SelectKey.AllWorksites
                            )
                        )
                    }
                )
            }
            item{Spacer(Modifier.size(8.dp))}
            if(previousBackStackEntry?.destination?.hasRoute<NavigationRoute.SingleInvoiceSummary>() == true) {
                item {
                    DeleteButton {
                        invoiceId?.let {
                            actions.deleteInvoice(invoiceId){
                                navController.navigate(NavigationRoute.AllInvoicesSummary){
                                    popUpTo(NavigationRoute.AllInvoicesSummary){inclusive = true}
                                    launchSingleTop = true
                                }
                            }
                        }
                    }
                }
                item {Spacer(Modifier.size(8.dp))}
            }
        }
    }
}