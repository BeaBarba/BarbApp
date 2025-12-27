package com.example.myapplication.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Air
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.ArrowDropUp
import androidx.compose.material.icons.filled.Campaign
import androidx.compose.material.icons.filled.DarkMode
import androidx.compose.material.icons.filled.FilterAlt
import androidx.compose.material.icons.filled.Inbox
import androidx.compose.material.icons.filled.LightMode
import androidx.compose.material.icons.filled.Lightbulb
import androidx.compose.material.icons.filled.Outbox
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun CustomDropDownMenu(
    expanded: Boolean,
    onDismissRequest: () -> Unit,
    height : Dp = 300.dp,
    content: @Composable () -> Unit
) {
    DropdownMenu(
        expanded = expanded,
        onDismissRequest = onDismissRequest,
        modifier = Modifier
            .height(height)
            .border(1.dp, MaterialTheme.colorScheme.onPrimary, RoundedCornerShape(15.dp))
            .background(MaterialTheme.colorScheme.primary),
        shape = RoundedCornerShape(15.dp)
    ) {
        content()
    }
}

@Composable
fun MenuText(text: String, color : Color = MaterialTheme.colorScheme.onPrimary){
    Text(
        text = text,
        color = color,
        fontSize = MaterialTheme.typography.bodyLarge.fontSize
    )
}

@Composable
fun MenuDivider(){
    CustomDivider(
        color = MaterialTheme.colorScheme.onPrimaryContainer,
        paddingHorizontal = 6.dp,
        paddingVertical = 0.dp
    )
}

@Composable
fun MenuTitle(text : String){
    Row(
        modifier = Modifier.padding(10.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ){
        Text(
            text = text,
            color = MaterialTheme.colorScheme.onPrimary,
            fontSize = MaterialTheme.typography.bodyLarge.fontSize
        )
    }
}

@Composable
fun Order(
    text : String = "Ordinamento",
    onClickAscending : () -> Unit,
    onClickDescending : () -> Unit
){
    MenuTitle(text)
    DropdownMenuItem(
        leadingIcon = {
            Icon(Icons.Filled.ArrowDropUp, contentDescription = "Ascending order",
                tint = MaterialTheme.colorScheme.onPrimary
            ) },
        text = {
            MenuText(text = "Crescente")
        },
        onClick = { onClickAscending() /* Order items to ascending order */ }
    )
    DropdownMenuItem(
        leadingIcon = {
            Icon(Icons.Filled.ArrowDropDown, contentDescription = "Descending order",
                tint = MaterialTheme.colorScheme.onPrimary
            ) },
        text = {
            MenuText(text = "Decrescente")
        },
        onClick = { onClickDescending() /* Order items to descending order */ }
    )
}

@Composable
fun DropDownMenuHome(){
    var expanded by remember { mutableStateOf(false) }
    Box() {
        IconButton(onClick = { expanded = !expanded }) {
            Icon(Icons.Filled.Settings, contentDescription = "Settings", tint = MaterialTheme.colorScheme.onPrimary)
        }

        CustomDropDownMenu(expanded, { expanded = false }) {
            Column(
                modifier = Modifier.padding(end = 20.dp, start = 20.dp),
            ) {
                MenuTitle("Aspetto")
                DropdownMenuItem(
                    leadingIcon = { Icon(Icons.Filled.LightMode, contentDescription = "Chiaro",tint = MaterialTheme.colorScheme.onPrimary) },
                    text = { MenuText(text = "Chiaro") },
                    onClick = { /* Change color light */ },
                )
                DropdownMenuItem(
                    leadingIcon =  { Icon(Icons.Filled.DarkMode, contentDescription = "Scuro",tint = MaterialTheme.colorScheme.onPrimary) },
                    text = { MenuText(text = "Scuro") },
                    onClick = { /* Change color dark */ },
                )

                MenuDivider()

                MenuTitle("Dati")
                DropdownMenuItem(
                    leadingIcon = { Icon(Icons.Filled.Inbox, contentDescription = "Import", tint = MaterialTheme.colorScheme.onPrimary) },
                    text = { MenuText(text = "Importa Database") },
                    onClick = { /* Action to import database */ },
                )
                DropdownMenuItem(
                    leadingIcon =  { Icon(Icons.Filled.Outbox, contentDescription = "Export", tint = MaterialTheme.colorScheme.onPrimary) },
                    text = { MenuText(text = "Esporta Database") },
                    onClick = { /* Action to export database */ },
                )
            }
        }
    }
}

@Composable
fun DropDownMenuBubbles(){
    var expanded by remember { mutableStateOf(false) }
    Box() {
        IconButton(onClick = { expanded = !expanded }) {
            Icon(Icons.Filled.FilterAlt, contentDescription = "Filter", tint = MaterialTheme.colorScheme.onPrimary)
        }

        CustomDropDownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false },
        ) {
            Column(
                modifier = Modifier.padding(end = 20.dp, start = 20.dp),
            ) {
                DropdownMenuItem(
                    text = { MenuText(text = "Tutte") },
                    onClick = { /* Query that select all bubbles */ }
                )
                DropdownMenuItem(
                    text = { MenuText(text = "Venditore") },
                    onClick = { /* Query that select all bubbles based on seller */ }
                )
                DropdownMenuItem(
                    text = { MenuText(text = "Data") },
                    onClick = { /* Query that select all bubbles on date */ }
                )

                MenuDivider()

                Order(onClickAscending = {}, onClickDescending = {})
            }
        }
    }
}

