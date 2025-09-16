package com.example.myapplication.ui.screen


import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.background
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
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.unit.LayoutDirection
import com.example.myapplication.ui.component.Avatar
import com.example.myapplication.ui.component.SearchAppBar

val items = List<String>(100, {item -> "item$item"})

@ExperimentalMaterial3Api
@Composable
fun Screen(modifier : Modifier){
    Scaffold (
        modifier = Modifier.fillMaxSize(),
        topBar = {TopAppBar("Screen")},
        floatingActionButton = {AddButton("Screen"){} }
    ){ contentPadding ->

        Column (
            modifier = Modifier.fillMaxSize().padding(contentPadding),
        ) {
            SearchAppBar("Screen", contentPadding)
            Spacer(Modifier.size(4.dp))
            SplitButtonMenu(content = "Venditori", contextPadding = contentPadding)

        }
    }
}

@Composable
fun SplitButtonMenu(
    content: String,
    item : List<String> = items,
    backgroundColor: Color = MaterialTheme.colorScheme.primaryContainer,
    textColor: Color = MaterialTheme.colorScheme.onPrimaryContainer,
    contextPadding : PaddingValues
    ){
        Row(
            modifier = Modifier
                .padding(end = contextPadding.calculateEndPadding(LayoutDirection.Ltr), start = contextPadding.calculateStartPadding(LayoutDirection.Ltr))
                .padding(horizontal = 8.dp)
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
                ToggleIconButton()
                //Qui va il DropDownMenu
            }

        }
    }



@Composable
fun ToggleIconButton() {
    var checked by remember { mutableStateOf(false) }

    val rotation by animateFloatAsState(
        targetValue = if (checked) 180f else 0f,
        label = "Trailing Icon Rotation"
    )

    IconButton(
        onClick = {
            checked = !checked
        }
    ) {
        Icon(
            imageVector = Icons.Filled.KeyboardArrowDown,
            modifier = Modifier.graphicsLayer {
                this.rotationZ = rotation
            },
            contentDescription = if(checked) "Ritira" else "Espandi"
        )
    }
}