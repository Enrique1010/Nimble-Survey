package com.erapp.nimblesurvey.data.models

data class ForgotPasswordResponse(
    val meta: ForgotPasswordMeta
)

data class ForgotPasswordMeta(
    val message: String
)
