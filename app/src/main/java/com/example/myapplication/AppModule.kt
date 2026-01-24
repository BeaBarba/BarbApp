package com.example.myapplication

import androidx.room.Room
import com.example.myapplication.data.database.BarbAppDatabase
import com.example.myapplication.data.repository.Repository
import com.example.myapplication.ui.screen.Address.add.AddressAddViewModel
import com.example.myapplication.ui.screen.Address.singleSummary.SingleAddressSummaryViewModel
import com.example.myapplication.ui.screen.Bubble.allSummary.AllBubblesSummaryViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module


val appModule = module{

    single{
        Room.databaseBuilder(
            get(),
            BarbAppDatabase::class.java,
            "database"
        ).build()
    }

    single{get<BarbAppDatabase>().barbAppDAO()}

    single{Repository(get())}

    viewModel {AddressAddViewModel(get())}
    viewModel {SingleAddressSummaryViewModel()}
    viewModel {AllBubblesSummaryViewModel()}
}