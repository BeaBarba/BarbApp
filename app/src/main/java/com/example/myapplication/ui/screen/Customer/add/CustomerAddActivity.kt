package com.example.myapplication.ui.screen.Customer.add

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.calculateEndPadding
import androidx.compose.foundation.layout.calculateStartPadding
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
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
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import com.example.myapplication.R
import com.example.myapplication.data.modules.CustomerType
import com.example.myapplication.data.modules.SelectKey
import com.example.myapplication.ui.component.BackButton
import com.example.myapplication.ui.component.CustomOutlineTextField
import com.example.myapplication.ui.NavigationRoute
import com.example.myapplication.ui.component.DatePickerFieldToModal
import com.example.myapplication.ui.component.GenericCard
import com.example.myapplication.ui.component.MenuItem
import com.example.myapplication.ui.component.SplitButtonMenu
import com.example.myapplication.ui.component.TitleLabel
import com.example.myapplication.ui.component.TopAppBar
import java.time.LocalDate
import java.time.format.DateTimeFormatter

@Composable
fun CustomerAddActivity(
    customerId : String?,
    state : CustomerAddState,
    actions: CustomerAddActions,
    navController : NavHostController
){
    LaunchedEffect(customerId){
        customerId?.let(actions::populateFromEdit)
    }

    val displayLabel =
        when (state.customerType){
            CustomerType.Azienda -> stringResource(R.string.company)
            CustomerType.Privato -> stringResource(R.string.private_customer)
        }

    var type = listOf(
        MenuItem(stringResource(R.string.company), {actions.setCustomerType(CustomerType.Azienda)}),
        MenuItem(stringResource(R.string.private_customer),{actions.setCustomerType(CustomerType.Privato)})
    )

    val selectSearchText = stringResource(R.string.customer)

    val currentBackStackEntry = navController.currentBackStackEntry

    val selectedItems by currentBackStackEntry?.savedStateHandle
        ?.getStateFlow<List<String>?>("selectedIds", emptyList())
        ?.collectAsStateWithLifecycle() ?: remember { mutableStateOf(emptyList())}
    LaunchedEffect(selectedItems) {
        selectedItems?.let{ ids ->
            if(ids.isNotEmpty()) {
                println("println " + ids.size)
                actions.setReferral(ids.first())
            }
        }
        currentBackStackEntry?.savedStateHandle?.remove<List<String>>("selectedIds")
    }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            TopAppBar(
                navigationIcon = {BackButton{navController.navigateUp()}},
                id = stringResource(R.string.customer),
                trailingIcon = {
                    IconButton(
                        onClick = {
                            actions.save()
                            navController.navigate(NavigationRoute.SingleCustomerSummary(state.id)){
                                popUpTo(NavigationRoute.AllCustomersSummary){inclusive = false}
                            }
                        },
                        colors = IconButtonDefaults.iconButtonColors(contentColor = MaterialTheme.colorScheme.onPrimary)
                    ) {
                        Icon(Icons.Filled.Check, contentDescription = stringResource(R.string.save))
                    }
                }
            )
        }
    ) { contentPadding ->
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
            item{SplitButtonMenu(content = displayLabel, type, heightMenu = (2 * 55).dp)}
            item{TitleLabel(stringResource(R.string.personal_details))}
            item{
                CustomOutlineTextField(
                    label = stringResource(R.string.id),
                    value = state.id,
                    onValueChange = actions::setCustomerId
                )
            }
            item{
                CustomOutlineTextField(
                    label = stringResource(R.string.name),
                    value = state.name,
                    onValueChange = actions::setCustomerName
                )
            }
            if(state.customerType == CustomerType.Privato){
                item{
                    CustomOutlineTextField(
                        label = stringResource(R.string.last_name),
                        value = state.privateLastName,
                        onValueChange = actions::setPrivateLastName,
                    )
                }
                item{
                    CustomOutlineTextField(
                        label = stringResource(R.string.place_birth),
                        value = state.privatePlaceBirth,
                        onValueChange = actions::setPrivatePlaceBirth,
                    )
                }
                item{
                    DatePickerFieldToModal(
                        title = stringResource(R.string.date_birth),
                        value = state.privateDateBirth.format(DateTimeFormatter.ofPattern("dd/MM/yyyy")),
                        onValueChange = {
                            actions.setPrivateDateBirth(
                                LocalDate.parse(it, DateTimeFormatter.ofPattern("dd/MM/yyyy"))
                            )
                        }
                    )
                }
            }
            if(state.customerType == CustomerType.Azienda){
                item{
                    CustomOutlineTextField(
                        label = stringResource(R.string.company_name),
                        value = state.companyName,
                        onValueChange = actions::setCompanyName,
                    )
                }
                item{
                    CustomOutlineTextField(
                        label = stringResource(R.string.unique_code),
                        value = state.companyUniqueCode,
                        onValueChange = actions::setCompanyUniqueCode
                    )
                }
                item{
                    CustomOutlineTextField(
                        label = stringResource(R.string.vat_number),
                        value = state.companyVatNumber,
                        onValueChange = actions::setCompanyVat
                    )
                }
            }
            item{Spacer(Modifier.size(8.dp))}
            item{TitleLabel(stringResource(R.string.contact))}
            item{
                CustomOutlineTextField(
                    label = stringResource(R.string.email),
                    value = state.email,
                    onValueChange = actions::setEmail
                )
            }
            item{
                CustomOutlineTextField(
                    label = stringResource(R.string.phone),
                    value = state.phoneNumber,
                    onValueChange = actions::setPhoneNumber
                )
            }
            item{
                CustomOutlineTextField(
                    label = stringResource(R.string.note_number),
                    value = state.notePhoneNumber,
                    onValueChange = actions::setPhoneNote
                )
            }
            item{Spacer(Modifier.size(8.dp))}
            item{Spacer(Modifier.size(8.dp))}
            item{TitleLabel(stringResource(R.string.residence))}
            item{
                CustomOutlineTextField(
                    leadingIcon = {
                        /*IconButton(
                            onClick = {}
                        ) {*/
                            Icon(Icons.Filled.LocationOn, contentDescription = stringResource(R.string.address))
                        //}
                    },
                    label = stringResource(R.string.address),
                    value = state.address,
                    onValueChange = actions::setAddress
                )
            }
            item{
                CustomOutlineTextField(
                    label = stringResource(R.string.house_number),
                    value = state.houseNumber,
                    onValueChange = actions::setHouseNumber
                )
            }
            item{
                CustomOutlineTextField(
                    label = stringResource(R.string.municipality),
                    value = state.municipality,
                    onValueChange = actions::setMunicipality
                )
            }
            item{
                CustomOutlineTextField(
                    label = stringResource(R.string.city),
                    value = state.city,
                    onValueChange = actions::setCity
                )
            }
            item{
                CustomOutlineTextField(
                    label = stringResource(R.string.province),
                    value = state.province,
                    onValueChange = actions::setProvince
                )
            }
            item{
                CustomOutlineTextField(
                    label = stringResource(R.string.postal_code),
                    value = state.zip,
                    onValueChange = actions::setZip,
                )
            }
            item{Spacer(Modifier.size(8.dp))}
            item{TitleLabel(stringResource(R.string.reference))}
            item{Spacer(Modifier.size(8.dp))}
            item{
                GenericCard(
                    text = stringResource(R.string.customer) + " " + stringResource(R.string.existing).lowercase(),
                    trailingContent = {
                        Icon(
                            imageVector = Icons.Filled.ChevronRight,
                            contentDescription = stringResource(R.string.edit),
                            modifier = Modifier.size(35.dp)
                        )
                    },
                    onClick = {navController.navigate(NavigationRoute.Select(selectSearchText, SelectKey.AllCustomers))}
                )
            }
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
                    value = state.referencePhoneNumber,
                    onValueChange = actions::setReferencePhoneNumber
                )
            }
            item {Spacer(Modifier.size(8.dp))}
        }
    }
}