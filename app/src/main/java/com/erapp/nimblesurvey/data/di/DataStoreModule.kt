package com.erapp.nimblesurvey.data.di

import android.content.Context
import androidx.datastore.core.handlers.ReplaceFileCorruptionHandler
import androidx.datastore.preferences.SharedPreferencesMigration
import androidx.datastore.preferences.core.emptyPreferences
import androidx.datastore.preferences.preferencesDataStore
import com.erapp.nimblesurvey.data.datastore.DataStorePreferencesRepository
import com.erapp.nimblesurvey.data.datastore.DataStorePreferencesRepositoryImpl
import com.erapp.nimblesurvey.data.datastore.DataStorePreferencesRepositoryImpl.Companion.PREFS_NAME
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object PreferencesStorageModule {

    private val Context.dataStore by preferencesDataStore(
        name = PREFS_NAME,
        corruptionHandler = ReplaceFileCorruptionHandler(
            produceNewData = { emptyPreferences() }
        ),
        produceMigrations = { context ->
            listOf(
                SharedPreferencesMigration(context, PREFS_NAME)
            )
        },
        scope = CoroutineScope(Dispatchers.IO + SupervisorJob())
    )

    @Singleton
    @Provides
    fun providePreferenceStorage(@ApplicationContext context: Context): DataStorePreferencesRepository =
        DataStorePreferencesRepositoryImpl(context.dataStore)
}