package com.example.myapplication.ui.screen.Cart

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.calculateEndPadding
import androidx.compose.foundation.layout.calculateStartPadding
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.myapplication.R
import com.example.myapplication.debug.item_num
import com.example.myapplication.debug.prodotti
import com.example.myapplication.ui.component.AddButton
import com.example.myapplication.ui.component.BackButton
import com.example.myapplication.ui.component.CartCard
import com.example.myapplication.ui.component.CustomAlertDialog
import com.example.myapplication.ui.NavigationRoute
import com.example.myapplication.ui.component.TopAppBar

@Composable
fun CartActivity(
    state : CartState,
    actions: CartActions,
    navController : NavHostController
){

    actions.populateCart()

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            TopAppBar(id = stringResource(R.string.cart),
                navigationIcon = {BackButton {navController.navigateUp()}},
                trailingIcon = {
                    IconButton(
                        onClick = {navController.navigate(NavigationRoute.BubbleAdd(0))}
                    ) {
                        Icon(
                            painter = painterResource(R.drawable.description_24dp),
                            contentDescription = stringResource(R.string.bubble_add),
                            tint = MaterialTheme.colorScheme.onPrimary
                        )
                    }
                })
        },
        floatingActionButton = {AddButton{navController.navigate(NavigationRoute.MaterialAdd)} }
    ) { contentPadding ->
        LazyColumn(
            modifier = Modifier
                .padding(
                    top = contentPadding.calculateTopPadding(),
                    start = contentPadding.calculateStartPadding(LayoutDirection.Ltr) + 8.dp,
                    end = contentPadding.calculateEndPadding(LayoutDirection.Ltr) + 8.dp,
                    bottom = contentPadding.calculateBottomPadding()
                )
                .fillMaxSize()
        ) {
            item{ Spacer(Modifier.size(10.dp))}
            items(state.items) { item ->
                var showDialog by remember { mutableStateOf(false) }

                CartCard(item, {showDialog = true})

                if (showDialog) {
                    CustomAlertDialog(
                        onDismiss = { showDialog = false },
                        title = item.material.category,
                        subtitle = item.material.model + " " + item.material.brand,
                        content = item.futureJobMaterial.map { job ->
                            val label : String =
                                if(job.jobAssignment.customer != null) {
                                    if (job.jobAssignment.isCompany) {
                                        "${job.jobAssignment.companyCustomer?.companyName}"
                                    } else if (job.jobAssignment.isPrivate) {
                                        "${job.jobAssignment.privateCustomer?.lastName} ${job.jobAssignment.customer.name}"
                                    } else {
                                        "Cliente Ignoto"
                                    }
                                } else{
                                    "${job.jobAssignment.address.address} ${job.jobAssignment.address.houseNumber}"
                                }

                            Pair(label, job.futureJobMaterial.quantity)
                        },
                        type = item.material.type.toString()
                    )
                }
            }
            item{Spacer(Modifier.size(80.dp))}
        }
    }
}