package com.example.myapplication

import android.content.Context
import androidx.datastore.preferences.preferencesDataStore
import androidx.room.Room
import com.example.myapplication.data.database.AppDatabase
import com.example.myapplication.data.repository.Repository
import com.example.myapplication.data.repository.ThemeRepository
import com.example.myapplication.ui.screen.Address.add.AddressAddViewModel
import com.example.myapplication.ui.screen.Address.singleSummary.SingleAddressSummaryViewModel
import com.example.myapplication.ui.screen.Bubble.add.BubbleAddViewModel
import com.example.myapplication.ui.screen.Bubble.allSummary.AllBubblesSummaryViewModel
import com.example.myapplication.ui.screen.Bubble.singleSummary.SingleBubbleSummaryViewModel
import com.example.myapplication.ui.screen.Calendar.day.DayCalendarViewModel
import com.example.myapplication.ui.screen.Calendar.today.TodayCalendarViewModel
import com.example.myapplication.ui.screen.Cart.CartViewModel
import com.example.myapplication.ui.screen.Customer.add.CustomerAddViewModel
import com.example.myapplication.ui.screen.Customer.allSummary.AllCustomersSummaryViewModel
import com.example.myapplication.ui.screen.Customer.singleSummary.SingleCustomerSummaryViewModel
import com.example.myapplication.ui.screen.Home.HomeViewModel
import com.example.myapplication.ui.screen.Job.add.JobAddViewModel
import com.example.myapplication.ui.screen.Job.allSummary.AllJobsSummaryViewModel
import com.example.myapplication.ui.screen.Job.singleSummary.SingleJobSummaryViewModel
import com.example.myapplication.ui.screen.Material.singleSummary.SingleMaterialSummaryViewModel
import com.example.myapplication.ui.screen.PurchaseInvoice.allSummary.AllPurchaseInvoicesSummaryViewModel
import com.example.myapplication.ui.screen.Select.SelectViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val Context.dataStore by preferencesDataStore("themeToggle")

val appModule = module{

    single{get<Context>().dataStore}

    single{ThemeRepository(get())}

    single{
        Room.databaseBuilder(
            get(),
            AppDatabase::class.java,
            "database"
        )
            .fallbackToDestructiveMigration(true)
            .build()
    }

    /* DAO */
    single{get<AppDatabase>().airConditionerDAO()}
    single{get<AppDatabase>().materialDAO()}
    single{get<AppDatabase>().sellerDAO()}
    single{get<AppDatabase>().purchaseInvoiceDAO()}
    single{get<AppDatabase>().purchaseDAO()}
    single{get<AppDatabase>().bubbleDAO()}
    single{get<AppDatabase>().deliveriesDAO()}
    single{get<AppDatabase>().categoryPurchaseInvoiceDAO()}
    single{get<AppDatabase>().singleExpenseDAO()}
    single{get<AppDatabase>().recurringExpenseDAO()}
    single{get<AppDatabase>().paymentDAO()}
    single{get<AppDatabase>().recurringPaymentDAO()}
    single{get<AppDatabase>().imageDAO()}
    single{get<AppDatabase>().customerProvisionDAO()}
    single{get<AppDatabase>().customerDAO()}
    single{get<AppDatabase>().privateDAO()}
    single{get<AppDatabase>().companyDAO()}
    single{get<AppDatabase>().referenceDAO()}
    single{get<AppDatabase>().referralDAO()}
    single{get<AppDatabase>().phoneNumberDAO()}
    single{get<AppDatabase>().propertyOwnershipDAO()}
    single{get<AppDatabase>().addressDAO()}
    single{get<AppDatabase>().workSiteDAO()}
    single{get<AppDatabase>().jobDAO()}
    single{get<AppDatabase>().jobMaterialDAO()}
    single{get<AppDatabase>().materialUsageDAO()}
    single{get<AppDatabase>().revenuesDAO()}

    single{get<AppDatabase>().cartDAO()}

    single{Repository(get())}

    /* Home */
    viewModel {HomeViewModel(get())}

    /* Cart */
    viewModel {CartViewModel(get())}

    /* Customer */
    viewModel {AllCustomersSummaryViewModel(get())}
    viewModel {SingleCustomerSummaryViewModel(get())}
    viewModel {CustomerAddViewModel(get())}

    /* Select */
    viewModel {SelectViewModel(get())}

    /* Address */
    viewModel {AddressAddViewModel(get())}
    viewModel {SingleAddressSummaryViewModel(get())}

    /* Calendar */
    viewModel {TodayCalendarViewModel(get())}
    viewModel {DayCalendarViewModel(get())}

    /* Bubbles */
    viewModel {AllBubblesSummaryViewModel(get())}
    viewModel {BubbleAddViewModel(get())}
    viewModel {SingleBubbleSummaryViewModel(get())}

    /* Job */
    viewModel {AllJobsSummaryViewModel(get())}
    viewModel {JobAddViewModel(get())}
    viewModel {SingleJobSummaryViewModel(get())}

    /* Material */
    viewModel {SingleMaterialSummaryViewModel(get())}

    /* Purchase Invoice */
    viewModel {AllPurchaseInvoicesSummaryViewModel(get())}
}