package com.example.myapplication.ui.screen.Bubble.allSummary

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.calculateEndPadding
import androidx.compose.foundation.layout.calculateStartPadding
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.myapplication.R
import com.example.myapplication.ui.component.AddButton
import com.example.myapplication.ui.component.BackButton
import com.example.myapplication.ui.component.DropDownMenuBubbles
import com.example.myapplication.ui.component.GenericCard
import com.example.myapplication.ui.NavigationRoute
import com.example.myapplication.ui.component.TopAppBar
import java.time.format.DateTimeFormatter

@Composable
fun AllBubblesSummaryActivity(
    state: AllBubblesSummaryState,
    actions: AllBubblesSummaryActions,
    navController : NavHostController
){
    actions.populate()
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            TopAppBar(
                navigationIcon = {BackButton{navController.navigate(NavigationRoute.Home)}},
                id = stringResource(R.string.bubbles),
                trailingIcon = {
                    DropDownMenuBubbles(
                        {actions.sellerSort()},
                        {actions.dateSort()},
                        {actions.ascendingSort()},
                        {actions.descendingSort()}
                    )
                }
            )
        },
        floatingActionButton = {AddButton{navController.navigate(NavigationRoute.BubbleAdd(null))}}
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
            items(state.bubbles){ item ->
                GenericCard(
                    text = item.fornitore,
                    textDescription = item.data.format(DateTimeFormatter.ofPattern("dd/MM/YYYY")),
                    onClick = {navController.navigate(NavigationRoute.SingleBubbleSummary(bubbleId = 0))}
                )
                Spacer(Modifier.size(8.dp))
            }
            item{Spacer(Modifier.size(80.dp))}
        }
    }
}