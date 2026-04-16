package com.example.myapplication.ui.screen.Invoice.singleSummary

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.calculateEndPadding
import androidx.compose.foundation.layout.calculateStartPadding
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.myapplication.R
import com.example.myapplication.ui.NavigationRoute
import com.example.myapplication.ui.component.Avatar
import com.example.myapplication.ui.component.BackButton
import com.example.myapplication.ui.component.GenericCard
import com.example.myapplication.ui.component.KeyValueLabel
import com.example.myapplication.ui.component.TitleLabel
import com.example.myapplication.ui.component.TopAppBar

@Composable
fun SingleInvoiceSummaryActivity(
    invoiceId : Int,
    state : SingleInvoiceSummaryState,
    actions: SingleInvoiceSummaryActions,
    navController : NavHostController
){
    actions.populateView(invoiceId)

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            TopAppBar(
                navigationIcon = {BackButton{navController.navigateUp()}},
                id = stringResource(R.string.invoice),
                trailingIcon = {
                    IconButton(
                        onClick = {navController.navigate(NavigationRoute.InvoiceAdd(state.invoicedId))},
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
            if(state.customer != null) {
                item {
                    val customerName = actions.getCustomerName()
                    GenericCard(
                        leadingContent = {
                            Avatar(char = customerName[0])
                        },
                        text = customerName,
                        onClick = {
                            navController.navigate(NavigationRoute.SingleCustomerSummary(state.customer.customer.cf))
                        }
                    )
                }
            }
            item{Spacer(Modifier.size(8.dp))}
            item{TitleLabel(stringResource(R.string.data))}
            item{Spacer(Modifier.size(8.dp))}
            item{
                KeyValueLabel(
                    title = stringResource(R.string.number),
                    description = "${state.invoice?.revenue?.invoice}"
                )
            }
            item{Spacer(Modifier.size(8.dp))}
            item{
                KeyValueLabel(
                    title = stringResource(R.string.date_issue),
                    description = "${state.invoice?.revenue?.issueDate}"
                )
            }
            item{Spacer(Modifier.size(8.dp))}
            item{
                KeyValueLabel(
                    title = stringResource(R.string.amount),
                    description = "${state.invoice?.revenue?.amount}"
                )
            }
            item{Spacer(Modifier.size(8.dp))}
            if(state.invoice?.workSite != null){
                item{TitleLabel(stringResource(R.string.construction_site))}
                item{Spacer(Modifier.size(8.dp))}
                item {
                    GenericCard(
                        leadingContent = {
                            Icon(
                                painterResource(R.drawable.brickwall),
                                contentDescription = stringResource(R.string.construction_site)
                            )
                        },
                        text = "${state.invoice.workSite.address.address} ${state.invoice.workSite.address
                            .houseNumber}",
                        textDescription = "${state.invoice.workSite.address.city} (${state.invoice.workSite.address
                            .province})",
                        onClick = {
                            navController.navigate(NavigationRoute.SingleWorkSiteSummary(state.invoice.workSite.workSite.id))
                        }
                    )
                    Spacer(Modifier.size(8.dp))
                }
            }
            if(state.invoice?.job != null) {
                item{TitleLabel(stringResource(R.string.interventions))}
                item{Spacer(Modifier.size(8.dp)) }
                item{
                    val type = state.type
                    GenericCard(
                        type = type.name,
                        leadingContent = {
                            Avatar(
                                char = type.name[0],
                                type = type.name
                            )
                        },
                        text = "${state.invoice.job.address.address} ${state.invoice.job.address
                            .houseNumber}",
                        textDescription = "${state.invoice.job.address.city} (${state.invoice.job.address
                            .province})",
                        onClick = {
                            navController.navigate(NavigationRoute.SingleJobSummary(state.invoice.job.job.id))
                        }
                    )
                    Spacer(Modifier.size(8.dp))
                }
            }
        }
    }
}