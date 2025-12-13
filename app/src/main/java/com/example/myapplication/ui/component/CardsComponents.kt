package com.example.myapplication.ui.component

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Image
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.carousel.HorizontalMultiBrowseCarousel
import androidx.compose.material3.carousel.rememberCarouselState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.example.myapplication.ui.screen.itemsList

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
    type: String = "NONE",
    onClick : () -> Unit = {},
    interactionSource: MutableInteractionSource? = null,
){

    val TEXT_TYPOGRAPHY = MaterialTheme.typography.headlineSmall
    val DESCRIPTION_TYPOGRAPHY = MaterialTheme.typography.bodyMedium

    Card(
        modifier = Modifier
            /*.padding(start = contentPadding.calculateStartPadding(LayoutDirection.Ltr) + 8.dp,
                end = contentPadding.calculateEndPadding(LayoutDirection.Ltr) + 8.dp)*/
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
fun StatisticCard(text: String, icon: Painter, iconDescription : String, description: String? = null, onClick : () -> Unit){
    GenericCard(
        text = text,
        textDescription =  description,
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
        content = { Image(
            Icons.Outlined.Image,
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