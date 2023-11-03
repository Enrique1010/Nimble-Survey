package com.erapp.nimblesurvey.utils

fun String.isNotBlankOrEmpty(): Boolean {
    return this.isNotBlank() && this.isNotEmpty()
}