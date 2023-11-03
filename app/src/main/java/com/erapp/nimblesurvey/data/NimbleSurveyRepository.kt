package com.erapp.nimblesurvey.data

import com.erapp.nimblesurvey.data.api.NimbleSurveyApiService
import com.erapp.nimblesurvey.data.models.Survey
import javax.inject.Inject
import com.erapp.nimblesurvey.data.result.Result
import com.erapp.nimblesurvey.utils.mapApiResponseToResult

interface NimbleSurveyRepository {
    suspend fun getSurveys(
        pageSize: Int? = null,
    ): Result<List<Survey>, *>
}

class NimbleSurveyRepositoryImpl @Inject constructor(
    private val apiService: NimbleSurveyApiService
) : NimbleSurveyRepository {
    override suspend fun getSurveys(
        pageSize: Int?
    ): Result<List<Survey>, *> = mapApiResponseToResult {
        apiService.getSurveyList(pageSize = pageSize)
    }
}