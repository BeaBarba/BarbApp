package com.example.myapplication.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import com.example.myapplication.ui.screen.Bubble.allSummary.AllBubblesSummaryActivity
import com.example.myapplication.ui.screen.Job.AllCleaningSummaryActivity
import com.example.myapplication.ui.screen.Customer.allSummary.AllCustomersSummaryActivity
import com.example.myapplication.ui.screen.Deadline.AllDeadlinesSummaryActivity
import com.example.myapplication.ui.screen.Job.AllJobsSummaryActivity
import com.example.myapplication.ui.screen.Payment.AllPaymentsSummaryActivity
import com.example.myapplication.ui.screen.Statistics.AllStatisticsActivity
import com.example.myapplication.ui.screen.Statistics.AveragePaymentsTimesStatisticsActivity
import com.example.myapplication.ui.screen.BubbleMaterialsActivity
import com.example.myapplication.ui.screen.Material.CartActivity
import com.example.myapplication.ui.screen.Customer.CustomerAddActivity
import com.example.myapplication.ui.screen.Calendar.day.DayCalendarActivity
import com.example.myapplication.ui.screen.Deadline.DeadlineAddActivity
import com.example.myapplication.ui.screen.HomeActivity
import com.example.myapplication.ui.screen.Job.add.JobAddActivity
import com.example.myapplication.ui.screen.Job.JobMaterialsActivity
import com.example.myapplication.ui.screen.Statistics.JobStatisticsActivity
import com.example.myapplication.ui.screen.Material.MaterialAddActivity
import com.example.myapplication.ui.screen.Payment.PaymentAddActivity
import com.example.myapplication.debug.Screen
import com.example.myapplication.ui.screen.Address.add.AddressAddActivity
import com.example.myapplication.ui.screen.Select.SelectActivity
import com.example.myapplication.ui.screen.Address.singleSummary.SingleAddressSummaryActivity
import com.example.myapplication.ui.screen.Address.add.AddressAddViewModel
import com.example.myapplication.ui.screen.Address.singleSummary.SingleAddressSummaryViewModel
import com.example.myapplication.ui.screen.Bubble.singleSummary.SingleBubbleSummaryActivity
import com.example.myapplication.ui.screen.Bubble.add.BubbleAddViewModel
import com.example.myapplication.ui.screen.Bubble.allSummary.AllBubblesSummaryViewModel
import com.example.myapplication.ui.screen.Bubble.bubbleAddActivity.BubbleAddActivity
import com.example.myapplication.ui.screen.Bubble.singleSummary.SingleBubbleSummaryViewModel
import com.example.myapplication.ui.screen.Customer.singleSummary.SingleCustomerSummaryActivity
import com.example.myapplication.ui.screen.Deadline.SingleDeadlineSummaryActivity
import com.example.myapplication.ui.screen.Job.singleSummary.SingleJobSummaryActivity
import com.example.myapplication.ui.screen.Material.SingleMaterialSummaryActivity
import com.example.myapplication.ui.screen.Payment.SinglePaymentSummaryActivity
import com.example.myapplication.ui.screen.Calendar.today.TodayCalendarActivity
import com.example.myapplication.ui.screen.Calendar.day.DayCalendarViewModel
import com.example.myapplication.ui.screen.Calendar.today.TodayCalendarViewModel
import com.example.myapplication.ui.screen.Construction.AllConstructionSummaryActivity
import com.example.myapplication.ui.screen.Construction.ConstructionAddActivity
import com.example.myapplication.ui.screen.Construction.SingleConstructionSummaryActivity
import com.example.myapplication.ui.screen.Customer.allSummary.AllCustomersSummaryViewModel
import com.example.myapplication.ui.screen.Customer.singleSummary.SingleCustomerSummaryViewModel
import com.example.myapplication.ui.screen.Invoice.AllInvoicesSummaryActivity
import com.example.myapplication.ui.screen.Invoice.InvoiceAddActivity
import com.example.myapplication.ui.screen.Invoice.SingleInvoiceSummaryActivity
import com.example.myapplication.ui.screen.Job.add.JobAddViewModel
import com.example.myapplication.ui.screen.Material.WarehouseActivity
import com.example.myapplication.ui.screen.PurchaseInvoice.AllPurchaseInvoicesSummaryActivity
import com.example.myapplication.ui.screen.PurchaseInvoice.PurchaseInvoiceAddActivity
import com.example.myapplication.ui.screen.PurchaseInvoice.SinglePurchaseInvoiceSummaryActivity
import kotlinx.serialization.Serializable
import org.koin.androidx.compose.koinViewModel

