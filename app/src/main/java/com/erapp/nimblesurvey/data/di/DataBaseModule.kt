package com.erapp.nimblesurvey.data.di

import android.content.Context
import com.erapp.nimblesurvey.data.database.NimbleSurveyDataBase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataBaseModule {

    @Singleton
    @Provides
    fun provideDatabase(@ApplicationContext appContext: Context): NimbleSurveyDataBase {
        return NimbleSurveyDataBase.getInstance(appContext)
    }

    @Singleton
    @Provides
    fun provideSurveyDao(db: NimbleSurveyDataBase) = db.surveyDao()
}