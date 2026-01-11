package com.example.myapplication.ui.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Checkbox
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.myapplication.ui.NavigationRoute

@Composable
fun ListItemCheckbox(
    char : Char? = null,
    text : String,
    textDescription : String? = null,
    trailingText : String? = null,
    type: String? = "NONE",
    checked : Boolean,
    onCheckedChange : ((Boolean) -> Unit)? = null,
    onClick: () -> Unit
) {
    GenericCard(
        type = type.toString(),
        leadingContent = {
            if(char != null) {
                Avatar(
                    char = char,
                    textColor = checkColorAvatar(type.toString(), primary = true),
                    backgroundColor = checkColorAvatar(type.toString(), onPrimary = true)
                )
            }else{null}
        },
        textSpace = if(trailingText.isNullOrEmpty()){0.85f}else{0.65f},
        text = text,
        textDescription = textDescription,
        trailingContent = {
            if(trailingText.isNullOrEmpty()){
                Checkbox(
                    checked = checked,
                    onCheckedChange = onCheckedChange,
                    colors = checkboxColors(type.toString())
                )
            }else {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center
                ) {
                    Text(
                        modifier = Modifier.weight(0.15f),
                        text = trailingText
                    )
                    Checkbox(
                        checked = checked,
                        onCheckedChange = onCheckedChange,
                        colors = checkboxColors(type.toString())
                    )
                }
            }
        },
        onClick = onClick
    )
}

@Composable
fun CardItemAvatar(itemID : String, onclick : () -> Unit){
    GenericCard(
        text = itemID,
        leadingContent = {
            Column(modifier = Modifier.padding(top = 5.dp, start = 4.dp)) {
                Avatar(
                    itemID.get(0),
                    size = 30.dp,
                    backgroundColor = MaterialTheme.colorScheme.primary
                )
            }
        },
        onClick = onclick
    )
}

@Composable
fun CustomersCardsList(
    letters : List<Char>,
    customers : List<String>,
    navController : NavHostController
){
    LazyColumn (
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(10.dp),
    ){
        items(letters){letter ->
            Column(){
                Avatar(letter,
                    backgroundColor = checkColor(type = "NONE", primaryContainer = true),
                    textColor = MaterialTheme.colorScheme.onPrimaryContainer,
                    modifierBox = Modifier.padding(8.dp),
                    size = 40.dp
                )
                Column{
                    Spacer(Modifier.size(4.dp))
                    customers.filter{ it[0] == letter }.forEach{ contact ->
                        CardItemAvatar(contact){
                            navController.navigate(NavigationRoute.SingleCustomerSummary)
                        }
                        Spacer(Modifier.size(4.dp))
                    }
                }
            }
        }
        item{Spacer(Modifier.size(80.dp))}
    }
}