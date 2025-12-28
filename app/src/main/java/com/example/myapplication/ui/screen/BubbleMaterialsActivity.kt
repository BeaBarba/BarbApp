package com.example.myapplication.ui.screen

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.calculateEndPadding
import androidx.compose.foundation.layout.calculateStartPadding
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SuggestionChip
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import com.example.myapplication.ui.component.AddButton
import com.example.myapplication.ui.component.BackButton
import com.example.myapplication.ui.component.DropDownMenuBubbles
import com.example.myapplication.ui.component.GenericCard
import com.example.myapplication.ui.component.MaterialTable
import com.example.myapplication.ui.component.SearchAppBar
import com.example.myapplication.ui.component.SuggestionTextField
import com.example.myapplication.ui.component.TopAppBar
import com.example.myapplication.ui.theme.MyApplicationTheme
import com.example.myapplication.ui.theme.TableColumn

class BubbleMaterialsActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MyApplicationTheme {
                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    topBar = {
                        TopAppBar(id = "Materiale - Bolla",
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
                    },
                    floatingActionButton = { AddButton {  } }
                ) { contentPadding ->
                    Column(
                        modifier = Modifier
                            .padding(
                                top = contentPadding.calculateTopPadding() + 8.dp,
                                start = contentPadding.calculateStartPadding(LayoutDirection.Ltr) + 8.dp,
                                end = contentPadding.calculateEndPadding(LayoutDirection.Ltr) + 8.dp,
                                bottom = contentPadding.calculateBottomPadding()
                            )
                            .fillMaxSize()
                    ) {
                        SearchAppBar("Materiale", contentPadding)
                        Spacer(Modifier.size(8.dp))
                        MaterialTable(
                            contentPadding = contentPadding,
                            listData = prodotti
                        )
                        //Spacer(Modifier.size(90.dp))
                    }
                }
            }
        }
    }
}