package com.example.myapplication

import androidx.room.Room
import com.example.myapplication.data.database.appDatabase
import com.example.myapplication.data.repository.Repository
import com.example.myapplication.ui.screen.Address.add.AddressAddViewModel
import com.example.myapplication.ui.screen.Address.singleSummary.SingleAddressSummaryViewModel
import com.example.myapplication.ui.screen.Bubble.add.BubbleAddViewModel
import com.example.myapplication.ui.screen.Bubble.allSummary.AllBubblesSummaryViewModel
import com.example.myapplication.ui.screen.Bubble.singleSummary.SingleBubbleSummaryViewModel
import com.example.myapplication.ui.screen.Calendar.day.DayCalendarViewModel
import com.example.myapplication.ui.screen.Calendar.today.TodayCalendarViewModel
import com.example.myapplication.ui.screen.Customer.add.CustomerAddViewModel
import com.example.myapplication.ui.screen.Customer.allSummary.AllCustomersSummaryViewModel
import com.example.myapplication.ui.screen.Customer.singleSummary.SingleCustomerSummaryViewModel
import com.example.myapplication.ui.screen.Job.add.JobAddViewModel
import com.example.myapplication.ui.screen.Select.SelectViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module


val appModule = module{

    single{
        Room.databaseBuilder(
            get(),
            appDatabase::class.java,
            "database"
        )
            .fallbackToDestructiveMigration(true)
            .build()
    }

    /* DAO */
    single{get<appDatabase>().airConditionerDAO()}
    single{get<appDatabase>().materialDAO()}
    single{get<appDatabase>().sellerDAO()}
    single{get<appDatabase>().purchaseInvoiceDAO()}
    single{get<appDatabase>().purchaseDAO()}
    single{get<appDatabase>().bubbleDAO()}
    single{get<appDatabase>().deliveriesDAO()}
    single{get<appDatabase>().categoryPurchaseInvoiceDAO()}
    single{get<appDatabase>().singleExpenseDAO()}
    single{get<appDatabase>().recurringExpenseDAO()}
    single{get<appDatabase>().paymentDAO()}
    single{get<appDatabase>().recurringPaymentDAO()}
    single{get<appDatabase>().imageDAO()}
    single{get<appDatabase>().materialPhotoDAO()}
    single{get<appDatabase>().jobPhotoDAO()}
    single{get<appDatabase>().customerProvisionDAO()}
    single{get<appDatabase>().customerDAO()}
    single{get<appDatabase>().privateDAO()}
    single{get<appDatabase>().companyDAO()}
    single{get<appDatabase>().referenceDAO()}
    single{get<appDatabase>().referralDAO()}
    single{get<appDatabase>().phoneNumberDAO()}
    single{get<appDatabase>().propertyOwnershipDAO()}
    single{get<appDatabase>().addressDAO()}
    single{get<appDatabase>().workSiteDAO()}
    single{get<appDatabase>().jobDAO()}
    single{get<appDatabase>().jobMaterialDAO()}
    single{get<appDatabase>().materialUsageDAO()}
    single{get<appDatabase>().revenuesDAO()}
    single{get<appDatabase>().workSiteRevenueDAO()}
    single{get<appDatabase>().jobRevenueDAO()}
    single{get<appDatabase>().customerFullDetailsDAO()}

    single{Repository(get(), get(), get(), get(), get(), get (),
                get(), get(), get(), get(), get(), get(), get(), get(),
                get(), get (), get(), get(), get(), get(), get(), get(),
                get(), get(), get(), get (), get(), get(), get(), get(),
                get(), get()
    )}
    /* Select */
    viewModel {SelectViewModel(get())}

    /* Address */
    viewModel {AddressAddViewModel(get())}
    viewModel {SingleAddressSummaryViewModel(get())}

    /* Calendar */
    viewModel {TodayCalendarViewModel()}
    viewModel {DayCalendarViewModel()}

    /* Customer */
    viewModel {AllCustomersSummaryViewModel()}
    viewModel {SingleCustomerSummaryViewModel()}
    viewModel {CustomerAddViewModel(get())}

    /* Bubbles */
    viewModel {AllBubblesSummaryViewModel()}
    viewModel {BubbleAddViewModel(get())}
    viewModel {SingleBubbleSummaryViewModel()}

    /* Job */
    viewModel {JobAddViewModel(get())}
}