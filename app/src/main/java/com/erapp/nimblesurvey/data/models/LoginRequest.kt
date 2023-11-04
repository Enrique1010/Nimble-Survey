package com.erapp.nimblesurvey.data.models

import com.erapp.nimblesurvey.BuildConfig
import com.erapp.nimblesurvey.utils.GrantTypes
import com.google.gson.annotations.SerializedName

data class LoginRequest(
    @SerializedName("grant_type")
    val grantType: String = GrantTypes.PASSWORD.value,
    @SerializedName("email")
    val email: String,
    @SerializedName("password")
    val password: String,
    @SerializedName("client_id")
    val clientId: String = BuildConfig.NIMBLE_KEY,
    @SerializedName("client_secret")
    val clientSecret: String = BuildConfig.NIMBLE_SECRET
)