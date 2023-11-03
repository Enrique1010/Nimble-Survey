package com.erapp.nimblesurvey.data.models


import com.google.gson.annotations.SerializedName

data class Questions(
    @SerializedName("data")
    val `data`: List<QuestionReference>
)