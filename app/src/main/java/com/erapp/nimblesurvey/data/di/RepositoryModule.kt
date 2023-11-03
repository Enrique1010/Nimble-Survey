package com.erapp.nimblesurvey.data.di

import com.erapp.nimblesurvey.data.NimbleAuthRepository
import com.erapp.nimblesurvey.data.NimbleAuthRepositoryImpl
import com.erapp.nimblesurvey.data.NimbleSurveyRepository
import com.erapp.nimblesurvey.data.NimbleSurveyRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun bindNimbleAuthRepository(
        nimbleAuthRepository: NimbleAuthRepositoryImpl
    ): NimbleAuthRepository

    @Binds
    @Singleton
    abstract fun bindNimbleSurveyRepository(
        nimbleSurveyRepository: NimbleSurveyRepositoryImpl
    ): NimbleSurveyRepository
}