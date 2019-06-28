package com.lagos.data.models

import com.google.gson.annotations.SerializedName

data class ProfileModel (
    @SerializedName("summary") val summary : String,
    @SerializedName("name") val name : String,
    @SerializedName("image") val image : String
)