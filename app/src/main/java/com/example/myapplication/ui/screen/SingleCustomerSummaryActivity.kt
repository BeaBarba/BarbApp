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
import androidx.compose.material.icons.filled.Mode
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.myapplication.R
import com.example.myapplication.ui.component.Avatar
import com.example.myapplication.ui.component.BackButton
import com.example.myapplication.ui.component.CustomDivider
import com.example.myapplication.ui.component.KeyValueLabel
import com.example.myapplication.ui.component.GenericCard
import com.example.myapplication.ui.NavigationRoute
import com.example.myapplication.ui.component.TitleLabel
import com.example.myapplication.ui.component.TopAppBar
import com.example.myapplication.ui.component.checkColor
import com.example.myapplication.ui.component.checkColorAvatar

@Composable
fun SingleCustomerSummaryActivity(
    navController : NavHostController
){
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            TopAppBar(
                id = stringResource(R.string.customers),
                navigationIcon = {BackButton{navController.navigateUp()} },
                trailingIcon = {
                    IconButton(
                        onClick = {navController.navigate(NavigationRoute.CustomerAdd)}
                    ){
                        Icon(
                            painterResource(R.drawable.edit_square_24dp),
                            contentDescription = stringResource(R.string.edit),
                            tint = MaterialTheme.colorScheme.onPrimary
                        )
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
        ){
            item{TitleLabel(stringResource(R.string.personal_details))}
            item{Spacer(Modifier.size(8.dp))}
            item{KeyValueLabel(stringResource(R.string.id), "RSSMRA80A01A944I", 1.0f, 2.0f)}
            item{Spacer(Modifier.size(8.dp))}
            item{KeyValueLabel(stringResource(R.string.name), "Mario", 1.0f, 2.0f)}
            item{Spacer(Modifier.size(8.dp))}
            item{KeyValueLabel(stringResource(R.string.last_name), "Rossi", 1.0f, 2.0f)}
            item{Spacer(Modifier.size(8.dp))}
            item{KeyValueLabel(stringResource(R.string.address), "Piazza dei martiri", 1.0f, 2.0f)}
            item{Spacer(Modifier.size(8.dp))}
            item{KeyValueLabel(stringResource(R.string.city), "Bologna", 1.0f, 2.0f)}
            item{Spacer(Modifier.size(8.dp))}
            item{KeyValueLabel(stringResource(R.string.postal_code), "40133", 1.0f, 2.0f)}
            item{CustomDivider()}
            item{TitleLabel(stringResource(R.string.interventions))}
            item{Spacer(Modifier.size(8.dp))}
            items(interventi){item ->
                GenericCard(
                    type = item.tipo,
                    text = item.indirizzo,
                    textDescription = item.data,
                    leadingContent = {
                        Avatar(
                            char = item.tipo.get(0),
                            textColor = checkColorAvatar(item.tipo, primary = true),
                            backgroundColor = checkColorAvatar(item.tipo, onPrimary = true)
                        )
                    },
                    trailingContent = {
                        IconButton(
                            onClick = {navController.navigate(NavigationRoute.JobAdd)}
                        ) {
                            Icon(Icons.Filled.Mode, contentDescription = stringResource(R.string.edit), tint = checkColor(item.tipo, onPrimaryContainer = true))
                        }
                    },
                    onClick = {navController.navigate(NavigationRoute.SingleJobSummary)}
                )
                Spacer(Modifier.size(8.dp))
            }
        }
    }
}