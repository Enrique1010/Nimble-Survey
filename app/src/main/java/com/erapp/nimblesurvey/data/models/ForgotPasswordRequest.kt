package com.erapp.nimblesurvey.data.models

import com.erapp.nimblesurvey.BuildConfig
import com.google.gson.annotations.SerializedName

data class ForgotPasswordRequest(
    @SerializedName("user")
    val forgotPasswordInfo: ForgotPasswordInfo,
    @SerializedName("client_id")
    val clientId: String = BuildConfig.NIMBLE_KEY,
    @SerializedName("client_secret")
    val clientSecret: String = BuildConfig.NIMBLE_SECRET,
)