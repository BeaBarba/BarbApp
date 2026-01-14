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
import com.example.myapplication.debug.categorie
import com.example.myapplication.debug.scadenze
import com.example.myapplication.debug.venditori
import com.example.myapplication.ui.component.BackButton
import com.example.myapplication.ui.component.CustomOutlineTextField
import com.example.myapplication.ui.component.DatePickerFieldToModal
import com.example.myapplication.ui.component.DeleteButton
import com.example.myapplication.ui.component.GenericCard
import com.example.myapplication.ui.NavigationRoute
import com.example.myapplication.ui.component.SplitButtonMenu
import com.example.myapplication.ui.component.TopAppBar

@Composable
fun DeadlineAddActivity(
    navController: NavHostController
) {
    val previousBackStackEntry = navController.previousBackStackEntry
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            TopAppBar(
                navigationIcon = {BackButton{navController.navigateUp()}},
                id = stringResource(R.string.deadline),
                trailingIcon = {
                    IconButton(
                        onClick = {
                            navController.navigate(NavigationRoute.SingleDeadlineSummary){
                                popUpTo(NavigationRoute.DeadlineAdd){inclusive = true}
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
        }
    ) { contentPadding ->
        LazyColumn(
            modifier = Modifier
                .padding(
                    top = contentPadding.calculateTopPadding(),
                    start = contentPadding.calculateStartPadding(LayoutDirection.Ltr) + 8.dp,
                    end = contentPadding.calculateEndPadding(LayoutDirection.Ltr) + 8.dp,
                    bottom = contentPadding.calculateBottomPadding()
                )
        ) {
            item{CustomOutlineTextField(stringResource(R.string.name))}
            item{Spacer(Modifier.size(8.dp))}
            item{DatePickerFieldToModal(stringResource(R.string.date_issue))}
            item{Spacer(Modifier.size(8.dp))}
            item{CustomOutlineTextField(stringResource(R.string.number))}
            item{Spacer(Modifier.size(8.dp))}
            /*item{
                SplitButtonMenu(
                    content = stringResource(R.string.category),
                    items = categorie,
                    heightMenu = (categorie.size * 55).dp
                )
            }
            item{Spacer(Modifier.size(8.dp))}
            item{
                SplitButtonMenu(
                    content = stringResource(R.string.seller),
                    items = venditori,
                    heightMenu = (venditori.size * 55).dp
                )
            }
            item{Spacer(Modifier.size(8.dp))}
            item{
                GenericCard(
                    text = stringResource(R.string.bubbles),
                    trailingContent = {
                        Icon(
                            imageVector = Icons.Filled.ChevronRight,
                            contentDescription = "Add Bubbles",
                            modifier = Modifier.size(35.dp)
                        )
                    },
                    onClick = {navController.navigate(NavigationRoute.BubbleAdd)}
                )
            }

             */
            item{Spacer(Modifier.size(8.dp))}
            item{
                GenericCard(
                    text = stringResource(R.string.materials),
                    trailingContent = {
                        Icon(
                            imageVector = Icons.Filled.ChevronRight,
                            contentDescription = stringResource(R.string.add_item),
                            modifier = Modifier.size(35.dp)
                        )
                    },
                    onClick = {navController.navigate(NavigationRoute.MaterialAdd)}
                )
            }
            item{Spacer(Modifier.size(8.dp))}
            if(previousBackStackEntry?.destination?.hasRoute<NavigationRoute.SingleDeadlineSummary>() == true) {
                item {
                    DeleteButton {
                        scadenze = scadenze.subList(1, scadenze.size)
                        navController.navigate(NavigationRoute.AllDeadlinesSummary)
                    }
                }
                item { Spacer(Modifier.size(8.dp)) }
            }
        }
    }
}