package com.example.myapplication.ui.screen

import android.widget.PopupMenu
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExperimentalMaterial3ExpressiveApi
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.ToggleButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.myapplication.ui.component.AddButton
import com.example.myapplication.ui.component.TopAppBar
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.LayoutDirection
import com.example.myapplication.ui.component.Avatar
import com.example.myapplication.ui.component.CheckLazyList
import com.example.myapplication.ui.component.CustomersCardsList
import com.example.myapplication.ui.component.SearchAppBar

val items = List<String>(100, {item -> "item$item"})

@Composable
fun Screen(modifier : Modifier){


    val ctx = LocalContext.current

    Scaffold (
        modifier = Modifier.fillMaxSize(),
        topBar = {TopAppBar("Screen")},
        floatingActionButton = {AddButton("Screen"){} }
    ){ contentPadding ->
        var checked by remember { mutableStateOf(false) }
        Column (
            modifier = Modifier.fillMaxSize().padding(contentPadding),
        ) {
            SearchAppBar("Screen", contentPadding)
            Spacer(Modifier.size(4.dp))
            SplitButtonMenu(content = "Venditori", contextPadding = contentPadding)
            CheckLazyList(items, contentPadding)
        }
    }
}

@Composable
fun SplitButtonMenu(
    content: String,
    item : List<String> = items,
    backgroundColor: Color = MaterialTheme.colorScheme.primaryContainer,
    textColor: Color = MaterialTheme.colorScheme.onPrimaryContainer,
    contextPadding : PaddingValues,
    colorBackgroudMenu: Color = MaterialTheme.colorScheme.primary,
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
                DropdownMenu(
                    expanded = checked,
                    onDismissRequest = { checked = false },
                    modifier = Modifier
                        .height(300.dp)
                        .border(1.dp, MaterialTheme.colorScheme.onPrimary, RoundedCornerShape(15.dp))
                        .background(colorBackgroudMenu),
                    shape = RoundedCornerShape(15.dp)
                ) {

                        Column(
                            modifier = Modifier.padding(end = 20.dp, start = 20.dp)
                        ) {
                            DropdownMenuItem(
                                text = {
                                    Text(
                                        text = "Edit",
                                        color = colorTextMenu
                                    )
                                },
                                onClick = {/* Change name split button */ },
                            )

                            HorizontalDivider(
                                modifier = Modifier.padding(end = 6.dp, start = 6.dp),
                                thickness = 1.dp,
                                color = MaterialTheme.colorScheme.onPrimaryContainer
                            )

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
