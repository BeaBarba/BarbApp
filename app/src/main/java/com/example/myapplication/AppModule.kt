package com.example.myapplication

import com.example.myapplication.ui.screen.Address.add.AddressAddViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module


val appModule = module{
    viewModel {AddressAddViewModel()}
}