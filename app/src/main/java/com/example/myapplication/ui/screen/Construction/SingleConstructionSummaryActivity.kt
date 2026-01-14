package com.example.myapplication.ui.screen.Construction

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.calculateEndPadding
import androidx.compose.foundation.layout.calculateStartPadding
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.LocationOn
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
import com.example.myapplication.debug.interventi
import com.example.myapplication.debug.listaCantieri
import com.example.myapplication.debug.listaClienti
import com.example.myapplication.ui.NavigationRoute
import com.example.myapplication.ui.component.Avatar
import com.example.myapplication.ui.component.BackButton
import com.example.myapplication.ui.component.GenericCard
import com.example.myapplication.ui.component.KeyValueLabel
import com.example.myapplication.ui.component.TitleLabel
import com.example.myapplication.ui.component.TopAppBar

@Composable
fun SingleConstructionSummaryActivity (
    navController : NavHostController
) {
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            TopAppBar(
                navigationIcon = { BackButton { navController.navigateUp() } },
                id = stringResource(R.string.construction_site),
                trailingIcon = {
                    IconButton(
                        onClick = {navController.navigate(NavigationRoute.ConstructionAdd)},
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
            if (listaClienti.isNotEmpty()) {
                item {
                    GenericCard(
                        leadingContent = {
                            Avatar(
                                char = listaClienti.get(0).nome.get(0)
                            )
                        },
                        text = listaClienti.get(0).cognome + " " + listaClienti.get(0).nome,
                        onClick = {navController.navigate(NavigationRoute.SingleCustomerSummary)}
                    )
                }
            }
            item{Spacer(Modifier.size(8.dp))}
            item {
                GenericCard(
                    leadingContent = {
                        Icon(
                            imageVector = Icons.Filled.LocationOn,
                            contentDescription = stringResource(R.string.address)
                        )
                    },
                    text = stringResource(R.string.address),
                    onClick = {navController.navigate(NavigationRoute.SingleAddressSummary)}
                )
            }
            item{Spacer(Modifier.size(8.dp))}
            item{
                KeyValueLabel(
                    title = stringResource(R.string.date_start),
                    description = listaCantieri.get(0).dataInizio
                )
            }
            item{Spacer(Modifier.size(8.dp))}
            item{
                KeyValueLabel(
                    title = stringResource(R.string.date_end),
                    description = listaCantieri.get(0).dataFine.toString()
                )
            }
            item{Spacer(Modifier.size(8.dp))}
            item{TitleLabel(stringResource(R.string.reference))}
            item{Spacer(Modifier.size(8.dp))}
            item{
                KeyValueLabel(
                    title = stringResource(R.string.name),
                    description = listaClienti.get(1).nome,
                )
            }
            item{Spacer(Modifier.size(8.dp))}
            item{
                KeyValueLabel(
                    title = stringResource(R.string.last_name),
                    description = listaClienti.get(1).cognome.toString(),
                )
            }
            item{Spacer(Modifier.size(8.dp))}
            item{
                KeyValueLabel(
                    title = stringResource(R.string.phone),
                    description = listaClienti.get(1).telefono,
                )
            }
            item{Spacer(Modifier.size(8.dp))}
            item{TitleLabel(stringResource(R.string.interventions))}
            item{Spacer(Modifier.size(8.dp))}
            items(interventi){item ->
                GenericCard(
                    type = item.tipo,
                    leadingContent = {
                        Avatar(char = item.tipo.get(0), type = item.tipo)
                    },
                    text = item.data,
                    textDescription = item.oraI + " - " + item.oraF,
                    onClick = {navController.navigate(NavigationRoute.SingleJobSummary)}
                )
                Spacer(Modifier.size(8.dp))
            }
        }
    }
}
