package com.example.myapplication.ui.screen.Select

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
import com.example.myapplication.ui.component.BackButton
import com.example.myapplication.ui.component.ListItemCheckbox
import com.example.myapplication.ui.component.TopAppBar
import androidx.compose.foundation.lazy.items
import com.example.myapplication.ui.component.CustomSearchBar

@Composable
fun SelectActivity(
    state : SelectState,
    actions: SelectActions,
    navController : NavHostController
) {
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            TopAppBar(
                navigationIcon = {BackButton{navController.navigateUp()}},
                id = stringResource(R.string.select),
                trailingIcon = {
                    IconButton(
                        onClick = {
                            val selectedIds = state.itemsList.filter { it.checked }.map{ it.id }
                            navController.previousBackStackEntry
                                ?.savedStateHandle
                                ?.set("selectedIds", selectedIds)
                            navController.popBackStack()
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
            item {CustomSearchBar(state.searchText, onValueChange = {})}
            item{Spacer(Modifier.size(8.dp))}
            items(state.itemsList) { item ->
                var checked by remember {mutableStateOf(item.checked)}
                ListItemCheckbox(
                    text = item.name,
                    checked = checked,
                    onCheckedChange = {
                        checked = !checked
                        item.checked = it
                    },
                    onClick = {},
                    type = item.type.toString()
                )
                Spacer(Modifier.size(8.dp))
            }
        }
    }
}