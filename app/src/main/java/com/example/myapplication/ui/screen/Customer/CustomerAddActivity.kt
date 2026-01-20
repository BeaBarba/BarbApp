package com.example.myapplication.ui.screen.Customer

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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import androidx.navigation.NavDestination.Companion.hasRoute
import androidx.navigation.NavHostController
import com.example.myapplication.R
import com.example.myapplication.data.modules.CustomerType
import com.example.myapplication.ui.component.BackButton
import com.example.myapplication.ui.component.CustomOutlineTextField
import com.example.myapplication.ui.component.DeleteButton
import com.example.myapplication.ui.NavigationRoute
import com.example.myapplication.ui.component.DatePickerFieldToModal
import com.example.myapplication.ui.component.GenericCard
import com.example.myapplication.ui.component.MenuItem
import com.example.myapplication.ui.component.SplitButtonMenu
import com.example.myapplication.ui.component.TitleLabel
import com.example.myapplication.ui.component.TopAppBar

@Composable
fun CustomerAddActivity(
    navController : NavHostController
){
    var typeSelected by remember{ mutableStateOf("") }
    val displayLabel = if (typeSelected.isEmpty()) {
        stringResource(R.string.type)
    } else {
        when (typeSelected){
            CustomerType.Company.toString() -> stringResource(R.string.company)
            CustomerType.Private.toString() -> stringResource(R.string.private_customer)
            else -> {""}
        }
    }
    var type = listOf(
        MenuItem(stringResource(R.string.company), {typeSelected = CustomerType.Company.toString()}),
        MenuItem(stringResource(R.string.private_customer),{typeSelected = CustomerType.Private.toString()})
    )

    val previousBackStackEntry = navController.previousBackStackEntry
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            TopAppBar(
                navigationIcon = {BackButton{navController.navigateUp()}},
                id = stringResource(R.string.customer),
                trailingIcon = {
                    IconButton(
                        onClick = {
                            navController.navigate(NavigationRoute.SingleCustomerSummary){
                                popUpTo(NavigationRoute.CustomerAdd){inclusive = true}
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
                    onValueChange = {}
                )
            }
            item{
                CustomOutlineTextField(
                    label = stringResource(R.string.name),
                    onValueChange = {}
                )
            }
            if(typeSelected == CustomerType.Private.toString()){
                item{
                    CustomOutlineTextField(
                        label = stringResource(R.string.last_name),
                        onValueChange = {}
                    )
                }
                item{
                    CustomOutlineTextField(
                        label = stringResource(R.string.place_birth),
                        onValueChange = {}
                    )
                }
                item{DatePickerFieldToModal(stringResource(R.string.date_birth))}
            }
            if(typeSelected == CustomerType.Company.toString()){
                item{
                    CustomOutlineTextField(
                        label = stringResource(R.string.company_name),
                        onValueChange = {}
                    )
                }
                item{
                    CustomOutlineTextField(
                        label = stringResource(R.string.unique_code),
                        onValueChange = {}
                    )
                }
                item{
                    CustomOutlineTextField(
                        label = stringResource(R.string.vat_number),
                        onValueChange = {}
                    )
                }
            }
            item{Spacer(Modifier.size(8.dp))}

            item{TitleLabel(stringResource(R.string.contact))}
            item{
                CustomOutlineTextField(
                    label = stringResource(R.string.email),
                    onValueChange = {}
                )
            }
            item{
                CustomOutlineTextField(
                    label = stringResource(R.string.phone),
                    onValueChange = {}
                )
            }
            item{
                CustomOutlineTextField(
                    label = stringResource(R.string.note_number),
                    onValueChange = {}
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
                    onValueChange = {}
                )
            }
            item{
                CustomOutlineTextField(
                    label = stringResource(R.string.municipality),
                    onValueChange = {}
                )
            }
            item{
                CustomOutlineTextField(
                    label = stringResource(R.string.city),
                    onValueChange = {}
                )
            }
            item{
                CustomOutlineTextField(
                    label = stringResource(R.string.province),
                    onValueChange = {}
                )
            }
            item{
                CustomOutlineTextField(
                    label = stringResource(R.string.postal_code),
                    onValueChange = {}
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
                            Icons.Filled.ChevronRight,
                            contentDescription = stringResource(R.string.edit),
                            modifier = Modifier.size(35.dp)
                        )
                    },
                    onClick = {navController.navigate(NavigationRoute.Select("Clienti", "CustomerAdd"))}
                )
            }
            item{
                CustomOutlineTextField(
                    label = stringResource(R.string.name),
                    onValueChange = {}
                )
            }
            item{
                CustomOutlineTextField(
                    label = stringResource(R.string.last_name),
                    onValueChange = {}
                )
            }
            item{
                CustomOutlineTextField(
                    label = stringResource(R.string.phone),
                    onValueChange = {}
                )
            }


            item {Spacer(Modifier.size(8.dp))}
            if (previousBackStackEntry?.destination?.hasRoute<NavigationRoute.SingleCustomerSummary>() == true) {
                item {
                    DeleteButton{
                        navController.navigate(NavigationRoute.AllCustomersSummary){
                            popUpTo(NavigationRoute.AllCustomersSummary){inclusive = true}
                            launchSingleTop = true
                        }
                    }
                }
                item{Spacer(Modifier.size(8.dp))}
            }
        }
    }
}