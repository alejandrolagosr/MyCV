package com.lagos.domain.models

import com.google.gson.annotations.SerializedName

data class Profile (
    @SerializedName("summary") val summary : String? = null,
    @SerializedName("name") val name : String? = null,
    @SerializedName("image") val image : String? = null
)