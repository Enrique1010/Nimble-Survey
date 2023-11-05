package com.erapp.nimblesurvey.data.models


import com.google.gson.annotations.SerializedName

data class ForgotPasswordInfo(
    @SerializedName("email")
    val email: String
)