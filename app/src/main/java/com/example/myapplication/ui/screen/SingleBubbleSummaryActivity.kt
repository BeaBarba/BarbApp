package com.example.myapplication.ui.screen

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.calculateEndPadding
import androidx.compose.foundation.layout.calculateStartPadding
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ChevronRight
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.myapplication.R
import com.example.myapplication.ui.component.Avatar
import com.example.myapplication.ui.component.BackButton
import com.example.myapplication.ui.component.GenericCard
import com.example.myapplication.ui.component.KeyValueLabel
import com.example.myapplication.ui.NavigationRoute
import com.example.myapplication.ui.component.TitleLabel
import com.example.myapplication.ui.component.TopAppBar
import com.example.myapplication.ui.component.checkColorAvatar

@Composable
fun SingleBubbleSummaryActivity(
    navController : NavHostController
){
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            TopAppBar(
                navigationIcon = {BackButton{navController.navigateUp()}},
                id = stringResource(R.string.bubble),
                trailingIcon = {
                    IconButton(
                        onClick = {navController.navigate(NavigationRoute.BubbleAdd)},
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
            item{KeyValueLabel(
                title = stringResource(R.string.seller),
                description = provenienze.get(0).fornitore,
                weightTitle = 1.0f,
                weighDescription = 2.0f
            )}
            item{Spacer(Modifier.size(8.dp))}
            item{KeyValueLabel(
                title = stringResource(R.string.number),
                description = provenienze.get(0).numeroBolla.toString(),
                weightTitle = 1.0f,
                weighDescription = 2.0f
            )}
            item{Spacer(Modifier.size(8.dp))}
            item{KeyValueLabel(
                title = stringResource(R.string.date_issue),
                description = provenienze.get(0).data,
                weightTitle = 1.0f,
                weighDescription = 2.0f
            )}
            item{Spacer(Modifier.size(8.dp))}
            if(!provenienze.get(1).fattura.isNullOrEmpty()){
                item{
                    GenericCard(
                        text = stringResource(R.string.invoice) + ": " + provenienze.get(1).fattura.toString(),
                        trailingContent = {
                            Icon(Icons.Filled.ChevronRight, contentDescription = stringResource(R.string.show_items))
                        },
                        textSpace = 0.9f,
                        onClick = {navController.navigate(NavigationRoute.SingleDeadlineSummary)}
                    )
                }
                item{Spacer(Modifier.size(8.dp))}
            }
            item{
                TitleLabel(title = stringResource(R.string.material))
            }
            item{Spacer(Modifier.size(8.dp))}
            items(prodotti){item ->
                GenericCard(
                    type = item.tipo,
                    leadingContent = {
                        Avatar(
                            char = item.tipo.get(0),
                            textColor = checkColorAvatar(item.tipo, primary = true),
                            backgroundColor = checkColorAvatar(item.tipo, onPrimary = true)
                        )
                    },
                    text = item.nome,
                    textDescription = item.modello,
                    trailingContent = {
                        Text(
                           text = item.quantita.toString() + " " + item.unitaMisura
                        )
                    },
                    onClick = {navController.navigate(NavigationRoute.SingleMaterialSummary)}
                )
                Spacer(Modifier.size(8.dp))
            }
        }
    }
}