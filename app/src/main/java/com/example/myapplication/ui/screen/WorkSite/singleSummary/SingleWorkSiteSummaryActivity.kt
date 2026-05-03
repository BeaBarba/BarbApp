package com.example.myapplication.ui.screen.WorkSite.singleSummary

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.calculateEndPadding
import androidx.compose.foundation.layout.calculateStartPadding
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ChevronRight
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
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
import java.time.format.DateTimeFormatter

@Composable
fun SingleWorksiteSummaryActivity (
    worksiteId : Int,
    state : SingleWorksiteSummaryState,
    actions : SingleWorksiteSummaryActions,
    navController : NavHostController
) {
    LaunchedEffect(worksiteId) {
        actions.populateView(worksiteId)
    }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            TopAppBar(
                navigationIcon = { BackButton { navController.navigateUp() } },
                id = stringResource(R.string.construction_site),
                trailingIcon = {
                    IconButton(
                        onClick = { navController.navigate(NavigationRoute.WorkSiteAdd(state.worksiteId))},
                        colors = IconButtonDefaults.iconButtonColors(
                            contentColor = MaterialTheme.colorScheme.onPrimary
                        )
                    ) {
                        Icon(
                            painterResource(R.drawable.edit_square_24dp),
                            contentDescription = stringResource(R.string.edit)
                        )
                    }
                })
        }
    ) { contentPadding ->
        LazyColumn(
            modifier = Modifier
                .padding(
                    top = contentPadding.calculateTopPadding() + 8.dp,
                    start = contentPadding.calculateStartPadding(LayoutDirection.Ltr) + 8.dp,
                    end = contentPadding.calculateEndPadding(LayoutDirection.Ltr) + 8.dp,
                    bottom = contentPadding.calculateBottomPadding()
                )
                .fillMaxSize()
        ) {
            state.customer?.let {
                val customer = actions.getCustomerName()
                item {
                    GenericCard(
                        leadingContent = { Avatar(char = customer[0]) },
                        text = customer,
                        onClick = { navController.navigate(NavigationRoute.SingleCustomerSummary(state.customer.customer.cf)) }
                    )
                }
                item { Spacer(Modifier.size(8.dp)) }
            }
            state.address?.let {
                item {
                    GenericCard(
                        leadingContent = {
                            Icon(
                                imageVector = Icons.Filled.LocationOn,
                                contentDescription = stringResource(R.string.address)
                            )
                        },
                        text = actions.getAddressText(),
                        trailingContent = {
                            Icon(
                                imageVector = Icons.Filled.ChevronRight,
                                contentDescription = stringResource(R.string.address_details),
                                modifier = Modifier.size(35.dp)
                            )
                        },
                        onClick = { navController.navigate(NavigationRoute.SingleAddressSummary(state.address.id)) }
                    )
                }
                item { Spacer(Modifier.size(8.dp)) }
            }
            item {
                KeyValueLabel(
                    title = stringResource(R.string.date_start),
                    description = state.worksite?.startDate?.format(
                        DateTimeFormatter
                            .ofPattern("dd/MM/yyyy")
                    ) ?: ""
                )
            }
            item { Spacer(Modifier.size(8.dp)) }
            item {
                KeyValueLabel(
                    title = stringResource(R.string.date_end),
                    description = state.worksite?.endDate?.format(
                        DateTimeFormatter
                            .ofPattern("dd/MM/yyyy")
                    ) ?: "/"
                )
            }
            item { Spacer(Modifier.size(8.dp)) }
            state.manager?.let {
                item { TitleLabel(stringResource(R.string.reference)) }
                item { Spacer(Modifier.size(8.dp)) }
                item {
                    KeyValueLabel(
                        title = stringResource(R.string.name),
                        description = state.manager.name
                    )
                }
                item { Spacer(Modifier.size(8.dp)) }
                item {
                    KeyValueLabel(
                        title = stringResource(R.string.last_name),
                        description = state.manager.lastName
                    )
                }
                item { Spacer(Modifier.size(8.dp)) }
                state.manager.phoneNumber?.let {
                    item {
                        KeyValueLabel(
                            title = stringResource(R.string.phone),
                            description = state.manager.phoneNumber
                        )
                    }
                    item { Spacer(Modifier.size(8.dp)) }
                }
            }
            if(state.jobs.isNotEmpty()) {
                item { TitleLabel(stringResource(R.string.interventions)) }
                item { Spacer(Modifier.size(8.dp)) }
                items(state.jobs) { item ->
                    val type = actions.getJobType(item)
                    GenericCard(
                        type = type.name,
                        leadingContent = {
                            Avatar(char = type.name[0], type = type.name)
                        },
                        text = item.date.format(DateTimeFormatter.ofPattern("dd/MM/yyyy")),
                        textDescription = "${item.startTime} - ${item.endTime}",
                        onClick = { navController.navigate(NavigationRoute.SingleJobSummary(item.id)) }
                    )
                    Spacer(Modifier.size(8.dp))
                }
            }
        }
    }
}
