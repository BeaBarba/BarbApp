package com.example.myapplication.ui.screen

import android.content.Intent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.ExperimentalMaterial3ExpressiveApi
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonColors
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.LayoutDirection
import com.example.myapplication.ui.component.AlertDialog
import com.example.myapplication.ui.component.Avatar
import com.example.myapplication.ui.component.DeleteButton
import com.example.myapplication.ui.component.DropDownMenuCustumers
import com.example.myapplication.ui.component.GenericCard
import com.example.myapplication.ui.component.SearchAppBar
import com.example.myapplication.ui.component.SplitButtonMenu

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
            var checked = false;
            var textButton = "24"
            GenericCard(
                text = "Ciao",
                textDescription = "Biticino",
                textSpace = 0.60f,
                leadingContent = {/*Icon(
                    painterResource(R.drawable.line_chart),
                    contentDescription = "prova",
                    modifier = Modifier.width(40.dp).height(40.dp),
                    tint = MaterialTheme.colorScheme.onPrimaryContainer)*/
                Avatar(char = 'C', modifierBox = Modifier.height(40.dp).width(40.dp))
                },
                contentPadding = contentPadding,
                trailingContent = {/*Icon(
                    painterResource(R.drawable.line_chart),
                    contentDescription = "prova",
                    modifier = Modifier.width(40.dp).height(40.dp),
                    tint = MaterialTheme.colorScheme.onPrimaryContainer)*/
                    //Checkbox(checked, {checked = !checked}, )
                    /*IconButton({}) { Icon(
                        Icons.Filled.ArrowDropDown,
                        contentDescription = "prova",
                        modifier = Modifier.width(40.dp).height(40.dp),
                        tint = MaterialTheme.colorScheme.onPrimaryContainer)
                    }*/
                    //Text("365", fontSize = MaterialTheme.typography.headlineSmallEmphasized.fontSize, color = MaterialTheme.colorScheme.onPrimaryContainer)
                    /*Row(modifier = Modifier.padding(8.dp,), verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.Center){
                        Text(textButton + "11,00€",
                            fontSize = MaterialTheme.typography.bodyLarge.fontSize,
                            color = MaterialTheme.colorScheme.onPrimaryContainer
                        )
                        Spacer(Modifier.size(3.dp))
                        Checkbox(checked, {checked = !checked}, )
                    }*/
                    /*Text(textButton + "11,00€",
                        fontSize = MaterialTheme.typography.bodyLarge.fontSize,
                        color = MaterialTheme.colorScheme.onPrimaryContainer
                    )*/
                    /*Text(textButton + " u",
                        fontSize = MaterialTheme.typography.bodyLargeEmphasized.fontSize,
                        color = MaterialTheme.colorScheme.onPrimaryContainer
                    )*/
                    Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.Center){
                        IconButton({}) { Icon(
                            Icons.Filled.Remove,
                            contentDescription = "prova",
                            modifier = Modifier.width(20.dp).height(20.dp),
                            tint = MaterialTheme.colorScheme.onPrimaryContainer)
                        }
                        Text(textButton + " u",
                            fontSize = MaterialTheme.typography.bodyLarge.fontSize,
                            color = MaterialTheme.colorScheme.onPrimaryContainer
                        )
                        IconButton({}) { Icon(
                            Icons.Filled.Add,
                            contentDescription = "prova",
                            modifier = Modifier.width(20.dp).height(20.dp),
                            tint = MaterialTheme.colorScheme.onPrimaryContainer)
                        }
                    }
                }
            )

        }
        if (showDialog) {
            AlertDialog(contentPadding, { showDialog = false }, itemsList)
        }
    }
}