sealed interface NavigationRoute{
    @Serializable
    data object AllBubblesSummary : NavigationRoute
    @Serializable
    data object AllCleaningSummary : NavigationRoute
    @Serializable
    data object AllConstructionSummary : NavigationRoute
    @Serializable
    data object AllCustomersSummary : NavigationRoute
    @Serializable
    data object AllDeadlinesSummary : NavigationRoute
    @Serializable
    data object AllInvoicesSummary : NavigationRoute
    @Serializable
    data object AllJobsSummary : NavigationRoute
    @Serializable
    data object AllPaymentsSummary : NavigationRoute
    @Serializable
    data object AllPurchaseInvoicesSummary : NavigationRoute
    @Serializable
    data object AllStatistics : NavigationRoute
    @Serializable
    data object AveragePaymentsTimesStatistics : NavigationRoute
    @Serializable
    data object Cart : NavigationRoute
    @Serializable
    data object Home : NavigationRoute
    @Serializable
    data object JobMaterials : NavigationRoute
    @Serializable
    data object JobStatistics : NavigationRoute
    @Serializable
    data object ConstructionAdd : NavigationRoute
    @Serializable
    data object CustomerAdd : NavigationRoute
    @Serializable
    data object DeadlineAdd : NavigationRoute
    @Serializable
    data object InvoiceAdd : NavigationRoute
    @Serializable
    data object JobAdd : NavigationRoute
    @Serializable
    data object MaterialAdd : NavigationRoute
    @Serializable
    data object PaymentAdd : NavigationRoute
    @Serializable
    data object PurchaseInvoiceAdd : NavigationRoute
    @Serializable
    data object SingleConstructionSummary : NavigationRoute
    @Serializable
    data object SingleDeadlineSummary: NavigationRoute
    @Serializable
    data object SingleInvoiceSummary : NavigationRoute
    @Serializable
    data object SingleJobSummary : NavigationRoute
    @Serializable
    data object SingleMaterialSummary : NavigationRoute
    @Serializable
    data object SinglePaymentSummary : NavigationRoute
    @Serializable
    data object SinglePurchaseInvoiceSummary : NavigationRoute
    @Serializable
    data object TodayCalendar : NavigationRoute
    @Serializable
    data object Warehouse : NavigationRoute

    @Serializable
    data class Select(val textSearch : String, val entry: String) : NavigationRoute
    @Serializable
    data class AddressAdd(val addressId: Int?) : NavigationRoute
    @Serializable
    data class SingleAddressSummary(val addressId: Int)  : NavigationRoute
    @Serializable
    data class BubbleAdd(val bubbleId: Int?) : NavigationRoute
    @Serializable
    data class SingleBubbleSummary(val bubbleId: Int) : NavigationRoute
    @Serializable
    data class SingleCustomerSummary (val customerId : String) : NavigationRoute
    @Serializable
    data class DayCalendar (val date : Long) : NavigationRoute

