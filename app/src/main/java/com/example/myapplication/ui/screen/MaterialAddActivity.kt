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
import com.example.myapplication.ui.component.DeleteButton
import com.example.myapplication.ui.component.Images
import com.example.myapplication.ui.component.SplitButtonMenu
import com.example.myapplication.ui.component.TopAppBar
import com.example.myapplication.ui.theme.MyApplicationTheme

class MaterialAddActivity  : ComponentActivity() {
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
                                start = contentPadding.calculateStartPadding(LayoutDirection.Ltr) + 8.dp,
                                end = contentPadding.calculateEndPadding(LayoutDirection.Ltr) + 8.dp,
                                bottom = contentPadding.calculateBottomPadding()
                            )
                            .fillMaxSize()
                    ) {
                        item{CustomOutlineTextField("Nome")}
                        item{Spacer(Modifier.size(8.dp))}
                        item{CustomOutlineTextField("Marca")}
                        item{Spacer(Modifier.size(8.dp))}
                        item{CustomOutlineTextField("Modello")}
                        item{Spacer(Modifier.size(8.dp))}
                        item{CustomOutlineTextField("Prezzo unitario")}
                        item{Spacer(Modifier.size(8.dp))}
                        item{CustomOutlineTextField("Quantità")}
                        item{Spacer(Modifier.size(8.dp))}
                        val v_size = if(venditori.size < 5){venditori.size}else{5}
                        item{SplitButtonMenu(content = "Venditori", venditori, heightMenu = (v_size * 55).dp)}
                        item{Spacer(Modifier.size(8.dp))}
                        val b_size = if(bolle.size < 5){bolle.size}else{5}
                        item{SplitButtonMenu(content = "Bolla", venditori, heightMenu = (b_size * 55).dp)}
                        item{Spacer(Modifier.size(8.dp))}
                        val f_size = if(fatture.size < 5){fatture.size}else{5}
                        item{SplitButtonMenu(content = "Fattura", fatture, heightMenu =  (f_size * 55).dp)}
                        item{Spacer(Modifier.size(8.dp))}
                        item{Images()}
                        item{Spacer(Modifier.size(8.dp))}
                        item{DeleteButton {  }}
                    }
                }
            }
        }
    }
}