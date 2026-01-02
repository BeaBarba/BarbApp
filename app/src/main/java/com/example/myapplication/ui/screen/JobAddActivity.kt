package com.example.myapplication.ui.screen

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.calculateEndPadding
import androidx.compose.foundation.layout.calculateStartPadding
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.ChevronRight
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.outlined.AddPhotoAlternate
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.myapplication.ui.component.Avatar
import com.example.myapplication.ui.component.BackButton
import com.example.myapplication.ui.component.CustomOutlineTextField
import com.example.myapplication.ui.component.CustomTimePicker
import com.example.myapplication.ui.component.DatePickerFieldToModal
import com.example.myapplication.ui.component.DeleteButton
import com.example.myapplication.ui.component.GenericCard
import com.example.myapplication.ui.component.NavigationRoute
import com.example.myapplication.ui.component.SplitButtonMenu
import com.example.myapplication.ui.component.TopAppBar

@Composable
fun JobAddActivity(
    navController : NavHostController
){
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            TopAppBar(
                id = "Intervento",
                navigationIcon = {BackButton{navController.navigateUp()}},
                trailingIcon = {
                    IconButton(
                        onClick = {navController.navigate(NavigationRoute.SingleJobSummary)},
                        colors = IconButtonDefaults.iconButtonColors(
                            contentColor = MaterialTheme.colorScheme.onPrimary
                        )
                    ) {
                        Icon(Icons.Filled.Check, contentDescription = "Save")
                    }
                }
            )
        },
    ){ contentPadding ->
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
            item{
                GenericCard(
                    leadingContent = {
                        Avatar(
                            char = letters.get(0),
                        )
                    },
                    text = customers.get(0),
                    trailingContent = {
                        Icon(
                            Icons.Filled.ChevronRight,
                            contentDescription = "Edit Customer",
                            modifier = Modifier.size(35.dp)
                        )
                    },
                    onClick = {navController.navigate(NavigationRoute.CustomerAdd)}
                )
            }
            item{Spacer(Modifier.size(8.dp))}
            item{
                CustomOutlineTextField(
                    leadingIcon = {
                        /*IconButton(
                            onClick = {}
                        ) {*/
                            Icon(Icons.Filled.LocationOn, contentDescription = "Address")
                        //}
                    },
                label = "Indirizzo"
                )
            }
            item{Spacer(Modifier.size(8.dp))}
            item{CustomOutlineTextField("Comune")}
            item{Spacer(Modifier.size(8.dp))}
            item{CustomOutlineTextField("CAP")}
            item{Spacer(Modifier.size(8.dp))}
            item{CustomOutlineTextField("Provincia")}
            item{Spacer(Modifier.size(8.dp))}
            item{CustomOutlineTextField("Prezzo")}
            item{Spacer(Modifier.size(8.dp))}
            item{DatePickerFieldToModal()}
            item{Spacer(Modifier.size(8.dp))}
            item{CustomTimePicker("Ora Inizio")}
            item{Spacer(Modifier.size(8.dp))}
            item{CustomTimePicker("Ora Fine")}
            item{Spacer(Modifier.size(8.dp))}
            item{CustomOutlineTextField("Descrizione")}
            item{Spacer(Modifier.size(8.dp))}
            item{SplitButtonMenu(content = "Tipo", items = tipi, heightMenu = (tipi.size *55).dp)}
            item{Spacer(Modifier.size(8.dp))}
            item{
                GenericCard(
                    text = "Materiale",
                    trailingContent = {
                        Icon(
                            Icons.Filled.ChevronRight,
                            contentDescription = "Edit Materials",
                            modifier = Modifier.size(35.dp)
                        )
                    },
                    onClick = {navController.navigate(NavigationRoute.JobMaterials)}
                )
            }
            item{Spacer(Modifier.size(8.dp))}
            item{GenericCard(
                text = "Aggiungi Foto",
                trailingContent = {Icon(Icons.Outlined.AddPhotoAlternate, contentDescription = "Add Photo", modifier = Modifier.size(40.dp))}
            )}
            item{Spacer(Modifier.size(10.dp))}
            item{
                DeleteButton{
                    interventi = interventi.subList(1, interventi.size)
                    navController.navigate(NavigationRoute.AllJobsSummary)
                }
            }

        }
    }
}