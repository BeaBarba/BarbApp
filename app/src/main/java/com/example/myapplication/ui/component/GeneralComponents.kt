package com.example.myapplication.ui.component

import android.content.Context
import android.content.Intent
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonShapes
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.Checkbox
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExperimentalMaterial3ExpressiveApi
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.ListItem
import androidx.compose.material3.ListItemDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
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
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.DialogProperties
import com.example.myapplication.R
import com.example.myapplication.ui.screen.HomeActivity
import com.example.myapplication.ui.screen.itemsList

@Composable
fun checkColor(
    type: String,
    primary: Boolean = false,
    onPrimary: Boolean = false,
    primaryContainer: Boolean = false,
    onPrimaryContainer: Boolean = false
): Color {
    return when (type) {
        "ALA" -> when {
            primary -> MaterialTheme.colorScheme.secondary
            onPrimary -> MaterialTheme.colorScheme.onSecondary
            primaryContainer -> MaterialTheme.colorScheme.secondaryContainer
            else -> MaterialTheme.colorScheme.onSecondaryContainer
        }
        "ELE" -> when{
            primary -> MaterialTheme.colorScheme.tertiary
            onPrimary -> MaterialTheme.colorScheme.onTertiary
            primaryContainer -> MaterialTheme.colorScheme.tertiaryContainer
            else -> MaterialTheme.colorScheme.onTertiaryContainer
        }
        "CDZ" -> when{
            primary -> MaterialTheme.colorScheme.surface
            onPrimary -> MaterialTheme.colorScheme.onSurface
            primaryContainer -> MaterialTheme.colorScheme.surfaceVariant
            else -> MaterialTheme.colorScheme.onSurfaceVariant
        }
        "NONE" -> when{
            primary -> MaterialTheme.colorScheme.primary
            onPrimary -> MaterialTheme.colorScheme.onPrimary
            primaryContainer -> MaterialTheme.colorScheme.primaryContainer
            else -> MaterialTheme.colorScheme.onPrimaryContainer
        }
        else -> throw IllegalArgumentException("Tipo non supportato")
    }
}

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
        actions = { action() }
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
fun DeleteButton(onclick: () -> Unit, contexPadding: PaddingValues){
    IconButton(
        onClick = onclick,
        modifier = Modifier
            .padding(
                start = contexPadding.calculateStartPadding(LayoutDirection.Ltr),
                end = contexPadding.calculateEndPadding(LayoutDirection.Ltr))
            .fillMaxWidth()
            .height(60.dp),
        enabled =   true,
        colors = IconButtonDefaults.iconButtonColors(MaterialTheme.colorScheme.secondary)
    ) {
        Row (
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ){
           Icon(
               painter = painterResource(R.drawable.delete_24dp),
               contentDescription = "Delete",
               tint = MaterialTheme.colorScheme.onSecondary,
               modifier = Modifier.height(30.dp).width(30.dp)
           )
            Spacer(Modifier.size(8.dp))
            Text(
                text = "Elimina",
                color = MaterialTheme.colorScheme.onSecondary,
                fontSize = MaterialTheme.typography.headlineSmall.fontSize
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class, ExperimentalMaterial3ExpressiveApi::class)
@Composable
fun AlertDialog(contentPadding: PaddingValues, onDismiss: ()-> Unit, content: List<String>, type: String = "NONE"){
    AlertDialog(
        modifier = Modifier
            .padding(
                end = contentPadding.calculateEndPadding(LayoutDirection.Ltr),
                start = contentPadding.calculateStartPadding(LayoutDirection.Ltr)
            )
            .fillMaxWidth(0.8f),
        shape = RoundedCornerShape(15),
        tonalElevation = 8.dp,
        containerColor = checkColor(type, primaryContainer = true),
        titleContentColor = checkColor(type, onPrimaryContainer = true),
        iconContentColor = checkColor(type, onPrimary = true),
        icon = {},
        title = {Text("Oggetto")},
        onDismissRequest = onDismiss,
        dismissButton = {},
        confirmButton= {
            TextButton(
                onClick = onDismiss,
                shapes = ButtonShapes(
                    shape = RoundedCornerShape(5.dp),
                    pressedShape = RoundedCornerShape(5.dp)
                ),
                colors = ButtonColors(
                    containerColor = checkColor(type, primaryContainer = true),
                    disabledContentColor = checkColor(type, primaryContainer = true),
                    contentColor = checkColor(type, onPrimaryContainer = true),
                    disabledContainerColor = checkColor(type, onPrimaryContainer = true),
                )
            ) {
                Text("Ok", color = checkColor(type, onPrimaryContainer = true))
            }
        },
        text = {
            Column(modifier = Modifier.fillMaxWidth()){
                Box(modifier = Modifier.padding(2.dp)) {
                    Text(
                        text = "Modello",
                        fontSize =  MaterialTheme.typography.bodyLargeEmphasized.fontSize,
                        color = checkColor(type, onPrimaryContainer = true)
                    )
                }
                Spacer(modifier = Modifier.size(30.dp))
                Column(modifier = Modifier.fillMaxWidth()) {
                    itemsList.forEach() { item ->
                        MenuDivider()
                        Spacer(modifier = Modifier.size(8.dp))
                        Box(modifier = Modifier.padding(4.dp)) {
                            Row(
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.Center
                            ) {
                                Box(modifier = Modifier.width(160.dp)) {
                                    Text(
                                        text = item,
                                        color = checkColor(type, onPrimaryContainer = true),
                                        fontSize = MaterialTheme.typography.bodyLarge.fontSize
                                    )
                                }
                                Box(modifier = Modifier.width(80.dp)) {
                                    Text(
                                        text = "num",
                                        color = checkColor(type, onPrimaryContainer = true),
                                        fontSize = MaterialTheme.typography.bodyLarge.fontSize
                                    )
                                }
                            }
                        }
                        Spacer(modifier = Modifier.size(8.dp))
                    }
                    Spacer(modifier = Modifier.size(8.dp))
                    MenuDivider()
                }
            }
        },
        properties = DialogProperties(
            usePlatformDefaultWidth = true,
            decorFitsSystemWindows = true,
            dismissOnClickOutside = true,
            dismissOnBackPress = true
        )
    )
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
fun GenericCard(
    text: String,
    textDescription: String? = null,
    textSpace : Float = 0.85f,
    leadingContent: (@Composable () -> Unit)? = null,
    trailingContent: (@Composable () -> Unit)? = null,
    contentPadding : PaddingValues,
    type: String = "NONE",
    onClick : () -> Unit = {},
    interactionSource: MutableInteractionSource? = null,
){
    Card(
        modifier = Modifier
            .padding(start = contentPadding.calculateStartPadding(LayoutDirection.Ltr) + 8.dp,
                end = contentPadding.calculateEndPadding(LayoutDirection.Ltr) + 8.dp)
            .height(60.dp)
            .clip(RoundedCornerShape(15.dp))
            .fillMaxSize(),
        colors = CardColors(
            contentColor = checkColor(type, onPrimaryContainer = true),
            containerColor = checkColor(type, primaryContainer = true),
            disabledContentColor = checkColor(type, onPrimaryContainer = true),
            disabledContainerColor = checkColor(type, primaryContainer = true)
        ),
        onClick = onClick,
    ) {
        if(leadingContent != null) {
            Row(
                modifier = Modifier.padding(10.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                leadingContent()
                Spacer(Modifier.size(8.dp))
                if(textDescription!= null){
                    Column (modifier = Modifier.fillMaxHeight()){
                        Text(
                            text,
                            fontSize = MaterialTheme.typography.headlineSmall.fontSize,
                            color = MaterialTheme.colorScheme.onPrimaryContainer,
                            modifier = Modifier.fillMaxWidth(textSpace)
                        )
                        Text(text = textDescription,
                            fontSize = MaterialTheme.typography.bodyMedium.fontSize,
                            color = MaterialTheme.colorScheme.onPrimaryContainer,
                            modifier = Modifier.fillMaxWidth(textSpace),

                            )
                    }
                }else{
                    Text(
                        text,
                        fontSize = MaterialTheme.typography.headlineSmall.fontSize,
                        color = MaterialTheme.colorScheme.onPrimaryContainer,
                        modifier = Modifier.fillMaxWidth(textSpace).padding(top = 5.dp)
                    )
                }
                if(trailingContent != null){
                    trailingContent()
                }
            }
        }else{
            if(textDescription != null){
                Row(modifier = Modifier.padding(top = 10.dp, bottom = 10.dp, start = 8.dp, end = 8.dp)){
                    Column {
                        Text(
                            text = text,
                            modifier = Modifier.padding(start = 8.dp).fillMaxWidth(textSpace),
                            fontSize = MaterialTheme.typography.headlineSmall.fontSize
                        )
                        Text(
                            text = textDescription,
                            fontSize = MaterialTheme.typography.bodyMedium.fontSize,
                            color = MaterialTheme.colorScheme.onPrimaryContainer,
                            modifier = Modifier.padding(start = 8.dp).fillMaxWidth(textSpace)
                        )
                    }
                    if(trailingContent != null){
                        trailingContent()
                    }
                }
            }else {
                Column(
                    modifier = Modifier.fillMaxHeight().fillMaxWidth()
                        .padding(start = 10.dp, end = 10.dp),
                    verticalArrangement = Arrangement.Center,
                ) {
                    if (trailingContent != null) {
                        Row() {

                            Text(
                                text = text,
                                modifier = Modifier.padding(start = 8.dp, top = 10.dp)
                                    .fillMaxWidth(textSpace),
                                fontSize = MaterialTheme.typography.headlineSmall.fontSize
                            )

                            trailingContent()
                        }
                    } else {
                        Text(
                            text = text,
                            modifier = Modifier.padding(start = 8.dp),
                            fontSize = MaterialTheme.typography.headlineSmall.fontSize
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun StatisticCard(text: String, icon: Painter, iconDescription : String, contentPadding : PaddingValues, onClick : () -> Unit){
    GenericCard(
        contentPadding = contentPadding,
        text = text,
        textSpace = 1.0f,
        leadingContent = {
            Icon(icon,
                contentDescription = iconDescription,
                tint = MaterialTheme.colorScheme.onPrimaryContainer,
                modifier = Modifier.width(50.dp).height(50.dp)
            )
        }
    )
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
    GenericCard(
        contentPadding = PaddingValues(4.dp),
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
fun SplitButtonMenu(
    content: String,
    items : List<String> = itemsList,
    backgroundColor: Color = MaterialTheme.colorScheme.primaryContainer,
    textColor: Color = MaterialTheme.colorScheme.onPrimaryContainer,
    contentPadding : PaddingValues,
    colorTextMenu: Color = MaterialTheme.colorScheme.onPrimary
){
    var checked by remember { mutableStateOf(false) }
    Row(
        modifier = Modifier
            .padding(end = contentPadding.calculateEndPadding(LayoutDirection.Ltr), start = contentPadding.calculateStartPadding(LayoutDirection.Ltr))
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
                            text = { MenuText( text = item.toString()) },
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
    contentPadding : PaddingValues
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