    @Serializable
    data object Screen : NavigationRoute
    @Serializable
    data object BubbleMaterials : NavigationRoute
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
            val allBubblesSummaryVM = koinViewModel<AllBubblesSummaryViewModel>()
            val state by allBubblesSummaryVM.state.collectAsStateWithLifecycle()
            AllBubblesSummaryActivity(state, allBubblesSummaryVM.actions, navController)
        }
        composable<NavigationRoute.AllCleaningSummary>{
            AllCleaningSummaryActivity(navController)
        }
        composable<NavigationRoute.AllCustomersSummary>{
            val allCustomersSummaryVM = koinViewModel<AllCustomersSummaryViewModel>()
            val state by allCustomersSummaryVM.state.collectAsStateWithLifecycle()
            AllCustomersSummaryActivity(state, allCustomersSummaryVM.actions, navController)
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
        composable<NavigationRoute.BubbleAdd>{backStackEntry ->
            val route = backStackEntry.toRoute<NavigationRoute.BubbleAdd>()
            val bubbleAddVM = koinViewModel<BubbleAddViewModel>()
            val state by bubbleAddVM.state.collectAsStateWithLifecycle()
            BubbleAddActivity(route.bubbleId, state, bubbleAddVM.actions, navController)
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
        composable<NavigationRoute.DayCalendar>{backStackEntry ->
            val route = backStackEntry.toRoute<NavigationRoute.DayCalendar>()
            val dayCalendarVM = koinViewModel<DayCalendarViewModel>()
            val state by dayCalendarVM.state.collectAsStateWithLifecycle()
            DayCalendarActivity(route.date, state, dayCalendarVM.actions, navController)

        }
        composable<NavigationRoute.DeadlineAdd>{
            DeadlineAddActivity(navController)
        }
        composable<NavigationRoute.Home>{
            HomeActivity(navController)
        }
        composable<NavigationRoute.JobAdd>{
            val singleJobVM = koinViewModel<JobAddViewModel>()
            val state by singleJobVM.state.collectAsStateWithLifecycle()
            JobAddActivity(state, navController)
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
        composable<NavigationRoute.SingleBubbleSummary>{backStackEntry ->
            val route = backStackEntry.toRoute<NavigationRoute.SingleBubbleSummary>()
            val singleBubbleVM = koinViewModel<SingleBubbleSummaryViewModel>()
            val state by singleBubbleVM.state.collectAsStateWithLifecycle()
            SingleBubbleSummaryActivity(route.bubbleId, state, singleBubbleVM.actions, navController)
        }
        composable<NavigationRoute.SingleCustomerSummary>{backStackEntry ->
            val route = backStackEntry.toRoute<NavigationRoute.SingleCustomerSummary>()
            val singleCustomerSummaryVM = koinViewModel<SingleCustomerSummaryViewModel>()
            val state by singleCustomerSummaryVM.state.collectAsStateWithLifecycle()
            SingleCustomerSummaryActivity(route.customerId, state, singleCustomerSummaryVM.actions, navController)
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
            val todayCalendarVM = koinViewModel<TodayCalendarViewModel>()
            val state by todayCalendarVM.state.collectAsStateWithLifecycle()
            TodayCalendarActivity(state, todayCalendarVM.actions, navController)
        }
        composable<NavigationRoute.Warehouse> {
            WarehouseActivity(navController)
        }
        composable<NavigationRoute.Select> { backStackEntry ->
            val route = backStackEntry.toRoute<NavigationRoute.Select>()
            SelectActivity(route.textSearch, route.entry, navController)
        }
        composable<NavigationRoute.AddressAdd> {backStackEntry ->
            val route = backStackEntry.toRoute<NavigationRoute.AddressAdd>()
            val addressAddVM = koinViewModel<AddressAddViewModel>()
            val state by addressAddVM.state.collectAsStateWithLifecycle()
            AddressAddActivity(route.addressId, state, addressAddVM.actions, navController)
        }
        composable<NavigationRoute.SingleAddressSummary> {backStackEntry ->
            val route = backStackEntry.toRoute<NavigationRoute.SingleAddressSummary>()
            val singleAddressVM = koinViewModel<SingleAddressSummaryViewModel>()
            val state by singleAddressVM.state.collectAsStateWithLifecycle()
            SingleAddressSummaryActivity(route.addressId, state, singleAddressVM.actions, navController)
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
        composable<NavigationRoute.SinglePurchaseInvoiceSummary>{
            SinglePurchaseInvoiceSummaryActivity(navController)
        }
    }
}