package com.example.myapplication.ui.screen.Material.singleSummary

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.calculateEndPadding
import androidx.compose.foundation.layout.calculateStartPadding
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.myapplication.R
import com.example.myapplication.data.modules.JobType
import com.example.myapplication.data.modules.MachineType
import com.example.myapplication.debug.prodotti
import com.example.myapplication.debug.provenienze
import com.example.myapplication.ui.component.BackButton
import com.example.myapplication.ui.component.CustomDivider
import com.example.myapplication.ui.component.Images
import com.example.myapplication.ui.component.KeyValueLabel
import com.example.myapplication.ui.NavigationRoute
import com.example.myapplication.ui.component.TitleLabel
import com.example.myapplication.ui.component.TopAppBar

@Composable
fun SingleMaterialSummaryActivity(
    materialId : Int,
    state : SingleMaterialSummaryState,
    actions: SingleMaterialSummaryActions,
    navController: NavHostController
){
    LaunchedEffect(materialId) {
        actions.populateFromId(materialId)
    }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            TopAppBar(
                id = stringResource(R.string.material),
                navigationIcon = {BackButton{navController.navigateUp()}},
                trailingIcon = {
                    IconButton(
                        onClick = {navController.navigate(NavigationRoute.MaterialAdd /*TODO*/)},
                        colors = IconButtonDefaults.iconButtonColors(
                            contentColor = MaterialTheme.colorScheme.onPrimary
                        )
                    ){
                        Icon(
                            painter = painterResource(R.drawable.edit_square_24dp),
                            contentDescription = stringResource(R.string.edit)
                        )
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
                KeyValueLabel(
                    title = stringResource(R.string.type),
                    description = state.materialData?.material?.material?.type.toString()
                )
            }
            item{Spacer(Modifier.size(8.dp))}
            item{
                KeyValueLabel(
                    title = stringResource(R.string.category),
                    description = state.materialData?.material?.material?.category ?: ""
                )
            }
            item{Spacer(Modifier.size(8.dp))}
            item{
                KeyValueLabel(
                    title = stringResource(R.string.brand),
                    description = state.materialData?.material?.material?.brand ?: ""
                )
            }
            item{Spacer(Modifier.size(8.dp))}
            item{
                KeyValueLabel(
                    title = stringResource(R.string.model),
                    description = state.materialData?.material?.material?.model ?: ""
                )
            }
            item{Spacer(Modifier.size(8.dp))}
            item{
                KeyValueLabel(
                    title = stringResource(R.string.total_q),
                    description = state.materialData?.material?.material?.availableQuantity.toString()
                )
            }
            if(state.materialData?.material?.isAirConditioner == true){
                item{CustomDivider()}
                item{TitleLabel(stringResource(R.string.specifications))}
                item{Spacer(Modifier.size(8.dp))}
                state.materialData.material.airConditioner.forEach{ airConditioner ->
                    item{
                        KeyValueLabel(
                            title = stringResource(R.string.serial_number),
                            description = airConditioner.serialNumber
                        )
                    }
                    item{Spacer(Modifier.size(8.dp))}
                    item{
                        KeyValueLabel(
                            title = stringResource(R.string.btu),
                            description = airConditioner.btu.toString()
                        )
                    }
                    item{Spacer(Modifier.size(8.dp))}
                    item{
                        KeyValueLabel(
                            title = stringResource(R.string.machine_type),
                            description = airConditioner.machineType.toString()
                        )
                    }
                    item{Spacer(Modifier.size(8.dp))}
                    if(airConditioner.machineType == MachineType.Esterna) {
                        item {
                            KeyValueLabel(
                                title = stringResource(R.string.split_number),
                                description = airConditioner.splitNumber.toString()
                            )
                        }
                        item { Spacer(Modifier.size(8.dp)) }
                        item {
                            KeyValueLabel(
                                title = stringResource(R.string.gas_quantity),
                                description = airConditioner.gasQty.toString()
                            )
                        }
                        item { Spacer(Modifier.size(8.dp)) }
                        item {
                            KeyValueLabel(
                                title = stringResource(R.string.gas_type),
                                description = airConditioner.gasType
                            )
                        }
                        item { Spacer(Modifier.size(8.dp)) }
                        if(airConditioner.yearInstallation != null) {
                            item {
                                KeyValueLabel(
                                    title = stringResource(R.string.year_installation),
                                    description = airConditioner.yearInstallation.toString(),
                                    weightTitle = 2.0f
                                )
                            }
                        }
                    }
                }
            }
            item{CustomDivider()}
            item{TitleLabel(stringResource(R.string.origin))}
            item{Spacer(Modifier.size(8.dp))}
            if(state.materialData?.deliveriesWithBubbles?.isNotEmpty() == true){
                items(state.materialData.deliveriesWithBubbles) { bubble ->
                    KeyValueLabel(
                        title = stringResource(R.string.seller),
                        description = bubble.bubble.seller.name
                    )
                    Spacer(Modifier.size(8.dp))
                    KeyValueLabel(
                        title = stringResource(R.string.quantity),
                        description = bubble.delivery.quantity.toString()
                    )
                    Spacer(Modifier.size(8.dp))

                    KeyValueLabel(
                        title = stringResource(R.string.bubble),
                        description = bubble.bubble.bubble.number
                    )
                    CustomDivider()
                }
            }
            if(state.materialData?.purchaseWithPurchaseInvoice?.isNotEmpty() == true){
                items(state.materialData.purchaseWithPurchaseInvoice) { purchaseInvoice ->
                    KeyValueLabel(
                        title = stringResource(R.string.seller),
                        description = purchaseInvoice.purchaseInvoice.seller.name
                    )
                    Spacer(Modifier.size(8.dp))
                    KeyValueLabel(
                        title = stringResource(R.string.quantity),
                        description = purchaseInvoice.purchase.quantity.toString()
                    )
                    Spacer(Modifier.size(8.dp))

                    KeyValueLabel(
                        title = stringResource(R.string.invoice),
                        description = purchaseInvoice.purchaseInvoice.purchaseInvoice.number
                    )
                    CustomDivider()
                }
            }
            item{Images()}
            item{Spacer(Modifier.size(8.dp))}
        }
    }
}