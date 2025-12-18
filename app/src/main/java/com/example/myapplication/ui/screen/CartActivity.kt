package com.example.myapplication.ui.screen

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.calculateEndPadding
import androidx.compose.foundation.layout.calculateStartPadding
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import com.example.myapplication.MainActivity
import com.example.myapplication.R
import com.example.myapplication.ui.component.AddButton
import com.example.myapplication.ui.component.BackButton
import com.example.myapplication.ui.component.CartCard
import com.example.myapplication.ui.component.CustomAlertDialog
import com.example.myapplication.ui.component.TopAppBar
import com.example.myapplication.ui.theme.MyApplicationTheme

class CartActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MyApplicationTheme {
                var ctx = LocalContext.current

                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    topBar = {
                        TopAppBar(id = "Carrello",
                            navigationIcon = { BackButton {val intent = Intent(ctx, MainActivity::class.java)
                                ctx.startActivity(intent)} },
                            trailingIcon = {
                                IconButton(onClick = {}) {
                                    Icon(
                                        painterResource(R.drawable.description_24dp),
                                        contentDescription = "Shop item",
                                        tint = MaterialTheme.colorScheme.onPrimary
                                    )
                                }
                            })
                    },
                    floatingActionButton = {AddButton(){} }
                ) { contentPadding ->
                    LazyColumn(
                        modifier = Modifier
                            .padding(
                                top = contentPadding.calculateTopPadding(),
                                start = contentPadding.calculateStartPadding(LayoutDirection.Ltr) + 8.dp,
                                end = contentPadding.calculateEndPadding(LayoutDirection.Ltr) + 8.dp,
                                bottom = contentPadding.calculateBottomPadding()
                            )
                            .fillMaxSize()
                    ) {
                        item{ Spacer(Modifier.size(10.dp))}
                        items(prodotti) { item ->
                            var showDialog by remember { mutableStateOf(false) }

                            CartCard(item, {showDialog = true})

                            if (showDialog) {
                                CustomAlertDialog(
                                    onDismiss = { showDialog = false },
                                    title = item.nome,
                                    subtitle = item.modello,
                                    content = itemsList.subList(0,5),
                                    type = item.tipo
                                )
                            }
                        }
                        item{Spacer(Modifier.size(80.dp))}
                    }
                }
            }
        }
    }
}
