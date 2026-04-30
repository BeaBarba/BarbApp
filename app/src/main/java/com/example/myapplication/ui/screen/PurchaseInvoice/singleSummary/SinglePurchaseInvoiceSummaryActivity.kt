package com.example.myapplication.ui.screen.PurchaseInvoice.singleSummary

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.calculateEndPadding
import androidx.compose.foundation.layout.calculateStartPadding
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.myapplication.R
import com.example.myapplication.ui.component.BackButton
import com.example.myapplication.ui.component.GenericCard
import com.example.myapplication.ui.component.KeyValueLabel
import com.example.myapplication.ui.NavigationRoute
import com.example.myapplication.ui.component.CustomDivider
import com.example.myapplication.ui.component.TitleLabel
import com.example.myapplication.ui.component.TopAppBar
import com.example.myapplication.ui.component.materialTable
import com.example.myapplication.ui.theme.TableStyleDefaults
import java.time.format.DateTimeFormatter

@Composable
fun SinglePurchaseInvoiceSummaryActivity(
    purchaseInvoiceId : Int,
    state : SinglePurchaseInvoiceSummaryState,
    actions: SinglePurchaseInvoiceSummaryActions,
    navController : NavHostController
){

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
                            navController.navigate(NavigationRoute.PurchaseInvoiceAdd(state.purchaseInvoiceId))
                        },
                        colors = IconButtonDefaults.iconButtonColors(
                            contentColor = MaterialTheme.colorScheme.onPrimary
                        )
                    ) {
                        Icon(
                            painter = painterResource(R.drawable.edit_square_24dp),
                            contentDescription = stringResource(R.string.edit)
                        )
                    }
                }
            )
        }
    ) { contentPadding ->

        val headerColumns = TableStyleDefaults.defaultColumns()
        val tableStyle = TableStyleDefaults.defaultStyle()

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
                    title = stringResource(R.string.seller),
                    description = state.purchaseInvoiceFullDetails?.seller?.name ?: "",
                    weightTitle = 1.0f,
                    weighDescription = 2.0f
                )
            }
            item{Spacer(Modifier.size(8.dp))}
            item{
                KeyValueLabel(
                    title = stringResource(R.string.number),
                    description = state.purchaseInvoiceFullDetails?.purchaseInvoice?.number ?: "",
                    weightTitle = 1.0f,
                    weighDescription = 2.0f
                )
            }
            item{Spacer(Modifier.size(8.dp))}
            item{
                KeyValueLabel(
                    title = stringResource(R.string.date_issue),
                    description = state.purchaseInvoiceFullDetails?.purchaseInvoice?.year?.format(
                        DateTimeFormatter.ofPattern("dd/MM/yyyy")) ?: "",
                    weightTitle = 1.0f,
                    weighDescription = 2.0f
                )
            }
            item{CustomDivider()}
            item{TitleLabel(stringResource(R.string.bubbles))}
            item{Spacer(Modifier.size(8.dp))}
            state.purchaseInvoiceFullDetails?.let {
                itemsIndexed(state.purchaseInvoiceFullDetails.bubbles) { index, item ->
                    GenericCard(
                        leadingContent = {
                            Icon(
                                painter = painterResource(R.drawable.receipt_bolle),
                                contentDescription = stringResource(R.string.bubble)
                            )
                        },
                        text = item.number,
                        textDescription = item.date.format(DateTimeFormatter.ofPattern("dd/MM/yyyy")),
                        onClick = {navController.navigate(NavigationRoute.SingleBubbleSummary(item.id))}
                    )
                    if (index < 2) {
                        Spacer(Modifier.size(8.dp))
                    }
                }
            }
            if(state.materials.isNotEmpty()) {
                item { CustomDivider() }
                item { TitleLabel(stringResource(R.string.materials)) }
                item { Spacer(Modifier.size(8.dp)) }
                materialTable(state.materials, headerColumns, tableStyle)
            }
            item{Spacer(Modifier.size(8.dp))}
        }
    }
}