package com.example.myapplication.ui.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.calculateEndPadding
import androidx.compose.foundation.layout.calculateStartPadding
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import com.example.myapplication.ui.component.CheckLazyList
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

        Column(
            modifier = Modifier.padding(contentPadding)
        ) {
            SearchAppBar("Cleaning", contentPadding)
            CheckLazyList(items, contentPadding)
        }
    }

}
