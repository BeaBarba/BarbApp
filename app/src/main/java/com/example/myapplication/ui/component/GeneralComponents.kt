package com.example.myapplication.ui.component

import android.content.Context
import android.content.Intent
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.outlined.Image
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
import androidx.compose.material3.carousel.HorizontalMultiBrowseCarousel
import androidx.compose.material3.carousel.rememberCarouselState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.DialogProperties
import com.example.myapplication.R
import com.example.myapplication.ui.screen.HomeActivity
import com.example.myapplication.ui.screen.itemsList
import com.example.myapplication.ui.theme.Yellow30
import com.example.myapplication.ui.theme.Yellow70
import com.example.myapplication.ui.theme.Yellow95

/* --------------------------------------------------------- Function ------------------------------------------------------------- */
/* Function that returns a specific color */
@Composable
fun checkColor(
    type: String = "NONE",
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
            onPrimaryContainer -> MaterialTheme.colorScheme.onSecondaryContainer
            else -> MaterialTheme.colorScheme.surfaceBright
        }
        "ELE" -> when{
            primary -> MaterialTheme.colorScheme.tertiary
            onPrimary -> MaterialTheme.colorScheme.onTertiary
            primaryContainer -> MaterialTheme.colorScheme.tertiaryContainer
            onPrimaryContainer -> MaterialTheme.colorScheme.onTertiaryContainer
            else -> MaterialTheme.colorScheme.surfaceBright
        }
        "CDZ" -> when{
            primary -> MaterialTheme.colorScheme.surface
            onPrimary -> MaterialTheme.colorScheme.onSurface
            primaryContainer -> MaterialTheme.colorScheme.surfaceVariant
            onPrimaryContainer -> MaterialTheme.colorScheme.onSurfaceVariant
            else -> MaterialTheme.colorScheme.surfaceBright
        }
        "NONE" -> when{
            primary -> MaterialTheme.colorScheme.primary
            onPrimary -> MaterialTheme.colorScheme.onPrimary
            primaryContainer -> MaterialTheme.colorScheme.primaryContainer
            onPrimaryContainer -> MaterialTheme.colorScheme.onPrimaryContainer
            else -> MaterialTheme.colorScheme.surfaceBright
        }
        else -> throw IllegalArgumentException("Tipo non supportato")
    }
}

/* --------------------------------------------------------- Avatar Composables  ------------------------------------------------------------- */
/* Function that generates a circle with a character inside it */
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
            )},
        colors = ListItemDefaults.colors(
            containerColor = checkColor(primaryContainer = true),
            headlineColor = checkColor(onPrimaryContainer = true),
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

/* --------------------------------------------------------- Common Composables  ------------------------------------------------------------- */

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

/* --------------------------------------------------------- Label  ------------------------------------------------------------- */
@Composable
fun TitleLabel(
    title: String,
    modifier: Modifier = Modifier
        .height(40.dp).clip(RoundedCornerShape(10.dp))
        .background(MaterialTheme.colorScheme.primary).fillMaxWidth(),
    color: Color = MaterialTheme.colorScheme.onPrimary,
    alignment: Alignment = Alignment.Center
){
    Box(
        modifier = modifier,
        contentAlignment = alignment,
    ) {
        Text(
            text = title,
            color = color,
            fontSize = MaterialTheme.typography.bodyLarge.fontSize,
            //fontWeight = FontWeight.Bold
        )
    }
}

@Composable
fun CustomLabel(
    title: String,
    description: String,
    weightTitle: Float,
    weighDescription: Float
){
    Row(
        modifier = Modifier
            .height(40.dp)
            .clip(RoundedCornerShape(10.dp))
            .background(color = MaterialTheme.colorScheme.primaryContainer),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically

    ) {
        TitleLabel(title, modifier= Modifier.weight(weightTitle).fillMaxHeight().clip(
            RoundedCornerShape(10.dp)).background(MaterialTheme.colorScheme.primary))
        TitleLabel(
            description,
            modifier= Modifier
                .weight(weighDescription)
                .fillMaxHeight()
                .clip(RoundedCornerShape(10.dp))
                .background(MaterialTheme.colorScheme.primaryContainer)
                .padding(horizontal = 10.dp),
            color = MaterialTheme.colorScheme.onPrimaryContainer,
            alignment = Alignment.CenterStart
        )
    }
}

/* --------------------------------------------------------- Buttons ------------------------------------------------------------- */
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
    val BOX_COLOR = MaterialTheme.colorScheme.secondary
    val CONTENT_COLOR = MaterialTheme.colorScheme.onSecondary
    IconButton(
        onClick = onclick,
        modifier = Modifier
            .padding(
                start = contexPadding.calculateStartPadding(LayoutDirection.Ltr),
                end = contexPadding.calculateEndPadding(LayoutDirection.Ltr))
            .fillMaxWidth()
            .height(60.dp),
        enabled =   true,
        colors = IconButtonDefaults.iconButtonColors(BOX_COLOR)
    ) {
        Row (
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ){
           Icon(
               painter = painterResource(R.drawable.delete_24dp),
               contentDescription = "Delete",
               tint = CONTENT_COLOR,
               modifier = Modifier.height(30.dp).width(30.dp)
           )
            Spacer(Modifier.size(8.dp))
            Text(
                text = "Elimina",
                color = CONTENT_COLOR,
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
fun SplitButtonMenu(
    content: String,
    items : List<String>,
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
                            text = { MenuText( text = item, colorTextMenu ) },
                            onClick = {/* Change name split button */ },
                        )
                    }
                }
            }
        }
    }
}

