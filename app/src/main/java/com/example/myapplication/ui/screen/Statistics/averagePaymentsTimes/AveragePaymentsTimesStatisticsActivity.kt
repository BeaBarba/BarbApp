package com.example.myapplication.ui.screen.Statistics.averagePaymentsTimes

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
import com.example.myapplication.ui.component.Avatar
import com.example.myapplication.ui.component.BackButton
import com.example.myapplication.ui.component.GenericCard
import com.example.myapplication.ui.NavigationRoute
import com.example.myapplication.ui.component.TopAppBar

@Composable
fun AveragePaymentsTimesStatisticsActivity(
    state : AveragePaymentsTimesStatisticsState,
    navController : NavHostController
){
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            TopAppBar(
                navigationIcon = { BackButton{ navController.navigate(NavigationRoute.AllStatistics) } },
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

            items(state.values) { item ->
                val check : Boolean = (item.days < 20)
                val type = if(check) {"NONE"} else {"ALA"}
                GenericCard(
                    type = type,
                    leadingContent = {
                        Avatar(
                            char = item.name[0],
                            type = type
                        )
                    },
                    text = item.name,
                    trailingContent = {
                        Text(
                            text = item.days.toString(),
                            color = if(check){ MaterialTheme.colorScheme.onPrimaryContainer }
                                    else { MaterialTheme.colorScheme.onSecondaryContainer }
                        )
                    },
                    onClick = { navController.navigate(NavigationRoute.SingleCustomerSummary(item.cf)) }
                )
                Spacer(Modifier.size(8.dp))
            }
        }
    }
}