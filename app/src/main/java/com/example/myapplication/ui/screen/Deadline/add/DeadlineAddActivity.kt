package com.example.myapplication.ui.screen.Deadline.add

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
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavDestination.Companion.hasRoute
import androidx.navigation.NavHostController
import com.example.myapplication.R
import com.example.myapplication.data.modules.DeadlineType
import com.example.myapplication.data.modules.FrequencyType
import com.example.myapplication.data.modules.SelectKey
import com.example.myapplication.debug.scadenze
import com.example.myapplication.ui.component.BackButton
import com.example.myapplication.ui.component.CustomOutlineTextField
import com.example.myapplication.ui.component.DatePickerFieldToModal
import com.example.myapplication.ui.component.DeleteButton
import com.example.myapplication.ui.component.GenericCard
import com.example.myapplication.ui.NavigationRoute
import com.example.myapplication.ui.component.SplitButtonMenu
import com.example.myapplication.ui.component.TopAppBar
import java.time.format.DateTimeFormatter

@Composable
fun DeadlineAddActivity(
    expenseId : Int?,
    expenseType : DeadlineType,
    state : DeadlineAddState,
    actions: DeadlineAddActions,
    navController: NavHostController
) {
    val labelNew = stringResource(R.string.new_item)

    val snackBarHostState = remember { SnackbarHostState() }

    actions.populateView(expenseId, expenseType, labelNew)

    val previousBackStackEntry = navController.previousBackStackEntry

    val selectSearchText = stringResource(R.string.invoice_purchase)

    val currentBackStackEntry = navController.currentBackStackEntry
    val selectedItems by currentBackStackEntry?.savedStateHandle
        ?.getStateFlow<List<String>?>("selectedIds", emptyList())
        ?.collectAsStateWithLifecycle() ?: remember { mutableStateOf(emptyList())}

    LaunchedEffect(selectedItems) {
        selectedItems?.let{ ids ->
            if(ids.isNotEmpty()) {
                actions.setPurchaseInvoices(ids)
            }
        }
        currentBackStackEntry?.savedStateHandle?.remove<List<String>>("selectedIds")
    }

    LaunchedEffect(state.errorMessage) {
        state.errorMessage?.let { message ->
            snackBarHostState.showSnackbar(
                message = message,
                duration = SnackbarDuration.Short
            )
            actions.resetErrorMessage()
        }
    }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            TopAppBar(
                navigationIcon = {BackButton{navController.navigateUp()}},
                id = stringResource(R.string.deadline),
                trailingIcon = {
                    IconButton(
                        onClick = {
                            actions.saveNewExpense() { id, type ->
                                navController.navigate(
                                    NavigationRoute.SingleDeadlineSummary(id, type.name)
                                ) {
                                    popUpTo(NavigationRoute.AllDeadlinesSummary) { inclusive = false }
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
        },
        snackbarHost = { SnackbarHost(hostState = snackBarHostState) },
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
                SplitButtonMenu(
                    content =
                        if(state.expenseType == DeadlineType.Tipo){
                            stringResource(R.string.type)
                        }else{
                            state.expenseType.name
                        },
                    items = state.expenseTypeMenu,
                    heightMenu = (state.expenseTypeMenu.size * 55).dp
                )
            }
            if(state.expenseType == DeadlineType.Periodica){
                item{
                    SplitButtonMenu(
                        content =
                            if(state.frequency == FrequencyType.Nessuna){
                                stringResource(R.string.frequency)
                            }else{
                                state.frequency.name
                            },
                        items = state.frequencyMenu,
                        heightMenu = (state.frequencyMenu.size * 55).dp
                    )
                }
            }
            item{
                SplitButtonMenu(
                    content =
                        if(state.category == null){
                            stringResource(R.string.category)
                        }else if(state.category.id == 0 || state.category.name == labelNew) {
                            labelNew
                        }else{
                            state.categoryView
                        },
                    items = state.categoriesMenu,
                    heightMenu = 400.dp
                )
            }
            if(state.category?.id == 0){
                item{
                    CustomOutlineTextField(
                        label = "${stringResource(R.string.name)} ${stringResource(R.string.category)}",
                        value = state.category.name,
                        onValueChange = actions::setNewCategory
                    )
                }
            }
            item{
                CustomOutlineTextField(
                    label = stringResource(R.string.name),
                    value = state.name,
                    onValueChange = actions::setName
                )
            }
            item{
                DatePickerFieldToModal(
                    title = stringResource(R.string.date_issue),
                    onValueChange = actions::setIssueDate,
                    value = state.issueDate?.format(DateTimeFormatter.ofPattern("dd/MM/yyyy")) ?: ""
                )
            }
            item{
                DatePickerFieldToModal(
                    title =
                        if(state.expenseType == DeadlineType.Singola) {
                                stringResource(R.string.date_deadline)
                        }else{
                            stringResource(R.string.date_end)
                        },
                    onValueChange = actions::setDeadlineDate,
                    value = state.deadlineDate?.format(DateTimeFormatter.ofPattern("dd/MM/yyyy")) ?: ""
                )
            }
            item{
                CustomOutlineTextField(
                    label = stringResource(R.string.amount),
                    value = if(state.amount != null) "${state.amount}" else "",
                    onValueChange = actions::setAmount
                )
            }
            item{Spacer(Modifier.size(8.dp))}
            item{
                GenericCard(
                    text = stringResource(R.string.invoices_purchase),
                    trailingContent = {
                        Icon(
                            imageVector = Icons.Filled.ChevronRight,
                            contentDescription = stringResource(R.string.add_item),
                            modifier = Modifier.size(35.dp)
                        )
                    },
                    onClick = {
                        if(state.purchaseInvoiceSelected.isNotEmpty()){
                            navController.currentBackStackEntry
                                ?.savedStateHandle
                                ?.set("initialIds", actions.getPurchaseInvoiceSelected())
                        }
                        navController.navigate(
                            NavigationRoute.Select(
                                selectSearchText,
                                SelectKey.AllPurchaseInvoices,
                            )
                        )
                    }
                )
            }
            item{Spacer(Modifier.size(8.dp))}
            if(previousBackStackEntry?.destination?.hasRoute<NavigationRoute.SingleDeadlineSummary>() == true) {
                item {
                    DeleteButton {
                        actions.deleteExpense()
                        navController.navigate(NavigationRoute.AllDeadlinesSummary){
                            popUpTo(NavigationRoute.AllDeadlinesSummary){inclusive = true}
                            launchSingleTop = true
                        }
                    }
                }
                item { Spacer(Modifier.size(8.dp)) }
            }
        }
    }
}