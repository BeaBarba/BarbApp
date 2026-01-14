package com.example.myapplication.ui.screen.Statistics

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.calculateEndPadding
import androidx.compose.foundation.layout.calculateStartPadding
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.myapplication.R
import com.example.myapplication.debug.pagamenti
import com.example.myapplication.ui.component.Avatar
import com.example.myapplication.ui.component.BackButton
import com.example.myapplication.ui.component.GenericCard
import com.example.myapplication.ui.NavigationRoute
import com.example.myapplication.ui.component.TopAppBar

@Composable
fun AveragePaymentsTimesStatisticsActivity(
    navController : NavHostController
){
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            TopAppBar(
                navigationIcon = {BackButton{navController.navigate(NavigationRoute.AllStatistics)}},
                id = stringResource(R.string.average_times)
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
            items(pagamenti) { item ->
                var difference = 30 - item.data.substring(0,2).toInt()
                val check : Boolean = (difference < 20)
                val type = if(check){"NONE"}else{"ALA"}
                GenericCard(
                    type = type,
                    leadingContent = {
                        Avatar(
                            char = item.cliente.get(0),
                            type = type
                        )
                    },
                    text = item.cliente,
                    trailingContent = {
                        Text(
                            text = difference.toString(),
                            color = if(check){MaterialTheme.colorScheme.onPrimaryContainer}
                                    else{MaterialTheme.colorScheme.onSecondaryContainer}
                        )
                    }
                )
                Spacer(Modifier.size(8.dp))
            }
        }
    }
}