package com.example.myapplication.ui.screen.Material.add

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.calculateEndPadding
import androidx.compose.foundation.layout.calculateStartPadding
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.ChevronRight
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavDestination.Companion.hasRoute
import androidx.navigation.NavHostController
import com.example.myapplication.R
import com.example.myapplication.data.modules.JobType
import com.example.myapplication.data.modules.MachineType
import com.example.myapplication.data.modules.SelectKey
import com.example.myapplication.data.modules.SplitNumber
import com.example.myapplication.ui.component.BackButton
import com.example.myapplication.ui.component.CustomOutlineTextField
import com.example.myapplication.ui.NavigationRoute
import com.example.myapplication.ui.component.GenericCard
import com.example.myapplication.ui.component.SplitButtonMenu
import com.example.myapplication.ui.component.SuggestionTextField
import com.example.myapplication.ui.component.TopAppBar

@Composable
fun MaterialAddActivity(
    materialId : Int?,
    serialNumber : String?,
    state : MaterialAddState,
    actions: MaterialAddActions,
    navController : NavHostController
){
    val snackBarHostState = remember { SnackbarHostState() }

    val previousBackStackEntry = navController.previousBackStackEntry

    val selectSearchText = stringResource(R.string.customer)

    val currentBackStackEntry = navController.currentBackStackEntry
    val selectedItems by currentBackStackEntry?.savedStateHandle
        ?.getStateFlow<List<String>?>("customers", emptyList())
        ?.collectAsStateWithLifecycle() ?: remember { mutableStateOf(emptyList())}

    LaunchedEffect(selectedItems) {
        selectedItems?.let{ ids ->
            if(ids.isNotEmpty()) {
                println("Println " + ids.size)
                actions.setCustomer(ids.first())
            }
        }
        currentBackStackEntry?.savedStateHandle?.remove<List<String>>("customers")
    }

    LaunchedEffect(materialId) {
        actions.populateView(materialId, serialNumber)
    }

    LaunchedEffect(state.errorMessage) {
        state.errorMessage?.let { message ->
            snackBarHostState.showSnackbar(
                message = message,
                duration = SnackbarDuration.Short
            )
            actions.resetErrorMessage()
        }
    }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            TopAppBar(id = stringResource(R.string.material),
                navigationIcon = {BackButton{navController.navigateUp()}},
                trailingIcon = {
                    IconButton(
                        onClick = {
                            actions.saveMaterial { id ->
                                navController.navigate(NavigationRoute.SingleMaterialSummary(id)){
                                    popUpTo(NavigationRoute.Warehouse){inclusive = false}
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
        snackbarHost = { SnackbarHost(hostState = snackBarHostState) }
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
            item{
                SplitButtonMenu(
                    content = if(state.type == JobType.NONE) stringResource(R.string.type) else state.type.name,
                    items = state.jobTypeMenu,
                    heightMenu = (state.jobTypeMenu.size * 55).dp
                )
            }
            item{
                SuggestionTextField(
                    title = stringResource(R.string.category ),
                    value = state.category,
                    isAutocompleteMode = true,
                    suggestions = state.categorySuggestionsList,
                    onValueChange = actions::setCategory
                )
            }
            item{Spacer(Modifier.size(8.dp))}
            item{
                CustomOutlineTextField(
                    label = stringResource(R.string.brand),
                    value = state.brand,
                    onValueChange = actions::setBrand
                )
            }
            item{Spacer(Modifier.size(8.dp))}
            item{
                CustomOutlineTextField(
                    label = stringResource(R.string.model),
                    value = state.model,
                    onValueChange = actions::setModel
                )
            }
            item{Spacer(Modifier.size(8.dp))}
            item{
                CustomOutlineTextField(
                    label = stringResource(R.string.quantity),
                    value = state.availableQuantity,
                    onValueChange = actions::setQuantity,
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Decimal)
                )
            }
            item{
                CustomOutlineTextField(
                    label = stringResource(R.string.unit_measurement),
                    value = state.unitMeasurement,
                    onValueChange = actions::setUnit
                )
            }
            item{Spacer(Modifier.size(8.dp))}
            if(previousBackStackEntry?.destination?.hasRoute<NavigationRoute.JobMaterials>() == true) {
                item{
                    GenericCard(
                        text = stringResource(R.string.customer),
                        trailingContent = {
                            Icon(
                                imageVector = Icons.Filled.ChevronRight,
                                contentDescription = stringResource(R.string.customers)
                            )
                        },
                        onClick = {
                            navController.navigate(
                                NavigationRoute.Select(
                                    textSearch = selectSearchText,
                                    items = SelectKey.AllCustomers
                                )
                            )
                        }
                    )
                }
                item{Spacer(Modifier.size(8.dp))}
            }
            if(state.type == JobType.CDZ){
                item{
                    CustomOutlineTextField(
                        label = stringResource(R.string.serial_number),
                        value = state.serialNumber,
                        onValueChange = actions::setSerialNumber
                    )
                }
                item{Spacer(Modifier.size(8.dp))}
                item{
                    CustomOutlineTextField(
                        label = stringResource(R.string.btu),
                        value = state.btu,
                        onValueChange = actions::setBtu
                    )
                }
                item{Spacer(Modifier.size(8.dp))}
                item{
                    CustomOutlineTextField(
                        label = stringResource(R.string.year_installation),
                        value = state.yearOfInstallation,
                        onValueChange = actions::setYearInstallation,
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Decimal)
                    )
                }
                item{Spacer(Modifier.size(8.dp))}
                item{
                    val machineTypeItems = actions.getMachineTypeMenu()
                    SplitButtonMenu(
                        content =
                            if(state.machineType == MachineType.NONE) stringResource(R.string.machine_type)
                            else state.machineType.name,
                        items = machineTypeItems,
                        heightMenu = (machineTypeItems.size * 55).dp
                    )
                }
                if(state.machineType == MachineType.Esterna){
                    item{
                        val splitItems = actions.getSplitNumbersMenu()
                        SplitButtonMenu(
                            content =
                                if(state.splitNumber == SplitNumber.NONE) stringResource(R.string.split_number)
                                else state.splitNumber.name,
                            items = splitItems,
                            heightMenu = (splitItems.size * 55).dp
                        )
                    }
                    item{Spacer(Modifier.size(8.dp))}
                    item{
                        CustomOutlineTextField(
                            label = stringResource(R.string.gas_quantity),
                            value = state.gasQty,
                            onValueChange = actions::setGasQty,
                            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Decimal)
                        )
                    }
                    item{Spacer(Modifier.size(8.dp))}
                    item{
                        CustomOutlineTextField(
                            label = stringResource(R.string.gas_type),
                            value = state.gasType,
                            onValueChange = actions::setGasType
                        )
                    }
                    item{Spacer(Modifier.size(8.dp))}
                }
            }
        }
    }
}