package com.example.myapplication.ui.screen

import android.widget.Button
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExperimentalMaterial3ExpressiveApi
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.myapplication.ui.component.AddButton
import com.example.myapplication.ui.component.CardItemAvatar
import com.example.myapplication.ui.component.DeleteButton
import com.example.myapplication.ui.component.SearchAppBar
import com.example.myapplication.ui.component.TopAppBar

import androidx.compose.material3.SplitButtonLayout

@OptIn(ExperimentalMaterial3ExpressiveApi::class)
@ExperimentalMaterial3Api
@Composable
fun Screen(modifier : Modifier){

    Scaffold (
        modifier = Modifier.fillMaxSize(),
        topBar = {TopAppBar("Screen")},
        floatingActionButton = {AddButton("Screen"){} }
    ){ contentPadding ->

        Column (
            modifier = Modifier.fillMaxSize(),
        ) {
            SearchAppBar("Screen", contentPadding)
            DeleteButton("Screen"){}
            Row {
                Icon(Icons.Filled.ArrowDropDown, "Drop-DownMenu")
                CardItemAvatar("A"){}

            }
            Spacer(Modifier.height(8.dp))
            /*SplitButtonLayout(leadingButton = {
                    IconButton(onClick = {}, modifier = Modifier.fillMaxSize(), enabled =   true,
                colors = IconButtonDefaults.iconButtonColors(MaterialTheme.colorScheme.secondary)){
                        Icon(Icons.Filled.ArrowDropDown, "Drop-DownMenu")}
            }, trailingButton = {})*/
        }
    }
}


