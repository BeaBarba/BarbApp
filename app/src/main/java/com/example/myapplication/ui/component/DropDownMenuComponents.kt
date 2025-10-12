package com.example.myapplication.ui.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.ArrowDropUp
import androidx.compose.material.icons.filled.DarkMode
import androidx.compose.material.icons.filled.FilterAlt
import androidx.compose.material.icons.filled.Inbox
import androidx.compose.material.icons.filled.LightMode
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.HorizontalDivider
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
import androidx.compose.ui.modifier.modifierLocalMapOf
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.myapplication.R

@Composable
fun DropDownMenuHome(){
    var expanded by remember { mutableStateOf(false) }
    Box(
        modifier = Modifier
            .padding(16.dp)
    ) {
        IconButton(onClick = { expanded = !expanded }) {
            Icon(Icons.Filled.Settings, contentDescription = "Settings", tint = MaterialTheme.colorScheme.onPrimary)
        }

        CustomDropDownMenu(expanded, { expanded = false }) {
            Column(
                modifier = Modifier.padding(end = 20.dp, start = 20.dp),
            ) {
                Row(
                    modifier = Modifier.padding(8.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center
                ) {
                    Text(
                        text = "Aspetto",
                        color = MaterialTheme.colorScheme.onPrimary,
                        fontSize = MaterialTheme.typography.bodyLarge.fontSize
                    )
                }
                DropdownMenuItem(
                    leadingIcon = { Icon(Icons.Filled.LightMode, contentDescription = "Chiaro",tint = MaterialTheme.colorScheme.onPrimary) },
                    text = {
                        Text(
                            text = "Chiaro",
                            color = MaterialTheme.colorScheme.onPrimary,
                            fontSize = MaterialTheme.typography.bodyLarge.fontSize
                        )
                    },
                    onClick = {/* Change color */ },
                )
                DropdownMenuItem(
                    leadingIcon =  { Icon(Icons.Filled.DarkMode, contentDescription = "Scuro",tint = MaterialTheme.colorScheme.onPrimary) },
                    text = {
                        Text(
                            text = "Scuro",
                            color = MaterialTheme.colorScheme.onPrimary,
                            fontSize = MaterialTheme.typography.bodyLarge.fontSize
                        )
                    },
                    onClick = {},
                )

                HorizontalDivider(
                    modifier = Modifier.padding(end = 6.dp, start = 6.dp),
                    thickness = 1.dp,
                    color = MaterialTheme.colorScheme.onPrimaryContainer
                )

                Row(
                    modifier = Modifier.padding(8.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center
                ) {
                    Text(
                        text = "Dati",
                        color = MaterialTheme.colorScheme.onPrimary,
                        fontSize = MaterialTheme.typography.bodyLarge.fontSize
                    )
                }
                DropdownMenuItem(
                    leadingIcon = { Icon(Icons.Filled.Inbox, contentDescription = "Chiaro", tint = MaterialTheme.colorScheme.onPrimary) },
                    text = {
                        Text(
                            text = "Importa Database",
                            color = MaterialTheme.colorScheme.onPrimary,
                            fontSize = MaterialTheme.typography.bodyLarge.fontSize
                        )
                    },
                    onClick = {/* Change color */ },
                )
                DropdownMenuItem(
                    leadingIcon =  { Icon(painterResource(R.drawable.export), contentDescription = "Scuro", tint = MaterialTheme.colorScheme.onPrimary) },
                    text = {
                        Text(
                            text = "Esporta Database",
                            color = MaterialTheme.colorScheme.onPrimary,
                            fontSize = MaterialTheme.typography.bodyLarge.fontSize
                        )
                    },
                    onClick = {},
                )
            }
        }
    }
}

@Composable
fun DropDownMenuBubbles(){
    var expanded by remember { mutableStateOf(false) }
    Box(
        modifier = Modifier.padding(16.dp)
    ) {
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
                    text = {
                        Text(
                            text = "Tutte",
                            color = MaterialTheme.colorScheme.onPrimary,
                            fontSize = MaterialTheme.typography.bodyLarge.fontSize
                        )
                    },
                    onClick = { /* Query that select all bubbles */ }
                )
                DropdownMenuItem(
                    text = {
                        Text(
                            text = "Venditore",
                            color = MaterialTheme.colorScheme.onPrimary,
                            fontSize = MaterialTheme.typography.bodyLarge.fontSize
                        )
                    },
                    onClick = { /* Query that select all bubbles based on seller */ }
                )
                DropdownMenuItem(
                    text = {
                        Text(
                            text = "Data",
                            color = MaterialTheme.colorScheme.onPrimary,
                            fontSize = MaterialTheme.typography.bodyLarge.fontSize
                        )
                    },
                    onClick = { /* Query that select all bubbles on date */ }
                )

                HorizontalDivider(
                    modifier = Modifier.padding(start = 6.dp, end = 6.dp),
                    thickness = 1.dp,
                    color = MaterialTheme.colorScheme.onPrimaryContainer
                )

                Row(
                    modifier = Modifier.padding(8.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center
                ) {
                    Text(
                        text = "Ordinamento",
                        color = MaterialTheme.colorScheme.onPrimary,
                        fontSize = MaterialTheme.typography.bodyLarge.fontSize
                    )
                }
                DropdownMenuItem(
                    leadingIcon = {
                        Icon(
                            Icons.Filled.ArrowDropUp,
                            contentDescription = "Ascending order",
                            tint = MaterialTheme.colorScheme.onPrimary
                        )
                    },
                    text = {
                        Text(
                            text = "Crescente",
                            color = MaterialTheme.colorScheme.onPrimary,
                            fontSize = MaterialTheme.typography.bodyLarge.fontSize
                        )
                    },
                    onClick = { /* Ascending order */ }
                )
                DropdownMenuItem(
                    leadingIcon = {
                        Icon(
                            Icons.Filled.ArrowDropDown,
                            contentDescription = "Descending order",
                            tint = MaterialTheme.colorScheme.onPrimary
                        )
                    },
                    text = {
                        Text(
                            text = "Decrescente",
                            color = MaterialTheme.colorScheme.onPrimary,
                            fontSize = MaterialTheme.typography.bodyLarge.fontSize
                        )
                    },
                    onClick = { /* Descending order */ }
                )
            }
        }
    }
}

