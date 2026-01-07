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
import androidx.compose.material.icons.filled.ChevronRight
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.outlined.AddPhotoAlternate
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
import com.example.myapplication.ui.component.Avatar
import com.example.myapplication.ui.component.BackButton
import com.example.myapplication.ui.component.CustomOutlineTextField
import com.example.myapplication.ui.component.CustomTimePicker
import com.example.myapplication.ui.component.DatePickerFieldToModal
import com.example.myapplication.ui.component.DeleteButton
import com.example.myapplication.ui.component.GenericCard
import com.example.myapplication.ui.NavigationRoute
import com.example.myapplication.ui.component.SplitButtonMenu
import com.example.myapplication.ui.component.TopAppBar

@Composable
fun JobAddActivity(
    navController : NavHostController
){
    val previousBackStackEntry = navController.previousBackStackEntry
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            TopAppBar(
                id = stringResource(R.string.intervention),
                navigationIcon = {BackButton{navController.navigateUp()}},
                trailingIcon = {
                    IconButton(
                        onClick = {navController.navigate(NavigationRoute.SingleJobSummary)},
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
                        Avatar(
                            char = letters.get(0),
                        )
                    },
                    text = customers.get(0),
                    trailingContent = {
                        Icon(
                            Icons.Filled.ChevronRight,
                            contentDescription = stringResource(R.string.edit),
                            modifier = Modifier.size(35.dp)
                        )
                    },
                    onClick = {navController.navigate(NavigationRoute.CustomerAdd)}
                )
            }
            item{Spacer(Modifier.size(8.dp))}
            item{
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
            item{Spacer(Modifier.size(8.dp))}
            item{CustomOutlineTextField(stringResource(R.string.city))}
            item{Spacer(Modifier.size(8.dp))}
            item{CustomOutlineTextField(stringResource(R.string.postal_code))}
            item{Spacer(Modifier.size(8.dp))}
            item{CustomOutlineTextField(stringResource(R.string.province))}
            item{Spacer(Modifier.size(8.dp))}
            item{CustomOutlineTextField(stringResource(R.string.price))}
            item{Spacer(Modifier.size(8.dp))}
            item{DatePickerFieldToModal()}
            item{Spacer(Modifier.size(8.dp))}
            item{CustomTimePicker(stringResource(R.string.start_time))}
            item{Spacer(Modifier.size(8.dp))}
            item{CustomTimePicker(stringResource(R.string.end_time))}
            item{Spacer(Modifier.size(8.dp))}
            item{CustomOutlineTextField(stringResource(R.string.description))}
            item{Spacer(Modifier.size(8.dp))}
            item{SplitButtonMenu(content = stringResource(R.string.type), items = tipi, heightMenu = (tipi.size *55).dp)}
            item{Spacer(Modifier.size(8.dp))}
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
            if (previousBackStackEntry?.destination?.hasRoute<NavigationRoute.SingleJobSummary>() == true) {
                item {
                    DeleteButton {
                        interventi = interventi.subList(1, interventi.size)
                        navController.navigate(NavigationRoute.AllJobsSummary)
                    }
                }
                item { Spacer(Modifier.size(8.dp)) }
            }
        }
    }
}