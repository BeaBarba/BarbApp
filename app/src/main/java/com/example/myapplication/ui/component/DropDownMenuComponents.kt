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
import androidx.compose.material.icons.filled.AutoMode
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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.myapplication.R

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
    text : String = stringResource(R.string.sorting),
    onClickAscending : () -> Unit,
    onClickDescending : () -> Unit
){
    MenuTitle(text)
    DropdownMenuItem(
        leadingIcon = {
            Icon(Icons.Filled.ArrowDropUp, contentDescription = stringResource(R.string.a_order),
                tint = MaterialTheme.colorScheme.onPrimary
            ) },
        text = {
            MenuText(text = stringResource(R.string.ascending))
        },
        onClick = { onClickAscending() }
    )
    DropdownMenuItem(
        leadingIcon = {
            Icon(Icons.Filled.ArrowDropDown, contentDescription = stringResource(R.string.d_order),
                tint = MaterialTheme.colorScheme.onPrimary
            ) },
        text = {
            MenuText(text = stringResource(R.string.descending))
        },
        onClick = { onClickDescending() }
    )
}

@Composable
fun DropDownMenuHome(){
    var expanded by remember { mutableStateOf(false) }
    Box() {
        IconButton(onClick = { expanded = !expanded }) {
            Icon(Icons.Filled.Settings, contentDescription = stringResource(R.string.settings), tint = MaterialTheme.colorScheme.onPrimary)
        }

        CustomDropDownMenu(expanded, { expanded = false }, 350.dp) {
            Column(
                modifier = Modifier.padding(end = 20.dp, start = 20.dp),
            ) {
                MenuTitle(stringResource(R.string.apparence))
                DropdownMenuItem(
                    leadingIcon = {
                        Icon(
                            imageVector = Icons.Filled.LightMode,
                            contentDescription = stringResource(R.string.light_mode),
                            tint = MaterialTheme.colorScheme.onPrimary
                        )
                    },
                    text = {MenuText(text = stringResource(R.string.light_mode))},
                    onClick = { /* Change color light */ },
                )
                DropdownMenuItem(
                    leadingIcon = {
                        Icon(
                            imageVector = Icons.Filled.DarkMode,
                            contentDescription = stringResource(R.string.dark_mode),
                            tint = MaterialTheme.colorScheme.onPrimary
                        )
                    },
                    text = {MenuText(text = stringResource(R.string.dark_mode))},
                    onClick = { /* Change color dark */ },
                )
                DropdownMenuItem(
                    leadingIcon = {
                        Icon(
                            imageVector = Icons.Filled.AutoMode,
                            contentDescription = stringResource(R.string.system_mode),
                            tint = MaterialTheme.colorScheme.onPrimary
                        )
                    },
                    text = {MenuText(text = stringResource(R.string.system_mode))},
                    onClick = { /* Change dependence of system color */ },
                )

                MenuDivider()

                MenuTitle(stringResource(R.string.data))
                DropdownMenuItem(
                    leadingIcon = {
                        Icon(
                            imageVector = Icons.Filled.Inbox,
                            contentDescription = stringResource(R.string.import_DB),
                            tint = MaterialTheme.colorScheme.onPrimary
                        )
                    },
                    text = { MenuText(text = stringResource(R.string.import_DB)) },
                    onClick = { /* Action to import database */ },
                )
                DropdownMenuItem(
                    leadingIcon =  {
                        Icon(
                            imageVector = Icons.Filled.Outbox,
                            contentDescription = stringResource(R.string.export_DB),
                            tint = MaterialTheme.colorScheme.onPrimary
                        )
                    },
                    text = { MenuText(text = stringResource(R.string.export_DB)) },
                    onClick = { /* Action to export database */ },
                )
            }
        }
    }
}

