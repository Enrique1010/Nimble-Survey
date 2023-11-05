package com.erapp.nimblesurvey.data

import androidx.room.withTransaction
import com.erapp.nimblesurvey.data.api.NetworkResponse
import com.erapp.nimblesurvey.data.api.NimbleSurveyApiService
import com.erapp.nimblesurvey.data.database.NimbleSurveyDataBase
import com.erapp.nimblesurvey.data.database.entities.SurveyEntity
import com.erapp.nimblesurvey.utils.toSurveyEntity
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

interface NimbleSurveyRepository {
    fun getSurveys(): Flow<List<SurveyEntity>>
    suspend fun getSurveysFromNetwork()
}

class NimbleSurveyRepositoryImpl @Inject constructor(
    private val apiService: NimbleSurveyApiService,
    private val dataBase: NimbleSurveyDataBase
) : NimbleSurveyRepository {
    override fun getSurveys(): Flow<List<SurveyEntity>> {
        return dataBase.surveyDao().getSurveys()
    }

    override suspend fun getSurveysFromNetwork() {
        val randomPageSize = (6..15).random() // simulate daily data flow
        val result = apiService.getSurveyList(
            page = 1,
            pageSize = randomPageSize
        )
        when (result) {
            is NetworkResponse.Success -> {
                val surveys = result.body.data?.map { it.toSurveyEntity() }
                dataBase.withTransaction {
                    surveys?.let {
                        dataBase.surveyDao().clearAllSurveys()
                        dataBase.surveyDao().insertSurveys(surveys)
                    }
                }
            }
            else -> Unit // do nothing
        }
    }
}