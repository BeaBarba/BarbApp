package com.example.myapplication.ui.screen

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.calculateEndPadding
import androidx.compose.foundation.layout.calculateStartPadding
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.myapplication.ui.component.AddButton
import com.example.myapplication.ui.component.Avatar
import com.example.myapplication.ui.component.BackButton
import com.example.myapplication.ui.component.CustomDivider
import com.example.myapplication.ui.component.DatePickerModal
import com.example.myapplication.ui.component.GenericCard
import com.example.myapplication.ui.component.LargeCard
import com.example.myapplication.ui.component.NavigationRoute
import com.example.myapplication.ui.component.TitleLabel
import com.example.myapplication.ui.component.TopAppBar
import com.example.myapplication.ui.component.checkColorAvatar

@Composable
fun TodayCalendarActivity(
    navController: NavHostController
){
    var selectedDate by remember {mutableStateOf<Long?>(null)}
    val openPicker = remember {mutableStateOf(false)}
    val datePickerState = rememberDatePickerState()
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            TopAppBar(
                navigationIcon = {BackButton{navController.navigate(NavigationRoute.Home)}},
                id = "Oggi",
                trailingIcon = {
                    IconButton(
                        onClick = {openPicker.value = true},
                        colors = IconButtonDefaults.iconButtonColors(
                            contentColor = MaterialTheme.colorScheme.onPrimary
                        )
                    ) {
                        Icon(Icons.Default.DateRange, contentDescription = "Apri date picker")
                    }

                    DatePickerModal(
                        datePickerState = datePickerState,
                        openDialogState = openPicker,
                        onDateSelected = { millis ->
                            selectedDate = millis
                            if(millis != null){
                                navController.navigate(NavigationRoute.DayCalendar)
                            }
                        }
                    )
                }
            )
        },
        floatingActionButton = {AddButton{navController.navigate(NavigationRoute.JobAdd)}}
    ) {contentPadding ->
        LazyColumn(
            modifier = Modifier.padding(
                top = contentPadding.calculateTopPadding() + 8.dp,
                start = contentPadding.calculateStartPadding(LayoutDirection.Ltr) + 8.dp,
                end = contentPadding.calculateEndPadding(LayoutDirection.Ltr) + 8.dp,
                bottom = contentPadding.calculateBottomPadding()
            )
        ) {
            items(appuntamenti){item ->
                LargeCard(
                    type = item.tipo,
                    title = item.indirizzo,
                    subtitle = item.cliente,
                    description = item.descrizione,
                    items = materialList,
                    onClick = {navController.navigate(NavigationRoute.SingleJobSummary)},
                )
                Spacer(Modifier.size(8.dp))
            }
            item{Spacer(Modifier.size(8.dp))}
            item{CustomDivider()}
            item{Spacer(Modifier.size(8.dp))}
            item{TitleLabel("Da Programmare")}
            item{Spacer(Modifier.size(8.dp))}
            items(appuntamenti){ item ->
                GenericCard(
                    type = item.tipo,
                    leadingContent = {
                        Avatar(
                            char = item.tipo.get(0),
                            textColor = checkColorAvatar(item.tipo, primary = true),
                            backgroundColor = checkColorAvatar(item.tipo, onPrimary = true)
                        )
                    },
                    text = item.indirizzo,
                    textDescription = item.cliente,
                    onClick = {navController.navigate(NavigationRoute.SingleJobSummary)}
                )
                Spacer(Modifier.size(8.dp))
            }
            item{Spacer(Modifier.size(80.dp))}
        }
    }
}