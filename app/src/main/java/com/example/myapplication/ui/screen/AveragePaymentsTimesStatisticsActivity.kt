package com.example.myapplication.ui.screen

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
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import com.example.myapplication.ui.component.Avatar
import com.example.myapplication.ui.component.BackButton
import com.example.myapplication.ui.component.GenericCard
import com.example.myapplication.ui.component.TopAppBar
import com.example.myapplication.ui.theme.MyApplicationTheme

class AveragePaymentsTimesStatisticsActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MyApplicationTheme {
                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    topBar = {
                        TopAppBar(id = "Tempi Medi Di Riscossione",
                            navigationIcon = { BackButton {} }
                        )
                    }
                ) { contentPadding ->
                    LazyColumn(
                        modifier = Modifier
                            .padding(
                                top = contentPadding.calculateTopPadding() + 8.dp,
                                start = contentPadding.calculateStartPadding(LayoutDirection.Ltr) + 8.dp,
                                end = contentPadding.calculateEndPadding(LayoutDirection.Ltr) + 8.dp,
                                bottom = contentPadding.calculateBottomPadding()
                            )
                            .fillMaxSize()
                    ) {
                        items(pagamenti) {item ->
                            var difference = 30 - item.data.substring(0,2).toInt()
                            val check : Boolean = (difference < 20)
                            GenericCard(
                                type = if(check){"NONE"}else{"ALA"},
                                leadingContent = {
                                    Avatar(
                                        char = item.cliente.get(0),
                                        textColor = if(check){MaterialTheme.colorScheme.onPrimary}else{MaterialTheme.colorScheme.onSecondary},
                                        backgroundColor = if(check){MaterialTheme.colorScheme.primary}else{MaterialTheme.colorScheme.secondary}
                                    )
                                },
                                text = item.cliente,
                                trailingContent = {
                                    Text(
                                        text = difference.toString(),
                                        color = if(check){MaterialTheme.colorScheme.onPrimaryContainer}else{MaterialTheme.colorScheme.onSecondaryContainer}
                                    )
                                }
                            )
                            Spacer(Modifier.size(8.dp))
                        }
                    }
                }
            }
        }
    }
}