package com.example.myapplication.ui.screen

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.calculateEndPadding
import androidx.compose.foundation.layout.calculateStartPadding
import androidx.compose.foundation.layout.fillMaxSize
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
import androidx.navigation.NavDestination.Companion.hasRoute
import androidx.navigation.NavHostController
import com.example.myapplication.R
import com.example.myapplication.ui.component.BackButton
import com.example.myapplication.ui.component.CustomOutlineTextField
import com.example.myapplication.ui.component.DeleteButton
import com.example.myapplication.ui.NavigationRoute
import com.example.myapplication.ui.component.TitleLabel
import com.example.myapplication.ui.component.TopAppBar

@Composable
fun CustomerAddActivity(
    navController : NavHostController
){
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
            item {TitleLabel(stringResource(R.string.personal_details))}
            item {CustomOutlineTextField(stringResource(R.string.id))}
            item {CustomOutlineTextField(stringResource(R.string.name))}
            item {CustomOutlineTextField(stringResource(R.string.last_name))}
            item {
                CustomOutlineTextField(
                    leadingIcon = {
                        /*IconButton(
                            onClick = {}
                        ) {*/
                            Icon(Icons.Filled.LocationOn, contentDescription = stringResource(R.string.address))
                        //}
                    },
                    label = stringResource(R.string.address)
                )
            }
            item {CustomOutlineTextField(stringResource(R.string.municipality))}
            item {CustomOutlineTextField(stringResource(R.string.province))}
            item {CustomOutlineTextField(stringResource(R.string.postal_code))}
            item {Spacer(Modifier.size(8.dp))}
            if (previousBackStackEntry?.destination?.hasRoute<NavigationRoute.SingleCustomerSummary>() == true) {
                item {
                    DeleteButton{
                        navController.navigate(NavigationRoute.AllCustomersSummary)
                    }
                }
                item{Spacer(Modifier.size(8.dp))}
            }
        }
    }
}