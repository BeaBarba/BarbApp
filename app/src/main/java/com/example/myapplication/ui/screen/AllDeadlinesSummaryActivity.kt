package com.example.myapplication.ui.screen

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
import com.example.myapplication.ui.component.SearchAppBar
import com.example.myapplication.ui.component.SplitButtonList
import com.example.myapplication.ui.component.TopAppBar

@Composable
fun AllDeadlinesSummaryActivity(
    navController: NavHostController
){
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            TopAppBar(
                navigationIcon = {BackButton{navController.navigate(NavigationRoute.Home)}},
                id = stringResource(R.string.deadline),
                trailingIcon = {DropDownMenuDeadlines(categorie, venditori)}
            )
        },
        floatingActionButton = {AddButton{navController.navigate(NavigationRoute.DeadlineAdd)}}
    ) { contentPadding ->
        var showItems by remember {mutableStateOf(false)}
        LazyColumn(
            modifier = Modifier
                .padding(
                    top = contentPadding.calculateTopPadding(),
                    start = contentPadding.calculateStartPadding(LayoutDirection.Ltr) + 8.dp,
                    end = contentPadding.calculateEndPadding(LayoutDirection.Ltr) + 8.dp,
                    bottom = contentPadding.calculateBottomPadding()
                )
        ) {
            item{SearchAppBar(stringResource(R.string.category))}
            items(scadenze.subList(0,5)){item ->
                var checked by remember {mutableStateOf(false)}
                ListItemCheckbox(
                    char = item.categoria.get(0),
                    text = item.fornitore,
                    textDescription = item.categoria,
                    trailingText = item.prezzo.toString() + "€",
                    checked = checked,
                    onCheckedChange = {checked = !checked},
                    onClick = {navController.navigate(NavigationRoute.SingleDeadlineSummary)}
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
                items(scadenze){item ->
                    var checked by remember { mutableStateOf(true) }
                    ListItemCheckbox(
                        char = item.categoria.get(0),
                        text = item.fornitore,
                        textDescription = item.categoria,
                        trailingText = item.prezzo.toString() + "€",
                        checked = checked,
                        onCheckedChange = {checked = !checked},
                        onClick = {navController.navigate(NavigationRoute.SingleDeadlineSummary)}
                    )
                    Spacer(Modifier.size(8.dp))
                }
            }
            item{Spacer(Modifier.size(80.dp))}
        }
    }
}