@Composable
fun DropDownMenuCleaning(){
    var expanded by remember { mutableStateOf(false) }
    Box() {
        IconButton(onClick = { expanded = !expanded }) {
            Icon(Icons.Filled.FilterAlt, contentDescription = "Filter", tint = MaterialTheme.colorScheme.onPrimary)
        }

        CustomDropDownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false }
        ) {
            Column(
                modifier = Modifier.padding(end = 20.dp, start = 20.dp),
            ) {
                DropdownMenuItem(
                    text = { MenuText(text = "Tutte") },
                    onClick = { /* Query that select all bubbles */ }
                )
                DropdownMenuItem(
                    text = { MenuText(text = "Indirizzo") },
                    onClick = { /* Query that select all bubbles based on address */ }
                )
                DropdownMenuItem(
                    text = { MenuText(text = "Data Inserimento") },
                    onClick = { /* Query that select all bubbles on date */ }
                )

                MenuDivider()

                Order(onClickAscending = {}, onClickDescending = {})
            }
        }
    }
}

@Composable
fun DropDownMenuJobs(){
    var expanded by remember { mutableStateOf(false) }

    Box(){
        IconButton(onClick = { expanded = !expanded }) {
            Icon(Icons.Filled.FilterAlt, contentDescription = "Filter", tint = MaterialTheme.colorScheme.onPrimary)
        }

        CustomDropDownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false},
            height = 485.dp
        ) {
            Column(
                modifier = Modifier.padding(start = 20.dp, end = 20.dp)
            ) {
                DropdownMenuItem(
                    text = { MenuText(text = "Tutti") },
                    onClick = { /* Query that select all jobs */ }
                )
                DropdownMenuItem(
                    text = { MenuText(text = "Effettuati") },
                    onClick = { /* Query that select jobs taken */ }
                )
                DropdownMenuItem(
                    text = { MenuText(text = "Da programmare") },
                    onClick = { /* Query that select jobs to be planned */ }
                )

                MenuDivider()

                MenuTitle("Tipologia")
                DropdownMenuItem(
                    leadingIcon = { Icon(Icons.Filled.Lightbulb,contentDescription = "Eletric",tint = MaterialTheme.colorScheme.onPrimary) },
                    text = { MenuText(text = "Elettrico") },
                    onClick = { /* Query that select jobs on eletric */ }
                )
                DropdownMenuItem(
                    leadingIcon = {Icon(Icons.Filled.Air,contentDescription = "Air conditioning",tint = MaterialTheme.colorScheme.onPrimary) },
                    text = { MenuText(text = "Condizionamento") },
                    onClick = { /* Query that select jobs on air conditioning */ }
                )
                DropdownMenuItem(
                    leadingIcon = { Icon(Icons.Filled.Campaign,contentDescription = "Alarm",tint = MaterialTheme.colorScheme.onPrimary) },
                    text = { MenuText(text = "Allarme") },
                    onClick = { /* Query that select jobs on air alarm */ }
                )

                MenuDivider()

                Order(onClickAscending = {}, onClickDescending = {})
            }
        }
    }
}

