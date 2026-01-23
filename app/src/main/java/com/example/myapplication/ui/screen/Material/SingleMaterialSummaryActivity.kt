package com.example.myapplication.ui.screen.Material

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.calculateEndPadding
import androidx.compose.foundation.layout.calculateStartPadding
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
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
import com.example.myapplication.debug.prodotti
import com.example.myapplication.debug.provenienze
import com.example.myapplication.ui.component.BackButton
import com.example.myapplication.ui.component.CustomDivider
import com.example.myapplication.ui.component.Images
import com.example.myapplication.ui.component.KeyValueLabel
import com.example.myapplication.ui.NavigationRoute
import com.example.myapplication.ui.component.TitleLabel
import com.example.myapplication.ui.component.TopAppBar

@Composable
fun SingleMaterialSummaryActivity(
    navController: NavHostController
){
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            TopAppBar(id = stringResource(R.string.material),
                navigationIcon = {BackButton{navController.navigateUp()}},
                trailingIcon = {
                    IconButton(
                        onClick = {navController.navigate(NavigationRoute.MaterialAdd)},
                        colors = IconButtonDefaults.iconButtonColors(
                            contentColor = MaterialTheme.colorScheme.onPrimary
                        )
                    ){
                        Icon(
                            painter = painterResource(R.drawable.edit_square_24dp),
                            contentDescription = stringResource(R.string.edit)
                        )
                    }
                }
            )
        }
    ) { contentPadding ->
        var somma = 0
        provenienze.forEach{ p->
            val numero = p.quantita.filter { it.isDigit() }
            if(numero.isNotEmpty()){
                somma += numero.toInt()
            }
        }
        var year_installation : String? = null
        var type_machine = "Esterna"
        var type_prodoct = "ELE"
        LazyColumn(
            modifier = Modifier
                .padding(
                    top = contentPadding.calculateTopPadding() + 8.dp,
                    start = contentPadding.calculateStartPadding(LayoutDirection.Ltr) + 8.dp,
                    end = contentPadding.calculateEndPadding(LayoutDirection.Ltr) + 8.dp,
                    bottom = contentPadding.calculateBottomPadding()
                )
                .fillMaxSize()
        ) {
            item{
                KeyValueLabel(
                    title = stringResource(R.string.type),
                    description = prodotti.get(0).tipo
                )
            }
            item{Spacer(Modifier.size(8.dp))}
            item{
                KeyValueLabel(
                    title = stringResource(R.string.category),
                    description = "Interruttore"
                )
            }
            item{Spacer(Modifier.size(8.dp))}
            item{
                KeyValueLabel(
                    title = stringResource(R.string.brand),
                    description = "Vimar"
                )
            }
            item{Spacer(Modifier.size(8.dp))}
            item{
                KeyValueLabel(
                    title = stringResource(R.string.model),
                    description = "Plana"
                )
            }
            item{Spacer(Modifier.size(8.dp))}
            item{
                KeyValueLabel(
                    title = stringResource(R.string.total_q),
                    description = somma.toString()
                )
            }
            if(type_prodoct.equals("CDZ")){
                item{CustomDivider()}
                item{TitleLabel(stringResource(R.string.specifications))}
                item{Spacer(Modifier.size(8.dp))}
                item{
                    KeyValueLabel(
                        title = stringResource(R.string.serial_number),
                        description = "NM21ZP10UABC"
                    )
                }
                item{Spacer(Modifier.size(8.dp))}
                if(!year_installation.isNullOrEmpty()) {
                    item {
                        KeyValueLabel(
                            title = stringResource(R.string.year_installation),
                            description = "2010"
                        )
                    }
                    item { Spacer(Modifier.size(8.dp)) }
                }
                item{
                    KeyValueLabel(
                        title = stringResource(R.string.btu),
                        description = "9.000"
                    )
                }
                item{Spacer(Modifier.size(8.dp))}
                item{
                    KeyValueLabel(
                        title = stringResource(R.string.machine_type),
                        description = type_machine
                    )
                }
                item{Spacer(Modifier.size(8.dp))}
                if(type_machine.equals("Esterna")){
                    item{
                        KeyValueLabel(
                            title = stringResource(R.string.split_number),
                            description = "3"
                        )
                    }
                    item{Spacer(Modifier.size(8.dp))}
                    item{
                        KeyValueLabel(
                            title = stringResource(R.string.gas_quantity),
                            description = "1.5 kg"
                        )
                    }
                    item{Spacer(Modifier.size(8.dp))}
                    item{
                        KeyValueLabel(
                            title = stringResource(R.string.gas_type),
                            description = "R410"
                        )
                    }
                }
            }
            item{CustomDivider()}
            item{TitleLabel(stringResource(R.string.origin))}
            item{Spacer(Modifier.size(8.dp))}
            items(provenienze.subList(0,2)){ item ->
                KeyValueLabel(
                    title = stringResource(R.string.seller),
                    description = item.fornitore
                )
                Spacer(Modifier.size(8.dp))
                KeyValueLabel(
                    title = stringResource(R.string.quantity),
                    description = item.quantita
                )
                Spacer(Modifier.size(8.dp))
                /*
                if(!item.numeroBolla.isNullOrEmpty()) {
                    KeyValueLabel(
                        title = stringResource(R.string.bubble),
                        description = item.numeroBolla
                    )
                }

                 */
                if(!item.fattura.isNullOrEmpty()){
                    KeyValueLabel(
                        title = stringResource(R.string.invoice),
                        description = item.fattura
                    )
                }
                Spacer(Modifier.size(8.dp))
                CustomDivider()
            }
            item{Images()}
            item{Spacer(Modifier.size(8.dp))}
        }
    }
}