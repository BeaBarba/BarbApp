package com.example.myapplication.ui.screen

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.calculateEndPadding
import androidx.compose.foundation.layout.calculateStartPadding
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import com.example.myapplication.ui.component.AddButton
import com.example.myapplication.ui.component.BackButton
import com.example.myapplication.ui.component.CustomersCardsList
import com.example.myapplication.ui.component.DropDownMenuCustomers
import com.example.myapplication.ui.component.SearchAppBar
import com.example.myapplication.ui.component.TopAppBar
import com.example.myapplication.ui.theme.MyApplicationTheme

class AllCustomersSummaryActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MyApplicationTheme {
                var ctx = LocalContext.current

                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    topBar = {
                        TopAppBar(
                            id = "Clienti",
                            navigationIcon = { BackButton() {} },
                            trailingIcon = { DropDownMenuCustomers() }
                        )
                    },
                    floatingActionButton = { AddButton() {} }
                ) { contentPadding ->
                    Column(
                        modifier = Modifier
                            .padding(
                                top = contentPadding.calculateTopPadding(),
                                start = contentPadding.calculateStartPadding(LayoutDirection.Ltr) + 8.dp,
                                end = contentPadding.calculateEndPadding(LayoutDirection.Ltr) + 8.dp
                            )
                            .fillMaxSize()
                    ) {
                        SearchAppBar("Cliente", contentPadding)
                        CustomersCardsList(letters, customers, ctx, contentPadding)
                    }
                }
            }
        }
    }
}