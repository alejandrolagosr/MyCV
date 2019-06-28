package com.lagos.data.models

import com.google.gson.annotations.SerializedName

data class ProfileModel(
    @SerializedName("summary") val summary: String? = null,
    @SerializedName("name") val name: String? = null,
    @SerializedName("image") val image: String? = null
)