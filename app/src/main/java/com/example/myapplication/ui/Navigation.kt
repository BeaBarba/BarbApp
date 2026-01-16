package com.example.myapplication.ui

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import com.example.myapplication.ui.screen.Bubble.AllBubblesSummaryActivity
import com.example.myapplication.ui.screen.Job.AllCleaningSummaryActivity
import com.example.myapplication.ui.screen.Customer.AllCustomersSummaryActivity
import com.example.myapplication.ui.screen.Deadline.AllDeadlinesSummaryActivity
import com.example.myapplication.ui.screen.Job.AllJobsSummaryActivity
import com.example.myapplication.ui.screen.Payment.AllPaymentsSummaryActivity
import com.example.myapplication.ui.screen.Statistics.AllStatisticsActivity
import com.example.myapplication.ui.screen.Statistics.AveragePaymentsTimesStatisticsActivity
import com.example.myapplication.ui.screen.Bubble.BubbleAddActivity
import com.example.myapplication.ui.screen.BubbleMaterialsActivity
import com.example.myapplication.ui.screen.Material.CartActivity
import com.example.myapplication.ui.screen.Customer.CustomerAddActivity
import com.example.myapplication.ui.screen.Calendar.DayCalendarActivity
import com.example.myapplication.ui.screen.Deadline.DeadlineAddActivity
import com.example.myapplication.ui.screen.HomeActivity
import com.example.myapplication.ui.screen.Job.JobAddActivity
import com.example.myapplication.ui.screen.Job.JobMaterialsActivity
import com.example.myapplication.ui.screen.Statistics.JobStatisticsActivity
import com.example.myapplication.ui.screen.Material.MaterialAddActivity
import com.example.myapplication.ui.screen.Payment.PaymentAddActivity
import com.example.myapplication.debug.Screen
import com.example.myapplication.ui.screen.Address.AddressAddActivity
import com.example.myapplication.ui.screen.SelectActivity
import com.example.myapplication.ui.screen.Address.SingleAddressSummaryActivity
import com.example.myapplication.ui.screen.Bubble.SingleBubbleSummaryActivity
import com.example.myapplication.ui.screen.Customer.SingleCustomerSummaryActivity
import com.example.myapplication.ui.screen.Deadline.SingleDeadlineSummaryActivity
import com.example.myapplication.ui.screen.Job.SingleJobSummaryActivity
import com.example.myapplication.ui.screen.Material.SingleMaterialSummaryActivity
import com.example.myapplication.ui.screen.Payment.SinglePaymentSummaryActivity
import com.example.myapplication.ui.screen.Calendar.TodayCalendarActivity
import com.example.myapplication.ui.screen.Construction.AllConstructionSummaryActivity
import com.example.myapplication.ui.screen.Construction.ConstructionAddActivity
import com.example.myapplication.ui.screen.Construction.SingleConstructionSummaryActivity
import com.example.myapplication.ui.screen.Invoice.AllInvoicesSummaryActivity
import com.example.myapplication.ui.screen.Invoice.InvoiceAddActivity
import com.example.myapplication.ui.screen.Invoice.SingleInvoiceSummaryActivity
import com.example.myapplication.ui.screen.Material.WarehouseActivity
import com.example.myapplication.ui.screen.PurchaseInvoice.AllPurchaseInvoicesSummaryActivity
import com.example.myapplication.ui.screen.PurchaseInvoice.PurchaseInvoiceAddActivity
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
    data class Select(val textSearch : String, val entry : String,) : NavigationRoute
    @Serializable
    data object AddressAdd : NavigationRoute
    @Serializable
    data object Screen : NavigationRoute
    @Serializable
    data object  SingleAddressSummary : NavigationRoute
    @Serializable
    data object AllConstructionSummary : NavigationRoute
    @Serializable
    data object SingleConstructionSummary : NavigationRoute
    @Serializable
    data object ConstructionAdd : NavigationRoute
    @Serializable
    data object AllInvoicesSummary : NavigationRoute
    @Serializable
    data object InvoiceAdd : NavigationRoute
    @Serializable
    data object SingleInvoiceSummary : NavigationRoute
    @Serializable
    data object AllPurchaseInvoicesSummary : NavigationRoute
    @Serializable
    data object PurchaseInvoiceAdd : NavigationRoute
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
            AllBubblesSummaryActivity(navController)
        }
        composable<NavigationRoute.AllCleaningSummary>{
            AllCleaningSummaryActivity(navController)
        }
        composable<NavigationRoute.AllCustomersSummary>{
            AllCustomersSummaryActivity(navController)
        }
        composable<NavigationRoute.AllDeadlinesSummary>{
            AllDeadlinesSummaryActivity(navController)
        }
        composable<NavigationRoute.AllJobsSummary>{
            AllJobsSummaryActivity(navController)
        }
        composable<NavigationRoute.AllPaymentsSummary>{
            AllPaymentsSummaryActivity(navController)
        }
        composable<NavigationRoute.AllStatistics>{
            AllStatisticsActivity(navController)
        }
        composable<NavigationRoute.AveragePaymentsTimesStatistics>{
            AveragePaymentsTimesStatisticsActivity(navController)
        }
        composable<NavigationRoute.BubbleAdd>{
            BubbleAddActivity(navController)
        }
        composable<NavigationRoute.BubbleMaterials>{
            BubbleMaterialsActivity(navController)
        }
        composable<NavigationRoute.Cart>{
            CartActivity(navController)
        }
        composable<NavigationRoute.CustomerAdd>{
            CustomerAddActivity(navController)
        }
        composable<NavigationRoute.DayCalendar>{
            DayCalendarActivity(navController)
        }
        composable<NavigationRoute.DeadlineAdd>{
            DeadlineAddActivity(navController)
        }
        composable<NavigationRoute.Home>{
            HomeActivity(navController)
        }
        composable<NavigationRoute.JobAdd>{
            JobAddActivity(navController)
        }
        composable<NavigationRoute.JobMaterials>{
            JobMaterialsActivity(navController)
        }
        composable<NavigationRoute.JobStatistics>{
            JobStatisticsActivity(navController)
        }
        composable<NavigationRoute.MaterialAdd>{
            MaterialAddActivity(navController)
        }
        composable<NavigationRoute.PaymentAdd>{
            PaymentAddActivity(navController)
        }
        composable<NavigationRoute.SingleBubbleSummary>{
            SingleBubbleSummaryActivity(navController)
        }
        composable<NavigationRoute.SingleCustomerSummary>{
            SingleCustomerSummaryActivity(navController)
        }
        composable<NavigationRoute.SingleDeadlineSummary>{
            SingleDeadlineSummaryActivity(navController)
        }
        composable<NavigationRoute.SingleJobSummary>{
            SingleJobSummaryActivity(navController)
        }
        composable<NavigationRoute.SingleMaterialSummary>{
            SingleMaterialSummaryActivity(navController)
        }
        composable<NavigationRoute.SinglePaymentSummary>{
            SinglePaymentSummaryActivity(navController)
        }
        composable<NavigationRoute.TodayCalendar>{
            TodayCalendarActivity(navController)
        }
        composable<NavigationRoute.Warehouse> {
            WarehouseActivity(navController)
        }
        composable<NavigationRoute.Select> { backStackEntry ->
            val route = backStackEntry.toRoute<NavigationRoute.Select>()
            SelectActivity(route.textSearch, route.entry, navController)
        }
        composable<NavigationRoute.AddressAdd> {
            AddressAddActivity(navController)
        }
        composable<NavigationRoute.SingleAddressSummary> {
            SingleAddressSummaryActivity(navController)
        }
        composable<NavigationRoute.AllConstructionSummary> {
            AllConstructionSummaryActivity(navController)
        }
        composable<NavigationRoute.SingleConstructionSummary> {
            SingleConstructionSummaryActivity(navController)
        }
        composable<NavigationRoute.ConstructionAdd> {
            ConstructionAddActivity(navController)
        }
        composable<NavigationRoute.AllInvoicesSummary> {
            AllInvoicesSummaryActivity(navController)
        }
        composable<NavigationRoute.InvoiceAdd>{
            InvoiceAddActivity(navController)
        }
        composable<NavigationRoute.SingleInvoiceSummary>{
            SingleInvoiceSummaryActivity(navController)
        }
        composable<NavigationRoute.AllPurchaseInvoicesSummary>{
            AllPurchaseInvoicesSummaryActivity(navController)
        }
        composable<NavigationRoute.PurchaseInvoiceAdd>{
            PurchaseInvoiceAddActivity(navController)
        }
    }
}