package com.erapp.nimblesurvey.data.datastore

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import com.erapp.nimblesurvey.BuildConfig
import com.erapp.nimblesurvey.data.datastore.DataStorePreferencesRepositoryImpl.PreferencesKeys.PREF_CREDENTIAL_INFO
import com.erapp.nimblesurvey.data.models.UserInfo
import com.google.gson.Gson
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import javax.inject.Singleton

interface DataStorePreferencesRepository {
    suspend fun saveUserCredentials(value: UserInfo?)
    val userCredentials: Flow<UserInfo?>
}

@Singleton
class DataStorePreferencesRepositoryImpl(
    private val dataStore: DataStore<Preferences>,
    private val cryptoManager: CryptoManager
) : DataStorePreferencesRepository {

    val gson by lazy { Gson() }
    private val json = Json { encodeDefaults = true }

    // preferences keys
    private object PreferencesKeys {
        val PREF_CREDENTIAL_INFO = stringPreferencesKey(PREF_CREDENTIAL_INFO_KEY)
    }

    // user preferences
    override suspend fun saveUserCredentials(value: UserInfo?) {
        dataStore.edit {
            val ciphertextWrapper = cryptoManager.encryptData(
                BuildConfig.NIMBLE_DATA_STORE_KEY,
                Json.encodeToString(value)
            )

            it[PREF_CREDENTIAL_INFO] = gson.toJson(ciphertextWrapper)
        }
    }

    override val userCredentials: Flow<UserInfo?>
        get() = dataStore.data.map { value ->
            val userInfo = value[PREF_CREDENTIAL_INFO] ?: return@map null
            val ciphertextWrapper =
                gson.fromJson(userInfo, CiphertextWrapper::class.java)

            val decryptedValue = cryptoManager.decryptData(
                BuildConfig.NIMBLE_DATA_STORE_KEY,
                ciphertextWrapper.ciphertext,
                ciphertextWrapper.initializationVector
            )
            json.decodeFromString(decryptedValue)
        }

    companion object {
        const val PREFS_NAME = "user_preferences"
        const val PREF_CREDENTIAL_INFO_KEY = "credential_info"
    }
}