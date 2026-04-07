package com.example.myapplication.ui.screen.Job.add

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.calculateEndPadding
import androidx.compose.foundation.layout.calculateStartPadding
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.ChevronRight
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.outlined.AddPhotoAlternate
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavDestination.Companion.hasRoute
import androidx.navigation.NavHostController
import com.example.myapplication.R
import com.example.myapplication.data.modules.JobType
import com.example.myapplication.data.modules.SelectKey
import com.example.myapplication.ui.component.BackButton
import com.example.myapplication.ui.component.CustomOutlineTextField
import com.example.myapplication.ui.component.CustomTimePicker
import com.example.myapplication.ui.component.DatePickerFieldToModal
import com.example.myapplication.ui.component.DeleteButton
import com.example.myapplication.ui.component.GenericCard
import com.example.myapplication.ui.NavigationRoute
import com.example.myapplication.ui.component.MenuItem
import com.example.myapplication.ui.component.SplitButtonMenu
import com.example.myapplication.ui.component.TopAppBar
import java.time.LocalDate
import java.time.format.DateTimeFormatter

@Composable
fun JobAddActivity(
    jobId : Int?,
    state : JobAddState,
    actions: JobAddActions,
    navController : NavHostController
){
    LaunchedEffect(jobId) {
        actions.populateView(jobId)
    }

    val previousBackStackEntry = navController.previousBackStackEntry

    val selectSearchTextCustomer = stringResource(R.string.customer)
    val selectSearchTextAddress = stringResource(R.string.address)

    val currentBackStackEntry = navController.currentBackStackEntry
    val selectedCustomersItems by currentBackStackEntry?.savedStateHandle
        ?.getStateFlow<List<String>?>("customers", emptyList())
        ?.collectAsStateWithLifecycle() ?: remember { mutableStateOf(emptyList()) }

    val selectedAddressItems by currentBackStackEntry?.savedStateHandle
        ?.getStateFlow<List<String>?>("selectedIds", emptyList())
        ?.collectAsStateWithLifecycle() ?: remember { mutableStateOf(emptyList()) }

    LaunchedEffect(selectedCustomersItems) {
        selectedCustomersItems?.let{ ids ->
            if(ids.isNotEmpty()) {
                actions.setCustomers(ids.first())
            }
        }
        currentBackStackEntry?.savedStateHandle?.remove<List<String>>("customers")
    }

    LaunchedEffect(selectedAddressItems) {
        selectedAddressItems?.let{ ids ->
            if(ids.isNotEmpty()) {
                actions.setAddress(ids.first().toInt())
            }
        }
        currentBackStackEntry?.savedStateHandle?.remove<List<String>>("selectedIds")
    }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            TopAppBar(
                id = stringResource(R.string.intervention),
                navigationIcon = {BackButton{navController.navigateUp()}},
                trailingIcon = {
                    IconButton(
                        onClick = {
                            actions.saveJob {
                                navController.navigate(NavigationRoute.SingleJobSummary(it)) {
                                    popUpTo(NavigationRoute.AllJobsSummary) { inclusive = false }
                                }
                            }
                        },
                        colors = IconButtonDefaults.iconButtonColors(
                            contentColor = MaterialTheme.colorScheme.onPrimary
                        )
                    ) {
                        Icon(Icons.Filled.Check, contentDescription = stringResource(R.string.save))
                    }
                }
            )
        },
    ){ contentPadding ->
        LazyColumn (
            modifier = Modifier
                .padding(
                    top = contentPadding.calculateTopPadding() + 8.dp,
                    start = contentPadding.calculateStartPadding(LayoutDirection.Ltr) + 8.dp,
                    end = contentPadding.calculateEndPadding(LayoutDirection.Ltr) + 8.dp,
                    bottom = contentPadding.calculateBottomPadding()
                )
                .fillMaxSize()
        ){
            item{
                GenericCard(
                    leadingContent = {
                        IconButton(
                            modifier = Modifier.border(1.dp,MaterialTheme.colorScheme.onPrimaryContainer, RoundedCornerShape(20.dp)),
                            onClick = {navController.navigate(NavigationRoute.CustomerAdd(null))}
                        ) {
                            Icon(Icons.Filled.Add, contentDescription = stringResource(R.string.add_item))
                        }
                    },
                    text = stringResource(R.string.customer),
                    trailingContent = {
                        Icon(
                            Icons.Filled.ChevronRight,
                            contentDescription = stringResource(R.string.edit),
                            modifier = Modifier.size(35.dp)
                        )
                    },
                    onClick = {
                        if(!state.customerId.isNullOrEmpty()) {
                            navController.currentBackStackEntry
                                ?.savedStateHandle
                                ?.set("initialIds", listOf(state.customerId))
                        }
                        navController.navigate(
                            NavigationRoute.Select(
                                selectSearchTextCustomer,
                                SelectKey.AllCustomers
                            )
                        )
                    }
                )
            }
            item{Spacer(Modifier.size(8.dp))}
            item{
                GenericCard(
                    text = stringResource(R.string.add) + " " + stringResource(R.string.address).lowercase(),
                    trailingContent = {
                        Icon(
                            imageVector = Icons.Filled.Add,
                            contentDescription = stringResource(R.string.add_item),
                            modifier = Modifier.size(25.dp)
                        )
                    },
                    onClick = {navController.navigate(NavigationRoute.AddressAdd(null))}
                )
            }
            item{Spacer(Modifier.size(8.dp))}
            item{
                GenericCard(
                    leadingContent = {
                        Icon(
                            imageVector = Icons.Filled.LocationOn,
                            contentDescription = stringResource(R.string.address)
                        )
                    },
                    text = stringResource(R.string.address) + " " + stringResource(R.string.existing).lowercase(),
                    trailingContent = {
                        Icon(
                            Icons.Filled.ChevronRight,
                            contentDescription = stringResource(R.string.edit),
                            modifier = Modifier.size(35.dp)
                        )
                    },
                    onClick = {
                        if(state.addressId != 0){
                            navController.currentBackStackEntry
                                ?.savedStateHandle
                                ?.set("initialIds", listOf(state.addressId.toString()))
                        }
                        navController.navigate(
                            NavigationRoute.Select(
                                selectSearchTextAddress,
                                SelectKey.AllAddresses
                            )
                        )
                    }
                )
            }
            item{Spacer(Modifier.size(8.dp))}
            item{SplitButtonMenu(
                content =
                    if(state.type == JobType.NONE){ stringResource(R.string.type)}
                    else {state.type.toString()},
                items = JobType.entries.map{type -> MenuItem(Pair(1,""), type.name){actions.setJobType(type)}},
                heightMenu = (JobType.entries.size * 55).dp
            )}
            item{
                CustomOutlineTextField(
                    label = stringResource(R.string.people_number),
                    value = state.peopleNumber?.toString() ?: "",
                    onValueChange = {number ->
                        actions.setPeopleNumber(number)
                    }
                )
            }
            item{Spacer(Modifier.size(8.dp))}
            item{
                DatePickerFieldToModal(
                    value = state.date?.format(DateTimeFormatter.ofPattern("dd/MM/yyyy")) ?: "",
                    onValueChange = {date ->
                        actions.setJobDate(
                            if(date.isBlank()) LocalDate.now()
                            else LocalDate.parse(date, DateTimeFormatter.ofPattern("dd/MM/yyyy"))
                        )
                    }
                )
            }
            item{Spacer(Modifier.size(8.dp))}
            item{
                CustomTimePicker(
                    title = stringResource(R.string.start_time),
                    onValueChange = actions::setStartTime,
                    value = state.startTime?.format(DateTimeFormatter.ofPattern("hh:mm")) ?: ""
                )
            }
            item{Spacer(Modifier.size(8.dp))}
            item{
                CustomTimePicker(
                    title = stringResource(R.string.end_time),
                    onValueChange = actions::setEndTime,
                    value = state.endTime?.format(DateTimeFormatter.ofPattern("hh:mm")) ?: ""
                )
            }
            item{Spacer(Modifier.size(8.dp))}
            item{
                CustomOutlineTextField(
                    label = stringResource(R.string.price),
                    value = state.price?.toString() ?: "",
                    onValueChange = actions::setPrice
                )
            }
            item{Spacer(Modifier.size(8.dp))}
            item{
                CustomOutlineTextField(
                    label = stringResource(R.string.description),
                    value = state.description,
                    onValueChange = actions::setDescription
                )
            }
            item{Spacer(Modifier.size(8.dp))}
            //item{SplitButtonMenu(content = stringResource(R.string.construction_site), items = cantieri_menu, heightMenu = (cantieri_menu.size *50).dp)}
            item{
                GenericCard(
                    text = stringResource(R.string.material),
                    trailingContent = {
                        Icon(
                            Icons.Filled.ChevronRight,
                            contentDescription = stringResource(R.string.edit),
                            modifier = Modifier.size(35.dp)
                        )
                    },
                    onClick = {navController.navigate(NavigationRoute.JobMaterials)}
                )
            }
            item{Spacer(Modifier.size(8.dp))}
            item{GenericCard(
                text = stringResource(R.string.photo_add),
                trailingContent = {
                    Icon(
                        imageVector = Icons.Outlined.AddPhotoAlternate,
                        contentDescription = stringResource(R.string.photo_add),
                        modifier = Modifier.size(40.dp)
                    )
                }
            )}
            item{Spacer(Modifier.size(8.dp))}
            if (previousBackStackEntry?.destination?.hasRoute<NavigationRoute.AllJobsSummary>() == false) {
                item {
                    DeleteButton {
                        actions.deleteJob()
                        navController.navigate(NavigationRoute.AllJobsSummary) {
                            popUpTo(NavigationRoute.AllJobsSummary) { inclusive = true }
                            launchSingleTop = true
                        }
                    }
                }
                item { Spacer(Modifier.size(8.dp)) }
            }
        }
    }
}