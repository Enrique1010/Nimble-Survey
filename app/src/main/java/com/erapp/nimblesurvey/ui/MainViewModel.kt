package com.erapp.nimblesurvey.ui

import androidx.lifecycle.ViewModel
import com.erapp.nimblesurvey.data.datastore.DataStorePreferencesRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val dataStoreRepository: DataStorePreferencesRepository
): ViewModel() {

    val isAuthenticated: Boolean = runBlocking(Dispatchers.Default) {
        val savedLoginData = dataStoreRepository.userCredentials.first()
        savedLoginData?.accessToken != null
    }

    fun logout() {
        //todo: implement logout
    }
}