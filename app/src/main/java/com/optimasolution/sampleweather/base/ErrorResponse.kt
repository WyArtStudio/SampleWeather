package com.optimasolution.sampleweather.base

import com.google.gson.annotations.SerializedName

data class ErrorResponse(
    @SerializedName("code")
    val code: Int?,
    @SerializedName("details")
    val details: String?,
    @SerializedName("hint")
    val hint: String?,
    @SerializedName("message")
    val message: String?
)