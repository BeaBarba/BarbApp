package com.example.myapplication.ui.screen.Bubble.singleSummary

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.calculateEndPadding
import androidx.compose.foundation.layout.calculateStartPadding
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ChevronRight
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
import com.example.myapplication.ui.component.BackButton
import com.example.myapplication.ui.component.GenericCard
import com.example.myapplication.ui.component.KeyValueLabel
import com.example.myapplication.ui.NavigationRoute
import com.example.myapplication.ui.component.CustomDivider
import com.example.myapplication.ui.component.TitleLabel
import com.example.myapplication.ui.component.TopAppBar
import com.example.myapplication.ui.component.materialTable
import com.example.myapplication.ui.theme.TableStyleDefaults
import java.time.format.DateTimeFormatter

@Composable
fun SingleBubbleSummaryActivity(
    bubbleId: Int,
    state: SingleBubbleSummaryState,
    actions: SingleBubbleSummaryActions,
    navController: NavHostController
){
    LaunchedEffect(bubbleId){
        actions.populateBubbleData(bubbleId)
    }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            TopAppBar(
                navigationIcon = {BackButton{navController.navigateUp()}},
                id = stringResource(R.string.bubble),
                trailingIcon = {
                    IconButton(
                        onClick = {
                            navController.navigate(NavigationRoute.BubbleAdd(bubbleId = bubbleId))
                        },
                        colors = IconButtonDefaults.iconButtonColors(
                            contentColor = MaterialTheme.colorScheme.onPrimary
                        )
                    ) {
                        Icon(
                            painter = painterResource(R.drawable.edit_square_24dp),
                            contentDescription = stringResource(R.string.edit)
                        )
                    }
                }
            )
        }
    ) { contentPadding ->
        val headerColumns = TableStyleDefaults.defaultColumns()
        val tableStyle = TableStyleDefaults.defaultStyle()
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
                    title = stringResource(R.string.seller),
                    description = state.bubble?.seller?.name ?: "",
                    weightTitle = 1.0f,
                    weighDescription = 2.0f
                )
            }
            item{Spacer(Modifier.size(8.dp))}
            item{
                KeyValueLabel(
                    title = stringResource(R.string.number),
                    description = state.bubble?.bubble?.number ?: "",
                    weightTitle = 1.0f,
                    weighDescription = 2.0f
                )
            }
            item{Spacer(Modifier.size(8.dp))}
            item{
                KeyValueLabel(
                    title = stringResource(R.string.date_issue),
                    description = state.bubble?.bubble?.date?.format(DateTimeFormatter.ofPattern("dd/MM/YYYY")) ?: "",
                    weightTitle = 1.0f,
                    weighDescription = 2.0f
                )
            }
            item{Spacer(Modifier.size(8.dp))}
            if(state.bubble?.purchaseInvoice != null){
                item{
                    GenericCard(
                        text = stringResource(R.string.invoice) + ": " + state.bubble.purchaseInvoice.number,
                        trailingContent = {
                            Icon(Icons.Filled.ChevronRight, contentDescription = stringResource(R.string.show_items))
                        },
                        textSpace = 0.9f,
                        onClick = {navController.navigate(NavigationRoute.SinglePurchaseInvoiceSummary/*(state.bubble.purchaseInvoice.id)*/)}
                    )
                }
            }
            item{CustomDivider()}
            item{TitleLabel(title = stringResource(R.string.material))}
            item{Spacer(Modifier.size(8.dp))}
            if(state.bubble?.deliveriesWithMaterials != null) {
                materialTable(state.bubble.deliveriesWithMaterials, headerColumns, tableStyle)
            }
            item{Spacer(Modifier.size(8.dp))}
        }
    }
}