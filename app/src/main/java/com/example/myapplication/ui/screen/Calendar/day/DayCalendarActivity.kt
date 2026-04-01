package com.example.myapplication.ui.screen.Calendar.day

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.calculateEndPadding
import androidx.compose.foundation.layout.calculateStartPadding
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.myapplication.R
import com.example.myapplication.data.modules.JobType
import com.example.myapplication.ui.component.AddButton
import com.example.myapplication.ui.component.BackButton
import com.example.myapplication.ui.component.DatePickerModal
import com.example.myapplication.ui.component.LargeCard
import com.example.myapplication.ui.NavigationRoute
import com.example.myapplication.ui.component.TopAppBar
import java.time.Instant
import java.time.ZoneId
import java.time.format.DateTimeFormatter

@Composable
fun DayCalendarActivity(
    date : Long,
    state : DayCalendarState,
    actions: DayCalendarActions,
    navController : NavHostController
){
    LaunchedEffect(date) {
        actions.populateCalendar(
            Instant.ofEpochMilli(date)
                .atZone(ZoneId.systemDefault())
                .toLocalDate()
        )
    }

    val formatter = DateTimeFormatter.ofPattern("dd MMMM yyyy", java.util.Locale.getDefault())
    val dateSelect = Instant.ofEpochMilli(date).atZone(ZoneId.systemDefault()).toLocalDate().format(formatter)
    val openPicker = remember {mutableStateOf(false)}
    val datePickerState = rememberDatePickerState()

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            TopAppBar(
                navigationIcon = {BackButton{navController.navigateUp()}},
                id = dateSelect,
                trailingIcon = {
                    IconButton(
                        onClick = {openPicker.value = true},
                        colors = IconButtonDefaults.iconButtonColors(
                            contentColor = MaterialTheme.colorScheme.onPrimary
                        )
                    ) {
                        Icon(Icons.Default.DateRange, contentDescription = stringResource(R.string.open_d_picker))
                    }

                    DatePickerModal(
                        datePickerState = datePickerState,
                        openDialogState = openPicker,
                        onDateSelected = { millis ->
                            if(millis != null){
                                navController.navigate(NavigationRoute.DayCalendar(millis))
                            }
                        }
                    )
                }
            )
        },
        floatingActionButton = {AddButton{navController.navigate(NavigationRoute.JobAdd(null))}}
    ) { contentPadding ->
        LazyColumn(
            modifier = Modifier.padding(
                top = contentPadding.calculateTopPadding() + 8.dp,
                start = contentPadding.calculateStartPadding(LayoutDirection.Ltr) + 8.dp,
                end = contentPadding.calculateEndPadding(LayoutDirection.Ltr) + 8.dp,
                bottom = contentPadding.calculateBottomPadding()
            )
        ) {
            items(state.calendar){ item ->
                val materials = item.materialsFuture.map { it.material.category } + item.materialUsage.map{it.material.category}
                LargeCard(
                    type =
                        when{
                            item.jobDetails.job.electric -> {JobType.ELE.toString()}
                            item.jobDetails.job.alarm -> {JobType.ALA.toString()}
                            item.jobDetails.job.airConditioning -> {JobType.CDZ.toString()}
                            else -> JobType.NONE.toString()
                        },
                    title =
                        "${item.jobDetails.address.address} ${item.jobDetails.address.houseNumber}, ${item.jobDetails.address.city}",
                    subtitle = when(item.jobDetails.customer != null){
                        item.jobDetails.customer?.isCompany -> {item.jobDetails.customer.companyCustomer?.companyName}
                        item.jobDetails.customer?.isPrivate -> {"${item.jobDetails.customer.privateCustomer?.lastName} ${item.jobDetails.customer.customer.name}"}
                        else -> ""
                    },
                    description = item.jobDetails.job.description ?: "",
                    items = materials,
                    onClick = {navController.navigate(NavigationRoute.SingleJobSummary(item.jobDetails.job.id))},
                )
                Spacer(Modifier.size(8.dp))
            }
            item{Spacer(Modifier.size(80.dp))}
        }
    }
}