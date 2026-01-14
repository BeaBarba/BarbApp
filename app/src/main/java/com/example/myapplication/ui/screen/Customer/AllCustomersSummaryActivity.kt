package com.example.myapplication.ui.screen.Customer

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.calculateEndPadding
import androidx.compose.foundation.layout.calculateStartPadding
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.myapplication.R
import com.example.myapplication.debug.customers
import com.example.myapplication.debug.letters
import com.example.myapplication.ui.component.AddButton
import com.example.myapplication.ui.component.BackButton
import com.example.myapplication.ui.component.CustomersCardsList
import com.example.myapplication.ui.component.DropDownMenuCustomers
import com.example.myapplication.ui.NavigationRoute
import com.example.myapplication.ui.component.SearchAppBar
import com.example.myapplication.ui.component.TopAppBar

@Composable
fun AllCustomersSummaryActivity(
    navController : NavHostController
){
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            TopAppBar(
                id = stringResource(R.string.customers),
                navigationIcon = {BackButton{navController.navigate(NavigationRoute.Home)}},
                trailingIcon = {DropDownMenuCustomers()}
            )
        },
        floatingActionButton = {AddButton{navController.navigate(NavigationRoute.CustomerAdd)}}
    ) { contentPadding ->
        Column(
            modifier = Modifier
                .padding(
                    top = contentPadding.calculateTopPadding(),
                    start = contentPadding.calculateStartPadding(LayoutDirection.Ltr) + 8.dp,
                    end = contentPadding.calculateEndPadding(LayoutDirection.Ltr) + 8.dp,
                    bottom = contentPadding.calculateBottomPadding()
                )
                .fillMaxSize()
        ) {
            SearchAppBar(stringResource(R.string.customer))
            CustomersCardsList(letters, customers, navController)
        }
    }
}