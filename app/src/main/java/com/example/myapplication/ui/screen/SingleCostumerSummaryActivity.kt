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
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Mode
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import com.example.myapplication.R
import com.example.myapplication.ui.component.AddButton
import com.example.myapplication.ui.component.Avatar
import com.example.myapplication.ui.component.BackButton
import com.example.myapplication.ui.component.CustomDivider
import com.example.myapplication.ui.component.KeyValueLabel
import com.example.myapplication.ui.component.GenericCard
import com.example.myapplication.ui.component.TitleLabel
import com.example.myapplication.ui.component.TopAppBar
import com.example.myapplication.ui.component.checkColor
import com.example.myapplication.ui.component.checkColorAvatar
import com.example.myapplication.ui.theme.MyApplicationTheme

class SingleCostumerSummaryActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MyApplicationTheme {
                var ctx = LocalContext.current

                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    topBar = {
                        TopAppBar(
                            id = "Clienti",
                            navigationIcon = { BackButton() {} },
                            trailingIcons = { IconButton(onClick = {}) {
                                Icon(painterResource(R.drawable.edit_square_24dp), contentDescription = "Edit", tint = MaterialTheme.colorScheme.onPrimary)
                            } }
                        )
                    },
                    floatingActionButton = { AddButton() {} }
                ) { contentPadding ->
                    LazyColumn (
                        modifier = Modifier
                            .padding(
                                top = contentPadding.calculateTopPadding() + 8.dp,
                                start = contentPadding.calculateStartPadding(LayoutDirection.Ltr) + 8.dp,
                                end = contentPadding.calculateEndPadding(LayoutDirection.Ltr) + 8.dp
                            )
                            .fillMaxSize()
                    ){
                        item{ TitleLabel("Dati Anagrafici") }
                        item{ Spacer(Modifier.size(8.dp)) }
                        item{ KeyValueLabel("CF", "RSSMRA80A01A944I", 1.0f, 2.0f) }
                        item{ Spacer(Modifier.size(8.dp)) }
                        item{ KeyValueLabel("Nome", "Mario", 1.0f, 2.0f) }
                        item{ Spacer(Modifier.size(8.dp)) }
                        item{ KeyValueLabel("Cognome", "Rossi", 1.0f, 2.0f) }
                        item{ Spacer(Modifier.size(8.dp)) }
                        item{ KeyValueLabel("Indirizzo", "Piazza dei martiri", 1.0f, 2.0f) }
                        item{ Spacer(Modifier.size(8.dp)) }
                        item{ KeyValueLabel("Comune", "Bologna", 1.0f, 2.0f) }
                        item{ Spacer(Modifier.size(8.dp)) }
                        item{ KeyValueLabel("CAP", "40133", 1.0f, 2.0f) }
                        item{ CustomDivider() }
                        item{ TitleLabel("Interventi")  }
                        item{ Spacer(Modifier.size(8.dp)) }
                        items(interventi){item ->
                            GenericCard(
                                text = item.indirizzo,
                                textDescription = item.data,
                                leadingContent = {
                                    Avatar(
                                        char = item.tipo.get(0),
                                        textColor = checkColorAvatar(item.tipo, primary = true),
                                        backgroundColor = checkColorAvatar(item.tipo, onPrimary = true)
                                    )
                                },
                                trailingContent = {
                                    IconButton(
                                        onClick = {}
                                    ) {Icon(Icons.Filled.Mode, contentDescription = "Modify", tint = checkColor(item.tipo, onPrimaryContainer = true)) }
                                },
                                type = item.tipo
                            )
                            Spacer(Modifier.size(8.dp))
                        }
                        item{ Spacer(Modifier.size(120.dp))}
                    }
                }
            }
        }
    } 
}