package com.example.myapplication.ui.screen

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.calculateEndPadding
import androidx.compose.foundation.layout.calculateStartPadding
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Air
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Money
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.focusModifier
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import com.example.myapplication.ui.component.StatisticCard
import com.example.myapplication.ui.component.TopAppBar
import com.example.myapplication.ui.theme.MyApplicationTheme

class StatisticsActivity : ComponentActivity(){
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MyApplicationTheme {
                Scaffold (Modifier.fillMaxSize()){ innerPadding ->
                    Scaffold( modifier = Modifier.padding(bottom = innerPadding.calculateBottomPadding()).fillMaxSize(),
                        topBar = { TopAppBar("Statistiche", {Icon(Icons.Filled.ArrowBack, "Back", tint = MaterialTheme.colorScheme.onPrimary)},{})}
                    ){ contentPadding ->
                        Column (
                            modifier = Modifier
                                .padding(
                                    top = innerPadding.calculateTopPadding(),
                                    start = contentPadding.calculateStartPadding(LayoutDirection.Ltr),
                                    end = contentPadding.calculateEndPadding(LayoutDirection.Ltr)
                                ).fillMaxSize(),
                        ) {
                            StatisticCard("Storico Prezzi Materiali", Icons.Filled.Money, "Price Statistics", contentPadding, {})
                            Spacer(Modifier.size(15.dp))
                            StatisticCard("Sommario Interventi", Icons.Filled.Air, "Jobs Statistics", contentPadding, {})
                            Spacer(Modifier.size(15.dp))
                            StatisticCard("Ricavo Interventi", Icons.Filled.Money, "Jobs Revenue", contentPadding, {})
                            Spacer(Modifier.size(15.dp))
                            StatisticCard("Tempi Medi Di Riscossione", Icons.Filled.Air, "Average Collection Times", contentPadding, {})
                            Spacer(Modifier.size(15.dp))
                            StatisticCard("Numero Interventi", Icons.Filled.Money, "Number Of Jobs Per Reference", contentPadding, {})
                        }
                    }
                }
            }
        }
    }
}