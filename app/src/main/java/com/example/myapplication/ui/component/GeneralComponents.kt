package com.example.myapplication.ui.component

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonColors
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.contentColorFor
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.myapplication.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopAppBar(id: String){
    CenterAlignedTopAppBar(
        navigationIcon = {
            IconButton(
                onClick = {}
            ){
                if(id.equals("Home")){
                        Icon(
                            Icons.Filled.Settings, "Settings",
                            tint = MaterialTheme.colorScheme.onPrimary
                        )
                }else {
                    Icon(
                        Icons.AutoMirrored.Filled.ArrowBack, "Back",
                        tint = MaterialTheme.colorScheme.onPrimary
                    )
                }
            }
        },
        title = {
            Text(
                text = id,
                fontWeight = FontWeight.Medium,
                color = MaterialTheme.colorScheme.onPrimary
            ) },
        colors = TopAppBarDefaults.topAppBarColors(
        containerColor = MaterialTheme.colorScheme.primary
        ),
        actions = {
            IconButton(
                onClick = {},
            ) {
                when{
                    id.equals("Home") ->
                        Icon(
                            Icons.Filled.ShoppingCart,
                            "Back",
                            tint = MaterialTheme.colorScheme.onPrimary
                        )
                    id.equals("Custumers")
                            || id.equals("Wrench")
                            || id.equals("Vacuum")
                            || id.equals("Receipt") ->
                        Icon(
                            painterResource(id=R.drawable.filter_alt_24dp),
                            "Filter",
                            tint = MaterialTheme.colorScheme.onPrimary
                        )
                    id.equals("ItemA") ->
                        Icon(
                            painterResource(id= R.drawable.edit_square_24dp),
                            "Edit",
                            tint = MaterialTheme.colorScheme.onPrimary
                        )
                    id.equals("Check") ->
                        Icon(Icons.Filled.Check,
                            "Check",
                            tint = MaterialTheme.colorScheme.onPrimary)
                }

/*
                Icon(
                    painterResource(id= R.drawable.note_add_24dp),
                    "Add Note",
                    tint = MaterialTheme.colorScheme.onPrimary
                )
                */


            }
        }
    )
}

@Composable
fun AddButton(id : String){
    FloatingActionButton(
        onClick = {},
        modifier = Modifier.size(80.dp).padding(8.dp).fillMaxSize(),
        containerColor = MaterialTheme.colorScheme.primary,
        contentColor = MaterialTheme.colorScheme.onPrimary
    ) {
        Icon(
            Icons.Filled.Add,
            "Add item"
        )
    }
}

@Composable
fun DeleteButton(id: String){
    IconButton(
        onClick = {},
        modifier = Modifier
            .border( border = BorderStroke(15.dp, color = MaterialTheme.colorScheme.background),
                    shape = RoundedCornerShape(30.dp))
            .fillMaxWidth()
            .height(80.dp),
        enabled =   true,
        colors = IconButtonDefaults.iconButtonColors(MaterialTheme.colorScheme.secondary)
    ) {
        Row{
           Icon(
               painter = painterResource(R.drawable.delete_24dp),
               contentDescription = "Delete",
               tint = MaterialTheme.colorScheme.onSecondary
           )
            Spacer(Modifier.size(8.dp))
            Text(
                text = "Elimina",
                color = MaterialTheme.colorScheme.onSecondary
            )
        }
    }
}