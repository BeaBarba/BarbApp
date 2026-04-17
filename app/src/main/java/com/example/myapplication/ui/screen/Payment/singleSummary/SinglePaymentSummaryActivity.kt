package com.example.myapplication.ui.screen.Payment.singleSummary

import androidx.compose.foundation.layout.Spacer
import androidx.compose.material3.Scaffold
import com.example.myapplication.ui.component.BackButton
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.myapplication.R
import com.example.myapplication.debug.listaFatture
import com.example.myapplication.debug.pagamenti
import com.example.myapplication.ui.component.Avatar
import com.example.myapplication.ui.component.DoubleKeyValueLabel
import com.example.myapplication.ui.component.GenericCard
import com.example.myapplication.ui.NavigationRoute
import com.example.myapplication.ui.component.KeyValueLabel
import com.example.myapplication.ui.component.TopAppBar
import java.time.format.DateTimeFormatter

@Composable
fun SinglePaymentSummaryActivity(
    paymentId : Int,
    state :SinglePaymentSummaryState,
    actions: SinglePaymentSummaryActions,
    navController: NavHostController
){
    actions.populateView(paymentId)

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            TopAppBar(
                navigationIcon = {BackButton{navController.navigateUp()}},
                id = stringResource(R.string.payment),
            )
        }
    ){ contentPadding ->
        LazyColumn(
            modifier = Modifier
                .padding(
                    top = contentPadding.calculateTopPadding() + 8.dp,
                    start = contentPadding.calculateRightPadding(LayoutDirection.Ltr) + 8.dp,
                    end = contentPadding.calculateLeftPadding(LayoutDirection.Ltr) + 8.dp,
                    bottom = contentPadding.calculateBottomPadding()
                )
        ){
            if(state.customerView != null) {
                item {
                    GenericCard(
                        leadingContent = {
                            Avatar(
                                char = state.customerView.second[0]
                            )
                        },
                        text = state.customerView.second,
                        onClick = { navController.navigate(NavigationRoute.SingleCustomerSummary(state.customerView.first)) }
                    )
                }
                item { Spacer(Modifier.size(8.dp)) }
            }
            item{
                GenericCard(
                    leadingContent = {
                        Icon(
                            painter = painterResource(R.drawable.description_24dp),
                            contentDescription = stringResource(R.string.invoice),
                            modifier = Modifier.size(35.dp)
                        )
                    },
                    text = state.revenue?.revenue?.invoice.toString(),
                    onClick = {navController.navigate(NavigationRoute.SingleInvoiceSummary(state.invoiceId))}
                )
            }
            item{Spacer(Modifier.size(8.dp))}
            item{
                DoubleKeyValueLabel(
                    firstTitle = stringResource(R.string.price),
                    firstDescription = state.revenue?.revenue?.amount.toString(),
                    secondTitle = stringResource(R.string.amount),
                    secondDescription = state.revenue?.revenue?.amountPaid.toString()
                )
            }
            item{Spacer(Modifier.size(8.dp))}
            item{
                KeyValueLabel(
                    title = stringResource(R.string.date_issue),
                    description = state.revenue?.revenue?.issueDate?.format(DateTimeFormatter.ofPattern("dd/MM/yyyy")
                    ) ?: ""
                )
            }
            item{Spacer(Modifier.size(8.dp))}
            item{
                KeyValueLabel(
                    title = stringResource(R.string.date_collection),
                    description = state.revenue?.revenue?.collectionDate?.format(DateTimeFormatter.ofPattern("dd/MM/yyyy")
                    ) ?: ""
                )
            }
            item{Spacer(Modifier.size(8.dp))}
            item{
                KeyValueLabel(
                    title = stringResource(R.string.percentage),
                    description = "${state.revenue?.revenue?.percent}%"
                )
            }
        }
    }
}