@Composable
fun DropDownMenuBubbles(
    sellerOnClick : () -> Unit,
    dateOnClick : () -> Unit,
    ascendingOnClick: () -> Unit,
    descendingOnClick: () -> Unit
){
    var expanded by remember { mutableStateOf(false) }
    Box() {
        IconButton(onClick = {expanded = !expanded}) {
            Icon(Icons.Filled.FilterAlt, contentDescription = stringResource(R.string.filters), tint = MaterialTheme.colorScheme.onPrimary)
        }

        CustomDropDownMenu(
            expanded = expanded,
            onDismissRequest = {expanded = false},
            height = 250.dp
        ) {
            Column(
                modifier = Modifier.padding(end = 20.dp, start = 20.dp),
            ) {
                DropdownMenuItem(
                    text = {MenuText(text = stringResource(R.string.seller))},
                    onClick = {sellerOnClick()}
                )
                DropdownMenuItem(
                    text = {MenuText(text = stringResource(R.string.date))},
                    onClick = {dateOnClick()}
                )
                MenuDivider()
                Order(onClickAscending = {ascendingOnClick()}, onClickDescending = {descendingOnClick()})
            }
        }
    }
}

@Composable
fun DropDownMenuCleaning(){
    var expanded by remember { mutableStateOf(false) }
    Box() {
        IconButton(onClick = { expanded = !expanded }) {
            Icon(Icons.Filled.FilterAlt, contentDescription = stringResource(R.string.filters), tint = MaterialTheme.colorScheme.onPrimary)
        }

        CustomDropDownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false }
        ) {
            Column(
                modifier = Modifier.padding(end = 20.dp, start = 20.dp),
            ) {
                DropdownMenuItem(
                    text = { MenuText(text = stringResource(R.string.all)) },
                    onClick = { /* Query that select all bubbles */ }
                )
                DropdownMenuItem(
                    text = { MenuText(text = stringResource(R.string.address)) },
                    onClick = { /* Query that select all bubbles based on address */ }
                )
                DropdownMenuItem(
                    text = { MenuText(text = stringResource(R.string.date_added)) },
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
            Icon(Icons.Filled.FilterAlt, contentDescription = stringResource(R.string.filters), tint = MaterialTheme.colorScheme.onPrimary)
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
                    text = { MenuText(text = stringResource(R.string.all)) },
                    onClick = { /* Query that select all jobs */ }
                )
                DropdownMenuItem(
                    text = { MenuText(text = stringResource(R.string.j_completed)) },
                    onClick = { /* Query that select jobs taken */ }
                )
                DropdownMenuItem(
                    text = { MenuText(text = stringResource(R.string.schedule)) },
                    onClick = { /* Query that select jobs to be planned */ }
                )

                MenuDivider()

                MenuTitle(stringResource(R.string.type))
                DropdownMenuItem(
                    leadingIcon = { Icon(Icons.Filled.Lightbulb,contentDescription = stringResource(R.string.eletric),tint = MaterialTheme.colorScheme.onPrimary) },
                    text = { MenuText(text = stringResource(R.string.eletric)) },
                    onClick = { /* Query that select jobs on eletric */ }
                )
                DropdownMenuItem(
                    leadingIcon = {Icon(Icons.Filled.Air,contentDescription = stringResource(R.string.air_conditioning),tint = MaterialTheme.colorScheme.onPrimary) },
                    text = { MenuText(text = stringResource(R.string.air_conditioning)) },
                    onClick = { /* Query that select jobs on air conditioning */ }
                )
                DropdownMenuItem(
                    leadingIcon = { Icon(Icons.Filled.Campaign,contentDescription = stringResource(R.string.alarm),tint = MaterialTheme.colorScheme.onPrimary) },
                    text = { MenuText(text = stringResource(R.string.alarm)) },
                    onClick = { /* Query that select jobs on air alarm */ }
                )

                MenuDivider()

                Order(onClickAscending = {}, onClickDescending = {})
            }
        }
    }
}