@Composable
fun DropDownMenuCustomers(){
    var expanded by remember { mutableStateOf(false) }

    Box( ){
        IconButton( onClick = {expanded = !expanded} ) {
            Icon(Icons.Filled.FilterAlt, "Filter", tint = MaterialTheme.colorScheme.onPrimary)
        }

        CustomDropDownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false },
            height = 450.dp
        ) {
            Column(modifier = Modifier.padding(start = 20.dp, end = 20.dp)) {
                DropdownMenuItem(
                    text = { MenuText(text = "Tutti") },
                    onClick = { /* Query that select all custumers */}
                )
                DropdownMenuItem(
                    text = { MenuText(text = "Riferimento") },
                    onClick = { /* Query tghat groups custumers by reference */ }
                )

                MenuDivider()

                MenuTitle("Intervento")
                DropdownMenuItem(
                    leadingIcon = { Icon(Icons.Filled.Air, contentDescription = "Air conditioning", tint = MaterialTheme.colorScheme.onPrimary)},
                    text = { MenuText(text = "Condizionamento") },
                    onClick = { /* Query that groups and filter custumers by air conditioning */ }
                )
                DropdownMenuItem(
                    leadingIcon = { Icon(Icons.Filled.Lightbulb, contentDescription = "Eletric", tint = MaterialTheme.colorScheme.onPrimary)},
                    text = { MenuText(text = "Elettrico") },
                    onClick = { /*Query that group and filter custumers by eletric */ }
                )
                DropdownMenuItem(
                    leadingIcon = { Icon(Icons.Filled.Campaign, contentDescription = "Alarm", tint = MaterialTheme.colorScheme.onPrimary)},
                    text = { MenuText("Allarme") },
                    onClick = { /* Query that groups and filter custumers by alarm */ }
                )

                MenuDivider()

                Order(onClickAscending = {}, onClickDescending = {})
            }
        }
    }
}

@Composable
fun DropDownMenuPayments(){
    var expanded by remember { mutableStateOf(false) }

    Box(){
        IconButton(onClick = {expanded = !expanded}) {
            Icon(Icons.Filled.FilterAlt, contentDescription = "Filter", tint = MaterialTheme.colorScheme.onPrimary)
        }

        CustomDropDownMenu(
            expanded = expanded,
            onDismissRequest = {expanded = false}
        ) {
            Column(modifier = Modifier.padding(start = 20.dp, end = 20.dp)) {
                Order("Importo", {}, {})

                MenuDivider()

                Order("Data Emissione", {}, {})
            }
        }
    }
}

@Composable
fun DropDownMenuDeadlines(){
    var expanded by remember { mutableStateOf(false) }
    var category =  listOf("cat1" , "cat2" , "cat3")

    Box() {
        IconButton(onClick = { expanded = !expanded }) {
            Icon(
                Icons.Filled.FilterAlt,
                contentDescription = "Filter",
                tint = MaterialTheme.colorScheme.onPrimary
            )
        }

        CustomDropDownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false },
            height = 480.dp
        ) {
            Column(modifier = Modifier.padding(start = 20.dp, end = 20.dp)) {
                DropdownMenuItem(
                    text = { MenuText(text = "Tutti") },
                    onClick = { /* Query that select all payments */ }
                )

                MenuDivider()

                MenuTitle("Categorie")
                category.forEach { cat ->
                    DropdownMenuItem(
                        text = { MenuText(cat.toString()) },
                        onClick = { /* Query that filter payments by category */ }
                    )
                }

                MenuDivider()

                MenuTitle("Venditori")
                DropdownMenuItem(
                    text = { MenuText("Venditore 1") },
                    onClick = { /* Query that filter payments by sellers */ }
                )

                MenuDivider()
                Order(onClickDescending = {}, onClickAscending = {})
            }
        }
    }
}