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
import androidx.compose.material.icons.filled.FilterAlt
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import com.example.myapplication.ui.component.AddButton
import com.example.myapplication.ui.component.Avatar
import com.example.myapplication.ui.component.BackButton
import com.example.myapplication.ui.component.CustomDivider
import com.example.myapplication.ui.component.DropDownMenuJobs
import com.example.myapplication.ui.component.GenericCard
import com.example.myapplication.ui.component.SearchAppBar
import com.example.myapplication.ui.component.TitleLabel
import com.example.myapplication.ui.component.TopAppBar
import com.example.myapplication.ui.component.checkColorAvatar
import com.example.myapplication.ui.theme.MyApplicationTheme

class AllJobsSummaryActivity : ComponentActivity() {
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
                            id = "Interventi",
                            navigationIcon = { BackButton() {}},
                            trailingIcon = {DropDownMenuJobs()}
                        )
                    },
                    floatingActionButton = { AddButton {} }
                ) {
                    contentPadding ->
                    LazyColumn (
                        modifier = Modifier
                            .padding(
                                top = contentPadding.calculateTopPadding() + 8.dp,
                                start = contentPadding.calculateStartPadding(LayoutDirection.Ltr) + 8.dp,
                                end = contentPadding.calculateEndPadding(LayoutDirection.Ltr) + 8.dp,
                                bottom = contentPadding.calculateBottomPadding()
                            )
                            .fillMaxSize()
                    ) {
                        item { SearchAppBar("Intervento", contentPadding) }
                        items(interventi){item ->
                            GenericCard(
                                item.indirizzo,
                                textDescription = item.cognomeNome,
                                leadingContent = { Avatar(item.tipo[0],
                                    textColor = checkColorAvatar(item.tipo, primary = true),
                                    backgroundColor = checkColorAvatar(item.tipo, onPrimary = true))
                                },
                                type = item.tipo
                            )
                            Spacer(Modifier.size(8.dp))
                        }
                        item { CustomDivider() }
                        item { TitleLabel("Effettuati") }
                        item { Spacer(Modifier.size(8.dp)) }
                        items(interventi){item ->
                            GenericCard(
                                item.indirizzo,
                                textDescription = item.cognomeNome,
                                leadingContent = { Avatar(item.tipo[0], textColor = checkColorAvatar(item.tipo, primary = true), backgroundColor = checkColorAvatar(item.tipo, onPrimary = true))},
                                type = item.tipo
                            )
                            Spacer(Modifier.size(8.dp))
                        }
                        item{Spacer(Modifier.size(90.dp)) }
                    }
                }
            }
        }
    }
}