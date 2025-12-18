package com.example.myapplication.ui.screen

import android.content.Intent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import com.example.myapplication.ui.component.AddButton
import com.example.myapplication.ui.component.TopAppBar
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.LayoutDirection
import com.example.myapplication.ui.component.Avatar
import com.example.myapplication.ui.component.BackButton
import com.example.myapplication.ui.component.BoxDescription
import com.example.myapplication.ui.component.CustomAlertDialog
import com.example.myapplication.ui.component.CustomDivider
import com.example.myapplication.ui.component.CustomOutlineTextField
import com.example.myapplication.ui.component.CustomTimePicker
import com.example.myapplication.ui.component.DatePickerFieldToModal
import com.example.myapplication.ui.component.DeleteButton
import com.example.myapplication.ui.component.DropDownMenuCustomers
import com.example.myapplication.ui.component.GenericCard
import com.example.myapplication.ui.component.Images
import com.example.myapplication.ui.component.KeyValueLabel
import com.example.myapplication.ui.component.LargeCard
import com.example.myapplication.ui.component.SearchAppBar
import com.example.myapplication.ui.component.SplitButtonMenu
import com.example.myapplication.ui.component.SuggestionTextField
import com.example.myapplication.ui.component.TitleLabel

/* ------------------------------------------------------ Schermata ------------------------------------------ */
@Composable
fun Screen(modifier : Modifier){

    //CustomDateRangePicker()
    /**/
    val ctx = LocalContext.current
    var showDialog by remember { mutableStateOf(false)}
    Scaffold (
        modifier = Modifier.fillMaxSize(),
        topBar = {
            TopAppBar("Screen",
                navigationIcon = {
                    BackButton(){val intent = Intent(ctx, DeadlineAddActivity ::class.java)
                        ctx.startActivity(intent)}
                },
                trailingIcon = { DropDownMenuCustomers() }
            )
        },
        floatingActionButton = {AddButton(){
            val intent = Intent(ctx, HomeActivity ::class.java)
            ctx.startActivity(intent)
        } }
    ) { contentPadding ->/***/
        /*Column(
            modifier = modifier
                .padding(
                    top = contentPadding.calculateTopPadding(),
                    start = contentPadding.calculateStartPadding(LayoutDirection.Ltr) + 8.dp,
                    end = contentPadding.calculateEndPadding(LayoutDirection.Ltr) + 8.dp
                )
                .fillMaxSize()
        ){
            Spacer(Modifier.size(8.dp))
            Table<Prodotto>(contentPadding, prodotti, listOf(
                TableColumn("PRODOTTO", 3.0f ),
                TableColumn("QTÀ", 1.5f),
                TableColumn("PREZZO", 1.5f ),
                TableColumn("IVA", 1.0f )
            ))
        }*//**/
        LazyColumn(
            modifier = modifier
                .padding(
                    top = contentPadding.calculateTopPadding(),
                    start = contentPadding.calculateStartPadding(LayoutDirection.Ltr) + 8.dp,
                    end = contentPadding.calculateEndPadding(LayoutDirection.Ltr) + 8.dp,
                    bottom = contentPadding.calculateBottomPadding()
                )
                .fillMaxSize()
        ) {

            item{SearchAppBar("Screen", contentPadding)}
            item{TitleLabel("Dati Anagrafici")}
            item{Spacer(Modifier.size(8.dp))}

            item{Spacer(Modifier.size(8.dp)) }
            item{KeyValueLabel("CF", "RSSMRA80A01A944I", weightTitle = 0.5f, weighDescription = 1.0f)}
            item{Spacer(Modifier.size(8.dp))}
            item{KeyValueLabel("Nome", "Mario", weightTitle = 0.5f, weighDescription = 1.0f)}
            item{Spacer(Modifier.size(8.dp))}
            item{KeyValueLabel("Cognome", "Rossi", weightTitle = 0.5f, weighDescription = 1.0f)}
            item{Spacer(Modifier.size(8.dp))}
            item{KeyValueLabel("Indirizzo", "Piazza Dei Martiri 2", weightTitle = 0.5f, weighDescription = 1.0f)}
            item{Spacer(Modifier.size(8.dp))}
            item{KeyValueLabel("Comune", "Bologna", weightTitle = 0.5f, weighDescription = 1.0f)}
            item{Spacer(Modifier.size(8.dp))}
            item{KeyValueLabel("CAP", "40133", weightTitle = 0.5f, weighDescription = 1.0f)}
            item{Spacer(Modifier.size(8.dp))}
            item{SuggestionTextField(title = "Cognome", leadingIcon = {Icon(Icons.Outlined.Search, "Search")}, isAutocompleteMode = true, suggestions = suggerimenti)}
            item{Spacer(Modifier.size(8.dp))}
            item{CustomOutlineTextField("Nome")}
            item{Spacer(Modifier.size(8.dp))}
            item{DatePickerFieldToModal()}
            item{Spacer(Modifier.size(8.dp))}
            item{CustomTimePicker()}
            item{Spacer(Modifier.size(8.dp))}
            item{CustomDivider(MaterialTheme.colorScheme.primary, thickness = 2.dp)}
            item{Spacer(Modifier.size(8.dp))}
            item{TitleLabel("Interventi")}
            item{Spacer(Modifier.size(8.dp))}
            item{SplitButtonMenu(items = itemsList, content = "Venditori")}
            item{Spacer(Modifier.size(8.dp))}
            item{GenericCard(prodotti.get(1).nome, textDescription = "Vimar", leadingContent = {
                Avatar('E', textColor = MaterialTheme.colorScheme.tertiary, backgroundColor = MaterialTheme.colorScheme.onTertiary)
            }, type = "ELE" )}
            item{Spacer(Modifier.size(8.dp))}
            item{LargeCard(
                type = "ALA",
                title = "Indirizzo",
                subtitle = "Cliente",
                items = materialList,
                description = "Portate le scatoline e le matasse di fili, finito di fruttare",
                onClick = {}
            )}
            item{Spacer(Modifier.size(8.dp))}
            item{LargeCard(
                type = "CDZ",
                title = "Indirizzo",
                subtitle = "Cliente",
                items = materialList,
                description = "Portate le scatoline e le matasse di fili, finito di fruttare",
                onClick = {}
            )}
            item{Spacer(Modifier.size(8.dp))}
            item{Images()}
            item{Spacer(Modifier.size(8.dp))}
            item{BoxDescription(textDescription)}
            item{Spacer(Modifier.size(8.dp))}
            item{DeleteButton({})}
            item{ Spacer(Modifier.size(40.dp))}
        }
        if (showDialog) {
            CustomAlertDialog(contentPadding, { showDialog = false }, "OGGETTO", "Modello", itemsList)
        }
    }
}


