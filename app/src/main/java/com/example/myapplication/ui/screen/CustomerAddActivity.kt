package com.example.myapplication.ui.screen

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Toast
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
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import com.example.myapplication.ui.component.BackButton
import com.example.myapplication.ui.component.CustomOutlineTextField
import com.example.myapplication.ui.component.DeleteButton
import com.example.myapplication.ui.component.TitleLabel
import com.example.myapplication.ui.component.TopAppBar
import com.example.myapplication.ui.theme.MyApplicationTheme

class CustomerAddActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            var ctx = LocalContext.current
            MyApplicationTheme {
                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    topBar = {
                        TopAppBar(
                            id = "Cliente",
                            navigationIcon = {
                                BackButton {}
                            },
                            trailingIcon = {
                                IconButton(
                                    onClick = {},
                                    colors = IconButtonDefaults.iconButtonColors(contentColor = MaterialTheme.colorScheme.onPrimary)
                                ) {
                                    Icon(Icons.Filled.Check, "Save")
                                }
                            }
                        )
                    }
                ) { contentPadding ->
                    LazyColumn (
                        modifier = Modifier
                            .padding(
                                top = contentPadding.calculateTopPadding() + 8.dp,
                                start = contentPadding.calculateStartPadding(LayoutDirection.Ltr) + 8.dp,
                                end = contentPadding.calculateEndPadding(LayoutDirection.Ltr) + 8.dp,
                                bottom = contentPadding.calculateBottomPadding()
                            )
                            .fillMaxSize()
                    ) {
                        item {TitleLabel("Dati Anagrafici")}
                        item {CustomOutlineTextField("CF")}
                        item {CustomOutlineTextField("Nome")}
                        item {CustomOutlineTextField("Cognome")}
                        item {
                            val location = Uri.parse("geo:44.1391, 12.24315")
                            CustomOutlineTextField(
                                leadingIcon = {
                                    IconButton(
                                        onClick = {
                                            println("DEBUG: Bottone cliccato!")

                                            val intent = Intent(Intent.ACTION_VIEW, location)
                                            try {
                                                ctx.startActivity(intent)
                                            } catch (e: Exception) {
                                                Toast.makeText(ctx, "Nessuna applicazione di mappe trovata", Toast.LENGTH_SHORT).show()
                                            }
                                        }
                                    ) {
                                        Icon(Icons.Filled.LocationOn, contentDescription = "Address")
                                    }
                                },
                                label = "Indirizzo",
                                value = location.toString()
                            )
                        }
                        item {CustomOutlineTextField("Comune")}
                        item {CustomOutlineTextField("Provincia")}
                        item {CustomOutlineTextField("CAP")}
                        item {Spacer(Modifier.size(10.dp))}
                        item {DeleteButton({})}
                    }
                }
            }
        }
    }
}