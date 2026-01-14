package com.example.myapplication.ui.screen

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
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.myapplication.R
import com.example.myapplication.debug.prodotti
import com.example.myapplication.debug.scadenze
import com.example.myapplication.ui.component.Avatar
import com.example.myapplication.ui.component.BackButton
import com.example.myapplication.ui.component.BoxDescription
import com.example.myapplication.ui.component.DoubleKeyValueLabel
import com.example.myapplication.ui.component.GenericCard
import com.example.myapplication.ui.component.KeyValueLabel
import com.example.myapplication.ui.NavigationRoute
import com.example.myapplication.ui.component.TitleLabel
import com.example.myapplication.ui.component.TopAppBar
import com.example.myapplication.ui.component.checkColorAvatar

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
                    title = stringResource(R.string.name),
                    description = scadenze.get(2).fornitore,
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
            val text = "fsdffsfs"
            if(!text.isNullOrEmpty()) {
                item { TitleLabel(stringResource(R.string.description)) }
                item { Spacer(Modifier.size(8.dp)) }
                item { BoxDescription(text) }
                item { Spacer(Modifier.size(8.dp)) }
            }
            item{TitleLabel(stringResource(R.string.materials))}
            item{Spacer(Modifier.size(8.dp))}
            items(prodotti){ item ->
                GenericCard(
                    type = item.tipo,
                    leadingContent = {
                        Avatar(
                            char = item.tipo.get(0),
                            textColor = checkColorAvatar(item.tipo, primary = true),
                            backgroundColor = checkColorAvatar(item.tipo, onPrimary = true)
                        )
                    },
                    textSpace = 0.8f,
                    text = item.nome,
                    textDescription = item.modello,
                    trailingContent = {
                        Text(text = item.quantita.toString() + " " + item.unitaMisura)
                    },
                    onClick = {navController.navigate(NavigationRoute.SingleMaterialSummary)}
                )
                Spacer(Modifier.size(8.dp))
            }
        }
    }
}