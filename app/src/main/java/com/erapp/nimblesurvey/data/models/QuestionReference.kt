package com.erapp.nimblesurvey.data.models


import com.google.gson.annotations.SerializedName

data class QuestionReference(
    @SerializedName("id")
    val id: String,
    @SerializedName("type")
    val type: String
)