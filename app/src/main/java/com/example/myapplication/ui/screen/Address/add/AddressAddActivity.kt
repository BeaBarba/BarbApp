package com.example.myapplication.ui.screen.Address.add

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.calculateEndPadding
import androidx.compose.foundation.layout.calculateStartPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.myapplication.R
import com.example.myapplication.ui.component.BackButton
import com.example.myapplication.ui.component.CustomOutlineTextField
import com.example.myapplication.ui.component.TitleLabel
import com.example.myapplication.ui.component.TopAppBar

@Composable
fun AddressAddActivity(
    addressId: Int?,
    state: AddressAddState,
    actions: AddressAddActions,
    navController : NavHostController
){
    addressId?.let(actions::populateFromEdit)
    Scaffold(
        topBar = {
            TopAppBar(
                navigationIcon = { BackButton { navController.navigateUp() }},
                id = stringResource(R.string.address),
                trailingIcon = {
                    IconButton(
                        onClick = {
                            actions.saveAddress()
                            navController.navigateUp()
                        },
                        colors = IconButtonDefaults.iconButtonColors(
                            contentColor = MaterialTheme.colorScheme.onPrimary
                        )
                    ) {
                        Icon(
                            imageVector = Icons.Filled.Check,
                            contentDescription = stringResource(R.string.save),
                        )
                    }
                }
            )
        }
    ) { contentPadding ->
        LazyColumn(
            modifier = Modifier
                .padding(
                    start = contentPadding.calculateStartPadding(LayoutDirection.Ltr) + 8.dp,
                    end = contentPadding.calculateEndPadding(LayoutDirection.Ltr) + 8.dp,
                    top = contentPadding.calculateTopPadding() + 8.dp,
                    bottom = contentPadding.calculateBottomPadding()
                )
        ) {
            item{
                CustomOutlineTextField(
                    leadingIcon = {
                        IconButton(
                            onClick = {},
                        ) {
                            Icon(
                                imageVector = Icons.Filled.LocationOn,
                                contentDescription = stringResource(R.string.address)
                            )
                        }
                    },
                    label = stringResource(R.string.address),
                    value = state.address,
                    onValueChange = actions::setAddress
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
                    onValueChange = actions::setZip
                )
            }
            item{Spacer(Modifier.size(8.dp))}

            item{TitleLabel(stringResource(R.string.address_details))}
            item{Spacer(Modifier.size(8.dp))}
            item{
                CustomOutlineTextField(
                    label = stringResource(R.string.staircase),
                    value = state.staircase,
                    onValueChange = actions::setStaircase
                )
            }
            item{
                CustomOutlineTextField(
                    label = stringResource(R.string.floor),
                    value = state.floor,
                    onValueChange = actions::setFloor
                )
            }
            item{
                CustomOutlineTextField(
                    label = stringResource(R.string.interior),
                    value = state.interior,
                    onValueChange = actions::setInterior
                )
            }
            item{
                CustomOutlineTextField(
                    label = stringResource(R.string.real_estate_units),
                    value = state.units,
                    onValueChange = actions::setUnits
                )
            }
            item{Spacer(Modifier.size(8.dp))}

            item{TitleLabel(stringResource(R.string.property_details))}
            item{Spacer(Modifier.size(8.dp))}
            item{
                CustomOutlineTextField(
                    label = stringResource(R.string.sheet),
                    value = state.sheet,
                    onValueChange = actions::setSheet
                )
            }
            item{
                CustomOutlineTextField(
                    label = stringResource(R.string.property_map),
                    value = state.map,
                    onValueChange = actions::setMap
                )
            }
            item{
                CustomOutlineTextField(
                    label = stringResource(R.string.subordinate),
                    value = state.subordinate,
                    onValueChange = actions::setSubordinate
                )
            }
            item{
                CustomOutlineTextField(
                    label = stringResource(R.string.year_construction),
                    value = state.yearOfConstruction,
                    onValueChange = actions::setYearOfConstruction
                )
            }
            item{
                CustomOutlineTextField(
                    label = stringResource(R.string.usable_area),
                    value = state.usableArea,
                    onValueChange = actions::setUsableArea
                )
            }
        }
    }
}