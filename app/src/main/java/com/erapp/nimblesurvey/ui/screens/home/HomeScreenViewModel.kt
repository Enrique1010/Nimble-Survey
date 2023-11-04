package com.erapp.nimblesurvey.ui.screens.home

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.erapp.nimblesurvey.data.NimbleAuthRepository
import com.erapp.nimblesurvey.data.NimbleSurveyRepository
import com.erapp.nimblesurvey.data.models.Survey
import com.erapp.nimblesurvey.data.result.Result
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeScreenViewModel @Inject constructor(
    private val surveyRepository: NimbleSurveyRepository,
    private val authRepository: NimbleAuthRepository
): ViewModel() {

    var homeScreenData by mutableStateOf(HomeScreenData())
    var homeScreenState: HomeScreenState by mutableStateOf(HomeScreenState.Loading)

    init {
        getUserProfile()
        getSurveys()
    }

    private fun getUserProfile() {
        viewModelScope.launch {
            when(val result = authRepository.getProfile()) {
                is Result.Success -> {
                    homeScreenData = homeScreenData.copy(
                        userName = result.data?.profileAttributes?.name.orEmpty(),
                        avatarUrl = result.data?.profileAttributes?.avatarUrl.orEmpty()
                    )
                }
                else -> Unit
            }
        }
    }

    private fun getSurveys() {
        viewModelScope.launch {
            when(val result = surveyRepository.getSurveys()) {
                Result.Loading -> Unit
                is Result.Error -> {
                    homeScreenState = HomeScreenState.Error(result.message.orEmpty())
                }
                is Result.Success -> {
                    if (result.data?.isEmpty() == true) {
                        homeScreenState = HomeScreenState.Empty
                    } else {
                        homeScreenState = HomeScreenState.Success
                        homeScreenData = homeScreenData.copy(surveys = result.data.orEmpty())
                    }
                }
            }
        }
    }

    fun onEvent(event: HomeScreenEvent) {
        when (event) {
            is HomeScreenEvent.OnRefreshSurveys -> getSurveys()
        }
    }

    sealed interface HomeScreenEvent {
        data object OnRefreshSurveys : HomeScreenEvent
    }

    sealed interface HomeScreenState {
        data object Loading : HomeScreenState
        data object Success : HomeScreenState
        data object Empty : HomeScreenState
        data class Error(val message: String) : HomeScreenState
    }

    data class HomeScreenData(
        val userName: String = "",
        val avatarUrl: String = "",
        val surveys: List<Survey> = emptyList()
    )
}