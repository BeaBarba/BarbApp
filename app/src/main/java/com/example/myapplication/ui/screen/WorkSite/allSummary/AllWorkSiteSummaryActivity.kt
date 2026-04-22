package com.example.myapplication.ui.screen.WorkSite.allSummary

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
import com.example.myapplication.debug.listaCantieri
import com.example.myapplication.ui.NavigationRoute
import com.example.myapplication.ui.component.AddButton
import com.example.myapplication.ui.component.BackButton
import com.example.myapplication.ui.component.GenericCard
import com.example.myapplication.ui.component.SplitButtonList
import com.example.myapplication.ui.component.TitleLabel
import com.example.myapplication.ui.component.TopAppBar

@Composable
fun AllWorksitesSummaryActivity(
    state: AllWorksitesSummaryState,
    actions: AllWorksitesSummaryActions,
    navController : NavHostController
) {

    actions.populateView()

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            TopAppBar(
                id = stringResource(R.string.construction_sites),
                navigationIcon = {BackButton{navController.navigateUp()}},
            )
        },
        floatingActionButton = {AddButton{navController.navigate(NavigationRoute.WorkSiteAdd(null))}}
    ) { contentPadding ->
        var showItems by remember {mutableStateOf(false)}
        LazyColumn(
            modifier = Modifier
                .padding(
                    top = contentPadding.calculateTopPadding() + 8.dp,
                    start = contentPadding.calculateStartPadding(LayoutDirection.Ltr) + 8.dp,
                    end = contentPadding.calculateEndPadding(LayoutDirection.Ltr) + 8.dp,
                    bottom = contentPadding.calculateBottomPadding()
                )
        ) {

            item{TitleLabel(stringResource(R.string.in_progress))}
            item{Spacer(Modifier.size(8.dp))}
            items(state.worksites){ item ->
                GenericCard(
                    text = "${item.address.address} ${item.address.houseNumber}, ${item.address.municipality} (${item.address.province})",
                    textDescription = "${item.workSite.startDate} - ${item.workSite.endDate ?: "/"}",
                    onClick = {navController.navigate(NavigationRoute.SingleWorkSiteSummary(item.workSite.id))}
                )
                Spacer(Modifier.size(8.dp))
            }
            item{
                SplitButtonList(
                    text = stringResource(R.string.completed),
                    showItems = showItems,
                    onClick = {showItems = !showItems}
                )
            }
            item{Spacer(Modifier.size(8.dp))}
            if(showItems){
                items(state.worksitesDone){ item ->
                    GenericCard(
                        text = "${item.address.address} ${item.address.houseNumber}, ${item.address.municipality} (${item.address.province})",
                        textDescription = "${item.workSite.startDate} - ${item.workSite.endDate ?: "/"}",
                        onClick = {navController.navigate(NavigationRoute.SingleWorkSiteSummary(item.workSite.id))}
                    )
                    Spacer(Modifier.size(8.dp))
                }
            }
            item{Spacer(Modifier.size(80.dp))}
        }
    }
}