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
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.CenterAlignedTopAppBar
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
import androidx.compose.material3.TopAppBarDefaults
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.LayoutDirection
import com.example.myapplication.R
import com.example.myapplication.ui.component.Avatar
import com.example.myapplication.ui.component.CheckLazyList
import com.example.myapplication.ui.component.CustomDropDownMenu
import com.example.myapplication.ui.component.CustomersCardsList
import com.example.myapplication.ui.component.DropDownMenuHome
import com.example.myapplication.ui.component.HomeCard
import com.example.myapplication.ui.component.SearchAppBar
import com.example.myapplication.ui.component.SplitButtonMenu
import com.example.myapplication.ui.component.ToggleIconButton
import com.example.myapplication.ui.theme.MyApplicationTheme

val itemsList = List<String>(100, {item -> "item$item"})

@Composable
fun Screen(modifier : Modifier){

    val ctx = LocalContext.current

    Scaffold (
        modifier = Modifier.fillMaxSize(),
        topBar = {TopAppBar("Screen",{ DropDownMenuHome()} )},
        floatingActionButton = {AddButton("Screen"){} }
    ){ contentPadding ->
        Column (
            modifier = Modifier.fillMaxSize().padding(contentPadding),
        ) {
            SearchAppBar("Screen", contentPadding)
            Spacer(Modifier.size(4.dp))
            SplitButtonMenu(content = "Venditori", contextPadding = contentPadding)
            //CheckLazyList(itemsList, contentPadding)

            /*Fare prova se un in uno switch si possono mettere cose diverse per la topapp bar della home */
        }
    }
}

