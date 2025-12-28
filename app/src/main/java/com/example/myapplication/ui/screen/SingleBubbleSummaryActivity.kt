package com.example.myapplication.ui.screen

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
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
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import com.example.myapplication.R
import com.example.myapplication.ui.component.Avatar
import com.example.myapplication.ui.component.BackButton
import com.example.myapplication.ui.component.GenericCard
import com.example.myapplication.ui.component.KeyValueLabel
import com.example.myapplication.ui.component.TitleLabel
import com.example.myapplication.ui.component.TopAppBar
import com.example.myapplication.ui.component.checkColorAvatar
import com.example.myapplication.ui.theme.MyApplicationTheme

class SingleBubbleSummaryActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MyApplicationTheme {
                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    topBar = {
                        TopAppBar(id = "Bolla",
                            navigationIcon = { BackButton {} },
                            trailingIcon = {
                                IconButton(
                                    onClick = {},
                                    colors = IconButtonDefaults.iconButtonColors(contentColor = MaterialTheme.colorScheme.onPrimary)
                                ) {
                                    Icon(painter = painterResource(R.drawable.edit_square_24dp), contentDescription = "Edit")
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
                            title = "Venditore",
                            description = provenienze.get(0).fornitore,
                            weightTitle = 1.0f,
                            weighDescription = 2.0f
                        )}
                        item{Spacer(Modifier.size(8.dp))}
                        item{KeyValueLabel(
                            title = "Numero",
                            description = provenienze.get(0).numeroBolla.toString(),
                            weightTitle = 1.0f,
                            weighDescription = 2.0f
                        )}
                        item{Spacer(Modifier.size(8.dp))}
                        item{KeyValueLabel(
                            title = "Data emissione",
                            description = provenienze.get(0).data,
                            weightTitle = 1.0f,
                            weighDescription = 2.0f
                        )}
                        item{Spacer(Modifier.size(8.dp))}
                        if(!provenienze.get(1).fattura.isNullOrEmpty()){
                            item{KeyValueLabel(
                                title = "Fattura",
                                description = provenienze.get(1).fattura.toString(),
                                weightTitle = 1.0f,
                                weighDescription = 2.0f
                            )}
                            item{Spacer(Modifier.size(8.dp))}
                        }
                        item{
                            TitleLabel(title = "Materiale")
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
                                }
                            )
                            Spacer(Modifier.size(8.dp))
                        }
                    }
                }
            }
        }
    }
}