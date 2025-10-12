package com.example.myapplication.ui.component

import android.content.Context
import android.content.Intent
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.calculateEndPadding
import androidx.compose.foundation.layout.calculateStartPadding
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.Checkbox
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.ListItem
import androidx.compose.material3.ListItemDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import com.example.myapplication.R
import com.example.myapplication.ui.screen.HomeActivity
import com.example.myapplication.ui.screen.itemsList

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopAppBar(id: String, navigationIcon: @Composable () -> Unit, action: @Composable () -> Unit){
    CenterAlignedTopAppBar(
        navigationIcon = navigationIcon,
        title = {
            Text(
                text = id,
                fontWeight = FontWeight.Medium,
                color = MaterialTheme.colorScheme.onPrimary
            ) },
        colors = TopAppBarDefaults.topAppBarColors(
        containerColor = MaterialTheme.colorScheme.primary
        ),
        actions = { action()
           /*IconButton(
                onClick = {},
            ) {
                when{
                    id.equals("Home") ->
                        Icon(
                            Icons.Filled.ShoppingCart,
                            "Back",
                            tint = MaterialTheme.colorScheme.onPrimary
                        )
                    id.equals("Customers")
                            || id.equals("Wrench")
                            || id.equals("Cleaning")
                            || id.equals("Receipt") ->
                        Icon(
                            painterResource(id=R.drawable.filter_alt_24dp),
                            "Filter",
                            tint = MaterialTheme.colorScheme.onPrimary
                        )
                    id.equals("ItemA") ->
                        Icon(
                            painterResource(id= R.drawable.edit_square_24dp),
                            "Edit",
                            tint = MaterialTheme.colorScheme.onPrimary
                        )
                    id.equals("Check") ->
                        Icon(Icons.Filled.Check,
                            "Check",
                            tint = MaterialTheme.colorScheme.onPrimary)
                }
            }*/
        }
    )
}

@Composable
fun AddButton(id : String, onClick: () -> Unit){
    FloatingActionButton(
        onClick = onClick,
        modifier = Modifier.size(60.dp).fillMaxSize(),
        containerColor = MaterialTheme.colorScheme.primary,
        contentColor = MaterialTheme.colorScheme.onPrimary,
        interactionSource = remember { MutableInteractionSource() } // Per le interazioni
    ) {
        Icon(Icons.Filled.Add, "Add item")
    }
}

@Composable
fun DeleteButton(id: String, onclick: () -> Unit){
    IconButton(
        onClick = onclick,
        modifier = Modifier
            .border( border = BorderStroke(4.dp, color = MaterialTheme.colorScheme.background),
                    shape = RoundedCornerShape(30.dp))
            .fillMaxWidth()
            .height(60.dp),
        enabled =   true,
        colors = IconButtonDefaults.iconButtonColors(MaterialTheme.colorScheme.secondary)
    ) {
        Row{
           Icon(
               painter = painterResource(R.drawable.delete_24dp),
               contentDescription = "Delete",
               tint = MaterialTheme.colorScheme.onSecondary
           )
            Spacer(Modifier.size(8.dp))
            Text(
                text = "Elimina",
                color = MaterialTheme.colorScheme.onSecondary
            )
        }
    }
}

@Composable
fun Avatar(
    char: Char,
    modifierBox: Modifier = Modifier,
    textColor : Color =  MaterialTheme.colorScheme.onPrimary,
    size : Dp = 35.dp,
    shape: Shape = CircleShape,
    backgroundColor: Color = MaterialTheme.colorScheme.primary,
){
    Box(
        modifier = modifierBox
            .size(size)
            .clip(shape)
            .background(backgroundColor),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = char.toString(),
            color = textColor,
            fontSize = MaterialTheme.typography.bodyLarge.fontSize
        )
    }
}

@Composable
fun HomeCard(item : String, iconName : Painter, onclick: () -> Unit) {
    Card(
        onClick = onclick,
        modifier = Modifier
            .size(150.dp)
            .fillMaxSize(),
        border = BorderStroke(16.dp, MaterialTheme.colorScheme.background),
        shape = RoundedCornerShape(40.dp),
        colors = CardColors(
            contentColor = MaterialTheme.colorScheme.onPrimaryContainer,
            containerColor = MaterialTheme.colorScheme.primaryContainer.copy(alpha = 0.5f),
            disabledContentColor = MaterialTheme.colorScheme.onPrimaryContainer,
            disabledContainerColor = MaterialTheme.colorScheme.primaryContainer
        ),
        interactionSource = remember { MutableInteractionSource() } // Per le interazioni con l'utente
    ) {
        Column(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Icon(
                painter = iconName,
                contentDescription = item,
                modifier = Modifier
                    .size(60.dp)
                    .padding(2.dp),
                tint = MaterialTheme.colorScheme.onPrimaryContainer,
            )
            Spacer(Modifier.size(8.dp))
            Text(
                text = item,
                color = MaterialTheme.colorScheme.onPrimaryContainer
            )
        }
    }
}

@Composable
fun ListItemAvatar(itemID : String){
    ListItem(
        headlineContent = { Text (text = itemID, fontSize = MaterialTheme.typography.headlineSmall.fontSize) } ,
        modifier =  Modifier
            .height(60.dp)
            .clip(shape = RoundedCornerShape(20))
            .background(color = MaterialTheme.colorScheme.primaryContainer)
            .fillMaxSize(),
        leadingContent = { Avatar(itemID.get(0)) },
        trailingContent = {
            Checkbox(
                checked = true,
                onCheckedChange = {},
                interactionSource = remember { MutableInteractionSource() }
            )},
        colors = ListItemDefaults.colors(
            containerColor = MaterialTheme.colorScheme.primaryContainer,
            headlineColor = MaterialTheme.colorScheme.onPrimaryContainer,
        ),
    )
}

