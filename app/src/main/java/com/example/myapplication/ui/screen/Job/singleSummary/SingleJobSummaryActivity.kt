package com.example.myapplication.ui.screen.Job.singleSummary

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
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.myapplication.R
import com.example.myapplication.ui.component.Avatar
import com.example.myapplication.ui.component.BackButton
import com.example.myapplication.ui.component.BoxDescription
import com.example.myapplication.ui.component.DoubleKeyValueLabel
import com.example.myapplication.ui.component.KeyValueLabel
import com.example.myapplication.ui.component.GenericCard
import com.example.myapplication.ui.component.Images
import com.example.myapplication.ui.NavigationRoute
import com.example.myapplication.ui.component.TitleLabel
import com.example.myapplication.ui.component.TopAppBar

@Composable
fun SingleJobSummaryActivity(
    jobId : Int,
    state : SingleJobSummaryState,
    actions: SingleJobSummaryActions,
    navController : NavHostController
){
    LaunchedEffect(jobId) {
        actions.populateJobData(jobId)
    }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            TopAppBar(
                id = stringResource(R.string.intervention),
                navigationIcon = {BackButton{navController.navigateUp()}},
                trailingIcon = {
                    IconButton(
                        onClick = {navController.navigate(NavigationRoute.JobAdd(jobId))},
                        colors = IconButtonDefaults.iconButtonColors(contentColor = MaterialTheme.colorScheme.onPrimary)
                    ) {
                        Icon(painterResource(R.drawable.edit_square_24dp), contentDescription = stringResource(R.string.edit))
                    }
                }
            )
        }
    ){ contentPadding ->
        LazyColumn(
            modifier = Modifier
                .padding(
                    top = contentPadding.calculateTopPadding() + 8.dp,
                    start = contentPadding.calculateStartPadding(LayoutDirection.Ltr) + 8.dp,
                    end = contentPadding.calculateEndPadding(LayoutDirection.Ltr) + 8.dp,
                    bottom = contentPadding.calculateBottomPadding()
                )
        ) {
            state.job?.jobDetails?.customer?.let { customer ->
                item {
                    GenericCard(
                        text =
                            if(customer.isPrivate) "${customer.privateCustomer!!.lastName} ${customer.customer.name}"
                            else customer.companyCustomer!!.companyName,
                        leadingContent = {
                            Avatar(
                                char = if(customer.isPrivate) customer.privateCustomer!!.lastName[0]
                                        else customer.companyCustomer!!.companyName[0],
                            )
                        },
                        onClick = { navController.navigate(NavigationRoute.SingleCustomerSummary(customer.customer.cf)) }
                    )
                }
                item { Spacer(Modifier.size(8.dp)) }
            }
            item{
                GenericCard(
                    leadingContent = {
                        Icon(
                            imageVector = Icons.Filled.LocationOn,
                            contentDescription = stringResource(R.string.address)
                        )
                    },
                    text = "${state.job?.jobDetails?.address?.address} ${state.job?.jobDetails?.address?.houseNumber}",
                    trailingContent = {
                        Icon(
                            Icons.Filled.ChevronRight,
                            contentDescription = stringResource(R.string.edit),
                            modifier = Modifier.size(35.dp)
                        )
                    },
                    onClick = {
                        navController.navigate(
                            NavigationRoute.SingleAddressSummary(state.job?.jobDetails?.address?.id.let { it ?: 0 }
                            )
                        )
                    }
                )
            }
            item{Spacer(Modifier.size(8.dp))}
            if(state.job?.jobDetails?.job?.workSite != null){
                item{
                    GenericCard(
                        leadingContent = {
                            Icon(
                                painterResource(R.drawable.brickwall),
                                contentDescription = stringResource(R.string.construction_site)
                            )
                        },
                        text = stringResource(R.string.construction_site),
                        trailingContent = {
                            Icon(
                                Icons.Filled.ChevronRight,
                                contentDescription = stringResource(R.string.edit),
                                modifier = Modifier.size(35.dp)
                            )
                        },
                        onClick = {
                            navController.navigate(
                                NavigationRoute.SingleWorkSiteSummary(state.job.jobDetails.job.workSite)
                            )
                        }
                    )
                }
                item{Spacer(Modifier.size(8.dp))}
            }

             /**/
            item{
                DoubleKeyValueLabel(
                    firstTitle = stringResource(R.string.price),
                    firstDescription = state.price.toString(),
                    secondTitle = stringResource(R.string.date),
                    secondDescription = state.job?.jobDetails?.job?.date.toString()
                )
            }
            item{Spacer(Modifier.size(8.dp))}
            item{
                DoubleKeyValueLabel(
                    firstTitle = stringResource(R.string.start_time),
                    firstDescription = state.job?.jobDetails?.job?.startTime.toString(),
                    secondTitle = stringResource(R.string.end_time),
                    secondDescription = state.job?.jobDetails?.job?.endTime.toString()
                )
            }
            item{Spacer(Modifier.size(8.dp))}
            item{
                KeyValueLabel(
                    title = stringResource(R.string.people_number),
                    description = state.job?.jobDetails?.job?.peopleNumber.toString()
                )
            }
            item{Spacer(Modifier.size(8.dp))}
            item{
                KeyValueLabel(
                    title = stringResource(R.string.type),
                    description = when {
                        state.job?.jobDetails?.job?.electric == true -> stringResource(R.string.electric)
                        state.job?.jobDetails?.job?.airConditioning == true -> stringResource(R.string.air_conditioning)
                        state.job?.jobDetails?.job?.alarm == true -> stringResource(R.string.alarm)
                        else -> ""
                    }
                )
            }
            item{Spacer(Modifier.size(8.dp))}
            item{TitleLabel(title = stringResource(R.string.description))}
            item{Spacer(Modifier.size(8.dp))}
            item{BoxDescription(state.job?.jobDetails?.job?.description.toString())}
            item{Spacer(Modifier.size(8.dp))}
            item{Images()}
            item{Spacer(Modifier.size(8.dp))}
            if(state.materials.isNotEmpty()) {
                item{TitleLabel(stringResource(R.string.materials))}
                item{Spacer(Modifier.size(8.dp))}
                items(state.materials) { item ->
                    val material = item.first
                    val quantity = item.second

                    GenericCard(
                        type = material.type.toString(),
                        leadingContent = {
                            Avatar(
                                char = material.type.toString()[0],
                                type = material.type.toString()
                            )
                        },
                        text = material.category,
                        textDescription = "${material.model} - ${material.brand}",
                        trailingContent = {
                            Text(
                                text = "$quantity ${material.unitMeasurement}"
                            )
                        },
                        onClick = {
                            navController.navigate(
                                NavigationRoute.SingleMaterialSummary(
                                    material.id
                                )
                            )
                        }
                    )
                    Spacer(Modifier.size(8.dp))
                }
            }
        }
    }
}