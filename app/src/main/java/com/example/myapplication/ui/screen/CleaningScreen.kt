package com.example.myapplication.ui.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.myapplication.ui.component.ListItemAvatar
import com.example.myapplication.ui.component.SearchAppBar
import com.example.myapplication.ui.component.TopAppBar

@Composable
fun CleaningScreen(modifier : Modifier){
    val items = List<String>(100, {item -> "item$item"})

    Scaffold (
        modifier = Modifier.fillMaxSize(),
        topBar = {TopAppBar("Cleaning")},
    ){ contentPadding ->

        LazyColumn (
            modifier = Modifier.fillMaxSize(),
            contentPadding = PaddingValues(8.dp,8.dp,8.dp,80.dp),
            verticalArrangement = Arrangement.spacedBy(10.dp),
        ){
            item{ SearchAppBar("Cleaning", contentPadding) }
            items(items) { item ->
                ListItemAvatar(item)

            }
        }
    }
}