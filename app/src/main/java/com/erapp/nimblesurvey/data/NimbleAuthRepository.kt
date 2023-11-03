package com.erapp.nimblesurvey.data

import com.erapp.nimblesurvey.data.api.NimbleSurveyApiService
import com.erapp.nimblesurvey.data.models.ErrorResponse
import com.erapp.nimblesurvey.data.models.Login
import com.erapp.nimblesurvey.data.models.LoginResponse
import com.erapp.nimblesurvey.utils.mapApiResponseToResult
import com.erapp.nimblesurvey.data.result.Result
import javax.inject.Inject

interface NimbleAuthRepository {
    suspend fun login(email: String, password: String): Result<LoginResponse, ErrorResponse>
}

class NimbleAuthRepositoryImpl @Inject constructor(
    private val apiService: NimbleSurveyApiService
) : NimbleAuthRepository {
    override suspend fun login(
        email: String,
        password: String
    ): Result<LoginResponse, ErrorResponse> = mapApiResponseToResult {
        apiService.login(
            Login(
                email = email,
                password = password
            )
        )
    }
}