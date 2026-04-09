package com.example.myapplication.ui.screen.Deadline.allSummary

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.calculateEndPadding
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
import com.example.myapplication.ui.component.AddButton
import com.example.myapplication.ui.component.BackButton
import com.example.myapplication.ui.component.DropDownMenuDeadlines
import com.example.myapplication.ui.component.ListItemCheckbox
import com.example.myapplication.ui.NavigationRoute
import com.example.myapplication.ui.component.CustomSearchBar
import com.example.myapplication.ui.component.SplitButtonList
import com.example.myapplication.ui.component.TopAppBar

@Composable
fun AllDeadlinesSummaryActivity(
    state : AllDeadlineSummaryState,
    actions: AllDeadlinesSummaryActions,
    navController: NavHostController
){
    var showItems by remember {mutableStateOf(false)}
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            TopAppBar(
                navigationIcon = { BackButton{navController.navigate(NavigationRoute.Home)} },
                id = stringResource(R.string.deadline),
                trailingIcon = {
                    DropDownMenuDeadlines(
                        category = state.categoriesList,
                        sellers = state.sellers,
                        allOnClick = actions::filterAll,
                        categoryOnClick = actions::filterByCategory,
                        sellerOnClick = actions::filterBySeller,
                        ascendingOnClick = actions::ascendingOrder,
                        descendingOnClick = actions::descendingOrder
                    )
                }
            )
        },
        floatingActionButton = { AddButton{navController.navigate(NavigationRoute.DeadlineAdd(null))} }
    ) { contentPadding ->

        LazyColumn(
            modifier = Modifier
                .padding(
                    top = contentPadding.calculateTopPadding(),
                    start = contentPadding.calculateStartPadding(LayoutDirection.Ltr) + 8.dp,
                    end = contentPadding.calculateEndPadding(LayoutDirection.Ltr) + 8.dp,
                    bottom = contentPadding.calculateBottomPadding()
                )
        ) {
            item{CustomSearchBar(stringResource(R.string.category), onValueChange = actions::searchDeadline)}
            item{Spacer(Modifier.size(8.dp))}
            items(state.deadlinesView.filter { !it.checked }){ item ->
                ListItemCheckbox(
                    char = item.category[0],
                    text = if(item.seller == null) item.name else "${item.seller} - ${item.name}",
                    textDescription = item.category,
                    trailingText = "${item.amount} €",
                    checked = item.checked,
                    onCheckedChange = {actions.setChecked(item)},
                    onClick = {navController.navigate(NavigationRoute.SingleDeadlineSummary(item.id, item.type.toString()))}
                )
                Spacer(Modifier.size(8.dp))
            }
            item{Spacer(Modifier.size(8.dp))}
            item{
                SplitButtonList(
                    text = stringResource(R.string.completed),
                    showItems = showItems,
                    onClick = {showItems = !showItems}
                )
            }
            item{Spacer(Modifier.size(8.dp))}
            if(showItems){
                items(state.deadlinesView.filter { it.checked }){ item ->
                    ListItemCheckbox(
                        char = item.category[0],
                        text = item.category,
                        textDescription = if(item.seller == null) item.name else "${item.seller} - ${item.name}",
                        trailingText = "${item.amount} €",
                        checked = item.checked,
                        onCheckedChange = {actions.setChecked(item)},
                        onClick = {navController.navigate(NavigationRoute.SingleDeadlineSummary(item.id, item.type.toString()))}
                    )
                    Spacer(Modifier.size(8.dp))
                }
            }
            item{Spacer(Modifier.size(80.dp))}
        }
    }
}