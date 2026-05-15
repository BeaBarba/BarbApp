package com.example.myapplication.ui.component

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.Checkbox
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
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
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import coil.compose.AsyncImage
import com.example.myapplication.R
import com.example.myapplication.data.database.CartDetails
import com.example.myapplication.data.database.Image
import com.example.myapplication.ui.utilities.ImageStorageManager
import com.example.myapplication.ui.utilities.checkColor
import com.example.myapplication.ui.utilities.checkboxColors
import java.io.File

@Composable
fun HomeCard(item : String, iconName : Painter, onclick: () -> Unit, modifier : Modifier = Modifier) {

    val contentColor = MaterialTheme.colorScheme.onPrimaryContainer

    Card(
        onClick = onclick,
        modifier = modifier
            .size(150.dp)
            .fillMaxSize(),
        border = BorderStroke(16.dp, MaterialTheme.colorScheme.background),
        shape = RoundedCornerShape(40.dp),
        colors = CardColors(
            contentColor = MaterialTheme.colorScheme.onPrimaryContainer,
            containerColor = MaterialTheme.colorScheme.primaryContainer.copy(alpha = 0.5f),
            disabledContentColor = MaterialTheme.colorScheme.onPrimaryContainer,
            disabledContainerColor = MaterialTheme.colorScheme.primaryContainer
        )
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
                tint = contentColor,
            )
            Spacer(Modifier.size(8.dp))
            Text(
                text = item,
                color = contentColor
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
    onClick : () -> Unit = {}
){
    val textTypography = MaterialTheme.typography.bodyLarge
    val descriptionTypography = MaterialTheme.typography.bodyMedium

    Card(
        modifier = Modifier
            .height(60.dp)
            .fillMaxWidth(),
        colors = CardColors(
            contentColor = checkColor(type, onPrimaryContainer = true),
            containerColor = checkColor(type, primaryContainer = true),
            disabledContentColor = checkColor(type, onPrimaryContainer = true),
            disabledContainerColor = checkColor(type, primaryContainer = true)
        ),
        shape = RoundedCornerShape(15.dp),
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
                            fontSize = textTypography.fontSize,
                            color = checkColor(type, onPrimaryContainer = true),
                            modifier = Modifier.fillMaxWidth(textSpace)
                        )
                        Text(text = textDescription,
                            fontSize = descriptionTypography.fontSize,
                            color = checkColor(type, onPrimaryContainer = true),
                            modifier = Modifier.fillMaxWidth(textSpace),
                        )
                    }
                }else{
                    Text(
                        text = text,
                        modifier = Modifier.fillMaxWidth(textSpace).padding(top = 5.dp),
                        fontSize = textTypography.fontSize,
                        color = checkColor(type, onPrimaryContainer = true),
                    )
                }
                if(trailingContent != null){
                    trailingContent()
                }
            }
        }else{
            if(textDescription != null){
                Row(
                    modifier = Modifier.padding(top = 10.dp, bottom = 10.dp, start = 8.dp, end = 8.dp),
                ){
                    Column {
                        Text(
                            text = text,
                            modifier = Modifier.padding(start = 8.dp).fillMaxWidth(textSpace),
                            color = checkColor(type, onPrimaryContainer = true),
                            fontSize = textTypography.fontSize
                        )
                        Text(
                            text = textDescription,
                            fontSize = descriptionTypography.fontSize,
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
                    modifier = Modifier
                        .fillMaxHeight()
                        .fillMaxWidth()
                        .padding(start = 10.dp, end = 10.dp),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    if (trailingContent != null) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.Center
                        ) {
                            Text(
                                text = text,
                                modifier = Modifier.padding(start = 8.dp, top = 10.dp)
                                    .fillMaxWidth(textSpace),
                                color = checkColor(type, onPrimaryContainer = true),
                                fontSize = textTypography.fontSize
                            )
                            trailingContent()
                        }
                    } else {
                        Text(
                            text = text,
                            modifier = Modifier.padding(start = 8.dp),
                            color = checkColor(type, onPrimaryContainer = true),
                            fontSize = textTypography.fontSize
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun StatisticCard(
    text: String,
    icon: Painter,
    iconDescription : String,
    description: String? = null,
    onClick : () -> Unit
){
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
        },
        onClick = onClick
    )
}

@Composable
fun LargeCard(
    type: String = "NONE",
    title: String,
    subtitle: String?,
    items: List<String>,
    description: String,
    onClick: () -> Unit
) {

    val textTypography = MaterialTheme.typography.headlineSmall
    val descriptionTypography = MaterialTheme.typography.bodyMedium
    val titleTypography = MaterialTheme.typography.titleMedium

    var materialText = ""
    for (item in items) {
        materialText = "$materialText$item, "
    }

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
                char = type[0],
                type = type,
                size = 40.dp
            )
            Spacer(Modifier.size(8.dp))
            if(subtitle.isNullOrEmpty()){
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center
                ) {
                    Text(
                        text = title,
                        fontSize = textTypography.fontSize,
                        color = checkColor(type, onPrimaryContainer = true),
                        modifier = Modifier.fillMaxWidth()
                    )
                }
            }else {
                Column{
                    Text(
                        text = title,
                        fontSize = textTypography.fontSize,
                        color = checkColor(type, onPrimaryContainer = true),
                        modifier = Modifier.fillMaxWidth()
                    )
                    Text(
                        text = subtitle,
                        fontSize = titleTypography.fontSize,
                        color = checkColor(type, onPrimaryContainer = true),
                        modifier = Modifier.fillMaxWidth(),
                    )
                }
            }
        }
        Spacer(Modifier.size(8.dp))
        Column {
            Row(modifier = Modifier.padding(start = 10.dp, end = 10.dp)) {
                Text(
                    text = stringResource(R.string.description) + ": ",
                    modifier = Modifier.padding(start = 8.dp).fillMaxWidth(0.25f),
                    color = checkColor(type, onPrimaryContainer = true),
                    fontSize = titleTypography.fontSize
                )
                Text(
                    text = description,
                    modifier = Modifier.fillMaxWidth(),
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    color = checkColor(type, onPrimaryContainer = true),
                    fontSize = descriptionTypography.fontSize
                )
            }
            Spacer(Modifier.size(8.dp))
            Row(modifier = Modifier.padding(start = 10.dp, end = 10.dp)) {
                Text(
                    text = stringResource(R.string.material) + ": ",
                    modifier = Modifier.padding(start = 8.dp).fillMaxWidth(0.20f),
                    color = checkColor(type, onPrimaryContainer = true),
                    fontSize = titleTypography.fontSize
                )
                Text(
                    text = materialText,
                    fontSize = descriptionTypography.fontSize,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    color = checkColor(type, onPrimaryContainer = true),
                    modifier = Modifier.fillMaxWidth()
                )
            }
        }
    }
}

