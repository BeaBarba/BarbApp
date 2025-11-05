package com.example.myapplication.ui.screen

import android.app.AlertDialog
import android.content.Intent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonElevation
import androidx.compose.material3.ButtonShapes
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExperimentalMaterial3ExpressiveApi
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonColors
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.myapplication.ui.component.AddButton
import com.example.myapplication.ui.component.TopAppBar
import androidx.compose.ui.Alignment
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.modifier.modifierLocalMapOf
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.window.DialogProperties
import com.example.myapplication.R
import com.example.myapplication.ui.component.AlertDialog
import com.example.myapplication.ui.component.Avatar
import com.example.myapplication.ui.component.DeleteButton
import com.example.myapplication.ui.component.DropDownMenuCustumers
import com.example.myapplication.ui.component.MenuDivider
import com.example.myapplication.ui.component.SearchAppBar
import com.example.myapplication.ui.component.SplitButtonMenu
import com.example.myapplication.ui.component.StatisticCard
import com.example.myapplication.ui.component.checkColor
import java.sql.Types.NULL

val itemsList = List<String>(5, {item -> "item $item"})



@Composable
fun Screen(modifier : Modifier){

    val ctx = LocalContext.current
    var showDialog by remember { mutableStateOf(false)}
    Scaffold (
        modifier = Modifier.fillMaxSize(),
        topBar = {
            TopAppBar("Screen",
            {IconButton(
                onClick = { showDialog = true },
                colors = IconButtonColors(
                    containerColor = MaterialTheme.colorScheme.primary,
                    contentColor = MaterialTheme.colorScheme.onPrimary,
                    disabledContentColor = MaterialTheme.colorScheme.onPrimary,
                    disabledContainerColor = MaterialTheme.colorScheme.primaryContainer
                )
            ) {
                Icon(
                Icons.AutoMirrored.Filled.ArrowForward,
                contentDescription = "Dialog",
                tint = MaterialTheme.colorScheme.onPrimary)
                }
            },
            { DropDownMenuCustumers() })
        },
        floatingActionButton = {AddButton("Screen"){
            val intent = Intent(ctx, HomeActivity ::class.java)
            ctx.startActivity(intent)
        } }
    ) { contentPadding ->
        Column(
            modifier = modifier
                .padding(
                    top = contentPadding.calculateTopPadding(),
                    start = contentPadding.calculateStartPadding(LayoutDirection.Ltr),
                    end = contentPadding.calculateEndPadding(LayoutDirection.Ltr)
                )
                .fillMaxSize()
        ) {
            SearchAppBar("Screen", contentPadding)
            Spacer(Modifier.size(8.dp))
            SplitButtonMenu(content = "Venditori", contentPadding = contentPadding)
            DeleteButton({}, contexPadding = contentPadding)
            //CheckLazyList(itemsList, contentPadding)
            Spacer(Modifier.size(8.dp))
            GenericCard(
                text = "Ciao",
                icon = {Icon(
                    painterResource(R.drawable.line_chart),
                    contentDescription = "prova",
                    modifier = Modifier.width(40.dp).height(40.dp),
                    tint = MaterialTheme.colorScheme.onPrimaryContainer)
                //Avatar(char = 'C', modifierBox = Modifier.height(40.dp).width(40.dp))

                },
                iconDescription = "Prova",
                contentPadding = contentPadding,
            ){}

        }
        if (showDialog) {
            AlertDialog(contentPadding, { showDialog = false }, itemsList)
        }
    }
}

@Composable
fun GenericCard(
    text: String,
    icon: (@Composable () -> Unit)? = null,
    iconDescription : String,
    contentPadding : PaddingValues,
    type: String = "NONE",
    onClick : () -> Unit
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
        onClick = onClick
    ) {
        if(icon != null) {
            Row(
                modifier = Modifier.padding(10.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                /*Icon(icon,
                contentDescription = iconDescription,
                tint = MaterialTheme.colorScheme.onPrimaryContainer,
                modifier = Modifier.width(50.dp).height(50.dp)
            )*/
                icon()
                Spacer(Modifier.size(8.dp))
                Text(text, fontSize = MaterialTheme.typography.headlineSmall.fontSize)
            }
        }else{
            Column(
                modifier = Modifier.fillMaxWidth().padding(10.dp),
                verticalArrangement = Arrangement.Center,
            ) {
                Spacer(Modifier.size(8.dp))
                Text(text, modifier = Modifier.padding(start = 8.dp), fontSize = MaterialTheme.typography.headlineSmall.fontSize)
            }
        }
    }
}




