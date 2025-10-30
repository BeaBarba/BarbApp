package com.example.myapplication.ui.screen

import android.content.Intent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.myapplication.ui.component.AddButton
import com.example.myapplication.ui.component.TopAppBar
import androidx.compose.ui.Alignment
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.LayoutDirection
import com.example.myapplication.ui.component.DeleteButton
import com.example.myapplication.ui.component.DropDownMenuCustumers
import com.example.myapplication.ui.component.SearchAppBar
import com.example.myapplication.ui.component.SplitButtonMenu

val itemsList = List<String>(5, {item -> "item $item"})

@Composable
fun Screen(modifier : Modifier){

    val ctx = LocalContext.current

    Scaffold (
        modifier = Modifier.fillMaxSize(),
        topBar = {TopAppBar("Screen",{},{ DropDownMenuCustumers() })},
        floatingActionButton = {AddButton("Screen"){
            val intent = Intent(ctx, HomeActivity ::class.java)
            ctx.startActivity(intent)
        } }
    ){ contentPadding ->
        Column (
            modifier = modifier
            /*
            modifier = Modifier*/
                .padding(
                    top = contentPadding.calculateTopPadding(),
                    start = contentPadding.calculateStartPadding(LayoutDirection.Ltr),
                    end = contentPadding.calculateEndPadding(LayoutDirection.Ltr)
                ).fillMaxSize(),/**/
        ) {
            SearchAppBar("Screen", contentPadding)
            Spacer(Modifier.size(8.dp))
            SplitButtonMenu(content = "Venditori", contentPadding = contentPadding)
            DeleteButton({},contexPadding = contentPadding)
            //CheckLazyList(itemsList, contentPadding)
            Spacer(Modifier.size(8.dp))



        }
    }
}


