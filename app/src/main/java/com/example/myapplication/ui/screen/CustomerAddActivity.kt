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
import androidx.compose.material.icons.filled.LocationOn
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
import com.example.myapplication.ui.component.DeleteButton
import com.example.myapplication.ui.component.NavigationRoute
import com.example.myapplication.ui.component.TitleLabel
import com.example.myapplication.ui.component.TopAppBar

@Composable
fun CustomerAddActivity(
    navController : NavHostController
){
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            TopAppBar(
                id = "Cliente",
                navigationIcon = {BackButton{navController.navigateUp()}},
                trailingIcon = {
                    IconButton(
                        onClick = {navController.navigate(NavigationRoute.SingleCustomerSummary)},
                        colors = IconButtonDefaults.iconButtonColors(contentColor = MaterialTheme.colorScheme.onPrimary)
                    ) {
                        Icon(Icons.Filled.Check, "Save")
                    }
                }
            )
        }
    ) { contentPadding ->
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
            item {TitleLabel("Dati Anagrafici")}
            item {CustomOutlineTextField("CF")}
            item {CustomOutlineTextField("Nome")}
            item {CustomOutlineTextField("Cognome")}
            item {
                CustomOutlineTextField(
                    leadingIcon = {
                        /*IconButton(
                            onClick = {}
                        ) {*/
                            Icon(Icons.Filled.LocationOn, contentDescription = "Address")
                        //}
                    },
                    label = "Indirizzo"
                )
            }
            item {CustomOutlineTextField("Comune")}
            item {CustomOutlineTextField("Provincia")}
            item {CustomOutlineTextField("CAP")}
            item {Spacer(Modifier.size(10.dp))}
            item {DeleteButton({navController.navigate(NavigationRoute.AllCustomersSummary)})}
        }
    }
}