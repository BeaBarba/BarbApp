package com.example.myapplication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.lifecycleScope
import androidx.navigation.compose.rememberNavController
import com.example.myapplication.data.modules.Theme
import com.example.myapplication.data.repository.Repository
import com.example.myapplication.debug.SeedDatabase
import com.example.myapplication.ui.NavGraph
import com.example.myapplication.ui.screen.Home.HomeViewModel
import com.example.myapplication.ui.theme.MyApplicationTheme
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.koin.android.ext.android.inject
import org.koin.androidx.compose.koinViewModel

class MainActivity : ComponentActivity() {

    private val repository : Repository by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        lifecycleScope.launch(Dispatchers.IO) {
            try {
                SeedDatabase.seedMaterials(repository)
                SeedDatabase.seedAirConditioner(repository)
                SeedDatabase.seedSeller(repository)
                SeedDatabase.seedPurchaseInvoice(repository)
                SeedDatabase.seedPurchase(repository)
                SeedDatabase.seedBubble(repository)
                SeedDatabase.seedDelivery(repository)
                SeedDatabase.seedCategories(repository)
                SeedDatabase.seedPayment(repository)
                SeedDatabase.seedSingleExpense(repository)
                SeedDatabase.seedRecurringExpense(repository)
                SeedDatabase.seedRecurringPayment(repository)
                SeedDatabase.seedAddress(repository)
                SeedDatabase.seedReference(repository)
                SeedDatabase.seedCustomers(repository)
                SeedDatabase.seedPrivate(repository)
                SeedDatabase.seedCompany(repository)
                SeedDatabase.seedReferral(repository)
                SeedDatabase.seedPhoneNumber(repository)
                SeedDatabase.seedCustomerProvision(repository)
                SeedDatabase.seedPropertyOwnership(repository)
                SeedDatabase.seedWorksite(repository)
                SeedDatabase.seedJob(repository)
                SeedDatabase.seedImage(repository)
                SeedDatabase.seedFutureJobMaterial(repository)
                SeedDatabase.seedMaterialUsage(repository)
                SeedDatabase.seedRevenue(repository)
                println("DEBUG: Main activity - Seed completato!")
            } catch (e: Exception) {
                android.util.Log.e("SEED_ERROR", "Errore durante il seeding", e)
            }
        }

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