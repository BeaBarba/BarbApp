package com.example.myapplication.ui.screen.Deadline

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
import com.example.myapplication.data.modules.DeadlineType
import com.example.myapplication.data.modules.FrequencyType
import com.example.myapplication.debug.categorie_menu
import com.example.myapplication.debug.categorie_s_menu
import com.example.myapplication.debug.invoicesType
import com.example.myapplication.debug.scadenze
import com.example.myapplication.ui.component.BackButton
import com.example.myapplication.ui.component.CustomOutlineTextField
import com.example.myapplication.ui.component.DatePickerFieldToModal
import com.example.myapplication.ui.component.DeleteButton
import com.example.myapplication.ui.component.GenericCard
import com.example.myapplication.ui.NavigationRoute
import com.example.myapplication.ui.component.MenuItem
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
        var selectedFrequency by remember { mutableStateOf(FrequencyType.Nessuna)}
        var frequency_menu : List<MenuItem> = listOf(
            MenuItem(FrequencyType.Anno.toString(), {selectedFrequency = FrequencyType.Anno}),
            MenuItem(FrequencyType.Mese.toString(), {selectedFrequency = FrequencyType.Mese}),
            MenuItem(FrequencyType.Settimana.toString(), {selectedFrequency = FrequencyType.Settimana})
        )
        var selectedType by remember{ mutableStateOf(DeadlineType.Tipo)}
        var type_menu : List<MenuItem> = listOf(
            MenuItem(DeadlineType.Singola.toString(), {selectedType = DeadlineType.Singola}),
            MenuItem(DeadlineType.Periodica.toString(), {selectedType = DeadlineType.Periodica}),
        )
        LazyColumn(
            modifier = Modifier
                .padding(
                    top = contentPadding.calculateTopPadding() + 8.dp,
                    start = contentPadding.calculateStartPadding(LayoutDirection.Ltr) + 8.dp,
                    end = contentPadding.calculateEndPadding(LayoutDirection.Ltr) + 8.dp,
                    bottom = contentPadding.calculateBottomPadding()
                )
        ) {
            item{
                SplitButtonMenu(
                    content =
                        if(selectedType.equals(DeadlineType.Tipo)){
                            stringResource(R.string.type)
                        }else{
                            selectedType.toString()
                        },
                    items = type_menu,
                    heightMenu = (type_menu.size * 55).dp
                )
            }
            if(selectedType.equals(DeadlineType.Periodica)){
                item{
                    SplitButtonMenu(
                        content =
                            if(selectedFrequency.equals(FrequencyType.Nessuna)){
                                stringResource(R.string.frequency)
                            }else{
                                selectedFrequency.toString()
                            },
                        items = frequency_menu,
                        heightMenu = (frequency_menu.size * 55).dp
                    )
                }
            }
            item{
                SplitButtonMenu(
                    content = stringResource(R.string.category),
                    items = categorie_s_menu,
                    heightMenu = (categorie_menu.size * 55).dp
                )
            }
            item{
                CustomOutlineTextField(
                    label = stringResource(R.string.name),
                    onValueChange = {}
                )
            }
            item{
                DatePickerFieldToModal(
                    title =
                        if(selectedType.equals(DeadlineType.Singola)) {
                                stringResource(R.string.date_deadline)
                        }else{
                            stringResource(R.string.date_end)
                        },
                    onValueChange = {},
                    value = ""
                )
            }
            item{
                DatePickerFieldToModal(
                    title = stringResource(R.string.date_payment),
                    onValueChange = {},
                    value = ""
                )
            }
            item{
                CustomOutlineTextField(
                    label = stringResource(R.string.amount),
                    onValueChange = {}
                )
            }
            item{Spacer(Modifier.size(8.dp))}
            item{
                GenericCard(
                    text = stringResource(R.string.invoices_purchase),
                    trailingContent = {
                        Icon(
                            imageVector = Icons.Filled.ChevronRight,
                            contentDescription = stringResource(R.string.add_item),
                            modifier = Modifier.size(35.dp)
                        )
                    },
                    onClick = {navController.navigate(NavigationRoute.Select("Fatture", "InvoiceAdd"))}
                )
            }
            item{Spacer(Modifier.size(8.dp))}
            if(previousBackStackEntry?.destination?.hasRoute<NavigationRoute.SingleDeadlineSummary>() == true) {
                item {
                    DeleteButton {
                        scadenze = scadenze.subList(1, scadenze.size)
                        navController.navigate(NavigationRoute.AllDeadlinesSummary){
                            popUpTo(NavigationRoute.AllDeadlinesSummary){inclusive = true}
                            launchSingleTop = true
                        }
                    }
                }
                item { Spacer(Modifier.size(8.dp)) }
            }
        }
    }
}