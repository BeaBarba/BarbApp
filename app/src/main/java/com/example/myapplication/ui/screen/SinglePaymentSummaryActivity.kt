package com.example.myapplication.ui.screen

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Spacer
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import com.example.myapplication.ui.component.BackButton
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import com.example.myapplication.R
import com.example.myapplication.ui.component.Avatar
import com.example.myapplication.ui.component.DoubleKeyValueLabel
import com.example.myapplication.ui.component.GenericCard
import com.example.myapplication.ui.component.SplitButtonList
import com.example.myapplication.ui.component.TopAppBar
import com.example.myapplication.ui.component.checkColorAvatar
import com.example.myapplication.ui.theme.MyApplicationTheme

class SinglePaymentSummaryActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState : Bundle?){
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent{
            MyApplicationTheme {
                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    topBar = {
                        TopAppBar(
                            id = "Pagamento",
                            navigationIcon = { BackButton {  }},
                            trailingIcon = {
                                IconButton(
                                    onClick = {},
                                    colors = IconButtonDefaults.iconButtonColors(
                                        contentColor = MaterialTheme.colorScheme.onPrimary
                                    )
                                ){
                                    Icon(painter = painterResource(R.drawable.edit_square_24dp), contentDescription = "Edit")
                                }
                            }
                        )
                    }
                ){ contentPadding ->
                    var showItems by remember{mutableStateOf(false)}
                    LazyColumn(
                        modifier = Modifier
                            .padding(
                                top = contentPadding.calculateTopPadding() + 8.dp,
                                start = contentPadding.calculateRightPadding(LayoutDirection.Ltr) + 8.dp,
                                end = contentPadding.calculateLeftPadding(LayoutDirection.Ltr) + 8.dp,
                                bottom = contentPadding.calculateBottomPadding()
                            )
                    ){
                        item{
                            GenericCard(
                                leadingContent = {Avatar(char = pagamenti[0].cliente.get(0))},
                                text = pagamenti[0].cliente,
                            )
                        }
                        item{Spacer(Modifier.size(8.dp))}
                        item{
                            DoubleKeyValueLabel(
                                firstTitle = "Prezzo",
                                firstDescription = pagamenti[0].prezzo,
                                secondTitle = "Data",
                                secondDescription = pagamenti[0].data
                            )
                        }
                        item{Spacer(Modifier.size(8.dp))}
                        item{SplitButtonList(text = "Interventi", showItems = showItems, onClick = {showItems = !showItems})}
                        item{Spacer(Modifier.size(8.dp))}
                        if(showItems) {
                            items(interventi){item ->
                                GenericCard(
                                    type = item.tipo,
                                    leadingContent = {
                                        Avatar(
                                            char = item.tipo.get(0),
                                            textColor = checkColorAvatar(item.tipo, primary = true),
                                            backgroundColor = checkColorAvatar(
                                                item.tipo,
                                                onPrimary = true
                                            )
                                        )
                                    },
                                    text = item.indirizzo + ", " + item.comune,
                                    textDescription = item.data,
                                    trailingContent = {
                                        Text(text = item.prezzo.toString())
                                    }
                                )
                                Spacer(Modifier.size(8.dp))
                            }
                        }
                    }
                }
            }
        }
    }
}