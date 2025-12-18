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
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.material.icons.filled.ChevronRight
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import com.example.myapplication.ui.component.AddButton
import com.example.myapplication.ui.component.Avatar
import com.example.myapplication.ui.component.BackButton
import com.example.myapplication.ui.component.CustomDivider
import com.example.myapplication.ui.component.DropDownMenuCleaning
import com.example.myapplication.ui.component.GenericCard
import com.example.myapplication.ui.component.SearchAppBar
import com.example.myapplication.ui.component.TopAppBar
import com.example.myapplication.ui.component.checkboxColors
import com.example.myapplication.ui.theme.MyApplicationTheme

class AllCleaningSummaryActivity : ComponentActivity() {
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
                            id = "Pulizie",
                            trailingIcon = {DropDownMenuCleaning()}
                        )
                    },
                    floatingActionButton = {AddButton {  }}
                ) { contentPadding ->
                    var showItems by remember { mutableStateOf(false) }
                    LazyColumn(
                        modifier = Modifier
                            .padding(
                                top = contentPadding.calculateTopPadding(),
                                start = contentPadding.calculateStartPadding(LayoutDirection.Ltr) + 8.dp,
                                end = contentPadding.calculateEndPadding(LayoutDirection.Ltr) + 8.dp,
                                bottom = contentPadding.calculateBottomPadding()
                            )
                    ) {
                        item{SearchAppBar("Cliente", contentPadding)}
                        items(customers.subList(0,5)){item ->
                            var checked by remember {mutableStateOf(false)}
                            GenericCard(
                                leadingContent = {Avatar(char = item.get(0))},
                                text = item,
                                trailingContent = {
                                    Checkbox(
                                        checked = checked,
                                        onCheckedChange = {checked = !checked},
                                        colors = checkboxColors()
                                    )
                                }
                            )
                            Spacer(Modifier.size(8.dp))
                        }
                        item{CustomDivider()}
                        item{
                            GenericCard(
                                leadingContent = {
                                    if(showItems){
                                        Icon(Icons.Filled.ChevronRight, contentDescription = "Show items", modifier = Modifier.size(35.dp))
                                    }else{
                                        Icon(Icons.Filled.KeyboardArrowDown, contentDescription = "Hide items", modifier = Modifier.size(35.dp))
                                    }
                                },
                                text = "Completati",
                                onClick = {showItems = !showItems}
                            )
                        }
                        item{Spacer(Modifier.size(8.dp))}
                        if(showItems){
                            items(customers){item ->
                                var checked by remember { mutableStateOf(true) }
                                GenericCard(
                                    leadingContent = {Avatar(char = item.get(0))},
                                    text = item,
                                     trailingContent = {
                                         Checkbox(
                                             checked = checked,
                                             onCheckedChange = {checked = !checked},
                                             colors = checkboxColors()
                                         )
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