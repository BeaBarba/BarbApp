package com.example.myapplication.ui.screen

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.calculateEndPadding
import androidx.compose.foundation.layout.calculateStartPadding
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.ChevronRight
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
import com.example.myapplication.ui.component.BackButton
import com.example.myapplication.ui.component.CustomOutlineTextField
import com.example.myapplication.ui.component.DatePickerFieldToModal
import com.example.myapplication.ui.component.DeleteButton
import com.example.myapplication.ui.component.GenericCard
import com.example.myapplication.ui.component.NavigationRoute
import com.example.myapplication.ui.component.SplitButtonMenu
import com.example.myapplication.ui.component.TopAppBar

@Composable
fun BubbleAddActivity(
    navController : NavHostController
){
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            TopAppBar(
                navigationIcon = {BackButton {navController.navigateUp()}},
                id = "Bolla",
                trailingIcon = {
                    IconButton(
                        onClick = {navController.navigate(NavigationRoute.SingleBubbleSummary)},
                        colors = IconButtonDefaults.iconButtonColors(
                            contentColor = MaterialTheme.colorScheme.onPrimary
                        )
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
            item{CustomOutlineTextField("Numero di bolla")}
            item{Spacer(Modifier.size(8.dp))}
            item{DatePickerFieldToModal("Data Emissione")}
            item{Spacer(Modifier.size(8.dp))}
            item{SplitButtonMenu("Venditori", venditori)}
            item{Spacer(Modifier.size(8.dp))}
            item{
                GenericCard(
                    text = "Materiale",
                    trailingContent = {
                        Icon(
                            Icons.Filled.ChevronRight,
                            contentDescription = "Edit Materials",
                            modifier = Modifier.size(35.dp)
                        )
                    },
                    onClick = {navController.navigate(NavigationRoute.BubbleMaterials)}
                )
            }
            item{Spacer(Modifier.size(8.dp))}
            item{
                DeleteButton{
                    bolle = bolle.subList(1, bolle.size)
                    navController.navigate(NavigationRoute.AllBubblesSummary)
                }
            }
            item{Spacer(Modifier.size(8.dp))}
        }
    }
}