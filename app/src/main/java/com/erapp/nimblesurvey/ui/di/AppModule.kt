package com.erapp.nimblesurvey.ui.di

import com.erapp.nimblesurvey.ui.notification.NotificationServiceImpl
import com.erapp.nimblesurvey.ui.notification.NotificationService
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class AppModule {

    @Binds
    abstract fun bindNotificationService(
        notificationService: NotificationServiceImpl
    ): NotificationService
}