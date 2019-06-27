package com.lagos.data.models

import com.google.gson.annotations.SerializedName

data class Education (
    @SerializedName("name") val name : String,
    @SerializedName("type") val type : String,
    @SerializedName("image") val image : String
)