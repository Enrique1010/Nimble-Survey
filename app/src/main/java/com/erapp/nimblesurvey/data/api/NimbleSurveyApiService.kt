package com.erapp.nimblesurvey.data.api

import com.erapp.nimblesurvey.data.models.ApiResponse
import com.erapp.nimblesurvey.data.models.ErrorResponse
import com.erapp.nimblesurvey.data.models.LoginRequest
import com.erapp.nimblesurvey.data.models.LoginResponse
import com.erapp.nimblesurvey.data.models.LogoutRequest
import com.erapp.nimblesurvey.data.models.ProfileResponse
import com.erapp.nimblesurvey.data.models.RefreshTokenRequest
import com.erapp.nimblesurvey.data.models.Survey
import com.erapp.nimblesurvey.utils.HTTPRoutes
import com.erapp.nimblesurvey.utils.NotRequiredAuthorization
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface NimbleSurveyApiService {
    // auth endpoints
    @POST(HTTPRoutes.AUTH)
    @NotRequiredAuthorization
    suspend fun login(
        @Body body: LoginRequest
    ): NetworkResponse<ApiResponse<LoginResponse>, ErrorResponse>

    @POST(HTTPRoutes.AUTH)
    @NotRequiredAuthorization
    suspend fun refreshToken(
        @Body body: RefreshTokenRequest
    ): NetworkResponse<ApiResponse<LoginResponse>, ErrorResponse>

    @POST(HTTPRoutes.LOGOUT)
    @NotRequiredAuthorization
    suspend fun logout(
        @Body body: LogoutRequest
    ): NetworkResponse<Unit, ErrorResponse>

    @GET(HTTPRoutes.PROFILE)
    suspend fun getProfile(): NetworkResponse<ApiResponse<ProfileResponse>, ApiResponse<ProfileResponse>>

    // survey endpoints
    @GET(HTTPRoutes.SURVEY_LIST)
    suspend fun getSurveyList(
        @Query("page[number]") page: Int? = null,
        @Query("page[size]") pageSize: Int? = null,
    ): NetworkResponse<ApiResponse<List<Survey>>, ApiResponse<List<Survey>>>
}