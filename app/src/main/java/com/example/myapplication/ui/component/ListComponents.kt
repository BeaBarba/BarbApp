package com.example.myapplication.ui.component

import android.content.Context
import android.content.Intent
import androidx.compose.foundation.background
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.calculateEndPadding
import androidx.compose.foundation.layout.calculateStartPadding
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Checkbox
import androidx.compose.material3.ListItem
import androidx.compose.material3.ListItemDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import com.example.myapplication.ui.screen.HomeActivity

@Composable
fun ListItemAvatar(itemID : String){
    ListItem(
        headlineContent = { Text (text = itemID, fontSize = MaterialTheme.typography.headlineSmall.fontSize) } ,
        modifier =  Modifier
            .height(60.dp)
            .clip(shape = RoundedCornerShape(20))
            .background(color = checkColor(primaryContainer = true))
            .fillMaxSize(),
        leadingContent = { Avatar(itemID.get(0)) },
        trailingContent = {
            Checkbox(
                checked = true,
                onCheckedChange = {},
                interactionSource = remember { MutableInteractionSource() }
            )
        },
        colors = ListItemDefaults.colors(
            containerColor = checkColor(primaryContainer = true),
            headlineColor = checkColor(onPrimaryContainer = true),
        ),
    )
}

@Composable
fun CardItemAvatar(itemID : String, onclick : () -> Unit){
    GenericCard(
        text = itemID,
        interactionSource = remember { MutableInteractionSource() },
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
fun CheckLazyList(items: List<String>, contentPadding : PaddingValues){
    LazyColumn (
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(
            start = contentPadding.calculateStartPadding(LayoutDirection.Ltr) + 8.dp,
            top = 0.dp,
            end = contentPadding.calculateEndPadding(LayoutDirection.Ltr) + 8.dp,
            bottom = contentPadding.calculateEndPadding(LayoutDirection.Ltr) + 90.dp
        ),
        verticalArrangement = Arrangement.spacedBy(10.dp),
    ){
        items(items) { item ->
            ListItemAvatar(item)
        }
    }
}

@Composable
fun CustomersCardsList(
    letters : List<Char>,
    customers : List<String>,
    context : Context,
    contentPadding : PaddingValues,
    type: String = "NONE"
){
    LazyColumn (
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(
            start = contentPadding.calculateStartPadding(LayoutDirection.Ltr) + 8.dp,
            end = contentPadding.calculateEndPadding(LayoutDirection.Ltr) + 8.dp,
            bottom = contentPadding.calculateEndPadding(LayoutDirection.Ltr) + 90.dp
        ),
        verticalArrangement = Arrangement.spacedBy(10.dp),
    ){
        items(letters){letter ->
            Column(){
                Avatar(letter,
                    backgroundColor = checkColor(type = type, primaryContainer = true),
                    textColor = MaterialTheme.colorScheme.onPrimaryContainer,
                    modifierBox = Modifier.padding(8.dp),
                    size = 40.dp
                )
                Column{
                    Spacer(Modifier.size(4.dp))
                    customers.filter{ it[0] == letter }.forEach{ contact ->
                        CardItemAvatar(contact){
                            val intent = Intent(context, HomeActivity::class.java)
                            context.startActivity(intent)
                        }
                        Spacer(Modifier.size(4.dp))
                    }
                }
            }
        }
        item{Spacer(Modifier.size(45.dp))}
    }
}