@Composable
fun CardItemAvatar(itemID : String, onclick : () -> Unit){
    Card (
        modifier = Modifier
            .height(60.dp)
            .padding(horizontal = 4.dp)
            .fillMaxSize(),
        colors = CardColors(
            contentColor = MaterialTheme.colorScheme.onPrimaryContainer,
            containerColor = MaterialTheme.colorScheme.primaryContainer,
            disabledContentColor = MaterialTheme.colorScheme.onPrimaryContainer,
            disabledContainerColor = MaterialTheme.colorScheme.primaryContainer
        ),
        onClick = onclick,
        interactionSource = remember { MutableInteractionSource() }
    ){
        Row (
            modifier = Modifier.padding(vertical = 15.dp, horizontal = 8.dp),
            verticalAlignment = Alignment.CenterVertically,
        ){
            Avatar(itemID.get(0), size =30.dp, backgroundColor = MaterialTheme.colorScheme.primary)

            Text(
                modifier = Modifier.padding(start = 8.dp),
                text = itemID,
                color = MaterialTheme.colorScheme.onPrimaryContainer,
                fontSize = MaterialTheme.typography.headlineSmall.fontSize
            )
        }
    }
}

@Composable
fun ToggleIconButton(checked : Boolean, onCheckedChange: (Boolean) -> Unit) {
    val rotation by animateFloatAsState(
        targetValue = if (checked) 180f else 0f,
        label = "Trailing Icon Rotation"
    )

    IconButton(
        onClick = {
            onCheckedChange(!checked)
        }
    ) {
        Icon(
            imageVector = Icons.Filled.KeyboardArrowDown,
            modifier = Modifier.graphicsLayer {
                this.rotationZ = rotation
            },
            contentDescription = if (checked) "Ritira" else "Espandi"
        )
    }
}

@Composable
fun CustomDropDownMenu(
    expanded: Boolean,
    onDismissRequest: () -> Unit,
    content: @Composable () -> Unit
) {
    DropdownMenu(
        expanded = expanded,
        onDismissRequest = onDismissRequest,
        modifier = Modifier
            .height(300.dp)
            .border(1.dp, MaterialTheme.colorScheme.onPrimary, RoundedCornerShape(15.dp))
            .background(MaterialTheme.colorScheme.primary),
        shape = RoundedCornerShape(15.dp)
    ) {
        content()
    }
}

@Composable
fun SplitButtonMenu(
    content: String,
    items : List<String> = itemsList,
    backgroundColor: Color = MaterialTheme.colorScheme.primaryContainer,
    textColor: Color = MaterialTheme.colorScheme.onPrimaryContainer,
    contextPadding : PaddingValues,
    colorTextMenu: Color = MaterialTheme.colorScheme.onPrimary
){
    var checked by remember { mutableStateOf(false) }
    Row(
        modifier = Modifier
            .padding(end = contextPadding.calculateEndPadding(LayoutDirection.Ltr), start = contextPadding.calculateStartPadding(LayoutDirection.Ltr))
            .padding(horizontal = 8.dp)
            .padding(bottom = 8.dp)
            .height(60.dp)
            .fillMaxSize()
            .clip(CircleShape),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ){
        Box(
            modifier = Modifier
                .fillMaxWidth(0.75f)
                .fillMaxHeight()
                .clip(RoundedCornerShape(topStart = 45.dp, bottomStart = 45.dp))
                .background(backgroundColor)
                .padding(12.dp),
        ) {
            Row(
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ){
                Avatar(content.get(0))
                Spacer(Modifier.size(8.dp))
                Text(
                    text = content.toString(),
                    color = textColor,
                    fontSize = MaterialTheme.typography.headlineSmall.fontSize
                )
            }
        }
        Spacer(Modifier.size(2.dp))
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(60.dp)
                .clip(RoundedCornerShape(topStart = 0.dp, bottomStart = 0.dp))
                .background(backgroundColor),
            contentAlignment = Alignment.Center
        ){
            ToggleIconButton(checked, { checked = it })
            CustomDropDownMenu(checked, {checked = false}){
                Column(
                    modifier = Modifier.padding(end = 20.dp, start = 20.dp)
                ) {
                    items.forEach { item ->
                        DropdownMenuItem(
                            text = {
                                Text(
                                    text = item.toString(),
                                    color = colorTextMenu,
                                    fontSize = MaterialTheme.typography.bodyLarge.fontSize
                                )
                            },
                            onClick = {/* Change name split button */ },
                        )
                    }
                }
            }
        }
    }
}

//----------------------------------- Composables --------------------
@Composable
fun CheckLazyList(items: List<String>, contextPadding : PaddingValues){
    LazyColumn (
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(
            start = contextPadding.calculateStartPadding(LayoutDirection.Ltr) + 8.dp,
            top = 0.dp,
            end = contextPadding.calculateEndPadding(LayoutDirection.Ltr) + 8.dp,
            bottom = contextPadding.calculateEndPadding(LayoutDirection.Ltr) + 90.dp
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
    contextPadding : PaddingValues){
    LazyColumn (
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(
            start = contextPadding.calculateStartPadding(LayoutDirection.Ltr) + 8.dp,
            top = 0.dp,
            end = contextPadding.calculateEndPadding(LayoutDirection.Ltr) + 8.dp,
            bottom = contextPadding.calculateEndPadding(LayoutDirection.Ltr) + 90.dp
        ),
        verticalArrangement = Arrangement.spacedBy(10.dp),
    ){
        items(letters){letter ->
            Column(){
                Avatar(letter,
                    backgroundColor = MaterialTheme.colorScheme.primaryContainer,
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
    }
}