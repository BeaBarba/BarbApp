package com.example.myapplication.ui.screen

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.calculateEndPadding
import androidx.compose.foundation.layout.calculateStartPadding
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.ChevronRight
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import com.example.myapplication.ui.component.Avatar
import com.example.myapplication.ui.component.BackButton
import com.example.myapplication.ui.component.CustomOutlineTextField
import com.example.myapplication.ui.component.DatePickerFieldToModal
import com.example.myapplication.ui.component.DeleteButton
import com.example.myapplication.ui.component.GenericCard
import com.example.myapplication.ui.component.ListItemCheckbox
import com.example.myapplication.ui.component.SplitButtonList
import com.example.myapplication.ui.component.TopAppBar
import com.example.myapplication.ui.theme.MyApplicationTheme

@Composable
fun PaymentAddActivity(){
    /*
}
class PaymentAddActivity : ComponentActivity(){
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent{
            MyApplicationTheme {

     */
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
                                ) {
                                    Icon(Icons.Filled.Check, contentDescription = "Save")
                                }
                            }
                        )
                    }
                ){ contentPadding ->
                    var showItems by remember{ mutableStateOf(false) }
                    LazyColumn(
                        modifier = Modifier
                            .padding(
                                top = contentPadding.calculateTopPadding() + 8.dp,
                                start = contentPadding.calculateStartPadding(LayoutDirection.Ltr) + 8.dp,
                                end = contentPadding.calculateEndPadding(LayoutDirection.Ltr) + 8.dp,
                                bottom = contentPadding.calculateBottomPadding()
                            )
                    ) {
                        item{
                            GenericCard(
                            leadingContent = {
                                Avatar(char = pagamenti[0].cliente.get(0))
                            },
                            text = pagamenti[0].cliente,
                            trailingContent = {
                                Icon(Icons.Filled.ChevronRight, contentDescription = "Edit Customer")
                            }
                        )}
                        item{Spacer(Modifier.size(8.dp))}
                        item{CustomOutlineTextField("Prezzo")}
                        item{Spacer(Modifier.size(8.dp))}
                        item{DatePickerFieldToModal("Data Riscossione")}
                        item{Spacer(Modifier.size(8.dp))}
                        item{SplitButtonList(text = "Interventi", showItems = showItems, onClick = {showItems = !showItems})}
                        item{Spacer(Modifier.size(8.dp))}
                        if(showItems) {
                            items(interventi){item ->
                                var checked by remember { mutableStateOf(false) }
                                ListItemCheckbox(
                                    char = item.tipo.get(0),
                                    text = item.indirizzo + ", " + item.comune,
                                    textDescription = item.data,
                                    checked = checked,
                                    onCheckedChange = {checked = !checked},
                                    type = item.tipo,
                                    onClick = {}
                                )
                                Spacer(Modifier.size(8.dp))
                            }
                        }
                        item{Spacer(Modifier.size(8.dp))}
                        item{DeleteButton {  }}
                    }

                }
            }
        /*}
    }
}

         */