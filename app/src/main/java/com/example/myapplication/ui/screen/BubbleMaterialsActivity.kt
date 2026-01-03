package com.example.myapplication.ui.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.calculateEndPadding
import androidx.compose.foundation.layout.calculateStartPadding
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.myapplication.ui.component.AddButton
import com.example.myapplication.ui.component.BackButton
import com.example.myapplication.ui.component.MaterialTable
import com.example.myapplication.ui.component.NavigationRoute
import com.example.myapplication.ui.component.SearchAppBar
import com.example.myapplication.ui.component.TopAppBar

@Composable
fun BubbleMaterialsActivity(
    navController : NavHostController
) {
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            TopAppBar(
                navigationIcon = {BackButton {navController.navigateUp()}},
                id = "Materiale - Bolla",
                trailingIcon = {
                    IconButton(
                        onClick = {navController.navigate(NavigationRoute.BubbleAdd)},
                        colors = IconButtonDefaults.iconButtonColors(
                            contentColor = MaterialTheme.colorScheme.onPrimary
                        )
                    ) {
                        Icon(Icons.Filled.Check, contentDescription = "Save")
                    }
                }
            )
        },
        floatingActionButton = {AddButton{navController.navigate(NavigationRoute.MaterialAdd)}}
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
            MaterialTable(
                contentPadding = contentPadding,
                listData = prodotti
            )
        }
    }
}