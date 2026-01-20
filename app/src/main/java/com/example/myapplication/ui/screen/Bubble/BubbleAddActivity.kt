package com.example.myapplication.ui.screen.Bubble

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.calculateEndPadding
import androidx.compose.foundation.layout.calculateStartPadding
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.ChevronRight
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
import com.example.myapplication.debug.bolle
import com.example.myapplication.debug.prodotti
import com.example.myapplication.debug.venditori
import com.example.myapplication.ui.component.BackButton
import com.example.myapplication.ui.component.CustomOutlineTextField
import com.example.myapplication.ui.component.DatePickerFieldToModal
import com.example.myapplication.ui.component.DeleteButton
import com.example.myapplication.ui.component.GenericCard
import com.example.myapplication.ui.NavigationRoute
import com.example.myapplication.ui.component.CustomDivider
import com.example.myapplication.ui.component.MenuItem
import com.example.myapplication.ui.component.SplitButtonMenu
import com.example.myapplication.ui.component.TitleLabel
import com.example.myapplication.ui.component.TopAppBar

@Composable
fun BubbleAddActivity(
    navController : NavHostController
){
    val previousBackStackEntry = navController.previousBackStackEntry
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            TopAppBar(
                navigationIcon = {BackButton {navController.navigateUp()}},
                id = stringResource(R.string.bubble),
                trailingIcon = {
                    IconButton(
                        onClick = {
                            navController.navigate(NavigationRoute.SingleBubbleSummary){
                                popUpTo(NavigationRoute.BubbleAdd){inclusive = true}
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
        var selected by remember { mutableStateOf("")}
        var venditori_menu : MutableList<MenuItem> = (
                listOf(MenuItem(name = "Nuovo",{selected = "Nuovo"})) +
                venditori.map{item ->MenuItem(name = item, {selected = item})}
                ).toMutableList()
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
                CustomOutlineTextField(
                    label = stringResource(R.string.number),
                    onValueChange = {}
                )
            }
            item{DatePickerFieldToModal(stringResource(R.string.date_issue))}
            item{Spacer(Modifier.size(8.dp))}
            item{
                SplitButtonMenu(
                    content = if(selected.equals("Nuovo") || selected.equals("")){stringResource(R.string.seller)}else{selected},
                    items = venditori_menu,
                    heightMenu = (venditori_menu.size * 55).dp
                )
            }
            if(selected.equals("Nuovo")){
                item{
                    CustomOutlineTextField(
                        label = stringResource(R.string.name),
                        onValueChange = {}
                    )
                }
                item{Spacer(Modifier.size(8.dp))}
            }
            item{CustomDivider()}
            item {
                GenericCard(
                    text = stringResource(R.string.material),
                    trailingContent = {
                        Icon(
                            Icons.Filled.ChevronRight,
                            contentDescription = stringResource(R.string.edit),
                            modifier = Modifier.size(35.dp)
                        )
                    },
                    onClick = { navController.navigate(NavigationRoute.Select("Materiale", "Materials")) }
                )
            }
            item {Spacer(Modifier.size(8.dp))}
            itemsIndexed(prodotti.subList(0,6)){index, item ->
                TitleLabel(item.nome)
                CustomOutlineTextField(
                    label = stringResource(R.string.quantity),
                    onValueChange = {}
                )
                CustomOutlineTextField(
                    label = stringResource(R.string.unit_price),
                    onValueChange = {}
                )
                CustomOutlineTextField(
                    label = stringResource(R.string.vat),
                    onValueChange = {}
                )
                if(index < 5/*prodotti.size*/) {
                    CustomDivider()
                }
            }
            item{Spacer(Modifier.size(8.dp))}
            if (previousBackStackEntry?.destination?.hasRoute<NavigationRoute.SingleBubbleSummary>() == true) {
                item {

                    DeleteButton {
                        bolle = bolle.subList(1, bolle.size)
                        navController.navigate(NavigationRoute.AllBubblesSummary){
                            popUpTo(NavigationRoute.AllBubblesSummary){inclusive = true}
                            launchSingleTop = true
                        }
                    }
                }
                item { Spacer(Modifier.size(8.dp)) }
            }
        }
    }
}