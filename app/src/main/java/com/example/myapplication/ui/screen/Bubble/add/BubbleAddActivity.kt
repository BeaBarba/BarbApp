package com.example.myapplication.ui.screen.Bubble.bubbleAddActivity

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.calculateEndPadding
import androidx.compose.foundation.layout.calculateStartPadding
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavDestination.Companion.hasRoute
import androidx.navigation.NavHostController
import com.example.myapplication.R
import com.example.myapplication.data.modules.SelectKey
import com.example.myapplication.debug.bolle
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
import com.example.myapplication.ui.screen.Bubble.add.BubbleAddActions
import com.example.myapplication.ui.screen.Bubble.add.BubbleAddState
import java.text.DecimalFormat
import java.time.LocalDate
import java.time.format.DateTimeFormatter

@Composable
fun BubbleAddActivity(
    bubbleId: Int?,
    state: BubbleAddState,
    actions: BubbleAddActions,
    navController : NavHostController
){
    actions.populateView(bubbleId)

    val previousBackStackEntry = navController.previousBackStackEntry

    val selectSearchText = stringResource(R.string.materials)

    val currentBackStackEntry = navController.currentBackStackEntry
    val selectedItems by currentBackStackEntry?.savedStateHandle
        ?.getStateFlow<List<String>?>("selectedIds", emptyList())
        ?.collectAsStateWithLifecycle() ?: remember { mutableStateOf(emptyList())}

    LaunchedEffect(selectedItems) {
        selectedItems?.let{ ids ->
            if(ids.isNotEmpty()) {
                actions.setMaterials(ids)
            }
        }
        currentBackStackEntry?.savedStateHandle?.remove<List<String>>("selectedIds")
    }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            TopAppBar(
                navigationIcon = {BackButton {navController.navigateUp()}},
                id = stringResource(R.string.bubble),
                trailingIcon = {
                    IconButton(
                        onClick = {
                            actions.saveBubble()
                            navController.navigate(NavigationRoute.SingleBubbleSummary(state.bubbleId))
                            {
                                popUpTo(NavigationRoute.AllBubblesSummary) { inclusive = false }
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
                    value = state.bubbleNumber,
                    onValueChange = actions::setBubbleNumber
                )
            }
            item{
                DatePickerFieldToModal(
                    title = stringResource(R.string.date_issue),
                    value = state.bubbleDate.format(DateTimeFormatter.ofPattern("dd/MM/yyyy")),
                    onValueChange = {actions.setBubbleDate(LocalDate.parse(it, DateTimeFormatter.ofPattern("dd/MM/yyyy")))},
                )
            }
            item{Spacer(Modifier.size(8.dp))}
            item{
                SplitButtonMenu(
                    content = state.selectedSeller?.name ?: "New",
                    items = state.sellers.map{ item -> MenuItem(name = item.name, actions::setSeller)},
                    heightMenu = (state.sellers.size * 55).dp
                )
            }
            if(state.selectedSeller?.name.equals("New")){
                item{
                    CustomOutlineTextField(
                        label = stringResource(R.string.name),
                        onValueChange = actions::setNewSeller,
                        value = state.newSeller?.name
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
                    onClick = {navController.navigate(NavigationRoute.Select(selectSearchText, SelectKey.AllMaterials))}
                )
            }

            if(state.materialsSelected.isNotEmpty()){
                item{CustomDivider()}
            }else {
                item{Spacer(Modifier.size(8.dp))}
            }

            itemsIndexed(state.materialsSelected){index, item ->
                TitleLabel(item.material.category)

                CustomOutlineTextField(
                    label = stringResource(R.string.quantity),
                    value = item.quantity.toString(),
                    onValueChange = { value ->
                        actions.setQuantityMaterial(item.material, value)
                    },
                )
                CustomOutlineTextField(
                    label = stringResource(R.string.unit_price),
                    value = item.unitPrice.let {
                        DecimalFormat("#.00").format(it)
                    },
                    onValueChange = { value ->
                        actions.setUnitPriceMaterial(item.material, value)
                    },
                )
                CustomOutlineTextField(
                    label = stringResource(R.string.vat_number),
                    value = item.vatNumber.toString(),
                    onValueChange = { value ->
                        actions.setVatNumberMaterial(item.material, value)
                    },
                )
                if(index < state.materialsSelected.size - 1) {
                    CustomDivider()
                }
            }
            item{Spacer(Modifier.size(8.dp))}
            if (previousBackStackEntry?.destination?.hasRoute<NavigationRoute.SingleBubbleSummary>() == true) {
                item {
                    DeleteButton {
                        actions.delete()
                        navController.navigate(NavigationRoute.AllBubblesSummary){
                            popUpTo(NavigationRoute.AllBubblesSummary){inclusive = true}
                            launchSingleTop = true
                        }
                    }
                }
                item{Spacer(Modifier.size(8.dp))}
            }
        }
    }
}