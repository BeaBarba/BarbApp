package com.example.myapplication.ui.screen.PurchaseInvoice.add

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.calculateEndPadding
import androidx.compose.foundation.layout.calculateStartPadding
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavDestination.Companion.hasRoute
import androidx.navigation.NavHostController
import com.example.myapplication.R
import com.example.myapplication.data.modules.SelectKey
import com.example.myapplication.ui.NavigationRoute
import com.example.myapplication.ui.component.BackButton
import com.example.myapplication.ui.component.CustomDivider
import com.example.myapplication.ui.component.CustomOutlineTextField
import com.example.myapplication.ui.component.DatePickerFieldToModal
import com.example.myapplication.ui.component.DeleteButton
import com.example.myapplication.ui.component.GenericCard
import com.example.myapplication.ui.component.MenuItem
import com.example.myapplication.ui.component.SplitButtonMenu
import com.example.myapplication.ui.component.TitleLabel
import com.example.myapplication.ui.component.TopAppBar
import java.time.format.DateTimeFormatter

@Composable
fun PurchaseInvoiceAddActivity(
    purchaseInvoiceId : Int?,
    sellers : List<MenuItem>,
    state : PurchaseInvoiceAddState,
    actions : PurchaseInvoiceAddActions,
    navController : NavHostController
) {
    val bubblesSearchText = stringResource(R.string.bubbles)
    val materialsSearchText = stringResource(R.string.materials)

    val previousBackStackEntry = navController.previousBackStackEntry
    val currentBackStackEntry = navController.currentBackStackEntry

    val bubblesItems by currentBackStackEntry?.savedStateHandle
        ?.getStateFlow<List<String>?>("bubbles", null)
        ?.collectAsStateWithLifecycle(initialValue = null) ?: remember { mutableStateOf(null)}

    val materialsIds by currentBackStackEntry?.savedStateHandle
        ?.getStateFlow<List<String>?>("selectedIds", null)
        ?.collectAsStateWithLifecycle(initialValue = null) ?: remember { mutableStateOf(null)}

    LaunchedEffect(bubblesItems) {
        bubblesItems?.let{ ids ->
            actions.setBubbles(ids)
            currentBackStackEntry?.savedStateHandle?.remove<List<String>>("bubbles")
        }
    }

    LaunchedEffect(materialsIds) {
        materialsIds?.let{ ids ->
            actions.setMaterials(ids)
            currentBackStackEntry?.savedStateHandle?.remove<List<String>>("selectedIds")
        }
    }

    LaunchedEffect(purchaseInvoiceId) {
        actions.populateView(purchaseInvoiceId)
    }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            TopAppBar(
                navigationIcon = {BackButton{navController.navigateUp()}},
                id = stringResource(R.string.invoice_purchase),
                trailingIcon = {
                    IconButton(
                        onClick = {
                            actions.save { id ->
                                navController.navigate(NavigationRoute.SinglePurchaseInvoiceSummary(id)) {
                                    popUpTo(NavigationRoute.AllPurchaseInvoicesSummary) { inclusive = false }
                                }
                            }
                        },
                        colors = IconButtonDefaults.iconButtonColors(
                            contentColor = MaterialTheme.colorScheme.onPrimary
                        ),
                        enabled = actions.checkRequirements()
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
            item{
                SplitButtonMenu(
                    content =
                        when(state.seller.first){
                            -1 -> {stringResource(R.string.seller)}
                            0 -> {stringResource(R.string.new_item)}
                            else -> {state.seller.second}
                        },
                    items = sellers,
                    heightMenu = if(sellers.size > 10) 300.dp else (sellers.size * 55).dp
                )
            }
            if(state.seller.first == 0){
                item{
                    CustomOutlineTextField(
                        label = stringResource(R.string.name),
                        value = state.newSeller,
                        onValueChange = actions::setNewSeller
                    )
                }
            }
            item{
                CustomOutlineTextField(
                    label = stringResource(R.string.number),
                    value = state.number,
                    onValueChange = actions::setNumber
                )
            }
            item{
                DatePickerFieldToModal(
                    title = stringResource(R.string.date_issue),
                    value = state.issueDate?.format(DateTimeFormatter.ofPattern("dd/MM/yyyy")) ?: "",
                    onValueChange = actions::setIssueDate
                )
            }
            item{CustomDivider()}
            item{
                GenericCard(
                    leadingContent = {
                        Icon(
                            painter = painterResource(R.drawable.receipt_bolle),
                            contentDescription = stringResource(R.string.bubbles)
                        )
                    },
                    text = stringResource(R.string.bubbles),
                    trailingContent = {
                        Icon(
                            imageVector = Icons.Filled.ChevronRight,
                            contentDescription = stringResource(R.string.select),
                            modifier = Modifier.size(35.dp)
                        )
                    },
                    onClick = {
                        navController.currentBackStackEntry
                            ?.savedStateHandle
                            ?.set("initialIds", actions.getBubblesIds())

                        navController.navigate(NavigationRoute.Select(bubblesSearchText, SelectKey.AllBubbles))
                    }
                )
            }
            item{CustomDivider()}
            item {
                GenericCard(
                    text = stringResource(R.string.material),
                    trailingContent = {
                        Icon(
                            Icons.Filled.ChevronRight,
                            contentDescription = stringResource(R.string.select),
                            modifier = Modifier.size(35.dp)
                        )
                    },
                    onClick = {
                        navController.currentBackStackEntry
                            ?.savedStateHandle
                            ?.set("initialIds", actions.getMaterialsIds())

                        navController.navigate(NavigationRoute.Select(materialsSearchText, SelectKey.AllMaterials))
                    }
                )
            }
            item {Spacer(Modifier.size(8.dp))}
            itemsIndexed(state.materials){ index, item ->
                TitleLabel("${item.material.category} - ${item.material.model}")

                CustomOutlineTextField(
                    label = stringResource(R.string.quantity),
                    value = item.quantity.toString(),
                    onValueChange = { value ->
                        actions.setMaterialQuantity(item.material.id, value)
                    }
                )
                CustomOutlineTextField(
                    label = stringResource(R.string.unit_price),
                    value = item.unitPrice.toString(),
                    onValueChange = { value ->
                        actions.setMaterialPrice(item.material.id, value)
                    }
                )
                CustomOutlineTextField(
                    label = stringResource(R.string.vat),
                    value = item.vatNumber.toString(),
                    onValueChange = { value ->
                        actions.setMaterialVat(item.material.id, value)
                    }
                )
                if(index < state.materials.size - 1) {
                    CustomDivider()
                }
            }
            item{ Spacer(Modifier.size(8.dp)) }

            if (previousBackStackEntry?.destination?.hasRoute<NavigationRoute.SinglePurchaseInvoiceSummary>() == true) {
               item {

                    DeleteButton {
                        actions.delete(state.purchaseInvoiceId)
                        navController.navigate(NavigationRoute.AllPurchaseInvoicesSummary){
                            popUpTo(NavigationRoute.AllPurchaseInvoicesSummary){inclusive = true}
                            launchSingleTop = true
                        }
                    }
                }
                item { Spacer(Modifier.size(8.dp)) }
            }
        }
    }
}