package com.example.myapplication.ui.screen.Customer.singleSummary

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
import com.example.myapplication.data.modules.CustomerType
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

@Composable
fun SingleCustomerSummaryActivity(
    customerId : String,
    state : SingleCustomerSummaryState,
    actions : SingleCustomerSummaryActions,
    navController : NavHostController
){
    actions.populateCustomerData(customerId)
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            TopAppBar(
                id = stringResource(R.string.customers),
                navigationIcon = {BackButton{navController.navigateUp()} },
                trailingIcon = {
                    IconButton(
                        onClick = {navController.navigate(NavigationRoute.CustomerAdd(null))}
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
            item{KeyValueLabel(stringResource(R.string.id), state.customerData.CF, 1.2f)}
            item{Spacer(Modifier.size(8.dp))}
            item{KeyValueLabel(stringResource(R.string.name), state.customerData.nome, 1.2f)}
            item{Spacer(Modifier.size(8.dp))}
            if(state.customerData.tipo == CustomerType.Privato.toString()) {
                item{KeyValueLabel(stringResource(R.string.last_name), state.customerData.cognome.toString(),1.2f)}
                item{Spacer(Modifier.size(8.dp))}
                item{KeyValueLabel(stringResource(R.string.place_birth), state.customerData.luogoNascita.toString(),1.2f)}
                item{Spacer(Modifier.size(8.dp))}
                item{KeyValueLabel(stringResource(R.string.date_birth), state.customerData.dataNascita.toString(),1.2f)}
            }
            if(state.customerData.tipo == CustomerType.Azienda.toString()){
                item{KeyValueLabel(stringResource(R.string.company_name), state.customerData.ragioneSociale.toString(),1.2f)}
                item{Spacer(Modifier.size(8.dp))}
                item{KeyValueLabel(stringResource(R.string.unique_code), state.customerData.codiceUnivoco.toString(),1.2f)}
                item{Spacer(Modifier.size(8.dp))}
                item{KeyValueLabel(stringResource(R.string.vat_number), state.customerData.partitaIVA.toString(),1.2f)}
            }

            item{CustomDivider()}
            item{TitleLabel(stringResource(R.string.contact))}
            item{Spacer(Modifier.size(8.dp))}
            item{KeyValueLabel(stringResource(R.string.email), state.customerData.email, 1.2f)}
            item{Spacer(Modifier.size(8.dp))}
            item{KeyValueLabel(stringResource(R.string.phone), state.customerData.telefono, 1.2f)}

            item{CustomDivider()}
            item{
                TitleLabel(
                    title =
                    if(state.customerData.tipo == CustomerType.Privato.toString()){
                        stringResource(R.string.residence)
                    }else{stringResource(R.string.address)}
                )
            }
            item{Spacer(Modifier.size(8.dp))}
            item{KeyValueLabel(stringResource(R.string.address), state.customerData.indirizzo, 1.2f)}
            item{Spacer(Modifier.size(8.dp))}
            item{KeyValueLabel(stringResource(R.string.municipality), state.customerData.comune, 1.2f)}
            item{Spacer(Modifier.size(8.dp))}
            item{KeyValueLabel(stringResource(R.string.city), state.customerData.citta, 1.2f)}
            item{Spacer(Modifier.size(8.dp))}
            item{KeyValueLabel(stringResource(R.string.province), state.customerData.provincia, 1.2f)}
            item{Spacer(Modifier.size(8.dp))}
            item{KeyValueLabel(stringResource(R.string.postal_code), state.customerData.cap, 1.2f)}

            item{CustomDivider()}
            item{TitleLabel(stringResource(R.string.interventions))}
            item{Spacer(Modifier.size(8.dp))}
            items(interventi){ item ->
                GenericCard(
                    type = item.tipo,
                    leadingContent = {
                        Avatar(
                            char = item.tipo.get(0),
                            type = item.tipo
                        )
                    },
                    text = item.indirizzo,
                    textDescription = item.data,
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