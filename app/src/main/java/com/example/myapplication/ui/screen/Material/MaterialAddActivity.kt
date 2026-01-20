package com.example.myapplication.ui.screen.Material

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
import androidx.compose.material3.Checkbox
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
import com.example.myapplication.debug.categorie_menu
import com.example.myapplication.debug.categorie_prodotti
import com.example.myapplication.debug.prodotti
import com.example.myapplication.debug.tipi_menu
import com.example.myapplication.ui.component.BackButton
import com.example.myapplication.ui.component.CustomOutlineTextField
import com.example.myapplication.ui.NavigationRoute
import com.example.myapplication.ui.component.DeleteButton
import com.example.myapplication.ui.component.GenericCard
import com.example.myapplication.ui.component.MenuDivider
import com.example.myapplication.ui.component.MenuItem
import com.example.myapplication.ui.component.SplitButtonMenu
import com.example.myapplication.ui.component.SuggestionTextField
import com.example.myapplication.ui.component.TopAppBar
import com.example.myapplication.ui.component.checkboxColors

@Composable
fun MaterialAddActivity(
    navController : NavHostController
){
    val previousBackStackEntry = navController.previousBackStackEntry
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            TopAppBar(id = stringResource(R.string.material),
                navigationIcon = {BackButton{navController.navigateUp()}},
                trailingIcon = {
                    IconButton(
                        onClick = {
                            navController.navigate(NavigationRoute.SingleMaterialSummary){
                                popUpTo(NavigationRoute.MaterialAdd){inclusive = true}
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
        var type by remember { mutableStateOf("")}
        var tipi = tipi_menu.map{item -> MenuItem(name = item.name, {type = item.name})}
        var category by remember { mutableStateOf("")}
        var categorie = categorie_menu.map{item -> MenuItem(name = item.name, {category = item.name})}
        var checked by remember {mutableStateOf("")}
        var machine_type = listOf(MenuItem(name = "Interna", {checked = "Interna"}), MenuItem(name = "Esterna", {checked = "Esterna"}))
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
                    content = if(type.isNotEmpty()){type}else{stringResource(R.string.type)},
                    items = tipi
                )
            }
            item{
                /*
                SplitButtonMenu(
                    content = if(category.isNotEmpty()){category}else{stringResource(R.string.category)},
                    items = categorie
                )
                */
                SuggestionTextField(
                    title = stringResource(R.string.category),
                    isAutocompleteMode = true,
                    suggestions = categorie_prodotti
                )
            }
            item{Spacer(Modifier.size(8.dp))}
            item{
                CustomOutlineTextField(
                    label =stringResource(R.string.brand),
                    onValueChange = {}
                )
            }
            item{Spacer(Modifier.size(8.dp))}
            item{
                CustomOutlineTextField(
                    label =stringResource(R.string.model),
                    onValueChange = {}
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
                        onClick = {navController.navigate(NavigationRoute.Select("Cliente", "CustomerAdd"))}
                    )
                }
                item{Spacer(Modifier.size(8.dp))}
            }
            if(type.equals("CDZ")){
                item{
                    CustomOutlineTextField(
                        label =stringResource(R.string.serial_number),
                        onValueChange = {}
                    )
                }
                item{Spacer(Modifier.size(8.dp))}
                item{
                    CustomOutlineTextField(
                        label =stringResource(R.string.btu),
                        onValueChange = {}
                    )
                }
                item{Spacer(Modifier.size(8.dp))}
                item{
                    CustomOutlineTextField(
                        label =stringResource(R.string.year_installation),
                        onValueChange = {}
                    )
                }
                item{Spacer(Modifier.size(8.dp))}
                item{
                    SplitButtonMenu(
                        content = if(checked.isNotEmpty()){checked}else{stringResource(R.string.machine_type)},
                        items = machine_type
                    )
                }
                if(checked.equals("Esterna")){
                    item{
                        CustomOutlineTextField(
                            label =stringResource(R.string.split_number),
                            onValueChange = {}
                        )
                    }
                    item{Spacer(Modifier.size(8.dp))}
                    item{
                        CustomOutlineTextField(
                            label =stringResource(R.string.gas_quantity),
                            onValueChange = {}
                        )
                    }
                    item{Spacer(Modifier.size(8.dp))}
                    item{
                        CustomOutlineTextField(
                            label =stringResource(R.string.gas_type),
                            onValueChange = {}
                        )
                    }
                    item{Spacer(Modifier.size(8.dp))}
                }
            }
            if(previousBackStackEntry?.destination?.hasRoute<NavigationRoute.SingleMaterialSummary>() == true) {
                item {
                    DeleteButton {
                        prodotti = prodotti.subList(1, prodotti.size)
                        navController.navigate(NavigationRoute.Warehouse){
                            popUpTo(NavigationRoute.Warehouse){inclusive = true}
                            launchSingleTop = true
                        }
                    }
                }
                item { Spacer(Modifier.size(8.dp)) }
            }
        }
    }
}