package com.erapp.nimblesurvey.data.models

import com.erapp.nimblesurvey.BuildConfig
import com.erapp.nimblesurvey.utils.GrantTypes
import com.google.gson.annotations.SerializedName

data class RefreshTokenRequest(
    @SerializedName("grant_type")
    val grantType: String = GrantTypes.REFRESH.value,
    @SerializedName("refresh_token")
    val refreshToken: String,
    @SerializedName("client_id")
    val clientId: String = BuildConfig.NIMBLE_KEY,
    @SerializedName("client_secret")
    val clientSecret: String = BuildConfig.NIMBLE_SECRET
)