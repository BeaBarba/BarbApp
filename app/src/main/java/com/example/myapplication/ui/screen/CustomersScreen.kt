package com.example.myapplication.ui.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import com.example.myapplication.ui.component.AddButton
import com.example.myapplication.ui.component.CustomersCardsList
import com.example.myapplication.ui.component.SearchAppBar
import com.example.myapplication.ui.component.TopAppBar

@Composable
fun CustomersScreen(modifier : Modifier){

    Scaffold (
        modifier = Modifier.fillMaxSize(),
        topBar = { TopAppBar("Customers",{}, { IconButton(
            content = { Icon(Icons.Filled.ArrowBack, "Back") }, onClick = {},
        )
        })},
        floatingActionButton = { AddButton(){} }
    ){ contentPadding ->
        Column (
            modifier = Modifier.padding(contentPadding)
        ){
            SearchAppBar("Customers", contentPadding)

        }

    }
}


