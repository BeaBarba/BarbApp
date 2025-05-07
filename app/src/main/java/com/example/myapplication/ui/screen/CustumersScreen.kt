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
import com.example.myapplication.ui.component.AddButton
import com.example.myapplication.ui.component.CardItemAvatar
import com.example.myapplication.ui.component.SearchAppBar
import com.example.myapplication.ui.component.TopAppBar

@Composable
fun CustumersScreen(modifier : Modifier){
    val items = List<String>(100, {item -> "item$item"})

    Scaffold (
        modifier = Modifier.fillMaxSize(),
        topBar = {TopAppBar("Custumers")},
        floatingActionButton = { AddButton("Custumers")}
    ){ contentPadding ->

        LazyColumn (
            modifier = Modifier.fillMaxSize(),
            contentPadding = PaddingValues(8.dp,8.dp,8.dp,135.dp),
            verticalArrangement = Arrangement.spacedBy(10.dp),
        ){
            item{ SearchAppBar("Custumers", contentPadding) }
            items(items) { item ->
                CardItemAvatar(item)
            }
        }
    }
}