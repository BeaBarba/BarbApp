package com.example.myapplication.ui.screen.Home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.data.modules.Theme
import com.example.myapplication.data.repository.ThemeRepository
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

data class HomeState(
    val theme : Theme
)

interface HomeActions {
    fun changeThemeToLight()
    fun changeThemeToDark()
    fun changeThemeToSystem()
}

class HomeViewModel(
    private val repository: ThemeRepository
) : ViewModel(){

    val state = repository.theme.map { HomeState(theme = it) }.stateIn(
        scope = this.viewModelScope,
        started = SharingStarted.WhileSubscribed(),
        initialValue = HomeState(Theme.System)
    )

    val actions = object : HomeActions {

        override fun changeThemeToLight() {
            viewModelScope.launch {
                repository.setTheme(Theme.Light)
            }
        }

        override fun changeThemeToDark() {
            viewModelScope.launch {
                repository.setTheme(Theme.Dark)
            }
        }

        override fun changeThemeToSystem() {
            viewModelScope.launch {
                repository.setTheme(Theme.System)
            }
        }
    }
}