@Composable
fun CartCard(
    item: CartDetails,
    onClick: () -> Unit,
    isChecked : Boolean,
    onClickCheckbox : (Boolean) -> Unit
){
    GenericCard(
        text = item.material.category,
        textDescription = item.material.model,
        type = item.material.type.toString(),
        onClick = onClick,
        textSpace = 0.7f,
        leadingContent = {
            Avatar(
                char = item.material.type.toString()[0],
                type = item.material.type.toString()
            )
        },
        trailingContent = {
            Row(
                modifier = Modifier.padding(end = 8.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.End
            ) {
                Row(
                    modifier = Modifier.weight(5.0f),
                    horizontalArrangement = Arrangement.End
                ) {

                    Text(
                        text = item.missingItems.toString(),
                        fontSize = MaterialTheme.typography.bodyLarge.fontSize,
                    )
                    Spacer(Modifier.size(4.dp))
                    Text(
                        text = item.material.unitMeasurement,
                        fontSize = MaterialTheme.typography.bodyLarge.fontSize,
                    )
                }
                Spacer(Modifier.size(10.dp))
                Checkbox(
                    checked = isChecked,
                    onCheckedChange = { checked ->
                        onClickCheckbox(checked)
                    },
                    interactionSource = remember { MutableInteractionSource() },
                    modifier = Modifier.weight(2.0f),
                    colors = checkboxColors(item.material.type.toString())
                )
            }

        }
    )
    Spacer(Modifier.size(8.dp))
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Images(
    images : List<Image>
){
    val ctx = LocalContext.current

    var selectedImagePath by remember { mutableStateOf<String?>(null) }

    HorizontalMultiBrowseCarousel(
        state = rememberCarouselState { images.count() },
        modifier = Modifier.wrapContentHeight(),
        itemSpacing = 8.dp,
        preferredItemWidth = 140.dp,
        content = {index ->
            val image = images[index]
            val imageFile = File(ImageStorageManager.getImagesDirectory(ctx), image.path)

            AsyncImage(
                model = imageFile,
                contentDescription = stringResource(R.string.image),
                modifier = Modifier
                    .size(140.dp)
                    .clip(RoundedCornerShape(16.dp))
                    .background(MaterialTheme.colorScheme.primaryContainer)
                    .clickable { selectedImagePath = image.path },
                contentScale = ContentScale.Crop
            )
        },
        contentPadding = PaddingValues(horizontal = 8.dp, vertical = 8.dp)
    )
    selectedImagePath?.let { path ->
        FullScreenImageDialog(
            imagePath = path,
            onDismiss = { selectedImagePath = null }
        )
    }
}

@Composable
fun FullScreenImageDialog(
    imagePath: String,
    onDismiss: () -> Unit
) {
    val ctx = LocalContext.current
    val imageFile = File(ImageStorageManager.getImagesDirectory(ctx), imagePath)

    Dialog(
        onDismissRequest = onDismiss,
        properties = DialogProperties( usePlatformDefaultWidth = false )
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.Black)
        ) {
            AsyncImage(
                model = imageFile,
                contentDescription = null,
                modifier = Modifier
                    .fillMaxSize()
                    .clickable { onDismiss() },
                contentScale = ContentScale.Fit
            )

            IconButton(
                onClick = onDismiss,
                modifier = Modifier
                    .align(Alignment.TopEnd)
                    .padding(16.dp)
            ) {
                Icon(
                    imageVector = Icons.Default.Close,
                    contentDescription = stringResource(R.string.close),
                    tint = Color.White
                )
            }
        }
    }
}