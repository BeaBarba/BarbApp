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
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.ChevronRight
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import com.example.myapplication.ui.component.BackButton
import com.example.myapplication.ui.component.CustomOutlineTextField
import com.example.myapplication.ui.component.DatePickerFieldToModal
import com.example.myapplication.ui.component.DeleteButton
import com.example.myapplication.ui.component.GenericCard
import com.example.myapplication.ui.component.SplitButtonMenu
import com.example.myapplication.ui.component.TopAppBar
import com.example.myapplication.ui.theme.MyApplicationTheme

class DeadlineAddActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MyApplicationTheme {
                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    topBar = {
                        TopAppBar(
                            navigationIcon = { BackButton { } },
                            id = "Scadenza",
                            trailingIcon = {
                                IconButton(
                                    onClick = {},
                                    colors = IconButtonDefaults.iconButtonColors(contentColor = MaterialTheme.colorScheme.onPrimary)
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
                                top = contentPadding.calculateTopPadding(),
                                start = contentPadding.calculateStartPadding(LayoutDirection.Ltr) + 8.dp,
                                end = contentPadding.calculateEndPadding(LayoutDirection.Ltr) + 8.dp,
                                bottom = contentPadding.calculateBottomPadding()
                            )
                    ) {
                        item{CustomOutlineTextField("Nome")}
                        item{Spacer(Modifier.size(8.dp))}
                        item{DatePickerFieldToModal("Data Emissione")}
                        item{Spacer(Modifier.size(8.dp))}
                        item{CustomOutlineTextField("Numero")}
                        item{Spacer(Modifier.size(8.dp))}
                        item{SplitButtonMenu("Categoria", items = categorie, heightMenu = (categorie.size * 55).dp)}
                        item{Spacer(Modifier.size(8.dp))}
                        item{SplitButtonMenu("Venditore", items = venditori, heightMenu = (venditori.size * 55).dp)}
                        item{Spacer(Modifier.size(8.dp))}
                        item{
                            GenericCard(
                                text = "Bolle",
                                trailingContent = {
                                    Icon(Icons.Filled.ChevronRight, contentDescription = "Add Bubbles", modifier = Modifier.size(35.dp))
                                },
                                onClick = {}
                            )
                        }
                        item{Spacer(Modifier.size(8.dp))}
                        item{
                            GenericCard(
                                text = "Materiale",
                                trailingContent = {
                                    Icon(Icons.Filled.ChevronRight, contentDescription = "Add Materials", modifier = Modifier.size(35.dp))
                                },
                                onClick = {}
                            )
                        }
                        item{Spacer(Modifier.size(8.dp))}
                        item{DeleteButton{}}
                    }
                }
            }
        }
    }
}