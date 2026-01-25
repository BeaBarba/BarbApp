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
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import androidx.navigation.NavDestination.Companion.hasRoute
import androidx.navigation.NavHostController
import com.example.myapplication.R
import com.example.myapplication.debug.bolle
import com.example.myapplication.debug.selectedMaterialResult
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
    if (!state.started) {
        actions.populateSellers()
        bubbleId?.let(actions::populateFromEdit)
    }

    if (selectedMaterialResult.isNotEmpty()) {
        actions.setMaterials(selectedMaterialResult)
        selectedMaterialResult = listOf()
    }
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
                            actions.saveBubble()
                            navController.navigate(NavigationRoute.SingleBubbleSummary(bubbleId = 0))
                            {
                                popUpTo(NavigationRoute.BubbleAdd(null)){inclusive = true}
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
                    onValueChange = actions::setBubbleNumber,
                    value = state.bubbleNumber
                )
            }
            item{DatePickerFieldToModal(stringResource(R.string.date_issue),
                onValueChange = {actions.setBubbleDate( LocalDate.parse(it, DateTimeFormatter.ofPattern("dd/MM/yyyy"))) },
                value = state.bubbleDate.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"))
            )}
            item{Spacer(Modifier.size(8.dp))}
            item{
                SplitButtonMenu(
                    content = state.selectedSeller?.sellerName ?: "New",
                    items = state.seller.map{item ->MenuItem(name = item.sellerName, actions::setSeller)},
                    heightMenu = (state.seller.size * 55).dp
                )
            }
            if(state.selectedSeller?.sellerName.equals("New")){
                item{
                    CustomOutlineTextField(
                        label = stringResource(R.string.name),
                        onValueChange = actions::setNewSeller,
                        value = state.newSeller?.sellerName
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
            itemsIndexed(state.materials){index, item ->
                TitleLabel(item.nome)
                CustomOutlineTextField(
                    label = stringResource(R.string.quantity),
                    onValueChange = { value -> actions.setQuantityMaterial(item, value)},
                    value = state.materials.firstOrNull { it.nome == item.nome }?.quantita.toString()
                )
                CustomOutlineTextField(
                    label = stringResource(R.string.unit_price),
                    onValueChange = { value -> actions.setUnitPriceMaterial(item, value)},
                    value = state.materials.firstOrNull { it.nome == item.nome }?.prezzo.let {
                        DecimalFormat("#.00").format(it)
                    }
                )
                CustomOutlineTextField(
                    label = stringResource(R.string.vat),
                    onValueChange = { value -> actions.setVatMaterial(item, value)},
                    value = state.materials.firstOrNull { it.nome == item.nome }?.iva.toString()
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