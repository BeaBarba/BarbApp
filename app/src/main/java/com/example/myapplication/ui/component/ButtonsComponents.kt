package com.example.myapplication.ui.component

import androidx.compose.animation.core.animateFloatAsState
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
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import com.example.myapplication.R

@Composable
fun AddButton(onClick: () -> Unit){
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
            .padding(end = contentPadding.calculateEndPadding(LayoutDirection.Ltr), start = contentPadding.calculateStartPadding(
                LayoutDirection.Ltr))
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