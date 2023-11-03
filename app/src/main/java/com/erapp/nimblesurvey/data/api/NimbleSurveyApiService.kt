package com.erapp.nimblesurvey.data.api

import com.erapp.nimblesurvey.utils.HTTPRoutes
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface NimbleSurveyApiService {

    // auth endpoints
    @POST(HTTPRoutes.AUTH)
    suspend fun login(
        @Body body: Any
    )

    @POST(HTTPRoutes.AUTH)
    suspend fun refresh(
        @Body body: Any
    )

    // survey endpoints
    @GET(HTTPRoutes.SURVEY_LIST)
    suspend fun getSurveyList(
        @Query("page[number]") page: Int? = null,
        @Query("page[size]") pageSize: Int? = null,
    )
}