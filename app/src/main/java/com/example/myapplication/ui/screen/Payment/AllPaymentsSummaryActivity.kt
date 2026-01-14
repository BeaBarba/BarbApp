package com.example.myapplication.ui.screen.Payment

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.calculateStartPadding
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
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
import androidx.navigation.NavHostController
import com.example.myapplication.R
import com.example.myapplication.debug.pagamenti
import com.example.myapplication.ui.component.AddButton
import com.example.myapplication.ui.component.BackButton
import com.example.myapplication.ui.component.DropDownMenuPayments
import com.example.myapplication.ui.component.ListItemCheckbox
import com.example.myapplication.ui.NavigationRoute
import com.example.myapplication.ui.component.SearchAppBar
import com.example.myapplication.ui.component.TopAppBar

@Composable
fun AllPaymentsSummaryActivity(
    navController : NavHostController
){
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            TopAppBar(
                navigationIcon = {BackButton{navController.navigate(NavigationRoute.Home)}},
                id = stringResource(R.string.payments),
                trailingIcon = {DropDownMenuPayments()}
            )
        },
        floatingActionButton = {AddButton{navController.navigate(NavigationRoute.PaymentAdd)}}
    ) {contentPadding ->
        LazyColumn(
            modifier = Modifier
                .padding(
                    top = contentPadding.calculateTopPadding(),
                    start = contentPadding.calculateStartPadding(LayoutDirection.Ltr) + 8.dp,
                    end = contentPadding.calculateLeftPadding(LayoutDirection.Ltr) + 8.dp,
                    bottom = contentPadding.calculateBottomPadding()
                )
        ) {
            item{SearchAppBar(stringResource(R.string.customers))}
            items(pagamenti.subList(5,10)){ item ->
                var checked by remember{mutableStateOf(false)}
                ListItemCheckbox(
                    char = item.cliente.get(0),
                    text = item.cliente,
                    textDescription = item.indirizzo,
                    trailingText = item.prezzo,
                    checked = checked,
                    onCheckedChange = {checked = !checked},
                    onClick = {navController.navigate(NavigationRoute.SinglePaymentSummary)}
                )
                Spacer(Modifier.size(8.dp))
            }
            item{Spacer(Modifier.size(80.dp))}
        }
    }
}