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
import com.example.myapplication.debug.seedDatabase
import com.example.myapplication.ui.NavGraph
import com.example.myapplication.ui.screen.Home.HomeViewModel
import com.example.myapplication.ui.theme.MyApplicationTheme
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
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
                // Ora seedCustomers è suspend, quindi aspetta qui
                seedDatabase.seedMaterials(repository)
                seedDatabase.seedAirConditioner(repository)
                seedDatabase.seedSeller(repository)
                seedDatabase.seedPurchaseInvoice(repository)
                seedDatabase.seedPurchase(repository)
                seedDatabase.seedBubble(repository)
                seedDatabase.seedDelivery(repository)
                seedDatabase.seedCategories(repository)
                seedDatabase.seedPayment(repository)
                seedDatabase.seedSingleExpense(repository)
                seedDatabase.seedRecurringExpense(repository)
                seedDatabase.seedRecurringPayment(repository)
                seedDatabase.seedAddress(repository)
                seedDatabase.seedReference(repository)
                seedDatabase.seedCustomers(repository)
                seedDatabase.seedPrivate(repository)
                seedDatabase.seedCompany(repository)
                seedDatabase.seedReferral(repository)
                seedDatabase.seedPhoneNumber(repository)
                seedDatabase.seedCustomerProvision(repository)
                seedDatabase.seedPropertyOwnership(repository)
                seedDatabase.seedWorksite(repository)
                android.util.Log.d("SEED", "Inizio interventi")
                seedDatabase.seedJob(repository)
                android.util.Log.d("SEED", "Inizio foto")
                seedDatabase.seedImage(repository)
                android.util.Log.d("SEED", "Inizio prenotazioni")
                seedDatabase.seedFutureJobMaterial(repository)
                android.util.Log.d("SEED", "Inizio utilizzi")
                seedDatabase.seedMaterialUsage(repository)
                android.util.Log.d("SEED", "Inizio ricavi")
                seedDatabase.seedRevenue(repository)
                println("DEBUG: Seed completato!")
            } catch (e: Exception) {
                // QUI vedrai finalmente l'errore (es. Foreign Key constraint failed)
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