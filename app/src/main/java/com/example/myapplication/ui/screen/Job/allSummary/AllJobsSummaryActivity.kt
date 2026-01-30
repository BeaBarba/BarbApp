package com.example.myapplication.ui.screen.Job.allSummary

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
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.myapplication.R
import com.example.myapplication.debug.interventi
import com.example.myapplication.ui.component.AddButton
import com.example.myapplication.ui.component.Avatar
import com.example.myapplication.ui.component.BackButton
import com.example.myapplication.ui.component.CustomDivider
import com.example.myapplication.ui.component.DropDownMenuJobs
import com.example.myapplication.ui.component.GenericCard
import com.example.myapplication.ui.NavigationRoute
import com.example.myapplication.ui.component.CustomSearchBar
import com.example.myapplication.ui.component.TitleLabel
import com.example.myapplication.ui.component.TopAppBar
import java.time.LocalTime

@Composable
fun AllJobsSummaryActivity(
    state : AllJobsSummaryState,
    actions: AllJobsSummaryActions,
    navController : NavHostController
){
    actions.populateCustomers()

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            TopAppBar(
                id = stringResource(R.string.interventions),
                navigationIcon = {BackButton{navController.navigate(NavigationRoute.Home)}},
                trailingIcon = {DropDownMenuJobs()}
            )
        },
        floatingActionButton = {AddButton{navController.navigate(NavigationRoute.JobAdd)}}
    ) {
        contentPadding ->
        LazyColumn (
            modifier = Modifier
                .padding(
                    top = contentPadding.calculateTopPadding() + 8.dp,
                    start = contentPadding.calculateStartPadding(LayoutDirection.Ltr) + 8.dp,
                    end = contentPadding.calculateEndPadding(LayoutDirection.Ltr) + 8.dp,
                    bottom = contentPadding.calculateBottomPadding()
                )
                .fillMaxSize()
        ) {
            item{CustomSearchBar(stringResource(R.string.intervention), onValueChange = {})}
            item{Spacer(Modifier.size(8.dp))}
            items(
                state.jobs.filter {
                    it.job.endTime == null || it.job.endTime.isAfter(LocalTime.now())
                }
            ){ item ->
                GenericCard(
                    type = actions.getTypeStringFromJob(item),
                    text = item.address.address,
                    textDescription = item.customer.name + item.privateCustomer?.lastName,
                    leadingContent = {
                        Avatar(
                            char = actions.getTypeStringFromJob(item).get(0),
                            type = actions.getTypeStringFromJob(item)
                        )
                    },
                    onClick = {navController.navigate(NavigationRoute.SingleJobSummary)}
                )
                Spacer(Modifier.size(8.dp))
            }
            item{CustomDivider()}
            item{TitleLabel(stringResource(R.string.j_completed))}
            item{Spacer(Modifier.size(8.dp))}
            items(
                state.jobs.filter {
                    it.job.endTime != null && it.job.endTime.isBefore(LocalTime.now())
                }
            ){ item ->
                GenericCard(
                    type = actions.getTypeStringFromJob(item),
                    text = item.address.address,
                    textDescription = item.customer.name + item.privateCustomer?.lastName,
                    leadingContent = {
                        Avatar(
                            char = actions.getTypeStringFromJob(item).get(0),
                            type = actions.getTypeStringFromJob(item)
                        )
                    },
                    onClick = {navController.navigate(NavigationRoute.SingleJobSummary)}
                )
                Spacer(Modifier.size(8.dp))
            }
            item{Spacer(Modifier.size(80.dp))}
        }
    }
}