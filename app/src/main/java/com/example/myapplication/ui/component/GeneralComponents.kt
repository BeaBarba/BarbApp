package com.example.myapplication.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonShapes
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExperimentalMaterial3ExpressiveApi
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.DialogProperties
import com.example.myapplication.R

/* --------------------------------------------------------- Avatar Composables  ------------------------------------------------------------- */
/* Function that generates a circle with a character inside it */
@Composable
fun Avatar(
    char: Char,
    modifierBox: Modifier = Modifier,
    size : Dp = 35.dp,
    shape: Shape = CircleShape,
    type : String? = "NONE"
){
    val TEXT_COLOR = checkColorAvatar(type = type.toString(), primary = true)
    val BACKGROUND_COLOR = checkColorAvatar(type = type.toString(), onPrimary = true)
    Box(
        modifier = modifierBox
            .size(size)
            .clip(shape)
            .background(BACKGROUND_COLOR),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = char.toString(),
            color = TEXT_COLOR,
            fontSize = MaterialTheme.typography.bodyLarge.fontSize
        )
    }
}

/* --------------------------------------------------------- Common Composables  ------------------------------------------------------------- */

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopAppBar(
    id: String,
    navigationIcon: @Composable () -> Unit,
    trailingIcon: (@Composable () -> Unit)? = null
){
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
        actions = { if(trailingIcon != null){ trailingIcon() }}
    )
}

@Composable
fun CustomDivider(color : Color = MaterialTheme.colorScheme.primary, paddingHorizontal: Dp = 2.dp, paddingVertical: Dp = 8.dp, thickness: Dp = 1.dp){
    HorizontalDivider(
        modifier = Modifier.padding(end = paddingHorizontal, start = paddingHorizontal, bottom = paddingVertical, top = paddingVertical),
        thickness = thickness,
        color = color
    )
}

/* --------------------------------------------------------- Popup composables ------------------------------------------------------------- */
@OptIn(ExperimentalMaterial3ExpressiveApi::class)
@Composable
fun CustomAlertDialog(
    onDismiss: ()-> Unit,
    title: String,
    subtitle: String,
    content: List<Pair<String,Int>>,
    type: String = "NONE"
){
    val TITLE_TYPOGRAFY = MaterialTheme.typography.headlineMedium
    val SUBTITLE_TYPOGRAFY = MaterialTheme.typography.titleMedium
    val ITEM_TYPOGRAPHY = MaterialTheme.typography.bodyLarge

    AlertDialog(
        modifier = Modifier
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
                Text(text = stringResource(R.string.ok), color = checkColor(type, onPrimaryContainer = true))
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
                    content.forEach() { item ->
                        MenuDivider()
                        Spacer(modifier = Modifier.size(8.dp))
                        Box(modifier = Modifier.padding(4.dp)) {
                            Row(
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.Center
                            ) {
                                Box(modifier = Modifier.width(160.dp)) {
                                    Text(
                                        text = item.first,
                                        color = checkColor(type, onPrimaryContainer = true),
                                        fontSize = ITEM_TYPOGRAPHY.fontSize
                                    )
                                }
                                Box(modifier = Modifier.width(80.dp)) {
                                    Text(
                                        text = item.second.toString(),
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