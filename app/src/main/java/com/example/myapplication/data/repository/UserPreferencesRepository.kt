package com.example.myapplication.data.repository

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import com.example.myapplication.data.modules.Theme
import kotlinx.coroutines.flow.map

class UserPreferencesRepository (
    private val dataStore : DataStore<Preferences>
){
    companion object{
        private val THEME_KEY = stringPreferencesKey("theme")

        private val LOCATION_REQUESTED_KEY = booleanPreferencesKey("location_requested")
    }

    val theme = dataStore.data.map { preferences ->
        try {
            Theme.valueOf(preferences[THEME_KEY] ?: "System")
        }catch (_ : Exception){
            Theme.System
        }
    }

    val isLocationRequested = dataStore.data.map { preferences ->
        preferences[LOCATION_REQUESTED_KEY] ?: false
    }

    suspend fun setTheme(theme : Theme) = dataStore.edit { preferences ->
        preferences[THEME_KEY] = theme.toString()
    }

    suspend fun setLocationRequested(requested: Boolean) = dataStore.edit {
        it[LOCATION_REQUESTED_KEY] = requested
    }
}