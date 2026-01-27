package com.example.myapplication.ui.screen.Customer.allSummary

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
import com.example.myapplication.ui.component.AddButton
import com.example.myapplication.ui.component.BackButton
import com.example.myapplication.ui.component.CustomersCardsList
import com.example.myapplication.ui.component.DropDownMenuCustomers
import com.example.myapplication.ui.NavigationRoute
import com.example.myapplication.ui.component.CustomSearchBar
import com.example.myapplication.ui.component.TopAppBar

@Composable
fun AllCustomersSummaryActivity(
    state : AllCustomersSummaryState,
    actions: AllCustomersSummaryActions,
    navController : NavHostController
){
    actions.populateCustomers()
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            TopAppBar(
                id = stringResource(R.string.customers),
                navigationIcon = {BackButton{navController.navigate(NavigationRoute.Home)}},
                trailingIcon = {
                    DropDownMenuCustomers(
                        onClickAscending = actions::setAscendingSort,
                        onClickDescending = actions::setDescendingSort,
                        onClickAllCustomer = actions::setAllCustomerFilter,
                        onClickReference = actions::setReferenceFilter,
                        onClickAirCondition = actions::setAirConditionerFilter,
                        onClickElectric = actions::setElectricFilter,
                        onClickAlarm = actions::setAlarmFilter
                    )
                }
            )
        },
        floatingActionButton = {AddButton{navController.navigate(NavigationRoute.CustomerAdd(null))}}
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
            CustomSearchBar(stringResource(R.string.customer),onValueChange = actions::setSearchString)
            CustomersCardsList(
                letters = state.startingChar,
                customers = state.customers.map {  if (it.cognome != null) (it.cognome + " " + it.nome) else it.nome},
                navController = navController
            )
        }
    }
}