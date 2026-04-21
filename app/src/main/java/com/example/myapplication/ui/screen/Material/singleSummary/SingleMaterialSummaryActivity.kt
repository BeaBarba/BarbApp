package com.example.myapplication.ui.screen.Material.singleSummary

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.calculateEndPadding
import androidx.compose.foundation.layout.calculateStartPadding
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Mode
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
import com.example.myapplication.ui.component.BackButton
import com.example.myapplication.ui.component.CustomDivider
import com.example.myapplication.ui.component.Images
import com.example.myapplication.ui.component.KeyValueLabel
import com.example.myapplication.ui.NavigationRoute
import com.example.myapplication.ui.component.AddButton
import com.example.myapplication.ui.component.GenericCard
import com.example.myapplication.ui.component.TitleLabel
import com.example.myapplication.ui.component.TopAppBar
import com.example.myapplication.ui.component.checkColor

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
                        onClick = { navController.navigate(NavigationRoute.MaterialAdd(state.materialData?.material
                            ?.material?.id, null))},
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
        },
        floatingActionButton = {
            state.materialData?.material?.let {
                if (state.materialData.material.isAirConditioner){
                    AddButton { navController.navigate(NavigationRoute.MaterialAdd(state.materialData.material
                        .material.id, null)) }
                }
            }
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
                    description = "${state.materialData?.material?.material?.availableQuantity} ${state.materialData?.material?.material?.unitMeasurement}"
                )
            }
            state.materialData?.material?.let{
                if(state.materialData.material.isAirConditioner){
                    item{CustomDivider()}
                    item{TitleLabel(stringResource(R.string.specifications))}
                    item{Spacer(Modifier.size(8.dp))}
                    state.materialData.material.airConditioner.forEach{ airConditioner ->
                        item{
                            GenericCard(
                                leadingContent = {
                                    Icon(
                                        painter = painterResource(R.drawable.mode_fan_24dp),
                                        contentDescription = stringResource(R.string.air_conditioning)
                                    )
                                },
                                text = "${airConditioner.serialNumber} - ${airConditioner.machineType}",
                                textDescription = "${airConditioner.btu}, ${airConditioner.gasType}: ${airConditioner
                                    .gasQty}, ${airConditioner.splitNumber}",
                                trailingContent = {
                                    IconButton(
                                        onClick = {navController.navigate(NavigationRoute.MaterialAdd(state
                                            .materialData.material.material.id,
                                            airConditioner.serialNumber))}
                                    ) {
                                        Icon(
                                            imageVector = Icons.Filled.Mode,
                                            contentDescription = stringResource(R.string.edit),
                                            tint = checkColor(JobType.NONE.name, onPrimaryContainer = true)
                                        )
                                    }
                                }
                            )
                            Spacer(Modifier.size(8.dp))
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
                        description = "${bubble.delivery.quantity}"
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
                        description = "${purchaseInvoice.purchase.quantity}"
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