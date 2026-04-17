package com.example.myapplication.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import com.example.myapplication.data.modules.DeadlineType
import com.example.myapplication.data.modules.SelectKey
import com.example.myapplication.ui.screen.Bubble.allSummary.AllBubblesSummaryActivity
import com.example.myapplication.ui.screen.Job.AllCleaningSummaryActivity
import com.example.myapplication.ui.screen.Customer.allSummary.AllCustomersSummaryActivity
import com.example.myapplication.ui.screen.Deadline.allSummary.AllDeadlinesSummaryActivity
import com.example.myapplication.ui.screen.Job.allSummary.AllJobsSummaryActivity
import com.example.myapplication.ui.screen.Payment.allSummary.AllPaymentsSummaryActivity
import com.example.myapplication.ui.screen.Statistics.AllStatisticsActivity
import com.example.myapplication.ui.screen.Statistics.AveragePaymentsTimesStatisticsActivity
import com.example.myapplication.ui.screen.Cart.CartActivity
import com.example.myapplication.ui.screen.Customer.add.CustomerAddActivity
import com.example.myapplication.ui.screen.Calendar.day.DayCalendarActivity
import com.example.myapplication.ui.screen.Deadline.add.DeadlineAddActivity
import com.example.myapplication.ui.screen.Home.HomeActivity
import com.example.myapplication.ui.screen.Job.add.JobAddActivity
import com.example.myapplication.ui.screen.Job.add.JobMaterialsActivity
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
import com.example.myapplication.ui.screen.Bubble.add.BubbleAddActivity
import com.example.myapplication.ui.screen.Bubble.singleSummary.SingleBubbleSummaryViewModel
import com.example.myapplication.ui.screen.Customer.singleSummary.SingleCustomerSummaryActivity
import com.example.myapplication.ui.screen.Deadline.singleSummary.SingleDeadlineSummaryActivity
import com.example.myapplication.ui.screen.Job.singleSummary.SingleJobSummaryActivity
import com.example.myapplication.ui.screen.Material.singleSummary.SingleMaterialSummaryActivity
import com.example.myapplication.ui.screen.Payment.singleSummary.SinglePaymentSummaryActivity
import com.example.myapplication.ui.screen.Calendar.today.TodayCalendarActivity
import com.example.myapplication.ui.screen.Calendar.day.DayCalendarViewModel
import com.example.myapplication.ui.screen.Calendar.today.TodayCalendarViewModel
import com.example.myapplication.ui.screen.Cart.CartViewModel
import com.example.myapplication.ui.screen.WorkSite.AllConstructionSummaryActivity
import com.example.myapplication.ui.screen.WorkSite.ConstructionAddActivity
import com.example.myapplication.ui.screen.WorkSite.SingleConstructionSummaryActivity
import com.example.myapplication.ui.screen.Customer.add.CustomerAddViewModel
import com.example.myapplication.ui.screen.Customer.allSummary.AllCustomersSummaryViewModel
import com.example.myapplication.ui.screen.Customer.singleSummary.SingleCustomerSummaryViewModel
import com.example.myapplication.ui.screen.Deadline.add.DeadlineAddViewModel
import com.example.myapplication.ui.screen.Deadline.allSummary.AllDeadlinesSummaryViewModel
import com.example.myapplication.ui.screen.Deadline.singleSummary.SingleDeadlineSummaryViewModel
import com.example.myapplication.ui.screen.Home.HomeViewModel
import com.example.myapplication.ui.screen.Invoice.allSummary.AllInvoicesSummaryActivity
import com.example.myapplication.ui.screen.Invoice.InvoiceAddActivity
import com.example.myapplication.ui.screen.Invoice.singleSummary.SingleInvoiceSummaryActivity
import com.example.myapplication.ui.screen.Invoice.allSummary.AllInvoicesSummaryViewModel
import com.example.myapplication.ui.screen.Invoice.singleSummary.SingleInvoiceSummaryViewModel
import com.example.myapplication.ui.screen.Job.add.JobAddViewModel
import com.example.myapplication.ui.screen.Job.allSummary.AllJobsSummaryViewModel
import com.example.myapplication.ui.screen.Job.singleSummary.SingleJobSummaryViewModel
import com.example.myapplication.ui.screen.Material.allSummary.WarehouseActivity
import com.example.myapplication.ui.screen.Material.singleSummary.SingleMaterialSummaryViewModel
import com.example.myapplication.ui.screen.Payment.allSummary.AllPaymentsSummaryViewModel
import com.example.myapplication.ui.screen.Payment.singleSummary.SinglePaymentSummaryViewModel
import com.example.myapplication.ui.screen.PurchaseInvoice.allSummary.AllPurchaseInvoicesSummaryActivity
import com.example.myapplication.ui.screen.PurchaseInvoice.PurchaseInvoiceAddActivity
import com.example.myapplication.ui.screen.PurchaseInvoice.SinglePurchaseInvoiceSummaryActivity
import com.example.myapplication.ui.screen.PurchaseInvoice.allSummary.AllPurchaseInvoicesSummaryViewModel
import com.example.myapplication.ui.screen.Select.SelectViewModel
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
    data object WorkSiteAdd : NavigationRoute
    @Serializable
    data object MaterialAdd : NavigationRoute
    @Serializable
    data object PaymentAdd : NavigationRoute
    @Serializable
    data object TodayCalendar : NavigationRoute
    @Serializable
    data object Warehouse : NavigationRoute

    @Serializable
    data class AddressAdd(val addressId : Int?) : NavigationRoute
    @Serializable
    data class BubbleAdd(val bubbleId: Int?) : NavigationRoute
    @Serializable
    data class CustomerAdd(val customerId: String?) : NavigationRoute
    @Serializable
    data class DeadlineAdd(val id: Int?, val type : String) : NavigationRoute
    @Serializable
    data class InvoiceAdd(val id : Int?) : NavigationRoute
    @Serializable
    data class JobAdd(val jobId : Int?) : NavigationRoute
    @Serializable
    data class PurchaseInvoiceAdd(val purchaseInvoiceId : Int?) : NavigationRoute

    @Serializable
    data class SingleAddressSummary(val addressId: Int)  : NavigationRoute
    @Serializable
    data class SingleBubbleSummary(val bubbleId: Int) : NavigationRoute
    @Serializable
    data class SingleCustomerSummary(val customerId : String) : NavigationRoute
    @Serializable
    data class SingleDeadlineSummary(val expenseId : Int, val type : String): NavigationRoute
    @Serializable
    data class SingleInvoiceSummary(val invoiceId : Int) : NavigationRoute
    @Serializable
    data class SingleJobSummary(val jobId : Int) : NavigationRoute
    @Serializable
    data class SingleMaterialSummary(val materialId : Int) : NavigationRoute
    @Serializable
    data class SinglePaymentSummary(val paymentId : Int) : NavigationRoute
    @Serializable
    data class SinglePurchaseInvoiceSummary(val purchaseInvoiceId : Int) : NavigationRoute
    @Serializable
    data class SingleWorkSiteSummary(val workSiteId : Int) : NavigationRoute

    @Serializable
    data class Select(val textSearch : String, val items: SelectKey) : NavigationRoute
    @Serializable
    data class DayCalendar (val date : Long) : NavigationRoute

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
        composable<NavigationRoute.Home>{
            val homeVM = koinViewModel<HomeViewModel>()
            val state by homeVM.state.collectAsStateWithLifecycle()
            HomeActivity(state, homeVM.actions, navController)
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
            val allDeadlinesSummaryVM = koinViewModel<AllDeadlinesSummaryViewModel>()
            val state by allDeadlinesSummaryVM.state.collectAsStateWithLifecycle()
            AllDeadlinesSummaryActivity(state, allDeadlinesSummaryVM.actions, navController)
        }
        composable<NavigationRoute.AllInvoicesSummary> {
            val allInvoiceSummaryVM = koinViewModel<AllInvoicesSummaryViewModel>()
            val state by allInvoiceSummaryVM.state.collectAsStateWithLifecycle()
            AllInvoicesSummaryActivity(state, allInvoiceSummaryVM.actions, navController)
        }
        composable<NavigationRoute.AllJobsSummary>{
            val allJobsSummaryVM = koinViewModel<AllJobsSummaryViewModel>()
            val state by allJobsSummaryVM.state.collectAsStateWithLifecycle()
            AllJobsSummaryActivity(state, allJobsSummaryVM.actions, navController)
        }
        composable<NavigationRoute.AllPaymentsSummary>{
            val allPaymentsSummaryVM = koinViewModel<AllPaymentsSummaryViewModel>()
            val state by allPaymentsSummaryVM.state.collectAsStateWithLifecycle()
            AllPaymentsSummaryActivity(state, allPaymentsSummaryVM.actions, navController)
        }
        composable<NavigationRoute.AllPurchaseInvoicesSummary>{
            val allPurchaseInvoiceSummaryVM = koinViewModel<AllPurchaseInvoicesSummaryViewModel>()
            val state by allPurchaseInvoiceSummaryVM.state.collectAsStateWithLifecycle()
            AllPurchaseInvoicesSummaryActivity(state, allPurchaseInvoiceSummaryVM.actions, navController)
        }
        composable<NavigationRoute.AllStatistics>{
            AllStatisticsActivity(navController)
        }
        composable<NavigationRoute.Warehouse> {
            WarehouseActivity(navController)
        }
        composable<NavigationRoute.AllConstructionSummary> {
            AllConstructionSummaryActivity(navController)
        }


        composable<NavigationRoute.AddressAdd> {backStackEntry ->
            val route = backStackEntry.toRoute<NavigationRoute.AddressAdd>()
            val addressAddVM = koinViewModel<AddressAddViewModel>()
            val state by addressAddVM.state.collectAsStateWithLifecycle()
            AddressAddActivity(route.addressId, state, addressAddVM.actions, navController)
        }
        composable<NavigationRoute.BubbleAdd>{backStackEntry ->
            val route = backStackEntry.toRoute<NavigationRoute.BubbleAdd>()
            val bubbleAddVM = koinViewModel<BubbleAddViewModel>()
            val state by bubbleAddVM.state.collectAsStateWithLifecycle()
            BubbleAddActivity(route.bubbleId, state, bubbleAddVM.actions, navController)
        }
        composable<NavigationRoute.CustomerAdd>{ backStackEntry ->
            val route = backStackEntry.toRoute<NavigationRoute.CustomerAdd>()
            val customerAddVM = koinViewModel<CustomerAddViewModel>()
            val state by customerAddVM.state.collectAsStateWithLifecycle()
            CustomerAddActivity(route.customerId, state, customerAddVM.actions, navController)
        }
        composable<NavigationRoute.DeadlineAdd>{ backStackEntry ->
            val route = backStackEntry.toRoute<NavigationRoute.DeadlineAdd>()
            val deadlineAddVM = koinViewModel<DeadlineAddViewModel>()
            val state by deadlineAddVM.state.collectAsStateWithLifecycle()
            DeadlineAddActivity(route.id, DeadlineType.valueOf(route.type), state, deadlineAddVM.actions, navController)
        }
        composable<NavigationRoute.InvoiceAdd>{
            InvoiceAddActivity(navController)
        }
        composable<NavigationRoute.JobAdd>{ backStackEntry ->
            val route = backStackEntry.toRoute<NavigationRoute.JobAdd>()
            val jobAddVM = koinViewModel<JobAddViewModel>()
            val state by jobAddVM.state.collectAsStateWithLifecycle()
            JobAddActivity(route.jobId, state, jobAddVM.actions, navController)
        }
        composable<NavigationRoute.MaterialAdd>{
            MaterialAddActivity(navController)
        }
        composable<NavigationRoute.PaymentAdd>{
            PaymentAddActivity(navController)
        }
        composable<NavigationRoute.PurchaseInvoiceAdd>{
            PurchaseInvoiceAddActivity(navController)
        }
        composable<NavigationRoute.WorkSiteAdd> {
            ConstructionAddActivity(navController)
        }


        composable<NavigationRoute.SingleAddressSummary> {backStackEntry ->
            val route = backStackEntry.toRoute<NavigationRoute.SingleAddressSummary>()
            val singleAddressVM = koinViewModel<SingleAddressSummaryViewModel>()
            val state by singleAddressVM.state.collectAsStateWithLifecycle()
            SingleAddressSummaryActivity(route.addressId, state, singleAddressVM.actions, navController)
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
        composable<NavigationRoute.SingleDeadlineSummary>{backStackEntry ->
            val route = backStackEntry.toRoute<NavigationRoute.SingleDeadlineSummary>()
            val singleDeadlineSummaryVM = koinViewModel<SingleDeadlineSummaryViewModel>()
            val state by singleDeadlineSummaryVM.state.collectAsStateWithLifecycle()
            SingleDeadlineSummaryActivity(route.expenseId, DeadlineType.valueOf(route.type), state,
                singleDeadlineSummaryVM.actions, navController)
        }
        composable<NavigationRoute.SingleInvoiceSummary>{backStackEntry ->
            val route = backStackEntry.toRoute<NavigationRoute.SingleInvoiceSummary>()
            val singleInvoicesSummaryVM = koinViewModel<SingleInvoiceSummaryViewModel>()
            val state by singleInvoicesSummaryVM.state.collectAsStateWithLifecycle()
            SingleInvoiceSummaryActivity(route.invoiceId, state, singleInvoicesSummaryVM.actions, navController)
        }
        composable<NavigationRoute.SingleJobSummary>{ backStackEntry ->
            val route = backStackEntry.toRoute<NavigationRoute.SingleJobSummary>()
            val singleJobVM = koinViewModel<SingleJobSummaryViewModel>()
            val state by singleJobVM.state.collectAsStateWithLifecycle()
            SingleJobSummaryActivity(route.jobId, state, singleJobVM.actions, navController)
        }
        composable<NavigationRoute.SingleMaterialSummary>{backStackEntry ->
            val route = backStackEntry.toRoute<NavigationRoute.SingleMaterialSummary>()
            val singleMaterialSummaryVM = koinViewModel<SingleMaterialSummaryViewModel>()
            val state by singleMaterialSummaryVM.state.collectAsStateWithLifecycle()
            SingleMaterialSummaryActivity(route.materialId, state, singleMaterialSummaryVM.actions, navController)
        }
        composable<NavigationRoute.SinglePaymentSummary>{ backStackEntry ->
            val route = backStackEntry.toRoute<NavigationRoute.SinglePaymentSummary>()
            val singlePaymentSummaryVM = koinViewModel<SinglePaymentSummaryViewModel>()
            val state by singlePaymentSummaryVM.state.collectAsStateWithLifecycle()
            SinglePaymentSummaryActivity(route.paymentId, state, singlePaymentSummaryVM.actions, navController)
        }
        composable<NavigationRoute.SinglePurchaseInvoiceSummary>{
            SinglePurchaseInvoiceSummaryActivity(navController)
        }
        composable<NavigationRoute.SingleWorkSiteSummary> {
            SingleConstructionSummaryActivity(navController)
        }


        composable<NavigationRoute.Cart>{
            val cartVM = koinViewModel<CartViewModel>()
            val state by cartVM.state.collectAsStateWithLifecycle()
            CartActivity(state, cartVM.actions, navController)
        }
        composable<NavigationRoute.DayCalendar>{backStackEntry ->
            val route = backStackEntry.toRoute<NavigationRoute.DayCalendar>()
            val dayCalendarVM = koinViewModel<DayCalendarViewModel>()
            val state by dayCalendarVM.state.collectAsStateWithLifecycle()
            DayCalendarActivity(route.date, state, dayCalendarVM.actions, navController)
        }
        composable<NavigationRoute.TodayCalendar>{
            val todayCalendarVM = koinViewModel<TodayCalendarViewModel>()
            val state by todayCalendarVM.state.collectAsStateWithLifecycle()
            TodayCalendarActivity(state, navController)
        }


        composable<NavigationRoute.AveragePaymentsTimesStatistics>{
            AveragePaymentsTimesStatisticsActivity(navController)
        }
        composable<NavigationRoute.JobStatistics>{
            JobStatisticsActivity(navController)
        }


        composable<NavigationRoute.Select> { backStackEntry ->
            val route = backStackEntry.toRoute<NavigationRoute.Select>()
            val selectVm = koinViewModel<SelectViewModel>()
            val state by selectVm.state.collectAsStateWithLifecycle()
            val previousBackStackEntry = navController.previousBackStackEntry
            val selectedItems = previousBackStackEntry?.savedStateHandle
                ?.get<List<String>>("initialIds") ?: emptyList()
            LaunchedEffect(route.items) {
                selectVm.actions.populateUI(route.textSearch, route.items, selectedItems)
            }
            SelectActivity(state, selectVm.actions, navController)
        }
        composable<NavigationRoute.JobMaterials>{backStackEntry ->
            val parentEntry = remember(backStackEntry) {
                navController.getBackStackEntry<NavigationRoute.JobAdd>()
            }
            val jobAddVM = koinViewModel<JobAddViewModel>(viewModelStoreOwner = parentEntry)
            val state by jobAddVM.state.collectAsStateWithLifecycle()
            JobMaterialsActivity(state, jobAddVM.actions, navController)
        }
    }
}