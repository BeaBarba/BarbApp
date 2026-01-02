package com.example.myapplication.ui.component

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.myapplication.ui.screen.AllBubblesSummaryActivity
import com.example.myapplication.ui.screen.AllCleaningSummaryActivity
import com.example.myapplication.ui.screen.AllCustomersSummaryActivity
import com.example.myapplication.ui.screen.AllDeadlinesSummaryActivity
import com.example.myapplication.ui.screen.AllJobsSummaryActivity
import com.example.myapplication.ui.screen.AllPaymentsSummaryActivity
import com.example.myapplication.ui.screen.AllStatisticsActivity
import com.example.myapplication.ui.screen.AveragePaymentsTimesStatisticsActivity
import com.example.myapplication.ui.screen.BubbleAddActivity
import com.example.myapplication.ui.screen.BubbleMaterialsActivity
import com.example.myapplication.ui.screen.CartActivity
import com.example.myapplication.ui.screen.CustomerAddActivity
import com.example.myapplication.ui.screen.DayCalendarActivity
import com.example.myapplication.ui.screen.DeadlineAddActivity
import com.example.myapplication.ui.screen.HomeActivity
import com.example.myapplication.ui.screen.JobAddActivity
import com.example.myapplication.ui.screen.JobMaterialsActivity
import com.example.myapplication.ui.screen.JobStatisticsActivity
import com.example.myapplication.ui.screen.MaterialAddActivity
import com.example.myapplication.ui.screen.PaymentAddActivity
import com.example.myapplication.ui.screen.Screen
import com.example.myapplication.ui.screen.SingleBubbleSummaryActivity
import com.example.myapplication.ui.screen.SingleCustomerSummaryActivity
import com.example.myapplication.ui.screen.SingleDeadlineSummaryActivity
import com.example.myapplication.ui.screen.SingleJobSummaryActivity
import com.example.myapplication.ui.screen.SingleMaterialSummaryActivity
import com.example.myapplication.ui.screen.SinglePaymentSummaryActivity
import com.example.myapplication.ui.screen.TodayCalendarActivity
import com.example.myapplication.ui.screen.WarehouseActivity
import kotlinx.serialization.Serializable

sealed interface NavigationRoute{
    @Serializable
    data object AllBubblesSummary : NavigationRoute
    @Serializable
    data object AllCleaningSummary : NavigationRoute
    @Serializable
    data object AllCustomersSummary : NavigationRoute
    @Serializable
    data object AllDeadlinesSummary : NavigationRoute
    @Serializable
    data object AllJobsSummary : NavigationRoute
    @Serializable
    data object AllPaymentsSummary : NavigationRoute
    @Serializable
    data object AllStatistics : NavigationRoute
    @Serializable
    data object AveragePaymentsTimesStatistics : NavigationRoute
    @Serializable
    data object BubbleAdd : NavigationRoute
    @Serializable
    data object BubbleMaterials : NavigationRoute
    @Serializable
    data object Cart : NavigationRoute
    @Serializable
    data object CustomerAdd : NavigationRoute
    @Serializable
    data object DayCalendar : NavigationRoute
    @Serializable
    data object DeadlineAdd : NavigationRoute
    @Serializable
    data object Home : NavigationRoute
    @Serializable
    data object JobAdd : NavigationRoute
    @Serializable
    data object JobMaterials : NavigationRoute
    @Serializable
    data object JobStatistics : NavigationRoute
    @Serializable
    data object MaterialAdd : NavigationRoute
    @Serializable
    data object PaymentAdd : NavigationRoute
    @Serializable
    data object SingleBubbleSummary : NavigationRoute
    @Serializable
    data object SingleCustomerSummary : NavigationRoute
    @Serializable
    data object SingleDeadlineSummary: NavigationRoute
    @Serializable
    data object SingleJobSummary : NavigationRoute
    @Serializable
    data object SingleMaterialSummary : NavigationRoute
    @Serializable
    data object SinglePaymentSummary : NavigationRoute
    @Serializable
    data object TodayCalendar : NavigationRoute
    @Serializable
    data object Warehouse : NavigationRoute
    @Serializable
    data object Screen : NavigationRoute
}

@Composable
fun NavGraph(
    navController : NavHostController,
    modifier : Modifier
){
    NavHost(
        navController = navController,
        modifier = modifier,
        startDestination = NavigationRoute.Home
    ){
        composable<NavigationRoute.Screen> {
            Screen(modifier)
        }
        composable<NavigationRoute.AllBubblesSummary>{
            AllBubblesSummaryActivity()
        }
        composable<NavigationRoute.AllCleaningSummary>{
            AllCleaningSummaryActivity()
        }
        composable<NavigationRoute.AllCustomersSummary>{
            AllCustomersSummaryActivity()
        }
        composable<NavigationRoute.AllDeadlinesSummary>{
            AllDeadlinesSummaryActivity()
        }
        composable<NavigationRoute.AllJobsSummary>{
            AllJobsSummaryActivity(navController)
        }
        composable<NavigationRoute.AllPaymentsSummary>{
            AllPaymentsSummaryActivity()
        }
        composable<NavigationRoute.AllStatistics>{
            AllStatisticsActivity()
        }
        composable<NavigationRoute.AveragePaymentsTimesStatistics>{
            AveragePaymentsTimesStatisticsActivity()
        }
        composable<NavigationRoute.BubbleAdd>{
            BubbleAddActivity()
        }
        composable<NavigationRoute.BubbleMaterials>{
            BubbleMaterialsActivity()
        }
        composable<NavigationRoute.Cart>{
            CartActivity(navController)
        }
        composable<NavigationRoute.CustomerAdd>{
            CustomerAddActivity()
        }
        composable<NavigationRoute.DayCalendar>{
            DayCalendarActivity()
        }
        composable<NavigationRoute.DeadlineAdd>{
            DeadlineAddActivity()
        }
        composable<NavigationRoute.Home>{
            HomeActivity(modifier, navController)
        }
        composable<NavigationRoute.JobAdd>{
            JobAddActivity(navController)
        }
        composable<NavigationRoute.JobMaterials>{
            JobMaterialsActivity(navController)
        }
        composable<NavigationRoute.JobStatistics>{
            JobStatisticsActivity()
        }
        composable<NavigationRoute.MaterialAdd>{
            MaterialAddActivity()
        }
        composable<NavigationRoute.PaymentAdd>{
            PaymentAddActivity()
        }
        composable<NavigationRoute.SingleBubbleSummary>{
            SingleBubbleSummaryActivity()
        }
        composable<NavigationRoute.SingleCustomerSummary>{
            SingleCustomerSummaryActivity()
        }
        composable<NavigationRoute.SingleDeadlineSummary>{
            SingleDeadlineSummaryActivity()
        }
        composable<NavigationRoute.SingleJobSummary>{
            SingleJobSummaryActivity(navController)
        }
        composable<NavigationRoute.SingleMaterialSummary>{
            SingleMaterialSummaryActivity()
        }
        composable<NavigationRoute.SinglePaymentSummary>{
            SinglePaymentSummaryActivity()
        }
        composable<NavigationRoute.TodayCalendar>{
            TodayCalendarActivity()
        }
        composable<NavigationRoute.Warehouse> {
            WarehouseActivity()
        }
    }
}