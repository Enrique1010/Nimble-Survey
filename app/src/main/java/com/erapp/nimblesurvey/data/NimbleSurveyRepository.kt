package com.erapp.nimblesurvey.data

import com.erapp.nimblesurvey.data.api.NimbleSurveyApiService
import com.erapp.nimblesurvey.data.models.Survey
import javax.inject.Inject
import com.erapp.nimblesurvey.data.result.Result
import com.erapp.nimblesurvey.utils.mapApiResponseToResult

interface NimbleSurveyRepository {
    suspend fun getSurveys(): Result<List<Survey>, *>
}

class NimbleSurveyRepositoryImpl @Inject constructor(
    private val apiService: NimbleSurveyApiService
) : NimbleSurveyRepository {
    override suspend fun getSurveys(): Result<List<Survey>, *> = mapApiResponseToResult {
        val randomPageSize = (6..15).random() // simulate daily data flow
        apiService.getSurveyList(
            page = 1,
            pageSize = randomPageSize
        )
    }
}