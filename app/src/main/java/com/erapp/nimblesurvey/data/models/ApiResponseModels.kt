package com.erapp.nimblesurvey.data.models

data class ApiResponse <T>(
    val data: T?,
)

// just in case pagination is needed todo: remove if not needed
data class PaginatedApiResponse <T>(
    val data: T?,
    val meta: Meta?
)

data class ErrorResponse(
    val errors: List<Error>?
)

data class Error(
    val code: String? = null,
    val detail: String? = null,
    val source: String? = null,
)
