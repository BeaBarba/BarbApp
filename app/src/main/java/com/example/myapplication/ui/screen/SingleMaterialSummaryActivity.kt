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
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import com.example.myapplication.R
import com.example.myapplication.ui.component.BackButton
import com.example.myapplication.ui.component.CustomDivider
import com.example.myapplication.ui.component.Images
import com.example.myapplication.ui.component.KeyValueLabel
import com.example.myapplication.ui.component.TitleLabel
import com.example.myapplication.ui.component.TopAppBar
import com.example.myapplication.ui.theme.MyApplicationTheme

class SingleMaterialSummaryActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MyApplicationTheme {
                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    topBar = {
                        TopAppBar(id = "Materiale",
                            navigationIcon = { BackButton {} },
                            trailingIcon = {
                                IconButton(
                                    onClick = {},
                                    colors = IconButtonDefaults.iconButtonColors(contentColor = MaterialTheme.colorScheme.onPrimary)
                                ){
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
                        var somma = 0
                        provenienze.forEach{p->
                            val numero = p.quantita.filter { it.isDigit() }
                            if(numero.isNotEmpty()){
                                somma += numero.toInt()
                            }
                        }
                        item{KeyValueLabel(title = "Nome", weightTitle = 1.0f, description = "Interruttore", weighDescription = 2.0f)}
                        item{ Spacer(Modifier.size(8.dp)) }
                        item{KeyValueLabel(title = "Marca", weightTitle = 1.0f, description = "Vimar", weighDescription = 2.0f)}
                        item{Spacer(Modifier.size(8.dp))}
                        item{KeyValueLabel(title = "Modello", weightTitle = 1.0f, description = "Plana", weighDescription = 2.0f)}
                        item{Spacer(Modifier.size(8.dp))}
                        item{KeyValueLabel(title = "Quantità tot.", weightTitle = 1.0f, description = somma.toString(), weighDescription = 2.0f)}
                        item{Spacer(Modifier.size(8.dp))}
                        item{CustomDivider()}
                        item{TitleLabel("Provenienza")}
                        item{Spacer(Modifier.size(8.dp))}
                        items(provenienze){item ->
                            KeyValueLabel(title = "Venditore", weightTitle = 1.0f, description = item.fornitore, weighDescription = 2.0f)
                            Spacer(Modifier.size(8.dp))
                            KeyValueLabel(title = "Quantità", weightTitle = 1.0f, description = item.quantita, weighDescription = 2.0f)
                            Spacer(Modifier.size(8.dp))
                            if(!item.numeroBolla.isNullOrEmpty()) {
                                KeyValueLabel(title = "Bolla n.", weightTitle = 1.0f, description = item.numeroBolla, weighDescription = 2.0f)
                                Spacer(Modifier.size(8.dp))
                            }
                            if(!item.fattura.isNullOrEmpty()){
                                KeyValueLabel(title = "Fattura", weightTitle = 1.0f, description = item.fattura, weighDescription = 2.0f)
                                Spacer(Modifier.size(8.dp))
                            }
                            CustomDivider()
                        }
                        item{Images()}
                    }
                }
            }
        }
    }
}