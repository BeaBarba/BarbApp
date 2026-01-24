package com.example.myapplication

import androidx.room.Room
import com.example.myapplication.data.database.appDatabase
import com.example.myapplication.data.repository.Repository
import com.example.myapplication.ui.screen.Address.add.AddressAddViewModel
import com.example.myapplication.ui.screen.Address.singleSummary.SingleAddressSummaryViewModel
import com.example.myapplication.ui.screen.Bubble.allSummary.AllBubblesSummaryViewModel
import com.example.myapplication.ui.screen.Job.add.JobAddViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module


val appModule = module{

    single{
        Room.databaseBuilder(
            get(),
            appDatabase::class.java,
            "database"
        ).build()
    }

    single{get<appDatabase>().appDAO()}

    single{Repository(get())}

    /* Address */
    viewModel {AddressAddViewModel(get())}
    viewModel {SingleAddressSummaryViewModel(get())}

    /* Bubbles */
    viewModel {AllBubblesSummaryViewModel()}

    /* Job */
    viewModel {JobAddViewModel(get())}
}