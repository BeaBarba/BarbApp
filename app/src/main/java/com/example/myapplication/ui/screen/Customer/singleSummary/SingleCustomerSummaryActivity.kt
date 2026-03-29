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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.myapplication.R
import com.example.myapplication.data.modules.JobType
import com.example.myapplication.ui.component.Avatar
import com.example.myapplication.ui.component.BackButton
import com.example.myapplication.ui.component.CustomDivider
import com.example.myapplication.ui.component.KeyValueLabel
import com.example.myapplication.ui.component.GenericCard
import com.example.myapplication.ui.NavigationRoute
import com.example.myapplication.ui.component.TitleLabel
import com.example.myapplication.ui.component.TopAppBar
import com.example.myapplication.ui.component.checkColor
import java.time.format.DateTimeFormatter

@Composable
fun SingleCustomerSummaryActivity(
    customerId : String,
    state : SingleCustomerSummaryState,
    actions : SingleCustomerSummaryActions,
    navController : NavHostController
){
    LaunchedEffect(customerId) {
        actions.populateCustomerData(customerId)
    }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            TopAppBar(
                id = stringResource(R.string.customers),
                navigationIcon = {BackButton{navController.navigateUp()} },
                trailingIcon = {
                    IconButton(
                        onClick = {
                            navController.navigate(NavigationRoute.CustomerAdd(customerId))
                        }
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
            item{
                KeyValueLabel(
                    title = stringResource(R.string.id),
                    description = state.customerData?.customer?.cf ?: "",
                    weightTitle = 1.2f
                )
            }
            item{Spacer(Modifier.size(8.dp))}
            item{
                KeyValueLabel(
                    title = stringResource(R.string.name),
                    description = state.customerData?.customer?.name ?: "",
                    weightTitle =  1.2f
                )
            }
            item{Spacer(Modifier.size(8.dp))}
            if(state.customerData?.privateCustomer != null) {
                item{
                    KeyValueLabel(
                        title = stringResource(R.string.last_name),
                        description = state.customerData.privateCustomer.lastName,
                        weightTitle =  1.2f
                    )
                }
                item{Spacer(Modifier.size(8.dp))}
                item{
                    KeyValueLabel(
                        title = stringResource(R.string.place_birth),
                        description = state.customerData.privateCustomer.placeBirth,
                        weightTitle = 1.2f
                    )
                }
                item{Spacer(Modifier.size(8.dp))}
                item{
                    KeyValueLabel(
                        title = stringResource(R.string.date_birth),
                        description = state.customerData.privateCustomer.dateBirth.toString(),
                        weightTitle =  1.2f
                    )
                }
            }
            if(state.customerData?.companyCustomer != null){
                item{
                    KeyValueLabel(
                        title = stringResource(R.string.company_name),
                        description = state.customerData.companyCustomer.companyName,
                        weightTitle =  1.2f
                    )
                }
                item{Spacer(Modifier.size(8.dp))}
                item{
                    KeyValueLabel(
                        title = stringResource(R.string.unique_code),
                        description = state.customerData.companyCustomer.uniqueCode,
                        weightTitle =  1.2f
                    )
                }
                item{Spacer(Modifier.size(8.dp))}
                item{
                    KeyValueLabel(
                        title = stringResource(R.string.vat_number),
                        description = state.customerData.companyCustomer.vatNumber,
                        weightTitle =  1.2f
                    )
                }
            }
            item{CustomDivider()}
            item{TitleLabel(stringResource(R.string.contact))}
            item{Spacer(Modifier.size(8.dp))}
            item{
                KeyValueLabel(
                    title = stringResource(R.string.email),
                    description = state.customerData?.customer?.mail ?: "",
                    weightTitle =  1.2f
                )
            }
            item{Spacer(Modifier.size(8.dp))}
            item{
                KeyValueLabel(
                    title = stringResource(R.string.phone),
                    description = state.customerData?.phoneNumber?.number ?: "",
                    weightTitle =  1.2f
                )
            }
            item{CustomDivider()}
            item{
                TitleLabel(
                    title =
                        if(state.customerData?.privateCustomer != null){
                            stringResource(R.string.residence)
                        }else{stringResource(R.string.address)}
                )
            }
            item{Spacer(Modifier.size(8.dp))}
            item{
                KeyValueLabel(
                    title = stringResource(R.string.address),
                    description = state.customerData?.address?.address ?: "",
                    weightTitle =  1.2f
                )
            }
            item{Spacer(Modifier.size(8.dp))}
            item{
                KeyValueLabel(
                    title = stringResource(R.string.house_number),
                    description = state.customerData?.address?.houseNumber ?: "",
                    weightTitle =  1.2f
                )
            }
            item{Spacer(Modifier.size(8.dp))}
            item{
                KeyValueLabel(
                    title = stringResource(R.string.municipality),
                    description = state.customerData?.address?.municipality ?: "",
                    weightTitle =  1.2f
                )
            }
            item{Spacer(Modifier.size(8.dp))}
            item{
                KeyValueLabel(
                    title = stringResource(R.string.city),
                    description = state.customerData?.address?.city ?: "",
                    weightTitle =  1.2f
                )
            }
            item{Spacer(Modifier.size(8.dp))}
            item{
                KeyValueLabel(
                    title = stringResource(R.string.province),
                    description = state.customerData?.address?.province ?: "",
                    weightTitle =  1.2f
                )
            }
            item{Spacer(Modifier.size(8.dp))}
            item{
                KeyValueLabel(
                    title = stringResource(R.string.postal_code),
                    description = state.customerData?.address?.zip ?: "",
                    weightTitle =  1.2f
                )
            }

            if(state.customerData?.reference != null || state.customerData?.referral != null){
                item{CustomDivider()}
                item{TitleLabel(stringResource(R.string.reference))}
                item{Spacer(Modifier.size(8.dp))}
                val description =
                    when{
                        state.customerData.reference != null -> {"${state.customerData.reference.lastName} ${state.customerData.reference.name}"}
                        state.customerData.referral != null ->{
                            if(state.customerData.referral.presenter.isCompany){
                                "${state.customerData.referral.presenter.companyCustomer?.companyName}"
                            }else{
                                "${state.customerData.referral.presenter.privateCustomer?.lastName} ${state.customerData.referral.presenter.customer.name}"
                            }
                        }
                    else ->""
                    }
                item{
                    KeyValueLabel(
                        title = stringResource(R.string.reference),
                        description =  description,
                        weightTitle =  1.2f
                    )
                }
            }

            if(state.customerData?.jobs?.isNotEmpty() == true){
                item{CustomDivider()}
                item{TitleLabel(stringResource(R.string.interventions))}
                item{Spacer(Modifier.size(8.dp))}
                items(state.customerData.jobs) { item ->
                    val typeJob =
                        if (item.job.airConditioning) {
                            JobType.CDZ.toString()
                        } else if (item.job.alarm) {
                            JobType.ALA.toString()
                        } else {
                            JobType.ELE.toString()
                        }

                    GenericCard(
                        type = typeJob,
                        leadingContent = {
                            Avatar(
                                char = typeJob[0],
                                type = typeJob
                            )
                        },
                        text = "${item.address.address} ${item.address.houseNumber} - ${item.address.city} (${item.address.province})",
                        textDescription = item.job.date.format(DateTimeFormatter.ofPattern("dd/MM/yyyy")),
                        trailingContent = {
                            IconButton(
                                onClick = { navController.navigate(NavigationRoute.JobAdd(item.job.id)) }
                            ) {
                                Icon(
                                    imageVector = Icons.Filled.Mode,
                                    contentDescription = stringResource(R.string.edit),
                                    tint = checkColor(typeJob, onPrimaryContainer = true)
                                )
                            }
                        },
                        onClick = { navController.navigate(NavigationRoute.SingleJobSummary(item.job.id)) }
                    )
                    Spacer(Modifier.size(8.dp))
                }
            }
        }
    }
}