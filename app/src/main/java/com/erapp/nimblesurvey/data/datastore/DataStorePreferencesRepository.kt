package com.erapp.nimblesurvey.data.datastore

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import javax.inject.Singleton

interface DataStorePreferencesRepository {
}

@Singleton
class DataStorePreferencesRepositoryImpl(
    private val dataStore: DataStore<Preferences>,
) : DataStorePreferencesRepository {

    private object PreferencesKeys {
        // todo: add preferences keys
    }

    // user preferences

    companion object {
        const val PREFS_NAME = "user_preferences"
    }
}