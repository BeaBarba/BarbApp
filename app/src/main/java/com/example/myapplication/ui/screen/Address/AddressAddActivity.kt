package com.example.myapplication.ui.screen

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
    navController : NavHostController
){
    Scaffold(
        topBar = {
            TopAppBar(
                navigationIcon = { BackButton { navController.navigateUp() }},
                id = stringResource(R.string.address),
                trailingIcon = {
                    IconButton(
                        onClick = {navController.navigateUp() /*Save data*/},
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
                    label = stringResource(R.string.address)
                )
            }
            item{ CustomOutlineTextField(stringResource(R.string.municipality))}
            item{ CustomOutlineTextField(stringResource(R.string.city))}
            item{ CustomOutlineTextField(stringResource(R.string.province))}
            item{ CustomOutlineTextField(stringResource(R.string.postal_code))}
            item{Spacer(Modifier.size(8.dp))}

            item{ TitleLabel(stringResource(R.string.address_details))}
            item{Spacer(Modifier.size(8.dp))}
            item{ CustomOutlineTextField(stringResource(R.string.staircase))}
            item{ CustomOutlineTextField(stringResource(R.string.floor))}
            item{ CustomOutlineTextField(stringResource(R.string.interior))}
            item{ CustomOutlineTextField(stringResource(R.string.real_estate_units))}
            item{Spacer(Modifier.size(8.dp))}

            item{ TitleLabel(stringResource(R.string.property_details))}
            item{Spacer(Modifier.size(8.dp))}
            item{ CustomOutlineTextField(stringResource(R.string.sheet))}
            item{ CustomOutlineTextField(stringResource(R.string.property_map))}
            item{ CustomOutlineTextField(stringResource(R.string.subordinate))}
            item{ CustomOutlineTextField(stringResource(R.string.year_construction))}
            item{ CustomOutlineTextField(stringResource(R.string.usable_area))}
        }
    }
}