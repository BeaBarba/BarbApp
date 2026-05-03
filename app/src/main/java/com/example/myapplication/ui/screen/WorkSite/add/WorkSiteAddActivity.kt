package com.example.myapplication.ui.screen.WorkSite.add

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
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavDestination.Companion.hasRoute
import androidx.navigation.NavHostController
import com.example.myapplication.R
import com.example.myapplication.data.modules.SelectKey
import com.example.myapplication.ui.component.BackButton
import com.example.myapplication.ui.component.CustomOutlineTextField
import com.example.myapplication.ui.component.DatePickerFieldToModal
import com.example.myapplication.ui.component.DeleteButton
import com.example.myapplication.ui.component.GenericCard
import com.example.myapplication.ui.NavigationRoute
import com.example.myapplication.ui.component.TopAppBar
import java.time.format.DateTimeFormatter

@Composable
fun WorksiteAddActivity(
    worksiteId : Int?,
    state : WorksiteAddState,
    actions : WorksiteAddActions,
    navController : NavHostController
){
    val selectSearchTextCustomers = stringResource(R.string.customer)
    val selectSearchTextAddresses = stringResource(R.string.address)
    val selectSearchTextReferences = stringResource(R.string.reference)

    val previousBackStackEntry = navController.previousBackStackEntry
    val currentBackStackEntry = navController.currentBackStackEntry

    val customerItems by currentBackStackEntry?.savedStateHandle
        ?.getStateFlow<List<String>?>("customers", null)
        ?.collectAsStateWithLifecycle(initialValue = null) ?: remember { mutableStateOf(null)}

    LaunchedEffect(customerItems) {
        customerItems?.let{ ids ->
            actions.setCustomer(ids.first())
        }
        currentBackStackEntry?.savedStateHandle?.remove<List<String>>("customers")
    }

    val addressItems by currentBackStackEntry?.savedStateHandle
        ?.getStateFlow<List<String>?>("selectedIds", null)
        ?.collectAsStateWithLifecycle(initialValue = null) ?: remember { mutableStateOf(null)}

    LaunchedEffect(addressItems) {
        addressItems?.let{ ids ->
            actions.setAddress(ids.first())
        }
        currentBackStackEntry?.savedStateHandle?.remove<List<String>>("selectedIds")
    }

    val referenceItems by currentBackStackEntry?.savedStateHandle
        ?.getStateFlow<List<String>?>("references", null)
        ?.collectAsStateWithLifecycle(initialValue = null) ?: remember { mutableStateOf(null)}

    LaunchedEffect(referenceItems) {
        referenceItems?.let{ ids ->
            actions.setReference(ids.first())
        }
        currentBackStackEntry?.savedStateHandle?.remove<List<String>>("references")
    }

    LaunchedEffect(worksiteId) {
        actions.populateView(worksiteId)
    }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            TopAppBar(
                navigationIcon = { BackButton{ navController.navigateUp() } },
                id = stringResource(R.string.construction_site),
                trailingIcon = {
                    IconButton(
                        onClick = {
                            actions.save { id ->
                                navController.navigate(NavigationRoute.SingleWorkSiteSummary(id)) {
                                    popUpTo(NavigationRoute.AllWorksitesSummary) { inclusive = false }
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
        var showItems by remember { mutableStateOf(false)}
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
                    leadingContent = {Icon(Icons.Filled.Add, contentDescription = stringResource(R.string.add_item))},
                    text = stringResource(R.string.customer),
                    trailingContent = {
                        Icon(
                            Icons.Filled.ChevronRight,
                            contentDescription = stringResource(R.string.edit),
                            modifier = Modifier.size(35.dp)
                        )
                    },
                    onClick = {
                        navController.currentBackStackEntry
                            ?.savedStateHandle
                            ?.set("initialIds", actions.getCustomers())

                        navController.navigate( NavigationRoute.Select(selectSearchTextCustomers, SelectKey.AllCustomers))
                    }
                )
            }
            item{Spacer(Modifier.size(8.dp))}
            item{
                GenericCard(
                    leadingContent = {Icon(Icons.Filled.LocationOn, contentDescription = stringResource(R.string.address))},
                    text = "${stringResource(R.string.address)} ${stringResource(R.string.existing).lowercase()}",
                    trailingContent = {
                        Icon(
                            Icons.Filled.ChevronRight,
                            contentDescription = stringResource(R.string.edit),
                            modifier = Modifier.size(35.dp)
                        )
                    },
                    onClick = {
                        navController.currentBackStackEntry
                            ?.savedStateHandle
                            ?.set("initialIds", actions.getAddresses())

                        navController.navigate(NavigationRoute.Select(selectSearchTextAddresses, SelectKey.AllAddresses))
                    }
                )
            }
            item{Spacer(Modifier.size(8.dp))}
            item{
                GenericCard(
                    leadingContent = {
                        IconButton(
                            onClick = {showItems = !showItems},
                            colors = IconButtonDefaults.iconButtonColors(
                                contentColor = MaterialTheme.colorScheme.onPrimaryContainer
                            ),
                            modifier = Modifier
                                .border(
                                    width = 1.dp,
                                    color = MaterialTheme.colorScheme.onPrimaryContainer,
                                    shape = RoundedCornerShape(20)
                                )
                        ) {
                            Icon(
                                imageVector = Icons.Filled.Add,
                                contentDescription = stringResource(R.string.add_item)
                            )
                        }
                    },
                    text = "${stringResource(R.string.add)} ${stringResource(R.string.reference).lowercase()}",
                    trailingContent = {
                        Icon(
                            Icons.Filled.ChevronRight,
                            contentDescription = stringResource(R.string.edit),
                            modifier = Modifier.size(35.dp)
                        )
                    },
                    onClick = {
                        navController.currentBackStackEntry
                            ?.savedStateHandle
                            ?.set("initialIds", actions.getReferences())

                        navController.navigate(NavigationRoute.Select(selectSearchTextReferences, SelectKey.AllReferences))}
                )
            }
            if(showItems){
                item{
                    CustomOutlineTextField(
                        label = stringResource(R.string.name),
                        value = state.referenceName,
                        onValueChange = actions::setReferenceName
                    )
                }
                item{
                    CustomOutlineTextField(
                        label = stringResource(R.string.last_name),
                        value = state.referenceLastName,
                        onValueChange = actions::setReferenceLastName
                    )
                }
                item{
                    CustomOutlineTextField(
                        label = stringResource(R.string.phone),
                        value = state.referenceNumber,
                        onValueChange = actions::setReferenceNumber
                    )
                }
            }
            item{Spacer(Modifier.size(8.dp))}
            item{
                DatePickerFieldToModal(
                    title = stringResource(R.string.date_start),
                    value = state.startDate?.format(DateTimeFormatter.ofPattern("dd/MM/yyyy")) ?: "",
                    onValueChange = actions::setStartDate
                )
            }
            item{Spacer(Modifier.size(8.dp))}
            item{
                DatePickerFieldToModal(
                    title = stringResource(R.string.date_end),
                    value = state.endDate?.format(DateTimeFormatter.ofPattern("dd/MM/yyyy")) ?: "",
                    onValueChange = actions::setEndDate,
                )
            }
            item{Spacer(Modifier.size(8.dp))}
            if (previousBackStackEntry?.destination?.hasRoute<NavigationRoute.SingleWorkSiteSummary>() == true) {
                item {
                    DeleteButton {
                        actions.delete(state.worksiteId)
                        navController.navigate(NavigationRoute.AllWorksitesSummary){
                            popUpTo(NavigationRoute.AllWorksitesSummary){ inclusive = true}
                            launchSingleTop = true
                        }
                    }
                }
                item { Spacer(Modifier.size(8.dp)) }
            }
        }
    }
}