@Composable
fun DropDownMenuCustomers(
    onClickAscending : () -> Unit,
    onClickDescending : () -> Unit,
    onClickAllCustomer : () -> Unit,
    onClickReference : () -> Unit,
    onClickAirCondition : () -> Unit,
    onClickElectric : () -> Unit,
    onClickAlarm : () -> Unit
){
    var expanded by remember { mutableStateOf(false) }

    Box(){
        IconButton( onClick = {expanded = !expanded} ) {
            Icon(Icons.Filled.FilterAlt, stringResource(R.string.filters), tint = MaterialTheme.colorScheme.onPrimary)
        }

        CustomDropDownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false },
            height = 450.dp
        ) {
            Column(modifier = Modifier.padding(start = 20.dp, end = 20.dp)) {
                DropdownMenuItem(
                    text = { MenuText(text = stringResource(R.string.all)) },
                    onClick = onClickAllCustomer
                )
                DropdownMenuItem(
                    text = { MenuText(text = stringResource(R.string.reference)) },
                    onClick = onClickReference
                )

                MenuDivider()

                MenuTitle(stringResource(R.string.intervention))
                DropdownMenuItem(
                    leadingIcon = {
                        Icon(
                            imageVector = Icons.Filled.Air,
                            contentDescription = stringResource(R.string.air_conditioning),
                            tint = MaterialTheme.colorScheme.onPrimary
                        )
                    },
                    text = { MenuText(text = stringResource(R.string.air_conditioning)) },
                    onClick = onClickAirCondition
                )
                DropdownMenuItem(
                    leadingIcon = {
                        Icon(
                            imageVector = Icons.Filled.Lightbulb,
                            contentDescription = stringResource(R.string.eletric),
                            tint = MaterialTheme.colorScheme.onPrimary
                        )
                    },
                    text = { MenuText(text = stringResource(R.string.eletric)) },
                    onClick = onClickElectric
                )
                DropdownMenuItem(
                    leadingIcon = {
                        Icon(
                            imageVector = Icons.Filled.Campaign,
                            contentDescription = stringResource(R.string.alarm),
                            tint = MaterialTheme.colorScheme.onPrimary
                        )
                    },
                    text = { MenuText(stringResource(R.string.alarm)) },
                    onClick = onClickAlarm
                )

                MenuDivider()

                Order(onClickAscending = onClickAscending, onClickDescending = onClickDescending)
            }
        }
    }
}

@Composable
fun DropDownMenuPayments(){
    var expanded by remember { mutableStateOf(false) }

    Box(){
        IconButton(onClick = {expanded = !expanded}) {
            Icon(Icons.Filled.FilterAlt, contentDescription = stringResource(R.string.filters), tint = MaterialTheme.colorScheme.onPrimary)
        }

        CustomDropDownMenu(
            expanded = expanded,
            onDismissRequest = {expanded = false}
        ) {
            Column(modifier = Modifier.padding(start = 20.dp, end = 20.dp)) {
                Order(stringResource(R.string.amount), {}, {})

                MenuDivider()

                Order(stringResource(R.string.date_issue), {}, {})
            }
        }
    }
}

@Composable
fun DropDownMenuDeadlines(
    category : List<String>,
    sellers : List<String>
){
    var expanded by remember { mutableStateOf(false) }

    Box() {
        IconButton(onClick = { expanded = !expanded }) {
            Icon(
                Icons.Filled.FilterAlt,
                contentDescription = stringResource(R.string.filters),
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
                    text = { MenuText(text = stringResource(R.string.all)) },
                    onClick = { /* Query that select all payments */ }
                )

                MenuDivider()

                MenuTitle(stringResource(R.string.category))
                category.forEach { cat ->
                    DropdownMenuItem(
                        text = { MenuText(cat) },
                        onClick = { /* Query that filter payments by category */ }
                    )
                }

                MenuDivider()

                MenuTitle(stringResource(R.string.seller))
                sellers.forEach { seller ->
                    DropdownMenuItem(
                        text = { MenuText(seller) },
                        onClick = { /* Query that filter payments by sellers */ }
                    )
                }

                MenuDivider()
                Order(onClickDescending = {}, onClickAscending = {})
            }
        }
    }
}