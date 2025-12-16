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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import com.example.myapplication.R
import com.example.myapplication.ui.component.Avatar
import com.example.myapplication.ui.component.BackButton
import com.example.myapplication.ui.component.BoxDescription
import com.example.myapplication.ui.component.DoubleKeyValueLabel
import com.example.myapplication.ui.component.KeyValueLabel
import com.example.myapplication.ui.component.GenericCard
import com.example.myapplication.ui.component.Images
import com.example.myapplication.ui.component.TitleLabel
import com.example.myapplication.ui.component.TopAppBar
import com.example.myapplication.ui.component.checkColorAvatar
import com.example.myapplication.ui.theme.MyApplicationTheme

class SingleJobSummaryActivity: ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MyApplicationTheme {
                var ctx = LocalContext.current

                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    topBar = { TopAppBar(
                            id = "Intervento",
                            navigationIcon = { BackButton {} },
                            trailingIcons = {
                                IconButton(
                                    onClick = {},
                                    colors = IconButtonDefaults.iconButtonColors(contentColor = MaterialTheme.colorScheme.onPrimary)
                                ) {
                                    Icon(painterResource(R.drawable.edit_square_24dp), contentDescription = "Edit")
                                }
                            }
                        )
                    }
                ){ contentPadding ->
                    LazyColumn(
                        modifier = Modifier
                            .padding(
                                top = contentPadding.calculateTopPadding() + 8.dp,
                                start = contentPadding.calculateStartPadding(LayoutDirection.Ltr) + 8.dp,
                                end = contentPadding.calculateEndPadding(LayoutDirection.Ltr) + 8.dp
                            )
                    ) {
                        item{ GenericCard(
                            text = customers[0],
                            leadingContent = { Avatar(char = customers[0][0]) }
                        )}
                        item{Spacer(Modifier.size(8.dp))}
                        item{KeyValueLabel(
                            title = "Indirizzo",
                            description = "Pasquale ordlandi 21",
                            weightTitle = 1.0f,
                            weighDescription = 2.0f
                            )
                        }
                        item{Spacer(Modifier.size(8.dp))}
                        item{KeyValueLabel(
                            title = "Comune",
                            description = "Medicina",
                            weightTitle = 1.0f,
                            weighDescription = 2.0f
                        )
                        }
                        item{Spacer(Modifier.size(8.dp))}
                        item{DoubleKeyValueLabel(
                            firstTitle = "CAP",
                            firstDescription = "40059",
                            secondTitle = "Provincia",
                            secondDescription = "BO"
                        )}
                        item{Spacer(Modifier.size(8.dp))}
                        item{DoubleKeyValueLabel(
                            firstTitle = "Prezzo",
                            firstDescription = "1.000€",
                            secondTitle = "Data",
                            secondDescription = "01/01/2025"
                        )}
                        item{Spacer(Modifier.size(8.dp))}
                        item{DoubleKeyValueLabel(
                            firstTitle = "Ora Inizio",
                            firstDescription = "09:00",
                            secondTitle = "Ora Fine",
                            secondDescription = "12:00"
                        )}
                        item{Spacer(Modifier.size(8.dp))}
                        item{KeyValueLabel(
                            title = "Tipo",
                            description = "Elettrico",
                            weightTitle = 1.0f,
                            weighDescription = 2.0f
                        )}
                        item{Spacer(Modifier.size(8.dp))}
                        item{TitleLabel(title = "Descrizione")}
                        item{Spacer(Modifier.size(8.dp))}
                        item{BoxDescription(textDescription)}
                        item{Spacer(Modifier.size(8.dp))}
                        item{Images()}
                        item{Spacer(Modifier.size(8.dp))}
                        item{TitleLabel("Materiali")}
                        item{Spacer(Modifier.size(8.dp))}
                        items(prodotti.subList(0,5)){item ->
                            GenericCard(
                                leadingContent = {
                                    Avatar(
                                        char = item.tipo[0],
                                        textColor = checkColorAvatar(type = item.tipo, primary = true),
                                        backgroundColor = checkColorAvatar(type = item.tipo, onPrimary = true)
                                    )
                                },
                                text = item.nome,
                                textDescription = item.modello,
                                trailingContent = {
                                    Text(
                                        text = item.quantita.toString() + " " + item.unitaMisura
                                    )
                                },
                                type = item.tipo
                            )
                            Spacer(Modifier.size(8.dp))
                        }
                        item{Spacer(Modifier.size(60.dp))}
                    }
                }
            }
        }
    }
}