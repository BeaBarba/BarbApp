package com.example.myapplication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import com.example.myapplication.ui.screen.CleaningScreen
import com.example.myapplication.ui.screen.CustomersScreen
import com.example.myapplication.ui.screen.HomeScreen
import com.example.myapplication.ui.screen.Screen
import com.example.myapplication.ui.theme.MyApplicationTheme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MyApplicationTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    val modifier = Modifier.padding(bottom = innerPadding.calculateBottomPadding())
                    //HomeScreen(modifier)
                    //CustomersScreen(modifier)
                    //CleaningScreen(modifier)
                    Screen(modifier)
                }
            }
        }
    }
}
