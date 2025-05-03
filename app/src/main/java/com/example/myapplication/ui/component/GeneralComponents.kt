package com.example.myapplication.ui.component

import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.requiredHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
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