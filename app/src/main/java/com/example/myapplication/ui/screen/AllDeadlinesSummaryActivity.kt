package com.example.myapplication.ui.screen

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.calculateEndPadding
import androidx.compose.foundation.layout.calculateStartPadding
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SplitButtonLayout
import androidx.compose.material3.Text
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import com.example.myapplication.ui.component.AddButton
import com.example.myapplication.ui.component.Avatar
import com.example.myapplication.ui.component.BackButton
import com.example.myapplication.ui.component.DropDownMenuDeadlines
import com.example.myapplication.ui.component.GenericCard
import com.example.myapplication.ui.component.SearchAppBar
import com.example.myapplication.ui.component.SplitButtonList
import com.example.myapplication.ui.component.TopAppBar
import com.example.myapplication.ui.component.checkboxColors
import com.example.myapplication.ui.theme.MyApplicationTheme

class AllDeadlinesSummaryActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent{
            MyApplicationTheme {
                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    topBar = {
                        TopAppBar(
                            navigationIcon = {BackButton {  }},
                            id = "Scadenze",
                            trailingIcon = { DropDownMenuDeadlines() }
                        )
                    },
                    floatingActionButton = {AddButton {  }}
                ) { contentPadding ->
                    var showItems = remember { mutableStateOf(false) }
                    LazyColumn(
                        modifier = Modifier
                            .padding(
                                top = contentPadding.calculateTopPadding(),
                                start = contentPadding.calculateStartPadding(LayoutDirection.Ltr) + 8.dp,
                                end = contentPadding.calculateEndPadding(LayoutDirection.Ltr) + 8.dp,
                                bottom = contentPadding.calculateBottomPadding()
                            )
                    ) {
                        item{SearchAppBar("Categoria", contentPadding)}
                        items(scadenze.subList(0,5)){item ->
                            var checked by remember { mutableStateOf(false)}
                            GenericCard(
                                leadingContent = {Avatar(char = item.categoria.get(0))},
                                textSpace = 0.70f,
                                text = item.fornitore,
                                textDescription = item.categoria,
                                trailingContent = {
                                    Row(
                                        verticalAlignment = Alignment.CenterVertically,
                                        horizontalArrangement = Arrangement.End
                                    ){
                                        Text(text = item.prezzo.toString() + "€")
                                        Spacer(Modifier.size(8.dp))
                                        Checkbox(
                                            checked = checked,
                                            onCheckedChange = {checked = !checked},
                                            colors = checkboxColors()
                                        )
                                    }
                                }
                            )
                            Spacer(Modifier.size(8.dp))
                        }
                        item{Spacer(Modifier.size(8.dp))}
                        item{
                            SplitButtonList(showItems = showItems.value, {showItems.value = !showItems.value})
                        }
                        item{Spacer(Modifier.size(8.dp))}
                        if(showItems.value){
                            items(scadenze){item ->
                                var checked by remember { mutableStateOf(true) }
                                GenericCard(
                                    leadingContent = {Avatar(char = item.categoria.get(0))},
                                    textSpace = 0.7f,
                                    text = item.fornitore,
                                    textDescription = item.categoria,
                                    trailingContent = {
                                        Row(
                                            verticalAlignment = Alignment.CenterVertically,
                                            horizontalArrangement = Arrangement.Center
                                        ){
                                            Text(text = item.prezzo.toString() + "€")
                                            Spacer(Modifier.size(8.dp))
                                            Checkbox(
                                                checked = checked,
                                                onCheckedChange = {checked = !checked},
                                                colors = checkboxColors()
                                            )
                                        }
                                    }
                                )
                                Spacer(Modifier.size(8.dp))
                            }
                        }
                        item{Spacer(Modifier.size(80.dp))}
                    }
                }
            }
        }
    }
}