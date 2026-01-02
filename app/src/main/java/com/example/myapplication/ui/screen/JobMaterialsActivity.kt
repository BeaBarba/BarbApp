package com.example.myapplication.ui.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.calculateEndPadding
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Remove
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.myapplication.ui.component.Avatar
import com.example.myapplication.ui.component.BackButton
import com.example.myapplication.ui.component.GenericCard
import com.example.myapplication.ui.component.SearchAppBar
import com.example.myapplication.ui.component.TopAppBar
import com.example.myapplication.ui.component.checkColor
import com.example.myapplication.ui.component.checkColorAvatar

@Composable
fun JobMaterialsActivity(
    navController: NavHostController
){
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            TopAppBar(
                id = "Materiale - Intervento",
                navigationIcon = {BackButton{navController.navigateUp()}},
                trailingIcon = {
                    IconButton(
                        onClick = {navController.navigateUp()},
                        colors = IconButtonDefaults.iconButtonColors(
                            contentColor = MaterialTheme.colorScheme.onPrimary
                        )
                    ) {
                        Icon(Icons.Filled.Check, contentDescription = "Save")
                    }
                }
            )
        }
    ) { contentPadding ->
        LazyColumn(
            modifier = Modifier
                .padding(
                    top = contentPadding.calculateTopPadding() + 8.dp,
                    start = contentPadding.calculateRightPadding(LayoutDirection.Ltr) + 8.dp,
                    end = contentPadding.calculateEndPadding(LayoutDirection.Ltr) + 8.dp,
                    bottom = contentPadding.calculateBottomPadding()
                )
        ) {
            item{SearchAppBar("Materiale", contentPadding)}
            items(prodotti){item ->
                var quantita by remember{ mutableStateOf(item.quantita)}
                if(quantita > 0) {
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
                        textSpace = 0.6f,
                        trailingContent = {
                            Row(
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.Center
                            ) {
                                IconButton(
                                    onClick = { quantita = quantita.dec() },
                                    colors = IconButtonDefaults.iconButtonColors(
                                        contentColor = checkColor(
                                            item.tipo,
                                            onPrimaryContainer = true
                                        )
                                    )
                                ) {
                                    Icon(Icons.Filled.Remove, contentDescription = "Decrease")
                                }
                                Text(
                                    text = quantita.toString() + " " + item.unitaMisura,
                                    color = checkColor(item.tipo, onPrimaryContainer = true)
                                )
                                IconButton(
                                    onClick = { quantita = quantita.inc() },
                                    colors = IconButtonDefaults.iconButtonColors(
                                        contentColor = checkColor(
                                            item.tipo,
                                            onPrimaryContainer = true
                                        )
                                    )
                                ) {
                                    Icon(Icons.Filled.Add, contentDescription = "Increase")
                                }
                            }
                        }
                    )
                    Spacer(Modifier.size(8.dp))
                }
            }
        }
    }
}