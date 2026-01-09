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
import com.example.myapplication.debug.interventi
import com.example.myapplication.debug.listaClienti
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
            item{KeyValueLabel(stringResource(R.string.id), listaClienti.get(1).CF, 1.2f, 2.0f)}
            item{Spacer(Modifier.size(8.dp))}
            item{KeyValueLabel(stringResource(R.string.name), listaClienti.get(1).nome, 1.2f, 2.0f)}
            item{Spacer(Modifier.size(8.dp))}
            if(listaClienti.get(1).tipo == "P") {
                item{KeyValueLabel(stringResource(R.string.last_name),listaClienti.get(1).cognome.toString(),1.2f,2.0f)}
                item{Spacer(Modifier.size(8.dp))}
                item{KeyValueLabel(stringResource(R.string.place_birth),listaClienti.get(1).luogoNascita.toString(),1.2f,2.0f)}
                item{Spacer(Modifier.size(8.dp))}
                item{KeyValueLabel(stringResource(R.string.date_birth),listaClienti.get(1).dataNascita.toString(),1.2f,2.0f)}
            }
            if(listaClienti.get(1).tipo == "A"){
                item{KeyValueLabel(stringResource(R.string.company_name),listaClienti.get(1).ragioneSociale.toString(),1.2f,2.0f)}
                item{Spacer(Modifier.size(8.dp))}
                item{KeyValueLabel(stringResource(R.string.unique_code),listaClienti.get(1).codiceUnivoco.toString(),1.2f,2.0f)}
                item{Spacer(Modifier.size(8.dp))}
                item{KeyValueLabel(stringResource(R.string.vat_number),listaClienti.get(1).partitaIVA.toString(),1.2f,2.0f)}
            }

            item{CustomDivider()}
            item{TitleLabel(stringResource(R.string.contact))}
            item{Spacer(Modifier.size(8.dp))}
            item{KeyValueLabel(stringResource(R.string.email), listaClienti.get(1).email, 1.2f, 2.0f)}
            item{Spacer(Modifier.size(8.dp))}
            item{KeyValueLabel(stringResource(R.string.phone), listaClienti.get(1).telefono, 1.2f, 2.0f)}

            item{CustomDivider()}
            item{TitleLabel(title = if(listaClienti.get(1).tipo == "P"){stringResource(R.string.residence)}else{stringResource(R.string.address)})}
            item{Spacer(Modifier.size(8.dp))}
            item{KeyValueLabel(stringResource(R.string.address), listaClienti.get(1).indirizzo, 1.2f, 2.0f)}
            item{Spacer(Modifier.size(8.dp))}
            item{KeyValueLabel(stringResource(R.string.municipality), listaClienti.get(1).comune, 1.2f, 2.0f)}
            item{Spacer(Modifier.size(8.dp))}
            item{KeyValueLabel(stringResource(R.string.city), listaClienti.get(1).citta, 1.2f, 2.0f)}
            item{Spacer(Modifier.size(8.dp))}
            item{KeyValueLabel(stringResource(R.string.province), listaClienti.get(1).provincia, 1.2f, 2.0f)}
            item{Spacer(Modifier.size(8.dp))}
            item{KeyValueLabel(stringResource(R.string.postal_code), listaClienti.get(1).cap, 1.2f, 2.0f)}

            item{CustomDivider()}
            item{TitleLabel(stringResource(R.string.interventions))}
            item{Spacer(Modifier.size(8.dp))}
            items(interventi){ item ->
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