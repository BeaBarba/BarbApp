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
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.myapplication.R
import com.example.myapplication.debug.customers
import com.example.myapplication.ui.component.BackButton
import com.example.myapplication.ui.component.ListItemCheckbox
import com.example.myapplication.ui.component.SearchAppBar
import com.example.myapplication.ui.component.TopAppBar
import androidx.compose.foundation.lazy.items
import com.example.myapplication.debug.CardItem
import com.example.myapplication.debug.address
import com.example.myapplication.debug.addressType
import com.example.myapplication.debug.customersType
import com.example.myapplication.debug.invoicesType
import com.example.myapplication.debug.materialsType

@Composable
fun SelectActivity(
    textSearch : String,
    entry : String,
    navController : NavHostController
) {
    var items_list : List<CardItem> =
        when(entry){
            "CustomerAdd" -> customersType
            "AddressAdd" -> addressType
            "InvoiceAdd" -> invoicesType
            "Materials" -> materialsType
            else -> listOf(CardItem("prova1", "NONE"), CardItem("prova2", "NONE"), CardItem("prova3", "NONE"))
        }
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            TopAppBar(
                navigationIcon = { BackButton{navController.navigateUp()} },
                id = stringResource(R.string.select),
                trailingIcon = {
                    IconButton(
                        onClick = {
                            navController.navigateUp()//{Save data}
                        },
                        colors = IconButtonDefaults.iconButtonColors(contentColor = MaterialTheme.colorScheme.onPrimary)
                    ) {
                        Icon(Icons.Filled.Check, contentDescription = stringResource(R.string.save))
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
            item { SearchAppBar(textSearch) }
            items(items_list) { item ->
                var checked by remember {mutableStateOf(false)}
                ListItemCheckbox(
                    text = item.name,
                    checked = checked,
                    onCheckedChange = { checked = !checked },
                    onClick = {},
                    type = item.type
                )
                Spacer(Modifier.size(8.dp))
            }
        }
    }
}