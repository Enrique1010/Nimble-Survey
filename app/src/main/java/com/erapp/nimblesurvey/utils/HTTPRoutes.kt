package com.erapp.nimblesurvey.utils

import com.erapp.nimblesurvey.BuildConfig

object HTTPRoutes {
    private const val BASE_URL = BuildConfig.NIMBLE_BASE_URL

    // auth endpoints
    const val AUTH = "${BASE_URL}/oauth/token"

    // survey endpoints
    const val SURVEY_LIST = "${BASE_URL}/surveys"
}