@Composable
fun DropDownMenuCleaning(){
    var expanded by remember { mutableStateOf(false) }
    Box(
        modifier = Modifier.padding(16.dp)
    ) {
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
                    text = {
                        Text(
                            text = "Tutte",
                            color = MaterialTheme.colorScheme.onPrimary,
                            fontSize = MaterialTheme.typography.bodyLarge.fontSize
                        )
                    },
                    onClick = { /* Query that select all bubbles */ }
                )
                DropdownMenuItem(
                    text = {
                        Text(
                            text = "Indirizzo",
                            color = MaterialTheme.colorScheme.onPrimary,
                            fontSize = MaterialTheme.typography.bodyLarge.fontSize
                        )
                    },
                    onClick = { /* Query that select all bubbles based on address */ }
                )
                DropdownMenuItem(
                    text = {
                        Text(
                            text = "Data Inserimento",
                            color = MaterialTheme.colorScheme.onPrimary,
                            fontSize = MaterialTheme.typography.bodyLarge.fontSize
                        )
                    },
                    onClick = { /* Query that select all bubbles on date */ }
                )

                HorizontalDivider(
                    modifier = Modifier.padding(start = 6.dp, end = 6.dp),
                    thickness = 1.dp,
                    color = MaterialTheme.colorScheme.onPrimaryContainer
                )

                Row(
                    modifier = Modifier.padding(8.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center
                ) {
                    Text(
                        text = "Ordinamento",
                        color = MaterialTheme.colorScheme.onPrimary,
                        fontSize = MaterialTheme.typography.bodyLarge.fontSize
                    )
                }
                DropdownMenuItem(
                    leadingIcon = {
                        Icon(
                            Icons.Filled.ArrowDropUp,
                            contentDescription = "Ascending order",
                            tint = MaterialTheme.colorScheme.onPrimary
                        )
                    },
                    text = {
                        Text(
                            text = "Crescente",
                            color = MaterialTheme.colorScheme.onPrimary,
                            fontSize = MaterialTheme.typography.bodyLarge.fontSize
                        )
                    },
                    onClick = { /* Ascending order */ }
                )
                DropdownMenuItem(
                    leadingIcon = {
                        Icon(
                            Icons.Filled.ArrowDropDown,
                            contentDescription = "Descending order",
                            tint = MaterialTheme.colorScheme.onPrimary
                        )
                    },
                    text = {
                        Text(
                            text = "Decrescente",
                            color = MaterialTheme.colorScheme.onPrimary,
                            fontSize = MaterialTheme.typography.bodyLarge.fontSize
                        )
                    },
                    onClick = { /* Descending order */ }
                )
            }
        }
    }
}