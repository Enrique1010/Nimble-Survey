package com.erapp.nimblesurvey.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.erapp.nimblesurvey.data.database.entities.SurveyEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface SurveyDao {
    @Query("SELECT * FROM survey")
    fun getSurveys(): Flow<List<SurveyEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertSurveys(surveys: List<SurveyEntity>)

    @Query("DELETE FROM survey")
    fun clearAllSurveys()
}