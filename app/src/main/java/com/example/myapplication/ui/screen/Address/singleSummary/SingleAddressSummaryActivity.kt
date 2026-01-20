package com.example.myapplication.ui.screen.Address.singleSummary

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.calculateEndPadding
import androidx.compose.foundation.layout.calculateStartPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.myapplication.R
import com.example.myapplication.ui.NavigationRoute
import com.example.myapplication.ui.component.BackButton
import com.example.myapplication.ui.component.DoubleKeyValueLabel
import com.example.myapplication.ui.component.KeyValueLabel
import com.example.myapplication.ui.component.TitleLabel
import com.example.myapplication.ui.component.TopAppBar

@Composable
fun SingleAddressSummaryActivity(
    addressId: Int?,
    state: SingleAddressSummaryState,
    actions: SingleAddressSummaryActions,
    navController: NavHostController
){

    actions.populateFromId(addressId)
    Scaffold(
        topBar = {
            TopAppBar(
                navigationIcon = {BackButton{navController.navigateUp()}},
                id = stringResource(R.string.address),
                trailingIcon = {
                    IconButton(
                        onClick = {navController.navigate(NavigationRoute.AddressAdd(state.addressId))},
                        colors = IconButtonDefaults.iconButtonColors(
                            contentColor = MaterialTheme.colorScheme.onPrimary
                        )
                    ) {
                        Icon(
                            painter = painterResource(R.drawable.edit_square_24dp),
                            contentDescription = stringResource(R.string.edit)
                        )
                    }
                }
            )
        }
    ) { contentPadding ->
        val ctx = LocalContext.current
        LazyColumn(
            modifier = Modifier
                .padding(
                    start = contentPadding.calculateStartPadding(LayoutDirection.Ltr) + 8.dp,
                    end = contentPadding.calculateEndPadding(LayoutDirection.Ltr) + 8.dp,
                    top = contentPadding.calculateTopPadding() + 8.dp,
                    bottom = contentPadding.calculateBottomPadding()
                )
        ) {
            item{
                KeyValueLabel(
                    title = stringResource(R.string.address),
                    description = state.address,
                    onClick = {
                        val location = Uri.parse("geo: 44.1391, 12.24315")
                        // Viene settato l'intent implicito con la proprietà
                        val intent = Intent(Intent.ACTION_VIEW).apply { data = location }
                        if(intent.resolveActivity(ctx.packageManager)!= null) {
                            ctx.startActivity(intent)
                        }
                    }
                )
            }
            item{Spacer(Modifier.size(8.dp))}
            item{
                KeyValueLabel(
                    title = stringResource(R.string.municipality),
                    description = state.municipality,
                )
            }
            item{Spacer(Modifier.size(8.dp))}
            item{
                KeyValueLabel(
                    title = stringResource(R.string.city),
                    description = state.city,
                )
            }
            item{Spacer(Modifier.size(8.dp))}
            item{
                DoubleKeyValueLabel(
                    firstTitle = stringResource(R.string.province),
                    firstDescription = state.province,
                    secondTitle = stringResource(R.string.postal_code),
                    secondDescription = state.zip
                )
            }
            item{Spacer(Modifier.size(8.dp))}

            item{TitleLabel(stringResource(R.string.address_details))}
            item{Spacer(Modifier.size(8.dp))}
            item{
                DoubleKeyValueLabel(
                    firstTitle = stringResource(R.string.floor),
                    firstDescription = "P" + state.floor,
                    secondTitle = stringResource(R.string.staircase),
                    secondDescription = state.staircase
                )
            }
            item{Spacer(Modifier.size(8.dp))}
            item{
                KeyValueLabel(
                    title = stringResource(R.string.interior),
                    description = state.interior
                )
            }
            item{Spacer(Modifier.size(8.dp))}
            item{
                KeyValueLabel(
                    title = stringResource(R.string.real_estate_units),
                    description = state.units,
                    weightTitle = 2.0f
                )
            }
            item{Spacer(Modifier.size(8.dp))}

            item{TitleLabel(stringResource(R.string.property_details))}
            item{Spacer(Modifier.size(8.dp))}
            item{
                KeyValueLabel(
                    title = stringResource(R.string.sheet),
                    description = state.sheet
                )
            }
            item{Spacer(Modifier.size(8.dp))}
            item{
                KeyValueLabel(
                    title = stringResource(R.string.property_map),
                    description = state.map
                )
            }
            item{Spacer(Modifier.size(8.dp))}
            item{
                KeyValueLabel(
                    title = stringResource(R.string.subordinate),
                    description = state.subordinate
                )
            }
            item{Spacer(Modifier.size(8.dp))}
            item{
                KeyValueLabel(
                    title = stringResource(R.string.usable_area),
                    description = state.usableArea + " mq"
                )
            }
            item{Spacer(Modifier.size(8.dp))}
            item{
                KeyValueLabel(
                    title = stringResource(R.string.year_construction),
                    description = state.yearOfConstruction,
                    weightTitle = 2.0f,
                )
            }
        }
    }
}