package com.erapp.nimblesurvey.data

import com.erapp.nimblesurvey.data.api.NetworkResponse
import com.erapp.nimblesurvey.data.api.NimbleSurveyApiService
import com.erapp.nimblesurvey.data.datastore.DataStorePreferencesRepository
import com.erapp.nimblesurvey.data.models.ErrorResponse
import com.erapp.nimblesurvey.data.models.LoginRequest
import com.erapp.nimblesurvey.data.models.LoginResponse
import com.erapp.nimblesurvey.data.models.LogoutRequest
import com.erapp.nimblesurvey.data.models.ProfileResponse
import com.erapp.nimblesurvey.data.models.UserInfo
import com.erapp.nimblesurvey.data.result.Result
import com.erapp.nimblesurvey.utils.mapApiResponseToResult
import com.erapp.nimblesurvey.utils.mapResponseToResult
import kotlinx.coroutines.flow.first
import javax.inject.Inject

interface NimbleAuthRepository {
    suspend fun login(email: String, password: String): Result<LoginResponse, ErrorResponse>
    suspend fun logout(): Result<Unit, ErrorResponse>
    suspend fun getProfile(): Result<ProfileResponse, *>
}

class NimbleAuthRepositoryImpl @Inject constructor(
    private val nimbleApiService: NimbleSurveyApiService,
    private val dataStorePreferencesRepository: DataStorePreferencesRepository
) : NimbleAuthRepository {
    override suspend fun login(
        email: String,
        password: String
    ): Result<LoginResponse, ErrorResponse> = mapApiResponseToResult {
        val result = nimbleApiService.login(
            LoginRequest(
                email = email,
                password = password
            )
        )
        // store token
        when(result) {
            is NetworkResponse.Success -> {
                dataStorePreferencesRepository.saveUserCredentials(
                    UserInfo(
                        accessToken = result.body.data?.attributes?.accessToken,
                        refreshToken = result.body.data?.attributes?.refreshToken,
                        expiresIn = result.body.data?.attributes?.expiresIn,
                        createdAt = result.body.data?.attributes?.createdAt
                    )
                )
            }
            else -> Unit
        }
        result
    }

    override suspend fun logout(): Result<Unit, ErrorResponse> = mapResponseToResult {
        val token = dataStorePreferencesRepository.userCredentials.first()?.accessToken
        val result = nimbleApiService.logout(
            LogoutRequest(token = token)
        )
        // clear token
        when(result) {
            is NetworkResponse.Success -> {
                dataStorePreferencesRepository.saveUserCredentials(UserInfo())
            }
            else -> Unit
        }
        result
    }

    override suspend fun getProfile(): Result<ProfileResponse, *> = mapApiResponseToResult {
        nimbleApiService.getProfile()
    }
}