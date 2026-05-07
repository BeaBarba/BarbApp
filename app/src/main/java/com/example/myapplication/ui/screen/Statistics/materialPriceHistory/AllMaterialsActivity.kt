package com.example.myapplication.ui.screen.Statistics.materialPriceHistory

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.calculateEndPadding
import androidx.compose.foundation.layout.calculateStartPadding
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ChevronRight
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.myapplication.R
import com.example.myapplication.ui.NavigationRoute
import com.example.myapplication.ui.component.Avatar
import com.example.myapplication.ui.component.BackButton
import com.example.myapplication.ui.component.CustomSearchBar
import com.example.myapplication.ui.component.GenericCard
import com.example.myapplication.ui.component.TopAppBar

@Composable
fun AllMaterialsActivity(
    state : AllMaterialsState,
    actions : AllMaterialsActions,
    navController : NavHostController
){
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar =  {
            TopAppBar(
                navigationIcon = { BackButton { navController.navigate(NavigationRoute.AllStatistics) }},
                id = stringResource(R.string.materials)
            )
        },
    ){ contentPadding ->

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
            item{
                CustomSearchBar(
                    label = stringResource(R.string.material),
                    value = state.searchText,
                    onValueChange = actions::search
                )
            }
            item{Spacer(Modifier.size(8.dp))}
            items(state.materialsView){ item ->
                GenericCard(
                    type = item.material.type.name,
                    leadingContent = {
                        Avatar(
                            type = item.material.type.name,
                            char = item.material.type.name[0],
                        )
                    },
                    text = item.material.category,
                    textDescription = "${item.material.model} - ${item.material.brand}",
                    trailingContent = {
                        Icon(
                            imageVector = Icons.Filled.ChevronRight,
                            contentDescription = stringResource(R.string.details),
                            modifier = Modifier.size(35.dp)
                        )
                    },
                    onClick = { navController.navigate(NavigationRoute.MaterialPriceHistory(item.material.id))}
                )

                Spacer(Modifier.size(8.dp))
            }
        }
    }
}