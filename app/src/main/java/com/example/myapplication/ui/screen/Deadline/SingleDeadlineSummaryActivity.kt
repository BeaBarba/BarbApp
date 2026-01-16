package com.example.myapplication.ui.screen.Deadline

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.calculateEndPadding
import androidx.compose.foundation.layout.calculateStartPadding
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.myapplication.R
import com.example.myapplication.data.modules.DeadlineType
import com.example.myapplication.data.modules.FrequencyType
import com.example.myapplication.debug.categorie_s_menu
import com.example.myapplication.debug.fatture
import com.example.myapplication.debug.scadenze
import com.example.myapplication.ui.component.BackButton
import com.example.myapplication.ui.component.DoubleKeyValueLabel
import com.example.myapplication.ui.component.GenericCard
import com.example.myapplication.ui.component.KeyValueLabel
import com.example.myapplication.ui.NavigationRoute
import com.example.myapplication.ui.component.TopAppBar

@Composable
fun SingleDeadlineSummaryActivity (
    navController : NavHostController
) {
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            TopAppBar(id = stringResource(R.string.deadline),
                navigationIcon = {BackButton{navController.navigateUp()}},
                trailingIcon = {
                    IconButton(
                        onClick = {navController.navigate(NavigationRoute.DeadlineAdd)},
                        colors = IconButtonDefaults.iconButtonColors(
                            contentColor = MaterialTheme.colorScheme.onPrimary
                        )
                    ) {
                        Icon(
                            painterResource(R.drawable.edit_square_24dp),
                            contentDescription = stringResource(R.string.edit)
                        )
                    }
                })
        }
    ) { contentPadding ->
        var deadlineType = DeadlineType.Periodica
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
                    description = scadenze.get(2).fornitore,
                    weightTitle = 1.0f,
                    weighDescription = 2.0f
                )
            }
            item{Spacer(Modifier.size(8.dp))}
            item{
                KeyValueLabel(
                    title = stringResource(R.string.type),
                    description = deadlineType.toString(),
                    weightTitle = 1.0f,
                    weighDescription = 2.0f
                )
            }
            item{Spacer(Modifier.size(8.dp))}
            if(deadlineType.equals(DeadlineType.Periodica)){
                item{
                    KeyValueLabel(
                        title = stringResource(R.string.frequency),
                        description = FrequencyType.Mese.toString(),
                        weightTitle = 1.0f,
                        weighDescription = 2.0f
                    )
                }
                item{Spacer(Modifier.size(8.dp))}
            }
            item{
                KeyValueLabel(
                    title = stringResource(R.string.category),
                    description = categorie_s_menu.get(0).name,
                    weightTitle = 1.0f,
                    weighDescription = 2.0f
                )
            }
            item{Spacer(Modifier.size(8.dp))}
            item{
                DoubleKeyValueLabel(
                    firstTitle = stringResource(R.string.price),
                    firstDescription = scadenze.get(2).prezzo.toString() + " €",
                    secondTitle = stringResource(R.string.date),
                    secondDescription = scadenze.get(2).data
                )
            }
            item{Spacer(Modifier.size(8.dp))}
            item{
                GenericCard(
                    leadingContent = {
                        Icon(
                            painter = painterResource(R.drawable.description_24dp),
                            contentDescription = stringResource(R.string.invoice_purchase),
                            modifier = Modifier.size(35.dp)
                        )
                    },
                    text = fatture.get(0).fattura.toString(),
                    onClick = {navController.navigate(NavigationRoute.SinglePurchaseInvoiceSummary)}
                )
            }
        }
    }
}