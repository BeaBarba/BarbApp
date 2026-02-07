package com.example.myapplication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.compose.rememberNavController
import com.example.myapplication.data.modules.Theme
import com.example.myapplication.ui.NavGraph
import com.example.myapplication.ui.screen.Home.HomeViewModel
import com.example.myapplication.ui.theme.MyApplicationTheme
import org.koin.androidx.compose.koinViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val homeVM: HomeViewModel = koinViewModel()
            val themeState by homeVM.state.collectAsStateWithLifecycle()

            val theme = when (themeState.theme) {
                Theme.Light -> false
                Theme.Dark -> true
                Theme.System -> isSystemInDarkTheme()
            }
            MyApplicationTheme(darkTheme = theme) {
                val navController = rememberNavController()
                NavGraph(navController, Modifier)
            }
        }
    }
}