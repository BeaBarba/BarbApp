package com.example.myapplication.ui.screen

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
import com.example.myapplication.debug.interventi
import com.example.myapplication.ui.component.AddButton
import com.example.myapplication.ui.component.Avatar
import com.example.myapplication.ui.component.BackButton
import com.example.myapplication.ui.component.CustomDivider
import com.example.myapplication.ui.component.DropDownMenuJobs
import com.example.myapplication.ui.component.GenericCard
import com.example.myapplication.ui.NavigationRoute
import com.example.myapplication.ui.component.SearchAppBar
import com.example.myapplication.ui.component.TitleLabel
import com.example.myapplication.ui.component.TopAppBar
import com.example.myapplication.ui.component.checkColorAvatar

@Composable
fun AllJobsSummaryActivity(
    navController : NavHostController
){
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            TopAppBar(
                id = stringResource(R.string.interventions),
                navigationIcon = {BackButton{navController.navigate(NavigationRoute.Home)}},
                trailingIcon = {DropDownMenuJobs()}
            )
        },
        floatingActionButton = {AddButton{navController.navigate(NavigationRoute.JobAdd)}}
    ) {
        contentPadding ->
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
            item {SearchAppBar(stringResource(R.string.intervention))}
            items(interventi){ item ->
                GenericCard(
                    type = item.tipo,
                    text = item.indirizzo,
                    textDescription = item.cognomeNome,
                    leadingContent = {
                        Avatar(
                            item.tipo[0],
                            textColor = checkColorAvatar(item.tipo, primary = true),
                            backgroundColor = checkColorAvatar(item.tipo, onPrimary = true)
                        )
                    },
                    onClick = {navController.navigate(NavigationRoute.SingleJobSummary)}
                )
                Spacer(Modifier.size(8.dp))
            }
            item {CustomDivider()}
            item {TitleLabel(stringResource(R.string.j_completed))}
            item {Spacer(Modifier.size(8.dp))}
            items(interventi){ item ->
                GenericCard(
                    type = item.tipo,
                    text = item.indirizzo,
                    textDescription = item.cognomeNome,
                    leadingContent = {
                        Avatar(
                            item.tipo[0],
                            textColor = checkColorAvatar(item.tipo, primary = true),
                            backgroundColor = checkColorAvatar(item.tipo, onPrimary = true)
                        )
                    },
                    onClick = {navController.navigate(NavigationRoute.SingleJobSummary)}
                )
                Spacer(Modifier.size(8.dp))
            }
            item{Spacer(Modifier.size(80.dp))}
        }
    }
}