/* --------------------------------------------------------- Popup composables ------------------------------------------------------------- */
@OptIn(ExperimentalMaterial3Api::class, ExperimentalMaterial3ExpressiveApi::class)
@Composable
fun AlertDialog(
    contentPadding: PaddingValues,
    onDismiss: ()-> Unit,
    title: String,
    subtitle: String,
    content: List<String>, type: String = "NONE"
){

    val TITLE_TYPOGRAFY = MaterialTheme.typography.headlineMedium
    val SUBTITLE_TYPOGRAFY = MaterialTheme.typography.titleMedium
    val ITEM_TYPOGRAPHY = MaterialTheme.typography.bodyLarge

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
        title = {Text(title, fontSize = TITLE_TYPOGRAFY.fontSize)},
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
                        text = subtitle,
                        fontSize =  SUBTITLE_TYPOGRAFY.fontSize,
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
                                        fontSize = ITEM_TYPOGRAPHY.fontSize
                                    )
                                }
                                Box(modifier = Modifier.width(80.dp)) {
                                    Text(
                                        text = "num",
                                        color = checkColor(type, onPrimaryContainer = true),
                                        fontSize = ITEM_TYPOGRAPHY.fontSize
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

/* --------------------------------------------------------- Card composables ------------------------------------------------------------- */
@Composable
fun HomeCard(item : String, iconName : Painter, onclick: () -> Unit) {

    val CONTENT_COLOR = MaterialTheme.colorScheme.onPrimaryContainer

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
                tint = CONTENT_COLOR,
            )
            Spacer(Modifier.size(8.dp))
            Text(
                text = item,
                color = CONTENT_COLOR
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

    val TEXT_TYPOGRAPHY = MaterialTheme.typography.headlineSmall
    val DESCRIPTION_TYPOGRAPHY = MaterialTheme.typography.bodyMedium

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
                            fontSize = TEXT_TYPOGRAPHY.fontSize,
                            color = checkColor(type, onPrimaryContainer = true),
                            modifier = Modifier.fillMaxWidth(textSpace)
                        )
                        Text(text = textDescription,
                            fontSize = DESCRIPTION_TYPOGRAPHY.fontSize,
                            color = checkColor(type, onPrimaryContainer = true),
                            modifier = Modifier.fillMaxWidth(textSpace),
                            )
                    }
                }else{
                    Text(
                        text,
                        fontSize = TEXT_TYPOGRAPHY.fontSize,
                        color = checkColor(type, onPrimaryContainer = true),
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
                            color = checkColor(type, onPrimaryContainer = true),
                            fontSize = TEXT_TYPOGRAPHY.fontSize
                        )
                        Text(
                            text = textDescription,
                            fontSize = DESCRIPTION_TYPOGRAPHY.fontSize,
                            color = checkColor(type, onPrimaryContainer = true),
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
                                modifier = Modifier.padding(start = 8.dp, top = 10.dp).fillMaxWidth(textSpace),
                                color = checkColor(type, onPrimaryContainer = true),
                                fontSize = TEXT_TYPOGRAPHY.fontSize
                            )
                            trailingContent()
                        }
                    } else {
                        Text(
                            text = text,
                            modifier = Modifier.padding(start = 8.dp),
                            color = checkColor(type, onPrimaryContainer = true),
                            fontSize = TEXT_TYPOGRAPHY.fontSize
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
fun LargeCard(type: String = "NONE", title: String, subtitle: String, items: List<String>, description: String, onClick: () -> Unit) {

    val TEXT_TYPOGRAPHY = MaterialTheme.typography.headlineSmall
    val DESCRIPTION_TYPOGRAPHY = MaterialTheme.typography.bodyMedium
    val TITLE_TYPOGRAPHY = MaterialTheme.typography.titleMedium

    var materialText = ""
    for (item in items) {
        materialText = "$materialText$item, "
    }

    val coloTextAvatar = if(type == "NONE" || type == "ALA") {checkColor(type, onPrimary = true)} else{checkColor(type, primary = true)}
    val colorCharacterAvatar = if(type == "NONE" || type == "ALA") {checkColor(type, primary = true)} else{checkColor(type, onPrimary = true)}

    Card(
        modifier = Modifier
            .height(140.dp)
            .fillMaxSize(),
        shape = RoundedCornerShape(15.dp),
        border = BorderStroke(1.dp, checkColor(type, onPrimaryContainer = true)),
        colors = CardColors(
            contentColor = checkColor(type, onPrimaryContainer = true),
            containerColor = checkColor(type, primaryContainer = true),
            disabledContentColor = checkColor(type, onPrimaryContainer = true),
            disabledContainerColor = checkColor(type, primaryContainer = true)
        ),
        onClick = onClick,
    ) {
        Row(
            modifier = Modifier.padding(10.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            Avatar(
                type.get(0),
                textColor = coloTextAvatar,
                size = 40.dp,
                backgroundColor = colorCharacterAvatar
            )
            Spacer(Modifier.size(8.dp))
            Column() {
                Text(
                    title,
                    fontSize = TEXT_TYPOGRAPHY.fontSize,
                    color = checkColor(type, onPrimaryContainer = true),
                    modifier = Modifier.fillMaxWidth()
                )
                Text(
                    text = subtitle,
                    fontSize = TITLE_TYPOGRAPHY.fontSize,
                    color = checkColor(type, onPrimaryContainer = true),
                    modifier = Modifier.fillMaxWidth(),
                )
            }
        }
        Spacer(Modifier.size(8.dp))
        Column {
            Row(modifier = Modifier.padding(start = 10.dp, end = 10.dp)) {
                Text(
                    text = "Descrizione: ",
                    modifier = Modifier.padding(start = 8.dp).fillMaxWidth(0.25f),
                    color = checkColor(type, onPrimaryContainer = true),
                    fontSize = TITLE_TYPOGRAPHY.fontSize
                )
                Text(
                    text = description,
                    modifier = Modifier.fillMaxWidth(),
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    color = checkColor(type, onPrimaryContainer = true),
                    fontSize = DESCRIPTION_TYPOGRAPHY.fontSize
                )
            }
            Spacer(Modifier.size(8.dp))
            Row(modifier = Modifier.padding(start = 10.dp, end = 10.dp)) {
                Text(
                    text = "Materiale: ",
                    modifier = Modifier.padding(start = 8.dp).fillMaxWidth(0.20f),
                    color = checkColor(type, onPrimaryContainer = true),
                    fontSize = TITLE_TYPOGRAPHY.fontSize
                )
                Text(
                    text = materialText,
                    fontSize = DESCRIPTION_TYPOGRAPHY.fontSize,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    color = checkColor(type, onPrimaryContainer = true),
                    modifier = Modifier.fillMaxWidth()
                )
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Images(){
    HorizontalMultiBrowseCarousel(
        state = rememberCarouselState { itemsList.count() },
        modifier = Modifier.wrapContentHeight(),
        itemSpacing = 8.dp,
        preferredItemWidth = 140.dp,
        content = { Image(Icons.Outlined.Image,
            "Image",
            modifier = Modifier
                .size(140.dp)
                .clip(CircleShape)
                .background(MaterialTheme.colorScheme.primaryContainer)
                .padding(20.dp),
            colorFilter = ColorFilter.tint(MaterialTheme.colorScheme.onPrimaryContainer),
            contentScale = ContentScale.Fit)
        },
        contentPadding = PaddingValues(horizontal = 8.dp)
    )
}

/* --------------------------------------------------------- Table -------------------------------------------------------*/
data class TableColumn(
    val title: String,
    val weight: Float,
)

@Composable
fun <T> Table(contentPadding: PaddingValues,
              listData: List<T>,
              headerColumns: List<TableColumn>
){
    val TABLE_BORDER_WIDTH = 2.dp
    val TABLE_BORDER_COLOR = MaterialTheme.colorScheme.onPrimaryContainer
    val HEIGHT_HEADER_ROW = 60.dp
    val HEADER_TYPOGRAPHY = MaterialTheme.typography.titleSmall
    val HEIGHT_ROW = 50.dp
    val TEXT = MaterialTheme.typography.bodyLarge
    val DESCRIPTION = MaterialTheme.typography.bodyMedium

    LazyColumn(modifier = Modifier
        .fillMaxWidth()
        .padding(
            start = contentPadding.calculateRightPadding(LayoutDirection.Ltr) + 8.dp,
            end = contentPadding.calculateLeftPadding(LayoutDirection.Ltr) + 8.dp,
        )
        .border(TABLE_BORDER_WIDTH, TABLE_BORDER_COLOR),
        contentPadding = PaddingValues(
            bottom = contentPadding.calculateEndPadding(LayoutDirection.Ltr) + 90.dp
        )
    ) {
        /* Header Cell */
        item{
            Row(
                modifier = Modifier
                    .height(HEIGHT_HEADER_ROW)
                    .background(MaterialTheme.colorScheme.primaryContainer, shape = RectangleShape),
                verticalAlignment = Alignment.CenterVertically
            ) {
                for(column in headerColumns) {
                    Box(
                        modifier = Modifier
                            .weight(column.weight)
                            .height(HEIGHT_HEADER_ROW),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = column.title, modifier = Modifier
                                .padding(horizontal = 4.dp), style = HEADER_TYPOGRAPHY
                        )
                    }
                }
            }
        }

        /* Rows */
        items(listData) {data ->
            MenuDivider()
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                /* Column 1 */
                Box(modifier = Modifier
                    .weight(headerColumns.get(0).weight)
                    .height(HEIGHT_ROW),
                ){
                    Column {
                        Text(
                            text = "",//data.nome,
                            modifier = Modifier.padding(horizontal = 4.dp),
                            fontSize = TEXT.fontSize
                        )
                        Text(
                            text = "",//data.modello,
                            modifier = Modifier.padding(horizontal = 4.dp),
                            fontSize = DESCRIPTION.fontSize
                        )
                    }
                }
                /* Column 2 */
                Box(modifier = Modifier
                    .weight(headerColumns.get(1).weight)
                    .height(HEIGHT_ROW),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "",//"${data.quantita.toString()} ${data.unitaMisura.toString()}",
                        modifier = Modifier.padding(horizontal = 4.dp),
                        fontSize = TEXT.fontSize
                    )
                }
                /* Column 3 */
                Box(modifier = Modifier
                    .weight(headerColumns.get(2).weight)
                    .height(HEIGHT_ROW),
                    contentAlignment = Alignment.Center
                ){
                    Text(
                        text = "",//"${data.prezzo}€",
                        modifier = Modifier.padding(horizontal = 4.dp),
                        fontSize = TEXT.fontSize
                    )}
                /* Column 4 */
                Box(modifier = Modifier
                    .weight(headerColumns.get(3).weight)
                    .height(HEIGHT_ROW),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "",//"${data.iva}%",
                        modifier = Modifier.padding(horizontal = 4.dp),
                        fontSize = TEXT.fontSize
                    )
                }
            }
            MenuDivider()
        }
    }
}

//----------------------------------- Composables List --------------------
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
    }
}