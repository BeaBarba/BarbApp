package com.example.myapplication.ui.screen.Job.add

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.calculateEndPadding
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Remove
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.myapplication.R
import com.example.myapplication.ui.NavigationRoute
import com.example.myapplication.ui.component.AddButton
import com.example.myapplication.ui.component.Avatar
import com.example.myapplication.ui.component.BackButton
import com.example.myapplication.ui.component.CustomSearchBar
import com.example.myapplication.ui.component.GenericCard
import com.example.myapplication.ui.component.TopAppBar
import com.example.myapplication.ui.component.checkColor

@Composable
fun JobMaterialsActivity(
    state : JobAddState,
    actions: JobAddActions,
    navController: NavHostController
){
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            TopAppBar(
                id = stringResource(R.string.material)+ " - " + stringResource(R.string.intervention),
                navigationIcon = {BackButton{navController.navigateUp()}},
                trailingIcon = {
                    IconButton(
                        onClick = {
                            actions.saveMaterials()
                            navController.navigateUp()
                        },
                        colors = IconButtonDefaults.iconButtonColors(
                            contentColor = MaterialTheme.colorScheme.onPrimary
                        )
                    ) {
                        Icon(Icons.Filled.Check, contentDescription = stringResource(R.string.save))
                    }
                }
            )
        },
        floatingActionButton = {AddButton{navController.navigate(NavigationRoute.MaterialAdd/*TODO(null)*/)}}
    ) { contentPadding ->
        LazyColumn(
            modifier = Modifier
                .padding(
                    top = contentPadding.calculateTopPadding() + 8.dp,
                    start = contentPadding.calculateRightPadding(LayoutDirection.Ltr) + 8.dp,
                    end = contentPadding.calculateEndPadding(LayoutDirection.Ltr) + 8.dp,
                    bottom = contentPadding.calculateBottomPadding()
                )
        ) {
            item{CustomSearchBar(stringResource(R.string.material), onValueChange = actions::searchMaterial)}
            item{Spacer(Modifier.size(8.dp))}
            items(state.materialsView){ item ->
                val material = item.first
                val quantity = item.second

                GenericCard(
                    type = material.material.type.name,
                    leadingContent = {
                        Avatar(
                            char = material.material.type.name[0],
                            type = material.material.type.name
                        )
                    },
                    text = material.material.category,
                    textDescription = "${material.material.model} - ${material.material.brand}",
                    textSpace = 0.6f,
                    trailingContent = {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.Center
                        ) {
                            IconButton(
                                onClick = { actions.decQuantity(material.material.id) },
                                colors = IconButtonDefaults.iconButtonColors(
                                    contentColor = checkColor(
                                        material.material.type.name,
                                        onPrimaryContainer = true
                                    )
                                )
                            ) {
                                Icon(Icons.Filled.Remove, contentDescription = stringResource(R.string.decrease))
                            }
                            Text(
                                text = "$quantity ${material.material.unitMeasurement}",
                                color = checkColor(material.material.type.name, onPrimaryContainer = true)
                            )
                            IconButton(
                                onClick = { actions.incQuantity(material.material.id) },
                                colors = IconButtonDefaults.iconButtonColors(
                                    contentColor = checkColor(
                                        item.first.material.type.name,
                                        onPrimaryContainer = true
                                    )
                                )
                            ) {
                                Icon(Icons.Filled.Add, contentDescription = stringResource(R.string.increase))
                            }
                        }
                    }
                )
                Spacer(Modifier.size(8.dp))
            }
            item{ Spacer(Modifier.size(80.dp))}
        }
    }
}