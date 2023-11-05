package com.erapp.nimblesurvey.utils

import com.erapp.nimblesurvey.data.database.entities.SurveyEntity
import com.erapp.nimblesurvey.data.models.Survey

fun Survey.toSurveyEntity(): SurveyEntity {
    return SurveyEntity(
        title = this.attributes.title,
        description = this.attributes.description,
        coverImageUrl = this.attributes.coverImageUrl,
        createdAt = this.attributes.createdAt
    )
}